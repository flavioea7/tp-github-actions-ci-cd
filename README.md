# TP - Unit Testing

## Ingeniería de Software

### Grupo:DemandadeNintendo

---

# Descripción

Este trabajo práctico continúa el TP anterior de Patrones de Diseño.
Se trabajó sobre el sistema de monitoreo de transporte implementando tests unitarios y realizando una pequeña refactorización del diseño original.

Se utilizaron los siguientes patrones vistos anteriormente:

* Strategy
* Observer
* Singleton

Además, en este TP se aplicaron conceptos de:

* Unit Testing
* Inyección de dependencias
* Fake objects
* Mocks con Mockito

---

# Refactorización realizada

Se creó la interfaz `AlertService` para separar la lógica de decisión de alertas de la lógica de logging.

Antes, `AlertObserver` tenía dos responsabilidades:

* decidir cuándo alertar
* escribir mensajes en el logger

Ahora:

* `ThresholdAlertService` se encarga de la lógica de umbrales
* `AlertObserver` solamente actúa cuando recibe una alerta

También se modificó `AlertObserver` para recibir dependencias por constructor (`AlertService` y `Logger`) y facilitar el testing.

---

# Tests implementados

## 2.1 - Tests de ThresholdAlertService

Se realizaron tests unitarios verificando:

* costo por debajo del umbral
* costo igual al umbral
* costo por encima del umbral
* ETA por debajo del umbral
* ETA por encima del umbral

---

## 2.2 - Tests con Fake

Se implementaron:

* `AlwaysAlertService`
* `NeverAlertService`

para probar el comportamiento de `AlertObserver` usando fakes manuales.

---

## 2.3 - Tests con Mockito

Se utilizaron mocks para verificar interacciones con el logger:

* `logWarning`
* `logError`
* ausencia de llamadas al logger

---

# Tecnologías utilizadas

* Java 21
* Gradle
* JUnit 5
* Mockito

---

# Cómo ejecutar el proyecto

Desde IntelliJ ejecutar la clase:

```text
Main.java
```

---

# Cómo correr los tests

Desde la terminal:

```powershell
.\gradlew test
```

o ejecutando los tests desde IntelliJ.

---

# Nota

Durante la ejecución de Mockito pueden aparecer warnings relacionados con ByteBuddy y Java 21.
Los tests funcionan correctamente igualmente.
