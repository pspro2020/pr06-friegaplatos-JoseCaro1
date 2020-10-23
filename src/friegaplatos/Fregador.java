package friegaplatos;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Fregador implements Runnable {

    private final BandejaDePlatos bandeja;
    private final LocalTime hora =LocalTime.now();
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
    private int numSerie=0;



    Fregador(BandejaDePlatos bandeja){
        this.bandeja=bandeja;
    }


    @Override
    public void run() {
            Platos plato;
        while(!Thread.currentThread().isInterrupted()){
            try {
                plato=cogerPlato();
            } catch (InterruptedException e) {
                return;
            } try {
                limpiarPlato(plato);
            } catch (InterruptedException e) {
                return;
            }

        }

    }

    private void limpiarPlato(Platos plato) throws InterruptedException {
        Random random = new Random();
        TimeUnit.SECONDS.sleep(random.nextInt(5)+4);
        bandeja.añadirBandeja(plato);

    }

    private Platos  cogerPlato() throws InterruptedException {
        Thread.sleep(5000);
        return new Platos(numSerie++);
    }
}
