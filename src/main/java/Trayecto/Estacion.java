package Trayecto;

import javax.persistence.*;

import Repositorios.PersistentEntity;

@Entity
@Table
public class Estacion extends PersistentEntity{
  @Column
  Integer km;

  public Estacion() {
  }

  public Estacion(Integer km){
    this.km = km;
  }

  public Integer getKm() {
    return km;
  }
  
  public double distanciaProximaEstacion(Estacion proximaEstacion) {
   return proximaEstacion.getKm() - km;
  }
}
