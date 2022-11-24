package Trayecto;

import Consumo.TipoConsumo;
import Repositorios.PersistentEntity;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Transporte extends PersistentEntity {
  
  @ManyToOne
  @JoinColumn(name = "tipoConsumo_id", referencedColumnName = "id")
  TipoConsumo tipoConsumo;
  
  @Column
  double combustibleXKm;
  
  public abstract double distancia() throws IOException;

  public double combustibleConsumido() throws IOException {
    return combustibleXKm * this.distancia();
  }
  
  public double obtenerFactorDeEmision() {
    return tipoConsumo.getFactorDeEmision();
  }
}