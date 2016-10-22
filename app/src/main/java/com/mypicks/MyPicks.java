package com.mypicks;

import android.app.Application;

import com.mypicks.network.RitoService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static com.mypicks.Constants.RITO_API_BASE_URI;

/**
 * Created by Pierre-Alain SIMON on 03/03/2016.
 * Vivoka
 * simon_p@vivoka.com
 */
public class MyPicks extends Application {

  /**
   * Create Retrofit builder and return service to defined routes
   * @return ApiRoute
   */
  public RitoService getApiService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RITO_API_BASE_URI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    return retrofit.create(RitoService.class);
  }
}
