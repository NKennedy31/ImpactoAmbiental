package Repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Consumo.Medicion;
import Consumo.Unidad;

public class RepositorioUnidades implements WithGlobalEntityManager{
  public static RepositorioUnidades instancia;
  
  private RepositorioUnidades() {
  }
  
  public static RepositorioUnidades instancia() {
    if(instancia == null) {
      instancia = new RepositorioUnidades(); 
    }
    return instancia;
  }
  
  public void agregar(Unidad medicion) {
    entityManager().persist(medicion);
  }

  public List<Unidad> listar() {
    return entityManager()
        .createQuery("from Unidad", Unidad.class)
        .getResultList();
  }
  
  public Unidad buscar(long id){
    return entityManager().find(Unidad.class, id);
  }

}
