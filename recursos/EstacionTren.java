package recursos;

import java.util.concurrent.CyclicBarrier;


import hilos.Pasajero;
import hilos.Viaje;

public class EstacionTren {
    /**
     * Clase que modela la estacion de tren, desde su partida hasta la llegada a las terminales
     */
     Viaje viaje = new Viaje(this);
     private CyclicBarrier tren;
     
     

     public EstacionTren(int capacidadTren){
        this.tren=new CyclicBarrier(capacidadTren, viaje);
     }

    
        //Pensar una manera dinamica de crear terminales , y que los pasajeros se bajen en esa
        
    public void tomarTren(Pasajero unPasajero) throws Exception{
        this.tren.await();    
    }

}
