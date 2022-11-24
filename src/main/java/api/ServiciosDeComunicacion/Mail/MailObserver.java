package api.ServiciosDeComunicacion.Mail;

import api.ServiciosDeComunicacion.LinkObserver;
import impacto_ambiental.Contacto;

import java.util.List;

public class MailObserver implements LinkObserver {

  private MailSender mailSender;

  public MailObserver(MailSender mailSender){
    this.mailSender = mailSender;
  }
  @Override
  public void enviarNotificacion(String link, List<Contacto> contactos) {
    mailSender.sendEmail(new Mail());
  }

  public void setMailService(MailSender mailSender) {
    this.mailSender = mailSender;
  }
}
