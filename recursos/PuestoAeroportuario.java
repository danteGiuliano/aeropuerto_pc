package recursos;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

import hilos.Pasajero;

public class PuestoAeroportuario {
    /**
     * Clase que modela los puestos aaeroportuarios
     * 
     */

    private static int CAPACIDAD_MAXIMA;
    private int capacidadActual;
    private Semaphore cola;
    private Semaphore mutex;
    private Exchanger<String> boleto;

    public PuestoAeroportuario(int CAPACIDAD_MAXIMA) {
        this.CAPACIDAD_MAXIMA = CAPACIDAD_MAXIMA;
        this.capacidadActual = 0;
        this.cola = new Semaphore(0, true);
        this.mutex = new Semaphore(1);
    }

    public boolean esperaFila(Pasajero unPasajero) throws Exception {
        this.mutex.acquire();
        if (this.capacidadActual + 1 <= CAPACIDAD_MAXIMA) {
            capacidadActual++;
            this.mutex.release();
            cola.acquire();
            unPasajero.obtenerTicket(boleto.exchange(unPasajero.getPuesto()+""));
            return true;
        } else {
            this.mutex.release();
            return false;
        }
    }


    //Aca usar el exchange entre pasajero y guardia
    public void atenderPasajero(Terminal uTerminal) throws Exception {
        this.mutex.acquire();
        if(this.capacidadActual>0){
            boleto.exchange(uTerminal.salidaDisponible());
            this.cola.release(1);
            this.capacidadActual--;
        }
        this.mutex.release();
    }
}
