/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaGrupos;

import controlador.traerDatos;
import controlador.unidades;
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
public class agregarUnidad implements ActionListener, MouseListener {

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
    String id = "";
    JScrollPane scrollpane;
    JSeparator linea;

    String horas[] = {"Seleccione una hora", "7:00", "8:00", "9:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"};

    public agregarUnidad() {
        ventana = new JFrame("Unidades-Horario - PoliAsistencia");
        ventana.setBounds(30, 30, 1300, 650);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setLayout(null);
    }

    public agregarUnidad(String datos[]) {
        ventana = new JFrame("Unidades-Horario - PoliAsistencia");
        ventana.setBounds(30, 30, 1300, 650);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setLayout(null);
    }

    public void crearComponentes(boolean permiso) {

        blanco = new Color(255, 255, 255);
        azulAcento = new Color(0, 145, 234);
        azul = new Color(0, 176, 255);
        subtitulos = new Font("Arial", 0, 25);
        titulopb = new Font("Calibri", 1, 20);
        titulop = new Font("Calibri", 0, 60);

        //Titulos
        titulo = new JLabel("Unidades-Horario");
        titulo.setBounds(340, 5, 700, 100);
        titulo.setFont(titulop);
        titulo.setForeground(blanco);
        ventana.add(titulo);

        ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("/img/atras.png")).getImage());
        cerrar = new JButton("<html>&nbsp; Regresar</html>", atras);
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

        sub = new JLabel("Para agregar una Unidad de Aprendizaje, debe de llenar el siguiente formulario");
        sub.setBounds(170, 30, 900, 40);
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

        //50
        descripcion = new JLabel("Unidad de Aprendizaje");
        descripcion.setBounds(260, 140, 400, 50);
        descripcion.setFont(titulopb);
        abajo.add(descripcion);
        traerDatos td = new traerDatos();
        String dobleArr[][] = td.materias();
        int tamanio = dobleArr.length;
        String nombreUnidades[] = new String[tamanio + 1];
        nombreUnidades[0] = "Seleccione una opción";
        for (int i = 0; i < tamanio; i++) {
            nombreUnidades[i + 1] = dobleArr[i][1];
        }
        unidad = new JComboBox(nombreUnidades);
        unidad.setBounds(470, 150, 530, 30);
        unidad.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        unidad.setBackground(blanco);
        unidad.setForeground(Color.gray);
        unidad.setFont(titulopb);
        unidad.addActionListener(this);
        abajo.add(unidad);

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
        lunes.addActionListener(this);
        abajo.add(lunes);

        martes = new JCheckBox("Martes");
        martes.setBounds(470, 220, 90, 50);
        martes.setFont(titulopb);
        martes.setBackground(blanco);
        martes.addActionListener(this);
        abajo.add(martes);

        miercoles = new JCheckBox("Miercoles");
        miercoles.setBounds(560, 220, 120, 50);
        miercoles.setFont(titulopb);
        miercoles.setBackground(blanco);
        miercoles.addActionListener(this);
        abajo.add(miercoles);

        jueves = new JCheckBox("Jueves");
        jueves.setBounds(680, 220, 90, 50);
        jueves.setFont(titulopb);
        jueves.setBackground(blanco);
        jueves.addActionListener(this);
        abajo.add(jueves);

        viernes = new JCheckBox("Viernes");
        viernes.setBounds(770, 220, 90, 50);
        viernes.setFont(titulopb);
        viernes.setBackground(blanco);
        viernes.addActionListener(this);
        abajo.add(viernes);

        //50
