public class Main {

    public static void main(String[] args) {

        // ===== MONITOR =====

        // El monitor ya no recibe strategy por constructor
        // porque ahora trabaja con fases

        TransportMonitor monitor = new TransportMonitor();


        // ===== OBSERVERS =====

        // Observer que imprime información en consola

        ConsolePrinter printer = new ConsolePrinter();


        // ===== ALERT SERVICE =====

        // Servicio encargado de decidir
        // cuándo debe generarse una alerta

        // El constructor recibe:
        // - costo máximo permitido
        // - ETA máximo permitido

        AlertService alertService =
                new ThresholdAlertService(5000, 40);


        // ===== LOGGER =====

        // Obtiene la instancia Singleton del logger

        Logger logger = Logger.getInstance();


        // ===== ALERT OBSERVER =====

        // AlertObserver recibe dependencias por constructor
        // para aplicar inyección de dependencias

        AlertObserver alert =
                new AlertObserver(alertService, logger);


        // ===== SUSCRIPCIONES =====

        // Se suscriben los observers al monitor

        monitor.subscribe(printer);

        monitor.subscribe(alert);


        // ===== FASES =====

        // Cada fase contiene:
        // - una strategy
        // - cantidad de ciclos

        // 4 ciclos de taxi

        monitor.addFase(new TaxiStrategy(), 4);


        // 3 ciclos de bicicleta

        monitor.addFase(new BicicletaStrategy(), 3);


        // 3 ciclos de colectivo

        monitor.addFase(new ColectivoStrategy(), 3);


        // ===== INICIO =====

        // Inicia las notificaciones cada 2000 ms

        monitor.start(2000);
    }
}