package api.ServiciosDeComunicacion.Mail;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;

import java.io.IOException;
import java.util.ArrayList;

public class MailService implements MailSender {
  private static final String API_KEY = "ZpxhYiKXBJ2rAaoigOZwfw";
  @Override
  public void sendEmail(Mail mail) {
    MandrillApi mandrillApi = new MandrillApi(API_KEY);
    MandrillMessage message = new MandrillMessage();
    message.setSubject("Guia de Recomendacion");
    message.setHtml("<h1>Hi pal!</h1><br /><a>https://www.guiaderecomendacion2022.com</a>");
    message.setAutoText(true);
    message.setFromEmail("melaniealarcon97@gmail.com");
    message.setFromName("Organizacion");

    try {
        ArrayList<MandrillMessage.Recipient> recipients = new ArrayList<MandrillMessage.Recipient>();
        MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
        recipient.setEmail("mail.com");
        recipients.add(recipient);
        message.setTo(recipients);
        message.setPreserveRecipients(true);
        mandrillApi.messages().send(message, null);
    } catch (final MandrillApiError e) {
      System.out.println(e.getMandrillErrorAsJson());
      System.out.println(e);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
