package grupo_8_sc303_mv_proyectono1;

import java.util.ArrayList;
import java.util.List;

public class CategoriaProducto {
    private String nombreCategoria; // Nombre de la categoría de productos
    private List<Producto> productos; // Lista de productos pertenecientes a esta categoría

    // Constructor que inicializa el nombre de la categoría y crea una lista vacía de productos
    public CategoriaProducto(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.productos = new ArrayList<>();
    }

    // Getter para obtener el nombre de la categoría
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    // Método para agregar un producto a la lista de productos de esta categoría
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Getter para obtener la lista de productos en esta categoría
    public List<Producto> getProductos() {
        return productos;
    }
}