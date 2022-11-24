package controlers;

import Repositorios.RepositorioPostulaciones;
import Repositorios.RepositorioTramos;
import Repositorios.RepositorioTrayectos;
import Trayecto.Tramo;
import Trayecto.Trayecto;
import impacto_ambiental.Postulacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.Request;
import spark.Response;

import java.util.List;

public class PostulanteControler implements WithGlobalEntityManager, TransactionalOps {
/*
  public Void crearPostulante(Request request, Response response) {
    if (request.queryParams("agregarPostulante").equals("no")) {
      withTransaction(() -> {
        List<Postulacion> postulaciones = RepositorioPostulaciones.instancia().listar();
        //Postulacion postulacion = new Postulacion();
        //RepositorioTrayectos.instancia().agregar(postulacion);
      });
    } else {
      withTransaction(() -> {
        //response.redirect("/tramo/nuevo");
      });
    }
  }*/
}
