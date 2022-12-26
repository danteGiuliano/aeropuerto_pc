package hilos;

import java.util.ArrayList;

import recursos.PuestoAeroportuario;
import recursos.Terminal;

public class Guardia implements Runnable {
    private PuestoAeroportuario puesto;
    private ArrayList<Terminal> terminales = new ArrayList<Terminal>();

    public Guardia(PuestoAeroportuario puesto,ArrayList<Terminal> terminales){
        this.puesto=puesto;
        this.terminales=terminales;
    }


    @Override
    public void run() {
        try {
            System.out.println("GUARDIA :"+Thread.currentThread().getName()+" INICIADO");
            Thread.sleep(100);
            while(true){
                this.puesto.atenderPasajero(terminales.get((int) (Math.random()*(terminales.size()-0.1))));
                Thread.sleep(1000);
            }           
        } catch (Exception e) {

        }        
    }
    
}
