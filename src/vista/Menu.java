/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.ControladorPrincipal;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import modelo.Estadisticas;
import modelo.NodoArchivo;

public class Menu extends javax.swing.JFrame implements VistaPrincipal {

    private ControladorPrincipal controlador;
    private final DefaultTableModel modeloResultados = new DefaultTableModel(
            new String[]{"Nombre", "Ruta absoluta", "Tamanio", "Modificacion"}, 0);
    private final DefaultTableModel modeloDuplicados = new DefaultTableModel(
            new String[]{"Grupo", "Nombre", "Ruta absoluta", "Tamanio"}, 0);

    public Menu() {
        initComponents();
        configurarComponentos();
    }

    private void configurarComponentos() {
        tablaResultados.setModel(modeloResultados);
        tablaDuplicados.setModel(modeloDuplicados);
        tablaResultados.setAutoCreateRowSorter(true);
        tablaDuplicados.setAutoCreateRowSorter(true);
        barra.setStringPainted(true);
        btnCancelar.setEnabled(false);
        areaStats.setEditable(false);
        areaStats.setFont(new java.awt.Font("Monospaced", 0, 12));
        arbol.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Sin analizar")));
        scrollArbol.setPreferredSize(new Dimension(260, 380));
        scrollResultados.setPreferredSize(new Dimension(520, 150));
        scrollEstadisticas.setPreferredSize(new Dimension(520, 200));
        scrollDuplicados.setPreferredSize(new Dimension(520, 150));
        txtRuta.setColumns(24);
    }

