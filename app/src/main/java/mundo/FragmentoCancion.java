package mundo;

import java.util.ArrayList;

/**
 * Created by Usuario on 09/10/2014.
 */
public class FragmentoCancion {

    private String id_fragmento;
    private String id_cancion;
    private int dificultad;
    private boolean completado;
    private int duracion;

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    private ArrayList<Nota> listaNotas;

    public FragmentoCancion() {
        listaNotas = new ArrayList<Nota>();
    }

    public String getId_fragmento() {
        return id_fragmento;
    }

    public void setId_fragmento(String id_fragmento) {
        this.id_fragmento = id_fragmento;
    }

    public String getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(String id_cancion) {
        this.id_cancion = id_cancion;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public int getDificultad() {
        return dificultad;
    }

    public ArrayList<Nota> getListaNotas() {
        return listaNotas;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public void setListaNotas(ArrayList<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }
}
