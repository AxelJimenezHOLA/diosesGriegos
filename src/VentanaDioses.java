import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class VentanaDioses extends JFrame {
    private final AplicacionDioses appDioses;
    private JList<Dios> listaDioses;
    private JComboBox<String> comboSubtipo;
    private JComboBox<String> comboOrden;
    private JTextArea descripcionArea;
    private JLabel imagenLabel;
    private JLabel contadorLabel;
    private JLabel nombreLabel;
    private JLabel tipoPrincipalLabel;
    private JLabel tipoSecundarioLabel;



    public VentanaDioses() {
        appDioses = new AplicacionDioses();
        configurarVentana();
        inicializarComponentes();
        actualizarInterfaz();
    }

    private void configurarVentana() {
        setTitle("Dioses griegos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setBackground(new Color(245, 245, 245));
        setLayout(new GridBagLayout());
        setResizable(false);
    }

    private void inicializarComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Panel izquierdo para la imagen
        JPanel panelImagen = crearPanelConBordeRedondeado();
        imagenLabel = new JLabel();
        imagenLabel.setPreferredSize(new Dimension(300, 300));
        panelImagen.add(imagenLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0.33;
        gbc.weighty = 1.0;
        add(panelImagen, gbc);

        // Panel central para la lista
        JPanel panelLista = crearPanelConBordeRedondeado();
        panelLista.setLayout(new BorderLayout());
        listaDioses = new JList<>(new DefaultListModel<>());
        JScrollPane scrollPane = new JScrollPane(listaDioses);
        panelLista.add(scrollPane, BorderLayout.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0.33;
        add(panelLista, gbc);

        // Panel derecho para controles y detalles
        JPanel panelDerecho = new JPanel(new GridBagLayout());
        GridBagConstraints gbcDerecho = new GridBagConstraints();
        gbcDerecho.fill = GridBagConstraints.BOTH;
        gbcDerecho.insets = new Insets(5, 5, 5, 5);

        // Panel de controles
        JPanel panelControles = crearPanelConBordeRedondeado();
        panelControles.setLayout(new GridBagLayout());
        GridBagConstraints gbcControles = new GridBagConstraints();
        gbcControles.fill = GridBagConstraints.HORIZONTAL;
        gbcControles.insets = new Insets(5, 5, 5, 5);

        comboSubtipo = new JComboBox<>(appDioses.getSubtipos());
        comboOrden = new JComboBox<>(new String[]{"Ascendente", "Descendente"});
        JButton btnArriba = new JButton("▲");
        JButton btnAbajo = new JButton("▼");
        contadorLabel = new JLabel("0 / 0", SwingConstants.CENTER);

        gbcControles.gridx = 0;
        gbcControles.gridy = 0;
        gbcControles.gridwidth = 2;
        panelControles.add(new JLabel("Subtipo:"), gbcControles);

        gbcControles.gridy = 1;
        panelControles.add(comboSubtipo, gbcControles);

        gbcControles.gridy = 2;
        panelControles.add(new JLabel("Orden:"), gbcControles);

        gbcControles.gridy = 3;
        panelControles.add(comboOrden, gbcControles);

        gbcControles.gridy = 4;
        gbcControles.gridwidth = 1;
        panelControles.add(btnArriba, gbcControles);

        gbcControles.gridx = 1;
        panelControles.add(btnAbajo, gbcControles);

        gbcControles.gridx = 0;
        gbcControles.gridy = 5;
        gbcControles.gridwidth = 2;
        panelControles.add(contadorLabel, gbcControles);

        gbcDerecho.gridx = 0;
        gbcDerecho.gridy = 0;
        gbcDerecho.weighty = 0.4;
        panelDerecho.add(panelControles, gbcDerecho);

        // Panel de información
        JPanel panelInfo = crearPanelConBordeRedondeado();
        panelInfo.setLayout(new GridBagLayout());
        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.insets = new Insets(5, 5, 5, 5);

        nombreLabel = new JLabel();
        tipoPrincipalLabel = new JLabel();
        tipoSecundarioLabel = new JLabel();
        descripcionArea = new JTextArea(5, 20);
        descripcionArea.setLineWrap(true);
        descripcionArea.setWrapStyleWord(true);
        descripcionArea.setEditable(false);

        gbcInfo.gridx = 0;
        gbcInfo.gridy = 0;
        panelInfo.add(nombreLabel, gbcInfo);

        gbcInfo.gridy = 1;
        panelInfo.add(tipoPrincipalLabel, gbcInfo);

        gbcInfo.gridy = 2;
        panelInfo.add(tipoSecundarioLabel, gbcInfo);

        gbcInfo.gridy = 3;
        gbcInfo.weighty = 1.0;
        gbcInfo.fill = GridBagConstraints.BOTH;
        panelInfo.add(new JScrollPane(descripcionArea), gbcInfo);

        gbcDerecho.gridy = 1;
        gbcDerecho.weighty = 0.6;
        panelDerecho.add(panelInfo, gbcDerecho);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0.33;
        add(panelDerecho, gbc);

        // Listeners
        comboSubtipo.addActionListener(e -> {
            String subtipo = (String) comboSubtipo.getSelectedItem();
            if (subtipo != null) {
                appDioses.seleccionarLista(subtipo);
                comboOrden.setSelectedItem("Ascendente");
                actualizarInterfaz();
            }
        });

        comboOrden.addActionListener(e -> {
            String orden = (String) comboOrden.getSelectedItem();
            if (orden != null) {
                appDioses.elegirOrden(orden);
                actualizarInterfaz();
            }
        });

        btnArriba.addActionListener(e -> {
            appDioses.retrocederEnListaSeleccionada();
            actualizarInterfaz();
        });

        btnAbajo.addActionListener(e -> {
            appDioses.avanzarEnListaSeleccionada();
            actualizarInterfaz();
        });
    }

    private JPanel crearPanelConBordeRedondeado() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.GRAY, 1, true),
                new EmptyBorder(10, 10, 10, 10)));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    private void actualizarInterfaz() {
        // Actualizar imagen
        ImageIcon imagen = appDioses.getImagenDios();
        if (imagen != null) {
            Image img = imagen.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imagenLabel.setIcon(new ImageIcon(img));
        }

        // Actualizar lista
        DefaultListModel<Dios> modeloLista = new DefaultListModel<>();
        ListaDoble<Dios> lista = appDioses.getListaSeleccionada();
        for (int i = 0; i < lista.size(); i++) {
            modeloLista.addElement(lista.get(i));
        }
        listaDioses.setModel(modeloLista);

        // Destacar dios seleccionado
        listaDioses.setSelectedIndex(appDioses.getIndiceDios());
        listaDioses.ensureIndexIsVisible(appDioses.getIndiceDios());

        // Actualizar contador
        contadorLabel.setText(String.format("%d / %d", appDioses.getIndiceDios() + 1, lista.size()));

        // Actualizar información del dios seleccionado
        Dios diosActual = appDioses.getDiosSeleccionado();
        if (diosActual != null) {
            nombreLabel.setText("%s (%s)".formatted(diosActual.getNombreIngles(), diosActual.getNombreGriego()));
            tipoPrincipalLabel.setText("Tipo principal: %s".formatted(diosActual.getTipoPrincipal()));
            tipoSecundarioLabel.setText("Tipo secundario: %s".formatted(diosActual.getTipoSecundario()));
            descripcionArea.setText(diosActual.getDescripcion());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaDioses().setVisible(true);
        });
    }
}