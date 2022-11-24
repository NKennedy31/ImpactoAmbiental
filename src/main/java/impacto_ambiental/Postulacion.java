package impacto_ambiental;

import Repositorios.PersistentEntity;

import javax.persistence.*;

@Entity
public class Postulacion extends PersistentEntity {

  @ManyToOne
  Miembro postulante;

  @ManyToOne
  Sector sector;

  public Postulacion(Miembro postulante, Sector sector){
    this.postulante = postulante;
    this.sector = sector;
  }

  public void rechazar(){
    sector.getOrganizacion().getPostulantes().remove(postulante);
  }

  public void aceptar(){
    sector.agregarMiembro(postulante);
    postulante.agregarOrganizacion(sector.getOrganizacion());
  }

  public Miembro getPostulante() {
    return postulante;
  }

  public Sector getSector() {
    return sector;
  }
}
