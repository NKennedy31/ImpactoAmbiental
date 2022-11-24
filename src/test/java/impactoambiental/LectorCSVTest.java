package impactoambiental;

import Exceptions.SectorNoExisteException;

import api.Ubicacion.ServicioGeoUbicacion;
import api.Ubicacion.Entities.Distancia;
import com.opencsv.exceptions.CsvValidationException;
import impacto_ambiental.*;
import Trayecto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static impacto_ambiental.Clasificacion.*;
import static impacto_ambiental.TipoOrganizacion.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LectorCSVTest {
  Organizacion ixpandit = new Organizacion("Ixpandit", TipoOrganizacion.EMPRESA, new Ubicacion(1, "Roque Saenz Pena", 917), Clasificacion.EMPRESASECTORSECUNDARIO );

  Miembro melanie = new Miembro("Melanie", "Alarcon", TipoDocumento.DNI, "40190745", new ArrayList<>());
  
  LectorCsv unLector = new LectorCsv();
  
  @Test 
  public void elLectorCargaLasMediciones() throws CsvValidationException, IOException {
    unLector.cargarMedicion(ixpandit);
       
    assertTrue(ixpandit.getMediciones().size() > 6);    //Por ahora se cargan 7 mediciones mediante el archivo csv
  }
}
