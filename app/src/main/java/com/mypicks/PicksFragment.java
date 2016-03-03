package com.mypicks;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.software.shell.fab.ActionButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicksFragment extends Fragment {
  @Bind(R.id.listView) ListView mChampionList;
  @Bind(R.id.new_champ_bt) ActionButton mNewChampBt;

  public PicksFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view =  inflater.inflate(R.layout.fragment_picks, container, false);

    ButterKnife.bind(this, view);

    mChampionList.setAdapter(new ListViewChampionAdapter(this.getActivity(), getListData()));
    mChampionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view,
          int position, long id) {
        Toast.makeText(getActivity().getApplicationContext(),
            "Click ListItem Number " + position, Toast.LENGTH_LONG)
            .show();
      }
    });

    mNewChampBt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ChampionListFragment fragment = new ChampionListFragment();
        fragmentTransaction.add(R.id.main_container, fragment);
        fragmentTransaction.commit();
      }
    });
    return view;
  }

  private ArrayList<ChampionItem> getListData() {
    ArrayList<ChampionItem> results = new ArrayList<>();
    ChampionItem newsData = new ChampionItem();
    newsData.setmImage("img");
    newsData.setmName("name");
    newsData.setmTitle("title");
    results.add(newsData);
    results.add(new ChampionItem());
    results.add(new ChampionItem());
    results.add(new ChampionItem());
    results.add(new ChampionItem());
    results.add(new ChampionItem());
    return results;
  }

  private class ChampionItem {
    private String mName;
    private String mTitle;
    private String mImage;

    public String getmImage() {
      return mImage;
    }

    public void setmImage(String mImage) {
      this.mImage = mImage;
    }

    public String getmName() {
      return mName;
    }

    public void setmName(String mName) {
      this.mName = mName;
    }

    public String getmTitle() {
      return mTitle;
    }

    public void setmTitle(String mTitle) {
      this.mTitle = mTitle;
    }
  }

  private class ListViewChampionAdapter extends BaseAdapter {
    private ArrayList<ChampionItem> mChampions;
    private LayoutInflater mLayoutInflater;


    public ListViewChampionAdapter(Context context, ArrayList<ChampionItem> listData) {
      mChampions = listData;
      mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
      return mChampions.size();
    }

    @Override
    public Object getItem(int position) {
      return mChampions.get(position);
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder;
      if (convertView == null) {
        convertView = mLayoutInflater.inflate(R.layout.list_item, null);
        holder = new ViewHolder();
        holder.mImage = (ImageView) convertView.findViewById(R.id.champion_icon);
        holder.mName = (TextView) convertView.findViewById(R.id.champion_name);
        holder.mTitle = (TextView) convertView.findViewById(R.id.champion_title);
        convertView.setTag(holder);
      } else {
        holder = (ViewHolder) convertView.getTag();
      }

      holder.mImage.setImageResource(R.drawable.vayne);
      holder.mName.setText("Name test 1");
      holder.mTitle.setText("test 2");
      return convertView;
    }
  }

  static class ViewHolder {
    ImageView mImage;
    TextView  mName;
    TextView  mTitle;
  }

}
