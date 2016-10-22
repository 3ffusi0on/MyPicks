package com.mypicks.views.main;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.mypicks.R;
import com.mypicks.controller.MyPicksDatabaseHelper;
import com.mypicks.model.database.Champion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO Display already selected champions
 * A simple {@link Fragment} subclass.
 */
public class ChampionListFragment extends Fragment {

  private final static String TAG = "ChampionListFragment";

  @Bind(R.id.champion_grid) GridView mGrid;

  private String mRole;
  private ArrayList<Integer> mPicksKeyList = new ArrayList<>();

  public ChampionListFragment() {
    // Required empty public constructor
  }

  public static ChampionListFragment newInstance(String role) {
    ChampionListFragment fragment = new ChampionListFragment();
    Bundle args = new Bundle();
    args.putString("role", role);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mRole = getArguments().getString("role", "");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_champion_list, container, false);

    ButterKnife.bind(this, view);
    MyPicksDatabaseHelper databaseHelper = MyPicksDatabaseHelper.getInstance(getActivity());
    ArrayList<Champion> mChampionList = databaseHelper.getAllChampions();

    mGrid.setAdapter(new ImageAdapter(getActivity(), mChampionList));

    mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View v,
          int position, long id) {
        if (!mPicksKeyList.contains(position)) {
          mPicksKeyList.add(position);
        } else {
          mPicksKeyList.remove(mPicksKeyList.indexOf(position));
        }
      }
    });
    return view;
  }

  public ArrayList<Integer> getPicks() {
    return mPicksKeyList;
  }

  /**
   * Adapter for the list of images in the layout
   * Holder is not necessary because the number of item in the list does not change
   */
  public class ImageAdapter extends ArrayAdapter<Champion> {

    public ImageAdapter(Context context, ArrayList<Champion> championList) {
      super(context, 0, championList);
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View imgView, ViewGroup parent) {
      Champion mChampion = getItem(position);
      ImageView imageView;

      if (imgView == null) {
        // if it's not recycled, initialize some attributes
        imageView = new ImageView(getActivity());
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(8, 8, 8, 8);
      } else {
        imageView = (ImageView) imgView;
      }

      //TODO version
      String version = "6.6.1";
      Picasso.with(getActivity())
          .load("http://ddragon.leagueoflegends.com/cdn/" + version + "/img/champion/"
              + mChampion.getChampionKey() + ".png")
          .into(imageView);
      return imageView;
    }
  }

  /**
   * Setter for the selected role
   * @param role
   */
  public void setRole(String role) {
    this.mRole = role;
  }
}
