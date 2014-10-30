package com.stigmasoft.matap.steelpan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import mundo.FragmentoCancion;

/**
 * Created by Usuario on 09/10/2014.
* creo la base de datos
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "BD_Steelpan";

    // Contacts table canciones
    public static final String TABLE_CANCIONES = "canciones";

    // Contacts table notas
    public static final String TABLE_NOTAS = "notas";


    // Contacts table fragmentos canciones
    public static final String TABLE_FRAG_CANCIONES = "fragmentoCanciones";

    // Contacts Table Columns names canciones
    public static final String KEY_ID = "idCancion";
    public static final String KEY_NAME = "nombre";
    public static final String KEY_ARTISTA = "artista";
    public static final String KEY_IMAGEN = "imagen";


    // Contacts Table Columns fragmento canciones
    public static final String KEY_ID_F= "idFragmento";
    public static final String KEY_DIFICULTAD= "dificultad";
    public static final String KEY_COMPLETO = "completo";
    public static final String KEY_DURACION = "duracion";


    // Contacts Table Columns names notas
    public static final String KEY_ID_N = "numeroDeSecuencia";
    public static final String KEY_NOTA = "nota";
    public static final String KEY_TIEMPO_ESPERA= "tiempoEspera";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CANCIONES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CANCIONES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ARTISTA + " TEXT,"
                + KEY_IMAGEN + "TEXT)";
        db.execSQL(CREATE_CANCIONES_TABLE);

        String CREATE_FRAGMENTO_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FRAG_CANCIONES + "("
                + KEY_ID_F + " INTEGER ,"
                + KEY_DIFICULTAD + " TEXT,"
                + KEY_COMPLETO + " TEXT,"
                + KEY_DURACION + "INTEGER,"
                + KEY_ID + " INTEGER, "
                + "FOREIGN KEY("+KEY_ID+") REFERENCES "+TABLE_CANCIONES+" ("+KEY_ID+"),"
                + "PRIMARY KEY("+KEY_ID+","+KEY_ID_F+"))";
        db.execSQL(CREATE_FRAGMENTO_TABLE);

        String CREATE_NOTA_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTAS + "("
                + KEY_ID_N + " INTEGER ," + KEY_NOTA + " TEXT,"
                + KEY_TIEMPO_ESPERA + " TEXT, "
                + KEY_ID + " INTEGER, "
                + KEY_ID_F + " INTEGER, "
                + "FOREIGN KEY("+KEY_ID+","+KEY_ID_F+") REFERENCES "+TABLE_FRAG_CANCIONES+" ("+KEY_ID+","+KEY_ID_F+"),"
                + "PRIMARY KEY("+KEY_ID+","+KEY_ID_F+","+KEY_ID_N+"))";
        db.execSQL(CREATE_NOTA_TABLE);


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CANCIONES);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_FRAG_CANCIONES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTAS);

        // Create tables again
        onCreate(db);
    }


}
