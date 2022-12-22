package hilos;

import java.util.ArrayList;

import recursos.PuestoAeroportuario;
import recursos.Terminal;

public class Guardia implements Runnable {
    private PuestoAeroportuario puesto;
    private ArrayList<Terminal> terminales = new ArrayList<Terminal>();

    public Guardia(PuestoAeroportuario puesto,ArrayList terminales){
        this.puesto=puesto;
        this.terminales=terminales;
    }


    @Override
    public void run() {
        try {
            while(true){
                this.puesto.atenderPasajero(terminales.get(Math.round(terminales.size())));
                Thread.sleep(4000);
            }           
        } catch (Exception e) {

        }        
    }
    
}
