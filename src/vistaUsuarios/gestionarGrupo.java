/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaUsuarios;

import controlador.traerDatos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import vistaUsuarios.editarAlumnos;

/**
 *
 * @author PoliAsistencia
 */
public class gestionarGrupo implements ActionListener, MouseListener, KeyListener {

    JFrame ventana;
    Font titulop;
    Font titulopb, mini;
    Font subtitulos;
    Color azul, whitesmoke;
    Color azulAcento;
    Color blanco;
    JLabel titulo, sub, descripcion;
    JPanel arriba, abajo, unidades;
    JTextField nombre, salon, buscarDisponible, buscarAsignar;
    JButton cerrar, guardar, agreg;
    JComboBox semestre, especialidad;
    ButtonGroup turno;
    JRadioButton matutino, vespertino;
    String id = "";
    JScrollPane scrollpane, disponibles, asignar;
    JSeparator linea;
    DefaultTableModel modDisoponible, modAsignar, agregarAsignar, eliminarAsignar, agregarDisponible, eliminarDisponible;
    JTable tablaDisponible, tablaAsignar;
    String[] columna = { "Boleta", "Nombre", "Apellido Paterno", "Apellido Materno", "Genero"};
    Object[][] datosDisponeble, datos;
    

    public gestionarGrupo(String id) {
        this.id = id;
        System.out.println(id);
        ventana = new JFrame("Gestionar Grupo - PoliAsistencia");
        ventana.setBounds(30, 30, 1300, 650);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setLayout(null);
        traerDatos td = new traerDatos();
        datos = td.AlumnosEnElGrupo(id);
        datosDisponeble = td.AlumnosEnElGrupo("Sin grupo");
    }

