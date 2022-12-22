package recursos;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EstacionTren {
    /**
     * Clase que modela la estacion de tren, desde su partida hasta la llegada a las terminales
     */

     private CyclicBarrier capacidadTren;
     

     public EstacionTren(int capacidadTren){
        this.capacidadTren=new CyclicBarrier(capacidadTren);
        
     }

    

    public void tomarTren() throws Exception{
        this.capacidadTren.await();    
    }
}
