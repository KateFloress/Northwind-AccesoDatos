# Práctica Acceso a Datos - Almacén (Northwind)

Aplicación desarrollada en **Java** con **Maven** que conecta con una base de datos **MySQL** mediante **JDBC**, consume datos desde una API externa en formato **JSON** y realiza operaciones de inserción y consulta sobre varias tablas relacionadas.

## Estructura del Proyecto

* **`model` (`Producto.java`):** Clase modelo que representa los atributos de cada producto (`id`, `nombre`, `descripcion`, `cantidad`, `precio`).
* **`database` (`SchemaDB.java`, `BaseDatos.java`):** 
  * `SchemaDB`: Interfaz que centraliza los nombres de la base de datos, tablas y columnas como constantes.
  * `BaseDatos`: Implementa el **patrón Singleton** para gestionar una única instancia de conexión (`Connection`) a MySQL.
* **`controller` (`Almacen.java`):** Encargada de descargar y parsear el JSON desde `://dummyjson.com` a un `ArrayList<Producto>`, volcar los datos a MySQL con `PreparedStatement` y ejecutar las inserciones y consultas solicitadas mediante `Statement` y `ResultSet`.
* **`Main.java`:** Punto de entrada que orquesta la ejecución secuencial de todas las operaciones.

## Funcionalidades Implementadas

1. **Importación desde JSON:** Descarga los productos de la API mediante `HttpClient`, los almacena en una lista de objetos y los inserta en la tabla `productos` usando `PreparedStatement`.
2. **Gestión de Empleados y Pedidos:** Inserta registros en `empleados` y `pedidos` mediante `Statement` respetando las claves foráneas.
3. **Filtrado de Favoritos:** Selecciona los productos con precio superior a `1000€` y los guarda automáticamente en la tabla `productos_fav`.
4. **Consultas por Consola:** Muestra el listado completo de productos, los productos con precio inferior a `600€` y los productos favoritos cruzando datos mediante `INNER JOIN`.

## Tecnologías Utilizadas

* Java + Maven
* JDBC (MySQL Connector/J)
* Librería `org.json`
* MySQL / MariaDB
