package com.mypicks;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChampionListFragment extends Fragment {
  @Bind(R.id.champion_table) TableLayout mTable;

  public ChampionListFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_champion_list, container, false);

    ButterKnife.bind(this, view);
    fillTable();
    return view;
  }

  private void fillTable() {
    int rowCount = 10;
    for (int i = 0; i < rowCount; i++) {
      fillRow(mTable, i);
    }
  }

  public void fillRow(TableLayout table, final int noRow) {
    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View fullRow = inflater.inflate(R.layout.table_row, null, false);
    ImageView img = (ImageView) fullRow.findViewById(R.id.imageView2);
    img.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getActivity().getApplicationContext(),
            "Click Line Number " + noRow, Toast.LENGTH_LONG)
            .show();
      }
    });
    table.addView(fullRow);
  }

}
