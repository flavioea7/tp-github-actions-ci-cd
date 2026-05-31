# TP GitHub Actions - CI/CD

## Grupo: DemandadeNintendo

Repositorio desarrollado por el grupo **DemandadeNintendo** para la implementación de un pipeline de Integración Continua (CI) y Entrega Continua (CD) utilizando GitHub Actions y Gradle.

---

## Composite Actions

### Build Action

Ubicación: `.github/actions/build/action.yml`

Esta action compila el proyecto Java utilizando Gradle y permite reutilizar el proceso de construcción dentro de distintos workflows.

### Test Action

Ubicación: `.github/actions/test/action.yml`

Esta action ejecuta la suite de pruebas automatizadas del proyecto para verificar que los cambios introducidos no rompan el comportamiento esperado.

---

## Workflow de Integración Continua (CI)

Archivo: `.github/workflows/main.yml`

### ¿Cuándo se ejecuta?

Se dispara automáticamente cuando se crea o actualiza un Pull Request dirigido a la rama `main`.

### ¿Qué hace?

1. Ejecuta la acción de Build.
2. Ejecuta la acción de Test.
3. Informa el resultado mediante checks visibles en el Pull Request.

### Objetivo

Evitar que cambios con errores de compilación o pruebas fallidas lleguen a la rama principal.

---

## Workflow de Entrega Continua (CD)

Archivo: `.github/workflows/release.yml`

### ¿Cuándo se ejecuta?

Cuando se crea un tag con formato semántico:

- `v1.0.0`
- `v1.1.0`
- `v2.0.0`

### ¿Qué hace?

1. Compila el proyecto.
2. Genera el archivo JAR.
3. Publica automáticamente un Release en GitHub.
4. Adjunta el artefacto generado al Release.

### Objetivo

Automatizar la generación y publicación de versiones listas para distribuir.

---

## Evidencias obtenidas

Durante el desarrollo se verificó el funcionamiento del pipeline mediante:

- Pull Requests con ejecución exitosa de Build y Test.
- Pull Requests con pruebas fallidas para demostrar el bloqueo del pipeline.
- Corrección posterior de los errores detectados.
- Publicación automática de un Release asociado al tag `v1.0.0`.

---

## Diferencia entre CI y CD

### Continuous Integration (CI)

La Integración Continua valida automáticamente cada cambio mediante compilación y pruebas antes de integrarlo a la rama principal.

En este proyecto está representada por el workflow:

`main.yml`

### Continuous Delivery (CD)

La Entrega Continua automatiza la generación y publicación de versiones listas para distribuir.

En este proyecto está representada por el workflow:

`release.yml`

---

## Tecnologías utilizadas

- Java 21
- Gradle
- GitHub Actions
- JUnit 5

---

## Repositorio

Repositorio del proyecto:

https://github.com/flavioea7/tp-github-actions-ci-cd
