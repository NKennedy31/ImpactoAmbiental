package Repositorios;

import java.util.List;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Trayecto.Ubicacion;
import impacto_ambiental.SectorTerritorial;

public class RepositorioSectoresTerritoriales  implements WithGlobalEntityManager{
  public static RepositorioSectoresTerritoriales instancia;
  
  private RepositorioSectoresTerritoriales() {
  }
  
  public static RepositorioSectoresTerritoriales instancia() {
    if(instancia == null) {
      instancia = new RepositorioSectoresTerritoriales(); 
    }
    return instancia;
  }
  
  public void agregar(SectorTerritorial sectorTerritorial) {
    entityManager().persist(sectorTerritorial);
  }

  public List<SectorTerritorial> listar() {
    return entityManager()
        .createQuery("from SectorTerritorial", SectorTerritorial.class)
        .getResultList();
  }
  
  public SectorTerritorial buscar(long id){
    return entityManager().find(SectorTerritorial.class, id);
  }
  
  public SectorTerritorial buscarPorUbicacion(Ubicacion ubicacion){
    return entityManager()
        .createQuery("from SectorTerritorial where ubicacion.altura =" + ubicacion.getAltura()
                     + " and ubicacion.localidad=" + ubicacion.getLocalidad() 
                     + " and ubicacion.calle = " + "'" + ubicacion.getCalle().toString()+ "'"
                     , SectorTerritorial.class)
        .getSingleResult();
   /* int localidad = ubicacion.getLocalidad();
    int altura = ubicacion.getAltura();
    String calle = ubicacion.getCalle();
    
    SectorTerritorial s1 = entityManager().find(SectorTerritorial.class, altura);
    SectorTerritorial s2 = entityManager().find(SectorTerritorial.class, calle);
    SectorTerritorial s3 = entityManager().find(SectorTerritorial.class, localidad);
    
    if(s1.getId() == s2.getId() && s1.getId() == s3.getId() && s2.getId() == s3.getId()) {
      return s1;
    }
    return null;*/
  }

}
