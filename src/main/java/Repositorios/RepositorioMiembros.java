package Repositorios;

import impacto_ambiental.Miembro;
import impacto_ambiental.Organizacion;
import impacto_ambiental.Postulacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioMiembros implements WithGlobalEntityManager {
  public static RepositorioMiembros instancia;

  public RepositorioMiembros(){}

  public Miembro buscarPorId(Integer id){
    return entityManager().find(Miembro.class, id);
  }

  public List<Miembro> listar() {
    return entityManager()
        .createQuery("from Miembro", Miembro.class)
        .getResultList();
  }

  public static RepositorioMiembros instancia() {
    if(instancia == null) {
      instancia = new RepositorioMiembros();
    }
    return instancia;
  }

  public void agregar(Miembro miembro) {
    entityManager().persist(miembro);
  }


}
