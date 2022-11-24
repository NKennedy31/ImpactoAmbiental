package Repositorios;

import Consumo.Unidad;
import Trayecto.Linea;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioLineas implements WithGlobalEntityManager {
  public static RepositorioLineas instancia;

  private RepositorioLineas() {
  }

  public static RepositorioLineas instancia() {
    if(instancia == null) {
      instancia = new RepositorioLineas();
    }
    return instancia;
  }

  public void agregar(Linea linea) {
    entityManager().persist(linea);
  }

  public List<Linea> listar() {
    return entityManager()
        .createQuery("from Linea", Linea.class)
        .getResultList();
  }

  public Linea buscar(String nombre){
    return entityManager()
        .createQuery("from Linea l WHERE l.nombre = :nombre", Linea.class).
        setParameter("nombre", nombre).getSingleResult();
  }
}
