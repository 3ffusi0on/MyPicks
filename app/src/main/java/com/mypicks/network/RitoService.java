package com.mypicks.network;

import com.mypicks.model.ChampionListDto;
import com.mypicks.model.RealmDto;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Interface needed for the Retrofit mapping for API routes.
 * This API is used for non static data such as statistics per champion
 * and game information.
 */
public interface RitoService {
  @GET("/api/lol/static-data/{region}/v1.2/realm")
  Call<RealmDto> dataDragonVersions(@Path("region") String version);

  @GET("/api/lol/static-data/{region}/v1.2/champion")
  Call<ChampionListDto> championList(@Path("region") String version);

}
