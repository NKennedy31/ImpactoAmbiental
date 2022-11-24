package controlers;

import Repositorios.RepositorioMiembros;
import Repositorios.RepositorioOrganizaciones;
import com.sun.org.apache.xpath.internal.operations.Mod;
import impacto_ambiental.Miembro;
import impacto_ambiental.Organizacion;
import impacto_ambiental.Postulacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiembroControler implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView getMiembroUsuario(Request request, Response response) {

    Map<String, Object> modelo = new HashMap<>();
    Miembro miembro = RepositorioMiembros.instancia().buscarPorId(1);
    request.session().attribute("miembro_id", miembro.getId());
    //return new ModelAndView(modelo, "proponer_vinculacion.html.hbs");
    return null;
  }

  public Void crearPostulante(Request request, Response response){
    Integer idMiembro = request.session().attribute("miembro_id");

    Miembro miembro = RepositorioMiembros
        .instancia()
        .buscarPorId(idMiembro);

    //miembro.vincularseA();

    response.redirect("/vinculacion");

    return null;

  }
}
