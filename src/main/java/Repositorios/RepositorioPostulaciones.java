package Repositorios;

import impacto_ambiental.Postulacion;
import impacto_ambiental.Sector;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioPostulaciones implements WithGlobalEntityManager {
  public static RepositorioPostulaciones instancia;

  public RepositorioPostulaciones(){}

  public List<Postulacion> listar() {
    return entityManager()
        .createQuery("from Postulacion", Postulacion.class)
        .getResultList();
  }

  public List<Postulacion> listarDeOrganizacion(int id) {
    return entityManager()
        .createQuery("from Postulacion", Postulacion.class)
        .getResultList()
        .stream().filter(postulacion -> postulacion.getSector().getOrganizacion().id == id).collect(Collectors.toList());
  }

  public static RepositorioPostulaciones instancia() {
    if(instancia == null) {
      instancia = new RepositorioPostulaciones();
    }
    return instancia;
  }

  public void agregar(Postulacion postulacion) {
    entityManager().persist(postulacion);
  }

}
