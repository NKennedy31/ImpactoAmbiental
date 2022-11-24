package Clave;

public class NoEsClavecomun implements ValidacionClave {


  @Override
  public Boolean esValida(String clave) {
    return ! RepositorioClavesComunes.getRepositorioclavesComunesInstance().esComun(clave);
  }
}