    public void crearComponentes(boolean permiso) {
        
        blanco = new Color(255, 255, 255);
        azulAcento = new Color(0, 145, 234);
        azul = new Color(0, 176, 255);
        whitesmoke = new Color(245,245,245);
        subtitulos = new Font("Arial", 0, 25);
        titulopb = new Font("Calibri", 1, 20);
        mini = new Font("Calibri", 0, 16);
        titulop = new Font("Calibri", 0, 60);

        //Titulos
        titulo = new JLabel("Gestionar Grupo");
        titulo.setBounds(490, 5, 700, 100);
        titulo.setFont(titulop);
        titulo.setForeground(blanco);
        ventana.add(titulo);

        ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("/img/atras.png")).getImage());
        cerrar = new JButton("<html>&nbsp;Seleccionar Grupo</html>", atras);
        cerrar.setBounds(20, 23, 200, 50);
        cerrar.setBackground(azulAcento);
        cerrar.setBorder(BorderFactory.createLineBorder(blanco, 2));
        cerrar.setFont(new java.awt.Font("Arial", 0, 19));
        cerrar.setForeground(blanco);
        cerrar.setFocusPainted(false);
        cerrar.addActionListener(this);
        ventana.add(cerrar);
        
        agreg = new JButton("Guardar Cambios");
        agreg.setBounds(1070, 23, 200, 50);
        agreg.setBackground(azulAcento);
        agreg.setBorder(BorderFactory.createLineBorder(blanco, 2));
        agreg.setFont(new java.awt.Font("Arial", 0, 17));
        agreg.setForeground(blanco);
        agreg.addActionListener(this);
        ventana.add(agreg);

        arriba = new JPanel();
        arriba.setBounds(0, 0, 1300, 100);
        arriba.setBackground(azulAcento);
        ventana.add(arriba);

        //Contenido

        /*
         b2.setVerticalTextPosition(AbstractButton.BOTTOM);
         b2.setHorizontalTextPosition(AbstractButton.CENTER);
         */
        

        abajo = new JPanel(new GridLayout());
        abajo.setPreferredSize(new Dimension(1300, 500));
        //abajo.setBounds(0, 100, 1300, 570);
        abajo.setBackground(blanco);
        abajo.setLayout(null);
        
        
        descripcion = new JLabel("Da clic en un alumno para agregarlo o eliminarlo del grupo");
        descripcion.setBounds(330, 10, 900, 40);
        descripcion.setFont(subtitulos);
        descripcion.setForeground(azulAcento);
        abajo.add(descripcion);
        
        unidades = new JPanel();
        unidades.setBackground(whitesmoke);
        unidades.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        unidades.setLayout(null);
        unidades.setBounds(30, 60, 1220, 450);
        
        modDisoponible = new DefaultTableModel(datosDisponeble, columna);
        tablaDisponible = new JTable(modDisoponible);
        tablaDisponible.addMouseListener(this);
        tablaDisponible.setDefaultEditor(Object.class, null);
        tablaDisponible.getTableHeader().setReorderingAllowed(false);
        
        disponibles = new JScrollPane(tablaDisponible);
        disponibles.setBounds(5, 50, 600, 390);
        unidades.add(disponibles);
        
        sub = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Alumnos</html>");
        sub.setBounds(5, 20, 599, 30);
        sub.setFont(subtitulos);
        sub.setFont(titulopb);
        sub.setForeground(blanco);
        sub.setBackground(Color.gray);
        sub.setOpaque(true);
        unidades.add(sub);
        
        
        
        modAsignar = new DefaultTableModel(datos, columna);
        tablaAsignar = new JTable(modAsignar);
        tablaAsignar.addMouseListener(this);
        tablaAsignar.setDefaultEditor(Object.class, null);
        tablaAsignar.getTableHeader().setReorderingAllowed(false);
        
        asignar = new JScrollPane(tablaAsignar);
        asignar.setBounds(610, 50, 600, 390);
        unidades.add(asignar);
        
        sub = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+id+"</html>");
        sub.setBounds(610, 20, 599, 30);
        sub.setFont(subtitulos);
        sub.setFont(titulopb);
        sub.setForeground(blanco);
        sub.setBackground(azulAcento);
        sub.setOpaque(true);
        unidades.add(sub);
        
        abajo.add(unidades);
        
        
        scrollpane = new JScrollPane(abajo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setBounds(0, 100, 1295, 523);
        scrollpane.getVerticalScrollBar().setUnitIncrement(16);
        ventana.add(scrollpane);
        
        

        ventana.setVisible(permiso);

    }

    @Override
    public void actionPerformed(ActionEvent a) {

        if (a.getSource() == cerrar) {
            ventana.dispose();
            seleccionarGrupo abrir = new seleccionarGrupo();
            abrir.crearComponentes(true);
        }
        
        if (a.getSource() == guardar) {
            /*
            * -1 = cerrado
            * 0 = si
            * 1 = no
             */
                JOptionPane.showMessageDialog(ventana, "Datos Guardados correctamente");
                ventana.dispose();
                editarAlumnos abrir = new editarAlumnos();
                abrir.crearComponentes(true);
            }
        if(a.getSource() == agreg){
            JOptionPane.showMessageDialog(ventana, "Datos guardados");
            ventana.dispose();
            seleccionarGrupo abrir = new seleccionarGrupo();
            abrir.crearComponentes(true);
            
        }
            
        }

    @Override
    public void mouseClicked(MouseEvent me) {
        for (int i = 0; i < tablaDisponible.getRowCount(); i++) {
            if (tablaDisponible.getSelectedRow() == i) {
                String[] pasarFila= {(String)tablaDisponible.getValueAt(i, 0), (String)tablaDisponible.getValueAt(i, 1), (String)tablaDisponible.getValueAt(i, 2), (String)tablaDisponible.getValueAt(i, 3), (String)tablaDisponible.getValueAt(i, 4)};          
                
                agregarAsignar = (DefaultTableModel) tablaAsignar.getModel();
                agregarAsignar.addRow(pasarFila);
                
                eliminarDisponible = (DefaultTableModel) tablaDisponible.getModel();
                eliminarDisponible.removeRow(i);
            }
        }
        
        for(int j=0; j<tablaAsignar.getRowCount(); j++){
            if(tablaAsignar.getSelectedRow() == j){
                String[] pasarF = {(String) tablaAsignar.getValueAt(j, 0), (String) tablaAsignar.getValueAt(j, 1), (String) tablaAsignar.getValueAt(j, 2), (String) tablaAsignar.getValueAt(j, 3), (String) tablaAsignar.getValueAt(j, 4)};
                
                agregarDisponible = (DefaultTableModel) tablaDisponible.getModel();
                agregarDisponible.addRow(pasarF);
                
                eliminarAsignar = (DefaultTableModel) tablaAsignar.getModel();
                eliminarAsignar.removeRow(j);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        String cadena = (buscarDisponible.getText()).toUpperCase();
        controlador.filtro filtrar = new controlador.filtro();
        filtrar.buscar(cadena, tablaDisponible);
    }
        
    }





