package grupo_8_sc303_mv_proyectono1;

import javax.swing.*;
import java.awt.*;

public class Bienvenida extends JFrame {

    public Bienvenida() {
        setTitle("Bienvenido");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Imagen de bienvenida (ajustar tamaño al marco)
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/grupo_8_sc303_mv_proyectono1/Imagenes/Logo.png"));
        Image image = logoIcon.getImage(); // Obtiene la imagen del icono
        Image scaledImage = image.getScaledInstance(500, 400, Image.SCALE_SMOOTH); // Escala la imagen
        ImageIcon scaledIcon = new ImageIcon(scaledImage); // Crea un nuevo ImageIcon con la imagen escalada
        JLabel labelLogo = new JLabel(scaledIcon);
        labelLogo.setHorizontalAlignment(JLabel.CENTER);

        // Mensaje de bienvenida
        JLabel mensajeBienvenida = new JLabel("¡Bienvenido a FideBurguesas!");
        mensajeBienvenida.setHorizontalAlignment(JLabel.CENTER);
        mensajeBienvenida.setFont(new Font("Arial", Font.BOLD, 30));
        mensajeBienvenida.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));  // Añadir un espacio alrededor del texto

        // Botón de continuar
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setPreferredSize(new Dimension(200, 60));  // Hacer el botón más grande
        btnContinuar.setBackground(Color.GRAY);  // Fondo de color gris
        btnContinuar.setForeground(Color.WHITE);  // Texto del botón en blanco
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 20));  // Aumentar tamaño del texto en el botón
        btnContinuar.setOpaque(true);  // Hacer que el fondo del botón sea opaco
        btnContinuar.setBorderPainted(false);  // Quitar el borde del botón

        btnContinuar.addActionListener(e -> {
            this.dispose();
            new MenuPrincipal(); // Abrir el menú principal
        });

        // Añadir los componentes a la ventana
        add(mensajeBienvenida, BorderLayout.NORTH);
        add(labelLogo, BorderLayout.CENTER);
        add(btnContinuar, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bienvenida());
    }
}