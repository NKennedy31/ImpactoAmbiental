package Repositorios;

import java.util.List;
import Consumo.FactorDeEmision;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioFactoresDeEmision implements WithGlobalEntityManager {
  static RepositorioFactoresDeEmision instancia;
  
  private RepositorioFactoresDeEmision() {
  }
  
  public static RepositorioFactoresDeEmision instancia() {
    if(instancia == null) {
      instancia = new RepositorioFactoresDeEmision();
      return instancia;
    }
    else {
      return instancia;
    }
  }
  
  public void agregar(FactorDeEmision factorDeEmision) {
    entityManager().persist(factorDeEmision);
  }

  public List<FactorDeEmision> listar() {
    return entityManager()
        .createQuery("from FactorDeEmision", FactorDeEmision.class)
        .getResultList();
  }
  
  public FactorDeEmision buscar(long id){
    return entityManager().find(FactorDeEmision.class, id);
  }
  
  /*List<FactorDeEmision> lista = new ArrayList<>();

  public double filtrarPorTipoDeConsumo(TipoConsumo tipoDeConsumo) {
    return lista
        .stream()
        .filter(fe -> fe.getTipoDeConsumo() == tipoDeConsumo)
        .collect(Collectors.toList())
        .get(0)
        .getValor();
  }*/
}
