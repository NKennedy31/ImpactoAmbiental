package Trayecto;

import javax.persistence.Embeddable;

@Embeddable
public class Ubicacion{
  int localidad;
  
  String calle;
  
  int altura;
  
  public Ubicacion() {}

  public Ubicacion(int localidad, String calle, int altura) {
    this.localidad = localidad;
    this.calle = calle;
    this.altura = altura;
  }

  public int getAltura() {
    return altura;
  }

  public int getLocalidad() {
    return localidad;
  }

  public String getCalle() {
    return calle;
  }
}
