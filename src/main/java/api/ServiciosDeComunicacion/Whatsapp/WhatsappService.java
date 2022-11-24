package api.ServiciosDeComunicacion.Whatsapp;

import com.twilio.Twilio;

public class WhatsappService implements WhatsappSender {
  public static final String ACCOUNT_SID = "AC27baddf0bf71b6ba7fd3f9eb5e0341fb";
  public static final String AUTH_TOKEN = "e898710a7aae510600e0cf16bd81e6a1";
  @Override
  public void sendSms(Sms sms) {

      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

      /*Message message = Message.creator(
          new PhoneNumber("+5491165485764"),
          new PhoneNumber("+5491155028171"),
          "This is the ship that made the Kessel Run in fourteen parsecs?").create();

      System.out.println(message.getSid());*/
  }
}
