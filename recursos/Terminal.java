package recursos;

public class Terminal {
    private int salidaMinima;
    private int salidaMaxima;
    private int arriboPasajero;
    private String IDTERMINAL;
    
    public Terminal(int salidaMinima,int salidaMaxima,String IDTERMINAL){
        this.salidaMinima=salidaMinima;
        this.salidaMaxima=salidaMaxima;
        arriboPasajero=0;
        this.IDTERMINAL=IDTERMINAL;
    }





    /* Le da una salida disponible al guardia, para que le de al pasajero */
    public String salidaDisponible(){
        return this.IDTERMINAL+Math.round(Math.abs(salidaMinima-salidaMaxima))+salidaMinima;
    }
    /* El Guardia avisa de un nuevo arribo de pasajero */
    public void nuevoArribo(){
        arriboPasajero++;
    }
}

//Usar monitores con condicions para el tren