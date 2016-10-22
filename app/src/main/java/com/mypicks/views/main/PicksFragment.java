package com.mypicks.views.main;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.mypicks.R;
import com.mypicks.controller.MyPicksDatabaseHelper;
import com.mypicks.model.database.Champion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicksFragment extends Fragment {
  private static final String TAG = "PicksFragment";

  @Bind(R.id.listView) ListView mChampionList;

  private String mRole;
  private PicksAdapter mAdapter;

  public PicksFragment() {
    // Empty constructor
  }

  public static PicksFragment newInstance(String role) {
    PicksFragment fragment = new PicksFragment();
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
    View view =  inflater.inflate(R.layout.fragment_picks, container, false);

    ButterKnife.bind(this, view);

    mAdapter = new PicksAdapter(getActivity(), new ArrayList<Champion>());
    mChampionList.setAdapter(mAdapter);
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    MyPicksDatabaseHelper databaseHelper = MyPicksDatabaseHelper.getInstance(getActivity());
    ArrayList<Champion> mPicksList = databaseHelper.getRole(mRole);

    mAdapter.addAll(mPicksList);
  }

  public class PicksAdapter extends ArrayAdapter<Champion> {
    private ArrayList<Champion> mPicks;
    public PicksAdapter(Context context, ArrayList<Champion> picks) {
      super(context, 0, picks);
      mPicks = picks;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View rowView, ViewGroup parent) {
      final Champion champion = getItem(position);

      ViewHolder holder;
      if (rowView == null) {
        rowView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
        holder = new ViewHolder();
        holder.setImage((ImageView) rowView.findViewById(R.id.champion_icon));
        holder.setName((TextView) rowView.findViewById(R.id.champion_name));
        holder.setTitle((TextView) rowView.findViewById(R.id.champion_title));
        rowView.setTag(holder);
      } else {
        holder = (ViewHolder) rowView.getTag();
      }

      //TODO version
      String version = "6.6.1";
      Picasso.with(getActivity())
          .load("http://ddragon.leagueoflegends.com/cdn/" + version + "/img/champion/"
              + champion.getChampionKey() + ".png")
          .into(holder.getImage());
      holder.getName().setText(champion.getChampionName());
      holder.getTitle().setText(champion.getChampionTitle());

      final SwipeLayout mSwipeLayout = (SwipeLayout) rowView.findViewById(R.id.swipe_layout);
      final ImageView deleteBt = (ImageView) rowView.findViewById(R.id.delete_champBt);
      deleteBt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // delete from db
          MyPicksDatabaseHelper databaseHelper = MyPicksDatabaseHelper.getInstance(getActivity());
          databaseHelper.deleteChampionForRole(champion, mRole);

          //delete from ui
          mSwipeLayout.close();
          mPicks.remove(position);//you can delete your item here
          notifyDataSetChanged();

          //display Toast
          Toast.makeText(getActivity(), champion.getChampionName() +  " has been removed",
              Toast.LENGTH_SHORT).show();
        }
      });
      return rowView;
    }
  }

  static private class ViewHolder {
    private ImageView mImage;
    private TextView  mName;
    private TextView  mTitle;

    public ImageView getImage() {
      return mImage;
    }

    public void setImage(ImageView mImage) {
      this.mImage = mImage;
    }

    public TextView getName() {
      return mName;
    }

    public void setName(TextView mName) {
      this.mName = mName;
    }

    public TextView getTitle() {
      return mTitle;
    }

    public void setTitle(TextView mTitle) {
      this.mTitle = mTitle;
    }
  }

  public void setRole(String role) {
    this.mRole = role;
  }
}
