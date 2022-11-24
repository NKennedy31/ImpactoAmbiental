package Repositorios;

import Trayecto.Linea;
import Trayecto.Trayecto;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioTrayectos implements WithGlobalEntityManager {
  public static RepositorioTrayectos instancia;

  private RepositorioTrayectos() {
  }

  public static RepositorioTrayectos instancia() {
    if(instancia == null) {
      instancia = new RepositorioTrayectos();
    }
    return instancia;
  }

  public void agregar(Trayecto trayecto) {
    entityManager().persist(trayecto);
  }

  public List<Trayecto> listar() {
    return entityManager()
        .createQuery("from Trayecto", Trayecto.class)
        .getResultList();
  }

  public Trayecto buscar(Long id){
    return entityManager().find(Trayecto.class, id);
  }
}
