package controlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Repositorios.RepositorioUsuarios;
import impacto_ambiental.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;


import services.SessionService;
import Repositorios.RepositorioOrganizaciones;
import Repositorios.RepositorioSectoresTerritoriales;
import impacto_ambiental.Organizacion;
import impacto_ambiental.Reporter;
import impacto_ambiental.SectorTerritorial;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ReportesControler implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView reportes(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo = SessionService.getSessionModel(request.session().attribute("user_id"));
    return new ModelAndView(modelo, "reportes.html.hbs");
  }
  
  public ModelAndView pantallaReporte(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo = SessionService.getSessionModel(request.session().attribute("user_id"));
    return new ModelAndView(modelo, "mostrarReporte.html.hbs");
  }
  
  public Void mostrarReporte(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo = SessionService.getSessionModel(request.session().attribute("user_id"));
    String tipoReporte = request.params(":reporte");
    modelo.put("reportes-organizacion", request.session().attribute("organizacion_id") != null);
    
    Reporter reportador = new Reporter();
    
    if(request.session().attribute("organizacion_id") != null) {
    Integer idOrganizacion = request.session().attribute("organizacion_id");
    
    Organizacion organizacion = RepositorioOrganizaciones.instancia().buscarPorId(idOrganizacion);
    
    //modelo.put("huellaDeCarbonoOrganizacion", reportador.huellaCarbonoTotalOrganizacion(organizacion));
    modelo.put("composicionHuellaDeCarbonoOrganizacion", reportador.composicionHuellaOrganizacion(organizacion));
    //modelo.put("evolucionHuellaDeCarbonoOrganizacion", reportador.evolucionHuellaOrganizacion(organizacion));
    return new ModelAndView(modelo, "reportesMostrar.html.hbs");
    }
    else {
      Integer idSector = request.session().attribute("sector_id");
      
      SectorTerritorial sector = RepositorioSectoresTerritoriales.
          instancia()
          .buscar(idSector);
      
      modelo.put("huellaDeCarbonoSector", reportador.huellaCarbonoTotalSector(sector));
      modelo.put("composicionHuellaDeCarbonoSector", reportador.composicionHuellaSector(sector));
      //modelo.put("evolucionHuellaDeCarbonoSector", reportador.evolucionHuellaSector(sector));
      return new ModelAndView(modelo, "reportesMostrar.html.hbs");
    }
  }
}
  