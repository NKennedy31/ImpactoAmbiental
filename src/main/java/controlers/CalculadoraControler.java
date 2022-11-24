package controlers;

import Repositorios.RepositorioOrganizaciones;
import Repositorios.RepositorioUsuarios;
import impacto_ambiental.Organizacion;
import impacto_ambiental.Usuario;
import services.SessionService;
import services.UsuarioService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculadoraControler {

  public ModelAndView getCalculadora(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo = SessionService.getSessionModel(request.session().attribute("user_id"));

    return new ModelAndView(modelo, "calculadora.html.hbs");
  }
  
  public ModelAndView calculo(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo = SessionService.getSessionModel(request.session().attribute("user_id"));

    return new ModelAndView(modelo, "calculadora.html.hbs");
  }
}
