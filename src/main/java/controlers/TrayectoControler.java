package controlers;

import Consumo.NombreTipoConsumo;
import Consumo.TipoConsumo;
import Consumo.Unidad;
import Exceptions.NoPerteneceOrganizacion;
import Repositorios.*;
import Trayecto.*;
import impacto_ambiental.Miembro;
import impacto_ambiental.Organizacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import services.SessionService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class TrayectoControler implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView nuevoTrayecto(Request request, Response response) {

    Map<String, Object> modelo = new HashMap<>();

    //modelo.put("sesionIniciada", request.session().attribute("user_id") != null);

    modelo = SessionService.getSessionModel(request.session().attribute("user_id"));
    //modelo.put("sesionIniciada", request.session().attribute("user_id") != null);
    //modeloFE.put("fes",RepositorioFactoresDeEmision.instancia.listar());

    return new ModelAndView(modelo, "registroTrayecto.html.hbs");
  }

  public Void crearTrayecto(Request request, Response response) {
    if(request.queryParams("agregarTramo").equals("no")) {
      withTransaction(() -> {
        List<Tramo> tramosPendientes = new ArrayList<>();
        tramosPendientes.add(request.session().attribute("tramos"));

        Miembro miembro = RepositorioMiembros.instancia().buscarPorId(request.session().attribute("miembro_id"));
        /*Integer id = request.session().attribute("user_id");
        Miembro miembro = RepositorioUsuarios.instancia().buscar(id).getMiembro();*/
        Organizacion organizacion =
            RepositorioOrganizaciones.instancia().buscarPorNombre(request.queryParams("organizacion"));
        try {
          miembro.agregarUnTrayecto(new Trayecto(tramosPendientes),organizacion);
        } catch (NoPerteneceOrganizacion e) {
          throw new RuntimeException(e);
        }
      });
    } else {
      withTransaction(() -> {
        response.redirect("/tramo/nuevo");
      });
    }

    response.redirect("/");
    return null;
  }

}
