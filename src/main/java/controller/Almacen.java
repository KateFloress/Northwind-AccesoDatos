package controller;

import database.BaseDatos;
import database.SchemaDB;
import model.Producto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Almacen {

    // 1. Saca los datos del JSON y los mete en un ArrayList
    public ArrayList<Producto> obtenerProductosJSON() throws Exception {
        ArrayList<Producto> listaProductos = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dummyjson.com/products"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonPrincipal = new JSONObject(response.body());
        JSONArray arrayJson = jsonPrincipal.getJSONArray("products");

        for (int i = 0; i < arrayJson.length(); i++) {
            JSONObject item = arrayJson.getJSONObject(i);

            Producto p = new Producto(
                    item.getLong("id"),
                    item.getString("title"),
                    item.getString("description"),
                    item.getInt("stock"),
                    item.getDouble("price")
            );
            listaProductos.add(p);
        }
        return listaProductos;
    }

    // 2. Mete el ArrayList en la BD usando PreparedStatement
    public void guardarProductosEnBD(ArrayList<Producto> lista) throws SQLException {
        String sql = String.format("INSERT IGNORE INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
                SchemaDB.TAB_PROD,
                SchemaDB.COL_PROD_ID,
                SchemaDB.COL_PROD_NOMBRE,
                SchemaDB.COL_PROD_DESC,
                SchemaDB.COL_PROD_CANT,
                SchemaDB.COL_PROD_PRECIO);

        Connection conn = BaseDatos.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        for (Producto p : lista) {
            ps.setLong(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setInt(4, p.getCantidad());
            ps.setDouble(5, p.getPrecio());
            ps.executeUpdate();
        }
        System.out.println("Se han insertado " + lista.size() + " productos en la BD.");
    }

    // 3. Agrega empleados y pedidos mediante Statement
    public void insertarEmpleadosYPedidos() throws SQLException {
        Connection conn = BaseDatos.getConnection();
        Statement st = conn.createStatement();

        st.executeUpdate("INSERT INTO " + SchemaDB.TAB_EMP + " (nombre, puesto) VALUES ('Ana García', 'Encargada')");
        st.executeUpdate("INSERT INTO " + SchemaDB.TAB_EMP + " (nombre, puesto) VALUES ('Luis Pérez', 'Comercial')");

        st.executeUpdate("INSERT INTO " + SchemaDB.TAB_PED + " (id_producto, id_empleado, cantidad) VALUES (1, 1, 5)");
        st.executeUpdate("INSERT INTO " + SchemaDB.TAB_PED + " (id_producto, id_empleado, cantidad) VALUES (2, 2, 10)");

        System.out.println("Empleados y pedidos insertados.");
    }

    // 4. Inserta en productos_fav los productos con precio > 1000€
    public void insertarFavoritosCaros() throws SQLException {
        Connection conn = BaseDatos.getConnection();
        Statement st = conn.createStatement();

        String sql = String.format("INSERT INTO %s (%s) SELECT %s FROM %s WHERE %s > 1000",
                SchemaDB.TAB_FAV, SchemaDB.COL_FAV_ID_PROD,
                SchemaDB.COL_PROD_ID, SchemaDB.TAB_PROD, SchemaDB.COL_PROD_PRECIO);

        int filas = st.executeUpdate(sql);
        System.out.println("Se han añadido " + filas + " productos a favoritos (> 1000€).");
    }

    // 5. Muestra todos los productos
    public void mostrarTodosLosProductos() throws SQLException {
        System.out.println("\n--- TODOS LOS PRODUCTOS ---");
        Statement st = BaseDatos.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM " + SchemaDB.TAB_PROD);

        while (rs.next()) {
            System.out.printf("ID: %d | %s | Stock: %d | Precio: %.2f€%n",
                    rs.getLong(SchemaDB.COL_PROD_ID),
                    rs.getString(SchemaDB.COL_PROD_NOMBRE),
                    rs.getInt(SchemaDB.COL_PROD_CANT),
                    rs.getDouble(SchemaDB.COL_PROD_PRECIO));
        }
    }

    // 6. Muestra productos con precio inferior a 600€
    public void mostrarProductosBaratos() throws SQLException {
        System.out.println("\n--- PRODUCTOS INFERIORES A 600€ ---");
        Statement st = BaseDatos.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM " + SchemaDB.TAB_PROD + " WHERE " + SchemaDB.COL_PROD_PRECIO + " < 600");

        while (rs.next()) {
            System.out.printf("ID: %d | %s | Precio: %.2f€%n",
                    rs.getLong(SchemaDB.COL_PROD_ID),
                    rs.getString(SchemaDB.COL_PROD_NOMBRE),
                    rs.getDouble(SchemaDB.COL_PROD_PRECIO));
        }
    }

    // 7. Muestra productos favoritos con sus datos
    public void mostrarFavoritos() throws SQLException {
        System.out.println("\n--- PRODUCTOS FAVORITOS ---");
        Statement st = BaseDatos.getConnection().createStatement();
        String sql = String.format("SELECT p.* FROM %s p INNER JOIN %s f ON p.%s = f.%s",
                SchemaDB.TAB_PROD, SchemaDB.TAB_FAV, SchemaDB.COL_PROD_ID, SchemaDB.COL_FAV_ID_PROD);

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.printf("FAV -> ID: %d | %s | Precio: %.2f€%n",
                    rs.getLong(SchemaDB.COL_PROD_ID),
                    rs.getString(SchemaDB.COL_PROD_NOMBRE),
                    rs.getDouble(SchemaDB.COL_PROD_PRECIO));
        }
    }
}


