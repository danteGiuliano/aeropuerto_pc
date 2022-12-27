import java.util.ArrayList;

import hilos.Caja;
import hilos.Guardia;
import hilos.Pasajero;
import hilos.Reloj;
import hilos.Viaje;
import recursos.Aeropuerto;
import recursos.EstacionTren;
import recursos.FreeShop;
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

        hilos.add(new Thread(reloj));
        hilos.add(new Thread(new Viaje(estacionTren, terminales)));

        // Crear pasajeros
        for (int i = 0; i <40; i++) {
           hilos.add(new Thread(new Pasajero(aeropuerto)));
        }

        //RUN hilos

        hilos.forEach(e->e.start());

    }

    private static void crearTerminales() {

        //Por cada terminal, se genera una nueva posta en el viaje
        FreeShop f1 = new FreeShop(10);
        FreeShop f2 = new FreeShop(15);
        FreeShop f3 = new FreeShop(4);
        crearCajeros(f1);
        crearCajeros(f2);
        crearCajeros(f3);

        
        terminales.add(new Terminal(0, 5, "A",f1));
        terminales.add(new Terminal(6, 8, "B",f2));
        terminales.add(new Terminal(9, 14, "C",f3));

    }

    private static void crearCajeros(FreeShop freeShop){
        hilos.add(new Thread(new Caja(freeShop,1)));
        hilos.add(new Thread(new Caja(freeShop,2)));

    }



    private static void crearPuestosAeroportuarios() {

        int CANTIDAD_PUESTOS_Y_GUARDIAS=5; //Por cada puesto un guardia

        puestosAeroportuarios = new PuestoAeroportuario[CANTIDAD_PUESTOS_Y_GUARDIAS];

        for (int i = 0; i < puestosAeroportuarios.length; i++) {
            puestosAeroportuarios[i]=new PuestoAeroportuario(10);
            hilos.add(new Thread(new Guardia(puestosAeroportuarios[i], terminales)));
        }

    }

}