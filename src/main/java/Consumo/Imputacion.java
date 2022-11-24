package Consumo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import Repositorios.PersistentEntity;

@Entity
public class Imputacion extends PersistentEntity{
  @Column
  String imputacion;
  
  @Column
  String year;
  
  @Column
  String mes = null;
  
  @Transient
  String separador = "/"; 
  
  public Imputacion() {
  }

  public Imputacion(String imputacion) {
    if (imputacion.contains(separador)) { //Si el string contiene el separador, el formato de la fecha de imputacion es mes/año
      year = getImputacion(imputacion, 1); 
      mes = getImputacion(imputacion, 0);
    } else {
      year = getImputacion(imputacion, 0); //Si el string no contiene el separador, el formato de la fecha de imputacion es año 
    }
    this.imputacion = imputacion;
  }

  String getImputacion(String string, int number) { //Recibe la fecha de imputacion con un numero
    String[] str = string.split(separador);         //Separa el string si encuentra el separador dentro de el 
    return str[number];                             //Si se encontro el separador en el string el año va a estar en la pos 1 del string
  }                                                 //Si no se encontro el separador el año va a estar en la pos 0 del string

  public String getMonth() {
    if (this.mes == null) {
      //Excepcion que el periodo no tiene mes
      throw new RuntimeException("change");
    }
    return this.mes;
  }
  
  public String getYear() {
  return year;
  }
  
  public String getFecha() {
    return imputacion;
  }
  
  public Periodo getPeriodo(Imputacion imputacion) { 
    if(imputacion.getFecha().length() <= 4) {
      return Periodo.ANUAL;
    }
    else return Periodo.MENSUAL;
  }                                                 


  public boolean igual(Imputacion periodoImputacion) {
    return imputacion.equals(periodoImputacion.getFecha());
    /*if (this.mes != null) {
      if (string.mes == null) {
        // TODO throw new EXCEPCION que el periodo no tiene mes
      } else {
        return (this.mes.equals(string.mes) && this.year.equals(string.year));
      }
    } else {
      return this.year.equals(string.year);
    }
    return false;*/
  }
}
