package controlers;

import java.lang.reflect.Array;
import java.util.*;

import Repositorios.RepositorioUsuarios;
import Utilidades.Hasheador;
import impacto_ambiental.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import services.SessionService;
import services.UsuarioService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeControler {
  public ModelAndView getHome(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo = SessionService.getSessionModel(request.session().attribute("user_id"));
    return new ModelAndView(modelo, "index.html.hbs");
}
}
