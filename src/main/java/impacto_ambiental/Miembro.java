package impacto_ambiental;

import Trayecto.Trayecto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import Exceptions.NoPerteneceOrganizacion;
import Exceptions.OrganizacionNoExiste;
import Exceptions.SectorNoExisteException;
import Repositorios.PersistentEntity;

import javax.persistence.*;

import Consumo.Imputacion;

@Entity
@Table
public class Miembro extends PersistentEntity {
  @Column
  private String nombre;
 
  @Column
  private String apellido;
  
  @Enumerated(EnumType.STRING)
  private TipoDocumento tipo;
  
  @Column
  private String documento;
  
  @OneToMany
  private List<Trayecto> trayectos = new ArrayList<>();
  @Transient
  //@OneToOne
  //@JoinColumn(name ="contacto_id")
  private Contacto contacto;
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Organizacion> organizaciones = new ArrayList<>();

  public  Miembro(String nombre, String apellido, TipoDocumento tipo, String documento){
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipo = tipo;
    this.documento = documento;
  }

  public Miembro() {
  }

  public Miembro(String nombre, String apellido, TipoDocumento tipo, String documento, List<Organizacion> organizaciones){
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipo = tipo;
    this.documento = documento;
    this.organizaciones = organizaciones;
  }

  public void setContacto(Contacto contacto){
    this.contacto = contacto;
  }

  public Contacto getContacto(){
    return contacto;
  }

  public void agregarOrganizacion(Organizacion unaOrganizacion) { organizaciones.add(unaOrganizacion); }

  public List<Organizacion> getOrganizaciones() { return organizaciones; }

  public void vincularseA(Sector sector, 	Organizacion organizacion) {
    //organizacion.agregarPostulante(sector, this);

    sector.agregarPostulante(this);
  }

  public double huellaDeCarbonoDelMiembro() {
    return getTrayectos().stream().mapToDouble(trayecto -> trayecto.huellaCarbonoDelTrayecto()).sum();
  }
  
  public double huellaPersonalEn(Organizacion unaOrganizacion,Imputacion imputacion) throws NoPerteneceOrganizacion {
	  if(organizaciones.contains(unaOrganizacion)) {
	  return huellaDeCarbonoDelMiembro()/unaOrganizacion.huellaDeCarbono(imputacion);
      }
	  else {
		  throw new NoPerteneceOrganizacion("La organizacion no pertenece al miembro");
	  }
  }
  
  //--------------------------------------------------------------------------------------------------------------------
  //TRAYECTOS
  //--------------------------------------------------------------------------------------------------------------------
  public List<Trayecto> getTrayectos() { return trayectos; }
  
  public void agregarUnTrayecto(Trayecto trayecto, Organizacion organizacion) throws NoPerteneceOrganizacion {
    if(organizaciones.contains(organizacion)) {
    trayecto.vincularMiembro(this);
    trayecto.vincularOrganizacion(organizacion);
    trayectos.add(trayecto);
    }
    else {
      throw new NoPerteneceOrganizacion("La organizacion no pertenece al miembro");
    }
  }

  /*public void registrarTrayectos(Trayecto ... trayectos) {
    Collections.addAll(this.trayectos, trayectos);
  }*/
  
  public List<Trayecto> getTrayectosOrganizacion(Organizacion organizacion) {
    return trayectos.stream().filter(trayecto -> trayecto.perteneceAOrganizacion(organizacion)).collect(Collectors.toList());
  }

  public String getApellido() {
    return apellido;
  }

  public String getDocumento() {
    return documento;
  }

  public String getNombre() {
    return nombre;
  }
}