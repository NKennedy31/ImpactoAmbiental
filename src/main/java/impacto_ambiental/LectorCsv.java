package impacto_ambiental;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import Consumo.ActividadConsumo;
import Consumo.Imputacion;
import Consumo.Medicion;
import Consumo.Periodo;
import Consumo.TipoConsumo;
import Repositorios.RepositorioTipoConsumo;

public class LectorCsv {
  private static LectorCsv instancia;
  
  List<Medicion> medicionesObtenidas = new ArrayList<>();
  
  public LectorCsv() {
  }
  
  public static LectorCsv getInstancia() {
    if(instancia == null) {
      instancia = new LectorCsv(); 
    }
    return instancia;
  }
  
  public List<Medicion> getMediciones() {
    return medicionesObtenidas;
  }
  
  TipoConsumo gasNatural = RepositorioTipoConsumo
      .instancia()
      .buscarPorNombre("GASNATURAL");
  
  TipoConsumo diesel = RepositorioTipoConsumo
      .instancia()
      .buscarPorNombre("DIESEL");
  
  TipoConsumo nafta = RepositorioTipoConsumo
      .instancia()
      .buscarPorNombre("NAFTA");
  
  TipoConsumo carbon = RepositorioTipoConsumo
      .instancia()
      .buscarPorNombre("CARBON");
  
  TipoConsumo gasoilConsumido = RepositorioTipoConsumo
      .instancia()
      .buscarPorNombre("GASOILCONSUMIDO");
  
  TipoConsumo naftaConsumida = RepositorioTipoConsumo
      .instancia()
      .buscarPorNombre("NAFTACONSUMIDO");
  
  TipoConsumo electricidad = RepositorioTipoConsumo
      .instancia()
      .buscarPorNombre("ELECTRICIDAD");
  
  TipoConsumo camion = RepositorioTipoConsumo
      .instancia()
      .buscarPorNombre("CAMION");
  
  TipoConsumo utilitario = RepositorioTipoConsumo
      .instancia()
      .buscarPorNombre("UTILITARIO");
  
