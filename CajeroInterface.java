package grupo_8_sc303_mv_proyectono1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CajeroInterface extends JFrame {
    private JList<String> listaOrdenes;
    private DefaultListModel<String> modeloOrdenes;
    private ArrayList<Orden> ordenesPendientes; // Lista de órdenes pendientes
    private int codigoFactura = 1; // Código de factura
    private String nombreEmpresa = "FideBurguesas";
    private String telefonoEmpresa = "2222-22-22";
    private String direccionEmpresa = "Heredia, La Aurora";

    public CajeroInterface() {
        super("Facturación del Cajero");

        modeloOrdenes = new DefaultListModel<>();
        listaOrdenes = new JList<>(modeloOrdenes);
        JScrollPane scrollPane = new JScrollPane(listaOrdenes);

        // Inicializa la lista de órdenes pendientes
        ordenesPendientes = new ArrayList<>(POSInterface.listaOrdenes); // Copia las órdenes de la lista estática

        // Aumentar el tamaño de la ventana
        setSize(700, 500);
        actualizarListaOrdenes();

        // Imagen y botón de regresar
        ImageIcon iconoCajero = new ImageIcon(getClass().getResource("/grupo_8_sc303_mv_proyectono1/Imagenes/Cajero.png"));
        Image imgCajero = iconoCajero.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Redimensionar la imagen
        JLabel labelCajero = new JLabel(new ImageIcon(imgCajero));

        JButton btnRegresarMenu = new JButton("Regresar al Menú Principal");
        btnRegresarMenu.addActionListener(e -> {
            this.dispose();
            new MenuPrincipal(); // Volver al menú principal
        });

        JButton btnFacturar = new JButton("Facturar Orden");
        btnFacturar.addActionListener(e -> facturarOrden());

        JPanel panel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(labelCajero, BorderLayout.NORTH);
        leftPanel.add(btnRegresarMenu, BorderLayout.SOUTH);

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnFacturar, BorderLayout.SOUTH); // Botón para facturar

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarListaOrdenes() {
        modeloOrdenes.clear(); // Limpiar la lista antes de agregar las órdenes
        for (Orden orden : ordenesPendientes) {
            StringBuilder ordenDetalles = new StringBuilder("Orden #" + orden.getIdOrden() + " - " + (orden.isParaLlevar() ? "Para llevar" : "Para comer aquí") + "\n");
            ordenDetalles.append("    Cliente: ").append(orden.getClienteNombre()).append("\n");
            ordenDetalles.append("    Mesa: ").append(orden.getMesa()).append("\n");
            ordenDetalles.append("    Sede: ").append(orden.getSede()).append("\n"); // Mostrar la sede
            ordenDetalles.append("    Productos:\n");

            for (Producto producto : orden.getProductos()) {
                ordenDetalles.append("- ").append(producto.getNombre()).append("\n"); // Solo nombre del producto
            }
            for (Combo combo : orden.getCombos()) {
                ordenDetalles.append("- ").append(combo.getNombre()).append("\n"); // Solo nombre del combo
            }

            modeloOrdenes.addElement(ordenDetalles.toString());
        }
    }

    private void facturarOrden() {
        int selectedIndex = listaOrdenes.getSelectedIndex();
        if (selectedIndex != -1) {
            Orden orden = ordenesPendientes.get(selectedIndex);

            // Generar la factura en formato de texto
            StringBuilder factura = new StringBuilder();
            factura.append("Factura - Orden #").append(codigoFactura++).append("\n"); // Código de factura
            factura.append("Empresa: ").append(nombreEmpresa).append("\n");
            factura.append("Teléfono: ").append(telefonoEmpresa).append("\n");
            factura.append("Dirección: ").append(direccionEmpresa).append("\n\n");
            factura.append("Cliente: ").append(orden.getClienteNombre()).append("\n");
            factura.append("Mesa: ").append(orden.getMesa()).append("\n");
            factura.append("Sede: ").append(orden.getSede()).append("\n"); // Mostrar la sede en la factura
            factura.append("Para: ").append(orden.isParaLlevar() ? "Llevar" : "Comer Aquí").append("\n");
            factura.append("Detalles:\n");

            for (Producto producto : orden.getProductos()) {
                factura.append("- ").append(producto.getNombre()).append("\n");
            }
            for (Combo combo : orden.getCombos()) {
                factura.append("- ").append(combo.getNombre()).append("\n");
            }

            // Cálculo de servicio
            double subtotal = orden.calcularSubtotal();
            double iva = subtotal * 0.13;
            double servicio = orden.isParaLlevar() ? 0 : subtotal * 0.10; // 10% de servicio si es para comer aquí
            double total = subtotal + iva + servicio;

            factura.append("Subtotal: ₡").append(String.format("%.2f", subtotal)).append("\n");
            factura.append("IVA: ₡").append(String.format("%.2f", iva)).append("\n");
            if (servicio > 0) {
                factura.append("Servicio: ₡").append(String.format("%.2f", servicio)).append("\n");
            }
            factura.append("Total: ₡").append(String.format("%.2f", total)).append("\n");

            // Mostrar factura en un cuadro de diálogo
            mostrarFacturaEnPantalla(factura.toString());

            // Eliminar la orden de la lista de pendientes
            ordenesPendientes.remove(selectedIndex);
            actualizarListaOrdenes();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una orden para facturar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarFacturaEnPantalla(String contenidoFactura) {
        JTextArea textArea = new JTextArea(contenidoFactura);
        textArea.setEditable(false); // Hacer que el área de texto no sea editable
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Estilo de fuente monoespaciada para mejor alineación

        // Crear un panel desplazable para el área de texto
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Tamaño de la ventana de la factura

        // Mostrar el cuadro de diálogo con la factura
        JOptionPane.showMessageDialog(this, scrollPane, "Factura Generada", JOptionPane.INFORMATION_MESSAGE);
    }
}