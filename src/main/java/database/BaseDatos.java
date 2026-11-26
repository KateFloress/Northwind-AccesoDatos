package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {
    private static Connection connection;

    private BaseDatos() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            createNewConnection();
        }
        return connection;
    }

    private static void createNewConnection() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + SchemaDB.DB_NAME,
                    "root",
                    ""
            );
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
