package friegaplatos;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Fregador implements Runnable {

    private BandejaDePlatos bandejaSucia;
    private BandejaDePlatos bandejaFregada;
    private final LocalTime hora = LocalTime.now();
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
    private int numSerie = 0;


    Fregador(BandejaDePlatos bandejaSucia,BandejaDePlatos bandejaFregada) {
        this.bandejaSucia = bandejaSucia;
        this.bandejaFregada=bandejaFregada;
    }


    @Override
    public void run() {
        Platos plato;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                plato = cogerPlato();
            } catch (InterruptedException e) {
                return;
            }
            try {
                limpiarPlato(plato);
            } catch (InterruptedException e) {
                return;
            }

        }

    }

    private void limpiarPlato(Platos plato) throws InterruptedException {
        Random random = new Random();
        TimeUnit.SECONDS.sleep(random.nextInt(5) + 4);
        bandejaFregada.añadirBandeja(plato);

    }

    private Platos cogerPlato() throws InterruptedException {
        return bandejaSucia.extraerDeLaBandeja();
    }
}
