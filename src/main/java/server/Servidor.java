package server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Servidor {
  public static void main(String[] args) {
    Spark.port(9000);
    Router.init();
    new Bootstrap().init();
    DebugScreen.enableDebugScreen(); //Esto no deberia estar en produccion. Si ocurre un error nos muestra en pantalla.
  }
}