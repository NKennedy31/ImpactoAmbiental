package impacto_ambiental;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import Consumo.Imputacion;
import Repositorios.PersistentEntity;
import Repositorios.RepositorioOrganizaciones;
import Trayecto.Ubicacion;

@Entity
public class SectorTerritorial extends PersistentEntity{
  @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "sectorTerritorial_id", referencedColumnName = "id")
	private List<Organizacion> organizaciones;
  
  @Embedded
	public Ubicacion ubicacion;
	
  public SectorTerritorial() {
  }
  
	public SectorTerritorial(Ubicacion ubicacion) {
	  this.ubicacion = ubicacion;
	  this.organizaciones = organizacionesPertenecientes();
	}
	
	public List<Organizacion> getOrganizaciones() {
	  return organizaciones;
	}
	
	public List<Organizacion> organizacionesPertenecientes(){
	  return RepositorioOrganizaciones.instancia().listar()
	      .stream()
	      .filter(unaOrganizacion -> unaOrganizacion.getUbicacion().equals(ubicacion))
	      .collect(Collectors.toList());
	}
	
	public double huellaCarbonoTotal(Imputacion imputacion) {
		return organizacionesPertenecientes().stream()
				.mapToDouble(organizacion -> organizacion.huellaDeCarbono(imputacion))
				.sum();
	}
}
