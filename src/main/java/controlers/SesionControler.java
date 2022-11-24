package controlers;

import Repositorios.RepositorioMiembros;
import Trayecto.Tramo;
import Utilidades.Hasheador;
import impacto_ambiental.Miembro;
import Repositorios.RepositorioOrganizaciones;
import Trayecto.Tramo;
import Utilidades.Hasheador;
import impacto_ambiental.Miembro;
import impacto_ambiental.Organizacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

import Repositorios.RepositorioOrganizaciones;
import Repositorios.RepositorioUsuarios;
import impacto_ambiental.Organizacion;
import impacto_ambiental.Usuario;

public class SesionControler {
  // TODO GRAN TODO: notar que las responsabildades
  // de saber si una personas está con sesión inciada,
  // de saber el usuario actual, etc, probablmente se vayan a repetir
  // y convendrá generalizarlas

  public ModelAndView mostrarLogin(Request request, Response response) {
    /*if (request.session().attribute("user_id") != null) {
      response.redirect("/"); //El index va a variar segun el tipo de usuario
      return null;
    }*/
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("sesionIniciada", request.session().attribute("user_id") != null);
    return new ModelAndView(modelo, "login.html.hbs");
  }
  
  public Void crearSesion(Request request, Response response) {
    try {
      Usuario usuario = RepositorioUsuarios.instancia().buscarPorUsuarioYContrasenia(
              request.queryParams("username"),
          Hasheador.procesar(request.queryParams("password")));
      System.out.println(usuario);
      // guardarlo en la session de servelet (jsessionid)
      // en realidad esto también lo guarda en la cookie
      // pero de forma indirecta
      request.session().attribute("user_id", usuario.getId());
      //List<Tramo> tramos = new ArrayList<>();
      //request.session().attribute("tramos",tramos);
      /*Miembro miembro = RepositorioMiembros.instancia().buscarPorId(1);
      request.session().attribute("miembro_id", miembro.getId());*/

//NUEVO
      Organizacion organizacion = RepositorioOrganizaciones.instancia().buscarPorId(1);
      request.session().attribute("organizacion_id", organizacion.getId());
  
      /*SectorTerritorial sector = RepositorioSectoresTerritoriales.instancia().buscar(1);
      request.session().attribute("sector_id", sector.getId());*/
      response.redirect("/"); // TODO aca va a convenir leer el origen
      return null;
    } catch (NoSuchElementException e) {
      response.redirect("/login"); // TODO redirigir agregando un mensaje de error
      return null;
    }
  }
}