//        descripcion = new JLabel("Turno");
//        descripcion.setBounds(610, 270, 400, 50);
//        descripcion.setFont(titulopb);
//        abajo.add(descripcion);
//        
//        turno = new ButtonGroup();
//        
//        matutino = new JRadioButton("Matutino");
//        matutino.setBounds(500, 300, 120, 50);
//        matutino.setFont(titulopb);
//        matutino.setBackground(blanco);
//        abajo.add(matutino);
//        
//        vespertino = new JRadioButton("Vespertino");
//        vespertino.setBounds(650, 300, 120, 50);
//        vespertino.setFont(titulopb);
//        vespertino.setBackground(blanco);
//        abajo.add(vespertino);
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
        inicioLunes.setEnabled(false);
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
        finLunes.setEnabled(false);
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
        inicioMartes.setEnabled(false);
        inicioMartes.addActionListener(this);
        mar.add(inicioMartes);

        descripcion = new JLabel("a");
        descripcion.setFont(titulopb);
        descripcion.setBounds(340, 20, 300, 100);
        mar.add(descripcion);

        finMartes = new JComboBox(horas);
        finMartes.setBounds(360, 55, 100, 30);
        finMartes.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        finMartes.setBackground(blanco);
        finMartes.setForeground(Color.gray);
        finMartes.setFont(titulopb);
        finMartes.setEnabled(false);
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
        iniciomiercoles.setEnabled(false);
        iniciomiercoles.addActionListener(this);
        mie.add(iniciomiercoles);

        descripcion = new JLabel("a");
        descripcion.setFont(titulopb);
        descripcion.setBounds(340, 20, 300, 100);
        mie.add(descripcion);

        finMiercoles = new JComboBox(horas);
        finMiercoles.setBounds(360, 55, 100, 30);
        finMiercoles.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        finMiercoles.setBackground(blanco);
        finMiercoles.setForeground(Color.gray);
        finMiercoles.setFont(titulopb);
        finMiercoles.setEnabled(false);
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
        inicioJueves.setBounds(230, 55, 100, 30);
        inicioJueves.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        inicioJueves.setBackground(blanco);
        inicioJueves.setForeground(Color.gray);
        inicioJueves.setFont(titulopb);
        inicioJueves.setEnabled(false);
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
        finJueves.setEnabled(false);
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
        inicioViernes.setEnabled(false);
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
        finViernes.setEnabled(false);
        finViernes.addActionListener(this);
        vie.add(finViernes);

        abajo.add(vie);

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
            inicioUnidades abrir = new inicioUnidades();
            abrir.crearComponentes(true);
        }

        if (a.getSource() == agreg) {
            System.out.println("Hola");
            boolean seguir[] = new boolean[5];
            boolean seleccionados = false, SeleccionadoF=true;
            if (lunes.isSelected() || martes.isSelected() || miercoles.isSelected() || jueves.isSelected() || viernes.isSelected()) {
                seleccionados = true;
            }
            for (int i = 0; i < 5; i++) {
                seguir[i] = seleccionados;
            }
            if (unidad.getSelectedIndex() > 0) {
                if (seleccionados) {
                    if (lunes.isSelected()) {
                        if (inicioLunes.getSelectedIndex() > 0) {
                            if (finLunes.getSelectedIndex() > 0) {
                                if (inicioLunes.getSelectedIndex() < finLunes.getSelectedIndex()) {
                                    seguir[0] = true;
                                } else {
                                    seguir[0] = false;
                                    JOptionPane.showMessageDialog(ventana, "La hora de inicio no puede ser mayor a la final en lunes", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                seguir[0] = false;
                                JOptionPane.showMessageDialog(ventana, "Seleccione una hora final en lunes", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            seguir[0] = false;
                            JOptionPane.showMessageDialog(ventana, "Seleccione una hora de inicio en lunes", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (martes.isSelected()) {
                        if (inicioMartes.getSelectedIndex() > 0) {
                            if (finMartes.getSelectedIndex() > 0) {
                                if (inicioMartes.getSelectedIndex() < finMartes.getSelectedIndex()) {
                                    seguir[1] = true;
                                } else {
                                    seguir[1] = false;
                                    JOptionPane.showMessageDialog(ventana, "La hora de inicio no puede ser mayor a la final en martes", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                seguir[1] = false;
                                JOptionPane.showMessageDialog(ventana, "Seleccione una hora final en martes", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            seguir[1] = false;
                            JOptionPane.showMessageDialog(ventana, "Seleccione una hora de inicio en martes", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (miercoles.isSelected()) {
                        if (iniciomiercoles.getSelectedIndex() > 0) {
                            if (finMiercoles.getSelectedIndex() > 0) {
                                if (iniciomiercoles.getSelectedIndex() < finMiercoles.getSelectedIndex()) {
                                    seguir[2] = true;
                                } else {
                                    seguir[2] = false;
                                    JOptionPane.showMessageDialog(ventana, "La hora de inicio no puede ser mayor a la final en miercoles", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                seguir[2] = false;
                                JOptionPane.showMessageDialog(ventana, "Seleccione una hora final en miercoles", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            seguir[2] = false;
                            JOptionPane.showMessageDialog(ventana, "Seleccione una hora de inicio en miercoles", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (jueves.isSelected()) {
                        if (inicioJueves.getSelectedIndex() > 0) {
                            if (finJueves.getSelectedIndex() > 0) {
                                if (inicioJueves.getSelectedIndex() < finJueves.getSelectedIndex()) {
                                    seguir[3] = true;
                                } else {
                                    seguir[3] = false;
                                    JOptionPane.showMessageDialog(ventana, "La hora de inicio no puede ser mayor a la final en jueves", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                seguir[3] = false;
                                JOptionPane.showMessageDialog(ventana, "Seleccione una hora final en jueves", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            seguir[3] = false;
                            JOptionPane.showMessageDialog(ventana, "Seleccione una hora de inicio en jueves", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (viernes.isSelected()) {
                        if (inicioViernes.getSelectedIndex() > 0) {
                            if (finViernes.getSelectedIndex() > 0) {
                                if (inicioViernes.getSelectedIndex() < finViernes.getSelectedIndex()) {
                                    seguir[4] = true;
                                } else {
                                    seguir[4] = false;
                                    JOptionPane.showMessageDialog(ventana, "La hora de inicio no puede ser mayor a la final en viernes", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                seguir[4] = false;
                                JOptionPane.showMessageDialog(ventana, "Seleccione una hora final  en viernes", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            seguir[4] = false;
                            JOptionPane.showMessageDialog(ventana, "Seleccione una hora de inicio en viernes", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(ventana, "Seleccione un día", "Error", JOptionPane.ERROR_MESSAGE);
                    lunes.requestFocus();
                }
            } else {
                SeleccionadoF = false;
                JOptionPane.showMessageDialog(ventana, "Seleccione una unidad", "Error", JOptionPane.ERROR_MESSAGE);
                unidad.requestFocus();
            }
            if (seguir[0] && seguir[1] && seguir[2] && seguir[3] && seguir[4] && SeleccionadoF) {
                unidades unid = new unidades();
                int mat = unid.guardarUnidad(unidad.getSelectedItem() + "", 30);
                int correcto[] = new int[5];
                for (int i = 0; i < 5; i++) {
                    correcto[i] = 1;
                }
                if(mat>0){
                    if (lunes.isSelected()) {
                        correcto[0] = unid.guardarUnidadHorario(mat, inicioLunes.getSelectedIndex(), finLunes.getSelectedIndex(), 1);
                        if (correcto[0] == 0) {
                            JOptionPane.showMessageDialog(ventana, unid.getMsj() + " en lunes", "Error lunes", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (martes.isSelected() && correcto[0] > 0) {
                        correcto[1] = unid.guardarUnidadHorario(mat, inicioMartes.getSelectedIndex(), finMartes.getSelectedIndex(), 2);
                        if (correcto[1] == 0) {
                            JOptionPane.showMessageDialog(ventana, unid.getMsj() + " en martes", "Error martes", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (miercoles.isSelected() && correcto[0] > 0 && correcto[1] > 0) {
                        correcto[2] = unid.guardarUnidadHorario(mat, iniciomiercoles.getSelectedIndex(), finMiercoles.getSelectedIndex(), 3);
                        if (correcto[2] == 0) {
                            JOptionPane.showMessageDialog(ventana, unid.getMsj() + " en miercoles", "Error miercoles", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (jueves.isSelected() && correcto[0] > 0 && correcto[1] > 0 && correcto[2] > 0) {
                        correcto[3] = unid.guardarUnidadHorario(mat, inicioJueves.getSelectedIndex(), finJueves.getSelectedIndex(), 4);
                        if (correcto[3] == 0) {
                            JOptionPane.showMessageDialog(ventana, unid.getMsj() + " en jueves", "Error jueves", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (viernes.isSelected() && correcto[0] > 0 && correcto[1] > 0 && correcto[2] > 0 && correcto[3] > 0) {
                        correcto[0] = unid.guardarUnidadHorario(mat, inicioViernes.getSelectedIndex(), finViernes.getSelectedIndex(), 5);
                        if (correcto[0] == 0) {
                            JOptionPane.showMessageDialog(ventana, unid.getMsj() + " en viernes", "Error viernes", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (correcto[0] > 0 && correcto[1] > 0 && correcto[2] > 0 && correcto[3] > 0 && correcto[4] > 0) {
                        JOptionPane.showMessageDialog(ventana, "Registro con exito");
                        ventana.dispose();
                        inicioUnidades ini = new inicioUnidades();
                        ini.crearComponentes(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(ventana, unid.getMsj(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (a.getSource() == lunes) {
            if (lunes.isSelected()) {
                inicioLunes.setEnabled(true);
                finLunes.setEnabled(true);
            } else {
                inicioLunes.setEnabled(false);
                finLunes.setEnabled(false);
            }
        }
        if (a.getSource() == martes) {
            if (martes.isSelected()) {
                inicioMartes.setEnabled(true);
                finMartes.setEnabled(true);
            } else {
                inicioMartes.setEnabled(false);
                finMartes.setEnabled(false);
            }
        }
        if (a.getSource() == miercoles) {
            if (miercoles.isSelected()) {
                iniciomiercoles.setEnabled(true);
                finMiercoles.setEnabled(true);
            } else {
                iniciomiercoles.setEnabled(false);
                finMiercoles.setEnabled(false);
            }
        }
        if (a.getSource() == jueves) {
            if (jueves.isSelected()) {
                inicioJueves.setEnabled(true);
                finJueves.setEnabled(true);
            } else {
                inicioJueves.setEnabled(false);
                finJueves.setEnabled(false);
            }
        }
        if (a.getSource() == viernes) {
            if (viernes.isSelected()) {
                inicioViernes.setEnabled(true);
                finViernes.setEnabled(true);
            } else {
                inicioViernes.setEnabled(false);
                finViernes.setEnabled(false);
            }
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
