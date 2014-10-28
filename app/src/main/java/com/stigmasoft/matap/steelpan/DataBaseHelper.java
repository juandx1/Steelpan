package com.stigmasoft.matap.steelpan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Usuario on 09/10/2014.
* creo la base de datos
 */

public class DataBaseHelper extends SQLiteOpenHelper {

private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BD_Steelpan";

    // Contacts table canciones
    private static final String TABLE_CANCIONES = "canciones";

    // Contacts table notas
    private static final String TABLE_NOTAS = "notas";


    // Contacts table fragmentos canciones
    private static final String TABLE_FRAG_CANCIONES = "fragmentoCanciones";


    // Contacts Table Columns names notas
    private static final String KEY_ID_N = "numeroDeSecuencia";
    private static final String KEY_NOTA = "nota";
    private static final String KEY_TIEMPO_NOTA = "tiempoNota";
    private static final String KEY_TIEMPO_ESPERA= "tiempoEspera";



    // Contacts Table Columns names canciones
    private static final String KEY_ID = "idCancion";
    private static final String KEY_NAME = "nombre";
    private static final String KEY_ARTISTA = "artista";


    // Contacts Table Columns fragmento canciones
    private static final String KEY_ID_F= "idFragmento";
    private static final String KEY_DIFICULTAD= "dificultad";
    private static final String KEY_FRAGMENTO_C_ESCOGIDO = "fragmentoCancionEscogido";
    private static final String KEY_COMPLETO = "completo";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CANCIONES_TABLE = "CREATE TABLE " + TABLE_CANCIONES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ARTISTA + " TEXT" + ")";
        db.execSQL(CREATE_CANCIONES_TABLE);


        String CREATE_NOTA_TABLE = "CREATE TABLE " + TABLE_NOTAS + "("
                + KEY_ID_N + " INTEGER PRIMARY KEY," + KEY_NOTA + " TEXT,"
                + KEY_TIEMPO_NOTA + " TEXT,"
                + KEY_TIEMPO_ESPERA + " TEXT" + ")";
        db.execSQL(CREATE_NOTA_TABLE);

        String CREATE_FRAGMENTO_TABLE = "CREATE TABLE " + TABLE_FRAG_CANCIONES + "("
                + KEY_ID_F + " INTEGER PRIMARY KEY," + KEY_DIFICULTAD + " TEXT,"
                + KEY_FRAGMENTO_C_ESCOGIDO + " TEXT,"
                + KEY_COMPLETO + " TEXT" + ")";
        db.execSQL(CREATE_FRAGMENTO_TABLE);




    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CANCIONES);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTAS);

        // Create tables again
        onCreate(db);
    }



}
