public class ConsolePrinter implements TransportObserver {

    private Logger logger;      //LOGGER

    public ConsolePrinter() {
        logger = Logger.getInstance();
    }

    @Override
    public void onUpdate(TransportSnapshot snapshot) {

        logger.logInfo("Transporte actualizado");

        logger.logDebug("Nombre: " + snapshot.getName());

        logger.logDebug("Costo: $"    + String.format("%.2f", snapshot.getCost()));

        logger.logDebug("Distancia: " + String.format("%.2f", snapshot.getDistance()) + " km");

        logger.logDebug("ETA: " + snapshot.getEta() + " min");
    }
}

