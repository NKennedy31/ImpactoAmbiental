package Clave;

import Utilidades.CargadorDeArchivo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositorioClavesComunes {

  private static RepositorioClavesComunes repositorioClavesComunes = new RepositorioClavesComunes();
  private Set<String> clavesComunes;

  private RepositorioClavesComunes() {
    String archivo = "common-passwords.txt";
    List<String> strings = CargadorDeArchivo.procesar(archivo);
    this.clavesComunes = new HashSet<>(strings);
  }

  public static RepositorioClavesComunes getRepositorioclavesComunesInstance() {return repositorioClavesComunes; }

  public Boolean esComun(String clave) {return clavesComunes.contains(clave);}

}
