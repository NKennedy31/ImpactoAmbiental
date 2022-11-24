package controlers;

import Consumo.NombreTipoConsumo;
import Consumo.TipoConsumo;
import Consumo.Unidad;
import Repositorios.RepositorioLineas;
import Repositorios.RepositorioTipoConsumo;
import Repositorios.RepositorioTramos;
import Trayecto.*;
import impacto_ambiental.Miembro;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import services.SessionService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class TramoControler implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView nuevoTramo(Request request, Response response) {

    /*if (request.session().attribute("user_id") == null) {   //redirecciona a loguearse desde Spark.Before
      response.redirect("/login?orign=/tramo/nuevo");
      return null;
    }
    Map<String, Object> model = new HashMap<>();
    model.put("sesionIniciada", request.session().attribute("user_id") != null);
    return new ModelAndView(model, "nuevoTramo.html.hbs");*/

    Map<String, Object> modelo = new HashMap<>();
    modelo = SessionService.getSessionModel(request.session().attribute("user_id"));
    return new ModelAndView(modelo, "nuevoTramo.html.hbs");
  }

  public Void crearTramo(Request request, Response response) {

    TipoConsumo tipoConsumo = RepositorioTipoConsumo
        .instancia()
        .buscarPorNombre(request.queryParams("tipoConsumo"));

      System.out.println(request.queryParams());

      if(request.queryParams("tipoTransporte").equals("privado")){

              Tramo tramo = new Tramo(
                  new TransportePrivado(
                      Double.parseDouble(request.queryParams("combustibleXKm")),
                      new Ubicacion(Integer.parseInt(request.queryParams("localidadInicio")), request.queryParams("calleInicio"), Integer.parseInt(request.queryParams("alturaInicio"))),
                      new Ubicacion(Integer.parseInt(request.queryParams("localidadFin")), request.queryParams("calleFin"), Integer.parseInt(request.queryParams("alturaFin"))),
                      tipoConsumo
                  ));

        withTransaction(() -> {

              List<Tramo> tramosPendientes = new ArrayList<>();
              tramosPendientes.add(request.session().attribute("tramos"));
              request.session().attribute("tramos",tramosPendientes);

          });

          response.redirect("/trayectos/nuevo");
          return null;

      } else {
        Linea linea = RepositorioLineas.instancia().buscar(request.queryParams("linea"));
              Tramo tramo = new Tramo(
                  new TransportePublico(linea,
                      new Estacion(Integer.parseInt(request.queryParams("kmPartida"))),
                      new Estacion(Integer.parseInt(request.queryParams("kmLlegada"))),
                      Double.parseDouble(request.queryParams("combustibleXKm")),
                      tipoConsumo
                      ));

        withTransaction(() -> {

          List<Tramo> tramosPendientes = new ArrayList<>();
          tramosPendientes.add(request.session().attribute("tramos"));
          request.session().attribute("tramos",tramosPendientes);

        });

          response.redirect("/trayectos/nuevo");
          return null;

      }
  }
}