package Trayecto;

import impacto_ambiental.Miembro;
import impacto_ambiental.Organizacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import Repositorios.PersistentEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity

public class Trayecto extends PersistentEntity {
  @OneToMany
  List<Tramo> tramos;
  
  @ManyToOne
  @JoinColumn(name = "miembro_id", referencedColumnName = "id")
  private Miembro miembro;
  
  @ManyToOne
  @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
  private Organizacion organizacion;

  public Trayecto() {
  }

  public Trayecto(List<Tramo> tramos) {
    this.tramos = tramos;
  }

  public void agregarTramos(Tramo ...tramos) {
    Collections.addAll(this.tramos, tramos);
  }

  public List<Tramo> getTramos() {
    return this.tramos;
  }

  public void vincularMiembro(Miembro unMiembro) {
    miembro = unMiembro;
  }
  
  public void vincularOrganizacion(Organizacion unaOrganizacion) {
    organizacion = unaOrganizacion;
  }
  
  public boolean perteneceAOrganizacion(Organizacion unaOrganizacion) {
    if(organizacion == unaOrganizacion) return true;
    else return false;
  }
  
  public double distanciaTotal() {
    return tramos.stream().mapToDouble(tramo -> {
      try {
        return tramo.distanciaDelTramo();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }).sum();
  }
  
  public double combustibleConsumido() {
    return tramos.stream().mapToDouble(tramo -> {
      try {
        return tramo.combustibleConsumidoEnElTramo();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }).sum();
  }

  public double huellaCarbonoDelTrayecto() {
    return tramos.stream().mapToDouble(tramo -> {
      try {
        return tramo.huellaCarbonoDelTramo();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }).sum();
  }
  
}