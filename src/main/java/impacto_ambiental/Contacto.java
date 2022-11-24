package impacto_ambiental;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import Repositorios.PersistentEntity;

@Entity
public class Contacto extends PersistentEntity {
  @Column
  String telefono;
  @Column
  String email;

  public Contacto() {
  }

  public Contacto(String telefono, String email){
    this.telefono = telefono;
    this.email = email;
  }

  public String getTelefono() {
    return telefono;
  }

  public String getEmail() {
    return email;
  }
}