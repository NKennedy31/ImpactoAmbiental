package impacto_ambiental;

import Clave.ClaveInseguraException;
import Clave.VerificadorDeClave;
import Repositorios.PersistentEntity;
import Utilidades.Hasheador;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.String.format;

@Entity
@Table(name="usuarios")
public class Usuario extends PersistentEntity {
  @Column
  private String usuario;
  @Column
  private String clave;
  
  @Column
  private String rol;

  @OneToOne
  private Miembro miembro;

  public Usuario() {
  }

  public Usuario(String usuario, String clave, String rol){
    guardar(usuario,clave, rol);
  }

  public void setMiembro(Miembro miembro) {
    this.miembro = miembro;
  }


  private void chequearCalidadDeContrasenia(String clave) {
    if (!VerificadorDeClave.getVerificadorDeClavesInstance().esClaveSegura(clave)) {
      throw new ClaveInseguraException(format("La clave ingresada %s no es segura", clave));
    }
  }

  public void guardar(String usuario, String clave, String rol) {
    chequearCalidadDeContrasenia(clave);
    this.clave = Hasheador.procesar(clave);
    this.usuario = usuario;
    this.rol = rol;
  }

  public String getUsuario() {
    return usuario;
  }

  public String getClave() {
    return clave;
  }

  public Miembro getMiembro() {return miembro; }

  public String getRol() {
    return rol;
  }

}