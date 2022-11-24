package Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Consumo.NombreTipoConsumo;
import Consumo.TipoConsumo;
import impacto_ambiental.Organizacion;


public class RepositorioTipoConsumo  implements WithGlobalEntityManager  {
  public static RepositorioTipoConsumo instancia;
  
  private RepositorioTipoConsumo() {
  }
  
  public static RepositorioTipoConsumo instancia() {
    if(instancia == null) {
      instancia = new RepositorioTipoConsumo(); 
    }
    return instancia;
  }
  
  public void agregar(TipoConsumo tipoConsumo) {
    entityManager().persist(tipoConsumo);
  }
  
  public List<TipoConsumo> listar() {
    return entityManager()
        .createQuery("from TipoConsumo", TipoConsumo.class)
        .getResultList();
  }
  
  public Organizacion buscar(long id){
    return entityManager().find(Organizacion.class, id);
  }

  public TipoConsumo buscarPorNombre(String nombre){
    return entityManager()
        .createQuery("from TipoConsumo", TipoConsumo.class)
        .getResultList()
        .stream().filter(tipoConsumo -> tipoConsumo.getNombre().equals(nombre))
        .collect(Collectors.toList())
        .get(0);
}


}
