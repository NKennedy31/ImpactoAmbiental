package controlers;

import Repositorios.RepositorioOrganizaciones;
import Repositorios.RepositorioSectores;
import impacto_ambiental.Organizacion;
import impacto_ambiental.Sector;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectorControler implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView getSector(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();

    List<Sector> sectores = RepositorioSectores.instancia().listar();

    modelo.put("sectores", sectores);

    return new ModelAndView(modelo, "proponer_vinculacion.html.hbs");
  }

}
