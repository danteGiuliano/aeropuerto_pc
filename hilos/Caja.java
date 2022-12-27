package hilos;

import recursos.FreeShop;

public class Caja implements Runnable{

    private FreeShop freeShop;
    private int numeroCaja=-1;

    public Caja(FreeShop freeShop,int cajaN){
        this.freeShop=freeShop;
        this.numeroCaja=cajaN;
    }

    @Override
    public void run() {
        
        try {
            System.out.println("HILO : "+Thread.currentThread().getName()+" INICIADO");
            Thread.sleep(100);

            while(true){
                if(this.numeroCaja==1){
                    freeShop.atiendeCaja1();
                }else{
                    freeShop.atiendeCaja2();
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
