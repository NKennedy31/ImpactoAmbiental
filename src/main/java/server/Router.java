package server;

import javax.persistence.PersistenceException;

import Repositorios.RepositorioUsuarios;
import controlers.*;
import impacto_ambiental.Usuario;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import com.github.jknack.handlebars.Handlebars;

import spark.ModelAndView;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

import static spark.Spark.halt;

public class Router {
  private static HandlebarsTemplateEngine engine;
  
  private static void initEngine() {
    Router.engine = HandlebarsTemplateEngineBuilder
        .create()
        .withDefaultHelpers()
        .withHelper("isTrue", BooleanHelper.isTrue)
        .build();
    
  }
  
  public static void init() {
    Router.initEngine();
    Spark.staticFileLocation("/public");
    Router.configure();
  }

  private static void autenticaRol(Integer idUser, String rol, Response response) {
    if (idUser != null) {
      Usuario usuario = RepositorioUsuarios.instancia().buscar(idUser);
      String rolUser = usuario.getRol();
      if  (!rolUser.equals(rol)) {
        halt(401, "No autorizado");
      }
    }
    if(idUser == null){
      response.redirect("/");
    }
  }


  private static void configure() {
    System.out.println("Iniciando servidor");

    HomeControler homeControler = new HomeControler();
    MedicionesControler medicionesControler = new MedicionesControler();
    SesionControler sesionControler = new SesionControler();
    TrayectoControler trayectoControler = new TrayectoControler();
    TramoControler tramoControler = new TramoControler();
    ReportesControler reportesControler = new ReportesControler();
    OrganizacionControler organizacionControler = new OrganizacionControler();
    CalculadoraControler calculadoraControler = new CalculadoraControler();
    SectorControler sectorControler = new SectorControler();
    PostulanteControler postulanteControler = new PostulanteControler();
    MiembroControler miembroControler = new MiembroControler();


    Spark.before("/",(request, response) -> {
      PerThreadEntityManagers.getEntityManager().clear();
      if((!request.pathInfo().startsWith("/login") || !request.pathInfo().startsWith("/"))
          && request.session().attribute("user_id") == null) {
        response.redirect("/login?orign=/");
      } // redirecciona a loguearse solo cuando ingresa a registrar trayecto y medición
    });

    Spark.before("/medicion", (request, response) -> {
      Integer userId = request.session().attribute("user_id");
      Router.autenticaRol(userId, "organizacion", response);
    });

    Spark.before("/medicion/*", (request, response) -> {
      Integer userId = request.session().attribute("user_id");
      Router.autenticaRol(userId, "organizacion", response);

    });

    Spark.before("/recomendaciones", (request, response) -> {
      Integer userId = request.session().attribute("user_id");
      Router.autenticaRol(userId, "organizacion", response);
    });
    Spark.before("/aceptar-vinculacion", (request, response) -> {
      Integer userId = request.session().attribute("user_id");
      Router.autenticaRol(userId, "organizacion", response);
    });

    Spark.before("/proponer-vinculacion", (request, response) -> {
      Integer userId = request.session().attribute("user_id");
      Router.autenticaRol(userId, "miembro", response);
    });
    Spark.before("/tramos", (request, response) -> {
      Integer userId = request.session().attribute("user_id");
      Router.autenticaRol(userId, "miembro", response);
    });
    Spark.before("/tramos/*", (request, response) -> {
      Integer userId = request.session().attribute("user_id");
      Router.autenticaRol(userId, "miembro", response);
    });

    Spark.after((request, response) -> {
      PerThreadEntityManagers.getEntityManager().clear();
    });
    //Spark.get("/usuarios", UsuarioControler::mostrarTodos, Router.engine); //Motor de renderizado
    
    //HOME
    Spark.get("/", homeControler::getHome, engine);

    //GUIA DE RECOMENDACIONES
    Spark.get("/recomendaciones", (request, response) -> {
      return new ModelAndView(null, "recomendaciones.html.hbs");
    }, engine);

    //MEDICIONES NUEVAS
    Spark.get("/medicion", medicionesControler::mostrarOpcionesDeMedicion, engine);
    Spark.post("/medicion/nueva", medicionesControler::registrarMedicion);
    Spark.post("/medicion/csv", medicionesControler::registrarMedicionCsv);
    
    //SESION
    Spark.get("/login", sesionControler::mostrarLogin, engine); 
    Spark.post("/login", sesionControler::crearSesion);

    Spark.exception(PersistenceException.class, (e, request, response) -> {
      response.redirect("/500"); //TODO
    });

    //aceptar_vinculacion
    /*Spark.get("/aceptar_vinculacion", (request, response) -> {
      return new ModelAndView(null, "aceptar_vinculacion.html.hbs");
    }, engine);*/
    Spark.get("/vinculaciones_pendientes",  organizacionControler::getPostulaciones, engine);
    Spark.post("/vinculaciones_pendientes/vincular", organizacionControler::vincularMiembro);
    Spark.post("/vinculaciones_pendientes/rechazar", organizacionControler::rechazar);
    //Spark.get("/aceptar_vinculacion", postulanteControler::getPostulantesPorOrganizacion, engine);
    //Spark.post("/aceptar_vincualion", postulanteControler::vincularMiembro);

    //postularse
    Spark.get("/vinculacion", sectorControler::getSector, engine);
    Spark.post("/vinculacion" , miembroControler::getMiembroUsuario);
    Spark.post("/vinculacion/nueva", miembroControler::crearPostulante);


    //calculadora
    Spark.get("/calculadora", calculadoraControler::getCalculadora, engine);
    Spark.get("/calculadora/calculo", calculadoraControler::calculo, engine);

    //TRAMOS
    Spark.get("/tramos/nuevo", tramoControler::nuevoTramo, engine);
    Spark.post("/tramos", tramoControler::crearTramo);

    //TRAYECTOS
    Spark.get("/trayectos/nuevo", trayectoControler::nuevoTrayecto, engine);
    Spark.post("/trayectos", trayectoControler::crearTrayecto);
    
    //REPORTES
    Spark.get("/reportes", reportesControler::reportes, engine);
    





    
    /*Spark.get("/organizaciones/:id", consultorasControler::buscar, engine);
    Spark.post("/consultoras", consultorasControler::crear);*/
  }
}