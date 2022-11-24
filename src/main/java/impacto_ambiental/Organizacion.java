package impacto_ambiental;

import Exceptions.NoExistenServiciosException;
import Exceptions.NoHayContactosException;
import Exceptions.SectorNoExisteException;
import Repositorios.PersistentEntity;
import Trayecto.Trayecto;
import Trayecto.Ubicacion;
import api.ServiciosDeComunicacion.LinkObserver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;

import Consumo.FactorDeEmision;
import Consumo.Imputacion;
import Consumo.Medicion;

@Entity
public class Organizacion extends PersistentEntity {
  @Column
  private String razonSocial;
  
  @Enumerated(EnumType.STRING)
  private TipoOrganizacion tipo;
  
  @Embedded
  private Ubicacion ubicacionGeografica;

  @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
  //@JoinColumn(name = "organizacion_id", referencedColumnName = "id")
  private List<Sector> sectores = new ArrayList<>();
  
  @Enumerated(EnumType.STRING)
  private Clasificacion clasificacion;
  
  @OneToMany
  @JoinColumn(name = "contacto_id", referencedColumnName = "id")
  private List<Contacto> contactos = new ArrayList<>();
  
  @Transient //PERSISTE
  private List<LinkObserver> servicios = new ArrayList<>();
  
  @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY) //PERSISTE
  @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
  private List<Medicion> mediciones = new ArrayList<>();

  //@OneToMany
  //@JoinColumn(name = "organizacion_id", referencedColumnName = "id")
  //private List<Postulacion> postulantes = new ArrayList<>();
  
  public Organizacion(
      String razonSocial,
      TipoOrganizacion tipo,
      Ubicacion ubicacionGeografica,
      Clasificacion clasificacion
  ) {
    this.razonSocial = razonSocial;
    this.tipo = tipo;
    this.ubicacionGeografica = ubicacionGeografica;
    this.clasificacion = clasificacion;
  }
  
  public Organizacion() {
  }
  
  public String getNombre() {
    return razonSocial;
  }
  
  public Ubicacion getUbicacion(){
    return ubicacionGeografica;
  }
  
  public TipoOrganizacion getTipo() {
    return tipo;
  }
  
  //------------------------------------------------------------------------------------------------------------------
  //MIEMBROS
  //------------------------------------------------------------------------------------------------------------------
  public boolean perteneceALaOrganizacion(Miembro miembro) {
    return sectores.stream().filter(sector -> sector.getMiembros().contains(miembro)).collect(Collectors.toList()).size() > 0;
  }

  public void aceptarMiembro(Sector sector, Miembro miembro) {
    if(!sectorExiste(sector)) {
      throw new SectorNoExisteException("No se ha encontrado dicho sector en la organizacion.");
    }
    sector.agregarMiembro(miembro);
    miembro.agregarOrganizacion(this);
  }
  
  //------------------------------------------------------------------------------------------------------------------
  //SECTORES
  //------------------------------------------------------------------------------------------------------------------
  public List<Sector> getSectores() {
    return this.sectores;
}
  
  public void darDeAltaSector(Sector sector) {
  	this.sectores.add(sector);
  }

  private boolean sectorExiste(Sector sector) {
    return sectores.contains(sector);
  }

  //------------------------------------------------------------------------------------------------------------------
  //MEDICIONES
  //------------------------------------------------------------------------------------------------------------------
  public void cargarMedicion(String path){
    mediciones.addAll(LectorCsv.getInstancia().cargarMedicion(path));
  }
  
  public void agregarMedicion(Medicion medicion) {
    mediciones.add(medicion);
  }
  
  public void agregarFactorDeEmision(FactorDeEmision fe) {
    mediciones.forEach(medicion -> medicion.agregarFactorDeEmision(fe));
  }

  public List<Medicion> getMediciones() {
    return mediciones;
  }

  public List<Trayecto> listaTrayectosSinDuplicados(){
    return sectores
        .stream()
        .map(sector -> sector.getMiembros())
        .flatMap(Collection::stream)
        .map(miembro -> miembro.getTrayectos())
        .flatMap(Collection::stream)
        .distinct()
        .collect(Collectors.toList());
  }

  //------------------------------------------------------------------------------------------------------------------
  //HUELLA DE CARBONO
  //------------------------------------------------------------------------------------------------------------------
  public double huellaDeCarbonoMediciones(Imputacion imputacion) {
    return medicionesSegun(imputacion)
          .stream()
          .mapToDouble(medicion -> medicion.datoActividad())
          .sum();
  }
  
  List<Medicion> medicionesSegun(Imputacion periodoDeImputacion){
    return mediciones.stream()
        .filter(medicion -> medicion.perteneceA(periodoDeImputacion))
        .collect(Collectors.toList());
  }
  
  public double huellaCarbonoDeTrasladoDeMiembros() {
    return listaTrayectosSinDuplicados().stream().mapToDouble(trayecto -> trayecto.huellaCarbonoDelTrayecto()).sum();
 
  }
  
  public double huellaDeCarbono(Imputacion imputacion) {
    return huellaDeCarbonoMediciones(imputacion) + huellaCarbonoDeTrasladoDeMiembros();
  }
  
  //------------------------------------------------------------------------------------------------------------------
  //CONTACTOS
  //------------------------------------------------------------------------------------------------------------------
 
  public List<Contacto> getContactos() {
    return contactos;
  }
  
  public void cargarContacto(Miembro miembro){
    contactos.add(miembro.getContacto());
  }

  public void eliminarContacto(Miembro miembro){
    contactos.remove(miembro.getContacto());
  }

  public void setLinkObservers(LinkObserver...servicios){
    Collections.addAll(this.servicios, servicios);
  }
  
  public void removeLinkObserver(LinkObserver servicio){
    servicios.remove(servicio);
  }

  public void recibir(String link){
    if(servicios.size() == 0){
      throw new NoExistenServiciosException("No hay servicios disponibles");
    }
    if(contactos.size() == 0) {
      throw new NoHayContactosException("No hay contactos para notificar en la Organizacion " + this.razonSocial);
    }
    servicios.stream().forEach(servicio -> servicio.enviarNotificacion(link, contactos));
  }

  public String getRazonSocial(){
    return razonSocial;
  }


  public void agregarPostulante(Sector sector, Miembro miembro){
    //postulantes.add(new Postulacion(miembro, sector));
  }

  //public List<Postulacion> getPostulantes(){
    //return postulantes;
  //}

  public List<Miembro> postulantes(){
    return sectores.stream().map(sector -> sector.getPostulantes()).flatMap(Collection::stream).collect(Collectors.toList());
  }

  public void rechazar(Sector sector, Miembro miembro){
    sector.getPostulantes().remove(miembro);
  }

  public void vincular(Sector sector, Miembro miembro){
    sector.agregarMiembro(miembro);
    miembro.agregarOrganizacion(this);
  }
}