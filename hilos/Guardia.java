package hilos;

import recursos.PuestoAeroportuario;

public class Guardia implements Runnable {
    private PuestoAeroportuario puesto;


    public Guardia(PuestoAeroportuario puesto){
        this.puesto=puesto;
    }


    @Override
    public void run() {
        try {
            while(true){
                this.puesto.atenderPasajero();
                Thread.sleep(4000);
            }           
        } catch (Exception e) {

        }        
    }
    
}
