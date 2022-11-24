package impacto_ambiental;

import java.util.List;
import java.util.stream.Collectors;
import Consumo.Imputacion;
import Consumo.Medicion;


public class Reporter {
   //HUELLA DE CARBONO TOTAL POR SECTOR TERRITORIAL
    public double huellaCarbonoTotalSector(SectorTerritorial sector){  
      return sector.getOrganizaciones()
          .stream()
          .mapToDouble(unaOrganizacion -> huellaCarbonoTotalOrganizacion(unaOrganizacion))
          .sum();
    }
    
    //HUELLA DE CARBONO TOTAL POR ORGANIZACION
    public double huellaCarbonoTotalOrganizacion(Organizacion organizacion){   
      return armarListaImputacionesOrganizacion(organizacion)
          .stream().mapToDouble(unaImputacion -> organizacion.huellaDeCarbono(unaImputacion))
          .sum();
    }

    
    //COMPOSICIONES
    //COMPOSICION - SECTOR
    public List<Medicion> composicionHuellaSector(SectorTerritorial sector){
      return sector
          .getOrganizaciones()
          .stream()
          .flatMap(unaOrganizacion -> composicionHuellaOrganizacion(unaOrganizacion).stream())
          .collect(Collectors.toList());
    }
    
    
    //COMPOSICION - ORGANIZACION
    public List<Medicion> composicionHuellaOrganizacion(Organizacion organizacion){
      return organizacion
          .getMediciones()
          .stream()
          .map(unaMedicion->unaMedicion)
          .collect(Collectors.toList());
    }
}

  }

 /* Organizacion organizacionEvolucion = RepositorioOrganizaciones
      .instancia()
      .buscarPorNombre(nombreOrganizacion);

  List<Imputacion> lista = new ArrayList<Imputacion>();

  List<Imputacion> agregarImputaciones(Imputacion imputacionInicial, Imputacion imputacionFinal){
   lista.add(imputacionInicial.getAnio());
   lista.add(imputacionFinal.getAnio());

   return lista.stream().agregarImputaciones.contains(Imputacion);
  }

  List<double> listaHuellasDeCarbono = lista
      .stream()
      .mapToDouble(unaImputacion -> organizacionEvolucion.huellaDeCarbono(unaImputacion))
      .collect(Collectors.toList());

    //EVOLUCIONES
    //EVOLUCION - SECTOR TERRITORIAL
    public List<Imputacion> armarListaImputacionesOrganizacion(Organizacion organizacion){
      return organizacion.getMediciones().stream().map(unaMedicion -> unaMedicion.getImputacion())
          .sorted((o1, o2)->o1.getYear()
              .compareTo(o2.getYear())).
              collect(Collectors.toList());
    }
    
    public List<Imputacion> armarListaImputacionesSector(SectorTerritorial sector){
      return sector.getOrganizaciones()
          .stream()
          .flatMap(unaOrganizacion -> armarListaImputacionesOrganizacion(unaOrganizacion).stream())
          .sorted((o1, o2)->o1.getYear()
              .compareTo(o2.getYear())).
              collect(Collectors.toList());
    }
    
    public List<Double> evolucionHuellaSector(SectorTerritorial sector) { 
      return (List<Double>) armarListaImputacionesSector(sector)
          .stream()
          .mapToDouble(unaImputacion -> sector.huellaCarbonoTotal(unaImputacion));
          
    }
    
    public List<Double> evolucionHuellaOrganizacion(Organizacion organizacion) {
      return (List<Double>) armarListaImputacionesOrganizacion(organizacion).stream()
          .mapToDouble(unaImputacion -> organizacion.huellaDeCarbono(unaImputacion));   
}
}
