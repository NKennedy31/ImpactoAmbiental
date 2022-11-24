package Consumo;

//TODO: revisar en el apunte de Java
public enum Periodo  {
  MENSUAL{
  public String getValor() {
    return "Mensual";
  }
  }
  ,
  ANUAL {
  public String getValor() {
    return "Anual";
  }
  }
}