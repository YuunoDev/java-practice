package projects;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.io.File;
import java.io.IOException;

/**
 * ============================================================
 * GUÍA COMPLETA DE JFRAME Y COMPONENTES SWING EN JAVA
 * Autor: Referencia educativa
 * Descripción: Todo lo que puedes agregar/configurar en una
 * ventana JFrame con anotaciones detalladas.
 * ============================================================
 */
public class JFrame_Completo extends JFrame {

    public JFrame_Completo() {

        // ════════════════════════════════════════════════════════
        // 1. CONFIGURACIÓN BÁSICA DEL JFRAME
        // ════════════════════════════════════════════════════════

        setTitle("Mi Ventana"); // Título de la barra de título
        setSize(900, 650); // Ancho x Alto en píxeles
        setPreferredSize(new Dimension(900, 650)); // Tamaño preferido (útil con pack())
        setMinimumSize(new Dimension(400, 300)); // Tamaño mínimo permitido al redimensionar
        setMaximumSize(new Dimension(1200, 900)); // Tamaño máximo permitido

        // Posición de la ventana en pantalla
        setLocation(100, 100); // Posición X, Y desde esquina superior izquierda
        setLocationRelativeTo(null); // null = centrar en pantalla | componente = relativo a él

        // Operación al cerrar la ventana
        // EXIT_ON_CLOSE → cierra la app
        // DISPOSE_ON_CLOSE → libera recursos pero no cierra la JVM
        // HIDE_ON_CLOSE → solo oculta la ventana
        // DO_NOTHING_ON_CLOSE → no hace nada (debes manejarlo manualmente)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(true); // true = el usuario puede redimensionar | false = tamaño fijo
        setVisible(true); // Muestra la ventana; llama siempre al final

        // Íconos de la ventana (barra de tareas y esquina superior)
        // Puedes agregar varios tamaños para que el SO elija el mejor
        ImageIcon icon = new ImageIcon("ruta/icono.png");
        setIconImage(icon.getImage());
        // Para múltiples íconos:
        // java.util.List<Image> icons = new java.util.ArrayList<>();
        // icons.add(new ImageIcon("icon16.png").getImage());
        // icons.add(new ImageIcon("icon32.png").getImage());
        // setIconImages(icons);

        // Estado de la ventana (maximizar, minimizar, etc.)
        // NORMAL | ICONIFIED (minimizada) | MAXIMIZED_BOTH | MAXIMIZED_HORIZ |
        // MAXIMIZED_VERT
        setExtendedState(JFrame.NORMAL);

        // Ventana sin decoración (sin barra de título ni bordes del SO)
        // setUndecorated(true); // ← descomenta si quieres ventana sin marco

        // Transparencia/Opacidad de la ventana completa (0.0f = invisible, 1.0f =
        // opaco)
        // setOpacity(0.9f);

        // Forma personalizada de la ventana (recorte con figura)
        // setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));

        // Siempre encima de otras ventanas
        setAlwaysOnTop(false); // true = flotante sobre todo

        // ════════════════════════════════════════════════════════
        // 2. LOOK AND FEEL (Apariencia global)
        // ════════════════════════════════════════════════════════
        try {
            // Opciones disponibles:
            // "javax.swing.plaf.metal.MetalLookAndFeel" → Metal (por defecto de Java)
            // "javax.swing.plaf.nimbus.NimbusLookAndFeel" → Nimbus (moderno)
            // "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" → Windows nativo
            // UIManager.getSystemLookAndFeelClassName() → LookAndFeel del SO actual
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this); // Aplica el cambio a todos los componentes
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ════════════════════════════════════════════════════════
        // 3. LAYOUT MANAGERS (Gestores de disposición)
        // ════════════════════════════════════════════════════════

        // BorderLayout → 5 zonas: NORTH, SOUTH, EAST, WEST, CENTER (default en JFrame)
        setLayout(new BorderLayout(5, 5)); // (hgap, vgap) separación entre zonas

        // FlowLayout → componentes en fila, se envuelven al llenarse
        // setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // (alineación, hgap,
        // vgap)

        // GridLayout → cuadrícula fija de filas x columnas
        // setLayout(new GridLayout(3, 2, 5, 5)); // (filas, cols, hgap, vgap)

        // GridBagLayout → el más flexible, control total por celda
        // setLayout(new GridBagLayout());

        // BoxLayout → apilado horizontal o vertical
        // setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // CardLayout → múltiples "tarjetas"/paneles apilados, muestras uno a la vez
        // setLayout(new CardLayout());

        // null layout → posicionamiento absoluto (no recomendado)
        // setLayout(null);

        // ════════════════════════════════════════════════════════
        // 4. CONTENT PANE / PANEL PRINCIPAL
        // ════════════════════════════════════════════════════════

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(8, 8));
        panelPrincipal.setBackground(new Color(245, 245, 250)); // Color de fondo
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno
        setContentPane(panelPrincipal); // Establece el panel como contenedor base

