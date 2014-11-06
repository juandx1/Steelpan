package mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Usuario on 09/10/2014.
 */
public class Steelpan implements Serializable{

private ArrayList<Cancion> canciones;

    public Steelpan()
    {
     canciones= new ArrayList<Cancion>();
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }
}

