import controller.Almacen;
import model.Producto;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Almacen dao = new Almacen();

        try {
            ArrayList<Producto> listaProductos = dao.obtenerProductosJSON();
            dao.guardarProductosEnBD(listaProductos);
            dao.insertarEmpleadosYPedidos();
            dao.mostrarTodosLosProductos();
            dao.mostrarProductosBaratos();
            dao.insertarFavoritosCaros();
            dao.mostrarFavoritos();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
