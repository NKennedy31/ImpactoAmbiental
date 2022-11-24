package Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Consumo.Medicion;
import impacto_ambiental.Organizacion;
import impacto_ambiental.SectorTerritorial;
import impacto_ambiental.TipoOrganizacion;

public class RepositorioOrganizaciones implements WithGlobalEntityManager{
  public static RepositorioOrganizaciones instancia;
  
  private RepositorioOrganizaciones() {
  }
  
  public static RepositorioOrganizaciones instancia() {
    if(instancia == null) {
      instancia = new RepositorioOrganizaciones(); 
    }
    return instancia;
  }
  
  public void agregar(Organizacion organizacion) {
    entityManager().persist(organizacion);
  }

  public List<Organizacion> listar() {
    return entityManager()
        .createQuery("from Organizacion", Organizacion.class)
        .getResultList();
  }
  
  public Organizacion buscarPorId(Integer id){
    return entityManager().find(Organizacion.class, id);
  }
  
  public void remover(Organizacion organizacion){
    entityManager().remove(organizacion);
  }
  
  public Organizacion buscarPorNombre(String nombre){
      return entityManager()
          .createQuery("from Organizacion", Organizacion.class)
          .getResultList()
          .stream().filter(organizacion -> organizacion.getNombre().equals(nombre))
          .findAny().get();
  }
  
  public List<Organizacion> buscarPorClasificacion(TipoOrganizacion tipo){
    return entityManager()
        .createQuery("from Organizacion", Organizacion.class)
        .getResultList()
        .stream()
        .filter(unaOrganizacion -> unaOrganizacion.getTipo() == tipo)
        .collect(Collectors.toList());
  }
}


