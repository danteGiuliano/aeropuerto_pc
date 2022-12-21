package hilos;

import recursos.Aeropuerto;

public class Pasajero implements Runnable {

    private Aeropuerto aeropuerto;
    private int puesto=-1;

    @Override
    public void run() {
        try {
            aeropuerto.entradaAeropuerto(this);
            aeropuerto.puestoInforme(this);
            aeropuerto.estacionTren(this);
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        
    }
    public void asignarPuesto(int numeroPuesto){
        this.puesto=numeroPuesto;
    }
    public int getPuesto(){
        return puesto;
    }

}
