package recursos;

import hilos.Pasajero;
import hilos.Reloj;

public class Aeropuerto {

    private Reloj reloj;
    private PuestoAeroportuario []puestos;
    private EstacionTren estacion;
    private Hall hall;

    public Aeropuerto(Reloj reloj,PuestoAeroportuario puestoAeroportuario[]){
        this.reloj=reloj;
        this.puestos=puestoAeroportuario;
    }



    public void entradaAeropuerto(Pasajero unPasajero)throws Exception {
        //se habre la puerta a un determinado horario
        reloj.horarioLaboral(); 
        this.puestoInforme(unPasajero);
    }


    public void puestoInforme(Pasajero unPasajero)throws Exception{
        //Designacion de que puesto debe ir con arrays
        //Por el enunciado , se debe genrerar de manera aleatoria 
        unPasajero.asignarPuesto(Math.round(puestos.length));

        while(!puestos[unPasajero.getPuesto()].esperaFila()){
           Hall.esperaPorHall();
        }
    }

    public void estacionTren(Pasajero unPasajero)throws Exception{
        estacion.tomarTren();
    }



}