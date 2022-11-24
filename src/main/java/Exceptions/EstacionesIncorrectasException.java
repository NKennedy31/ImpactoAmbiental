package Exceptions;

public class EstacionesIncorrectasException extends RuntimeException {
  public EstacionesIncorrectasException(){
    super("Al menos una estación no pertenece a la linea de transporte");
  }
}
