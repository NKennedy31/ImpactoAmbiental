package Consumo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import Repositorios.PersistentEntity;

@Entity
public class Medicion extends PersistentEntity {
  @Column
  String nombre;
  
  @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "tipoConsumo_id", referencedColumnName = "id")
  TipoConsumo tipoConsumo;
  
  @Enumerated(EnumType.STRING)
  ActividadConsumo actividad;
  
  @Column
  double valor;
  
  @Enumerated(EnumType.STRING)
  Periodo periodo;
  
  @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "imputacion_id", referencedColumnName = "id")
  Imputacion imputacion;

  @Transient
  String unidad;

  public Medicion() {
  }
  public Medicion(TipoConsumo tipoConsumo, Double valor, Imputacion imputacion) {
    this.tipoConsumo = tipoConsumo;
    this.nombre = obtenerNombre(tipoConsumo);
    this.actividad = obtenerActividad(tipoConsumo);
    this.valor = valor;
    this.periodo = obtenerPeriodo(imputacion);
    this.imputacion = imputacion;
  }
  
  public Medicion(TipoConsumo tipoConsumo, ActividadConsumo actividad, Double valor, Periodo periodo, Imputacion imputacion) {
    this.tipoConsumo = tipoConsumo;
    this.nombre = obtenerNombre(tipoConsumo);
    this.actividad = actividad;
    this.valor = valor;
    this.periodo = periodo;
    this.imputacion = imputacion;
  }

  public TipoConsumo getTipoConsumo(){
    return tipoConsumo;
  }

  public ActividadConsumo getActividad(){
    return actividad;
  }
  
  public Double getValor() {
    return valor;
  }
  
  public Periodo getPeriodo(){
    return periodo;
  }
  
  public Imputacion getImputacion(){
    return imputacion;
  }
  
  public String obtenerNombre(TipoConsumo tipoConsumo) {
    return tipoConsumo.getNombre();
  }
  
  public ActividadConsumo obtenerActividad(TipoConsumo tipo) {
    switch(tipo.getNombre()) {
      case "GASNATURAL":
        return ActividadConsumo.COMBUSTIONFIJA;
      case "DIESEL":
        return ActividadConsumo.COMBUSTIONFIJA;
      case "NAFTA":
        return ActividadConsumo.COMBUSTIONFIJA;
      case "CARBON":
        return ActividadConsumo.COMBUSTIONFIJA;
      case "GASOILCONSUMIDO":
        return ActividadConsumo.COMBUSTIONMOVIL;
      case "NAFTACONSUMIDO":
        return ActividadConsumo.COMBUSTIONMOVIL;
      case "ELECTRICIDAD":
        return ActividadConsumo.ELECTRICIDAD;
      case "CAMION":
        return ActividadConsumo.LOGISTICA;
      case "UTILITARIO":
        return ActividadConsumo.LOGISTICA;
     default:
        return ActividadConsumo.LOGISTICA;
    }
  }
  
  public Periodo obtenerPeriodo(Imputacion imputacion) {
    return imputacion.getPeriodo(imputacion);
  }
  
  //------------------------------------------------------------------------------------------------------------------
  //CALCULADORA
  //------------------------------------------------------------------------------------------------------------------
  public boolean perteneceA(Imputacion periodoDeImputacion) {
    return imputacion.igual(periodoDeImputacion);
  }
  
  public double proporcionalSegunPeriodo() {
    if(periodo == Periodo.MENSUAL) {
      return valor / 30;  //Se gastan en el caso de electricidad consumo 1 kWh/dia * 30 dia =  30 KWh
    }
    else {
      return valor / 365;
    }
  }
  
  public double getFactorDeEmision() {
    return tipoConsumo.getFactorDeEmision();
  }
  
  public double datoActividad() {
    return proporcionalSegunPeriodo() 
        * valor
        * getFactorDeEmision();
  }
  
  public void agregarFactorDeEmision(FactorDeEmision fe) { //Como tipoDeConsumo lo vamos a levantar de la base, vamos a obtener el mismo objeto
    if(fe.tipoDeConsumo.nombre == tipoConsumo.nombre) {
      tipoConsumo.agregarFactorDeEmision(fe.getValor());
    }
  }
}