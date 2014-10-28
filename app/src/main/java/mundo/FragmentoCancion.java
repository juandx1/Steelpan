package mundo;

import java.util.ArrayList;

/**
 * Created by Usuario on 09/10/2014.
 */
public class FragmentoCancion {

    private int dificultad;
    private boolean completado;
    private ArrayList<Nota> listaNotas;


public FragmentoCancion()
{
    listaNotas= new ArrayList<Nota>();
}








    public int getDificultad() {
        return dificultad;
    }

    public boolean isCompletado() {
        return completado;
    }
}
