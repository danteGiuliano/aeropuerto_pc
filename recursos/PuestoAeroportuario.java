package recursos;

public class PuestoAeroportuario {
    /**
     * Clase que modela los puestos aaeroportuarios
     * 
     */

    private static  int CAPACIDAD_MAXIMA;
    private int capacidadActual;


    public PuestoAeroportuario(int CAPACIDAD_MAXIMA){
        this.CAPACIDAD_MAXIMA=CAPACIDAD_MAXIMA;
        this.capacidadActual=0;
    }



    public boolean esperaFila()throws Exception{
        return true;
    }
}
