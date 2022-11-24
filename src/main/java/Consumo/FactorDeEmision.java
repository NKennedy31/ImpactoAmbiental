package Consumo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import Exceptions.UnidadesNoCoinciden;
import Repositorios.PersistentEntity;

@Entity
public class FactorDeEmision extends PersistentEntity{
  @OneToOne
  @JoinColumn(name = "tipoConsumo_id", referencedColumnName = "id")
  public TipoConsumo tipoDeConsumo;

  @Enumerated(EnumType.STRING)
  private Unidad unidad;

  @Transient
  private Double valor = 0.0;

  public FactorDeEmision() {
  }

  public FactorDeEmision(TipoConsumo tipoConsumo, Unidad unidad, Double valor) {
    try {
      validarCargadeFE(tipoConsumo, unidad);
    } catch (UnidadesNoCoinciden e) {
      e.printStackTrace();
    }
    this.tipoDeConsumo = tipoConsumo;
    this.unidad = unidad;
    this.valor = valor;
    tipoConsumo.agregarFactorDeEmision(valor);
  }

  private void validarCargadeFE(TipoConsumo tipoConsumo, Unidad unidad) throws UnidadesNoCoinciden {
    if (tipoConsumo.getUnidad() != unidad) {
      throw new UnidadesNoCoinciden("Las unidades no coinciden");
    }
  }

  /*public void cargarFE() {
    FactoresDeEmisionRepo.getInstance().agregarFE(this);
  }*/

  public Double getValor() {
    return this.valor;
  }

  public TipoConsumo getTipoDeConsumo() {
    return tipoDeConsumo;
  }
}
