package Repositorios;

import java.util.List;

import impacto_ambiental.Miembro;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import impacto_ambiental.Organizacion;
import impacto_ambiental.Usuario;

public class RepositorioUsuarios implements WithGlobalEntityManager{
  private static RepositorioUsuarios instancia;
  
  private RepositorioUsuarios() {
  }
  
  public static RepositorioUsuarios instancia() {
    if(instancia == null) {
      instancia = new RepositorioUsuarios(); 
    }
    return instancia;
  }
  
  public void agregar(Usuario usuario) {
    entityManager().persist(usuario);
  }

  public List<Usuario> listar() {
    return entityManager()
        .createQuery("from Usuario", Usuario.class)
        .getResultList();
  }
  
  public Usuario buscar(int id){
    return entityManager().find(Usuario.class, id);
  }

  public Miembro miembroDelUsuario(Usuario usuario) { return usuario.getMiembro(); }

  public Usuario buscarPorUsuarioYContrasenia(String username, String password) {
    return listar().stream()
        .filter(u -> u.getClave().equals(password) && u.getUsuario().equals(username)).findFirst().get();
  }

}
