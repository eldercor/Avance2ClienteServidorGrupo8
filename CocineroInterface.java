package grupo_8_sc303_mv_proyectono1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CocineroInterface extends JFrame {
    private JList<String> listaOrdenes;
    private DefaultListModel<String> modeloOrdenes;
    private ArrayList<Orden> ordenesActivas; // Lista para almacenar las órdenes activas

    public CocineroInterface() {
        super("Órdenes del Cocinero");

        modeloOrdenes = new DefaultListModel<>();
        listaOrdenes = new JList<>(modeloOrdenes);
        JScrollPane scrollPane = new JScrollPane(listaOrdenes);

        // Inicializa la lista de órdenes activas
        ordenesActivas = new ArrayList<>(POSInterface.listaOrdenes); // Copia las órdenes de la lista estática

        // Aumentar el tamaño de la ventana
        setSize(600, 400);

        // Cargar órdenes activas en la lista
        actualizarListaOrdenes();

        // Icono de cocinero
        ImageIcon iconoCocinero = new ImageIcon(getClass().getResource("/grupo_8_sc303_mv_proyectono1/Imagenes/Cocinero.png"));
        Image img = iconoCocinero.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Redimensionar la imagen
        JLabel labelCocinero = new JLabel(new ImageIcon(img));

        // Botón de marcar como finalizado
        JButton btnFinalizar = new JButton("Marcar Como Finalizada");
        btnFinalizar.addActionListener(e -> marcarComoFinalizada());

        // Botón de regresar al menú principal
        JButton btnRegresarMenu = new JButton("Regresar al Menú Principal");
        btnRegresarMenu.addActionListener(e -> {
            this.dispose(); // Cierra la ventana actual
            new MenuPrincipal(); // Vuelve al menú principal
        });

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(labelCocinero, BorderLayout.NORTH);
        leftPanel.add(btnRegresarMenu, BorderLayout.SOUTH);

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnFinalizar, BorderLayout.SOUTH); // Agregar el botón en el panel

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarListaOrdenes() {
        modeloOrdenes.clear(); // Limpiar la lista antes de agregar las órdenes
        for (Orden orden : POSInterface.listaOrdenes) {
            if (!orden.isFinalizada()) { // Solo mostrar órdenes que no han sido finalizadas
                StringBuilder ordenDetalles = new StringBuilder();
                // Formato para la orden
                ordenDetalles.append(String.format("Orden #%d  |  %s\n", 
                    orden.getIdOrden(), 
                    orden.isParaLlevar() ? "Para Llevar" : "Para Comer Aquí"));
                ordenDetalles.append("Sede: ").append(orden.getSede()).append("\n"); // Mostrar la sede
                ordenDetalles.append("\n"); // Línea de separación

                // Desglose de productos
                if (!orden.getProductos().isEmpty()) {
                    ordenDetalles.append("Productos:\n");
                    for (Producto producto : orden.getProductos()) {
                        ordenDetalles.append(" - ").append(producto.getNombre()).append("\n");
                    }
                }

                // Desglose de combos
                if (!orden.getCombos().isEmpty()) {
                    ordenDetalles.append("Combos:\n");
                    for (Combo combo : orden.getCombos()) {
                        ordenDetalles.append(" - ").append(combo.getNombre()).append("\n");
                    }
                }

                // Añadir detalles de la orden al modelo
                modeloOrdenes.addElement(ordenDetalles.toString());
            }
        }
    }

    private void marcarComoFinalizada() {
        int selectedIndex = listaOrdenes.getSelectedIndex();
        if (selectedIndex != -1) {
            // Marcar la orden seleccionada como finalizada
            Orden ordenFinalizada = POSInterface.listaOrdenes.get(selectedIndex);
            ordenFinalizada.marcarComoFinalizada();
            // Actualizar la lista de órdenes en la interfaz
            actualizarListaOrdenes();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una orden para marcar como finalizada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}