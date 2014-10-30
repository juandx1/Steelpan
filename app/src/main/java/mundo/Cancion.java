package mundo;

import java.util.ArrayList;

/**
 * Created by Usuario on 09/10/2014.
 */
public class Cancion {

    private String nombreCancion;
    private String artista;
    private String imagen;
    private ArrayList<FragmentoCancion> fragmentos;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Cancion() {
        fragmentos = new ArrayList<FragmentoCancion>();
    }

    public String getArtista() {
        return artista;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public ArrayList<FragmentoCancion> getFragmentos() {
        return fragmentos;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setFragmentos(ArrayList<FragmentoCancion> fragmentos) {
        this.fragmentos = fragmentos;
    }
}