        // ════════════════════════════════════════════════════════
        // 5. MENÚ BAR (Barra de menús superior)
        // ════════════════════════════════════════════════════════

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(60, 63, 65));

        // ── Menú "Archivo" ──
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic(KeyEvent.VK_A); // Alt+A para abrir con teclado
        menuArchivo.setForeground(Color.WHITE);

        JMenuItem itemNuevo = new JMenuItem("Nuevo", new ImageIcon("nuevo.png"));
        JMenuItem itemAbrir = new JMenuItem("Abrir");
        JMenuItem itemGuardar = new JMenuItem("Guardar");
        JMenuItem itemSalir = new JMenuItem("Salir");

        // Atajos de teclado (Ctrl+N, Ctrl+S, etc.)
        itemNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        itemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        itemSalir.addActionListener(e -> System.exit(0)); // Cierra la aplicación

        // Separador visual entre ítems
        menuArchivo.add(itemNuevo);
        menuArchivo.add(itemAbrir);
        menuArchivo.add(itemGuardar);
        menuArchivo.addSeparator(); // ── línea divisoria ──
        menuArchivo.add(itemSalir);

        // ── Menú con casilla de verificación ──
        JCheckBoxMenuItem itemModoOscuro = new JCheckBoxMenuItem("Modo Oscuro");
        itemModoOscuro.setSelected(false); // Estado inicial

        // ── Menú con botón de radio ──
        JMenu menuIdioma = new JMenu("Idioma");
        ButtonGroup grupoIdioma = new ButtonGroup(); // Solo uno seleccionado a la vez
        JRadioButtonMenuItem rbEspanol = new JRadioButtonMenuItem("Español", true);
        JRadioButtonMenuItem rbIngles = new JRadioButtonMenuItem("English");
        grupoIdioma.add(rbEspanol);
        grupoIdioma.add(rbIngles);
        menuIdioma.add(rbEspanol);
        menuIdioma.add(rbIngles);

        // ── Submenú (menú dentro de menú) ──
        JMenu menuExportar = new JMenu("Exportar como...");
        menuExportar.add(new JMenuItem("PDF"));
        menuExportar.add(new JMenuItem("CSV"));
        menuExportar.add(new JMenuItem("Excel"));
        menuArchivo.add(menuExportar);

        JMenu menuVer = new JMenu("Ver");
        JMenu menuAyuda = new JMenu("Ayuda");
        menuVer.add(itemModoOscuro);
        menuVer.add(menuIdioma);

        menuBar.add(menuArchivo);
        menuBar.add(menuVer);
        menuBar.add(menuAyuda);
        setJMenuBar(menuBar);

        // ════════════════════════════════════════════════════════
        // 6. TOOLBAR (Barra de herramientas)
        // ════════════════════════════════════════════════════════

        JToolBar toolbar = new JToolBar("Herramientas");
        toolbar.setFloatable(false); // false = no se puede arrastrar/desacoplar
        toolbar.setRollover(true); // Efecto hover en botones
        toolbar.setBackground(new Color(230, 230, 235));

        JButton btnToolNuevo = new JButton("Nuevo");
        JButton btnToolAbrir = new JButton(new ImageIcon("abrir.png")); // Solo ícono
        JButton btnToolGuardar = new JButton("💾 Guardar");

        // Tooltip emergente al pasar el ratón
        btnToolNuevo.setToolTipText("Crear nuevo archivo (Ctrl+N)");

        toolbar.add(btnToolNuevo);
        toolbar.add(btnToolAbrir);
        toolbar.addSeparator(); // Espacio visual
        toolbar.add(btnToolGuardar);

        // Agregar un combo en la toolbar
        JComboBox<String> comboZoom = new JComboBox<>(new String[] { "50%", "75%", "100%", "150%" });
        comboZoom.setSelectedItem("100%");
        comboZoom.setMaximumSize(new Dimension(80, 30));
        toolbar.add(comboZoom);

        panelPrincipal.add(toolbar, BorderLayout.NORTH);

        // ════════════════════════════════════════════════════════
        // 7. STATUS BAR (Barra de estado inferior)
        // ════════════════════════════════════════════════════════

        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        statusBar.setPreferredSize(new Dimension(0, 22));

        JLabel lblStatus = new JLabel(" Listo");
        JLabel lblVersion = new JLabel("v1.0.0  ");
        lblVersion.setForeground(Color.GRAY);

        statusBar.add(lblStatus, BorderLayout.WEST);
        statusBar.add(lblVersion, BorderLayout.EAST);

        panelPrincipal.add(statusBar, BorderLayout.SOUTH);

        // ════════════════════════════════════════════════════════
        // 8. PANEL CON PESTAÑAS (JTabbedPane)
        // ════════════════════════════════════════════════════════

        JTabbedPane pestañas = new JTabbedPane();
        // Posición de las pestañas: TOP | BOTTOM | LEFT | RIGHT
        pestañas.setTabPlacement(JTabbedPane.TOP);
        // Política de desbordamiento: WRAP_TAB_LAYOUT (filas) | SCROLL_TAB_LAYOUT
        // (scroll)
        pestañas.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        // ── Pestaña 1: Controles básicos ──
        JPanel tabControles = new JPanel(new GridBagLayout());
        tabControles.setBackground(Color.WHITE);
        pestañas.addTab("Controles", tabControles);
        pestañas.setToolTipTextAt(0, "Controles básicos de Swing");

        // ── Pestaña 2: Tablas ──
        JPanel tabTablas = new JPanel(new BorderLayout());
        pestañas.addTab("Tablas", tabTablas);

        // ── Pestaña 3: Árbol ──
        JPanel tabArbol = new JPanel(new BorderLayout());
        pestañas.addTab("Árbol", tabArbol);

        // ── Pestaña 4: Dibujo ──
        JPanel tabDibujo = new JPanel(new BorderLayout());
        pestañas.addTab("Dibujo", tabDibujo);

        panelPrincipal.add(pestañas, BorderLayout.CENTER);

        // ════════════════════════════════════════════════════════
        // 9. CONTROLES BÁSICOS (Tab 1)
        // ════════════════════════════════════════════════════════
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margen: top, left, bottom, right
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ── JLabel ──────────────────────────────────────────────
        JLabel etiqueta = new JLabel("Nombre:");
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 13));
        // Font.PLAIN | Font.BOLD | Font.ITALIC | Font.BOLD+Font.ITALIC
        etiqueta.setForeground(new Color(33, 33, 33));
        etiqueta.setHorizontalAlignment(SwingConstants.LEFT);
        // SwingConstants: LEFT | CENTER | RIGHT | LEADING | TRAILING
        // etiqueta.setIcon(new ImageIcon("icon.png")); // Ícono junto al texto
        // etiqueta.setIconTextGap(8); // Separación entre ícono y texto

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        tabControles.add(etiqueta, gbc);

        // ── JTextField ──────────────────────────────────────────
        JTextField txtNombre = new JTextField(20);
        // 20 = columnas de ancho aproximado
        txtNombre.setText("Texto inicial");
        txtNombre.setEditable(true); // false = solo lectura visual
        txtNombre.setEnabled(true); // false = deshabilitado (gris)
        txtNombre.setToolTipText("Escribe tu nombre");
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNombre.setBackground(Color.WHITE);
        txtNombre.setForeground(Color.BLACK);
        txtNombre.setCaretColor(Color.BLUE); // Color del cursor
        txtNombre.setHorizontalAlignment(JTextField.LEFT); // LEFT | CENTER | RIGHT
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 200), 1),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        // Placeholder (Java no lo trae nativo; se simula con FocusListener):
        txtNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtNombre.getText().equals("Escribe aquí..."))
                    txtNombre.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtNombre.getText().isEmpty())
                    txtNombre.setText("Escribe aquí...");
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 0;
        tabControles.add(txtNombre, gbc);

        // ── JPasswordField ──────────────────────────────────────
        JPasswordField txtPassword = new JPasswordField(20);
        txtPassword.setEchoChar('●'); // Carácter de enmascaramiento
        // txtPassword.getPassword() → char[] (más seguro que getText())
        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel lblPass = new JLabel("Contraseña:");
        gbc.gridx = 0;
        tabControles.add(lblPass, gbc);
        gbc.gridx = 1;
        tabControles.add(txtPassword, gbc);

        // ── JTextArea ───────────────────────────────────────────
        JTextArea areaTexto = new JTextArea(4, 30);
        areaTexto.setLineWrap(true); // Ajuste de línea automático
        areaTexto.setWrapStyleWord(true); // Ajuste por palabra (no corta palabras)
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaTexto.setTabSize(4); // Tamaño de tabulación
        // Siempre envolver el JTextArea en JScrollPane:
        JScrollPane scrollArea = new JScrollPane(areaTexto);
        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // VERTICAL_SCROLLBAR_ALWAYS | AS_NEEDED | NEVER
        scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        tabControles.add(new JLabel("Descripción:"), gbc);
        gbc.gridy = 3;
        tabControles.add(scrollArea, gbc);
        gbc.gridwidth = 1;

        // ── JButton ─────────────────────────────────────────────
        JButton btnAccion = new JButton("Guardar");
        btnAccion.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnAccion.setBackground(new Color(70, 130, 180));
        btnAccion.setForeground(Color.WHITE);
        btnAccion.setFocusPainted(false); // Quita el borde de foco interno
        btnAccion.setBorderPainted(true);
        btnAccion.setContentAreaFilled(true); // false = sin fondo (transparente)
        btnAccion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAccion.setMnemonic(KeyEvent.VK_G); // Alt+G activa el botón
        btnAccion.setPreferredSize(new Dimension(120, 35));
        // Deshabilitar con: btnAccion.setEnabled(false);
        btnAccion.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "¡Guardado exitosamente!");
        });
        // Efecto hover manual:
        btnAccion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAccion.setBackground(new Color(50, 110, 160));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAccion.setBackground(new Color(70, 130, 180));
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        tabControles.add(btnAccion, gbc);

        // ── JCheckBox ───────────────────────────────────────────
        JCheckBox chkActivo = new JCheckBox("Activo", true); // true = marcado por defecto
        JCheckBox chkRecordarme = new JCheckBox("Recuérdame");
        chkActivo.setBackground(Color.WHITE);
        // chkActivo.isSelected() → boolean de estado
        // chkActivo.setSelected(true/false) → cambiar estado programáticamente
        chkActivo.addItemListener(e -> {
            boolean seleccionado = (e.getStateChange() == ItemEvent.SELECTED);
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        tabControles.add(chkActivo, gbc);
        gbc.gridx = 1;
        tabControles.add(chkRecordarme, gbc);

        // ── JRadioButton ────────────────────────────────────────
        // Los RadioButtons en el mismo ButtonGroup se excluyen mutuamente
        ButtonGroup grupoSexo = new ButtonGroup();
        JRadioButton rbMasculino = new JRadioButton("Masculino", true);
        JRadioButton rbFemenino = new JRadioButton("Femenino");
        JRadioButton rbOtro = new JRadioButton("Otro");
        grupoSexo.add(rbMasculino);
        grupoSexo.add(rbFemenino);
        grupoSexo.add(rbOtro);

        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelRadio.setBackground(Color.WHITE);
        panelRadio.add(rbMasculino);
        panelRadio.add(rbFemenino);
        panelRadio.add(rbOtro);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        tabControles.add(new JLabel("Género:"), gbc);
        gbc.gridy = 7;
        tabControles.add(panelRadio, gbc);
        gbc.gridwidth = 1;

        // ── JComboBox ───────────────────────────────────────────
        String[] paises = { "México", "España", "Argentina", "Colombia", "Chile" };
        JComboBox<String> comboPais = new JComboBox<>(paises);
        comboPais.setSelectedIndex(0); // Selecciona por índice
        comboPais.setSelectedItem("España"); // O por valor
        comboPais.setEditable(false); // true = permite escribir texto libre
        comboPais.setMaximumRowCount(5); // Filas visibles al abrir
        comboPais.addItem("Otro"); // Agregar ítem dinámicamente
        comboPais.removeItemAt(comboPais.getItemCount() - 1); // Remover último ítem
        comboPais.addActionListener(e -> {
            String seleccionado = (String) comboPais.getSelectedItem();
        });

        gbc.gridx = 0;
        gbc.gridy = 8;
        tabControles.add(new JLabel("País:"), gbc);
        gbc.gridx = 1;
        tabControles.add(comboPais, gbc);

        // ── JList ───────────────────────────────────────────────
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Elemento 1");
        modeloLista.addElement("Elemento 2");
        modeloLista.addElement("Elemento 3");
        modeloLista.addElement("Elemento 4");

        JList<String> lista = new JList<>(modeloLista);
        lista.setVisibleRowCount(3);
        // Modo de selección:
        // SINGLE_SELECTION | SINGLE_INTERVAL_SELECTION | MULTIPLE_INTERVAL_SELECTION
        lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lista.setSelectedIndex(0);
        lista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                java.util.List<String> seleccionados = lista.getSelectedValuesList();
            }
        });
        JScrollPane scrollLista = new JScrollPane(lista);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        tabControles.add(new JLabel("Lista:"), gbc);
        gbc.gridy = 10;
        tabControles.add(scrollLista, gbc);
        gbc.gridwidth = 1;

        // ── JSlider ─────────────────────────────────────────────
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        // Parámetros: orientación, mín, máx, valor inicial
        slider.setMajorTickSpacing(25); // Marcas principales cada 25
        slider.setMinorTickSpacing(5); // Marcas menores cada 5
        slider.setPaintTicks(true); // Mostrar marcas
        slider.setPaintLabels(true); // Mostrar etiquetas numéricas
        slider.setSnapToTicks(false); // true = salta de marca en marca
        slider.addChangeListener(e -> {
            int valor = slider.getValue();
            // slider.getValueIsAdjusting() → true mientras se arrastra
        });

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        tabControles.add(new JLabel("Volumen:"), gbc);
        gbc.gridy = 12;
        tabControles.add(slider, gbc);
        gbc.gridwidth = 1;

        // ── JSpinner ────────────────────────────────────────────
        // SpinnerNumberModel(valor, mín, máx, paso)
        JSpinner spinnerNum = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));
        // SpinnerListModel: lista de valores
        JSpinner spinnerMes = new JSpinner(new SpinnerListModel(
                new String[] { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" }));
        // SpinnerDateModel: fechas
        JSpinner spinnerFecha = new JSpinner(new SpinnerDateModel());
        spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));

        gbc.gridx = 0;
        gbc.gridy = 13;
        tabControles.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        tabControles.add(spinnerNum, gbc);

        // ── JProgressBar ────────────────────────────────────────
        JProgressBar progreso = new JProgressBar(0, 100);
        progreso.setValue(65); // Valor actual
        progreso.setStringPainted(true); // Muestra el porcentaje en texto
        progreso.setString("65% completado"); // Texto personalizado
        progreso.setForeground(new Color(46, 139, 87));
        // Modo indeterminado (animación sin valor fijo):
        // progreso.setIndeterminate(true);
        // progreso.setOrientation(JProgressBar.VERTICAL); // Vertical

        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        tabControles.add(new JLabel("Progreso:"), gbc);
        gbc.gridy = 15;
        tabControles.add(progreso, gbc);
        gbc.gridwidth = 1;

        // ── JFormattedTextField ─────────────────────────────────
        // Campo con formato fijo (teléfono, fecha, número, etc.)
        try {
            javax.swing.text.MaskFormatter mask = new javax.swing.text.MaskFormatter("###-###-####");
            mask.setPlaceholderCharacter('_');
            JFormattedTextField txtTelefono = new JFormattedTextField(mask);
            gbc.gridx = 0;
            gbc.gridy = 16;
            tabControles.add(new JLabel("Teléfono:"), gbc);
            gbc.gridx = 1;
            tabControles.add(txtTelefono, gbc);
        } catch (Exception ignored) {
        }

        // ── JSeparator ──────────────────────────────────────────
        JSeparator separador = new JSeparator(JSeparator.HORIZONTAL);
        gbc.gridx = 0;
        gbc.gridy = 17;
        gbc.gridwidth = 2;
        tabControles.add(separador, gbc);
        gbc.gridwidth = 1;

        // ════════════════════════════════════════════════════════
        // 10. TABLA (Tab 2 - JTable)
        // ════════════════════════════════════════════════════════

        String[] columnas = { "ID", "Nombre", "Edad", "Ciudad", "Activo" };
        Object[][] datos = {
                { 1, "Ana García", 28, "CDMX", true },
                { 2, "Luis Pérez", 35, "Guadalajara", false },
                { 3, "María López", 22, "Monterrey", true },
                { 4, "Carlos Ruiz", 41, "Puebla", true },
        };

        // TableModel personalizado para mayor control:
        DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas) {
            @Override
            public Class<?> getColumnClass(int col) {
                // Devuelve el tipo real por columna (activa renderizador correcto)
                if (col == 4)
                    return Boolean.class; // checkbox
                if (col == 0)
                    return Integer.class; // número
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return col != 0; // La columna ID no es editable
            }
        };

        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(28); // Alto de cada fila
        tabla.setGridColor(new Color(220, 220, 230)); // Color de la cuadrícula
        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(true);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // SINGLE_SELECTION | SINGLE_INTERVAL_SELECTION | MULTIPLE_INTERVAL_SELECTION

        tabla.setAutoCreateRowSorter(true); // Ordenar columnas al hacer clic en el encabezado

        // Personalizar encabezado
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setReorderingAllowed(false); // Impide mover columnas

        // Ajustar ancho de columnas:
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40); // ID
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre

        // Renderizador personalizado (color alternante de filas):
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 245, 255));
                }
                return c;
            }
        });

        // Detectar selección de fila:
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) {
                String nombre = (String) modeloTabla.getValueAt(tabla.getSelectedRow(), 1);
            }
        });

        // Menú contextual en la tabla (clic derecho):
        JPopupMenu menuContextual = new JPopupMenu();
        menuContextual.add(new JMenuItem("Editar fila"));
        menuContextual.add(new JMenuItem("Eliminar fila"));
        tabla.setComponentPopupMenu(menuContextual);

        JScrollPane scrollTabla = new JScrollPane(tabla);
        tabTablas.add(scrollTabla, BorderLayout.CENTER);

        // Botones para agregar/quitar filas
        JPanel panelBotonesTabla = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = new JButton("+ Agregar");
        JButton btnEliminar = new JButton("− Eliminar");
        btnAgregar.addActionListener(
                e -> modeloTabla.addRow(new Object[] { modeloTabla.getRowCount() + 1, "Nuevo", 0, "", false }));
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0)
                modeloTabla.removeRow(fila);
        });
        panelBotonesTabla.add(btnAgregar);
        panelBotonesTabla.add(btnEliminar);
        tabTablas.add(panelBotonesTabla, BorderLayout.SOUTH);

        // ════════════════════════════════════════════════════════
        // 11. ÁRBOL (Tab 3 - JTree)
        // ════════════════════════════════════════════════════════

        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Empresa");

        DefaultMutableTreeNode depTI = new DefaultMutableTreeNode("TI");
        DefaultMutableTreeNode depRH = new DefaultMutableTreeNode("RRHH");
        DefaultMutableTreeNode depVen = new DefaultMutableTreeNode("Ventas");

        depTI.add(new DefaultMutableTreeNode("Desarrolladores"));
        depTI.add(new DefaultMutableTreeNode("QA"));
        depTI.add(new DefaultMutableTreeNode("DevOps"));
        depRH.add(new DefaultMutableTreeNode("Reclutamiento"));
        depRH.add(new DefaultMutableTreeNode("Nómina"));
        depVen.add(new DefaultMutableTreeNode("Región Norte"));
        depVen.add(new DefaultMutableTreeNode("Región Sur"));

        raiz.add(depTI);
        raiz.add(depRH);
        raiz.add(depVen);

        JTree arbol = new JTree(raiz);
        arbol.setRootVisible(true); // Muestra el nodo raíz
        arbol.setShowsRootHandles(true); // Flechas en la raíz
        arbol.setRowHeight(24);

        // Expandir todos los nodos al inicio:
        for (int i = 0; i < arbol.getRowCount(); i++)
            arbol.expandRow(i);

        arbol.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode nodoSel = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
            if (nodoSel != null) {
                Object objeto = nodoSel.getUserObject();
                boolean esHoja = nodoSel.isLeaf();
            }
        });

        tabArbol.add(new JScrollPane(arbol), BorderLayout.CENTER);

        // ════════════════════════════════════════════════════════
        // 12. PANEL DE DIBUJO PERSONALIZADO (Tab 4)
        // ════════════════════════════════════════════════════════

        JPanel panelDibujo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                // Activar anti-aliasing (bordes suavizados)
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                // Fondo degradado
                GradientPaint degradado = new GradientPaint(
                        0, 0, new Color(200, 220, 255),
                        getWidth(), getHeight(), new Color(255, 230, 200));
                g2.setPaint(degradado);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Rectángulo
                g2.setColor(new Color(70, 130, 180));
                g2.setStroke(new BasicStroke(2.5f)); // Grosor del trazo
                g2.drawRect(20, 20, 120, 60); // solo borde
                g2.setColor(new Color(70, 130, 180, 80)); // Con transparencia (alpha)
                g2.fillRect(20, 20, 120, 60); // relleno

                // Rectángulo redondeado
                g2.setColor(Color.ORANGE);
                g2.fillRoundRect(160, 20, 120, 60, 20, 20); // (x,y,w,h,arcW,arcH)

                // Óvalo / Círculo
                g2.setColor(Color.RED);
                g2.fillOval(300, 20, 80, 80);

                // Línea
                g2.setColor(Color.DARK_GRAY);
                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine(20, 120, 400, 120);

                // Línea punteada
                float[] patron = { 8, 4 }; // 8px trazo, 4px espacio
                g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 10, patron, 0));
                g2.drawLine(20, 140, 400, 140);

                // Texto con fuente personalizada
                g2.setStroke(new BasicStroke(1));
                g2.setColor(new Color(30, 30, 80));
                g2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
                g2.drawString("Dibujo con Graphics2D", 20, 180);

                // Polígono
                int[] xs = { 450, 480, 510, 490, 510, 480, 450, 470 };
                int[] ys = { 20, 50, 20, 60, 80, 50, 80, 60 };
                g2.setColor(new Color(150, 0, 200));
                g2.fillPolygon(xs, ys, xs.length);

                // Arco / sector circular
                g2.setColor(new Color(0, 160, 80));
                // drawArc(x, y, w, h, ángulo inicial, extensión)
                g2.fillArc(550, 20, 100, 100, 0, 270); // 270° relleno

                // Imagen desde recursos
                BufferedImage img;
                try {
                    img = ImageIO.read(new File("projects/image.png"));
                    g2.drawImage(img, 420, 200, 200, 200, null);
                    //g2.drawImage(img, 600, 40, null); // tamaño original
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        };
        panelDibujo.setPreferredSize(new Dimension(700, 300));
        tabDibujo.add(new JScrollPane(panelDibujo), BorderLayout.CENTER);

        // ════════════════════════════════════════════════════════
        // 13. PANEL LATERAL (JPanel izquierdo con JSplitPane)
        // ════════════════════════════════════════════════════════

        // JSplitPane divide la ventana en dos secciones arrastrables
        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setPreferredSize(new Dimension(160, 0));
        panelIzq.setBackground(new Color(45, 45, 50));
        panelIzq.add(new JLabel(" Navegación", SwingConstants.LEFT), BorderLayout.NORTH);

        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, // o VERTICAL_SPLIT
                panelIzq, // componente izquierdo/superior
                pestañas // componente derecho/inferior
        );
        split.setDividerLocation(160); // Posición inicial del divisor (px)
        split.setDividerSize(4); // Grosor del divisor
        split.setOneTouchExpandable(true); // Flechas para colapsar de un clic
        split.setContinuousLayout(true); // Redibujar mientras se arrastra

        panelPrincipal.add(split, BorderLayout.CENTER);

        // ════════════════════════════════════════════════════════
        // 14. BORDES (Borders)
        // ════════════════════════════════════════════════════════

        JPanel panelBordes = new JPanel(new GridLayout(2, 3, 10, 10));

        JPanel p1 = new JPanel();
        p1.setBackground(Color.WHITE);
        p1.setBorder(BorderFactory.createTitledBorder("TitledBorder"));

        JPanel p2 = new JPanel();
        p2.setBackground(Color.WHITE);
        p2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

        JPanel p3 = new JPanel();
        p3.setBackground(Color.WHITE);
        p3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        // EtchedBorder.RAISED para el efecto inverso

        JPanel p4 = new JPanel();
        p4.setBackground(Color.WHITE);
        p4.setBorder(BorderFactory.createRaisedBevelBorder());

        JPanel p5 = new JPanel();
        p5.setBackground(Color.WHITE);
        p5.setBorder(BorderFactory.createLoweredBevelBorder());

        JPanel p6 = new JPanel();
        p6.setBackground(Color.WHITE);
        // Borde compuesto: combina dos bordes
        p6.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED, 2),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));

        // ════════════════════════════════════════════════════════
        // 15. DIÁLOGOS (JOptionPane y JDialog)
        // ════════════════════════════════════════════════════════

        JButton btnDialogos = new JButton("Ver Diálogos");
        btnDialogos.addActionListener(e -> mostrarDialogos());

        // ════════════════════════════════════════════════════════
        // 16. EVENTOS DE TECLADO Y RATÓN
        // ════════════════════════════════════════════════════════

        // KeyListener en el JFrame completo
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int codigo = e.getKeyCode(); // VK_ENTER, VK_ESCAPE, VK_F1, etc.
                char caracter = e.getKeyChar();
                boolean ctrl = e.isControlDown();
                boolean shift = e.isShiftDown();
                boolean alt = e.isAltDown();
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    // Acción al presionar Escape
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        setFocusable(true); // Necesario para que el JFrame reciba eventos de teclado

        // MouseListener en el panel principal
        panelPrincipal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX(), y = e.getY();
                int clicks = e.getClickCount(); // Doble clic = 2
                boolean esDerecho = SwingUtilities.isRightMouseButton(e);
                boolean esIzquierdo = SwingUtilities.isLeftMouseButton(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });

        // MouseMotionListener: movimiento continuo del ratón
        panelPrincipal.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
            } // Sin botón presionado

            @Override
            public void mouseDragged(MouseEvent e) {
            } // Con botón presionado
        });

        // MouseWheelListener: rueda del ratón
        panelPrincipal.addMouseWheelListener(e -> {
            int unidades = e.getWheelRotation(); // negativo = hacia arriba
        });

        // WindowListener: eventos de la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Confirmar antes de cerrar
                int resp = JOptionPane.showConfirmDialog(null,
                        "¿Seguro que deseas salir?", "Salir",
                        JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION)
                    System.exit(0);
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            } // minimizada

            @Override
            public void windowDeiconified(WindowEvent e) {
            } // restaurada
        });

        // ════════════════════════════════════════════════════════
        // 17. TEMPORIZADORES (Timer)
        // ════════════════════════════════════════════════════════

        // Timer de Swing (hilo de la UI): dispara cada N ms
        Timer timer = new Timer(1000, e -> {
            lblStatus.setText(" " + new java.text.SimpleDateFormat("HH:mm:ss").format(new Date()));
        });
        timer.setRepeats(true); // false = ejecutar solo una vez
        timer.setInitialDelay(0); // Delay antes del primer disparo
        timer.start();
        // timer.stop() / timer.restart() para controlar

        // ════════════════════════════════════════════════════════
        // 18. COLORES Y FUENTES ÚTILES
        // ════════════════════════════════════════════════════════
        /*
         * Color - formas de crearlo:
         * new Color(255, 0, 0) → RGB (0-255 cada canal)
         * new Color(255, 0, 0, 128) → RGBA (último es opacidad 0-255)
         * Color.decode("#FF5733") → Hex
         * new Color(0xFF5733) → Hex como int
         * Color.RED, Color.BLUE, Color.GREEN, Color.WHITE,
         * Color.BLACK, Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY,
         * Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.ORANGE, Color.PINK
         *
         * Font - estilos y familias comunes:
         * new Font("Segoe UI", Font.PLAIN, 14) → Windows moderno
         * new Font("Arial", Font.BOLD, 12)
         * new Font("Courier New", Font.PLAIN, 12) → monoespaciada
         * new Font("Serif", Font.ITALIC, 16)
         * new Font("SansSerif", Font.BOLD, 18)
         * Font.deriveFont(Font.BOLD) → derivar estilo
         * Font.deriveFont(20f) → derivar tamaño
         *
         * Cursores predefinidos:
         * Cursor.DEFAULT_CURSOR → flecha normal
         * Cursor.HAND_CURSOR → manito (links)
         * Cursor.TEXT_CURSOR → cursor de texto (I)
         * Cursor.WAIT_CURSOR → rueda de carga
         * Cursor.CROSSHAIR_CURSOR → cruz
         * Cursor.MOVE_CURSOR → cuatro flechas
         * Cursor.N_RESIZE_CURSOR → flecha de redimensión
         */

        // ════════════════════════════════════════════════════════
        // 19. CONFIGURACIÓN FINAL
        // ════════════════════════════════════════════════════════

        pack(); // Ajusta el tamaño al contenido mínimo
        setMinimumSize(new Dimension(600, 450));
        setLocationRelativeTo(null); // Centrar DESPUÉS de pack()
        setVisible(true); // ← siempre al final
    }

    // ════════════════════════════════════════════════════════════
    // 20. MÉTODO: TODOS LOS TIPOS DE DIÁLOGOS
    // ════════════════════════════════════════════════════════════

    private void mostrarDialogos() {

        // ── Mensaje simple ──────────────────────────────────────
        JOptionPane.showMessageDialog(this,
                "Operación completada.", // Mensaje
                "Éxito", // Título
                JOptionPane.INFORMATION_MESSAGE); // Tipo de ícono:
        // INFORMATION_MESSAGE | WARNING_MESSAGE | ERROR_MESSAGE
        // QUESTION_MESSAGE | PLAIN_MESSAGE

        // ── Confirmación Sí/No ──────────────────────────────────
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Deseas continuar?", "Confirmar",
                JOptionPane.YES_NO_OPTION, // YES_NO | YES_NO_CANCEL | OK_CANCEL
                JOptionPane.QUESTION_MESSAGE);
        // respuesta: YES_OPTION=0 | NO_OPTION=1 | CANCEL_OPTION=2 | CLOSED_OPTION=-1

        // ── Input (texto) ───────────────────────────────────────
        String entrada = JOptionPane.showInputDialog(this,
                "¿Cuál es tu nombre?", "Ingreso", JOptionPane.PLAIN_MESSAGE);
        // entrada == null → canceló | entrada.isEmpty() → dejó vacío

        // ── Input con opciones (combo) ───────────────────────────
        String[] opciones = { "Rojo", "Verde", "Azul" };
        String color = (String) JOptionPane.showInputDialog(this,
                "Elige un color:", "Selección",
                JOptionPane.QUESTION_MESSAGE,
                null, // ícono personalizado (null = default)
                opciones, // valores del combo
                opciones[0]); // valor inicial

        // ── Diálogo con botones personalizados ──────────────────
        Object[] botones = { "Guardar", "Descartar", "Cancelar" };
        int opcion = JOptionPane.showOptionDialog(this,
                "¿Qué deseas hacer con los cambios?", "Cambios sin guardar",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null, botones, botones[0]);
        // opcion: 0=Guardar | 1=Descartar | 2=Cancelar | -1=cerró con X

        // ── JDialog personalizado (ventana modal propia) ─────────
        JDialog dialogo = new JDialog(this, "Formulario", true); // true = modal
        dialogo.setSize(350, 200);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout(10, 10));

        JPanel contenido = new JPanel(new GridLayout(2, 2, 8, 8));
        contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
        contenido.add(new JLabel("Usuario:"));
        contenido.add(new JTextField(15));
        contenido.add(new JLabel("Email:"));
        contenido.add(new JTextField(15));

        JPanel botonesD = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okBtn = new JButton("Aceptar");
        JButton cancelBtn = new JButton("Cancelar");
        okBtn.addActionListener(e -> dialogo.dispose());
        cancelBtn.addActionListener(e -> dialogo.dispose());
        botonesD.add(cancelBtn);
        botonesD.add(okBtn);

        dialogo.add(contenido, BorderLayout.CENTER);
        dialogo.add(botonesD, BorderLayout.SOUTH);
        dialogo.setVisible(true); // Bloqueante si es modal

        // ── JFileChooser (selector de archivos) ─────────────────
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // FILES_ONLY | DIRECTORIES_ONLY | FILES_AND_DIRECTORIES
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imágenes (*.png, *.jpg)", "png", "jpg", "jpeg"));
        int resultado = fileChooser.showOpenDialog(this); // showSaveDialog para guardar
        if (resultado == JFileChooser.APPROVE_OPTION) {
            java.io.File archivo = fileChooser.getSelectedFile();
        }

        // ── JColorChooser (selector de color) ───────────────────
        Color colorElegido = JColorChooser.showDialog(this, "Elige un color", Color.BLUE);
        // colorElegido == null → canceló

        // ── JFontChooser no existe en Swing estándar, pero puedes
        // usar Font.decode() o implementar el tuyo propio ───────
    }

    // ════════════════════════════════════════════════════════════
    // PUNTO DE ENTRADA
    // ════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        // SwingUtilities.invokeLater garantiza que la UI se crea
        // en el hilo de despacho de eventos (EDT) — obligatorio en Swing
        SwingUtilities.invokeLater(() -> new JFrame_Completo());
    }
}