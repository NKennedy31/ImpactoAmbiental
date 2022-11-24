package api.ServiciosDeComunicacion;

import impacto_ambiental.Contacto;

import java.util.List;

public interface LinkObserver {
  public abstract void enviarNotificacion(String link, List<Contacto> contactos);
}
