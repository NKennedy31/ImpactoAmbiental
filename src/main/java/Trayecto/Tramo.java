package Trayecto;

import java.io.IOException;

import javax.persistence.*;

import Repositorios.PersistentEntity;

@Entity
@Table(name = "tramos")
public class Tramo extends PersistentEntity {

  @ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
  Transporte transporte;

  public Tramo() {
  }

  public Tramo(Transporte transporte) {
    this.transporte = transporte;
  }

  public double distanciaDelTramo() throws IOException {
    return transporte.distancia();
  }

  public double combustibleConsumidoEnElTramo() throws IOException {
    return transporte.combustibleConsumido();
  }

  public double huellaCarbonoDelTramo() throws IOException {
    return transporte.combustibleConsumido()
        * transporte.obtenerFactorDeEmision();
  }
}
