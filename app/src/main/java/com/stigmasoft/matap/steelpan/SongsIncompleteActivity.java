package com.stigmasoft.matap.steelpan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import mundo.Cancion;
import mundo.FragmentoCancion;
import mundo.Steelpan;

/**
 * Created by Juan Manuel on 09/10/2014.
 */
public class SongsIncompleteActivity extends Fragment {

    private ArrayList<String[]> myStringArray1;

    public static CustomAdapter adapter;

    private Steelpan steelpan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.songs_activity, container, false);

        final ListView listView = (ListView) root.findViewById(R.id.listViewSongs);

        myStringArray1 = new ArrayList<String[]>();

        steelpan = AdminBD.getSteelpan();
        ArrayList<Cancion> canciones = steelpan.getCanciones();
        for (int i = 0; i < canciones.size(); i++) {
            Cancion cancion = canciones.get(i);
            ArrayList<FragmentoCancion> fragmentos = cancion.getFragmentos();
            for (int j = 0; j < fragmentos.size(); j++) {
                FragmentoCancion fragmento = fragmentos.get(j);
                if (!fragmento.isCompletado()) {
                    int id = getResources().getIdentifier(cancion.getImagen(), "drawable", getActivity().getPackageName());
                    myStringArray1.add(new String[]{cancion.getNombreCancion() + fragmento.getId_fragmento(), cancion.getArtista(), id+""});
                }
            }
        }

        adapter = new CustomAdapter(getActivity(), myStringArray1);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);
            }

        });

        return root;
    }
}
