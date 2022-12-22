package recursos;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

import hilos.Pasajero;
import hilos.Viaje;

public class EstacionTren {
    /**
     * Clase que modela la estacion de tren, desde su partida hasta la llegada a las
     * terminales
     */
    Viaje viaje;
    private CyclicBarrier tren;
    ArrayList<Terminal> terminales;

    public EstacionTren(int capacidadTren, ArrayList terminales) {
        this.tren = new CyclicBarrier(capacidadTren, viaje);
        this.terminales = terminales;
        this.viaje = new Viaje(this, terminales);
    }

    public Terminal tomarTren(Pasajero unPasajero) throws Exception {
        Terminal uT=avisaParada(unPasajero.terminalAsociada());
        this.tren.await();
        return uT;
    }

    // De esta manera incremento un nuevo arribo a una terminal X
    private Terminal avisaParada(String IDTerminal) {
        boolean flag = true;
        Terminal uTerminal=null;
        for (int i = 0; i < terminales.size() && flag; i++) {
            if (IDTerminal.equals(terminales.get(i))) {
                uTerminal=terminales.get(i);
                uTerminal.nuevoArribo();
                flag = false;
            }
        }
        return uTerminal;
    }

}
