package friegaplatos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BandejaDePlatos {
    private List<Platos> platos = new ArrayList<>();
    private final LocalTime hora =LocalTime.now();
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");


    protected synchronized void añadirBandeja(Platos plato) throws InterruptedException {
        System.out.printf("%s - %s plato nº %d\n",LocalTime.now().format(format),Thread.currentThread().getName(),plato.getNumSerie());
        platos.add(plato);
        notifyAll();

    }

    protected Platos extraerDeLaBandeja() throws InterruptedException {
        Platos plato = null;
        synchronized (this) {
            while (platos.isEmpty()) {
                wait();
            }
            plato = platos.remove(0);
            notifyAll();
            return plato;
        }
    }


}

