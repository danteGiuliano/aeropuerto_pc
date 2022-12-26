import java.util.ArrayList;

import hilos.Guardia;
import hilos.Pasajero;
import hilos.Reloj;
import hilos.Viaje;
import recursos.Aeropuerto;
import recursos.EstacionTren;
import recursos.PuestoAeroportuario;
import recursos.Terminal;

class main {

    static PuestoAeroportuario[] puestosAeroportuarios;
    static ArrayList<Terminal> terminales = new ArrayList<Terminal>();
    static ArrayList<Thread> hilos = new ArrayList<>();
    public static void main(String[] args) {

        crearTerminales();
        crearPuestosAeroportuarios();
        Reloj reloj = new Reloj();
        EstacionTren estacionTren = new EstacionTren(10, terminales);
        Aeropuerto aeropuerto = new Aeropuerto(reloj, puestosAeroportuarios,estacionTren);

        hilos.add(new Thread(new Viaje(estacionTren, terminales)));

        // Crear pasajeros
        for (int i = 0; i < 12; i++) {
           hilos.add(new Thread(new Pasajero(aeropuerto)));
        }


        //RUN hilos



        hilos.forEach(e->e.start());

    }

    private static void crearTerminales() {

        //Por cada terminal, se genera una nueva posta en el viaje
        
        terminales.add(new Terminal(0, 5, "A"));
        terminales.add(new Terminal(6, 8, "B"));
        terminales.add(new Terminal(9, 14, "C"));

    }

    private static void crearPuestosAeroportuarios() {

        int CANTIDAD_PUESTOS_Y_GUARDIAS=5; //Por cada puesto un guardia

        puestosAeroportuarios = new PuestoAeroportuario[CANTIDAD_PUESTOS_Y_GUARDIAS];

        for (int i = 0; i < puestosAeroportuarios.length; i++) {
            puestosAeroportuarios[i]=new PuestoAeroportuario(30);
            hilos.add(new Thread(new Guardia(puestosAeroportuarios[i], terminales)));
        }


    }

}