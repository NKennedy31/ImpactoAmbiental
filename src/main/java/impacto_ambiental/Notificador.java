package impacto_ambiental;

import Repositorios.RepositorioOrganizaciones;

public class Notificador {

  private RepositorioOrganizaciones repositorioOrganizaciones;
  public Notificador(RepositorioOrganizaciones repositorioOrganizaciones){
    this.repositorioOrganizaciones = repositorioOrganizaciones;
  }

  public void enviarNotificaciones(String link){
    repositorioOrganizaciones.instancia().listar().forEach(organizacion -> organizacion.recibir(link));

  }
}
