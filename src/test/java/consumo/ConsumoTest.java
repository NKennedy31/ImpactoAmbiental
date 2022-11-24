package consumo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Consumo.FactorDeEmision;
import Consumo.Imputacion;
import Consumo.NombreTipoConsumo;
import Consumo.TipoConsumo;
import Consumo.Unidad;
import Trayecto.Ubicacion;
import impacto_ambiental.Clasificacion;
import impacto_ambiental.Organizacion;
import impacto_ambiental.TipoOrganizacion;

public class ConsumoTest {
  TipoConsumo gasNatural = new TipoConsumo(NombreTipoConsumo.GASNATURAL, Unidad.M3);
  TipoConsumo diesel = new TipoConsumo(NombreTipoConsumo.DIESEL, Unidad.LT); //Diesel/Gasoil es lo mismo
  TipoConsumo nafta = new TipoConsumo(NombreTipoConsumo.NAFTA, Unidad.LT);
  TipoConsumo carbon = new TipoConsumo(NombreTipoConsumo.CARBON, Unidad.KG);
  TipoConsumo gasoilConsumido = new TipoConsumo(NombreTipoConsumo.GASOILCONSUMIDO, Unidad.LTS);
  TipoConsumo naftaConsumida = new TipoConsumo(NombreTipoConsumo.NAFTACONSUMIDO, Unidad.LTS);
  TipoConsumo electricidad = new TipoConsumo(NombreTipoConsumo.ELECTRICIDAD, Unidad.KWH);
  TipoConsumo camion = new TipoConsumo(NombreTipoConsumo.CAMION, null);
  TipoConsumo utilitario = new TipoConsumo(NombreTipoConsumo.UTILITARIO, null);
  TipoConsumo distanciaMediaRecorrida = new TipoConsumo(NombreTipoConsumo.DISTANCIAMEDIARECORRIDA, Unidad.KM);
  
  Organizacion ixpandit = new Organizacion("Ixpandit", TipoOrganizacion.EMPRESA, new Ubicacion(1, "Roque Saenz Pena", 917), Clasificacion.EMPRESASECTORSECUNDARIO );
  
    @BeforeEach
    void init() {
    } 
    
    @Test
    public void calculoHuellaCarbonCsv() {
      ixpandit.cargarMedicion();
      
        FactorDeEmision fegasNatural = new FactorDeEmision(gasNatural, Unidad.M3, 1.00);
        FactorDeEmision fediesel = new FactorDeEmision(diesel, Unidad.LT, 2.00);
        FactorDeEmision fecarbon = new FactorDeEmision(carbon, Unidad.KG, 2.00);
        
        ixpandit.agregarFactorDeEmision(fegasNatural);
        ixpandit.agregarFactorDeEmision(fecarbon);
      
      /*System.out.println(expandit.getMediciones().get(0).getTipoConsumo().getNombre());
      System.out.println(expandit.getMediciones().get(3).getTipoConsumo().getNombre());
      System.out.println(expandit.getMediciones());
      System.out.println(expandit.getMediciones().get(0).getTipoConsumo().nombre);
      System.out.println(expandit.getMediciones().get(0).getTipoConsumo().getFactorDeEmision());
      System.out.println(expandit.getMediciones().get(1).getTipoConsumo().nombre);
      System.out.println(expandit.getMediciones().get(1).getTipoConsumo().getFactorDeEmision());
      System.out.println(expandit.getMediciones().get(2).getTipoConsumo().nombre);
      System.out.println(expandit.getMediciones().get(2).getTipoConsumo().getFactorDeEmision());
      System.out.println(expandit.getMediciones().get(3).getTipoConsumo().nombre);
      System.out.println(expandit.getMediciones().get(3).getTipoConsumo().getFactorDeEmision());
      System.out.println(expandit.getMediciones().get(4).getTipoConsumo().nombre);
      System.out.println(expandit.getMediciones().get(4).getTipoConsumo().getFactorDeEmision());
      System.out.println(expandit.getMediciones().get(5).getTipoConsumo().nombre);
      System.out.println(expandit.getMediciones().get(5).getTipoConsumo().getFactorDeEmision());
      System.out.println(expandit.getMediciones().get(6).getTipoConsumo().nombre);
      System.out.println(expandit.getMediciones().get(6).getTipoConsumo().getFactorDeEmision());
      System.out.println(expandit.calcularHuellaDeCarbono(new Imputacion("2022")));
      System.out.println("Paso");*/
      
      assertEquals(0.024657534246575342, ixpandit.huellaDeCarbono(new Imputacion("2022")));
      
      //valor medicion gas natural -> 1
      //valor medicion carbon -> 4
      //valor promedio anual -> 1/365 = 0.0028
      //valor promedio anual -> 2/365 = 0.0055
      //valor fe gas natural -> 1
      //valor fe carbon -> 2
      
      //1*0.0028*1 + 4 * 0.0055 * 2 = 0.0468
    }
}
