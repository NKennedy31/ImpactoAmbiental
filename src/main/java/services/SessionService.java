package services;

import Repositorios.RepositorioUsuarios;
import impacto_ambiental.Usuario;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionService {
  public static Map<String, Object>  getSessionModel(Integer userId) {
    Map<String, Object> modelo = new HashMap<>();
    List<Map<String, String>> links = new ArrayList<>();
    boolean sesionIniciada = false;
    if(userId != null) {
      sesionIniciada = true;
      Usuario usuario = RepositorioUsuarios.instancia().buscar(userId);
      Map<String, String> usuarioSesion = new HashMap<String, String>();
      usuarioSesion.put("name", usuario.getUsuario());
      usuarioSesion.put("rol", usuario.getRol() );
      modelo.put("usuarioSesion", usuarioSesion);
      links = UsuarioService.getlinksByUserRol(usuario);
    }
    modelo.put("links", links);
    modelo.put("sesionIniciada", sesionIniciada);
    return modelo;
  }
}
