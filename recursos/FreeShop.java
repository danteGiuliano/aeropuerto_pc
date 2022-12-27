package recursos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FreeShop {

    private int CAPACIDAD_SHOP;
    private int capacidadActual;

    ReentrantLock caja1 = new ReentrantLock();
    ReentrantLock caja2 = new ReentrantLock();


    Condition atenderCaja1 = caja1.newCondition();
    Condition esperaCaja1 = caja1.newCondition();
    private int cola1 = 0;

    Condition atenderCaja2 = caja2.newCondition();
    Condition esperaCaja2 = caja2.newCondition();
    private int cola2 = 0;


    public FreeShop(int CAPACIDAD_MAXIMA) {
        this.CAPACIDAD_SHOP = CAPACIDAD_MAXIMA;
        this.capacidadActual = 0;
    }


    //-- Pasajero ________________________________________

    public synchronized boolean hayEspacio()throws Exception{
        return this.capacidadActual+1<=this.CAPACIDAD_SHOP;
    }


    public synchronized void llegadaLocal() throws Exception {
        this.capacidadActual++;
    }

    public void esperaCaja1() throws Exception {
        this.caja1.lock();
        this.atenderCaja1.signal();
        this.esperaCaja1.await();
        this.caja1.unlock();
    }
    public void esperaCaja2()throws Exception{
        this.caja2.lock();
        this.atenderCaja2.signal();
        this.esperaCaja2.await();
        this.caja2.unlock();
    }

    public synchronized void salidaLocal() throws Exception {
        this.capacidadActual--;
    }

    //-- Caja ____________________________________________

    public void atiendeCaja1()throws Exception{
        this.caja1.lock();
        while(this.cola1<0){
            atenderCaja1.await();
        }
        this.cola1--;
        this.esperaCaja1.signal();
        this.caja1.unlock();
    }

    public void atiendeCaja2()throws Exception{
        this.caja2.lock();
        while(this.cola2<0){
            atenderCaja2.await();
        }
        this.cola2--;
        this.esperaCaja2.signal();
        this.caja2.unlock();
    }

}
