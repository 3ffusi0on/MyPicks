package com.mypicks.views.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mypicks.MyPicks;
import com.mypicks.R;
import com.mypicks.controller.MyPicksDatabaseHelper;
import com.mypicks.model.api.ChampionDto;
import com.mypicks.model.api.ChampionListDto;
import com.mypicks.model.database.Champion;
import com.mypicks.network.RitoService;
import com.mypicks.views.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

  private final static String TAG = "MainActivity";

  private ArrayList<Champion> mChampionList = new ArrayList<>();
  private PicksFragment mPicksFragment;
  private MapFragment mMapFragment;
  private ChampionListFragment mChampionListFragment;
  private String mRole;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mMapFragment = new MapFragment();
    getFragmentManager()
        .beginTransaction()
        .add(R.id.main_container, mMapFragment)
        .disallowAddToBackStack()
        .commit();

    fetchData();
  }

  public void displayPicksFragment() {
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    if (mPicksFragment == null) {
      mPicksFragment = PicksFragment.newInstance(mRole);
    } else {
      mPicksFragment.setRole(mRole);
    }
    fragmentTransaction.replace(R.id.main_container, mPicksFragment);
    fragmentTransaction.addToBackStack("tagPicks");
    fragmentTransaction.commit();
  }

  public void displayChampionListFragment(View v) {
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    if (mChampionListFragment == null) {
      mChampionListFragment = ChampionListFragment.newInstance(mRole);
    } else {
      mChampionListFragment.setRole(mRole);
    }
    fragmentTransaction.replace(R.id.main_container, mChampionListFragment);
    fragmentTransaction.addToBackStack("tagChamp");
    fragmentTransaction.commit();
  }

  public void displayMapFragment() {
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    fragmentTransaction.replace(R.id.main_container, mMapFragment);
    fragmentTransaction.addToBackStack("tagChamp");
    fragmentTransaction.commit();
  }

  public void updateChampionSelection(View v) {
    ArrayList<Integer> list = mChampionListFragment.getPicks();
    for (int c : list) {
      MyPicksDatabaseHelper databaseHelper = MyPicksDatabaseHelper.getInstance(getApplicationContext());
      databaseHelper.addPref(mChampionList.get(c), mRole);
    }

    displayPicksFragment();
  }

  private void startSettingsActivity() {
    startActivity(new Intent(this, SettingsActivity.class));
  }

  private Map<String, ChampionDto> fetchData() {
    RitoService mApiService = ((MyPicks) getApplication()).getApiService();

    //TODO region hardcoded
    Call<ChampionListDto> call = mApiService.championList("euw");

    call.enqueue(new Callback<ChampionListDto>() {
      @Override
      public void onResponse(Response<ChampionListDto> response, Retrofit retrofit) {
        ArrayList<ChampionDto> mChampionArrayList
            = new ArrayList<>(response.body().getData().values());

        // Get singleton instance of database
        MyPicksDatabaseHelper databaseHelper = MyPicksDatabaseHelper.getInstance(getApplicationContext());
        if (mChampionArrayList.size() > 0 ) {
          if (databaseHelper.getAllChampions().size() == 0) {
            databaseHelper.addAllChampions(mChampionArrayList);
          }
          mChampionList = databaseHelper.getAllChampions();
        }
     }

      @Override
      public void onFailure(Throwable t) {
        // Log error here since request failed
      }
    });
    return null;
  }

  @Override
  public void onBackPressed() {
    displayMapFragment();
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle presses on the action bar items
    switch (item.getItemId()) {
      case R.id.menu_app_home:
        displayMapFragment();
        return true;
      case R.id.menu_settings:
        startSettingsActivity();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  /**
   * Click event handler methods for MapFragment
   */

  public void mapClickTopLane(View v) {
    mRole = "TOP";
    displayPicksFragment();
  }

  public void mapClickMidLane(View v) {
    mRole = "MID";
    displayPicksFragment();
  }

  public void mapClickJungle(View v) {
    mRole = "JUNGLE";
    displayPicksFragment();
  }

  public void mapClickSupport(View v) {
    mRole = "SUPPORT";
    displayPicksFragment();
  }

  public void mapClickMarksman(View v) {
    mRole = "MARKSMAN";
    displayPicksFragment();
  }

  public void mapClickBan(View v) {
    mRole = "BAN";
    displayPicksFragment();
  }

  /**
   * Click event handler methods for PicksFragment
   */

  public void picksDeleteChamp(View v) {

  }
}
