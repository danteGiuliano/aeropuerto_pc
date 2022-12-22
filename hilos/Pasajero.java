package hilos;

import recursos.Aeropuerto;
import recursos.Terminal;

public class Pasajero implements Runnable {

    private Aeropuerto aeropuerto;
    private int puesto = -1;
    private String ticket = "";

    @Override
    public void run() {
        try {
            aeropuerto.entradaAeropuerto(this);
            aeropuerto.puestoInforme(this);
            Terminal uT = aeropuerto.estacionTren(this);
            uT.decensoPasajero();
        } catch (Exception e) {
            // TODO: handle exception
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
