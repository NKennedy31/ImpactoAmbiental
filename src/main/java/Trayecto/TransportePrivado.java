package Trayecto;

import Consumo.TipoConsumo;
import api.Ubicacion.ServicioUbicacion;
import api.Ubicacion.Entities.Distancia;
import javax.persistence.*;
import java.io.IOException;

@Entity
//@DiscriminatorValue(value = "privado")
public class TransportePrivado extends Transporte {
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "localidad", column=@Column(name="localidad_inicio")),
    @AttributeOverride(name = "calle", column=@Column(name="calle_inicio")),
    @AttributeOverride(name = "altura", column=@Column(name="altura_inicio")),
  })
  Ubicacion inicio;
  
  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "localidad", column=@Column(name="localidad_llegada")),
    @AttributeOverride(name = "calle", column=@Column(name="calle_llegada")),
    @AttributeOverride(name = "altura", column=@Column(name="altura_llegada")),
  })
  Ubicacion llegada;
  
  @Transient
  ServicioUbicacion servicio;

  /*@ManyToOne
  @JoinColumn(name = "tipoConsumo_id", referencedColumnName = "id")
  TipoConsumo tipoConsumo;*/

  public TipoConsumo getTipoConsumo() {
    return tipoConsumo;
  }

  public void setTipoConsumo(TipoConsumo tipoConsumo) {
    this.tipoConsumo = tipoConsumo;
  }

  public TransportePrivado() {
  }

  public TransportePrivado(double combustibleXKm, Ubicacion inicio, Ubicacion llegada, TipoConsumo tipoConsumo) {
    this.combustibleXKm = combustibleXKm;
    this.inicio = inicio;
    this.llegada = llegada;
    this.tipoConsumo = tipoConsumo;
  }

  public void setServicioUbicacion(ServicioUbicacion servicio) { this.servicio = servicio;}
  
  //Se calcula distancia utilizando servicio externo.
  public double distancia() throws IOException {
  	Distancia distancia = servicio.distancia(inicio,llegada);
    return distancia.valor;
  }
}