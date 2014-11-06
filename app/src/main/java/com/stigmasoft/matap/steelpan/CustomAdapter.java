package com.stigmasoft.matap.steelpan;


import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juan Manuel on 28/10/2014.
 */
public class CustomAdapter extends ArrayAdapter<String[]> {

    private final Context context;
    private final ArrayList<String[]> values;

    public CustomAdapter(Context context, ArrayList<String[]> objects) {
        super(context, R.layout.row_list_view, objects);
        this.context = context;
        this.values = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_list_view, parent, false);

        TextView textViewSongName = (TextView) row.findViewById(R.id.textViewSongName);
        TextView textViewSongArtist = (TextView) row.findViewById(R.id.textViewSongArtist);

        textViewSongName.setText(values.get(position)[0]);
        textViewSongArtist.setText(values.get(position)[1]);

        ImageView imageViewSong = (ImageView) row.findViewById(R.id.imageViewSong);
        imageViewSong.setImageDrawable(context.getResources().getDrawable(Integer.parseInt(values.get(position)[2])));

        return row;

    }
}
