package grupo_8_sc303_mv_proyectono1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuPrincipal extends JFrame {
    private static List<Usuario> usuarios; // Hacer la lista estática

    public MenuPrincipal() {
        // Inicializa la lista de usuarios
        usuarios = new ArrayList<>();
        cargarUsuarios(); // Cargar usuarios predefinidos

        setTitle("Menú Principal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear un panel para contener el logo pequeño y la leyenda
        JPanel panelSuperior = new JPanel(new BorderLayout());

        // Logo pequeño en la esquina superior derecha
        ImageIcon logoIcon = new ImageIcon(getClass().getClassLoader().getResource("grupo_8_sc303_mv_proyectono1/Imagenes/Logo.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setHorizontalAlignment(JLabel.RIGHT);
        panelSuperior.add(logoLabel, BorderLayout.EAST);

        // Leyenda en la parte superior
        JLabel leyenda = new JLabel("Favor indique cómo desea ingresar", JLabel.CENTER);
        leyenda.setFont(new Font("Arial", Font.BOLD, 20));
        panelSuperior.add(leyenda, BorderLayout.CENTER);

        // Añadir el panel superior a la ventana
        add(panelSuperior, BorderLayout.NORTH);

        // Panel con las imágenes y opciones de ingreso
        JPanel panelOpciones = new JPanel(new GridLayout(2, 2, 10, 10));

        panelOpciones.add(crearOpcionIngreso("Administrador", "grupo_8_sc303_mv_proyectono1/Imagenes/Administrador.png", e -> abrirLogin("Administrador")));
        panelOpciones.add(crearOpcionIngreso("Mesero", "grupo_8_sc303_mv_proyectono1/Imagenes/Mesero.png", e -> abrirLogin("Mesero")));
        panelOpciones.add(crearOpcionIngreso("Cajero", "grupo_8_sc303_mv_proyectono1/Imagenes/Cajero.png", e -> abrirLogin("Cajero")));
        panelOpciones.add(crearOpcionIngreso("Cocinero", "grupo_8_sc303_mv_proyectono1/Imagenes/Cocinero.png", e -> abrirLogin("Cocinero")));

        // Añadir el panel de opciones al centro
        add(panelOpciones, BorderLayout.CENTER);

        // Botón para salir del sistema
        JButton btnSalir = new JButton("Salir del Sistema");
        btnSalir.setPreferredSize(new Dimension(200, 60));
        btnSalir.setBackground(Color.GRAY);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 20));
        btnSalir.setOpaque(true);
        btnSalir.setBorderPainted(false);
        btnSalir.addActionListener(e -> salirDelSistema());

        // Añadir el botón en la parte inferior
        add(btnSalir, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel crearOpcionIngreso(String nombre, String rutaImagen, java.awt.event.ActionListener action) {
        JPanel panelOpcion = new JPanel(new BorderLayout());

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
        if (icon.getIconWidth() == -1) {
            System.err.println("No se pudo cargar la imagen: " + rutaImagen);
            icon = new ImageIcon(new byte[0]);
        } else {
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        }

        JLabel labelImagen = new JLabel(icon);
        labelImagen.setHorizontalAlignment(JLabel.CENTER);

        JLabel labelNombre = new JLabel(nombre, JLabel.CENTER);
        labelNombre.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton btnSeleccionar = new JButton("Ingresar");
        btnSeleccionar.addActionListener(action);

        panelOpcion.add(labelImagen, BorderLayout.CENTER);
        panelOpcion.add(labelNombre, BorderLayout.NORTH); // Mantener el nombre arriba
        panelOpcion.add(btnSeleccionar, BorderLayout.SOUTH); // Colocar el botón abajo

        return panelOpcion;
    }

    private void abrirLogin(String rol) {
        // Pedir las credenciales del usuario según el rol
        String username = JOptionPane.showInputDialog(this, "Ingrese su usuario para " + rol + ":", "Login " + rol, JOptionPane.PLAIN_MESSAGE);
        String password = JOptionPane.showInputDialog(this, "Ingrese su contraseña:", "Login " + rol, JOptionPane.PLAIN_MESSAGE);

        // Verificar credenciales
        if (validarCredenciales(username, password)) {
            // Abrir la interfaz correspondiente según el rol
            switch (rol) {
                case "Mesero":
                    new POSInterface(); // Abrir el menú POS
                    break;
                case "Cocinero":
                    new CocineroInterface(); // Abrir la interfaz de cocinero
                    break;
                case "Cajero":
                    new CajeroInterface(); // Abrir la interfaz del cajero
                    break;
                case "Administrador":
                    new AdministradorInterface(); // Abrir la interfaz del administrador
                    break;
            }
            this.dispose(); // Cerrar el menú principal
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Intente de nuevo.", "Error de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salirDelSistema() {
        ImageIcon logoIcon = new ImageIcon(getClass().getClassLoader().getResource("grupo_8_sc303_mv_proyectono1/Imagenes/Logo.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        JOptionPane.showMessageDialog(this, logoLabel, "Saliendo del sistema", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private boolean validarCredenciales(String nombreUsuario, String contraseña) {
        for (Usuario usuario : DataManager.getUsuarios()) {
            if (usuario.verificarCredenciales(nombreUsuario, contraseña)) {
                return true;
            }
        }
        return false; // Retornar falso si no coincide
    }

    private void cargarUsuarios() {
        // Cargar usuarios de ejemplo con sede "San José"
        usuarios.add(new Usuario(1, "mesero", "meseropass", "Mesero", "San José"));
        usuarios.add(new Usuario(2, "admin", "adminpass", "Administrador", "San José"));
        usuarios.add(new Usuario(3, "cajero", "cajeropass", "Cajero", "San José"));
        usuarios.add(new Usuario(4, "cocinero", "cocineropass", "Cocinero", "San José"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPrincipal::new);
    }
}