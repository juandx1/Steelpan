package com.stigmasoft.matap.steelpan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Juan Manuel on 09/10/2014.
 */
public class SongsCompleteActivity extends Fragment {

    private ArrayList<String> myStringArray1 ;

    public static ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.songs_activity, container, false);

        ListView listView = (ListView) root.findViewById(R.id.listViewSongs);

        myStringArray1 =  new ArrayList<String>();
        myStringArray1.add("sdas");
        myStringArray1.add("asdas");
        myStringArray1.add("iudj");

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.row_list_view, R.id.textViewSongName, myStringArray1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

            }

        });

        return root;

    }
}
