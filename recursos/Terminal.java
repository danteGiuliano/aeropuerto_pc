package recursos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Terminal {

   public FreeShop freeShop;


    private int salidaMinima; //Numero Random
    private int salidaMaxima; //Numero Random
    private int arriboPasajero;
    private String IDTERMINAL;

    private ReentrantLock mutex = new ReentrantLock();
    private Condition salidaTerminal = mutex.newCondition();
    private Condition llegadaTren = mutex.newCondition();

    private boolean llegada = false;

    public Terminal(int salidaMinima, int salidaMaxima, String IDTERMINAL,FreeShop freeShop) {
        this.freeShop = freeShop;
        this.salidaMinima = salidaMinima;
        this.salidaMaxima = salidaMaxima;
        arriboPasajero = 0;
        this.IDTERMINAL = IDTERMINAL;
    }

    public String getID(){
        return this.IDTERMINAL;
    }




    //---------------- GUARDIA ______________________________________________________



    /* Le da una salida disponible al guardia, para que le de al pasajero */
    public String salidaDisponible() {
        return this.IDTERMINAL+"-";
        //+ "-" + Math.round(Math.abs(salidaMinima - salidaMaxima)) + salidaMinima;
    }





    //----------------- PASAJERO ____________________________________________________



    /**
     * Aviso de un nuevo arribo a la terminal correspondiente con el ticket
     */

    public void nuevoArribo() {
        mutex.lock();
        arriboPasajero++;
        mutex.unlock();
    }


    public void decensoPasajero() throws Exception {
        this.mutex.lock();
        while (!llegada) {
            System.out.println("Espera al tren");
            this.llegadaTren.await(); //Espera a que el tren llegue a la parada correspondiente
        }
        // Desiendo de la terminal
        System.out.println("Pasajero bajando: " );
        this.arriboPasajero--;
        this.salidaTerminal.signal();
        this.mutex.unlock();

    }




//---------------------VIAJE _________________________________________________________


    /**
     * Metodo de TREN
     * 
     * 
     * @throws Exception
     */
    public void parada() throws Exception {
        this.mutex.lock();
        System.out.println("PASAJEROS PARA BAJAR :"+this.arriboPasajero);
        while (arriboPasajero > 0) {
            salidaTerminal.await();
        }
        this.llegada=false;
        this.mutex.unlock();

    }

    public void llegadaTren() throws Exception {
        this.mutex.lock();
        this.llegada = true;
        this.llegadaTren.signalAll();
        this.mutex.unlock();
    }
}
