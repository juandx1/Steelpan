package mundo;

import java.io.Serializable;

/**
 * Created by Usuario on 09/10/2014.
 */
public class Nota implements Serializable{

    private String id_cancion;
    private String id_fragmento;
    private String id_nota;
    private long tiempoDeEspera;
    private String nota;

    public Nota(){

    }

    public String getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(String id_cancion) {
        this.id_cancion = id_cancion;
    }

    public String getId_fragmento() {
        return id_fragmento;
    }

    public void setId_fragmento(String id_fragmento) {
        this.id_fragmento = id_fragmento;
    }

    public String getId_nota() {
        return id_nota;
    }

    public void setId_nota(String id_nota) {
        this.id_nota = id_nota;
    }

    public long getTiempoDeEspera() {
        return tiempoDeEspera;
    }

    public String getNota() {
        return nota;
    }

    public void setTiempoDeEspera(long tiempoDeEspera) {
        this.tiempoDeEspera = tiempoDeEspera;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
