package recursos;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import hilos.Pasajero;

public class EstacionTren {
    /**
     * Clase que modela la estacion de tren, desde su partida hasta la llegada a las
     * terminales
     */
    private CyclicBarrier tren;
    ArrayList<Terminal> terminales;
    private ReentrantLock mutex = new ReentrantLock();
    private Semaphore launch = new Semaphore(0);
    
    private int capacidadTren;

    public EstacionTren(int capacidadTren, ArrayList<Terminal> terminales) {
        this.terminales = terminales;
        this.capacidadTren=capacidadTren;
        this.tren = new CyclicBarrier(capacidadTren);
    }




    //------------------- PASAJERO_________________________________________


    public  Terminal tomarTren(Pasajero unPasajero) throws Exception {
        this.mutex.lock();
        //Terminal UT debo asegurar la exclusion. usar semaforos o monitor para UT
        Terminal uT=avisaParada(unPasajero.terminalAsociada());
        this.mutex.unlock();
        return uT;
    }
    public void esperaTren()throws Exception{
        if(this.tren.getNumberWaiting()+1==this.capacidadTren){this.launch.release();}
        this.tren.await();
    }

    public void salidaTren()throws Exception{
        this.launch.acquire();
        this.mutex.lock();
    }
    public void llegadaTren(){
        this.mutex.unlock();
    }

    // De esta manera incremento un nuevo arribo a una terminal X
    private Terminal avisaParada(String IDTerminal) {
        boolean flag = true;
        Terminal uTerminal=null;
        for (int i = 0; i < terminales.size() && flag; i++) {
            if (IDTerminal.equals(terminales.get(i).getID())) {
                uTerminal=terminales.get(i);
                uTerminal.nuevoArribo();
                flag = false;
            }
        }
        return uTerminal;
    }

}
