package recursos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Terminal {
    private int salidaMinima;
    private int salidaMaxima;
    private int arriboPasajero;
    private String IDTERMINAL;

    private ReentrantLock mutex = new ReentrantLock();
    private Condition salidaTerminal = mutex.newCondition();
    private Condition llegadaTren = mutex.newCondition();

    private boolean llegada = false;

    public Terminal(int salidaMinima, int salidaMaxima, String IDTERMINAL) {
        this.salidaMinima = salidaMinima;
        this.salidaMaxima = salidaMaxima;
        arriboPasajero = 0;
        this.IDTERMINAL = IDTERMINAL;
    }

    /* Le da una salida disponible al guardia, para que le de al pasajero */
    public String salidaDisponible() {
        return this.IDTERMINAL + "-" + Math.round(Math.abs(salidaMinima - salidaMaxima)) + salidaMinima;
    }

    /* El Guardia avisa de un nuevo arribo de pasajero */
    public void nuevoArribo() {
        mutex.lock();
        arriboPasajero++;
        mutex.unlock();
    }

    public void decensoPasajero() throws Exception {
        this.mutex.lock();
        while (!llegada) {
            this.llegadaTren.await();
        }
        // Desiendo de la terminal
        this.arriboPasajero--;
        this.salidaTerminal.signal();
        this.mutex.unlock();

    }

    public void parada() throws Exception {
        this.mutex.lock();
        while (arriboPasajero > 0) {
            salidaTerminal.await();
        }
        this.mutex.unlock();

    }

    public void llegadaTren() throws Exception {
        this.mutex.lock();
        this.llegada = true;
        this.llegadaTren.signalAll();
        this.mutex.unlock();
    }
}

// Usar monitores con condicions para el tren