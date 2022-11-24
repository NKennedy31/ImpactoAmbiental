package Exceptions;

public class NoExistenServiciosException extends RuntimeException {
  public NoExistenServiciosException(String message) {
    super(message);
  }
}
