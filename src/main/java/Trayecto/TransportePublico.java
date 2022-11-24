package Trayecto;

import Consumo.TipoConsumo;
import Exceptions.EstacionesIncorrectasException;
import javax.persistence.*;

@Entity
//@DiscriminatorValue(value = "publico")
public class TransportePublico extends Transporte {
  @OneToOne
  Linea linea;
  @OneToOne
  Estacion partida;
  @OneToOne
  Estacion llegada;
/*  @ManyToOne
  @JoinColumn(name = "tipoConsumo_id", referencedColumnName = "id")
  TipoConsumo tipoConsumo;*/

  public TipoConsumo getTipoConsumo() {
    return tipoConsumo;
  }

  public void setTipoConsumo(TipoConsumo tipoConsumo) {
    this.tipoConsumo = tipoConsumo;
  }

  public TransportePublico() {
  }

  public TransportePublico(Linea linea, Estacion partida, Estacion llegada, double combustibleXKm, TipoConsumo tipoConsumo) {
    this.linea = linea;
    validarEstaciones(linea, llegada, partida);
    this.partida = partida;
    this.llegada = llegada;
    this.combustibleXKm = combustibleXKm;
    this.tipoConsumo = tipoConsumo;
  }

  private void validarEstaciones(Linea linea, Estacion llegada, Estacion partida){
    if(linea.getEstaciones().contains(partida) & linea.getEstaciones().contains(llegada)) {
      throw new EstacionesIncorrectasException();
    }
  }

  public double distancia() {
    return linea.distanciaEntreEstaciones(partida, llegada);
  }
}
