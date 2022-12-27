package hilos;

import java.util.Random;

import recursos.Aeropuerto;
import recursos.Terminal;

public class Pasajero implements Runnable {

    private Aeropuerto aeropuerto;
    private int puesto = 0;
    private String ticket = "";

    private Random rd = new Random();

    public Pasajero(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    @Override
    public void run() {
        try {
            System.out.println("PASAJERO :" + Thread.currentThread().getName() + " INICIADO");
            Thread.sleep(100);
            while(true){

                aeropuerto.entradaAeropuerto(this);
                aeropuerto.puestoInforme(this);
                Terminal uT = aeropuerto.estacionTren(this);
                System.out.println("ESpera el tren TERMINAL ASOCIADA " + this.terminalAsociada());
                aeropuerto.esperaTren();
                uT.decensoPasajero();
                // Modelar la terminal
                if (rd.nextBoolean()) {
                    // Entra a comprar
                    if(uT.freeShop.hayEspacio()) {
                        uT.freeShop.llegadaLocal();
                        // Busca por ahi
                        if (rd.nextBoolean()) { // Caja 1 o 2
                            uT.freeShop.atiendeCaja1();
                        } else {
                            uT.freeShop.atiendeCaja2();
                        }
                    }
                }
                Thread.sleep(1000); //Espera su viaje
                // ENlazar la terminal
    
                System.out.println("PASAJERO :" + Thread.currentThread().getName() + " TERMINADO");
            }
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
