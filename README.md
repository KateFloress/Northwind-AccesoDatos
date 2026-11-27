# 📦 Gestión de Almacén y Consumo de API REST (Northwind)

Aplicación desarrollada en **Java** con **Maven** que conecta con una base de datos **MySQL** mediante **JDBC**, consume datos desde una API externa en formato **JSON** ([DummyJSON](https://dummyjson.com)) y ejecuta operaciones de inserción y consulta relacional. Proyecto realizado dentro del módulo de **Acceso a Datos** en **DAM**.

## 🚀 Funcionalidades Implementadas

* 🌐 **Importación desde API JSON:** Descarga el catálogo de productos mediante `HttpClient`, los parsea a objetos `Producto` usando `org.json` y los inserta en MySQL con `PreparedStatement`.
* 👥 **Gestión de Empleados y Pedidos:** Inserta registros en las tablas `empleados` y `pedidos` mediante `Statement` respetando las relaciones e integridad referencial.
* ⭐ **Filtrado Automático de Favoritos:** Selecciona los productos con precio superior a `1000 €` y los almacena en la tabla `productos_fav`.
* 🔍 **Consultas Relacionales por Consola:** Muestra el listado completo de productos, artículos con precio inferior a `600 €` y productos favoritos cruzando tablas mediante `INNER JOIN`.

## 📂 Estructura del Proyecto

* **`model` (`Producto.java`):** Clase modelo que encapsula los atributos de cada artículo (`id`, `nombre`, `descripcion`, `cantidad`, `precio`).
* **`database` (`SchemaDB.java`, `BaseDatos.java`):**
  * `SchemaDB`: Interfaz que centraliza los nombres de la base de datos, tablas y columnas como constantes.
  * `BaseDatos`: Implementa el **patrón Singleton** para gestionar una única instancia de conexión (`Connection`) a MySQL.
* **`controller` (`Almacen.java`):** Controlador encargado del consumo de la API, el volcado de datos y las consultas SQL con `PreparedStatement`, `Statement` y `ResultSet`.
* **`Main.java`:** Punto de entrada que orquesta la ejecución secuencial de todas las operaciones.

## 🗄️ Base de Datos (`almacen` / Northwind)

El proyecto trabaja sobre cuatro tablas relacionales en MySQL:
* **`productos`:** Catálogo principal importado desde la API (`id`, `nombre`, `descripcion`, `cantidad`, `precio`).
* **`empleados`:** Registro del personal del almacén (`id`, `nombre`, `apellidos`, `correo`).
* **`pedidos`:** Pedidos asociados a productos mediante clave foránea (`id`, `id_producto`, `descripcion`, `precio_total`).
* **`productos_fav`:** Selección de productos destacados con precio superior a `1000 €` (`id`, `id_producto`).

## 🛠️ Tecnologías Utilizadas

* Java
* Maven
* JDBC (MySQL Connector/J)
* API REST & `org.json`
* MySQL & phpMyAdmin
* IntelliJ IDEA

