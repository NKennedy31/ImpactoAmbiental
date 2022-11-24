package server;

import Repositorios.*;
import Trayecto.Estacion;
import Trayecto.Linea;
import Trayecto.Trayecto;
import Trayecto.Tramo;
import Trayecto.*;
import impacto_ambiental.*;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Consumo.FactorDeEmision;
import Consumo.NombreTipoConsumo;
import Consumo.TipoConsumo;
import Consumo.Unidad;
import Trayecto.Ubicacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
  
  public void init(){
    withTransaction(() ->{ //Este metodo hace el beginTransaction y commitTransaction
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
      
      persist(gasNatural);
      persist(diesel);
      persist(nafta);
      persist(carbon);
      persist(gasoilConsumido);
      persist(naftaConsumida);
      persist(electricidad);
      persist(camion);
      persist(utilitario);
      persist(distanciaMediaRecorrida);
      
      Ubicacion roqueSaenz = new Ubicacion(1, "Roque Saenz Pena", 917);
      Ubicacion lasHeras = new Ubicacion(1, "Las Heras", 500);
      
      Organizacion ixpandit = new Organizacion("Ixpandit", TipoOrganizacion.EMPRESA, roqueSaenz, Clasificacion.EMPRESASECTORSECUNDARIO );      
      ixpandit.cargarMedicion("mediciones.csv"); //Carga mediciones del csv en la lista de mediciones de la organizacion.
      Organizacion mercadoLibre = new Organizacion("Mercado Libre", TipoOrganizacion.EMPRESA, lasHeras, Clasificacion.EMPRESASECTORPRIMARIO );

      List<Organizacion> organizaciones = new ArrayList<>();
      organizaciones.add(ixpandit);
      Miembro miembro1 = new Miembro("Juan","González",TipoDocumento.DNI,"3000100",organizaciones);
      persist(miembro1);
      List<Miembro> miembros = new ArrayList<>();
      miembros.add(miembro1);

      Sector finanzas = new Sector(miembros,ixpandit);
      persist(finanzas);
      ixpandit.getSectores().add(finanzas);

      persist(ixpandit);
      persist(mercadoLibre);
      
      
      RepositorioOrganizaciones.instancia().agregar(ixpandit);
      RepositorioOrganizaciones.instancia().agregar(mercadoLibre);

      Sector sector1 = new Sector(mercadoLibre);
      Sector sector2 = new Sector(mercadoLibre);

      persist(sector1);
      persist(sector2);

      RepositorioSectores.instancia().agregar(sector1);
      RepositorioSectores.instancia().agregar(sector2);


      
      SectorTerritorial sectorRoqueSaenz = new SectorTerritorial(roqueSaenz);
      SectorTerritorial sectorLasHeras = new SectorTerritorial(lasHeras);
      
      persist(sectorRoqueSaenz);
      persist(sectorLasHeras);
      
      RepositorioSectoresTerritoriales.instancia().agregar(sectorRoqueSaenz);
      RepositorioSectoresTerritoriales.instancia().agregar(sectorLasHeras);
      
      RepositorioTipoConsumo.instancia().agregar(gasNatural);
      RepositorioTipoConsumo.instancia().agregar(diesel);
      RepositorioTipoConsumo.instancia().agregar(nafta);
      RepositorioTipoConsumo.instancia().agregar(carbon);
      RepositorioTipoConsumo.instancia().agregar(gasoilConsumido);
      RepositorioTipoConsumo.instancia().agregar(naftaConsumida);
      RepositorioTipoConsumo.instancia().agregar(electricidad);
      RepositorioTipoConsumo.instancia().agregar(camion);
      RepositorioTipoConsumo.instancia().agregar(utilitario);
      RepositorioTipoConsumo.instancia().agregar(distanciaMediaRecorrida);
      
      FactorDeEmision feGasNatural = new FactorDeEmision(gasNatural,Unidad.M3,1.00);
      FactorDeEmision feDiesel = new FactorDeEmision(diesel,Unidad.LT,2.00);
      FactorDeEmision feNafta = new FactorDeEmision(nafta,Unidad.LT,1.00);
      FactorDeEmision feCarbon = new FactorDeEmision(carbon,Unidad.KG,2.00);
      FactorDeEmision feGasoilConsumido = new FactorDeEmision(gasoilConsumido,Unidad.LTS,1.00);
      FactorDeEmision feNaftaConsumida = new FactorDeEmision(naftaConsumida,Unidad.LTS,1.00);
      FactorDeEmision feElectricidad = new FactorDeEmision(electricidad ,Unidad.KWH,1.00);
      FactorDeEmision feCamion = new FactorDeEmision(camion ,null,1.00);
      FactorDeEmision feUtilitario = new FactorDeEmision(utilitario ,null,1.00);
      FactorDeEmision feDistanciaMediaRecorrida = new FactorDeEmision(distanciaMediaRecorrida,Unidad.KM,0.00);
      
      persist(feGasNatural);
      persist(feDiesel);
      persist(feNafta);
      persist(feCarbon);
      persist(feGasoilConsumido);
      persist(feNaftaConsumida);
      persist(feElectricidad);
      persist(feCamion);
      persist(feUtilitario);
      persist(feDistanciaMediaRecorrida);

      Estacion estacion1 = new Estacion(10);
      Estacion estacion2 = new Estacion(20);
      Estacion estacion3 = new Estacion(30);
      Estacion estacion4 = new Estacion(40);
      Estacion estacion5 = new Estacion(50);
      Estacion estacion6 = new Estacion(60);

      persist(estacion1);
      persist(estacion2);
      persist(estacion3);
      persist(estacion4);
      persist(estacion5);
      persist(estacion6);


      List<Estacion> estaciones1 = Arrays.asList(estacion1,estacion2);
      List<Estacion> estaciones2 = Arrays.asList(estacion3,estacion4);
      List<Estacion> estaciones3 = Arrays.asList(estacion5,estacion6);
      Linea linea2 = new Linea("linea2",estaciones1);
      Linea linea5 = new Linea("linea5",estaciones2);
      Linea linea8 = new Linea("linea8",estaciones3);

      persist(linea2);
      persist(linea5);
      persist(linea8);
      
      /*      
      RepositorioFactoresDeEmision.instancia().agregar(feGasNatural);
      RepositorioFactoresDeEmision.instancia().agregar(feDiesel);
      RepositorioFactoresDeEmision.instancia().agregar(feNafta);
      RepositorioFactoresDeEmision.instancia().agregar(feCarbon);
      RepositorioFactoresDeEmision.instancia().agregar(feGasoilConsumido);
      RepositorioFactoresDeEmision.instancia().agregar(feNaftaConsumida);
      RepositorioFactoresDeEmision.instancia().agregar(feElectricidad);
      RepositorioFactoresDeEmision.instancia().agregar(feCamion);
      RepositorioFactoresDeEmision.instancia().agregar(feUtilitario);
      RepositorioFactoresDeEmision.instancia().agregar(feDistanciaMediaRecorrida);*/
      Miembro lionel = new Miembro("lionel", "messi", TipoDocumento.DNI, "342345i0423");

      lionel.vincularseA(sector1, mercadoLibre);

      persist(lionel);
      
      Usuario Ixpandit = new Usuario("Ixpandit", "Ixpandit123!", "organizacion");
      Usuario agente = new Usuario("Agente", "Agente123!", "agente");
      Usuario messi = new Usuario("Lionel Messi", "Messi123!", "miembro");
      
      RepositorioUsuarios.instancia().agregar(Ixpandit);
      
      RepositorioUsuarios.instancia().agregar(messi);

      Tramo tramo = new Tramo(new TransportePrivado(20.00,roqueSaenz,lasHeras,gasNatural));
      persist(tramo);
      List<Tramo> tramos = new ArrayList<>();
      tramos.add(tramo);
      Trayecto trayecto = new Trayecto(tramos);
      persist(trayecto);


    });
  }
}