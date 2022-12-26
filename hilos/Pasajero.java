package hilos;

import recursos.Aeropuerto;
import recursos.Terminal;

public class Pasajero implements Runnable {

    private Aeropuerto aeropuerto;
    private int puesto = -1;
    private String ticket = "";


    public Pasajero(Aeropuerto aeropuerto){
        this.aeropuerto=aeropuerto;
    }

    @Override
    public void run() {
        try {
            System.out.println("PASAJERO :"+Thread.currentThread().getName()+" INICIADO");
            Thread.sleep(100);
            aeropuerto.entradaAeropuerto(this);
            aeropuerto.puestoInforme(this);
            Terminal uT = aeropuerto.estacionTren(this);
            System.out.println("ESpera el tren");
            aeropuerto.esperaTren();
            uT.decensoPasajero();
            // Modelar la terminal 
            System.out.println("PASAJERO :"+Thread.currentThread().getName()+" TERMINADO");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void asignarPuesto(int numeroPuesto) {
        this.puesto = numeroPuesto;
    }

    public int getPuesto() {
        return puesto;
    }

    public void obtenerTicket(String ticket) {
        this.ticket = ticket;
    }

    public String terminalAsociada() {
        return ticket.substring(0, ticket.indexOf('-'));
    }

}
