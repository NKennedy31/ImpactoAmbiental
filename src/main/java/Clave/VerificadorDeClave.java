package Clave;

import java.util.Arrays;
import java.util.List;

public class VerificadorDeClave {

  private List<ValidacionClave> validaciones; // listado de validaciones a realizar

  private static VerificadorDeClave verificadorDeClave = new VerificadorDeClave();

  private VerificadorDeClave() { // constructor
    validaciones = Arrays.asList(
        new NoEsClavecomun(),
        new MinimaCantidadDeCaracteres()
    );
  }

  public static VerificadorDeClave getVerificadorDeClavesInstance() {
    return verificadorDeClave;
  }

  public Boolean esClaveSegura(String clave) {
    return validaciones.stream().allMatch(chequeo -> chequeo.esValida(clave));
  }

}
