import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AlertObserverMockTest {
    // TEST:
    // Verifica que cuando shouldAlertCost retorna true,
    // AlertObserver llame al logger con logWarning

    @Test
    void testShouldAlertCostTriggersLogWarning() {
        // + CREACIÓN DE MOCKS:

        // Mock del logger para verificar interacciones
        // sin usar el logger real

        Logger mockLogger = mock(Logger.class);


        // Mock del AlertService para controlar
        // manualmente el comportamiento de las alertas

        AlertService mockService = mock(AlertService.class);


        // + CONFIGURACIÓN DEL MOCK:

        // Simula que el costo supera el umbral

        when(mockService.shouldAlertCost(anyDouble()))
                .thenReturn(true);


        // Simula que el ETA NO supera el umbral

        when(mockService.shouldAlertETA(anyInt()))
                .thenReturn(false);


        // + CREACIÓN DEL OBSERVER:

        AlertObserver observer =
                new AlertObserver(mockService, mockLogger);


        // + SNAPSHOT DE PRUEBA:

        observer.onUpdate(
                new TransportSnapshot(
                        "Uber",
                        200.0,
                        5.0,
                        20
                )
        );


        // + VERIFICACIONES:

        // Verifica que se llamó logWarning

        verify(mockLogger).logWarning(anyString());


        // Verifica que NO se llamó logError

        verify(mockLogger, never())
                .logError(anyString());
    }



    // TEST:
    // Verifica que cuando shouldAlertETA retorna true,
    // AlertObserver llame al logger con logError

    @Test
    void testShouldAlertEtaTriggersLogError() {


        // + MOCKS:

        Logger mockLogger = mock(Logger.class);

        AlertService mockService = mock(AlertService.class);


        // + CONFIGURACIÓN:

        // El costo NO genera alerta

        when(mockService.shouldAlertCost(anyDouble()))
                .thenReturn(false);


        // El ETA SI genera alerta

        when(mockService.shouldAlertETA(anyInt()))
                .thenReturn(true);


        // + OBSERVER:

        AlertObserver observer =
                new AlertObserver(mockService, mockLogger);


        // + EJECUCIÓN:

        observer.onUpdate(
                new TransportSnapshot(
                        "Uber",
                        50.0,
                        5.0,
                        40
                )
        );


        // + VERIFICACIONES:

        // Debe registrar error

        verify(mockLogger).logError(anyString());


        // No debe registrar warning

        verify(mockLogger, never())
                .logWarning(anyString());
    }

    // TEST:
    // Verifica que cuando ambas condiciones son false,
    // el logger no sea llamado en ningún momento

    @Test
    void testNoAlertsNoLogging() {


        // + MOCKS:

        Logger mockLogger = mock(Logger.class);

        AlertService mockService = mock(AlertService.class);


        // + CONFIGURACIÓN:

        // Ninguna condición genera alerta

        when(mockService.shouldAlertCost(anyDouble()))
                .thenReturn(false);

        when(mockService.shouldAlertETA(anyInt()))
                .thenReturn(false);


        // + OBSERVER:

        AlertObserver observer =
                new AlertObserver(mockService, mockLogger);


        // + EJECUCIÓN:

        observer.onUpdate(
                new TransportSnapshot(
                        "Uber",
                        50.0,
                        5.0,
                        10
                )
        );


        // + VERIFICACIÓN:

        // Verifica que el logger NO fue utilizado

        verifyNoInteractions(mockLogger);
    }
}