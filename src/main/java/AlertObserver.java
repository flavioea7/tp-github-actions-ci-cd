public class AlertObserver implements TransportObserver {
    // AlertObserver implementa la interfaz TransportObserver
    // para recibir actualizaciones del transporte y decidir
    // si debe generar alertas en el logger


    // ===== ATRIBUTOS =====

    private AlertService alertService;
    // Servicio encargado de decidir si se debe alertar
    // (lógica de umbrales desacoplada del observer)

    private Logger logger;
    // Logger encargado de registrar advertencias y errores


    // ===== CONSTRUCTOR =====

    public AlertObserver(AlertService alertService, Logger logger) {

        // Inyección de dependencias:
        // AlertObserver NO sabe cómo se implementa AlertService
        // ni cómo se implementa Logger.
        // Solo los usa.

        this.alertService = alertService;

        this.logger = logger;
    }


    // ===== MÉTODO DEL OBSERVER =====

    @Override
    public void onUpdate(TransportSnapshot snapshot) {

        // Verifica si el costo supera el umbral permitido
        // usando la lógica definida en AlertService

        if (alertService.shouldAlertCost(snapshot.getCost())) {

            // Si supera el máximo permitido,
            // registra advertencia en el logger

            logger.logWarning(
                    "Costo muy alto: $" +
                            String.format("%.2f", snapshot.getCost())
            );
        }


        // Verifica si el ETA supera el umbral permitido

        if (alertService.shouldAlertETA(snapshot.getEta())) {

            // Si supera el máximo permitido,
            // registra error en el logger

            logger.logError(
                    "ETA demasiado alto: " +
                            snapshot.getEta() + " min"
            );
        }
    }
}