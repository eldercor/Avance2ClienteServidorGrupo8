package grupo_8_sc303_mv_proyectono1;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<CategoriaProducto> categoriasProductos = new ArrayList<>();
    private static List<CategoriaCombo> categoriasCombos = new ArrayList<>();

    static {
        cargarUsuarios();
        cargarCategoriasDeProductos();
        cargarCategoriasDeCombos();
    }

    private static void cargarUsuarios() {
        usuarios.add(new Usuario(1, "admin", "adminpass", "Administrador", "San José"));
        usuarios.add(new Usuario(2, "mesero", "meseropass", "Mesero", "San José"));
        usuarios.add(new Usuario(3, "cajero", "cajeropass", "Cajero", "San José"));
        usuarios.add(new Usuario(4, "cocinero", "cocineropass", "Cocinero", "San José"));
    }

    private static void cargarCategoriasDeProductos() {
        CategoriaProducto bebidas = new CategoriaProducto("Bebidas");
        bebidas.agregarProducto(new Producto(1, "Jugo Natural", 600, "/grupo_8_sc303_mv_proyectono1/Imagenes/Jugo natural.png", "Jugo natural de frutas", "Bebidas"));
        bebidas.agregarProducto(new Producto(2, "Gaseosa", 800, "/grupo_8_sc303_mv_proyectono1/Imagenes/Gaseosa.png", "Gaseosa refrescante", "Bebidas"));
        bebidas.agregarProducto(new Producto(3, "Café", 500, "/grupo_8_sc303_mv_proyectono1/Imagenes/Café.png", "Café caliente y aromático", "Bebidas"));
        bebidas.agregarProducto(new Producto(4, "Agua", 500, "/grupo_8_sc303_mv_proyectono1/Imagenes/Agua.png", "Agua mineral fresca", "Bebidas"));
        bebidas.agregarProducto(new Producto(5, "Cerveza", 1500, "/grupo_8_sc303_mv_proyectono1/Imagenes/Cerveza.png", "Cerveza fría y refrescante", "Bebidas"));

        CategoriaProducto comidas = new CategoriaProducto("Comidas");
        comidas.agregarProducto(new Producto(6, "Hamburguesa", 2900, "/grupo_8_sc303_mv_proyectono1/Imagenes/Hamburguesa.png", "Deliciosa hamburguesa de carne", "Comidas"));
        comidas.agregarProducto(new Producto(7, "Pizza", 7500, "/grupo_8_sc303_mv_proyectono1/Imagenes/Pizza.png", "Pizza con pepperoni y queso", "Comidas"));
        comidas.agregarProducto(new Producto(8, "Ensalada", 3500, "/grupo_8_sc303_mv_proyectono1/Imagenes/Ensalada.png", "Ensalada fresca con aderezo", "Comidas"));
        comidas.agregarProducto(new Producto(9, "Tacos", 3000, "/grupo_8_sc303_mv_proyectono1/Imagenes/Tacos.png", "Tacos tradicionales con salsa", "Comidas"));
        comidas.agregarProducto(new Producto(10, "Papas Fritas", 1000, "/grupo_8_sc303_mv_proyectono1/Imagenes/Papas fritas.png", "Papas fritas crujientes", "Comidas"));

        CategoriaProducto postres = new CategoriaProducto("Postres");
        postres.agregarProducto(new Producto(11, "Helado", 1500, "/grupo_8_sc303_mv_proyectono1/Imagenes/Helado.png", "Helado cremoso y delicioso", "Postres"));
        postres.agregarProducto(new Producto(12, "Pastel", 1500, "/grupo_8_sc303_mv_proyectono1/Imagenes/Pastel.png", "Pastel de chocolate suave", "Postres"));

        categoriasProductos.add(bebidas);
        categoriasProductos.add(comidas);
        categoriasProductos.add(postres);
    }

    private static void cargarCategoriasDeCombos() {
        CategoriaCombo combosGrandes = new CategoriaCombo("Combos Grandes");
        combosGrandes.agregarCombo(new Combo(1, "Combo Fiesta", "Incluye Pizza, Soda y Helado.", 8000, "/grupo_8_sc303_mv_proyectono1/Imagenes/Combo Fiesta.png", "Combos Grandes"));
        combosGrandes.agregarCombo(new Combo(2, "Combo Cena", "Incluye Pizza, Alitas y Soda.", 9000, "/grupo_8_sc303_mv_proyectono1/Imagenes/Combo Cena.png", "Combos Grandes"));

        CategoriaCombo combosPequenos = new CategoriaCombo("Combos Pequeños");
        combosPequenos.agregarCombo(new Combo(3, "Combo Almuerzo", "Incluye Hamburguesa, Papas Fritas y Jugo Natural.", 6000, "/grupo_8_sc303_mv_proyectono1/Imagenes/Combo Almuerzo.png", "Combos Pequeños"));
        combosPequenos.agregarCombo(new Combo(4, "Combo Snack", "Incluye Sándwich, Café y Pastel.", 5000, "/grupo_8_sc303_mv_proyectono1/Imagenes/Combo Snack.png", "Combos Pequeños"));

        categoriasCombos.add(combosGrandes);
        categoriasCombos.add(combosPequenos);

        CategoriaCombo combosSaludables = new CategoriaCombo("Combos Saludables");
        combosSaludables.agregarCombo(new Combo(5, "Combo Saludable", "Incluye Ensalada, Jugo Natural y Agua.", 4500, "/grupo_8_sc303_mv_proyectono1/Imagenes/Combo Saludable.png", "Combos Saludables"));
        categoriasCombos.add(combosSaludables);

        CategoriaCombo combosEspeciales = new CategoriaCombo("Combos Especiales");
        combosEspeciales.agregarCombo(new Combo(6, "Combo Mexicano", "Incluye Tacos, Nachos y Cerveza.", 7000, "/grupo_8_sc303_mv_proyectono1/Imagenes/Combo Mexicano.png", "Combos Especiales"));
        combosEspeciales.agregarCombo(new Combo(7, "Combo Picante", "Incluye Alitas, Nachos y Cerveza.", 7500, "/grupo_8_sc303_mv_proyectono1/Imagenes/Combo Picante.png", "Combos Especiales"));
        categoriasCombos.add(combosEspeciales);
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static List<CategoriaProducto> getCategoriasProductos() {
        return categoriasProductos;
    }

    public static List<CategoriaCombo> getCategoriasCombos() {
        return categoriasCombos;
    }

    public static List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();
        for (CategoriaProducto categoria : categoriasProductos) {
            productos.addAll(categoria.getProductos());
        }
        return productos;
    }

    public static List<Combo> getCombos() {
        List<Combo> combos = new ArrayList<>();
        for (CategoriaCombo categoria : categoriasCombos) {
            combos.addAll(categoria.getCombos());
        }
        return combos;
    }

    public static void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static void addProducto(Producto producto) {
        // Añadir producto a la categoría correspondiente
        for (CategoriaProducto categoria : categoriasProductos) {
            if (categoria.getNombreCategoria().equals(producto.getNombreCategoria())) {
                categoria.agregarProducto(producto);
                break;
            }
        }
    }

    public static void addCombo(Combo combo) {
        // Añadir combo a la lista de combos
        for (CategoriaCombo categoria : categoriasCombos) {
            if (categoria.getNombreCategoria().equals(combo.getNombreCategoria())) {
                categoria.agregarCombo(combo);
                break;
            }
        }
    }
}