package Exceptions;

public class UnidadesIncorrectasException extends RuntimeException {
  public UnidadesIncorrectasException() {
    super("La unidad del factor de emisión no coincide con las del tipo de consumo");
  }
}
