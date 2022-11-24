/*package impacto_ambiental;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import impacto_ambiental.Organizacion;

import Repositorios.RepositorioOrganizaciones;
import Trayecto.Ubicacion;

public class ImpactoAmbientalApp {
  static Organizacion ixpandit = new Organizacion("Ixpandit", TipoOrganizacion.EMPRESA, new Ubicacion(1, "Roque Saenz Pena", 917), Clasificacion.EMPRESASECTORSECUNDARIO );
  static Organizacion mercadoLibre = new Organizacion("mercadoLibre", TipoOrganizacion.EMPRESA, new Ubicacion(1, "Las Heras", 1000), Clasificacion.EMPRESASECTORPRIMARIO );

  public static void main(String[] args){
    RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones(
        ixpandit,
        mercadoLibre
    );
    Notificador notificador = new Notificador(repositorioOrganizaciones);
    Timer T = new Timer();
    TimerTask Notificacion = new TimerTask(){
      @Override
      public void run(){
        notificador.enviarNotificaciones("www.guia-de-recomendaciones.com.ar");
      }
    };
    //El 1ª de c/mes a las 10 am se ejecutaría el envío del link a todas las organizaciones
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DATE, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 10);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);

    T.schedule(Notificacion, calendar.getTime());
  }
}*/
