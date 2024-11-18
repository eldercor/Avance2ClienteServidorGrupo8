package grupo_8_sc303_mv_proyectono1;

public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private String imagePath;
    private String descripcion;
    private String nombreCategoria;

    public Producto(int idProducto, String nombre, double precio, String imagePath, String descripcion, String nombreCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.imagePath = imagePath;
        this.descripcion = descripcion;
        this.nombreCategoria = nombreCategoria; // Inicializar el campo categoría
    }

    // Métodos getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCategoria() {
        return nombreCategoria; // Método para obtener el nombre de la categoría
    }

    @Override
    public String toString() {
        return nombre + " - ₡" + String.format("%.2f", precio); // Representación en cadena
    }
}