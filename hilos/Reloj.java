package hilos;


public class Reloj implements Runnable{
    
    private boolean abierto=true;
    private String log="";
    @Override
    public void run() {

        try {
            System.out.println("RELOJ :"+Thread.currentThread().getName()+" INICIADO");
            Thread.sleep(100);
            while(true){
                if(abierto){
                    synchronized (this){
                       // System.out.println("AEROPUERTO CERRADO");
                        Thread.sleep(3000);
                        abierto=false;
                    }
                }
                else{
                   // System.out.println("AEROPUERTO ABIERTO");
                    Thread.sleep(20000);
                    abierto=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
                }
        
    }


    public synchronized void horarioLaboral(Pasajero unPasajero) {
        this.log+="Pasajero nuevo :"+unPasajero.hashCode();
    }
    
}
