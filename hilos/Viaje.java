package hilos;

import java.util.ArrayList;

import recursos.EstacionTren;
import recursos.Terminal;

public class Viaje implements Runnable {

    private EstacionTren estacion;
    private ArrayList<Terminal> terminales;

    public Viaje(EstacionTren estacion, ArrayList terminales) {
        this.estacion = estacion;
        this.terminales = terminales;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (estacion) {
                    terminales.forEach(t -> {
                        try {
                            t.parada();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
