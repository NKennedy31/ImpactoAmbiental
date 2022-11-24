import Trayecto.Ubicacion;
import api.ServiciosDeComunicacion.Mail.MailObserver;
import api.ServiciosDeComunicacion.Mail.MailService;
import api.ServiciosDeComunicacion.Whatsapp.WhatsappObserver;
import api.ServiciosDeComunicacion.Whatsapp.WhatsappService;
import impacto_ambiental.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiciosDeComunicacionTest {
  private MailService mailservice;
  private WhatsappService whatsappservice;
  @BeforeEach
  void initFileSystem() {
    mailservice = Mockito.mock(MailService.class);
    whatsappservice = Mockito.mock(WhatsappService.class);
  }

  @Test
  public void CargarContactoEnOrganizacionYEnviarRecomendacion(){
    Miembro melanie = altaMiembro();
    Organizacion ixpandit = altaOrganizacion();
    Contacto contacto = new Contacto("1155028171", "melaniealarcon97@gmail.com");
    melanie.setContacto(contacto);
    ixpandit.cargarContacto(melanie);
    ixpandit.setLinkObservers(
        new MailObserver(mailservice),
        new WhatsappObserver(whatsappservice)
    );
    ixpandit.recibir("guiaDeRecomendacion.com.ar");
    Mockito.verify(mailservice, Mockito.only()).sendEmail(Mockito.any());


  }

  private Organizacion altaOrganizacion(){
    return new Organizacion("Ixpandit", TipoOrganizacion.EMPRESA, new Ubicacion(1, "Roque Saenz Pena", 917), Clasificacion.EMPRESASECTORSECUNDARIO );
  }

  private Miembro altaMiembro(){
    return new Miembro("Melanie", "Alarcon", TipoDocumento.DNI, "40190745", new ArrayList<>());
  }
}
