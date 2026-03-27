package projects;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.print.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import javax.imageio.ImageIO;

/**
 * ══════════════════════════════════════════════════════════════
 *   GUÍA AVANZADA — LO QUE FALTA EN SWING / JFRAME
 *   Complemento del archivo JFrame_Completo.java
 *   Temas: SwingWorker, JTextPane, JEditorPane, JInternalFrame,
 *          JDesktopPane, JLayeredPane, Drag&Drop, Portapapeles,
 *          System Tray, Key Bindings, Undo/Redo, Acciones,
 *          Renderizadores personalizados, SpringLayout, GroupLayout,
 *          AffineTransform, BufferedImage, Impresión, y más.
 * ══════════════════════════════════════════════════════════════
 */
public class JFrame_Avanzado extends JFrame {

    public JFrame_Avanzado() {
        setTitle("Swing Avanzado");
        setSize(1000, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(6, 6));

        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabs, BorderLayout.CENTER);

        // ──────────────────────────────────────────────────────
        //  Pestaña 1 – JToggleButton, JToolBar avanzada, Actions
        // ──────────────────────────────────────────────────────
        tabs.addTab("Botones y Actions", buildTabBotones());

        // ──────────────────────────────────────────────────────
        //  Pestaña 2 – JTextPane, JEditorPane, Undo/Redo
        // ──────────────────────────────────────────────────────
        tabs.addTab("Texto Avanzado", buildTabTexto());

        // ──────────────────────────────────────────────────────
        //  Pestaña 3 – JInternalFrame + JDesktopPane (MDI)
        // ──────────────────────────────────────────────────────
        tabs.addTab("MDI (Ventanas Internas)", buildTabMDI());

        // ──────────────────────────────────────────────────────
        //  Pestaña 4 – JLayeredPane + componentes superpuestos
        // ──────────────────────────────────────────────────────
        tabs.addTab("JLayeredPane", buildTabLayered());

        // ──────────────────────────────────────────────────────
        //  Pestaña 5 – Renderizadores y editores personalizados
        // ──────────────────────────────────────────────────────
        tabs.addTab("Render Custom", buildTabRender());

        // ──────────────────────────────────────────────────────
        //  Pestaña 6 – Layouts: SpringLayout y GroupLayout
        // ──────────────────────────────────────────────────────
        tabs.addTab("Layouts Extra", buildTabLayouts());

        // ──────────────────────────────────────────────────────
        //  Pestaña 7 – Graphics2D Avanzado (Transform, Clips…)
        // ──────────────────────────────────────────────────────
        tabs.addTab("Graphics2D Avanzado", buildTabGraphics());

        // ──────────────────────────────────────────────────────
        //  Pestaña 8 – SwingWorker + Portapapeles + Drag&Drop
        // ──────────────────────────────────────────────────────
        tabs.addTab("Hilos / Clipboard / D&D", buildTabMisc());

        // ──────────────────────────────────────────────────────
        //  Key Bindings (mapas de teclas) — alternativos a KeyListener
        // ──────────────────────────────────────────────────────
        configurarKeyBindings();

        // ──────────────────────────────────────────────────────
        //  System Tray (ícono en bandeja del SO)
        // ──────────────────────────────────────────────────────
        configurarSystemTray();

        // ──────────────────────────────────────────────────────
        //  Personalización de UIManager (colores globales)
        // ──────────────────────────────────────────────────────
        personalizarUIManager();

