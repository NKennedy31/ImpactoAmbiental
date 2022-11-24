package Repositorios;

import Trayecto.Tramo;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioTramos implements WithGlobalEntityManager {
  public static RepositorioTramos instancia;

  private RepositorioTramos() {
  }

  public static RepositorioTramos instancia() {
    if(instancia == null) {
      instancia = new RepositorioTramos();
    }
    return instancia;
  }

  public void agregar(Tramo tramo) {
    entityManager().persist(tramo);
  }

  public List<Tramo> listar() {
    return entityManager()
        .createQuery("from Tramo", Tramo.class)
        .getResultList();
  }

  public Tramo buscar(Long id){
    return entityManager().find(Tramo.class, id);
  }
}
