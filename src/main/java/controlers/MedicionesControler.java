package controlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Repositorios.RepositorioUsuarios;
import impacto_ambiental.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Consumo.Imputacion;
import Consumo.Medicion;
import Consumo.TipoConsumo;
import Repositorios.RepositorioMediciones;
import Repositorios.RepositorioOrganizaciones;
import Repositorios.RepositorioTipoConsumo;
import impacto_ambiental.LectorCsv;
import impacto_ambiental.Organizacion;
import services.SessionService;
import services.UsuarioService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MedicionesControler implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView mostrarOpcionesDeMedicion(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo = SessionService.getSessionModel(request.session().attribute("user_id"));

    return new ModelAndView(modelo, "inicioMedicion.html.hbs");
  }
  
  public Void registrarMedicion(Request request, Response response) {
    TipoConsumo tipoConsumo = RepositorioTipoConsumo
        .instancia()
        .buscarPorNombre(request.queryParams("tipo-de-consumo"));
    
    Double valor = Double.parseDouble(request.queryParams("valor"));
    String imputacion = request.queryParams("imputacion");
    
    Medicion medicion = new Medicion(tipoConsumo, valor, new Imputacion(imputacion));
    
    Integer idOrganizacion = request.session().attribute("organizacion_id");
    
    
    Organizacion organizacion = RepositorioOrganizaciones
        .instancia()
        .buscarPorId(idOrganizacion);
    
    organizacion.agregarMedicion(medicion); 
    
    withTransaction(() ->{
    RepositorioMediciones.instancia().agregar(medicion);
    });
    
    List<String> nombres = organizacion.getMediciones().stream().map(o->o.getTipoConsumo().getNombre()).collect(Collectors.toList());
    System.out.print(nombres);
    response.redirect("/medicion");
    return null;
  }
  
  //Esta fallando
  public Void registrarMedicionCsv(Request request, Response response) {

    Map<String, Object> modelo = new HashMap<>();
    modelo.put("sesionIniciada", request.session().attribute("user_id") != null);
    
    Integer idOrganizacion = request.session().attribute("organizacion_id");
    
    Organizacion organizacion = RepositorioOrganizaciones
        .instancia()
        .buscarPorId(idOrganizacion);
    
    LectorCsv lector = new LectorCsv();
    
    String path = request.queryParams("csv"); 
    
    organizacion.cargarMedicion(path);
    
    RepositorioMediciones.instancia().agregarListaMediciones(lector.cargarMedicion(path));
 
   /* withTransaction(() ->{
    RepositorioMediciones.instancia().agregarListaMediciones(lector.cargarMedicion(path));
    });*/
    
    response.redirect("/medicion");
    
    List<String> nombres = organizacion.getMediciones().stream().map(o->o.getTipoConsumo().getNombre()).collect(Collectors.toList());
    System.out.print(nombres);
    return null;
  }
}
