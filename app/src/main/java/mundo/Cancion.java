package mundo;

import java.util.ArrayList;

/**
 * Created by Usuario on 09/10/2014.
 */
public class Cancion {

   private String nombreCancion;
   private String artista;
   private ArrayList<FragmentoCancion> fragmentos;



    public Cancion()
    {
        fragmentos=new ArrayList<FragmentoCancion>();
    }




    public String getArtista() {
        return artista;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }
}
