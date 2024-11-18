package grupo_8_sc303_mv_proyectono1;

import java.util.ArrayList;

public class Orden {
    private int idOrden; // Identificador único de la orden
    private String clienteNombre; // Nombre del cliente
    private String mesa; // Número de mesa
    private boolean paraLlevar; // Indica si es para llevar
    private String sede; // Sede donde se realiza la orden
    private ArrayList<Producto> productos; // Lista de productos en la orden
    private ArrayList<Combo> combos; // Lista de combos en la orden
    private boolean finalizada; // Indica si la orden está finalizada

    // Constructor para inicializar la orden con los parámetros dados
    public Orden(int idOrden, String clienteNombre, String mesa, boolean paraLlevar, String sede) {
        this.idOrden = idOrden; // Asignar ID de la orden
        this.clienteNombre = clienteNombre; // Asignar nombre del cliente
        this.mesa = mesa; // Asignar número de mesa
        this.paraLlevar = paraLlevar; // Asignar tipo de pedido
        this.sede = sede; // Inicializar la sede
        this.productos = new ArrayList<>(); // Inicializar la lista de productos
        this.combos = new ArrayList<>(); // Inicializar la lista de combos
        this.finalizada = false; // Inicializar estado de finalización
    }

    // Métodos getters
    public int getIdOrden() {
        return idOrden; // Retorna el ID de la orden
    }

    public String getClienteNombre() {
        return clienteNombre; // Retorna el nombre del cliente
    }

    public String getMesa() {
        return mesa; // Retorna el número de mesa
    }

    public boolean isParaLlevar() {
        return paraLlevar; // Retorna si es para llevar
    }

    public String getSede() {
        return sede; // Retorna la sede
    }

    public boolean isFinalizada() {
        return finalizada; // Retorna si la orden está finalizada
    }

    // Método para marcar la orden como finalizada
    public void marcarComoFinalizada() {
        this.finalizada = true;
    }

    // Métodos para agregar productos y combos a la orden
    public void agregarProducto(Producto producto) {
        productos.add(producto); // Agrega un producto a la lista
    }

    public void agregarCombo(Combo combo) {
        combos.add(combo); // Agrega un combo a la lista
    }

    // Métodos para obtener las listas de productos y combos
    public ArrayList<Producto> getProductos() {
        return productos; // Retorna la lista de productos
    }

    public ArrayList<Combo> getCombos() {
        return combos; // Retorna la lista de combos
    }

    // Método para calcular el subtotal de la orden
    public double calcularSubtotal() {
        double subtotal = 0; // Inicializa subtotal
        for (Producto producto : productos) {
            subtotal += producto.getPrecio(); // Suma el precio de cada producto
        }
        for (Combo combo : combos) {
            subtotal += combo.getPrecio(); // Suma el precio de cada combo
        }
        return subtotal; // Retorna el subtotal
    }

    // Método para calcular el total de la orden (subtotal + impuestos + servicio)
    public double calcularTotal() {
        double subtotal = calcularSubtotal(); // Calcula el subtotal
        double iva = subtotal * 0.13; // Calcula el IVA (13%)
        double servicio = paraLlevar ? 0 : subtotal * 0.10; // 10% de servicio si es para comer aquí
        return subtotal + iva + servicio; // Retorna el total
    }
}