package services;

import impacto_ambiental.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioService {

  public static List<Map<String, String>> getlinksByUserRol(Usuario usuario) {
    List<Map<String, String>> links = new ArrayList<>();
    if (usuario.getRol().equals("organizacion")) {
      Map<String, String> linkMediciones = new HashMap<String, String>();
      linkMediciones.put("name", "Registrar Mediciones");
      linkMediciones.put("url", "/medicion");

      Map<String, String> linkVinculaciones = new HashMap<String, String>();
      linkVinculaciones.put("name", "Vinculaciones");
      linkVinculaciones.put("url", "/vinculaciones_pendientes");

      Map<String, String> linkHuella = new HashMap<String, String>();
      linkHuella.put("name", "Calcular Huella de carbono");
      linkHuella.put("url", "/calculadora");


      links.add( linkMediciones);
      links.add( linkVinculaciones);
      links.add( linkHuella);
      return links;

    }
    if (usuario.getRol().equals("miembro")) {
      Map<String, String> linkTrayectos = new HashMap<String, String>();
      linkTrayectos.put("name", "Registrar Trayectos");
      linkTrayectos.put("url", "/trayecto/nueva");

      Map<String, String> linkVinculaciones = new HashMap<String, String>();
      linkVinculaciones.put("name", "Vinculaciones");
      linkVinculaciones.put("url", "/vinculacion");

      Map<String, String> linkHuella = new HashMap<String, String>();
      linkHuella.put("name", "Calcular Huella de carbono");
      linkHuella.put("url", "/calculadora");

      links.add( linkTrayectos);
      links.add( linkVinculaciones);
      links.add( linkHuella);
      return links;

    }
    if (usuario.getRol().equals("admin")) {
      Map<String, String> linkOrg = new HashMap<String, String>();
      linkOrg.put("name", "Registrar Organizaciones");
      linkOrg.put("url", "/organizaciones/nueva");
      links.add( linkOrg);
      return links;
    }
    return links;
  }
}
