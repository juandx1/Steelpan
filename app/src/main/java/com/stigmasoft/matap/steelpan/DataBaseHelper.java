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


    public String[] canciones = {
            "1-Mario-Nintendo-mario",
            "2-Zelda-Nintendo-zelda",
            "3-Buffalo Soldier-Bob Marley-buffalo_soldier"
    };

    public String[] fragmentos = {
            "1-2-true-1000-1",
            "2-3-false-2100-1",
            "1-1-false-4200-2",
            "2-2-true-4000-2",
            "1-3-false-1000-3"
    };

    public String[] notas = {
            "1-F-300-2-1",
            "2-A-600-2-1",
            "3-B-900-2-1",
            "4-F-1500-2-1",
            "5-A-1800-2-1",
            "6-B-2100-2-1",
            "7-F-2700-2-1",
            "8-A-3000-2-1",
            "9-B-3300-2-1",
            "10-E-3600-2-1",
            "11-D-3900-2-1",
            "1-E-540-1-2",
            "2-E-800-1-2",
            "3-E-1180-1-2",
            "4-C-1480-1-2",
            "5-E-1620-1-2",
            "6-G-1900-1-2",
    };

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CANCIONES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CANCIONES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ARTISTA + " TEXT,"
                + KEY_IMAGEN + " TEXT)";
        db.execSQL(CREATE_CANCIONES_TABLE);

        String CREATE_FRAGMENTO_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FRAG_CANCIONES + "("
                + KEY_ID_F + " INTEGER ,"
                + KEY_DIFICULTAD + " TEXT,"
                + KEY_COMPLETO + " TEXT,"
                + KEY_DURACION + " INTEGER,"
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

        String[] lineaCancion;
        for (int i = 0; i<canciones.length; i++) {
            lineaCancion = canciones[i].split("-");
            db.execSQL("INSERT INTO "+TABLE_CANCIONES+" VALUES('"+lineaCancion[0]+"','"+lineaCancion[1]+"','"+lineaCancion[2]+"','"+lineaCancion[3]+"')");
        }

        String[] lineaFragmento;
        for (int i = 0; i<fragmentos.length; i++){
            lineaFragmento = fragmentos[i].split("-");
            db.execSQL("INSERT INTO "+TABLE_FRAG_CANCIONES+" VALUES('"+lineaFragmento[0]+"','"+lineaFragmento[1]+"','"+lineaFragmento[2]+"','"
                    + lineaFragmento[3] +"','"+lineaFragmento[4]+"')");
        }

        String[] lineaNota;
        for (int i = 0; i<notas.length; i++){
            lineaNota = notas[i].split("-");
            db.execSQL("INSERT INTO "+TABLE_NOTAS+" VALUES('"+lineaNota[0]+"','"+lineaNota[1]+"','"+lineaNota[2]+"','"+lineaNota[3]+"','"
                    + lineaNota[4]+"')");
        }
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
