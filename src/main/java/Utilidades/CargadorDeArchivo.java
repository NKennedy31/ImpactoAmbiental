package Utilidades;

import Clave.CreacionDeRepositorioDeClavesComunesException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class CargadorDeArchivo {

  public static List<String> procesar(String archivo) {
    try{
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    File file = new File(classLoader.getResource(archivo).getFile());
     return Files.readAllLines(file.toPath());
    } catch(IOException e) {
      String message = "Hubo un problema en cargar el archivo %s";
      throw new CreacionDeRepositorioDeClavesComunesException(String.format(message,archivo));
    }
  }
}
