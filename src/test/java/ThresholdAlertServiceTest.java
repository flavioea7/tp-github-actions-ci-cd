import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThresholdAlertServiceTest {

    //1. Costo por debajo del umbral → shouldAlertCost retorna false .
    @Test
    void testCostBelowThreshold() {
        AlertService service = new ThresholdAlertService(100.0, 30);
        assertFalse(service.shouldAlertCost(50.0));
    }
    //2. Costo exactamente en el umbral → definir y documentar el comportamiento esperado.
    @Test
    void testCostExactlyAtThreshold() {
        AlertService service = new ThresholdAlertService(100.0, 30);
        // Documentación: retorna false porque usa ">"
        assertFalse(service.shouldAlertCost(100.0));
    }
    //3. Costo por encima del umbral → shouldAlertCost retorna true .
    @Test
    void testCostAboveThreshold() {
        AlertService service = new ThresholdAlertService(100.0, 30);
        assertTrue(service.shouldAlertCost(150.0));
    }
    //4. ETA por debajo del umbral → shouldAlertETA retorna false .
    @Test
    void testEtaBelowThreshold() {
        AlertService service = new ThresholdAlertService(100.0, 30);
        assertFalse(service.shouldAlertETA(20));
    }
    //5. ETA por encima del umbral → shouldAlertETA retorna true .
    @Test
    void testEtaAboveThreshold() {
        AlertService service = new ThresholdAlertService(100.0, 30);
        assertTrue(service.shouldAlertETA(40));
    }
}
