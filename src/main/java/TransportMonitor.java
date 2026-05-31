import java.util.ArrayList;
import java.util.List;

public class TransportMonitor {

    private List<TransportObserver> observers;  //LISTA DE OBSERVERS
    private List<Fase> fases;   //LISTA DE FASES (estrategia + ciclos)


    //CREO UNA "INNER CLASE", ES PRIVADA PQ SOLO LA USARIA TRANSPORT MONITOR, USO UNA CLASE Y NO 2 LISTAS PQ ES MEJOR DISEÑO
    //LA CLASE BASICAMENTE RECIBE LA ESTRATEGIA QUE TOCA Y UN NUMERO DE VECES QUE SE VA A USAR, ES COMOODO PARA EL BUCLE PQ
    //PUEDO CAMBIAR LA STRATEGY Y LA CANT DE CICLOS SIN TOQUETEAR DE NUEVO TRANSPORT MONITOR, SOLO EL MAIN. Y EVITO USAR DOBLE LISTAS

    private static class Fase {
        TransporteStrategy strategy;    //ESTRATEGIA DE LA FASE
        int ciclos; //CANTIDAD DE CICLOS DE LA FASE

        Fase(TransporteStrategy strategy, int ciclos) { //CONSTRUCTOR DE FASE
            this.strategy = strategy;
            this.ciclos = ciclos;
        }
    }

    public TransportMonitor() {
        this.observers = new ArrayList<>(); //lista de observers del monitor de transporte
        this.fases = new ArrayList<>(); //lista de fases del monitor de transporte
    }

    public void addFase(TransporteStrategy strategy, int ciclos) {  //METODO PARA AGREGAR UNA FASE
        fases.add(new Fase(strategy, ciclos));  //añade la fase a la lista de fases
    }

    public void subscribe(TransportObserver observer) { //METODO PARA SUSCRIBIRSE
        observers.add(observer);    //añade el transportObserver a la lista de observers
    }

    public void unsubscribe(TransportObserver observer) {   //METODO PARA DESSUSCRIBIRSE
        observers.remove(observer); //quita el transportObserver de la lista
    }

    private void notifyObservers(TransporteStrategy strategy) {    //notifica a los observers con un snapshot de la informacion del transporte

        TransportSnapshot snapshot = new TransportSnapshot( //snapshot con la informacion del transporte
                strategy.getNombre(),
                strategy.getPrecio(),
                strategy.getDistancia(),
                strategy.getETA()
        );

        for (TransportObserver observer : observers) {
            observer.onUpdate(snapshot);    //notifica a cada observer con el snapshot
        }
    }

    public void start(int intervalMs) { //notifica cada intervalo de ms a los observers

        for (Fase fase : fases) {   //recorre cada fase de la lista

            for (int i = 0; i < fase.ciclos; i++) {    //repite la notificacion la cantidad de ciclos de la fase

                notifyObservers(fase.strategy);

                try {
                    Thread.sleep(intervalMs);   //espera de intervalMs antes de la siguiente notificacion
                } catch (InterruptedException e) {
                    e.printStackTrace();    //manejo de excepcion en caso de que sleep sea interrumpido
                }
            }
        }
    }
}