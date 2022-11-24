package Consumo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import Repositorios.PersistentEntity;

@Entity
public class TipoConsumo extends PersistentEntity{
  @Enumerated(EnumType.STRING)
  public NombreTipoConsumo nombre;
  
  @Enumerated(EnumType.STRING)
  public Unidad unidad;

  @Transient
  private double valorFe;

  public TipoConsumo() {
  }

  public TipoConsumo(NombreTipoConsumo nombre, Unidad unidad) {
    this.nombre = nombre;
    this.unidad = unidad;
  }
  
  public TipoConsumo(String tipoConsumo) {
    try {
      this.nombre = obtenerNombre(tipoConsumo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    obtenerUnidad();
  }

  public String getNombre() {
    return nombre.toString();
  }
  
  public Unidad getUnidad() {
    return unidad;
  }
  
  public double getFactorDeEmision() {
    return valorFe;
  }
  
  public void agregarFactorDeEmision(double valor){
    valorFe = valor;
  }
  
  NombreTipoConsumo obtenerNombre(String tipoConsumo) {
    try {
    switch(tipoConsumo) {
      case "Gas Natural":
        return NombreTipoConsumo.GASNATURAL;
      case "Diesel":
        return NombreTipoConsumo.DIESEL;
      case "Nafta":
        return NombreTipoConsumo.NAFTA;
      case "Carbon":
        return NombreTipoConsumo.CARBON;
      case "Gasoil Consumido":
        return NombreTipoConsumo.GASOILCONSUMIDO;
      case "Nafta Consumida":
        return NombreTipoConsumo.NAFTACONSUMIDO;
      case "Electricidad":
        return NombreTipoConsumo.ELECTRICIDAD;
      case "Camion":
        return NombreTipoConsumo.CAMION;
      case "Utilitario":
        return NombreTipoConsumo.UTILITARIO;
      case "Distancia Media Recorrida":
        return NombreTipoConsumo.DISTANCIAMEDIARECORRIDA;
    }
    }
    catch(Exception e) {
     e = new Exception("El tipo de consumo ingresado no es valido");
    }
    return null;
  }
  
  void obtenerUnidad() {
    switch(nombre) {
      case GASNATURAL:
        this.unidad = Unidad.M3;
      case DIESEL:
        this.unidad = Unidad.LT;
      case NAFTA:
        this.unidad = Unidad.LT;
      case CARBON:
        this.unidad = Unidad.KG;
      case GASOILCONSUMIDO:
        this.unidad = Unidad.LTS;
      case NAFTACONSUMIDO:
        this.unidad = Unidad.LTS;
      case ELECTRICIDAD:
        this.unidad = Unidad.KWH;
      case CAMION:
        this.unidad = null;
      case UTILITARIO:
        this.unidad = null;
      case DISTANCIAMEDIARECORRIDA:
        this.unidad = Unidad.KM;
    }
  }
}
