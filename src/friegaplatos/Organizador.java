package friegaplatos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Organizador implements Runnable {


    private final BandejaDePlatos bandejaFregadoSeco;
    private final BandejaDePlatos bandejaGuardado;
    private final LocalTime hora =LocalTime.now();
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");



    public Organizador(BandejaDePlatos bandejaFregadoSeco,BandejaDePlatos bandejaGuardado){
        this.bandejaFregadoSeco=bandejaFregadoSeco;
        this.bandejaGuardado=bandejaGuardado;
    }




    @Override
    public void run() {
        Platos plato;
        while(!Thread.currentThread().isInterrupted()){
            try {
                plato=bandejaFregadoSeco.extraerDeLaBandeja();
            } catch (InterruptedException e) {
                return;
            } try {
                guardarPlato(plato);
            } catch (InterruptedException e) {
                return;
            }

        }

    }

    private void guardarPlato(Platos plato) throws InterruptedException {
        Random random = new Random();
        TimeUnit.SECONDS.sleep(random.nextInt(2)+1);
        bandejaGuardado.añadirBandeja(plato);

    }


}