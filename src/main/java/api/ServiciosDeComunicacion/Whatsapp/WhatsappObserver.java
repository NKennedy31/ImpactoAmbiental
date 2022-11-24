package api.ServiciosDeComunicacion.Whatsapp;

import api.ServiciosDeComunicacion.LinkObserver;
import impacto_ambiental.Contacto;

import java.util.List;

public class WhatsappObserver implements LinkObserver {
  public WhatsappSender whatsappSender = new WhatsappService();

  public WhatsappObserver(WhatsappSender whatsappSender){
    this.whatsappSender = whatsappSender;
  }
  
  @Override
  public void enviarNotificacion(String link, List<Contacto> contactos)
  {
    whatsappSender.sendSms(new Sms());
  }
  
  public void setWhatsappSender(WhatsappSender whatsappSender) {
    this.whatsappSender = whatsappSender;
  }
}
