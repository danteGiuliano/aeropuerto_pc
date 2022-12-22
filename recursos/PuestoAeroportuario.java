package recursos;

import java.util.concurrent.Semaphore;

public class PuestoAeroportuario {
    /**
     * Clase que modela los puestos aaeroportuarios
     * 
     */

    private static int CAPACIDAD_MAXIMA;
    private int capacidadActual;
    private Semaphore cola;
    private Semaphore mutex;

    public PuestoAeroportuario(int CAPACIDAD_MAXIMA) {
        this.CAPACIDAD_MAXIMA = CAPACIDAD_MAXIMA;
        this.capacidadActual = 0;
        this.cola = new Semaphore(0, true);
        this.mutex = new Semaphore(1);
    }

    public boolean esperaFila() throws Exception {
        this.mutex.acquire();
        if (this.capacidadActual + 1 <= CAPACIDAD_MAXIMA) {
            capacidadActual++;
            this.mutex.release();
            cola.acquire();
            return true;
        } else {
            this.mutex.release();
            return false;
        }
    }

    public void atenderPasajero() throws Exception {
        this.mutex.acquire();
        if(this.capacidadActual>0){
            this.cola.release(1);
            this.capacidadActual--;
        }
        this.mutex.release();
    }
}
