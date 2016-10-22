package com.mypicks.network;

import com.mypicks.model.api.ChampionListDto;
import com.mypicks.model.api.RealmDto;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

import static com.mypicks.Constants.RITO_API_KEY;

/**
 * Interface needed for the Retrofit mapping for API routes.
 * This API is used for non static data such as statistics per champion
 * and game information.
 */
public interface RitoService {
  @GET("/api/lol/static-data/{region}/v1.2/realm")
  Call<RealmDto> dataDragonVersions(@Path("region") String version);

  @GET("/api/lol/static-data/{region}/v1.2/champion?api_key=" + RITO_API_KEY)
  Call<ChampionListDto> championList(@Path("region") String version);

}