  TipoConsumo distanciaMedia = RepositorioTipoConsumo
      .instancia()
      .buscarPorNombre("DISTANCIAMEDIARECORRIDA");
  
  
  public List<Medicion> cargarMedicion(String path) {
    CSVReader csvReader = null;
    try {
      csvReader = new CSVReader(new FileReader(path));
    } 
    
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    String[] fila = null;
    
    try {
      while((fila = csvReader.readNext()) != null) {
        if(fila[0].matches("Gas Natural") && fila[3].matches("ANUAL")) {
          medicionesObtenidas.add(
              new Medicion(gasNatural,
                  ActividadConsumo.COMBUSTIONFIJA, 
                  Double.valueOf(fila[2]), Periodo.ANUAL, 
                  new Imputacion(fila[4]))); //Falta cambiar de String a Imputacion
 }
        else if(fila[0].matches("Gas Natural") && fila[3].matches("MENSUAL")) {
          medicionesObtenidas.add(
              new Medicion(gasNatural,
                  ActividadConsumo.COMBUSTIONFIJA, 
                  Double.parseDouble(fila[2]), Periodo.MENSUAL, 
                  new Imputacion(fila[4])));
        }
        
        else if(fila[0].matches("Diesel") && fila[3].matches("ANUAL")) {
          medicionesObtenidas.add(
              new Medicion(diesel,
                  ActividadConsumo.COMBUSTIONFIJA, 
                  Double.parseDouble(fila[2]), Periodo.ANUAL, 
                  new Imputacion(fila[4])));
 }
        else if(fila[0].matches("Diesel") && fila[3].matches("MENSUAL")) {
          medicionesObtenidas.add(
              new Medicion(diesel,
                  ActividadConsumo.COMBUSTIONFIJA, 
                  Double.parseDouble(fila[2]), Periodo.MENSUAL, 
                  new Imputacion(fila[4])));
        }
        
        else if(fila[0].matches("Nafta") && fila[3].matches("ANUAL")) {
          medicionesObtenidas.add(
              new Medicion(nafta,
                  ActividadConsumo.COMBUSTIONFIJA, 
                  Double.parseDouble(fila[2]), Periodo.ANUAL, 
                  new Imputacion(fila[4])));
 }
        else if(fila[0].matches("Nafta") && fila[3].matches("MENSUAL")) {
          medicionesObtenidas.add(
              new Medicion(nafta,
                  ActividadConsumo.COMBUSTIONFIJA, 
                  Double.parseDouble(fila[2]), Periodo.MENSUAL, 
                  new Imputacion(fila[4])));
        }
        else if(fila[0].matches("Carbon") && fila[3].matches("ANUAL")) {
          medicionesObtenidas.add(
              new Medicion(carbon,
                  ActividadConsumo.COMBUSTIONFIJA, 
                  Double.parseDouble(fila[2]), Periodo.ANUAL, 
                  new Imputacion(fila[4])));
 }
        else if(fila[0].matches("Carbon") && fila[3].matches("MENSUAL")) {
          medicionesObtenidas.add(
              new Medicion(carbon,
                  ActividadConsumo.COMBUSTIONFIJA, 
                  Double.parseDouble(fila[2]), Periodo.MENSUAL, 
                  new Imputacion(fila[4])));
        }
        else if(fila[0].matches("Combustible consumido - Gasoil") && fila[3].matches("ANUAL")) {
          medicionesObtenidas.add(
              new Medicion(gasoilConsumido,
                  ActividadConsumo.COMBUSTIONMOVIL, 
                  Double.parseDouble(fila[2]), Periodo.ANUAL, 
                  new Imputacion(fila[4])));
 }
        else if(fila[0].matches("Combustible consumido - Gasoil") && fila[3].matches("MENSUAL")) {
          medicionesObtenidas.add(
              new Medicion(gasoilConsumido,
                  ActividadConsumo.COMBUSTIONMOVIL, 
                  Double.parseDouble(fila[2]), Periodo.MENSUAL, 
                  new Imputacion(fila[4])));
        }
        else if(fila[0].matches("Combustible consumido - Nafta") && fila[3].matches("ANUAL")) {
          medicionesObtenidas.add(
              new Medicion(naftaConsumida,
                  ActividadConsumo.COMBUSTIONMOVIL, 
                  Double.parseDouble(fila[2]), Periodo.ANUAL, 
                  new Imputacion(fila[4])));
 }
        else if(fila[0].matches("Combustible consumido - Nafta") && fila[3].matches("MENSUAL")) {
          medicionesObtenidas.add(
              new Medicion(naftaConsumida,
                  ActividadConsumo.COMBUSTIONMOVIL, 
                  Double.parseDouble(fila[2]), Periodo.MENSUAL, 
                  new Imputacion(fila[4])));
        }
        else if(fila[0].matches("Electricidad") && fila[3].matches("ANUAL")) {
          medicionesObtenidas.add(
              new Medicion(electricidad,
                  ActividadConsumo.ELECTRICIDAD, 
                  Double.parseDouble(fila[2]), Periodo.ANUAL, 
                  new Imputacion(fila[4])));
 }
        else if(fila[0].matches("Electricidad") && fila[3].matches("MENSUAL")) {
          medicionesObtenidas.add(
              new Medicion(electricidad,
                  ActividadConsumo.ELECTRICIDAD, 
                  Double.parseDouble(fila[2]), Periodo.MENSUAL, 
                  new Imputacion(fila[4])));
        }
      }
      return medicionesObtenidas;
    } 
    catch (CsvValidationException e) {
      e.printStackTrace();
      } 
    catch (NumberFormatException e) {
      e.printStackTrace();
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
    return medicionesObtenidas;
}
}
