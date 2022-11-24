package controlers;

import Repositorios.RepositorioOrganizaciones;
import Repositorios.RepositorioPostulaciones;
import impacto_ambiental.Miembro;
import impacto_ambiental.Organizacion;
import impacto_ambiental.Postulacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import services.SessionService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrganizacionControler implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView getOrganizacion(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();

    modelo = SessionService.getSessionModel(request.session().attribute("user_id"));
    List<Organizacion> organizaciones = RepositorioOrganizaciones.instancia.listar();
    modelo.put("organizaciones", organizaciones);

    return new ModelAndView(modelo, "proponer_vinculacion.html.hbs");
  }

  public ModelAndView getPostulaciones(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();

    Integer idOrganizacion = request.session().attribute("organizacion_id");

    Organizacion organizacion = RepositorioOrganizaciones
        .instancia()
        .buscarPorId(idOrganizacion);

    List<Miembro> postulantes = organizacion.postulantes();

    modelo.put("postulaciones", postulantes);

    return new ModelAndView(modelo, "aceptar_vinculacion.html.hbs");
  }

  public Void vincularMiembro(Request request, Response response){
    Integer idOrganizacion = request.session().attribute("organizacion_id");

    Organizacion organizacion = RepositorioOrganizaciones
        .instancia()
        .buscarPorId(idOrganizacion);

    //organizacion.aceptarMiembro();

    response.redirect("/vinculaciones_pendientes");
    return null;
  }


  public Void rechazar(Request request, Response response){
    Integer idOrganizacion = request.session().attribute("organizacion_id");

    Organizacion organizacion = RepositorioOrganizaciones
        .instancia()
        .buscarPorId(idOrganizacion);

    //organizacion.rechazar();


    response.redirect("/vinculaciones_pendientes");
    return null;
  }

}
