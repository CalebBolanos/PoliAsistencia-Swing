/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaGrupos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class editarUnidad implements ActionListener, MouseListener {

    JFrame ventana;
    Font titulop;
    Font titulopb;
    Font subtitulos;
    Color azul;
    Color azulAcento;
    Color blanco;
    JLabel titulo, sub, descripcion;
    JPanel arriba, abajo, lun, mar, mie, jue, vie;
    JTextField nombre, paterno, materno, boleta;
    JTextField horaMartes;
    JButton cerrar, guardar, agreg;
    JComboBox semestre, unidad;
    JComboBox inicioLunes, finLunes;
    JComboBox inicioMartes, finMartes;
    JComboBox iniciomiercoles, finMiercoles;
    JComboBox inicioJueves, finJueves;
    JComboBox inicioViernes, finViernes;
    JCheckBox lunes, martes, miercoles, jueves, viernes;
    ButtonGroup turno;
    JRadioButton matutino, vespertino;
    JScrollPane scrollpane;
    JSeparator linea;
    int semesD, unidD, inL, fL, inMa, fMa, inMi, fMi, inJ, fJ, inV, fV, cupo, uni;
    boolean sL, sMa, sMi, sJ, sV;
    String nombreUn;
    

    public editarUnidad(String datos[]) {
        ventana = new JFrame("Editar Unidad - PoliAsistencia");
        ventana.setBounds(30, 30, 1300, 650);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setLayout(null);
        unidD = Integer.parseInt(datos[0]);
        inL = Integer.parseInt(datos[1]);
        inMa = Integer.parseInt(datos[2]);
        inMi = Integer.parseInt(datos[3]);
        inJ = Integer.parseInt(datos[4]);
        inV = Integer.parseInt(datos[5]);
        fL = Integer.parseInt(datos[6]);
        fMa = Integer.parseInt(datos[7]);
        fMi = Integer.parseInt(datos[8]);
        fJ = Integer.parseInt(datos[9]);
        fV = Integer.parseInt(datos[10]);
        nombreUn = datos[11];
        semesD = Integer.parseInt(datos[12]);
        cupo = Integer.parseInt(datos[13]);
        uni = Integer.parseInt(datos[14]);
        sL = inL>0;
        sMa = inMa>0;
        sMi = inMi>0;
        sJ = inJ>0;
        sV = inV>0;
    }

    public void crearComponentes(boolean permiso) {
        String horas[] = {"Seleccione una hora", "7:00", "8:00", "9:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};
        blanco = new Color(255, 255, 255);
        azulAcento = new Color(0, 145, 234);
        azul = new Color(0, 176, 255);
        subtitulos = new Font("Arial", 0, 25);
        titulopb = new Font("Calibri", 1, 20);
        titulop = new Font("Calibri", 0, 60);

        //Titulos
        titulo = new JLabel(nombreUn);
        titulo.setBounds(540, 5, 700, 100);
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
        
        agreg = new JButton("Agregar Unidad");
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
        
        sub = new JLabel("Editar Unidad");
        sub.setBounds(550, 30, 900, 40);
        sub.setFont(subtitulos);
        sub.setForeground(azulAcento);
        abajo.add(sub);
        
        descripcion = new JLabel("Semestre de la Unidad");
        descripcion.setBounds(260, 90, 400, 50);
        descripcion.setFont(titulopb);
        abajo.add(descripcion);
        
        semestre = new JComboBox();
        semestre.addItem("Seleccione una opción");
        semestre.addItem("1");
        semestre.addItem("2");
        semestre.addItem("3");
        semestre.addItem("4");
        semestre.addItem("5");
        semestre.addItem("6");
        semestre.setBounds(470, 100, 530, 30);
        semestre.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        semestre.setBackground(blanco);
        semestre.setForeground(Color.gray);
        semestre.setFont(titulopb);
        semestre.addActionListener(this);
        abajo.add(semestre);
        semestre.setSelectedIndex(semesD);
        
        //50
        
        descripcion = new JLabel("Unidad de Aprendizaje");
        descripcion.setBounds(260, 140, 400, 50);
        descripcion.setFont(titulopb);
        abajo.add(descripcion);
        
        unidad = new JComboBox();
        unidad.addItem("Seleccione una opción");
        unidad.addItem("Fisica xd");
        unidad.setBounds(470, 150, 530, 30);
        unidad.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        unidad.setBackground(blanco);
        unidad.setForeground(Color.gray);
        unidad.setFont(titulopb);
        unidad.addActionListener(this);
        abajo.add(unidad);
        unidad.setSelectedItem(nombreUn);
        
        //50
        
        descripcion = new JLabel("Días");
        descripcion.setBounds(615, 190, 400, 50);
        descripcion.setFont(titulopb);
        abajo.add(descripcion);
        
        //30
        lunes = new JCheckBox("Lunes");
        lunes.setBounds(380, 220, 90, 50);
        lunes.setFont(titulopb);
        lunes.setBackground(blanco);
        abajo.add(lunes);
        lunes.setSelected(sL);
        
        martes = new JCheckBox("Martes");
        martes.setBounds(470, 220, 90, 50);
        martes.setFont(titulopb);
        martes.setBackground(blanco);
        abajo.add(martes);
        martes.setSelected(sMa);
        
        miercoles = new JCheckBox("Miercoles");
        miercoles.setBounds(560, 220, 120, 50);
        miercoles.setFont(titulopb);
        miercoles.setBackground(blanco);
        abajo.add(miercoles);
        miercoles.setSelected(sMi);
        
        jueves = new JCheckBox("Jueves");
        jueves.setBounds(680, 220, 90, 50);
        jueves.setFont(titulopb);
        jueves.setBackground(blanco);
        abajo.add(jueves);
        jueves.setSelected(sJ);
        
        viernes = new JCheckBox("Viernes");
        viernes.setBounds(770, 220, 90, 50);
        viernes.setFont(titulopb);
        viernes.setBackground(blanco);
        abajo.add(viernes);
        viernes.setSelected(sV);
        
        //50
        descripcion = new JLabel("Turno");
        descripcion.setBounds(610, 270, 400, 50);
        descripcion.setFont(titulopb);
        abajo.add(descripcion);
        
        turno = new ButtonGroup();
        
        matutino = new JRadioButton("Matutino");
        matutino.setBounds(500, 300, 120, 50);
        matutino.setFont(titulopb);
        matutino.setBackground(blanco);
        abajo.add(matutino);
        
        vespertino = new JRadioButton("Vespertino");
        vespertino.setBounds(650, 300, 120, 50);
        vespertino.setFont(titulopb);
        vespertino.setBackground(blanco);
        abajo.add(vespertino);
        
        descripcion = new JLabel("Lunes");
        descripcion.setBounds(615, 370, 400, 50);
        descripcion.setFont(titulopb);
        descripcion.setForeground(azulAcento);
        abajo.add(descripcion);
        
        lun = new JPanel();
        lun.setBackground(blanco);
        lun.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        lun.setLayout(null);
        lun.setBounds(300, 370, 700, 100);
        
        
        //new java.awt.Font("Arial", 1, 17)
        
          
        descripcion = new JLabel("De");
        descripcion.setFont(titulopb);
        descripcion.setBounds(200, 20, 300, 100);
        lun.add(descripcion);
        
        inicioLunes = new JComboBox(horas);
        inicioLunes.setBounds(230, 55, 100, 30);
        inicioLunes.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        inicioLunes.setBackground(blanco);
        inicioLunes.setForeground(Color.gray);
        inicioLunes.setFont(titulopb);
        inicioLunes.addActionListener(this);
        lun.add(inicioLunes);
        
        descripcion = new JLabel("a");
        descripcion.setFont(titulopb);
        descripcion.setBounds(340, 20, 300, 100);
        lun.add(descripcion);
        
        finLunes = new JComboBox(horas);
        finLunes.setBounds(360, 55, 100, 30);
        finLunes.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        finLunes.setBackground(blanco);
        finLunes.setForeground(Color.gray);
        finLunes.setFont(titulopb);
        finLunes.addActionListener(this);
        lun.add(finLunes);
        
        abajo.add(lun);
        
        
        
        //130 Martes
        descripcion = new JLabel("Martes");
        descripcion.setBounds(615, 500, 400, 50);
        descripcion.setFont(titulopb);
        descripcion.setForeground(azulAcento);
        abajo.add(descripcion);
        
        mar = new JPanel();
        mar.setBackground(blanco);
        mar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        mar.setLayout(null);
        mar.setBounds(300, 500, 700, 100);
        
        
        
        //new java.awt.Font("Arial", 1, 17)
        
          
        descripcion = new JLabel("De");
        descripcion.setFont(titulopb);
        descripcion.setBounds(200, 20, 300, 100);
        mar.add(descripcion);
        
        inicioMartes = new JComboBox(horas);
        inicioMartes.setBounds(230, 55, 100, 30);
        inicioMartes.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        inicioMartes.setBackground(blanco);
        inicioMartes.setForeground(Color.gray);
        inicioMartes.setFont(titulopb);
        inicioMartes.addActionListener(this);
        mar.add(inicioMartes);
        
        descripcion = new JLabel("a");
        descripcion.setFont(titulopb);
        descripcion.setBounds(340, 20, 300, 100);
        mar.add(descripcion);
        
        finMartes = new JComboBox();
        finMartes.setBounds(360, 55, 100, 30);
        finMartes.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        finMartes.setBackground(blanco);
        finMartes.setForeground(Color.gray);
        finMartes.setFont(titulopb);
        finMartes.addActionListener(this);
        mar.add(finMartes);
        
        abajo.add(mar);
        
        
        
        descripcion = new JLabel("Miercoles");
        descripcion.setBounds(615, 630, 400, 50);
        descripcion.setFont(titulopb);
        descripcion.setForeground(azulAcento);
        abajo.add(descripcion);
        
        mie = new JPanel();
        mie.setBackground(blanco);
        mie.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        mie.setLayout(null);
        mie.setBounds(300, 630, 700, 100);
        
        
        
        //new java.awt.Font("Arial", 1, 17)
        
          
        descripcion = new JLabel("De");
        descripcion.setFont(titulopb);
        descripcion.setBounds(200, 20, 300, 100);
        mie.add(descripcion);
        
        iniciomiercoles = new JComboBox(horas);
        iniciomiercoles.setBounds(230, 55, 100, 30);
        iniciomiercoles.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        iniciomiercoles.setBackground(blanco);
        iniciomiercoles.setForeground(Color.gray);
        iniciomiercoles.setFont(titulopb);
        iniciomiercoles.addActionListener(this);
        mie.add(iniciomiercoles);
        
        descripcion = new JLabel("a");
        descripcion.setFont(titulopb);
        descripcion.setBounds(340, 20, 300, 100);
        mie.add(descripcion);
        
        finMiercoles = new JComboBox(horas);
        finMiercoles.addItem("9:00");
        finMiercoles.setBounds(360, 55, 100, 30);
        finMiercoles.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        finMiercoles.setBackground(blanco);
        finMiercoles.setForeground(Color.gray);
        finMiercoles.setFont(titulopb);
        finMiercoles.addActionListener(this);
        mie.add(finMiercoles);
        
        abajo.add(mie);
        
        
        
        descripcion = new JLabel("Jueves");
        descripcion.setBounds(615, 760, 400, 50);
        descripcion.setFont(titulopb);
        descripcion.setForeground(azulAcento);
        abajo.add(descripcion);
        
        jue = new JPanel();
        jue.setBackground(blanco);
        jue.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        jue.setLayout(null);
        jue.setBounds(300, 760, 700, 100);
        
        
        
        //new java.awt.Font("Arial", 1, 17)
        
          
        descripcion = new JLabel("De");
        descripcion.setFont(titulopb);
        descripcion.setBounds(200, 20, 300, 100);
        jue.add(descripcion);
        
        inicioJueves = new JComboBox(horas);
        inicioJueves.addItem("9:00");
        inicioJueves.setBounds(230, 55, 100, 30);
        inicioJueves.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        inicioJueves.setBackground(blanco);
        inicioJueves.setForeground(Color.gray);
        inicioJueves.setFont(titulopb);
        inicioJueves.addActionListener(this);
        jue.add(inicioJueves);
        
        descripcion = new JLabel("a");
        descripcion.setFont(titulopb);
        descripcion.setBounds(340, 20, 300, 100);
        jue.add(descripcion);
        
        finJueves = new JComboBox(horas);
        finJueves.setBounds(360, 55, 100, 30);
        finJueves.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        finJueves.setBackground(blanco);
        finJueves.setForeground(Color.gray);
        finJueves.setFont(titulopb);
        finJueves.addActionListener(this);
        jue.add(finJueves);
        
        abajo.add(jue);
        
        
        
        descripcion = new JLabel("Viernes");
        descripcion.setBounds(615, 890, 400, 50);
        descripcion.setFont(titulopb);
        descripcion.setForeground(azulAcento);
        abajo.add(descripcion);
        
        vie = new JPanel();
        vie.setBackground(blanco);
        vie.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        vie.setLayout(null);
        vie.setBounds(300, 890, 700, 100);
        
        
        
        //new java.awt.Font("Arial", 1, 17)
        
          
        descripcion = new JLabel("De");
        descripcion.setFont(titulopb);
        descripcion.setBounds(200, 20, 300, 100);
        vie.add(descripcion);
        
        inicioViernes = new JComboBox(horas);
        inicioViernes.setBounds(230, 55, 100, 30);
        inicioViernes.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        inicioViernes.setBackground(blanco);
        inicioViernes.setForeground(Color.gray);
        inicioViernes.setFont(titulopb);
        inicioViernes.addActionListener(this);
        vie.add(inicioViernes);
        
        descripcion = new JLabel("a");
        descripcion.setFont(titulopb);
        descripcion.setBounds(340, 20, 300, 100);
        vie.add(descripcion);
        
        finViernes = new JComboBox(horas);
        finViernes.setBounds(360, 55, 100, 30);
        finViernes.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        finViernes.setBackground(blanco);
        finViernes.setForeground(Color.gray);
        finViernes.setFont(titulopb);
        finViernes.addActionListener(this);
        vie.add(finViernes);
        
        abajo.add(vie);
        
        
        
        scrollpane = new JScrollPane(abajo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setBounds(0, 100, 1295, 523);
        scrollpane.getVerticalScrollBar().setUnitIncrement(16);
        ventana.add(scrollpane);
        
        inicioLunes.setSelectedIndex(inL);
        inicioMartes.setSelectedIndex(inMa);
        iniciomiercoles.setSelectedIndex(inMi);
        inicioJueves.setSelectedIndex(inJ);
        inicioViernes.setSelectedIndex(inV);
        finLunes.setSelectedIndex(fL);
        finMartes.setSelectedIndex(fMa);
        finMiercoles.setSelectedIndex(fMi);
        finJueves.setSelectedIndex(fJ);
        finViernes.setSelectedIndex(fV);

        ventana.setVisible(permiso);

    }

    @Override
    public void actionPerformed(ActionEvent a) {

        if (a.getSource() == cerrar) {
            ventana.dispose();
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
            JOptionPane.showMessageDialog(ventana, "Unidad de aprendizaje agregada correctamente");
            ventana.dispose();
            
        }
            
        }

    @Override
    public void mouseClicked(MouseEvent me) {
        
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
        
    }





