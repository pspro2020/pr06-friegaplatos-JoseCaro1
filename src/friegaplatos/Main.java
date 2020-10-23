package friegaplatos;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        BandejaDePlatos bandejaFregar= new BandejaDePlatos();
        BandejaDePlatos bandejaFregarSeco= new BandejaDePlatos();
        BandejaDePlatos bandejaGuardar= new BandejaDePlatos();
        Thread fregador= new Thread(new Fregador(bandejaFregar),"Fregar");
        Thread secador= new Thread(new Secador(bandejaFregar,bandejaFregarSeco),"Secar");
        Thread organizador= new Thread(new Organizador(bandejaFregarSeco,bandejaGuardar),"Guardar");




        fregador.start();
        secador.start();
        organizador.start();
        Thread.sleep(60000);
        fregador.interrupt();
        secador.interrupt();
        organizador.interrupt();
        fregador.join();
        secador.join();
        organizador.join();

        System.out.println("Feliz cumpleaños");


    }
}
