package grupo_8_sc303_mv_proyectono1;

public class Combo {
    private int idCombo;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagePath;
    private String nombreCategoria; // Agregado para almacenar la categoría

    public Combo(int idCombo, String nombre, String descripcion, double precio, String imagePath, String nombreCategoria) {
        this.idCombo = idCombo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagePath = imagePath;
        this.nombreCategoria = nombreCategoria; // Inicializar el campo categoría
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getNombreCategoria() {
        return nombreCategoria; // Método para obtener la categoría
    }
}