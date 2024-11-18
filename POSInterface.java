package grupo_8_sc303_mv_proyectono1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class POSInterface extends JFrame {
    private DefaultListModel<String> modeloPedido;
    private Map<String, Integer> cantidadesPedido; // Mapa para almacenar productos y sus cantidades
    private JList<String> listaPedido;
    private JTextField nombreCliente;
    private JTextField numeroMesa;
    private JRadioButton paraLlevar;
    private JRadioButton comerAqui;
    private AtomicInteger contadorOrdenes;
    public static List<Orden> listaOrdenes = new ArrayList<>(); // Lista estática para guardar órdenes

    public POSInterface() {
        super("Comida Rápida FideBurguesas");

        modeloPedido = new DefaultListModel<>();
        cantidadesPedido = new HashMap<>();
        contadorOrdenes = new AtomicInteger(1);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Agregar los paneles existentes
        add(crearPanelNorte(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
        add(crearPanelSur(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel crearPanelNorte() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Nombre del Cliente:"));
        nombreCliente = new JTextField(10);
        panel.add(nombreCliente);
        panel.add(new JLabel("Número de Mesa:"));
        numeroMesa = new JTextField(5);
        panel.add(numeroMesa);

        paraLlevar = new JRadioButton("Para Llevar");
        comerAqui = new JRadioButton("Comer Aquí", true);
        ButtonGroup group = new ButtonGroup();
        group.add(paraLlevar);
        group.add(comerAqui);

        panel.add(paraLlevar);
        panel.add(comerAqui);

        return panel;
    }

    private JPanel crearPanelCentral() {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Productos", crearPanelProductosPorCategoria());
        tabbedPane.addTab("Combos", crearPanelCombos());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tabbedPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelProductosPorCategoria() {
        List<CategoriaProducto> categorias = DataManager.getCategoriasProductos(); // Obtener categorías de productos
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        for (CategoriaProducto categoria : categorias) {
            JPanel panelCategoria = new JPanel(new GridLayout(0, 3, 10, 10));
            panelCategoria.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            for (Producto producto : categoria.getProductos()) {
                JPanel productoPanel = new JPanel(new BorderLayout());
                ImageIcon icon = new ImageIcon(getClass().getResource(producto.getImagePath()));

                if (icon.getIconWidth() == -1) {
                    System.err.println("No se pudo cargar la imagen: " + producto.getImagePath());
                } else {
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(90, 80, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newImg);
                }

                JButton btn = new JButton("<html><div style='text-align: center;'>" + producto.getNombre() + "<br>₡" + producto.getPrecio() + "</div></html>", icon);
                btn.setFont(new Font("Arial", Font.PLAIN, 12));
                btn.setVerticalTextPosition(SwingConstants.BOTTOM);
                btn.setHorizontalTextPosition(SwingConstants.CENTER);
                btn.addActionListener(e -> {
                    cantidadesPedido.put(producto.getNombre(), cantidadesPedido.getOrDefault(producto.getNombre(), 0) + 1);
                    actualizarPedido();
                });

                productoPanel.add(btn, BorderLayout.CENTER);
                panelCategoria.add(productoPanel);
            }

            tabbedPane.addTab(categoria.getNombreCategoria(), panelCategoria);
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tabbedPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelCombos() {
        List<CategoriaCombo> categoriasCombos = DataManager.getCategoriasCombos(); // Obtener categorías de combos
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        for (CategoriaCombo categoria : categoriasCombos) {
            JPanel panelCategoria = new JPanel(new GridLayout(0, 3, 10, 10));
            panelCategoria.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            for (Combo combo : categoria.getCombos()) {
                JPanel comboPanel = new JPanel(new BorderLayout());
                ImageIcon icon = new ImageIcon(getClass().getResource(combo.getImagePath()));

                if (icon.getIconWidth() == -1) {
                    System.err.println("No se pudo cargar la imagen: " + combo.getImagePath());
                } else {
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(90, 80, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newImg);
                }

                JButton btn = new JButton("<html><div style='text-align: center;'>" + combo.getNombre() + "<br>₡" + combo.getPrecio() + "</div></html>", icon);
                btn.setFont(new Font("Arial", Font.PLAIN, 12));
                btn.setVerticalTextPosition(SwingConstants.BOTTOM);
                btn.setHorizontalTextPosition(SwingConstants.CENTER);
                btn.addActionListener(e -> {
                    cantidadesPedido.put(combo.getNombre(), cantidadesPedido.getOrDefault(combo.getNombre(), 0) + 1);
                    actualizarPedido();
                });

                comboPanel.add(btn, BorderLayout.CENTER);
                panelCategoria.add(comboPanel);
            }

            tabbedPane.addTab(categoria.getNombreCategoria(), panelCategoria);
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tabbedPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelSur() {
        JPanel panel = new JPanel(new BorderLayout());

        // Crear el panel para el pedido actual
        JPanel pedidoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        listaPedido = new JList<>(modeloPedido);
        JScrollPane scrollPane = new JScrollPane(listaPedido);
        scrollPane.setPreferredSize(new Dimension(350, 130));

        JButton btnModificar = new JButton("Modificar Pedido");
        btnModificar.addActionListener(this::modificarPedido);

        JButton btnFinalizar = new JButton("Finalizar Orden");
        btnFinalizar.addActionListener(e -> finalizarOrden());

        // Botón de regresar al menú principal
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(e -> {
            this.dispose(); // Cierra la ventana actual
            new MenuPrincipal(); // Abre el menú principal
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnModificar);
        buttonPanel.add(btnFinalizar);
        buttonPanel.add(btnRegresar); // Añadir el botón de regresar

        JLabel pedidoActualLabel = new JLabel("Pedido Actual:", JLabel.CENTER);

        pedidoPanel.add(pedidoActualLabel, gbc);
        gbc.gridy++;
        pedidoPanel.add(scrollPane, gbc);
        gbc.gridy++;
        pedidoPanel.add(buttonPanel, gbc);

        // Agregar imagen de mesero
        ImageIcon iconoMesero = new ImageIcon(getClass().getResource("/grupo_8_sc303_mv_proyectono1/Imagenes/Mesero.png"));
        Image img = iconoMesero.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Ajustar tamaño a 60x60
        iconoMesero = new ImageIcon(img);
        JLabel labelMesero = new JLabel(iconoMesero);
        labelMesero.setHorizontalAlignment(JLabel.CENTER);
        panel.add(labelMesero, BorderLayout.WEST); // Agregar imagen al panel

        panel.add(pedidoPanel, BorderLayout.CENTER);
        return panel;
    }

    private void modificarPedido(ActionEvent e) {
        if (!cantidadesPedido.isEmpty()) {
            JDialog dialog = new JDialog(this, "Modificar Pedido", true);
            dialog.setLayout(new BorderLayout());
            dialog.setSize(400, 300);
            dialog.setLocationRelativeTo(this);

            String[] columnas = {"Producto", "Cantidad", "Eliminar"};
            Object[][] datos = new Object[cantidadesPedido.size()][3];

            int index = 0;
            for (Map.Entry<String, Integer> entry : cantidadesPedido.entrySet()) {
                String productoNombre = entry.getKey();
                Integer cantidad = entry.getValue();
                datos[index][0] = productoNombre;
                datos[index][1] = cantidad;
                datos[index][2] = false; // CheckBox para eliminar
                index++;
            }

            JTable tabla = new JTable(datos, columnas) {
                public Class getColumnClass(int column) {
                    return column == 2 ? Boolean.class : String.class;
                }
            };

            JScrollPane scrollPane = new JScrollPane(tabla);
            dialog.add(scrollPane, BorderLayout.CENTER);

            JButton btnAplicar = new JButton("Aplicar Cambios");
            btnAplicar.addActionListener(ev -> {
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    String productoNombre = (String) tabla.getValueAt(i, 0);
                    int nuevaCantidad = Integer.parseInt(tabla.getValueAt(i, 1).toString());
                    boolean eliminar = (Boolean) tabla.getValueAt(i, 2);

                    if (eliminar) {
                        cantidadesPedido.remove(productoNombre);
                    } else {
                        cantidadesPedido.put(productoNombre, nuevaCantidad);
                    }
                }
                actualizarPedido();  // Actualizar la lista de pedidos
                dialog.dispose();  // Cerrar el diálogo
            });

            dialog.add(btnAplicar, BorderLayout.SOUTH);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No hay productos en el pedido para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void finalizarOrden() {
        // Validar que el nombre del cliente esté ingresado
        String nombre = nombreCliente.getText();
        if (nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que el número de mesa esté ingresado solo si es "Comer Aquí"
        if (comerAqui.isSelected()) {
            String mesa = numeroMesa.getText();
            if (mesa.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el número de mesa.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Generar el número de orden
        int numeroOrden = contadorOrdenes.getAndIncrement();

        // Crear una nueva orden con el nombre del cliente, número de mesa (si aplica), tipo de pedido y sede
        Orden orden = new Orden(numeroOrden, nombre, (comerAqui.isSelected() ? numeroMesa.getText() : "N/A"), paraLlevar.isSelected(), "San José"); // Reemplaza "San José" con la sede deseada

        // Agregar productos a la orden
        for (Map.Entry<String, Integer> entry : cantidadesPedido.entrySet()) {
            String productoNombre = entry.getKey();
            Producto producto = obtenerProductoPorNombre(productoNombre);
            if (producto != null) {
                for (int i = 0; i < entry.getValue(); i++) {
                    orden.agregarProducto(producto); // Agregar el producto tantas veces como su cantidad
                }
            } else {
                System.err.println("Producto no encontrado: " + productoNombre);
            }
        }

        // Agregar la orden a la lista estática
        listaOrdenes.add(orden); // Asegúrate de que la orden se añade aquí

        // Mostrar mensaje de orden finalizada con detalles
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(String.format("Orden #%d finalizada.\n", numeroOrden));
        mensaje.append("Nombre del Cliente: " + nombre + "\n");
        mensaje.append("Número de Mesa: " + (comerAqui.isSelected() ? numeroMesa.getText() : "N/A") + "\n");
        mensaje.append("Para " + (orden.isParaLlevar() ? "Llevar" : "Comer Aquí") + "\n");
        mensaje.append("Detalle del Pedido:\n");

        for (Map.Entry<String, Integer> entry : cantidadesPedido.entrySet()) {
            mensaje.append(entry.getValue() + "x " + entry.getKey() + "\n");
        }

        mensaje.append(String.format("Total: ₡%.2f", orden.calcularTotal()));
        JOptionPane.showMessageDialog(this, mensaje.toString(), "Orden Finalizada", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar el formulario para la siguiente orden
        limpiarFormulario();
    }

    private Producto obtenerProductoPorNombre(String nombre) {
        for (CategoriaProducto categoria : DataManager.getCategoriasProductos()) {
            for (Producto producto : categoria.getProductos()) {
                if (producto.getNombre().equals(nombre)) {
                    return producto;
                }
            }
        }
        return null; // Si no se encuentra el producto, retornar null
    }

    private void limpiarFormulario() {
        nombreCliente.setText("");
        numeroMesa.setText("");
        modeloPedido.clear();
        cantidadesPedido.clear();
    }

    private void actualizarPedido() {
        modeloPedido.clear();
        for (Map.Entry<String, Integer> entry : cantidadesPedido.entrySet()) {
            String productoNombre = entry.getKey();
            int cantidad = entry.getValue();
            modeloPedido.addElement(cantidad + "x " + productoNombre);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new POSInterface().setVisible(true));
    }
}