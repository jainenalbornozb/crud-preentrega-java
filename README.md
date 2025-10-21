# CRUD Preentrega Java

Aplicación desarrollada en Java que implementa operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar datos en una base de datos.

### Descripción

Este proyecto es una implementación básica de un sistema CRUD utilizando Java. Está diseñado para facilitar la comprensión y aplicación de operaciones fundamentales en la gestión de datos.

### Requisitos 🚀

JDK 8 o superior

IDE como Eclipse o IntelliJ IDEA

Base de datos MySQL o H2

Conector JDBC adecuado para la base de datos elegida

### Instalación📦

Clona este repositorio en tu máquina local:

git clone https://github.com/jainenalbornozb/crud-preentrega-java.git


Importa el proyecto en tu IDE favorito.

Configura la conexión a la base de datos en el archivo de configuración correspondiente.

Ejecuta la clase principal para iniciar la aplicación.

### Uso 🛠️ 

La aplicación permite realizar las siguientes operaciones sobre los datos:

Crear: Añadir nuevos registros a la base de datos.

Leer: Consultar registros existentes.

Actualizar: Modificar registros existentes.

Eliminar: Eliminar registros de la base de datos.

Cada operación está implementada en clases separadas para mantener una estructura organizada y modular.

###  Estructura del Proyecto 🔍
src/
├── aplicacion/   # Clase principal y controladores
├── excepciones/  # Manejo de excepciones personalizadas
├── modelo/       # Clases de entidad y objetos del dominio
└── servicio/     # Lógica de negocio y operaciones CRUD

