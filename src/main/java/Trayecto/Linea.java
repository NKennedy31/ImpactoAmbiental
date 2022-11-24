package Trayecto;

import javax.persistence.*;

import Repositorios.PersistentEntity;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table
public class Linea extends PersistentEntity {
  @Column
  public String nombre;
  
  @OneToMany
  @OrderBy(value = "km") //En base a un campo de la otra
  private List<Estacion> estaciones = new ArrayList<>();

  public Linea() {
  }

  public Linea(String nombre, List<Estacion> estaciones) {
	  this.nombre = nombre;
	  this.estaciones = estaciones;
	}


  public void agregarEstaciones(Estacion ...estaciones) {
    Collections.addAll(this.estaciones, estaciones);
  }

  public List<Estacion> getEstaciones() {
    return estaciones;
  }

  public Double distanciaEntreEstaciones(Estacion estacionA,Estacion estacionB) {
    return estacionA.distanciaProximaEstacion(estacionB);
  }
}