    @Override
    public void setControlador(ControladorPrincipal controlador) {
        this.controlador = controlador;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuArchivo = new javax.swing.JMenu();
        itemExaminar = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();
        menuAnalizar = new javax.swing.JMenu();
        itemAnalizar = new javax.swing.JMenuItem();
        itemBuscar = new javax.swing.JMenuItem();
        itemDuplicados = new javax.swing.JMenuItem();
        itemCancelar = new javax.swing.JMenuItem();
        menuGestionar = new javax.swing.JMenu();
        itemCrearCarpeta = new javax.swing.JMenuItem();
        itemCrearArchivo = new javax.swing.JMenuItem();
        itemRenombrar = new javax.swing.JMenuItem();
        itemCopiar = new javax.swing.JMenuItem();
        itemMover = new javax.swing.JMenuItem();
        itemEliminar = new javax.swing.JMenuItem();
        itemEditarTexto = new javax.swing.JMenuItem();
        menuVer = new javax.swing.JMenu();
        itemBitacora = new javax.swing.JMenuItem();
        menuReportes = new javax.swing.JMenu();
        itemExportar = new javax.swing.JMenuItem();

        panelCampos = new javax.swing.JPanel();
        panelFiltros = new javax.swing.JPanel();
        panelAcciones = new javax.swing.JPanel();
        panelCentro = new javax.swing.JPanel();
        panelResultados = new javax.swing.JPanel();
        panelBotones = new javax.swing.JPanel();
        panelOperaciones = new javax.swing.JPanel();
        panelProgreso = new javax.swing.JPanel();
        scrollArbol = new javax.swing.JScrollPane();
        scrollResultados = new javax.swing.JScrollPane();
        scrollEstadisticas = new javax.swing.JScrollPane();
        scrollDuplicados = new javax.swing.JScrollPane();
        lblRuta = new javax.swing.JLabel();
        lblTexto = new javax.swing.JLabel();
        lblExtension = new javax.swing.JLabel();
        lblMin = new javax.swing.JLabel();
        lblMax = new javax.swing.JLabel();
        lblDesde = new javax.swing.JLabel();
        lblHasta = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        lblOpciones = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        txtTexto = new javax.swing.JTextField();
        txtExtension = new javax.swing.JTextField();
        txtMin = new javax.swing.JTextField();
        txtMax = new javax.swing.JTextField();
        txtDesde = new javax.swing.JTextField();
        txtHasta = new javax.swing.JTextField();
        txtNivel = new javax.swing.JTextField();
        chkOcultos = new javax.swing.JCheckBox();
        btnExaminar = new javax.swing.JButton();
        btnAnalizar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnDuplicados = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCrearCarpeta = new javax.swing.JButton();
        btnCrearArchivo = new javax.swing.JButton();
        btnRenombrar = new javax.swing.JButton();
        btnCopiar = new javax.swing.JButton();
        btnMover = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditarTexto = new javax.swing.JButton();
        btnBitacora = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        arbol = new javax.swing.JTree();
        tablaResultados = new javax.swing.JTable();
        areaStats = new javax.swing.JTextArea();
        tablaDuplicados = new javax.swing.JTable();
        barra = new javax.swing.JProgressBar();
        estado = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Explorador y gestor de archivos");

        lblRuta.setText("Ruta:");
        lblTexto.setText("Buscar:");
        lblExtension.setText("Extension:");
        lblMin.setText("KB minimo:");
        lblMax.setText("KB maximo:");
        lblDesde.setText("Desde (yyyy-MM-dd):");
        lblHasta.setText("Hasta (yyyy-MM-dd):");
        lblNivel.setText("Profundidad (0=todo):");
        lblOpciones.setText("Opciones:");
        txtNivel.setText("0");
        chkOcultos.setText("Incluir ocultos");
        btnExaminar.setText("Examinar");
        btnAnalizar.setText("Analizar");
        btnBuscar.setText("Buscar");
        btnDuplicados.setText("Duplicados");
        btnCancelar.setText("Cancelar");
        btnCrearCarpeta.setText("Crear carpeta");
        btnCrearArchivo.setText("Crear archivo");
        btnRenombrar.setText("Renombrar");
        btnCopiar.setText("Copiar");
        btnMover.setText("Mover");
        btnEliminar.setText("Eliminar");
        btnEditarTexto.setText("Abrir/editar");
        btnBitacora.setText("Bitacora");
        btnExportar.setText("Exportar");
        estado.setText("Listo");

        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt);
            }
        });
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        btnDuplicados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuplicadosActionPerformed(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCrearCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCarpetaActionPerformed(evt);
            }
        });
        btnCrearArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearArchivoActionPerformed(evt);
            }
        });
        btnRenombrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenombrarActionPerformed(evt);
            }
        });
        btnCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarActionPerformed(evt);
            }
        });
        btnMover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoverActionPerformed(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        btnEditarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarTextoActionPerformed(evt);
            }
        });
        btnBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBitacoraActionPerformed(evt);
            }
        });
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        itemExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExaminarActionPerformed(evt);
            }
        });
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        itemAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAnalizarActionPerformed(evt);
            }
        });
        itemBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarActionPerformed(evt);
            }
        });
        itemDuplicados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDuplicadosActionPerformed(evt);
            }
        });
        itemCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCancelarActionPerformed(evt);
            }
        });
        itemCrearCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCrearCarpetaActionPerformed(evt);
            }
        });
        itemCrearArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCrearArchivoActionPerformed(evt);
            }
        });
        itemRenombrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRenombrarActionPerformed(evt);
            }
        });
        itemCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCopiarActionPerformed(evt);
            }
        });
        itemMover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMoverActionPerformed(evt);
            }
        });
        itemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEliminarActionPerformed(evt);
            }
        });
        itemEditarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEditarTextoActionPerformed(evt);
            }
        });
        itemBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBitacoraActionPerformed(evt);
            }
        });
        itemExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExportarActionPerformed(evt);
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        menuArchivo.setText("Archivo");
        itemExaminar.setText("Examinar ruta...");
        menuArchivo.add(itemExaminar);
        itemSalir.setText("Salir");
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        menuAnalizar.setText("Analizar");
        itemAnalizar.setText("Analizar");
        menuAnalizar.add(itemAnalizar);
        itemBuscar.setText("Buscar");
        menuAnalizar.add(itemBuscar);
        itemDuplicados.setText("Duplicados");
        menuAnalizar.add(itemDuplicados);
        itemCancelar.setText("Cancelar");
        menuAnalizar.add(itemCancelar);
        menuBar.add(menuAnalizar);
        menuGestionar.setText("Gestionar");
        itemCrearCarpeta.setText("Crear carpeta");
        menuGestionar.add(itemCrearCarpeta);
        itemCrearArchivo.setText("Crear archivo");
        menuGestionar.add(itemCrearArchivo);
        itemRenombrar.setText("Renombrar");
        menuGestionar.add(itemRenombrar);
        itemCopiar.setText("Copiar");
        menuGestionar.add(itemCopiar);
        itemMover.setText("Mover");
        menuGestionar.add(itemMover);
        itemEliminar.setText("Eliminar");
        menuGestionar.add(itemEliminar);
        itemEditarTexto.setText("Abrir/editar texto");
        menuGestionar.add(itemEditarTexto);
        menuBar.add(menuGestionar);
        menuVer.setText("Ver");
        itemBitacora.setText("Ver bitacora");
        menuVer.add(itemBitacora);
        menuBar.add(menuVer);
        menuReportes.setText("Reportes");
        itemExportar.setText("Exportar reporte");
        menuReportes.add(itemExportar);
        menuBar.add(menuReportes);
        setJMenuBar(menuBar);

        scrollArbol.setViewportView(arbol);
        scrollResultados.setViewportView(tablaResultados);
        scrollEstadisticas.setViewportView(areaStats);
        scrollDuplicados.setViewportView(tablaDuplicados);

        panelFiltros.setLayout(new FlowLayout());
        panelAcciones.setLayout(new FlowLayout());
        panelCampos.setLayout(new BorderLayout());
        panelResultados.setLayout(new BorderLayout());
        panelCentro.setLayout(new BorderLayout());
        panelOperaciones.setLayout(new FlowLayout());
        panelProgreso.setLayout(new FlowLayout());
        panelBotones.setLayout(new BorderLayout());
        getContentPane().setLayout(new BorderLayout());

        panelFiltros.add(lblRuta);
        panelFiltros.add(txtRuta);
        panelFiltros.add(lblTexto);
        panelFiltros.add(txtTexto);
        panelFiltros.add(lblExtension);
        panelFiltros.add(txtExtension);
        panelFiltros.add(lblMin);
        panelFiltros.add(txtMin);
        panelFiltros.add(lblMax);
        panelFiltros.add(txtMax);
        panelFiltros.add(lblDesde);
        panelFiltros.add(txtDesde);
        panelFiltros.add(lblHasta);
        panelFiltros.add(txtHasta);
        panelFiltros.add(lblNivel);
        panelFiltros.add(txtNivel);
        panelFiltros.add(lblOpciones);
        panelFiltros.add(chkOcultos);
        panelAcciones.add(btnExaminar);
        panelAcciones.add(btnAnalizar);
        panelAcciones.add(btnBuscar);
        panelAcciones.add(btnDuplicados);
        panelAcciones.add(btnCancelar);
        panelCampos.add(panelFiltros, BorderLayout.CENTER);
        panelCampos.add(panelAcciones, BorderLayout.SOUTH);
        panelResultados.add(scrollResultados, BorderLayout.NORTH);
        panelResultados.add(scrollEstadisticas, BorderLayout.CENTER);
        panelResultados.add(scrollDuplicados, BorderLayout.SOUTH);
        panelCentro.add(scrollArbol, BorderLayout.WEST);
        panelCentro.add(panelResultados, BorderLayout.CENTER);
        panelOperaciones.add(btnCrearCarpeta);
        panelOperaciones.add(btnCrearArchivo);
        panelOperaciones.add(btnRenombrar);
        panelOperaciones.add(btnCopiar);
        panelOperaciones.add(btnMover);
        panelOperaciones.add(btnEliminar);
        panelOperaciones.add(btnEditarTexto);
        panelOperaciones.add(btnBitacora);
        panelOperaciones.add(btnExportar);
        panelProgreso.add(barra);
        panelProgreso.add(estado);
        panelBotones.add(panelOperaciones, BorderLayout.NORTH);
        panelBotones.add(panelProgreso, BorderLayout.SOUTH);
        getContentPane().add(panelCampos, BorderLayout.NORTH);
        getContentPane().add(panelCentro, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        if (controlador != null) controlador.analizar();
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnBitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBitacoraActionPerformed
        if (controlador != null) controlador.operacion(7);
    }//GEN-LAST:event_btnBitacoraActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (controlador != null) controlador.buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (controlador != null) controlador.cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarActionPerformed
        if (controlador != null) controlador.operacion(3);
    }//GEN-LAST:event_btnCopiarActionPerformed

    private void btnCrearArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearArchivoActionPerformed
        if (controlador != null) controlador.operacion(1);
    }//GEN-LAST:event_btnCrearArchivoActionPerformed

    private void btnCrearCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCarpetaActionPerformed
        if (controlador != null) controlador.operacion(0);
    }//GEN-LAST:event_btnCrearCarpetaActionPerformed

    private void btnDuplicadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuplicadosActionPerformed
        if (controlador != null) controlador.duplicados();
    }//GEN-LAST:event_btnDuplicadosActionPerformed

    private void btnEditarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarTextoActionPerformed
        if (controlador != null) controlador.operacion(6);
    }//GEN-LAST:event_btnEditarTextoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (controlador != null) controlador.operacion(5);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarActionPerformed
        if (controlador != null) controlador.examinar();
    }//GEN-LAST:event_btnExaminarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        if (controlador != null) controlador.operacion(8);
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnMoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoverActionPerformed
        if (controlador != null) controlador.operacion(4);
    }//GEN-LAST:event_btnMoverActionPerformed

    private void btnRenombrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenombrarActionPerformed
        if (controlador != null) controlador.operacion(2);
    }//GEN-LAST:event_btnRenombrarActionPerformed

    private void itemAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAnalizarActionPerformed
        if (controlador != null) controlador.analizar();
    }//GEN-LAST:event_itemAnalizarActionPerformed

    private void itemBitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBitacoraActionPerformed
        if (controlador != null) controlador.operacion(7);
    }//GEN-LAST:event_itemBitacoraActionPerformed

    private void itemBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarActionPerformed
        if (controlador != null) controlador.buscar();
    }//GEN-LAST:event_itemBuscarActionPerformed

    private void itemCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCancelarActionPerformed
        if (controlador != null) controlador.cancelar();
    }//GEN-LAST:event_itemCancelarActionPerformed

    private void itemCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCopiarActionPerformed
        if (controlador != null) controlador.operacion(3);
    }//GEN-LAST:event_itemCopiarActionPerformed

    private void itemCrearArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCrearArchivoActionPerformed
        if (controlador != null) controlador.operacion(1);
    }//GEN-LAST:event_itemCrearArchivoActionPerformed

    private void itemCrearCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCrearCarpetaActionPerformed
        if (controlador != null) controlador.operacion(0);
    }//GEN-LAST:event_itemCrearCarpetaActionPerformed

    private void itemDuplicadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDuplicadosActionPerformed
        if (controlador != null) controlador.duplicados();
    }//GEN-LAST:event_itemDuplicadosActionPerformed

    private void itemEditarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEditarTextoActionPerformed
        if (controlador != null) controlador.operacion(6);
    }//GEN-LAST:event_itemEditarTextoActionPerformed

    private void itemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEliminarActionPerformed
        if (controlador != null) controlador.operacion(5);
    }//GEN-LAST:event_itemEliminarActionPerformed

    private void itemExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExaminarActionPerformed
        if (controlador != null) controlador.examinar();
    }//GEN-LAST:event_itemExaminarActionPerformed

    private void itemExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportarActionPerformed
        if (controlador != null) controlador.operacion(8);
    }//GEN-LAST:event_itemExportarActionPerformed

    private void itemMoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMoverActionPerformed
        if (controlador != null) controlador.operacion(4);
    }//GEN-LAST:event_itemMoverActionPerformed

    private void itemRenombrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRenombrarActionPerformed
        if (controlador != null) controlador.operacion(2);
    }//GEN-LAST:event_itemRenombrarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        if (controlador != null) controlador.salir();
    }//GEN-LAST:event_itemSalirActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (controlador != null) {
            controlador.salir();
        }
    }//GEN-LAST:event_formWindowClosing


    @Override public String getRuta() { return txtRuta.getText().trim(); }
    @Override public String getTexto() { return txtTexto.getText().trim(); }
    @Override public String getExtension() { return txtExtension.getText().trim(); }
    @Override public String getMin() { return txtMin.getText().trim(); }
    @Override public String getMax() { return txtMax.getText().trim(); }
    @Override public String getDesde() { return txtDesde.getText().trim(); }
    @Override public String getHasta() { return txtHasta.getText().trim(); }
    @Override public String getNivel() { return txtNivel.getText().trim(); }
    @Override public boolean getOcultos() { return chkOcultos.isSelected(); }

    @Override
    public String[] getCampos() {
        return new String[]{getRuta(), getTexto(), getExtension(), getMin(), getMax(),
            getDesde(), getHasta(), getNivel(), String.valueOf(getOcultos())};
    }

    @Override
    public void setCampos(String[] d) {
        txtRuta.setText(d[0]); txtTexto.setText(d[1]); txtExtension.setText(d[2]);
        txtMin.setText(d[3]); txtMax.setText(d[4]); txtDesde.setText(d[5]);
        txtHasta.setText(d[6]); txtNivel.setText(d[7]);
        chkOcultos.setSelected(Boolean.parseBoolean(d[8]));
    }

    @Override
    public void mostrarArbol(NodoArchivo raiz) {
        DefaultMutableTreeNode nodo = raiz == null
                ? new DefaultMutableTreeNode("Sin resultados") : convertir(raiz);
        arbol.setModel(new DefaultTreeModel(nodo));
        arbol.expandRow(0);
    }

    private DefaultMutableTreeNode convertir(NodoArchivo nodo) {
        DefaultMutableTreeNode resultado = new DefaultMutableTreeNode(nodo);
        for (NodoArchivo hijo : nodo.getHijos()) {
            resultado.add(convertir(hijo));
        }
        return resultado;
    }

    @Override
    public void mostrarResultados(ArrayList<File> archivos) {
        modeloResultados.setRowCount(0);
        for (File f : archivos) {
            modeloResultados.addRow(new Object[]{f.getName(), f.getAbsolutePath(),
                Estadisticas.tamanoLegible(f.length()), fecha(f)});
        }
        if (archivos.isEmpty()) {
            mostrarInfo("No se encontraron archivos que coincidan con los criterios.");
        }
    }

    @Override
    public void mostrarDuplicados(Map<String, ArrayList<File>> grupos) {
        modeloDuplicados.setRowCount(0);
        for (Map.Entry<String, ArrayList<File>> entrada : grupos.entrySet()) {
            for (File f : entrada.getValue()) {
                modeloDuplicados.addRow(new Object[]{entrada.getKey(), f.getName(),
                    f.getAbsolutePath(), Estadisticas.tamanoLegible(f.length())});
            }
        }
        if (grupos.isEmpty()) {
            mostrarInfo("No se encontraron archivos duplicados.");
        }
    }

    @Override
    public void mostrarEstadisticas(Estadisticas e, File raiz) {
        StringBuilder s = new StringBuilder();
        s.append("Ruta: ").append(raiz.getAbsolutePath()).append('\n');
        s.append("Archivos: ").append(e.getTotalArchivos()).append('\n');
        s.append("Carpetas: ").append(e.getTotalCarpetas()).append('\n');
        s.append("Vacias: ").append(e.getCarpetasVacias()).append('\n');
        s.append("Sin permiso: ").append(e.getSinPermiso()).append('\n');
        s.append("Profundidad: ").append(e.getProfundidadMaxima()).append('\n');
        s.append("Total: ").append(Estadisticas.tamanoLegible(e.getTotalBytes())).append('\n');
        s.append("Mayor: ").append(ruta(e.getMasGrande())).append('\n');
        s.append("Reciente: ").append(ruta(e.getMasReciente())).append("\n\n");
        for (int i = 0; i < 4; i++) {
            s.append(String.format("%-8s %5d %12s %6.1f%% %6.1f%%%n",
                    e.getNombreCategoria(i), e.getCantidad(i),
                    Estadisticas.tamanoLegible(e.getBytesCategoria(i)),
                    e.getPorcentajeCantidad(i), e.getPorcentajeBytes(i)));
        }
        areaStats.setText(s.toString());
        areaStats.setCaretPosition(0);
    }

    private String ruta(File f) { return f == null ? "-" : f.getAbsolutePath(); }

    private String fecha(File f) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(f.lastModified()));
    }

    @Override
    public File getArchivoSeleccionado() {
        javax.swing.tree.DefaultMutableTreeNode n =
                (javax.swing.tree.DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
        if (n != null && n.getUserObject() instanceof NodoArchivo) {
            return ((NodoArchivo) n.getUserObject()).getArchivo();
        }
        int fila = tablaResultados.getSelectedRow();
        if (fila >= 0) {
            return new File((String) tablaResultados.getValueAt(
                    tablaResultados.convertRowIndexToModel(fila), 1));
        }
        fila = tablaDuplicados.getSelectedRow();
        if (fila >= 0) {
            return new File((String) tablaDuplicados.getValueAt(
                    tablaDuplicados.convertRowIndexToModel(fila), 2));
        }
        return null;
    }

    @Override
    public File getCarpetaBase() {
        File sel = getArchivoSeleccionado();
        if (sel != null) {
            return sel.isDirectory() ? sel : sel.getParentFile();
        }
        File r = new File(getRuta());
        return r.isDirectory() ? r : null;
    }

    @Override
    public void iniciarProgreso(String mensaje) {
        btnAnalizar.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnDuplicados.setEnabled(false);
        btnCancelar.setEnabled(true);
        barra.setIndeterminate(true);
        barra.setString(mensaje);
        estado.setText(mensaje);
    }

    @Override
    public void terminarProgreso() {
        btnAnalizar.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnDuplicados.setEnabled(true);
        btnCancelar.setEnabled(false);
        barra.setIndeterminate(false);
        barra.setValue(100);
        barra.setString("");
    }

    @Override public void setEstado(String texto) { estado.setText(texto); }

    @Override
    public void mostrarError(String texto) {
        JOptionPane.showMessageDialog(this, texto, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void mostrarInfo(String texto) {
        JOptionPane.showMessageDialog(this, texto, "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean confirmar(String texto) {
        return JOptionPane.showConfirmDialog(this, texto, "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    @Override public String pedirTexto(String texto) { return JOptionPane.showInputDialog(this, texto); }

    @Override
    public File pedirCarpeta(String titulo) {
        JFileChooser c = new JFileChooser();
        c.setDialogTitle(titulo);
        c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        return c.showDialog(this, "Elegir") == JFileChooser.APPROVE_OPTION ? c.getSelectedFile() : null;
    }

    @Override
    public File pedirArchivo(String titulo) {
        JFileChooser c = new JFileChooser();
        c.setDialogTitle(titulo);
        return c.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ? c.getSelectedFile() : null;
    }

    @Override
    public String pedirContenido(String titulo, String contenido) {
        JTextArea area = new JTextArea(contenido, 12, 50);
        int op = JOptionPane.showConfirmDialog(this, new JScrollPane(area), titulo,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        return op == JOptionPane.OK_OPTION ? area.getText() : null;
    }

    @Override
    public void mostrarTexto(String titulo, String contenido) {
        JTextArea area = new JTextArea(contenido == null ? "" : contenido, 20, 70);
        area.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(area), titulo,
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override public void cerrarVentana() { dispose(); }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            Menu menu = new Menu();
            new ControladorPrincipal(menu);
            menu.setSize(1100, 720);
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbol;
    private javax.swing.JTextArea areaStats;
    private javax.swing.JProgressBar barra;
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnBitacora;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCopiar;
    private javax.swing.JButton btnCrearArchivo;
    private javax.swing.JButton btnCrearCarpeta;
    private javax.swing.JButton btnDuplicados;
    private javax.swing.JButton btnEditarTexto;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExaminar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnMover;
    private javax.swing.JButton btnRenombrar;
    private javax.swing.JCheckBox chkOcultos;
    private javax.swing.JLabel estado;
    private javax.swing.JMenuItem itemAnalizar;
    private javax.swing.JMenuItem itemBitacora;
    private javax.swing.JMenuItem itemBuscar;
    private javax.swing.JMenuItem itemCancelar;
    private javax.swing.JMenuItem itemCopiar;
    private javax.swing.JMenuItem itemCrearArchivo;
    private javax.swing.JMenuItem itemCrearCarpeta;
    private javax.swing.JMenuItem itemDuplicados;
    private javax.swing.JMenuItem itemEditarTexto;
    private javax.swing.JMenuItem itemEliminar;
    private javax.swing.JMenuItem itemExaminar;
    private javax.swing.JMenuItem itemExportar;
    private javax.swing.JMenuItem itemMover;
    private javax.swing.JMenuItem itemRenombrar;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel lblDesde;
    private javax.swing.JLabel lblExtension;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lblMax;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblOpciones;
    private javax.swing.JLabel lblRuta;
    private javax.swing.JLabel lblTexto;
    private javax.swing.JMenu menuAnalizar;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuGestionar;
    private javax.swing.JMenu menuReportes;
    private javax.swing.JMenu menuVer;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCampos;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JPanel panelOperaciones;
    private javax.swing.JPanel panelProgreso;
    private javax.swing.JPanel panelResultados;
    private javax.swing.JScrollPane scrollArbol;
    private javax.swing.JScrollPane scrollDuplicados;
    private javax.swing.JScrollPane scrollEstadisticas;
    private javax.swing.JScrollPane scrollResultados;
    private javax.swing.JTable tablaDuplicados;
    private javax.swing.JTable tablaResultados;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtExtension;
    private javax.swing.JTextField txtHasta;
    private javax.swing.JTextField txtMax;
    private javax.swing.JTextField txtMin;
    private javax.swing.JTextField txtNivel;
    private javax.swing.JTextField txtRuta;
    private javax.swing.JTextField txtTexto;
    // End of variables declaration//GEN-END:variables
}
