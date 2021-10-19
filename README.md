Se trata de un proyecto que se identifica como un API REST de control horario de empleados y sus datos
laborales, e intercambia dicha información en formato JSON con el cliente que la consume, que puede
ser desde otras APIs hasta clientes móvil, web (Como será en este caso).

El proyecto se divide en 2 grandes bloques.

El Frontend: Que se encarga de la parte visual del proyecto. Utilizando el framework Angular y Node.js
para el desarrollo de la lógica frontal y enrutamiento y Bootstrap para la creación de componentes y la
aplicación de estilos.

El Backend: Que engloba toda la lógica del proyecto. Desarrollado en lenguaje Java, tiene una estructura
cliente-servidor siguiendo una arquitectura de capas con tareas bien diferenciadas, como son
presentación, manipulación y procesamiento de datos o la gestión de repositorios (persistencia) en SQL
contra una base de datos H2. Todo ello creado sobre la base estructural que proporciona Maven, el
framework Spring y herramientas propias de éste framework como Spring Boot y Spring Data JPA. Se
utiliza además, Hibernate para el modelo autogenerado en la base de datos y Tomcat como contenedor
web para el despliegue de la misma.