/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaGrupos;

import controlador.traerDatos;
import controlador.unidades;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import vistaUsuarios.editarAlumnos;

/**
 *
 * @author alexi
 */
public class asignarHorario implements ActionListener, MouseListener, KeyListener {

    JFrame ventana;
    Font titulop;
    Font titulopb;
    Font subtitulos;
    Color azul;
    Color azulAcento;
    Color blanco;
    JLabel titulo, tituloBold, dnombre, sub, dcontrasena, dcontrasena2, confirm = new JLabel("<html>¿Estás seguro de que deseas dar de baja a este Profesor? <br>Se necesita tu contraseña para continuar</html>");
    JPanel arriba, abajo;
    JTextField nombre, paterno, materno, boleta, buscador;
    JPasswordField contrasena, contrasenax2, contrasenaConfirmacion = new JPasswordField();
    ; 
    JSeparator lineaNombre, lineaCont, lineaCont2;
    JButton huella, cerrar, guardar, borrar, agreg;
    JCheckBox eleccion = new JCheckBox("No mostrar este dialogo mientras se esté registrando");
    int box = 0;
    Object[] agregar;
    JComboBox semestres, unidad;
    DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    JFormattedTextField nacimiento;
    String id = "", nomGrup = "";
    DefaultTableModel modelo;
    JTable tabla;
    JScrollPane scrollpane;
    String[] columnNames = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"}, horas = new String[15];
    Object[][] datos;
    JOptionPane opcionesUnidades;
    JLabel diaHoraSel, unidadLab, profesorLab;
    JButton btnAceptar;
    ArrayList<String[]> unidadesArr;
    int seleccionI = 0, seleccionJ = 0;
    String datoS[][][];
    ArrayList<String> unidadAEliminar = new ArrayList<>();

    public asignarHorario(String grupo) {
        ventana = new JFrame("Unidades - PoliAsistencia");
        ventana.setBounds(30, 30, 1300, 650);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setLayout(null);
        for (int i = 7; i < 22; i++) {
            horas[i - 7] = i + ":00";
        }
        nomGrup = grupo;
    }

    public void crearComponentes(boolean permiso) {

        eleccion.addActionListener(this);
        agregar = new Object[]{confirm, contrasenaConfirmacion};

        blanco = new Color(255, 255, 255);
        azulAcento = new Color(0, 145, 234);
        azul = new Color(0, 176, 255);
        subtitulos = new Font("Arial", 0, 25);
        titulopb = new Font("Calibri", 1, 17);
        titulop = new Font("Calibri", 0, 60);

        //Titulos
        titulo = new JLabel(nomGrup);
        titulo.setBounds(500, 5, 700, 100);
        titulo.setFont(titulop);
        titulo.setForeground(blanco);
        ventana.add(titulo);

        agreg = new JButton("Guardar horario");
        agreg.setBounds(1070, 23, 200, 50);
        agreg.setBackground(azulAcento);
        agreg.setBorder(BorderFactory.createLineBorder(blanco, 2));
        agreg.setFont(new java.awt.Font("Arial", 0, 17));
        agreg.setForeground(blanco);
        agreg.addActionListener(this);
        ventana.add(agreg);

        ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("/img/atras.png")).getImage());
        cerrar = new JButton("<html>&nbsp;Inicio</html>", atras);
        cerrar.setBounds(20, 23, 200, 50);
        cerrar.setBackground(azulAcento);
        cerrar.setBorder(BorderFactory.createLineBorder(blanco, 2));
        cerrar.setFont(new java.awt.Font("Arial", 0, 19));
        cerrar.setForeground(blanco);
        cerrar.setFocusPainted(false);
        cerrar.addActionListener(this);
        ventana.add(cerrar);

        arriba = new JPanel();
        arriba.setBounds(0, 0, 1300, 100);
        arriba.setBackground(azulAcento);
        ventana.add(arriba);

        //Contenido

        /*
         b2.setVerticalTextPosition(AbstractButton.BOTTOM);
         b2.setHorizontalTextPosition(AbstractButton.CENTER);
         */
