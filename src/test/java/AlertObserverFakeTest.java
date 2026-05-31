import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AlertObserverFakeTest {
    // FAKE ALERT SERVICE QUE SIEMPRE GENERA ALERTAS

    static class AlwaysAlertService implements AlertService {
        // Siempre retorna true para alertas de costo

        @Override
        public boolean shouldAlertCost(double cost) {

            return true;
        }

        // Siempre retorna true para alertas de ETA

        @Override
        public boolean shouldAlertETA(int eta) {

            return true;
        }
    }

    // FAKE ALERT SERVICE QUE NUNCA GENERA ALERTAS

    static class NeverAlertService implements AlertService {
        // Nunca alerta por costo

        @Override
        public boolean shouldAlertCost(double cost) {

            return false;
        }

        // Nunca alerta por ETA

        @Override
        public boolean shouldAlertETA(int eta) {

            return false;
        }
    }

    // TEST:
    // Verifica que AlertObserver loggee mensajes
    // cuando AlertService retorna true

    @Test
    void shouldLogWhenAlertServiceReturnsTrue() {
        // Redirecciona la salida estándar (System.out)
        // a un buffer en memoria para poder inspeccionarla

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));

        // Crea logger singleton

        Logger logger = Logger.getInstance();

        // Observer usando fake que siempre alerta

        AlertObserver observer =
                new AlertObserver(
                        new AlwaysAlertService(),
                        logger
                );

        // Snapshot de prueba

        TransportSnapshot snapshot =
                new TransportSnapshot(
                        "Colectivo",
                        120.0,
                        45.2,
                        25
                );

        // Ejecuta actualización

        observer.onUpdate(snapshot);

        // Convierte la salida capturada a String

        String consoleOutput = output.toString();

        // Verifica que exista mensaje de warning
        // o error en consola

        assertTrue(
                consoleOutput.contains("[WARN]") ||
                        consoleOutput.contains("[ERROR]")
        );
    }

    // TEST:
    // Verifica que NO se loggee nada cuando
    // AlertService retorna false

    @Test
    void shouldNotLogWhenAlertServiceReturnsFalse() {
        // Redirecciona salida estándar

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));

        // Logger singleton

        Logger logger = Logger.getInstance();

        // Observer usando fake que nunca alerta

        AlertObserver observer =
                new AlertObserver(
                        new NeverAlertService(),
                        logger
                );

        // Snapshot de prueba

        TransportSnapshot snapshot =
                new TransportSnapshot(
                        "Colectivo",
                        120.0,
                        45.2,
                        25
                );

        // Ejecuta actualización

        observer.onUpdate(snapshot);

        // Obtiene salida capturada

        String consoleOutput = output.toString();

        // Verifica que NO exista ningún warning
        // ni error en consola

        assertFalse(consoleOutput.contains("[WARN]"));

        assertFalse(consoleOutput.contains("[ERROR]"));
    }
}