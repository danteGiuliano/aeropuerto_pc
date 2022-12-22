package recursos;

import java.util.ArrayList;

import hilos.Pasajero;
import hilos.Reloj;

public class Aeropuerto {

    private Reloj reloj;
    private PuestoAeroportuario []puestos;
    private EstacionTren estacion;
    private ArrayList<Terminal> terminales = new ArrayList<Terminal>();

    public Aeropuerto(Reloj reloj,PuestoAeroportuario puestoAeroportuario[], ArrayList terminales){
        this.reloj=reloj;
        this.puestos=puestoAeroportuario;
        this.terminales=terminales;
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
        while(!puestos[unPasajero.getPuesto()].esperaFila(unPasajero)){
           Hall.esperaPorHall();
        }
    }

    public void estacionTren(Pasajero unPasajero)throws Exception{
        estacion.tomarTren(unPasajero);
    }



}