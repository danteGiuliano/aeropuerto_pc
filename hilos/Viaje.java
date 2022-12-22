package hilos;

import recursos.EstacionTren;

public class Viaje implements Runnable {

    private EstacionTren estacion;

    public Viaje(EstacionTren estacion) {
        this.estacion = estacion;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (estacion) {
                    // Comienzo mi viaje

                }

            }
        } catch (Exception e) {
        }

    }

}
