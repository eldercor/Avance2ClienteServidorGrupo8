package grupo_8_sc303_mv_proyectono1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdministradorInterface extends JFrame {

    public AdministradorInterface() {
        super("Panel de Administrador");

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Logo del Administrador
        ImageIcon adminIcon = new ImageIcon(getClass().getResource("/grupo_8_sc303_mv_proyectono1/Imagenes/Administrador.png"));
        Image imgAdmin = adminIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Redimensionar la imagen
        JLabel logoLabel = new JLabel(new ImageIcon(imgAdmin));
        logoLabel.setPreferredSize(new Dimension(60, 60));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(logoLabel, BorderLayout.NORTH);

        // Botón para abrir el submenú
        JButton btnAgregar = crearBoton("Agregar", this::abrirSubmenu);
        add(btnAgregar, BorderLayout.CENTER);

        // Botón de regresar al menú principal
        JButton btnRegresar = crearBoton("Regresar al Menú Principal", e -> {
            this.dispose();
            new MenuPrincipal(); // Vuelve al menú principal
        });
        add(btnRegresar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton crearBoton(String texto, java.awt.event.ActionListener action) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(100, 40)); // Tamaño del botón
        boton.setBackground(new Color(211, 211, 211)); // Color de fondo gris claro
        boton.setForeground(Color.BLACK); // Color del texto en negro
        boton.setFont(new Font("Arial", Font.BOLD, 14)); // Cambiar la fuente
        boton.setFocusPainted(false); // Quitar el borde de enfoque
        boton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Borde gris

        // Agregar efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(200, 200, 200)); // Color al pasar el mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(211, 211, 211)); // Color original
            }
        });

        boton.addActionListener(action); // Asignar el ActionListener
        return boton;
    }

    private void abrirSubmenu(ActionEvent e) {
        // Crear un nuevo JDialog para el submenú
        JDialog submenu = new JDialog(this, "Submenú", true);
        submenu.setSize(400, 300);
        submenu.setLayout(new BorderLayout());

        // Panel con las opciones
        JPanel panelOpciones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelOpciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir borde
        panelOpciones.add(crearBoton("Agregar Usuario", e1 -> agregarUsuario()));
        panelOpciones.add(crearBoton("Agregar Producto", e1 -> agregarProducto()));
        panelOpciones.add(crearBoton("Agregar Combo", e1 -> agregarCombo()));
        submenu.add(panelOpciones, BorderLayout.CENTER);

        // Botón de regresar
        JButton btnRegresar = crearBoton("Regresar", e1 -> submenu.dispose());
        submenu.add(btnRegresar, BorderLayout.SOUTH);

        submenu.setLocationRelativeTo(this);
        submenu.setVisible(true);
    }

    private void agregarUsuario() {
        JTextField nombreUsuario = new JTextField();
        JTextField contraseñaUsuario = new JTextField();
        String[] sedes = {"San José", "Alajuela", "Cartago", "Heredia", "Guanacaste", "Puntarenas", "Limón"};
        JComboBox<String> comboSedes = new JComboBox<>(sedes);
        String[] roles = {"Cocinero", "Cajero", "Mesero", "Administrador"};
        JComboBox<String> comboRoles = new JComboBox<>(roles);

        Object[] message = {
            "Sede:", comboSedes,
            "Nombre de Usuario:", nombreUsuario,
            "Contraseña:", contraseñaUsuario,
            "Rol:", comboRoles
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            // Validar que los campos no estén vacíos
            if (nombreUsuario.getText().trim().isEmpty() || contraseñaUsuario.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Salir del método si hay campos vacíos
            }

            String sede = (String) comboSedes.getSelectedItem();
            Usuario nuevoUsuario = new Usuario(DataManager.getUsuarios().size() + 1, nombreUsuario.getText(), contraseñaUsuario.getText(), (String) comboRoles.getSelectedItem(), sede);
            DataManager.addUsuario(nuevoUsuario); // Agregar el nuevo usuario a la lista central
            JOptionPane.showMessageDialog(this, "Usuario creado exitosamente. Sede: " + sede, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void agregarProducto() {
        JTextField nombreProducto = new JTextField();
        JTextField precioProducto = new JTextField();
        String[] categorias = {"Bebidas", "Comidas", "Postres"};
        JComboBox<String> comboCategorias = new JComboBox<>(categorias);

        Object[] message = {
            "Categoría:", comboCategorias,
            "Nombre del Producto:", nombreProducto,
            "Precio:", precioProducto
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Agregar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            // Validar que los campos no estén vacíos
            if (nombreProducto.getText().trim().isEmpty() || precioProducto.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Salir del método si hay campos vacíos
            }

            String imagePath = "/grupo_8_sc303_mv_proyectono1/Imagenes/Logo.png"; // Imagen predeterminada
            String categoriaSeleccionada = (String) comboCategorias.getSelectedItem(); // Obtener categoría seleccionada
            Producto nuevoProducto = new Producto(DataManager.getProductos().size() + 1, nombreProducto.getText(), Double.parseDouble(precioProducto.getText()), imagePath, "", categoriaSeleccionada);
            DataManager.addProducto(nuevoProducto); // Agregar el nuevo producto a la lista central
            JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void agregarCombo() {
        JTextField nombreCombo = new JTextField();
        JTextField precioCombo = new JTextField();
        String[] categorias = {"Combos Grandes", "Combos Pequeños", "Combos Saludables"};
        JComboBox<String> comboCategorias = new JComboBox<>(categorias);

        Object[] message = {
            "Categoría:", comboCategorias,
            "Nombre del Combo:", nombreCombo,
            "Precio:", precioCombo
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Agregar Combo", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            // Validar que los campos no estén vacíos
            if (nombreCombo.getText().trim().isEmpty() || precioCombo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; // Salir del método si hay campos vacíos
            }

            String imagePath = "/grupo_8_sc303_mv_proyectono1/Imagenes/Logo.png"; // Imagen predeterminada
            String categoriaSeleccionada = (String) comboCategorias.getSelectedItem(); // Obtener categoría seleccionada
            Combo nuevoCombo = new Combo(DataManager.getCombos().size() + 1, nombreCombo.getText(), "", Double.parseDouble(precioCombo.getText()), imagePath, categoriaSeleccionada);
            DataManager.addCombo(nuevoCombo); // Agregar el nuevo combo a la lista central
            JOptionPane.showMessageDialog(this, "Combo agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdministradorInterface::new);
    }
}