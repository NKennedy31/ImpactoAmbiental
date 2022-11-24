package api.Ubicacion;

import Trayecto.Ubicacion;
import api.Ubicacion.Exceptions.ErrorException;
import api.Ubicacion.Exceptions.NoAutorizadoException;
import api.Ubicacion.Entities.Distancia;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoUbicacion implements ServicioUbicacion {
  private static final String urlAPI = "https://ddstpa.com.ar/api/";
  private static final String token = "RF+rCtDgl3obFNdOAA8K+E4oOxpkQxGVdHERoD1+7Eo=";
  private Retrofit retrofit;

  OkHttpClient client = new OkHttpClient.Builder()
      .addInterceptor(new Interceptor() {
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
      Request newRequest  = chain.request().newBuilder()
          .addHeader("Authorization", "Bearer "+token)
          .build();
      return chain.proceed(newRequest);
    }
  }).build();

  public ServicioGeoUbicacion() {
    this.retrofit = new Retrofit.Builder()
        .client(client)
          .baseUrl(urlAPI)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
  }


  public Distancia distancia(Ubicacion inicio, Ubicacion fin) throws IOException {
    GeoDdsService geoDds = this.retrofit.create(GeoDdsService.class);
    Call<Distancia> requestDistancia = geoDds.distancia(inicio.getLocalidad(), inicio.getCalle(), inicio.getAltura(),
        fin.getLocalidad(), fin.getCalle(), fin.getAltura());
    Response<Distancia> responseDistancia = requestDistancia.execute();

    if (responseDistancia.code() == 401) {
      throw new NoAutorizadoException("No tiene permisos para ingresar");
    }
    if (responseDistancia.code() != 200) {
      throw new ErrorException("Ocurrió un error, estado " + responseDistancia.code());
    }
    return responseDistancia.body();
  }
}