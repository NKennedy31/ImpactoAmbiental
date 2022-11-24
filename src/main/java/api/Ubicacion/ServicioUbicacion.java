package api.Ubicacion;

import Trayecto.Ubicacion;
import api.Ubicacion.Entities.Distancia;

import java.io.IOException;

public interface ServicioUbicacion {
  public Distancia distancia(Ubicacion inicio, Ubicacion fin) throws IOException;
}
