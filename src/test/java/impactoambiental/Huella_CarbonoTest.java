package impactoambiental;

import Exceptions.NoPerteneceOrganizacion;
import Exceptions.SectorNoExisteException;

import api.Ubicacion.ServicioGeoUbicacion;
import api.Ubicacion.Entities.Distancia;
import com.opencsv.exceptions.CsvValidationException;

import Consumo.NombreTipoConsumo;
import Consumo.TipoConsumo;
import Consumo.Unidad;
import impacto_ambiental.*;
import Trayecto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static impacto_ambiental.Clasificacion.*;
import static impacto_ambiental.TipoOrganizacion.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Huella_CarbonoTest {
  
  Organizacion ixpandit = new Organizacion("Ixpandit", TipoOrganizacion.EMPRESA, new Ubicacion(1, "Roque Saenz Pena", 917), Clasificacion.EMPRESASECTORSECUNDARIO );
  Organizacion mercadoLibre = new Organizacion("mercadoLibre", TipoOrganizacion.EMPRESA, new Ubicacion(1, "Las Heras", 1000), Clasificacion.EMPRESASECTORPRIMARIO );

  Miembro melanie = new Miembro("Melanie", "Alarcon", TipoDocumento.DNI, "40190745", new ArrayList<>());
  
  Ubicacion ubicacionCasa = new Ubicacion(1, "Av Callao", 1500);
  Ubicacion ubicacionIxpandit = ixpandit.getUbicacion();
  
  TransportePrivado vehiculoMelanieIda = new TransportePrivado(2.0, ubicacionCasa, ubicacionIxpandit, new TipoConsumo(NombreTipoConsumo.NAFTA,Unidad.LT));
  TransportePrivado vehiculoMelanieVuelta = new TransportePrivado(2.0, ubicacionIxpandit, ubicacionCasa, new TipoConsumo(NombreTipoConsumo.NAFTA,Unidad.LT));
  
  Tramo tramoIda = new Tramo(vehiculoMelanieIda);
  Tramo tramoVuelta = new Tramo(vehiculoMelanieVuelta);
  
  List<Tramo> tramos = Arrays.asList(tramoIda, tramoVuelta);
  
  Trayecto idaYVueltaACasa = new Trayecto(tramos);

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

  private ServicioGeoUbicacion servicio;

	@BeforeEach
  void initFileSystem() {
    servicio = Mockito.mock(ServicioGeoUbicacion.class);
  }
	
	@Test
  public void CrearOrganizacionYDarDeAltaSectoryMiembro(){
    Sector finanzas = new Sector(new ArrayList<>(), ixpandit);
    ixpandit.darDeAltaSector(finanzas);
    melanie.vincularseA(finanzas, ixpandit);
    assertTrue(ixpandit.getSectores().contains(finanzas));
    assertTrue(ixpandit.perteneceALaOrganizacion(melanie));
  }

  @Test
  public void CrearOrganizacionYDarDeAltaSectoryMiembroExcepcion() throws Exception{
    SectorNoExisteException exception = Assertions.assertThrows(SectorNoExisteException.class, () -> {
      Sector finanzas = new Sector(Collections.emptyList(), ixpandit);
      melanie.vincularseA(finanzas, ixpandit);
    });
    assertEquals("No se ha encontrado dicho sector en la organizacion.", exception.getMessage());

  }
  
  @Test
  public void SeAgregaUnTrayectoAUnMiembroDeUnaOrganizacion() {
    Sector finanzas = new Sector(new ArrayList<>(), ixpandit);
    ixpandit.darDeAltaSector(finanzas);
    melanie.vincularseA(finanzas, ixpandit);
    
    try {
      melanie.agregarUnTrayecto(idaYVueltaACasa, ixpandit);
    } catch (NoPerteneceOrganizacion e) {
      e.printStackTrace();
    }
    
    assertTrue(melanie.getTrayectos().contains(idaYVueltaACasa));
  }

  @Test
  public void SeAgregaUnTrayectoAUnMiembroConVariasOrganizaciones() {
    Sector finanzas = new Sector(new ArrayList<>(), ixpandit);
    ixpandit.darDeAltaSector(finanzas);
    melanie.vincularseA(finanzas, ixpandit);
    
    Sector comunicaciones = new Sector(new ArrayList<>(), mercadoLibre);
    mercadoLibre.darDeAltaSector(comunicaciones);
    melanie.vincularseA(comunicaciones, mercadoLibre);
    
    try {
      melanie.agregarUnTrayecto(idaYVueltaACasa, ixpandit);
    } catch (NoPerteneceOrganizacion e) {
      e.printStackTrace();
    }
    
    assertTrue(melanie.getTrayectos().contains(idaYVueltaACasa));
    assertFalse(idaYVueltaACasa.perteneceAOrganizacion(mercadoLibre));
  }
  
  
/*
  @Test
  public void validarConsumo(){
    Organizacion organizacion = altaOrganizacion();
    organizacion.setConsumos();
    String[] fila = {"Nafta","1","Anual","2022"};
    Medicion medicion = new MedicionAnual(fila, organizacion.getTiposDeConsumo());
    assertEquals(medicion.getTipo().getConsumo(), "NAFTA");
    assertEquals(medicion.getValor(), 1);
  }

  @Test
  public void leerCsv() throws CsvValidationException, IOException {
    Organizacion organizacion = altaOrganizacion();
    //organizacion.cargarMedicion();
  }

  @Test
  public void altaTrayectoYAgregarleunTramo() throws IOException {
    Trayecto trayecto = generarTrayecto();
    Tramo unTramo = generarTramoEnAuto();
    trayecto.agregarTramos(unTramo);
    assertTrue(trayecto.getTramos().contains(unTramo));
  }

  @Test
  public void unaLineaCalculaDistanciaEntreEstaciones() {
    List<Estacion> estaciones = new ArrayList<>();
    Linea linea5 = new Linea("5",estaciones);
    Estacion lanus = new Estacion(100);
    Estacion banfield = new Estacion(150);
    Estacion avellaneda = new Estacion(200);
    linea5.agregarEstaciones(lanus, banfield, avellaneda);
    assertEquals(100, linea5.distanciaEntreEstaciones(lanus,avellaneda));
  }

  @Test
  public void unTramoConTransportePublicoCalculaSuDistancia() throws IOException {
    Tramo tramo = generarTramoEnColectivo();
    assertEquals(100,tramo.distanciaDelTramo());
  }

  @Test
  public void unTramoConTransportePrivadoCalculaSuDistancia() throws IOException {
    when(servicio.distancia(Mockito.any(), Mockito.any())).thenReturn(new Distancia(30.723, "KM"));
    Tramo tramo = generarTramoEnAuto();
    tramo.distanciaDelTramo();
    Mockito.verify(servicio, Mockito.only()).distancia(Mockito.any(), Mockito.any());
  }

  @Test
  public void unTramoCalculaSuHuellaDeCarbono() throws IOException {
    Tramo tramo = generarTramoEnColectivo(); // CombustibleConsumido = 40000 = CombustibleXKm(400) x Distancia(100)
    assertEquals(4000000.0, tramo.huellaCarbonoDelTramo()); // HC = CombConsumido x FE(100)
  }

  @Test
  public void elTrayectoDebeConocersuDistanciaTotal() {
    Trayecto trayecto = generarTrayecto();
    assertEquals(100,trayecto.distanciaTotal());
  }

  @Test
  public void elSectorConoceSuHuellaDeCarbono() {
    Sector sector = new Sector(new ArrayList<>(), altaOrganizacion());
    Miembro miembro1 = altaMiembro();
    Miembro miembro2 = altaMiembro();
    miembro1.agregarUnTrayecto(generarTrayecto());
    miembro2.agregarUnTrayecto(generarTrayecto());
    sector.agregarMiembro(miembro1);
    sector.agregarMiembro(miembro2);
    assertEquals(8000000.0, sector.huellaCarbonoDelSector()); // TramoEnColectivo * 2 = 8000000
  }

  @Test
  public void elMiembroConoceSuHuellaDeCarbono() {
    Miembro miembro = altaMiembro();
    assertEquals(0.0, miembro.huellaDeCarbonoDelMiembro());
    miembro.agregarUnTrayecto(generarTrayecto());
    assertEquals(4000000.0, miembro.huellaDeCarbonoDelMiembro());
  }

  private Linea generarUnaLinea() {
    List<Estacion> estaciones = new ArrayList<>();
    Linea linea5 = new Linea("5",estaciones);
    Estacion lanus = new Estacion(100);
    Estacion banfield = new Estacion(150);
    Estacion avellaneda = new Estacion(200);
    linea5.agregarEstaciones(lanus, banfield, avellaneda);
    return linea5;
  }

  private VehiculoParticular generarAutoElectrico() {
    VehiculoParticular auto = new VehiculoParticular(TipoVehiculo.AUTO, TipoConsumo.ELECTRICIDAD, 200);
    return auto;
  }

  private Tramo generarTramoEnAuto() {
    TransportePrivado transporte = new TransportePrivado(electricidad,200,
        new Ubicacion(3,"Rivadavia",150),
        new Ubicacion(3,"Rivadavia",5000)
    );
    transporte.setServicioUbicacion(servicio);
    Tramo tramo = new Tramo(transporte);
    return tramo;
  }

  TREN(200.00, TipoConsumo.DIESEL),
  SUBTE(100.00, TipoConsumo.ELECTRICIDAD),
  COLECTIVO(400.00, TipoConsumo.NAFTA);

  private Tramo generarTramoEnColectivo() {
    Tramo tramo = new Tramo(new TransportePublico(generarUnaLinea(), new Estacion(100), new Estacion(200), 400, nafta));
    return tramo;
  }

  private Trayecto generarTrayecto() {
    Trayecto trayecto = new Trayecto();
    trayecto.agregarTramos(generarTramoEnColectivo());
    return trayecto;
  }

  private TransportePrivado generarUntransportePrivado() {

    TransportePrivado transporte = new TransportePrivado(electricidad, 200,
        new Ubicacion(2,"Cochabamba",1264),
        new Ubicacion(2,"Avellaneda",774));
    transporte.setServicioUbicacion(servicio);
    return transporte;
  }
*/
  /*private Organizacion altaOrganizacion(){
    return new Organizacion("Ixpandit", TipoOrganizacion.EMPRESA, "Roque Saenz Peña 917", Clasificacion.EMPRESASECTORSECUNDARIO );
  }

  private Miembro altaMiembro(){
    return new Miembro("Melanie", "Alarcon", TipoDocumento.DNI, "40190745", new ArrayList<>());
  }*/

}

