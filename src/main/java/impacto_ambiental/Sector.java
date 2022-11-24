package impacto_ambiental;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import Trayecto.Trayecto;

import javax.persistence.*;

import Repositorios.PersistentEntity;

@Entity
public class Sector extends PersistentEntity {
  
  @OneToMany
  @JoinColumn(name = "sector_id", referencedColumnName = "id")
  private List<Miembro> miembros;
  
  @ManyToOne
  @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
  private Organizacion organizacion;

  @ManyToMany
  private List<Miembro> postulantes = new ArrayList<>();

  public Sector(Organizacion organizacion){
    this.organizacion = organizacion;
  }

  public Sector() {
  }

  public Sector(List<Miembro> miembros, Organizacion organizacion){
    this.miembros = miembros;
    this.organizacion = organizacion;
  }

  public List<Miembro> getMiembros() {
    return miembros;
  }
  
  public Organizacion getOrganizacion() {
	  return organizacion;
  }

  public void agregarMiembro(Miembro miembro){
  	miembros.add(miembro);
  }

  public List<Trayecto> listaTrayectosSinDuplicados(){
	  return miembros
			  .stream()
			  .map(miembro -> miembro.getTrayectos())
			  .flatMap(Collection::stream)
			  .distinct()
			  .collect(Collectors.toList());
  }
  
  public double huellaCarbonoDelSector() {
    return listaTrayectosSinDuplicados().stream().mapToDouble(trayecto -> trayecto.huellaCarbonoDelTrayecto()).sum();
  }

  public double huellaDeCarbonoPromedioPorMiembro(){
    return huellaCarbonoDelSector()/miembros.size();
  }

  public void agregarPostulante(Miembro miembro){
    postulantes.add(miembro);
  }

  public List<Miembro> getPostulantes(){
    return postulantes;
  }
}
