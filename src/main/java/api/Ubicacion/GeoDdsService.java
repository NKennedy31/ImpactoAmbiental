package api.Ubicacion;

import api.Ubicacion.Entities.Distancia;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoDdsService {
  @GET("distancia")
  Call<Distancia> distancia(@Query("localidadOrigenId") int localidadOId, @Query("calleOrigen") String calleOrigen,
                            @Query("alturaOrigen") int alturaOrigen, @Query("localidadDestinoId") int localidadDId,
                            @Query("calleDestino") String calleDestino, @Query("alturaDestino") int alturaDestino);
}
