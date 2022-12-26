package hilos;

import java.util.ArrayList;

import recursos.EstacionTren;
import recursos.Terminal;

public class Viaje implements Runnable {

    private EstacionTren estacion;
    private ArrayList<Terminal> terminales;

    public Viaje(EstacionTren estacion, ArrayList<Terminal> terminales) {
        this.estacion = estacion;
        this.terminales = terminales;
    }

    @Override
    public void run() {
        try {
            System.out.println("VIAJE :" + Thread.currentThread().getName() + " INICIADO");
            estacion.salidaTren();
            terminales.forEach(t -> {
                try {
                    System.out.println("LLEGADA A PARADA " + t.getID() + " Espera pasajeros");
                    t.llegadaTren();
                    t.parada();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            estacion.llegadaTren();
            System.out.println("VIAJE :" + Thread.currentThread().getName() + " TERMINADO");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
