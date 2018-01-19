/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaUsuarios;

import controlador.gruposControlador;
import controlador.traerDatos;
import controlador.unidades;
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
import java.util.ArrayList;
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
public class alumnoGrupo implements ActionListener, MouseListener, KeyListener {
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
    String[] columna = { "Grupo", "Semestre", "Turno", "Area"};
    Object[][] datosDisponeble, yaInscrito;
    private String nombreS;
    private int contador=0;

    public alumnoGrupo(String nombreR) {
        nombreS = nombreR;
        ventana = new JFrame("modificar Grupo - PoliAsistencia");
        ventana.setBounds(30, 30, 1300, 650);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setLayout(null);
        traerDatos td = new traerDatos();
        datosDisponeble = td.gruposAlumno(nombreR);
        yaInscrito = td.grupoAlumno(nombreR);
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
        titulo = new JLabel("Modificar Grupo");
        titulo.setBounds(490, 5, 700, 100);
        titulo.setFont(titulop);
        titulo.setForeground(blanco);
        ventana.add(titulo);

        ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("/img/atras.png")).getImage());
        cerrar = new JButton("<html>&nbsp;Ver Unidades</html>", atras);
        cerrar.setBounds(20, 23, 200, 50);
        cerrar.setBackground(azulAcento);
        cerrar.setBorder(BorderFactory.createLineBorder(blanco, 2));
        cerrar.setFont(new java.awt.Font("Arial", 0, 19));
        cerrar.setForeground(blanco);
        cerrar.setFocusPainted(false);
        cerrar.addActionListener(this);
        ventana.add(cerrar);
        
        agreg = new JButton("Modificar Grupo");
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
        abajo.setPreferredSize(new Dimension(1300, 1000));
        //abajo.setBounds(0, 100, 1300, 570);
        abajo.setBackground(blanco);
        abajo.setLayout(null);
        
        sub = new JLabel("Para agregar un Grupo de Aprendizaje, debe de llenar el siguiente formulario");
        sub.setBounds(170, 30, 900, 40);
        sub.setFont(subtitulos);
        sub.setForeground(azulAcento);
        abajo.add(sub);
        
        //nombre 260, 90, 400, 50
        descripcion = new JLabel("Nombre: "+ nombreS);
        descripcion.setBounds(300, 90, 400, 50);
        descripcion.setFont(titulopb);
        abajo.add(descripcion);
        
        descripcion = new JLabel("Unidades de Aprendizaje");
        descripcion.setBounds(540, 110, 400, 50);
        descripcion.setFont(titulopb);
        abajo.add(descripcion);
        
        descripcion = new JLabel("Da clic en una materia para cambiarla de tabla");
        descripcion.setBounds(400, 150, 900, 40);
        descripcion.setFont(subtitulos);
        descripcion.setForeground(azulAcento);
        abajo.add(descripcion);
        
        unidades = new JPanel();
        unidades.setBackground(whitesmoke);
        unidades.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        unidades.setLayout(null);
        unidades.setBounds(30, 210, 1220, 510);
        
        modDisoponible = new DefaultTableModel(datosDisponeble, columna);
        tablaDisponible = new JTable(modDisoponible);
        tablaDisponible.addMouseListener(this);
        tablaDisponible.setDefaultEditor(Object.class, null);
        
        tablaDisponible.getTableHeader().setReorderingAllowed(false);
        
        disponibles = new JScrollPane(tablaDisponible);
        disponibles.setBounds(5, 50, 600, 450);
        unidades.add(disponibles);
        
        sub = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unidades de Aprendizaje Disponibles</html>");
        sub.setBounds(5, 20, 599, 30);
        sub.setFont(subtitulos);
        sub.setFont(titulopb);
        sub.setForeground(blanco);
        sub.setBackground(Color.gray);
        sub.setOpaque(true);
        unidades.add(sub);
        
        
        
        modAsignar = new DefaultTableModel(yaInscrito, columna);
        tablaAsignar = new JTable(modAsignar);
        tablaAsignar.addMouseListener(this);
        tablaAsignar.setDefaultEditor(Object.class, null);
        tablaAsignar.getTableHeader().setReorderingAllowed(false);
        
        
        asignar = new JScrollPane(tablaAsignar);
        asignar.setBounds(610, 50, 600, 450);
        unidades.add(asignar);
        if(yaInscrito != null){
            for (int i = 0; i < tablaDisponible.getRowCount(); i++) {
                if(((String)tablaDisponible.getValueAt(i, 0)).equals((String) tablaAsignar.getValueAt(0, 0))){
                    eliminarDisponible = (DefaultTableModel) tablaDisponible.getModel();
                    eliminarDisponible.removeRow(i);
                }
            }
        }
        if("Sin grupo".equals((String) tablaAsignar.getValueAt(0, 0))){
            eliminarAsignar = (DefaultTableModel) tablaAsignar.getModel();
            eliminarAsignar.removeRow(0);
            contador = 0;
        }else{
            contador = 1;
        }
        
        
        sub = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unidades de Aprendizaje a Asignar</html>");
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
            alumnoRegular abrir = new alumnoRegular();
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
            int tamanioA = modAsignar.getRowCount(), tamanioD = modDisoponible.getRowCount();
            if(tamanioA>0){
                gruposControlador gC = new gruposControlador();
                String ms = gC.inscribirAlumno(nombreS, (String)modAsignar.getValueAt(0, 0));
                if(ms.equalsIgnoreCase("ok")){
                    JOptionPane.showMessageDialog(ventana, "Alumno inscrito");
                    ventana.dispose();
                    alumnoRegular abrir = new alumnoRegular();
                    abrir.crearComponentes(true);
                }else
                    JOptionPane.showMessageDialog(ventana,  gC.getMsj(), "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(ventana, "Asigne un grupo", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            
        }
            
        }

    @Override
    public void mouseClicked(MouseEvent me) {
        for (int i = 0; i < tablaDisponible.getRowCount(); i++) {
            if (tablaDisponible.getSelectedRow() == i) {
                String[] pasarFila= {(String)tablaDisponible.getValueAt(i, 0), (String)tablaDisponible.getValueAt(i, 1), (String)tablaDisponible.getValueAt(i, 2), (String)tablaDisponible.getValueAt(i, 3)};          
                
                if(contador > 0){
                    JOptionPane.showMessageDialog(ventana, "No se puede tener mas de un grupo", "Error", JOptionPane.ERROR_MESSAGE);
                    tablaDisponible.clearSelection();
                }
                else{
                    tablaDisponible.clearSelection();
                    agregarAsignar = (DefaultTableModel) tablaAsignar.getModel();
                    agregarAsignar.addRow(pasarFila);
                    contador=1;
                    eliminarDisponible = (DefaultTableModel) tablaDisponible.getModel();
                    eliminarDisponible.removeRow(i);
                }
                
                
            }
        }
        
        for(int j=0; j<tablaAsignar.getRowCount(); j++){
            if(tablaAsignar.getSelectedRow() == j){
                String[] pasarF = {(String) tablaAsignar.getValueAt(j, 0), (String) tablaAsignar.getValueAt(j, 1), (String) tablaAsignar.getValueAt(j, 2), (String) tablaAsignar.getValueAt(j, 3)};
                agregarDisponible = (DefaultTableModel) tablaDisponible.getModel();
                agregarDisponible.addRow(pasarF);
                
                eliminarAsignar = (DefaultTableModel) tablaAsignar.getModel();
                eliminarAsignar.removeRow(j);
                contador = 0;
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





