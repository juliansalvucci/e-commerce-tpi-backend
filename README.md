# **MegaStore Backend**

## **Descripción del Proyecto**
El proyecto MegaStore Backend se encarga de proveer la lógica de negocio y los servicios necesarios para el funcionamiento del sistema de gestión de la plataforma de eCommerce MegaStore. Esta aplicación de backend trabaja en conjunto con el repositorio del frontend ([MegaStore Frontend](https://github.com/Grupo-9-Anita/e-commerce-tpi-frontend/tree/main)) para ofrecer una experiencia integral a los usuarios finales.

El backend incluye funcionalidades como la gestión de productos, usuarios, pedidos y stock, además de implementar validaciones, controladores y servicios para garantizar la seguridad, eficiencia y escalabilidad del sistema.

---

## **Tabla de Contenidos**
- [**MegaStore Backend**](#megastore-backend)
  - [**Descripción del Proyecto**](#descripción-del-proyecto)
  - [**Tabla de Contenidos**](#tabla-de-contenidos)
  - [**Tecnologías**](#tecnologías)
  - [**Estructura del Proyecto**](#estructura-del-proyecto)
  - [**Clonar Proyecto**](#clonar-proyecto)
  - [**Participantes**](#participantes)

---

## **Tecnologías**

Este proyecto utiliza las siguientes tecnologías:

- **[Spring Boot](https://spring.io/projects/spring-boot):** Framework para desarrollar aplicaciones basadas en Java que simplifica la creación de APIs y servicios de backend.
- **[JUnit](https://junit.org/junit5/):** Framework de pruebas para Java, utilizado para garantizar la calidad del código mediante tests unitarios.
- **[Mockito](https://site.mockito.org/):** Biblioteca de simulación para Java que facilita la creación de pruebas unitarias al permitir mockear dependencias.

---

## **Estructura del Proyecto**

La estructura de carpetas del proyecto es la siguiente:

```plaintext
- src
  - main
    - java
      - tpi
        - backend
          - e-commerce
            - controllers/        # Controladores para manejar las solicitudes HTTP
            - dto/                # Objetos de transferencia de datos utilizados entre capas
            - enums/              # Enumeraciones utilizadas en el sistema
            - mapper/             # Mapeo entre entidades y DTOs
            - middlewares/        # Middleware para la gestión de autenticación y autorización
            - models/             # Modelos de datos utilizados en la aplicación
            - repositories/       # Interfaces para interactuar con la base de datos
            - services/           # Lógica de negocio de la aplicación
            - validation/         # Validaciones personalizadas
            - EcommerceApplication.java  # Punto de entrada principal de la aplicación backend
    - resources/                  # Configuraciones y recursos de la aplicación (application.properties, etc.)
  - test                          # Pruebas unitarias y de integración del backend
```

---

## **Clonar Proyecto para testing**

Sigue estos pasos para clonar y ejecutar el proyecto en tu máquina local:

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/usuario/megastore-backend.git
   cd megastore-backend
   ```

2. **Configurar la base de datos**
   Asegúrate de tener una base de datos compatible configurada. Modifica el archivo `application.properties` en el directorio `src/main/resources` para ajustar las configuraciones de conexión.

3. **Construir el proyecto**
   Asegúrate de tener [Maven](https://maven.apache.org/) instalado. Luego, ejecuta:
   ```bash
   mvn clean install
   ```

4. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

5. **Probar la API**
   Accede a la documentación de la API (si está habilitada) o utiliza herramientas como Postman para interactuar con los endpoints disponibles.

---

## **Participantes**

El equipo de desarrollo está compuesto por:

- **Ariel Cometto** - [arielcometto@gmail.com](mailto:arielcometto@gmail.com)
- **Felipe Del Zoppo** - [felipedelzoppo@gmail.com](mailto:felipedelzoppo@gmail.com)
- **Julian Fassetta** - [julianfassetta@gmail.com](mailto:julianfassetta@gmail.com)
- **Manuel Namuncurá** - [mnamun94@gmail.com](mailto:mnamun94@gmail.com)
- **Ana Elisa Pizzi** - [anapizzi02@gmail.com](mailto:anapizzi02@gmail.com)
- **Marisol Rojas López** - [mrojaslopez03@gmail.com](mailto:mrojaslopez03@gmail.com)
- **Lohana Rumis** - [rumislohana@gmail.com](mailto:rumislohana@gmail.com)
- **Julián López Salvucci** - [julianls783@gmail.com](mailto:julianls783@gmail.com)
