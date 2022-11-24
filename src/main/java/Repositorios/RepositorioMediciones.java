package Repositorios;

import java.util.List;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Consumo.Medicion;

public class RepositorioMediciones implements WithGlobalEntityManager{
  public static RepositorioMediciones instancia;
  
  private RepositorioMediciones() {
  }
  
  public static RepositorioMediciones instancia() {
    if(instancia == null) {
      instancia = new RepositorioMediciones(); 
    }
    return instancia;
  }
  
  public void agregar(Medicion medicion) {
    entityManager().persist(medicion);
  }
  
  public void agregarListaMediciones(List<Medicion> mediciones) {
    mediciones.forEach(unaMedicion -> agregar(unaMedicion));
  }

  public List<Medicion> listar() {
    return entityManager()
        .createQuery("from Medicion", Medicion.class)
        .getResultList();
  }
  
  public Medicion buscar(long id){
    return entityManager().find(Medicion.class, id);
  }
  
  public List<Medicion> listarSegunOrganizacion(long id) {
    return entityManager()
        .createQuery("SELECT tipoConsumo FROM Medicion WHERE organizacion_id LIKE " + id)
        .getResultList();
  }

}