        pack();
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
        setVisible(true);
    }


    // ════════════════════════════════════════════════════════════
    //  1. BOTONES AVANZADOS Y ACTIONS
    // ════════════════════════════════════════════════════════════
    private JPanel buildTabBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        panel.setBackground(Color.WHITE);

        // ── JToggleButton ───────────────────────────────────────
        // Como JButton pero mantiene estado presionado/no presionado
        JToggleButton tglBold = new JToggleButton("B");
        tglBold.setFont(new Font("Serif", Font.BOLD, 14));
        tglBold.setSelected(false);
        tglBold.addItemListener(e -> {
            boolean activo = (e.getStateChange() == ItemEvent.SELECTED);
            // activo == true → botón presionado/activado
        });

        // ── Grupo de ToggleButtons (exclusivo, como RadioButtons) ─
        ButtonGroup grupoAlineacion = new ButtonGroup();
        JToggleButton tglLeft   = new JToggleButton("⬅");
        JToggleButton tglCenter = new JToggleButton("⬛");
        JToggleButton tglRight  = new JToggleButton("➡");
        grupoAlineacion.add(tglLeft);
        grupoAlineacion.add(tglCenter);
        grupoAlineacion.add(tglRight);
        tglCenter.setSelected(true); // Seleccionado por defecto

        panel.add(new JLabel("ToggleButton:"));
        panel.add(tglBold);
        panel.add(new JLabel(" Grupo exclusivo:"));
        panel.add(tglLeft);
        panel.add(tglCenter);
        panel.add(tglRight);

        // ── AbstractAction (reutilizable en menú + botón + atajo) ──
        // Una Action encapsula ícono, nombre, tooltip y el listener
        // en un solo objeto que puedes compartir entre múltiples widgets.
        AbstractAction accionGuardar = new AbstractAction("Guardar") {
            {
                // Propiedades de la Action
                putValue(SHORT_DESCRIPTION, "Guarda el archivo actual");    // Tooltip
                putValue(MNEMONIC_KEY, KeyEvent.VK_G);                      // Alt+G
                putValue(ACCELERATOR_KEY,
                    KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)); // Ctrl+S
                putValue(SMALL_ICON, UIManager.getIcon("FileView.floppyDriveIcon")); // ícono 16px
                putValue(LARGE_ICON_KEY, UIManager.getIcon("FileView.floppyDriveIcon")); // ícono 24px
                // Deshabilitar la acción completa (afecta a todos los widgets que la usan):
                // setEnabled(false);
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Guardando...");
            }
        };

        // La misma Action conectada a un botón, a un ítem de menú y a una toolbar
        JButton      btnAccion   = new JButton(accionGuardar);
        JMenuItem    menuAccion  = new JMenuItem(accionGuardar);
        JToolBar     toolbarAcc  = new JToolBar();
        toolbarAcc.add(accionGuardar);
        toolbarAcc.setFloatable(false);

        panel.add(new JSeparator(JSeparator.VERTICAL));
        panel.add(new JLabel("AbstractAction → botón:"));
        panel.add(btnAccion);
        panel.add(toolbarAcc);

        // ── JScrollBar standalone ───────────────────────────────
        // (Normalmente dentro de JScrollPane, pero puede usarse solo)
        JScrollBar scrollH = new JScrollBar(JScrollBar.HORIZONTAL, 50, 10, 0, 100);
        // (orientación, valorInicial, extensión, mín, máx)
        scrollH.setPreferredSize(new Dimension(200, 20));
        scrollH.addAdjustmentListener(e -> {
            int valor = e.getValue(); // valor actual
            // e.getAdjustmentType(): UNIT_INCREMENT | UNIT_DECREMENT |
            //                        BLOCK_INCREMENT | BLOCK_DECREMENT | TRACK
        });

        panel.add(new JLabel("  JScrollBar:"));
        panel.add(scrollH);

        // ── JScrollPane avanzado ────────────────────────────────
        JTextArea area = new JTextArea("Texto de prueba\nLínea 2\nLínea 3\n".repeat(5), 6, 30);
        JScrollPane scroll = new JScrollPane(area);

        // Columna de encabezado (filas numeradas, por ejemplo)
        JPanel rowHeader = new JPanel(new GridLayout(0, 1));
        rowHeader.setBackground(new Color(240, 240, 240));
        for (int i = 1; i <= 15; i++) {
            JLabel num = new JLabel(String.format(" %2d ", i), SwingConstants.RIGHT);
            num.setFont(new Font("Monospaced", Font.PLAIN, 11));
            num.setForeground(Color.GRAY);
            rowHeader.add(num);
        }
        scroll.setRowHeaderView(rowHeader); // Panel lateral izquierdo
        // scroll.setColumnHeaderView(comp); // Panel superior (como en tablas)
        // scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, comp); // Esquina

        // Política de scroll
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Velocidad del scroll (unidades y bloques)
        scroll.getVerticalScrollBar().setUnitIncrement(16);   // px por clic en flecha
        scroll.getVerticalScrollBar().setBlockIncrement(80);  // px por clic en pista

        JPanel panelScroll = new JPanel(new BorderLayout());
        panelScroll.add(new JLabel("JScrollPane avanzado (con RowHeader):"), BorderLayout.NORTH);
        panelScroll.add(scroll, BorderLayout.CENTER);
        panel.add(panelScroll);

        return panel;
    }


    // ════════════════════════════════════════════════════════════
    //  2. JTEXTPANE, JEDITORPANE, UNDO/REDO
    // ════════════════════════════════════════════════════════════
    private JPanel buildTabTexto() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ── JTextPane ───────────────────────────────────────────
        // Texto enriquecido con estilos (negrita, color, tamaño por fragmento)
        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();

        // Definir estilos
        Style estiloBase   = StyleContext.getDefaultStyleContext()
                                .getStyle(StyleContext.DEFAULT_STYLE);

        Style estiloTitulo = doc.addStyle("titulo", estiloBase);
        StyleConstants.setFontSize(estiloTitulo, 18);
        StyleConstants.setBold(estiloTitulo, true);
        StyleConstants.setForeground(estiloTitulo, new Color(30, 80, 180));
        StyleConstants.setAlignment(estiloTitulo, StyleConstants.ALIGN_CENTER);

        Style estiloNormal = doc.addStyle("normal", estiloBase);
        StyleConstants.setFontSize(estiloNormal, 13);
        StyleConstants.setBold(estiloNormal, false);
        StyleConstants.setForeground(estiloNormal, Color.DARK_GRAY);

        Style estiloResaltado = doc.addStyle("resaltado", estiloNormal);
        StyleConstants.setBackground(estiloResaltado, Color.YELLOW);
        StyleConstants.setItalic(estiloResaltado, true);

        Style estiloEnlace = doc.addStyle("enlace", estiloNormal);
        StyleConstants.setForeground(estiloEnlace, Color.BLUE);
        StyleConstants.setUnderline(estiloEnlace, true);

        // Insertar texto con estilos
        try {
            doc.insertString(doc.getLength(), "JTextPane – Texto Enriquecido\n\n", estiloTitulo);
            doc.insertString(doc.getLength(), "Este es texto normal. ", estiloNormal);
            doc.insertString(doc.getLength(), "Este fragmento está resaltado en amarillo. ", estiloResaltado);
            doc.insertString(doc.getLength(), "Y este parece un enlace.\n\n", estiloEnlace);
            doc.insertString(doc.getLength(),
                "Puedes combinar fuentes, colores, alineación y más por fragmento.", estiloNormal);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        // Aplicar párrafo centrado al primer párrafo:
        doc.setParagraphAttributes(0, 30, estiloTitulo, false);

        // Insertar un componente dentro del texto (ícono/botón embebido)
        textPane.setCaretPosition(doc.getLength());
        textPane.insertComponent(new JButton("Botón dentro del texto"));

        // ── Undo / Redo ─────────────────────────────────────────
        UndoManager undoManager = new UndoManager();
        undoManager.setLimit(50); // Máximo de acciones recordadas
        doc.addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));

        JButton btnUndo = new JButton("↩ Deshacer");
        JButton btnRedo = new JButton("↪ Rehacer");

        btnUndo.addActionListener(e -> {
            if (undoManager.canUndo()) undoManager.undo();
        });
        btnRedo.addActionListener(e -> {
            if (undoManager.canRedo()) undoManager.redo();
        });

        // Actualizar estado de botones según disponibilidad
        doc.addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e)  { actualizar(); }
            @Override public void removeUpdate(DocumentEvent e)  { actualizar(); }
            @Override public void changedUpdate(DocumentEvent e) { actualizar(); }
            void actualizar() {
                btnUndo.setEnabled(undoManager.canUndo());
                btnRedo.setEnabled(undoManager.canRedo());
                // undoManager.getUndoPresentationName() → nombre de la acción a deshacer
            }
        });

        // ── JEditorPane ─────────────────────────────────────────
        // Puede mostrar HTML o RTF directamente
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html"); // "text/plain" | "text/html" | "text/rtf"
        editorPane.setText("""
            <html><body style='font-family:Segoe UI; padding:8px;'>
              <h2 style='color:#336699'>HTML dentro de Swing</h2>
              <p>Puedes renderizar <b>HTML básico</b> con <i>JEditorPane</i>.</p>
              <ul>
                <li>Listas</li><li>Tablas</li><li>Colores</li>
              </ul>
              <a href='https://example.com'>Enlace clicable</a>
            </body></html>
            """);
        editorPane.setEditable(false); // false = solo lectura (para mostrar HTML)
        // Maneja clics en hipervínculos:
        editorPane.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String url = e.getURL().toString();
                // Abrir el navegador: Desktop.getDesktop().browse(e.getURL().toURI());
            }
        });
        // Cargar página web remota:
        // editorPane.setPage(new URL("https://example.com"));

        JSplitPane splitTexto = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(textPane),
            new JScrollPane(editorPane));
        splitTexto.setResizeWeight(0.55);

        JPanel barraTexto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barraTexto.add(btnUndo);
        barraTexto.add(btnRedo);

        // ── DocumentListener (escuchar cambios en cualquier JTextComponent) ──
        JTextField txtVivo = new JTextField("Escribe aquí...", 20);
        JLabel lblContador = new JLabel("0 caracteres");
        txtVivo.getDocument().addDocumentListener(new DocumentListener() {
            void update() { lblContador.setText(txtVivo.getText().length() + " caracteres"); }
            @Override public void insertUpdate(DocumentEvent e)  { update(); }
            @Override public void removeUpdate(DocumentEvent e)  { update(); }
            @Override public void changedUpdate(DocumentEvent e) { update(); }
        });

        // ── DocumentFilter (filtrar lo que se puede escribir) ────
        // Ejemplo: solo números en un campo
        JTextField txtSoloNumeros = new JTextField(10);
        ((AbstractDocument) txtSoloNumeros.getDocument()).setDocumentFilter(
            new DocumentFilter() {
                @Override
                public void insertString(FilterBypass fb, int offset,
                                         String string, AttributeSet attr)
                        throws BadLocationException {
                    if (string.matches("[0-9]*")) // Solo deja pasar dígitos
                        super.insertString(fb, offset, string, attr);
                }
                @Override
                public void replace(FilterBypass fb, int offset, int length,
                                    String text, AttributeSet attrs)
                        throws BadLocationException {
                    if (text.matches("[0-9]*"))
                        super.replace(fb, offset, length, text, attrs);
                }
            }
        );

        JPanel panelDocumentos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDocumentos.add(new JLabel("DocumentListener:"));
        panelDocumentos.add(txtVivo);
        panelDocumentos.add(lblContador);
        panelDocumentos.add(new JLabel("  Solo números:"));
        panelDocumentos.add(txtSoloNumeros);

        panel.add(barraTexto, BorderLayout.NORTH);
        panel.add(splitTexto, BorderLayout.CENTER);
        panel.add(panelDocumentos, BorderLayout.SOUTH);
        return panel;
    }


    // ════════════════════════════════════════════════════════════
    //  3. MDI — JDesktopPane + JInternalFrame
    // ════════════════════════════════════════════════════════════
    private JPanel buildTabMDI() {
        JPanel panel = new JPanel(new BorderLayout());

        // JDesktopPane es el "escritorio" que contiene ventanas internas
        JDesktopPane desktop = new JDesktopPane();
        desktop.setBackground(new Color(100, 120, 150));
        // Política de arrastre: LIVE_DRAG_MODE (redibuja en vivo) | OUTLINE_DRAG_MODE (solo silueta)
        desktop.setDragMode(JDesktopPane.LIVE_DRAG_MODE);

        // Crear ventanas internas
        for (int i = 1; i <= 3; i++) {
            JInternalFrame interna = new JInternalFrame(
                "Documento " + i,  // Título
                true,              // resizable
                true,              // closable (botón X)
                true,              // maximizable
                true               // iconifiable (minimizable al escritorio)
            );
            interna.setSize(280, 200);
            interna.setLocation((i - 1) * 60 + 20, (i - 1) * 40 + 20);

            JTextArea areaInterna = new JTextArea("Contenido de la ventana " + i);
            interna.add(new JScrollPane(areaInterna));

            // Eventos de JInternalFrame
            interna.addInternalFrameListener(new InternalFrameAdapter() {
                @Override public void internalFrameActivated(InternalFrameEvent e)   { }
                @Override public void internalFrameDeactivated(InternalFrameEvent e) { }
                @Override public void internalFrameClosing(InternalFrameEvent e)     { }
                @Override public void internalFrameClosed(InternalFrameEvent e)      { }
                @Override public void internalFrameIconified(InternalFrameEvent e)   { }
                @Override public void internalFrameDeiconified(InternalFrameEvent e) { }
                @Override public void internalFrameOpened(InternalFrameEvent e)      { }
            });

            interna.setVisible(true);   // ¡Obligatorio antes de agregar al desktop!
            desktop.add(interna);

            // interna.setSelected(true); → poner en primer plano (requiere try/catch)
            // interna.toFront() / interna.toBack() → orden Z
            // interna.setIcon(true) → minimizar programáticamente
            // interna.setMaximum(true) → maximizar programáticamente
            // desktop.getAllFrames() → obtener todas las ventanas internas
        }

        JToolBar barMDI = new JToolBar();
        barMDI.setFloatable(false);
        JButton btnNueva = new JButton("+ Nueva ventana");
        btnNueva.addActionListener(e -> {
            int n = desktop.getAllFrames().length + 1;
            JInternalFrame nueva = new JInternalFrame("Nuevo " + n, true, true, true, true);
            nueva.setSize(260, 180);
            nueva.setLocation(n * 30, n * 20);
            nueva.add(new JLabel("Ventana nueva " + n, SwingConstants.CENTER));
            nueva.setVisible(true);
            desktop.add(nueva);
            try { nueva.setSelected(true); } catch (Exception ignored) {}
        });
        JButton btnMosaico = new JButton("Mosaico");
        btnMosaico.addActionListener(e -> {
            JInternalFrame[] frames = desktop.getAllFrames();
            int cols = (int) Math.ceil(Math.sqrt(frames.length));
            int rows = (int) Math.ceil((double) frames.length / cols);
            int w = desktop.getWidth() / cols;
            int h = desktop.getHeight() / rows;
            for (int i = 0; i < frames.length; i++) {
                try {
                    frames[i].setIcon(false);
                    frames[i].setMaximum(false);
                } catch (Exception ignored) {}
                frames[i].setBounds((i % cols) * w, (i / cols) * h, w, h);
            }
        });
        barMDI.add(btnNueva);
        barMDI.addSeparator();
        barMDI.add(btnMosaico);

        panel.add(barMDI, BorderLayout.NORTH);
        panel.add(desktop, BorderLayout.CENTER);
        return panel;
    }


    // ════════════════════════════════════════════════════════════
    //  4. JLAYEREDPANE — SUPERPOSICIÓN DE COMPONENTES
    // ════════════════════════════════════════════════════════════
    private JPanel buildTabLayered() {
        JPanel panel = new JPanel(new BorderLayout());

        // JLayeredPane permite apilar componentes en capas Z
        JLayeredPane layered = new JLayeredPane();
        layered.setPreferredSize(new Dimension(500, 350));
        layered.setBackground(Color.LIGHT_GRAY);

        // Capas predefinidas (de más profunda a más superficial):
        // DEFAULT_LAYER (0) | PALETTE_LAYER (100) | MODAL_LAYER (200)
        // POPUP_LAYER (300) | DRAG_LAYER (400)
        // También se puede usar cualquier Integer

        JPanel fondo = new JPanel();
        fondo.setBackground(new Color(200, 220, 255));
        fondo.setBounds(0, 0, 500, 350);
        fondo.add(new JLabel("Capa DEFAULT (fondo)"));

        JPanel medio = new JPanel();
        medio.setBackground(new Color(255, 220, 180, 200)); // semi-transparente
        medio.setBounds(60, 60, 300, 200);
        medio.setBorder(BorderFactory.createTitledBorder("Capa PALETTE (encima)"));

        JButton botonTop = new JButton("Capa MODAL (más arriba)");
        botonTop.setBounds(120, 130, 220, 40);

        layered.add(fondo,     JLayeredPane.DEFAULT_LAYER);
        layered.add(medio,     JLayeredPane.PALETTE_LAYER);
        layered.add(botonTop,  JLayeredPane.MODAL_LAYER);

        // Cambiar la posición dentro de la misma capa:
        // layered.setPosition(componente, 0); // 0 = frente, -1 = atrás dentro de capa

        // Mover un componente a otra capa:
        // layered.setLayer(componente, JLayeredPane.DRAG_LAYER);

        // Obtener la capa de un componente:
        // int capa = layered.getLayer(componente);

        JLabel nota = new JLabel("<html>JLayeredPane permite superposición Z.<br>"
            + "Útil para overlays, tooltips, arrastre, glasspanes.</html>");
        nota.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        panel.add(layered, BorderLayout.CENTER);
        panel.add(nota, BorderLayout.SOUTH);

        // ── GlassPane ────────────────────────────────────────────
        // Capa invisible sobre TODA la ventana principal (JFrame)
        // Útil para bloquear interacción, mostrar overlays de carga, etc.
        JPanel glassPane = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 80)); // Oscurecer toda la ventana
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.WHITE);
                g.setFont(new Font("Segoe UI", Font.BOLD, 20));
                g.drawString("Cargando...", getWidth()/2 - 60, getHeight()/2);
            }
        };
        glassPane.setOpaque(false);
        glassPane.setVisible(false); // Oculto por defecto
        // Para activarlo sobre el JFrame principal:
        // JFrame_Avanzado.this.setGlassPane(glassPane);
        // glassPane.setVisible(true);
        // glassPane.setVisible(false); // Para ocultarlo

        return panel;
    }


    // ════════════════════════════════════════════════════════════
    //  5. RENDERIZADORES Y EDITORES PERSONALIZADOS
    // ════════════════════════════════════════════════════════════
    private JPanel buildTabRender() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // ── ListCellRenderer personalizado ─────────────────────
        // Controla cómo se dibuja cada ítem de un JList
        DefaultListModel<String[]> modeloColores = new DefaultListModel<>();
        modeloColores.addElement(new String[]{"#E74C3C", "Rojo Tomate"});
        modeloColores.addElement(new String[]{"#2ECC71", "Verde Esmeralda"});
        modeloColores.addElement(new String[]{"#3498DB", "Azul Cielo"});
        modeloColores.addElement(new String[]{"#F39C12", "Naranja Ámbar"});
        modeloColores.addElement(new String[]{"#9B59B6", "Morado Amatista"});

        JList<String[]> listaColores = new JList<>(modeloColores);
        listaColores.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
            item.setBackground(isSelected ? new Color(200, 220, 255) : Color.WHITE);
            // Cuadrito de color
            JPanel cuadro = new JPanel();
            cuadro.setBackground(Color.decode(value[0]));
            cuadro.setPreferredSize(new Dimension(22, 22));
            cuadro.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            // Texto con el nombre del color
            JLabel lbl = new JLabel(value[1]);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            item.add(cuadro);
            item.add(lbl);
            return item;
        });

        // ── TreeCellRenderer personalizado ─────────────────────
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Proyecto");
        DefaultMutableTreeNode src  = new DefaultMutableTreeNode("src");
        DefaultMutableTreeNode res  = new DefaultMutableTreeNode("resources");
        src.add(new DefaultMutableTreeNode("Main.java"));
        src.add(new DefaultMutableTreeNode("Utils.java"));
        res.add(new DefaultMutableTreeNode("icon.png"));
        raiz.add(src); raiz.add(res);

        JTree arbol = new JTree(raiz);
        arbol.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(
                    JTree tree, Object value, boolean selected,
                    boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                String texto = value.toString();
                // Ícono diferente por extensión
                if (texto.endsWith(".java")) {
                    setIcon(UIManager.getIcon("FileView.fileIcon"));
                    setForeground(new Color(0, 100, 200));
                } else if (texto.endsWith(".png")) {
                    setIcon(UIManager.getIcon("FileView.fileIcon"));
                    setForeground(new Color(0, 150, 0));
                } else if (leaf) {
                    setForeground(Color.DARK_GRAY);
                }
                return this;
            }
        });
        for (int i = 0; i < arbol.getRowCount(); i++) arbol.expandRow(i);

        // ── TableCellRenderer + TableCellEditor personalizados ──
        String[] cols = {"Tarea", "Prioridad", "% Avance", "Color"};
        Object[][] datos = {
            {"Diseño UI",     "Alta",   85, Color.RED},
            {"Base de datos", "Media",  40, Color.ORANGE},
            {"Testing",       "Baja",   10, Color.GREEN},
        };
        DefaultTableModel modeloTabla = new DefaultTableModel(datos, cols) {
            @Override public Class<?> getColumnClass(int c) {
                return switch (c) { case 2 -> Integer.class; case 3 -> Color.class; default -> String.class; };
            }
        };
        JTable tabla = new JTable(modeloTabla);
        tabla.setRowHeight(32);

        // Renderizador de JProgressBar en celda
        tabla.getColumnModel().getColumn(2).setCellRenderer((t, value, isSelected, hasFocus, row, col) -> {
            JProgressBar bar = new JProgressBar(0, 100);
            bar.setValue((Integer) value);
            bar.setStringPainted(true);
            int v = (Integer) value;
            bar.setForeground(v >= 70 ? new Color(46,139,87) : v >= 40 ? Color.ORANGE : Color.RED);
            return bar;
        });

        // Renderizador de color (muestra cuadro de color)
        tabla.getColumnModel().getColumn(3).setCellRenderer((t, value, isSelected, hasFocus, row, col) -> {
            JPanel p = new JPanel();
            p.setBackground((Color) value);
            p.setToolTipText(value.toString());
            return p;
        });

        // Editor de celda con JComboBox
        JComboBox<String> comboPrio = new JComboBox<>(new String[]{"Alta","Media","Baja"});
        tabla.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboPrio));

        // Editor de celda con JColorChooser (personalizado)
        // ⚠️ Las clases anónimas NO pueden extender AbstractCellEditor e implementar
        //    TableCellEditor al mismo tiempo → se usa una clase local nombrada.
        class ColorCellEditor extends AbstractCellEditor implements TableCellEditor {
            Color colorActual;
            final JButton botonColor = new JButton();

            ColorCellEditor() {
                botonColor.addActionListener(e -> {
                    Color elegido = JColorChooser.showDialog(botonColor, "Color", colorActual);
                    if (elegido != null) {          // null = el usuario canceló
                        colorActual = elegido;
                        botonColor.setBackground(colorActual);
                    }
                    fireEditingStopped();            // confirma la edición
                });
            }

            @Override
            public Object getCellEditorValue() { return colorActual; }

            @Override
            public Component getTableCellEditorComponent(
                    JTable t, Object value, boolean isSelected, int row, int col) {
                colorActual = (Color) value;
                botonColor.setBackground(colorActual);
                return botonColor;
            }
        }
        tabla.getColumnModel().getColumn(3).setCellEditor(new ColorCellEditor());

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Tabla con renderers/editors"));
        panelTabla.add(new JScrollPane(tabla));

        panel.add(new JScrollPane(listaColores) {{
            setBorder(BorderFactory.createTitledBorder("ListCellRenderer"));
        }});
        panel.add(new JScrollPane(arbol) {{
            setBorder(BorderFactory.createTitledBorder("TreeCellRenderer"));
        }});
        panel.add(panelTabla);

        return panel;
    }


    // ════════════════════════════════════════════════════════════
    //  6. SPRINGLAYOUT Y GROUPLAYOUT
    // ════════════════════════════════════════════════════════════
    private JPanel buildTabLayouts() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // ── SpringLayout ────────────────────────────────────────
        // Posicionamiento relativo: cada borde de un componente se
        // ancla al borde de otro componente con un resorte (spring).
        JPanel panelSpring = new JPanel();
        SpringLayout spring = new SpringLayout();
        panelSpring.setLayout(spring);
        panelSpring.setBorder(BorderFactory.createTitledBorder("SpringLayout"));
        panelSpring.setBackground(new Color(250, 250, 240));

        JLabel  lUser  = new JLabel("Usuario:");
        JLabel  lPass  = new JLabel("Contraseña:");
        JLabel  lEmail = new JLabel("Email:");
        JTextField tUser  = new JTextField(15);
        JTextField tPass  = new JTextField(15);
        JTextField tEmail = new JTextField(15);
        JButton    bOk    = new JButton("Aceptar");

        panelSpring.add(lUser);  panelSpring.add(tUser);
        panelSpring.add(lPass);  panelSpring.add(tPass);
        panelSpring.add(lEmail); panelSpring.add(tEmail);
        panelSpring.add(bOk);

        int gap = 10, labelW = 95, fieldH = 26;
        // Anclar etiquetas al borde WEST del contenedor
        for (JLabel lbl : new JLabel[]{lUser, lPass, lEmail}) {
            spring.putConstraint(SpringLayout.WEST, lbl, gap, SpringLayout.WEST, panelSpring);
        }
        // Anclar campos al borde EAST de la etiqueta + gap
        for (int i = 0; i < 3; i++) {
            JLabel   lbl   = new JLabel[]{lUser, lPass, lEmail}[i];
            JTextField fld = new JTextField[]{tUser, tPass, tEmail}[i];
            spring.putConstraint(SpringLayout.NORTH, lbl, gap + 30 + i * (fieldH + gap),
                                 SpringLayout.NORTH, panelSpring);
            spring.putConstraint(SpringLayout.NORTH, fld, gap + 30 + i * (fieldH + gap),
                                 SpringLayout.NORTH, panelSpring);
            spring.putConstraint(SpringLayout.WEST,  fld, gap, SpringLayout.EAST, lbl);
            spring.putConstraint(SpringLayout.EAST,  fld, -gap, SpringLayout.EAST, panelSpring);
        }
        spring.putConstraint(SpringLayout.NORTH, bOk, gap, SpringLayout.SOUTH, tEmail);
        spring.putConstraint(SpringLayout.EAST,  bOk, -gap, SpringLayout.EAST, panelSpring);
        spring.putConstraint(SpringLayout.SOUTH, panelSpring, gap, SpringLayout.SOUTH, bOk);

        // ── GroupLayout ─────────────────────────────────────────
        // Organiza componentes en grupos horizontales y verticales (usado por NetBeans)
        JPanel panelGroup = new JPanel();
        GroupLayout gl = new GroupLayout(panelGroup);
        panelGroup.setLayout(gl);
        panelGroup.setBorder(BorderFactory.createTitledBorder("GroupLayout"));
        panelGroup.setBackground(new Color(240, 250, 245));

        gl.setAutoCreateGaps(true);           // Gaps automáticos entre componentes
        gl.setAutoCreateContainerGaps(true);  // Gaps automáticos en los bordes

        JLabel  gLName  = new JLabel("Nombre:");
        JLabel  gLAge   = new JLabel("Edad:");
        JLabel  gLCity  = new JLabel("Ciudad:");
        JTextField gFName = new JTextField(15);
        JTextField gFAge  = new JTextField(5);
        JTextField gFCity = new JTextField(15);

        // Grupo HORIZONTAL: etiquetas en paralelo | campos en paralelo
        gl.setHorizontalGroup(gl.createSequentialGroup()
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(gLName).addComponent(gLAge).addComponent(gLCity))
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(gFName).addComponent(gFAge).addComponent(gFCity))
        );
        // Grupo VERTICAL: cada fila es un grupo secuencial alineado en baseline
        gl.setVerticalGroup(gl.createSequentialGroup()
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(gLName).addComponent(gFName))
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(gLAge).addComponent(gFAge))
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(gLCity).addComponent(gFCity))
        );

        panel.add(panelSpring);
        panel.add(panelGroup);
        return panel;
    }


    // ════════════════════════════════════════════════════════════
    //  7. GRAPHICS2D AVANZADO
    // ════════════════════════════════════════════════════════════
    private JPanel buildTabGraphics() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel canvas = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create(); // create() para no contaminar el original
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // ── AffineTransform (transformaciones 2D) ────────
                // Guardar estado original
                AffineTransform original = g2.getTransform();

                // Traslación
                g2.translate(80, 80);
                g2.setColor(new Color(200, 100, 0));
                g2.fillRect(0, 0, 80, 40);
                g2.setColor(Color.BLACK);
                g2.drawString("Trasladado", 5, 25);
                g2.setTransform(original);

                // Rotación (en radianes, alrededor de un punto)
                g2.rotate(Math.toRadians(30), 250, 100);
                g2.setColor(new Color(0, 150, 200));
                g2.fillRoundRect(200, 60, 100, 50, 15, 15);
                g2.setColor(Color.WHITE);
                g2.drawString("Rotado 30°", 207, 90);
                g2.setTransform(original);

                // Escalado
                g2.scale(1.5, 0.8);
                g2.setColor(new Color(150, 0, 200));
                g2.fillRect(250, 10, 60, 30);
                g2.setColor(Color.WHITE);
                g2.drawString("Escala", 254, 30);
                g2.setTransform(original);

                // Cizallamiento (shear)
                g2.shear(0.3, 0);
                g2.setColor(new Color(0, 180, 80));
                g2.fillRect(30, 160, 80, 40);
                g2.setColor(Color.BLACK);
                g2.drawString("Shear", 35, 185);
                g2.setTransform(original);

                // ── Clipping (recortar área de dibujo) ──────────
                // Solo dibuja dentro de la forma clip
                Shape clipCircle = new Ellipse2D.Double(350, 20, 150, 150);
                g2.setClip(clipCircle);
                g2.setPaint(new GradientPaint(350, 20, Color.MAGENTA, 500, 170, Color.YELLOW));
                g2.fillRect(350, 20, 150, 150);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial", Font.BOLD, 13));
                g2.drawString("Clip circular", 358, 100);
                g2.setClip(null); // Quitar el clip

                // ── Alpha Composite (transparencia/blending) ────
                g2.setColor(Color.BLUE);
                g2.fillRect(50, 220, 120, 80);
                // Dibujar encima con 50% de transparencia
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                // Otros modos: SRC, DST, SRC_IN, SRC_OUT, DST_IN, XOR, etc.
                g2.setColor(Color.RED);
                g2.fillRect(100, 240, 120, 80);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Reset

                // ── BufferedImage (imagen en memoria) ───────────
                BufferedImage img = new BufferedImage(120, 80, BufferedImage.TYPE_INT_ARGB);
                Graphics2D ig = img.createGraphics();
                ig.setColor(new Color(255, 200, 0));
                ig.fillOval(0, 0, 120, 80);
                ig.setColor(Color.BLACK);
                ig.drawString("BufferedImage", 8, 45);
                ig.dispose();
                g2.drawImage(img, 300, 220, null);
                // Escalar la imagen al dibujarla:
                // g2.drawImage(img, x, y, nuevoAncho, nuevoAlto, null);
                // Manipular píxeles:
                // img.setRGB(x, y, color.getRGB());
                // int argb = img.getRGB(x, y);

                // ── RadialGradient (degradado radial) ───────────
                float[] fracciones = {0.0f, 0.5f, 1.0f};
                Color[] colores    = {Color.WHITE, Color.CYAN, Color.BLUE};
                RadialGradientPaint radial = new RadialGradientPaint(
                    new Point2D.Float(500, 260),  // Centro
                    80f,                           // Radio
                    fracciones, colores
                );
                g2.setPaint(radial);
                g2.fillOval(420, 180, 160, 160);

                // ── TexturePaint (relleno con imagen mosaico) ────
                BufferedImage textura = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
                Graphics2D tg = textura.createGraphics();
                tg.setColor(Color.DARK_GRAY);
                tg.fillRect(0, 0, 10, 10);
                tg.setColor(Color.GRAY);
                tg.fillRect(5, 0, 5, 5);
                tg.fillRect(0, 5, 5, 5);
                tg.dispose();
                g2.setPaint(new TexturePaint(textura, new Rectangle(0, 0, 10, 10)));
                g2.fillRect(50, 320, 150, 60);
                g2.setColor(Color.WHITE);
                g2.drawString("TexturePaint", 70, 355);

                g2.dispose(); // Liberar el contexto creado con create()
            }
        };
        canvas.setBackground(new Color(240, 245, 250));
        canvas.setPreferredSize(new Dimension(650, 420));

        JLabel nota = new JLabel("<html><b>Temas:</b> AffineTransform · Clipping · AlphaComposite · "
            + "BufferedImage · RadialGradient · TexturePaint</html>");
        nota.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        panel.add(new JScrollPane(canvas), BorderLayout.CENTER);
        panel.add(nota, BorderLayout.SOUTH);
        return panel;
    }


    // ════════════════════════════════════════════════════════════
    //  8. SWINGWORKER · PORTAPAPELES · DRAG & DROP · IMPRESIÓN
    // ════════════════════════════════════════════════════════════
    private JPanel buildTabMisc() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ── SwingWorker (tarea en hilo de fondo) ─────────────────
        // Evita bloquear la UI durante operaciones largas.
        // Tipos genéricos: <TipoResultadoFinal, TipoActualizacionParcial>
        JPanel swPanel = new JPanel(new BorderLayout(5, 5));
        swPanel.setBorder(BorderFactory.createTitledBorder("SwingWorker (hilo de fondo)"));

        JProgressBar swBar = new JProgressBar(0, 100);
        swBar.setStringPainted(true);
        JLabel swLabel = new JLabel("Sin iniciar");
        JButton swBtn  = new JButton("Iniciar tarea");

        swBtn.addActionListener(e -> {
            swBtn.setEnabled(false);
            swLabel.setText("Trabajando...");

            SwingWorker<String, Integer> worker = new SwingWorker<>() {
                @Override
                protected String doInBackground() throws Exception {
                    // Aquí va el trabajo pesado (no toca la UI)
                    for (int i = 0; i <= 100; i += 5) {
                        Thread.sleep(80);
                        publish(i);         // Envía progreso parcial
                        setProgress(i);     // Actualiza propiedad "progress"
                    }
                    return "¡Tarea completada!";
                }

                @Override
                protected void process(java.util.List<Integer> chunks) {
                    // Llamado en el EDT con los valores publicados
                    int ultimo = chunks.get(chunks.size() - 1);
                    swBar.setValue(ultimo);
                    swLabel.setText(ultimo + "%");
                }

                @Override
                protected void done() {
                    // Llamado en el EDT cuando doInBackground termina
                    try {
                        String resultado = get(); // Puede lanzar ExecutionException
                        swLabel.setText(resultado);
                    } catch (Exception ex) {
                        swLabel.setText("Error: " + ex.getMessage());
                    }
                    swBtn.setEnabled(true);
                }
            };

            // Escuchar cambios de propiedad "progress" (alternativa a process())
            worker.addPropertyChangeListener(ev -> {
                if ("progress".equals(ev.getPropertyName()))
                    swBar.setValue((Integer) ev.getNewValue());
            });

            worker.execute(); // Inicia el worker
            // worker.cancel(true); → cancelar (doInBackground debe revisar isCancelled())
        });

        swPanel.add(swLabel, BorderLayout.NORTH);
        swPanel.add(swBar,   BorderLayout.CENTER);
        swPanel.add(swBtn,   BorderLayout.SOUTH);

        // ── Portapapeles (Clipboard) ─────────────────────────────
        JPanel clipPanel = new JPanel(new BorderLayout(5, 5));
        clipPanel.setBorder(BorderFactory.createTitledBorder("Portapapeles (Clipboard)"));

        JTextField txtClipOrigen  = new JTextField("Texto a copiar");
        JTextField txtClipDestino = new JTextField();
        JButton    btnCopiar      = new JButton("Copiar");
        JButton    btnPegar       = new JButton("Pegar");

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        btnCopiar.addActionListener(e -> {
            String texto = txtClipOrigen.getText();
            // StringSelection implementa Transferable para texto
            StringSelection sel = new StringSelection(texto);
            clipboard.setContents(sel, sel);
        });

        btnPegar.addActionListener(e -> {
            try {
                // Verificar que hay texto disponible
                if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                    String texto = (String) clipboard.getData(DataFlavor.stringFlavor);
                    txtClipDestino.setText(texto);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JPanel clipBtns = new JPanel(new FlowLayout());
        clipBtns.add(btnCopiar); clipBtns.add(btnPegar);
        clipPanel.add(new JLabel("Origen:"), BorderLayout.NORTH);
        clipPanel.add(txtClipOrigen, BorderLayout.CENTER);
        clipPanel.add(clipBtns, BorderLayout.SOUTH);

        // ── Drag & Drop (arrastrar y soltar entre componentes) ───
        JPanel dndPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        dndPanel.setBorder(BorderFactory.createTitledBorder("Drag & Drop"));

        DefaultListModel<String> modeloOrigen  = new DefaultListModel<>();
        DefaultListModel<String> modeloDestino = new DefaultListModel<>();
        modeloOrigen.addElement("Item A");
        modeloOrigen.addElement("Item B");
        modeloOrigen.addElement("Item C");

        JList<String> listaOrigen  = new JList<>(modeloOrigen);
        JList<String> listaDestino = new JList<>(modeloDestino);

        // Activar DnD nativo entre JLists con TransferHandler
        listaOrigen.setDragEnabled(true);
        listaDestino.setDragEnabled(true);
        listaOrigen.setDropMode(DropMode.INSERT);
        listaDestino.setDropMode(DropMode.INSERT);

        // TransferHandler simple para mover texto entre listas
        TransferHandler transferHandler = new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDataFlavorSupported(DataFlavor.stringFlavor);
            }
            @Override
            public boolean importData(TransferSupport support) {
                try {
                    String dato = (String) support.getTransferable()
                                                   .getTransferData(DataFlavor.stringFlavor);
                    JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
                    DefaultListModel<String> modelo =
                        (DefaultListModel<String>) ((JList<?>) support.getComponent()).getModel();
                    modelo.add(Math.min(dl.getIndex(), modelo.size()), dato);
                    return true;
                } catch (Exception ex) { return false; }
            }
            @Override
            protected Transferable createTransferable(JComponent c) {
                @SuppressWarnings("unchecked")
                JList<String> lista = (JList<String>) c;
                String valor = lista.getSelectedValue();
                return new StringSelection(valor);
            }
            @Override public int getSourceActions(JComponent c) { return COPY_OR_MOVE; }
            @Override
            protected void exportDone(JComponent source, Transferable data, int action) {
                if (action == MOVE) {
                    @SuppressWarnings("unchecked")
                    JList<String> lista = (JList<String>) source;
                    ((DefaultListModel<String>) lista.getModel())
                        .remove(lista.getSelectedIndex());
                }
            }
        };
        listaOrigen.setTransferHandler(transferHandler);
        listaDestino.setTransferHandler(transferHandler);

        JPanel panelOrig = new JPanel(new BorderLayout());
        panelOrig.add(new JLabel("Origen", SwingConstants.CENTER), BorderLayout.NORTH);
        panelOrig.add(new JScrollPane(listaOrigen));
        JPanel panelDest = new JPanel(new BorderLayout());
        panelDest.add(new JLabel("Destino", SwingConstants.CENTER), BorderLayout.NORTH);
        panelDest.add(new JScrollPane(listaDestino));
        dndPanel.add(panelOrig);
        dndPanel.add(panelDest);

        // ── Impresión (Print API) ─────────────────────────────────
        JPanel printPanel = new JPanel(new BorderLayout(5, 5));
        printPanel.setBorder(BorderFactory.createTitledBorder("Impresión"));

        JTextArea txtImprimir = new JTextArea("Contenido a imprimir...\nLínea 2\nLínea 3", 5, 25);
        JButton   btnImprimir = new JButton("🖨 Imprimir");
        JButton   btnPDF      = new JButton("Vista previa");

        btnImprimir.addActionListener(e -> {
            // Impresión rápida de un JTextComponent:
            try {
                // Abre diálogo del SO y envía a impresora
                boolean enviado = txtImprimir.print();
                if (enviado) JOptionPane.showMessageDialog(null, "Enviado a impresora.");
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        });

        btnPDF.addActionListener(e -> {
            // Impresión con PrinterJob (control total)
            PrinterJob job = PrinterJob.getPrinterJob();
            // PageFormat define el tamaño/orientación de la página
            PageFormat formato = job.defaultPage();
            // formato.setOrientation(PageFormat.LANDSCAPE);

            job.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex > 0) return Printable.NO_SUCH_PAGE;

                Graphics2D g2 = (Graphics2D) graphics;
                g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                // Dibujar en la página (coordenadas en puntos tipográficos, 72pt = 1 pulgada)
                g2.setFont(new Font("Serif", Font.BOLD, 16));
                g2.drawString("Mi Documento", 50, 50);
                g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
                String[] lineas = txtImprimir.getText().split("\n");
                for (int i = 0; i < lineas.length; i++) {
                    g2.drawString(lineas[i], 50, 80 + i * 18);
                }
                return Printable.PAGE_EXISTS;
            }, formato);

            // Mostrar diálogo de impresión del SO
            if (job.printDialog()) {
                try { job.print(); }
                catch (PrinterException ex) { ex.printStackTrace(); }
            }
        });

        JPanel printBtns = new JPanel(new FlowLayout());
        printBtns.add(btnImprimir); printBtns.add(btnPDF);
        printPanel.add(new JScrollPane(txtImprimir), BorderLayout.CENTER);
        printPanel.add(printBtns, BorderLayout.SOUTH);

        panel.add(swPanel);
        panel.add(clipPanel);
        panel.add(dndPanel);
        panel.add(printPanel);

        return panel;
    }


    // ════════════════════════════════════════════════════════════
    //  9. KEY BINDINGS (mapas de teclas — mejor que KeyListener)
    // ════════════════════════════════════════════════════════════
    private void configurarKeyBindings() {
        // Key Bindings funcionan incluso si el componente no tiene foco
        // Son más robustos que KeyListener y evitan conflictos con el foco.

        JRootPane rootPane = getRootPane();
        InputMap  inputMap  = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        // Condiciones de activación:
        // WHEN_FOCUSED                → solo cuando el componente tiene foco
        // WHEN_ANCESTOR_OF_FOCUSED_COMPONENT → cuando un hijo tiene foco
        // WHEN_IN_FOCUSED_WINDOW      → cualquier componente de la ventana tiene foco

        // Registrar tecla → clave de acción
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0),               "actualizar");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0),               "ayuda");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),           "cerrar");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "deshacer");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "rehacer");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),            "confirmar");

        // Registrar clave de acción → Action
        actionMap.put("actualizar", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                System.out.println("F5 presionado – Actualizando...");
            }
        });
        actionMap.put("ayuda", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Ayuda del sistema (F1)");
            }
        });
        actionMap.put("cerrar", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Para ELIMINAR un binding:
        // inputMap.remove(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        // actionMap.remove("actualizar");
    }


    // ════════════════════════════════════════════════════════════
    //  10. SYSTEM TRAY (ícono en bandeja del sistema)
    // ════════════════════════════════════════════════════════════
    private void configurarSystemTray() {
        if (!SystemTray.isSupported()) return; // Verificar soporte del SO

        SystemTray tray = SystemTray.getSystemTray();

        // Crear ícono (16x16 en Windows, 22x22 en Linux/Mac)
        BufferedImage imgTray = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imgTray.createGraphics();
        g.setColor(Color.BLUE);
        g.fillOval(0, 0, 16, 16);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("S", 4, 12);
        g.dispose();

        // Menú emergente al hacer clic derecho en el ícono de bandeja
        PopupMenu popupTray = new PopupMenu();
        MenuItem  miAbrir   = new MenuItem("Abrir ventana");
        MenuItem  miSalir   = new MenuItem("Salir");
        miAbrir.addActionListener(e -> {
            setVisible(true);
            setExtendedState(JFrame.NORMAL);
            toFront();
        });
        miSalir.addActionListener(e -> System.exit(0));
        popupTray.add(miAbrir);
        popupTray.addSeparator();
        popupTray.add(miSalir);

        TrayIcon trayIcon = new TrayIcon(imgTray, "Mi App Swing", popupTray);
        trayIcon.setImageAutoSize(true); // Escala el ícono automáticamente
        trayIcon.setToolTip("Mi Aplicación – Haz clic para abrir");

        // Clic simple o doble en el ícono
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    setVisible(true);
                    setExtendedState(JFrame.NORMAL);
                    toFront();
                }
            }
        });

        // Mostrar globo de notificación
        // trayIcon.displayMessage("Título", "Mensaje del globo", TrayIcon.MessageType.INFO);
        // MessageType: INFO | WARNING | ERROR | NONE

        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }

        // Ocultar ventana al minimizar (ir a bandeja en vez de barra de tareas)
        addWindowStateListener(e -> {
            if ((e.getNewState() & Frame.ICONIFIED) != 0) {
                setVisible(false); // Oculta la ventana; el ícono queda en la bandeja
            }
        });
    }


    // ════════════════════════════════════════════════════════════
    //  11. PERSONALIZACIÓN DE UIManager (temas globales)
    // ════════════════════════════════════════════════════════════
    private void personalizarUIManager() {
        // UIManager permite cambiar colores, fuentes y comportamientos globales
        // ANTES de crear los componentes para que tengan efecto.

        // Ejemplos de claves (varían según el LookAndFeel):
        UIManager.put("Button.background",         new ColorUIResource(70, 130, 180));
        UIManager.put("Button.foreground",         new ColorUIResource(Color.WHITE));
        UIManager.put("Button.font",               new Font("Segoe UI", Font.BOLD, 12));
        UIManager.put("Panel.background",          new ColorUIResource(245, 245, 250));
        UIManager.put("TextField.background",      new ColorUIResource(Color.WHITE));
        UIManager.put("TextField.selectionBackground", new ColorUIResource(173, 214, 255));
        UIManager.put("Table.selectionBackground", new ColorUIResource(100, 149, 237));
        UIManager.put("Table.selectionForeground", new ColorUIResource(Color.WHITE));
        UIManager.put("TableHeader.background",    new ColorUIResource(60, 100, 160));
        UIManager.put("TableHeader.foreground",    new ColorUIResource(Color.WHITE));
        UIManager.put("OptionPane.background",     new ColorUIResource(250, 250, 255));
        UIManager.put("ToolTip.background",        new ColorUIResource(255, 255, 220));
        UIManager.put("ToolTip.font",              new Font("Segoe UI", Font.PLAIN, 11));
        UIManager.put("TabbedPane.selected",       new ColorUIResource(200, 220, 255));
        UIManager.put("ProgressBar.foreground",    new ColorUIResource(46, 139, 87));
        UIManager.put("ScrollBar.width",           14); // Ancho de scrollbars

        // Botón por defecto del JOptionPane (el que se activa con Enter)
        // UIManager.put("OptionPane.okButtonText",     "Aceptar");
        // UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        // UIManager.put("OptionPane.yesButtonText",    "Sí");
        // UIManager.put("OptionPane.noButtonText",     "No");

        // Ver TODAS las claves disponibles del LookAndFeel actual:
        // UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        // defaults.keySet().stream().sorted(Comparator.comparing(Object::toString))
        //         .forEach(k -> System.out.println(k + " = " + defaults.get(k)));
    }


    // ════════════════════════════════════════════════════════════
    //  12. PROPIEDADES Y REFERENCIAS ADICIONALES
    // ════════════════════════════════════════════════════════════
    /*
     * ── Focus (Foco / Tabulación) ──────────────────────────────
     *   comp.requestFocus()                    → solicitar foco
     *   comp.requestFocusInWindow()            → más seguro dentro de la ventana
     *   comp.setFocusable(false)               → excluir del ciclo de tabulación
     *   comp.setNextFocusableComponent(otro)   → definir orden personalizado
     *   KeyboardFocusManager.getCurrentKeyboardFocusManager()
     *       .getFocusOwner()                   → componente con foco actual
     *   FocusTraversalPolicy personalizada:
     *     setFocusTraversalPolicy(new LayoutFocusTraversalPolicy());
     *
     * ── PropertyChangeListener ────────────────────────────────
     *   comp.addPropertyChangeListener("enabled", e -> { ... });
     *   comp.addPropertyChangeListener("text",    e -> { ... });
     *   // Escucha cambios en propiedades bound del componente.
     *
     * ── ComponentListener (cambios de tamaño/posición) ─────────
     *   comp.addComponentListener(new ComponentAdapter() {
     *       public void componentResized(ComponentEvent e)  { }
     *       public void componentMoved(ComponentEvent e)    { }
     *       public void componentShown(ComponentEvent e)    { }
     *       public void componentHidden(ComponentEvent e)   { }
     *   });
     *
     * ── HierarchyListener (cambios en jerarquía de contenedores) ─
     *   comp.addHierarchyListener(e -> {
     *       if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
     *           boolean visible = comp.isShowing();
     *       }
     *   });
     *
     * ── Múltiples monitores ────────────────────────────────────
     *   GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
     *   GraphicsDevice[]    pantallas = ge.getScreenDevices();
     *   for (GraphicsDevice pantalla : pantallas) {
     *       DisplayMode mode = pantalla.getDisplayMode();
     *       int ancho  = mode.getWidth();
     *       int alto   = mode.getHeight();
     *       // Colocar ventana en una pantalla específica:
     *       // GraphicsConfiguration gc = pantalla.getDefaultConfiguration();
     *       // Rectangle bounds = gc.getBounds();
     *       // frame.setLocation(bounds.x + 50, bounds.y + 50);
     *   }
     *
     * ── Robot (automatización de ratón/teclado y captura de pantalla) ──
     *   Robot robot = new Robot();
     *   robot.mouseMove(x, y);
     *   robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
     *   robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
     *   robot.keyPress(KeyEvent.VK_ENTER);
     *   robot.keyRelease(KeyEvent.VK_ENTER);
     *   robot.delay(500); // ms
     *   // Captura de pantalla:
     *   Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
     *   BufferedImage screenshot = robot.createScreenCapture(screenRect);
     *   ImageIO.write(screenshot, "png", new File("captura.png"));
     *
     * ── Información de pantalla ────────────────────────────────
     *   Dimension  screen  = Toolkit.getDefaultToolkit().getScreenSize();
     *   int        dpi     = Toolkit.getDefaultToolkit().getScreenResolution();
     *   // Área útil (sin barra de tareas):
     *   Insets     insets  = Toolkit.getDefaultToolkit()
     *                               .getScreenInsets(getGraphicsConfiguration());
     *   int workW = screen.width  - insets.left - insets.right;
     *   int workH = screen.height - insets.top  - insets.bottom;
     *
     * ── JTooltip personalizado ────────────────────────────────
     *   comp.setToolTipText("<html><b>Título</b><br>Descripción larga</html>");
     *   // HTML en tooltips: acepta etiquetas básicas
     *   ToolTipManager.sharedInstance().setInitialDelay(300);  // ms antes de aparecer
     *   ToolTipManager.sharedInstance().setDismissDelay(5000); // ms visible
     *   ToolTipManager.sharedInstance().setReshowDelay(100);   // ms para reaparecer
     *   // Tooltip personalizado con JComponent propio:
     *   JComponent comp2 = new JComponent() {
     *       public JToolTip createToolTip() {
     *           JToolTip tip = super.createToolTip();
     *           tip.setBackground(Color.BLACK);
     *           tip.setForeground(Color.WHITE);
     *           return tip;
     *       }
     *   };
     *
     * ── Cursor personalizado ──────────────────────────────────
     *   Toolkit tk = Toolkit.getDefaultToolkit();
     *   Image imgCursor = new ImageIcon("cursor.png").getImage();
     *   Cursor cursorPersonal = tk.createCustomCursor(imgCursor, new Point(0,0), "miCursor");
     *   comp.setCursor(cursorPersonal);
     *
     * ── Teclado en pantalla / InputMethod ─────────────────────
     *   comp.enableInputMethods(true);
     *   comp.addInputMethodListener(...); // Soporte para idiomas CJK, etc.
     *
     * ── Bordes personalizados (Border propio) ─────────────────
     *   Border misBorde = new AbstractBorder() {
     *       public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
     *           g.setColor(Color.BLUE);
     *           ((Graphics2D) g).setStroke(new BasicStroke(3));
     *           g.drawRoundRect(x+1, y+1, w-3, h-3, 15, 15);
     *       }
     *       public Insets getBorderInsets(Component c) { return new Insets(8,8,8,8); }
     *   };
     *   comp.setBorder(misBorde);
     *
     * ── Layout personalizado (LayoutManager propio) ────────────
     *   class MiLayout implements LayoutManager2 {
     *       public void layoutContainer(Container parent) {
     *           // Distribuye los componentes manualmente
     *           int n = parent.getComponentCount();
     *           for (int i = 0; i < n; i++) {
     *               Component c = parent.getComponent(i);
     *               c.setBounds(...);
     *           }
     *       }
     *       ... // preferredLayoutSize, minimumLayoutSize, addLayoutComponent, removeLayoutComponent
     *   }
     */


    // ════════════════════════════════════════════════════════════
    //  PUNTO DE ENTRADA
    // ════════════════════════════════════════════════════════════
    public static void main(String[] args) {
        SwingUtilities.invokeLater(JFrame_Avanzado::new);
    }
}