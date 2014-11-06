package com.stigmasoft.matap.steelpan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

import mundo.Cancion;
import mundo.FragmentoCancion;
import mundo.Nota;
import mundo.Steelpan;

/**
 * Created by Juan Manuel on 28/10/2014.
 */
public class AdminBD {

    private static Steelpan steelpan;

    private static SQLiteDatabase database;

    private static DataBaseHelper helper;

    private String[] columnsCanciones = {DataBaseHelper.KEY_ID, DataBaseHelper.KEY_NAME, DataBaseHelper.KEY_ARTISTA, DataBaseHelper.KEY_IMAGEN};

    private String[] columnsFragmentoCancion = {DataBaseHelper.KEY_ID_F, DataBaseHelper.KEY_DIFICULTAD, DataBaseHelper.KEY_COMPLETO, DataBaseHelper.KEY_DURACION, DataBaseHelper.KEY_ID};

    private String[] columnsNotas = {DataBaseHelper.KEY_ID_N, DataBaseHelper.KEY_NOTA, DataBaseHelper.KEY_TIEMPO_ESPERA, DataBaseHelper.KEY_ID_F, DataBaseHelper.KEY_ID};

    public AdminBD(Context context) {
        try {
            helper = new DataBaseHelper(context);
            open();
            steelpan = new Steelpan();
            steelpan.setCanciones(createSongs());
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

    private static void close() {
        helper.close();
    }

    private ArrayList<Cancion> createSongs() {
        ArrayList<Cancion> canciones = new ArrayList<Cancion>();
        Cursor cursor = database.query(DataBaseHelper.TABLE_CANCIONES, columnsCanciones, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Cancion cancion = cursorToCancion(cursor);
            canciones.add(cancion);
            cursor.moveToNext();
        }
        cursor.close();
        return canciones;
    }

    private Cancion cursorToCancion(Cursor cursor) {
        Cancion cancion = new Cancion();
        String nombre = cursor.getString(1);
        cancion.setNombreCancion(nombre);
        cancion.setArtista(cursor.getString(2));
        cancion.setImagen(cursor.getString(3));
        ArrayList<FragmentoCancion> fragmentoCanciones = new ArrayList<FragmentoCancion>();
        cancion.setFragmentos(fragmentosCancion(cursor.getString(0), fragmentoCanciones));
        return cancion;
    }

    private ArrayList<FragmentoCancion> fragmentosCancion(String cancion, ArrayList<FragmentoCancion> fragmentos) {
        ArrayList<FragmentoCancion> fragmentosCancion = new ArrayList<FragmentoCancion>();
        Cursor cursor = database.query(DataBaseHelper.TABLE_FRAG_CANCIONES, columnsFragmentoCancion, DataBaseHelper.KEY_ID + "=?", new String[]{cancion}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FragmentoCancion fragmento = cursorToFragmento(cursor);
            fragmentosCancion.add(fragmento);
            cursor.moveToNext();
        }
        return fragmentosCancion;
    }

    private FragmentoCancion cursorToFragmento(Cursor cursor) {
        FragmentoCancion fragmento = new FragmentoCancion();
        fragmento.setId_fragmento(cursor.getString(0));
        fragmento.setDificultad(Integer.parseInt(cursor.getString(1)));
        fragmento.setCompletado(cursor.getString(2).contains("true"));
        fragmento.setDuracion(cursor.getInt(3));
        fragmento.setListaNotas(notasFragmento(cursor.getString(0),cursor.getString(4)));
        return fragmento;
    }

    private ArrayList<Nota> notasFragmento(String idFragemto, String nombreCancion) {
        ArrayList<Nota> notasFragmento = new ArrayList<Nota>();
        Cursor cursor = database.query(DataBaseHelper.TABLE_NOTAS, columnsNotas, DataBaseHelper.KEY_ID + "=? AND " + DataBaseHelper.KEY_ID_F + "=?", new String[]{nombreCancion, idFragemto}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Nota nota = cursorToNota(cursor);
            notasFragmento.add(nota);
            cursor.moveToNext();
        }
        return notasFragmento;

    }

    private Nota cursorToNota(Cursor cursor) {
        Nota nota = new Nota();
        nota.setId_nota(cursor.getString(0));
        nota.setNota(cursor.getString(1));
        nota.setTiempoDeEspera(cursor.getInt(2));
        nota.setId_fragmento(cursor.getString(3));
        nota.setId_cancion(cursor.getString(4));
        return nota;
    }

    public static Steelpan getSteelpan() {
        return steelpan;
    }
}
