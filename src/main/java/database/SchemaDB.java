package database;

public interface SchemaDB {
    String DB_NAME = "almacen";

    // Tabla Productos
    String TAB_PROD = "productos";
    String COL_PROD_ID = "id";
    String COL_PROD_NOMBRE = "nombre";
    String COL_PROD_DESC = "descripcion";
    String COL_PROD_CANT = "cantidad";
    String COL_PROD_PRECIO = "precio";

    // Tabla Productos Favoritos
    String TAB_FAV = "productos_fav";
    String COL_FAV_ID = "id";
    String COL_FAV_ID_PROD = "id_producto";

    // Tabla Empleados
    String TAB_EMP = "empleados";
    String COL_EMP_ID = "id";
    String COL_EMP_NOMBRE = "nombre";
    String COL_EMP_PUESTO = "puesto";

    // Tabla Pedidos
    String TAB_PED = "pedidos";
    String COL_PED_ID = "id";
    String COL_PED_ID_PROD = "id_producto";
    String COL_PED_ID_EMP = "id_empleado";
    String COL_PED_CANT = "cantidad";
}
