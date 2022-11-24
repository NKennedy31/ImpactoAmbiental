import Clave.ClaveInseguraException;
import impacto_ambiental.Usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


public class UsuarioTest {
  @Test
  public void crearUsuarioConClaveInvalidaRompe() {
    ClaveInseguraException exception = Assertions.assertThrows(ClaveInseguraException.class, () -> {
      String clave = "a2b456";
      new Usuario("manuel", clave, "comun");
    });
    assertEquals("La clave ingresada a2b456 no es segura", exception.getMessage());
  }

  @Test
  public void crearUsuarioConClaveInvalidaPorSerNula() {
    ClaveInseguraException exception = Assertions.assertThrows(ClaveInseguraException.class, () -> {
      String clave = null;
      new Usuario("manuel", clave, "comun");
    });
    assertEquals("La clave ingresada null no es segura", exception.getMessage());
  }

  @Test
  public void crearUsuarioConClaveInvalidaPorSerVacia() {

    ClaveInseguraException exception = Assertions.assertThrows(ClaveInseguraException.class, () -> {
      String clave = "";
      new Usuario("manuel", clave, "comun");
    });
    assertEquals("La clave ingresada  no es segura", exception.getMessage());
  }

  @Test
  public void crearUsuarioConClaveInvalidaPorSerDeUsoComun() {

    ClaveInseguraException exception = Assertions.assertThrows(ClaveInseguraException.class, () -> {
      String clave = "12345678";
      new Usuario("manuel", clave, "comun");
    });
    assertEquals("La clave ingresada 12345678 no es segura", exception.getMessage());
  }

  @Test
  public void crearUsuarioConClavePasswordPorSerDeUsoComun() {
    ClaveInseguraException exception = Assertions.assertThrows(ClaveInseguraException.class, () -> {
      String clave = "password";
      new Usuario("manuel", clave, "comun");
    });
    assertEquals("La clave ingresada password no es segura", exception.getMessage());
  }

  @Test
  public void crearUsuarioAdminConClaveHasheada() {
    String clave = "a2b4c6d4f";
    Usuario admin = new Usuario("laura", clave, "admin");
    assertEquals(admin.getClave(), "bd76eba5f971e54a4651afd62da20516");
  }


}
