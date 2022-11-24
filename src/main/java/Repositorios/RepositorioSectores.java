package Repositorios;

import impacto_ambiental.Organizacion;
import impacto_ambiental.Sector;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioSectores implements WithGlobalEntityManager {
  public static RepositorioSectores instancia;

  private RepositorioSectores(){}

  public List<Sector> listar() {
    return entityManager()
        .createQuery("from Sector", Sector.class)
        .getResultList();
  }

  public static RepositorioSectores instancia() {
    if(instancia == null) {
      instancia = new RepositorioSectores();
    }
    return instancia;
  }

  public void agregar(Sector sector) {
    entityManager().persist(sector);
  }

}