//        sub = new JLabel("Para agregar una un grupo, debe de llenar el siguiente formulario");
//        sub.setBounds(130, 110, 900, 40);
//        sub.setFont(subtitulos);
//        sub.setForeground(azulAcento);
//        ventana.add(sub);
//        
//        agreg = new JButton("Agregar Grupo");
//        agreg.setBounds(990, 110, 200, 40);
//        agreg.setBackground(blanco);
//        agreg.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
//        agreg.setFont(new java.awt.Font("Arial", 0, 17));
//        agreg.setForeground(Color.GREEN);
//        agreg.setFocusPainted(false);
//        agreg.addActionListener(this);
//        ventana.add(agreg);
        sub = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Horario</html>");
        sub.setBounds(50, 110, 1200, 40);
        sub.setFont(subtitulos);
        sub.setForeground(blanco);
        sub.setBackground(Color.LIGHT_GRAY);
        sub.setOpaque(true);
        ventana.add(sub);

        modelo = new DefaultTableModel(columnNames, 15);
        tabla = new JTable(modelo);
        tabla.setRowHeight(50);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(7);
        for (int i = 0; i < 15; i++) {
            tabla.setValueAt(horas[i], i, 0);
        }
        tabla.addMouseListener(this);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setCellSelectionEnabled(true);
        scrollpane = new JScrollPane(tabla);

        scrollpane.setBounds(50, 150, 950, 400);
        ventana.add(scrollpane);

        diaHoraSel = new JLabel("Selccione una hora");
        diaHoraSel.setBounds(1005, 160, 300, 40);
        diaHoraSel.setFont(subtitulos);
        diaHoraSel.setForeground(azulAcento);
        ventana.add(diaHoraSel);

        unidadLab = new JLabel("Unidad: ");
        unidadLab.setBounds(1005, 200, 300, 40);
        unidadLab.setFont(subtitulos);
        unidadLab.setForeground(Color.gray);
        ventana.add(unidadLab);
        unidadLab.setVisible(false);

        btnAceptar = new JButton("<html>&nbsp;Aceptar</html>");
        btnAceptar.setBounds(1005, 300, 280, 50);
        btnAceptar.setBackground(azulAcento);
        btnAceptar.setBorder(BorderFactory.createLineBorder(blanco, 2));
        btnAceptar.setFont(new java.awt.Font("Arial", 0, 19));
        btnAceptar.setForeground(blanco);
        btnAceptar.setFocusPainted(false);
        btnAceptar.addActionListener(this);
        ventana.add(btnAceptar);
        btnAceptar.setVisible(false);

        traerDatos td = new traerDatos();
        String dobleArr[][] = td.materias();
        int tamanio = dobleArr.length;
        String nombreUnidades[] = new String[tamanio + 1];
        nombreUnidades[0] = "Seleccione una opción";
        for (int t = 0; t < tamanio; t++) {
            nombreUnidades[t + 1] = dobleArr[t][1];
        }
        unidad = new JComboBox(nombreUnidades);
        unidad.setBounds(1005, 243, 280, 40);
        unidad.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        unidad.setBackground(blanco);
        unidad.setForeground(Color.gray);
        unidad.setFont(titulopb);
        ventana.add(unidad);
        unidad.setVisible(false);

        datoS = td.unidadesHorarioGrupoFacil(nomGrup);
        if (datoS != null) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 6; j++) {
                    if (datoS[i][j][0] != null) {
                        tabla.setValueAt(datoS[i][j][0], i - 1, j);
                    }
                }
            }
        }

        sub = new JLabel("Da clic en un recuadro para asignar la unidad");
        sub.setBounds(290, 573, 1000, 40);
        sub.setFont(subtitulos);
        sub.setForeground(Color.gray);
        ventana.add(sub);

        abajo = new JPanel();
        abajo.setBounds(0, 100, 1300, 570);
        abajo.setBackground(blanco);
        ventana.add(abajo);

        ventana.setVisible(permiso);

    }

    @Override
    public void actionPerformed(ActionEvent a) {

        if (a.getSource() == cerrar) {
            ventana.dispose();
            asignarUnidadesGrupos abrir = new asignarUnidadesGrupos(2);
            abrir.crearComponentes(true);
        }

        if (a.getSource() == guardar) {
            /*
            * -1 = cerrado
            * 0 = si
            * 1 = no
             */
            JOptionPane.showMessageDialog(ventana, "Datos Guardados correctamente", "Confirmación", JOptionPane.OK_OPTION);
            ventana.dispose();
            editarAlumnos abrir = new editarAlumnos();
            abrir.crearComponentes(true);
        }
        if (a.getSource() == agreg) {
            int opc = JOptionPane.showConfirmDialog(ventana, "¿Esta seguro de guardar el horario?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
            if (opc == 0) {
                compararYborrar();
                guardarHorar();
                JOptionPane.showMessageDialog(ventana, "Guardado correctamente");
                ventana.dispose();
                asignarUnidadesGrupos abrir = new asignarUnidadesGrupos(2);
                abrir.crearComponentes(true);
            }
        }

        if (a.getSource() == btnAceptar) {
            if (unidad.getSelectedIndex() != 0) {
                tabla.setValueAt(unidad.getSelectedItem().toString(), seleccionI, seleccionJ);
                //tabla.clearSelection();
                diaHoraSel.setText("Selccione una hora");
                unidad.setSelectedIndex(0);
                unidad.setVisible(false);
                unidadLab.setVisible(false);
                btnAceptar.setVisible(false);
                tabla.changeSelection(seleccionI, seleccionJ, false, false);
                seleccionI = 0;
                seleccionJ = 0;
            } else {
                JOptionPane.showMessageDialog(ventana, "Seleccione una unidad", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            for (int j = 1; j < tabla.getColumnCount(); j++) {
                if (tabla.getSelectedRow() == i && tabla.getSelectedColumn() == j) {
                    //JOptionPane.showMessageDialog(ventana, (String)tabla.getValueAt(i, j));
                    seleccionI = i;
                    seleccionJ = j;
                    diaHoraSel.setText(columnNames[j] + "  " + horas[i]);
                    if (tabla.getValueAt(i, j) != null) {
                        for (int k = 0; k < unidad.getItemCount(); k++) {
                            if (unidad.getItemAt(k).toString().equals(tabla.getValueAt(i, j).toString())) {
                                unidad.setSelectedIndex(k);
                            }
                        }
                    }
                    unidad.setVisible(true);
                    unidadLab.setVisible(true);
                    btnAceptar.setVisible(true);
                }
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String cadena = (buscador.getText()).toUpperCase();
        controlador.filtro filtrar = new controlador.filtro();
        filtrar.buscar(cadena, tabla);
    }

    private boolean guardarHorar() {
        boolean ret = false, entrar = false;
        int coincidencias = 0, idU = 0;
        String arrS[] = new String[2];
        unidadesArr = new ArrayList<>();
        unidades unid = new unidades();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            for (int j = 1; j < tabla.getColumnCount(); j++) {
                if (tabla.getValueAt(i, j) != null) {
                    if (!"".equals(tabla.getValueAt(i, j).toString())) {
                        if (unidadesArr.size() < 1) {
                            if (datoS[i+1][j][0] == null) {
                                arrS = new String[2];
                                arrS[0] = tabla.getValueAt(i, j).toString();
                                arrS[1] = "" + unid.guardarUnidad(tabla.getValueAt(i, j).toString(), 50);
                                idU = Integer.parseInt(arrS[1]);
                                unid.horarioGrupo(idU, nomGrup);
                                unid.guardarUnidadHorario(idU, i + 1, i + 2, j);
                                unidadesArr.add(arrS);
                            } else {
                                for (int k = 0; k < unidadAEliminar.size(); k++) {
                                    if (unidadAEliminar.get(k).equals(datoS[i+1][j][1])) {
                                        arrS = new String[2];
                                        arrS[0] = tabla.getValueAt(i, j).toString();
                                        arrS[1] = "" + unid.guardarUnidad(tabla.getValueAt(i, j).toString(), 50);
                                        idU = Integer.parseInt(arrS[1]);
                                        unid.horarioGrupo(idU, nomGrup);
                                        unid.guardarUnidadHorario(idU, i + 1, i + 2, j);
                                        unidadesArr.add(arrS);
                                    } else {
                                        unidadesArr.add(datoS[i+1][j]);
                                    }
                                }
                            }

                        } else {
                            for (int k = 0; k < unidadesArr.size(); k++) {
                                if (tabla.getValueAt(i, j).toString().equals(unidadesArr.get(k)[0])) {
                                    coincidencias++;
                                    idU = Integer.parseInt(unidadesArr.get(k)[1]);
                                }
                            }
                            if (coincidencias < 1) {
                                if (datoS[i+1][j][0] == null) {
                                    arrS = new String[2];
                                    arrS[0] = tabla.getValueAt(i, j).toString();
                                    arrS[1] = "" + unid.guardarUnidad(tabla.getValueAt(i, j).toString(), 50);
                                    idU = Integer.parseInt(arrS[1]);
                                    unid.horarioGrupo(idU, nomGrup);
                                    unid.guardarUnidadHorario(idU, i + 1, i + 2, j);
                                    unidadesArr.add(arrS);
                                } else {
                                    for (int k = 0; k < unidadAEliminar.size(); k++) {
                                        if (unidadAEliminar.get(k).equals(datoS[i+1][j][1])) {
                                            arrS = new String[2];
                                            arrS[0] = tabla.getValueAt(i, j).toString();
                                            arrS[1] = "" + unid.guardarUnidad(tabla.getValueAt(i, j).toString(), 50);
                                            idU = Integer.parseInt(arrS[1]);
                                            unid.horarioGrupo(idU, nomGrup);
                                            unid.guardarUnidadHorario(idU, i + 1, i + 2, j);
                                            unidadesArr.add(arrS);
                                        } else {
                                            unidadesArr.add(datoS[i+1][j]);
                                        }
                                    }
                                }
                            } else {
                                for (int k = 0; k < unidadAEliminar.size(); k++) {
                                    if (unidadAEliminar.get(k).equals(datoS[i+1][j][1]) || datoS[i+1][j][0] == null) {
                                        unid.guardarUnidadHorario(idU, i + 1, i + 2, j);
                                    }
                                }

                            }
                            coincidencias = 0;
                            idU = 0;
                        }
                    }
                }
            }
        }
        System.out.println("Tamaño: " + unidadesArr.size());
        return ret;
    }

    private void compararYborrar() {
        unidades uni = new unidades();
        if (datoS != null) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 6; j++) {
                    if (datoS[i][j][0] != null) {
                        String s1 = datoS[i][j][0];
                        if (tabla.getValueAt(i-1, j) != null) {
                            if (!datoS[i][j][0].equals(tabla.getValueAt(i-1, j).toString())) {
                                String s2 = tabla.getValueAt(i-1, j).toString();
                                if(unidadAEliminar.size()>0)
                                    for (int k = 0; k < unidadAEliminar.size(); k++) {
                                        if (!unidadAEliminar.get(k).equals(tabla.getValueAt(i-1, j))) {
                                            unidadAEliminar.add(datoS[i][j][1]);
                                        }
                                    }
                                else
                                    unidadAEliminar.add(datoS[i][j][1]);
                            }
                        } else {
                            if(unidadAEliminar.size()>0)
                                for (int k = 0; k < unidadAEliminar.size(); k++) {
                                    if (!unidadAEliminar.get(k).equals(tabla.getValueAt(i-1, j))) {
                                        unidadAEliminar.add(datoS[i][j][1]);
                                    }
                                }
                            else
                                unidadAEliminar.add(datoS[i][j][1]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < unidadAEliminar.size(); i++) {
            uni.quitaHorario(Integer.parseInt(unidadAEliminar.get(i)));
        }
    }

}
