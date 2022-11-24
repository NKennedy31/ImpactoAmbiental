package api;

import api.ServiciosDeComunicacion.Whatsapp.WhatsappService;

public class uso {

    public static void main(String[] args) throws Exception{
      /*ServicioGeoUbicacion servicioGeoUbicacion = new ServicioGeoUbicacion();
      Ubicacion ubicacion1 = new Ubicacion(3,"Rivadavia",150);
      Ubicacion ubicacion2 = new Ubicacion(3,"Rivadavia",5000);
      Distancia distancia = servicioGeoUbicacion.distancia(ubicacion1, ubicacion2);
      System.out.println(distancia.valor);*/

      WhatsappService service = new WhatsappService();
      //service.enviarMensaje();
    }
}
