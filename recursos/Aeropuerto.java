package recursos;


import java.util.concurrent.Semaphore;

import hilos.Pasajero;
import hilos.Reloj;

public class Aeropuerto {

    private Reloj reloj;
    private PuestoAeroportuario []puestos;
    private EstacionTren estacion;

    private Semaphore mutex = new Semaphore(1);

    public Aeropuerto(Reloj reloj,PuestoAeroportuario puestoAeroportuario[],EstacionTren estacion){
        this.reloj=reloj;
        this.puestos=puestoAeroportuario;
        this.estacion=estacion;
    }



    public void entradaAeropuerto(Pasajero unPasajero)throws Exception {
        //se habre la puerta a un determinado horario
        reloj.horarioLaboral(unPasajero); 
    }


    public void puestoInforme(Pasajero unPasajero)throws Exception{
        //Designacion de que puesto debe ir con arrays
        //Por el enunciado , se debe genrerar de manera aleatoria 
        mutex.acquire();
        unPasajero.asignarPuesto(Math.round(puestos.length-1));
        mutex.release();
        while(!puestos[unPasajero.getPuesto()].esperaFila(unPasajero)){
           Hall.esperaPorHall();
        }
    }

    public Terminal estacionTren(Pasajero unPasajero)throws Exception{
       return estacion.tomarTren(unPasajero);
    }
    public void esperaTren()throws Exception{
        estacion.esperaTren();
    }



}