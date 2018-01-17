/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaUsuarios;

import controlador.traerDatos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PoliAsistencia
 */
public class editarAlumnos implements ActionListener, MouseListener {

    JFrame ventana;
    Font titulop;
    Font titulopb;
    Font subtitulos;
    Color azul;
    Color azulAcento;
    Color blanco;
    JLabel titulo, tituloBold, dnombre, dcontrasena, dcontrasena2, confirm = new JLabel("Escribe tu contraseña para guardar los cambios");
    JPanel arriba, abajo;
    JTextField nombre, paterno, materno, nTrabajador;
    JPasswordField contrasena, contrasenax2, contrasenaConfirmacion = new JPasswordField();
    JSeparator lineaNombre, lineaCont, lineaCont2;
    JButton cerrar, guardar;
    JTable tabla;
    JScrollPane scrollpane;
    DefaultTableModel modelo;
    Object[] agregar = {confirm, contrasenaConfirmacion};
    String[] columnNames = { "Boleta", "Nombre", "Apellido Paterno", "Apellido Materno", "Estado"};
    Object[][] datos;
    public editarAlumnos() {
        ventana = new JFrame("Editar Datos de Alumno - PoliAsistencia");
        ventana.setBounds(30, 30, 1300, 650);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setLayout(null);
    }

    public void crearComponentes(boolean permiso) {
        traerDatos td = new traerDatos();
        datos = td.datosAlumnos();
        blanco = new Color(255, 255, 255);
        azulAcento = new Color(0, 145, 234);
        azul = new Color(0, 176, 255);
        subtitulos = new Font("Arial", 0, 25);
        titulopb = new Font("Calibri", 1, 93);
        titulop = new Font("Calibri", 0, 50);

        //Titulos
        titulo = new JLabel("Editar Datos del Alumno");
        titulo.setBounds(400, 5, 700, 100);
        titulo.setFont(titulop);
        titulo.setForeground(blanco);
        ventana.add(titulo);

        ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("/img/atras.png")).getImage());
        cerrar = new JButton("<html>&nbsp;Modificar datos</html>", atras);
        cerrar.setBounds(20, 23, 170, 50);
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
        modelo = new DefaultTableModel(datos, columnNames);
        tabla = new JTable(modelo);
        tabla.addMouseListener(this);
        tabla.isCellEditable(0, 0);
        scrollpane = new JScrollPane(tabla);

        scrollpane.setBounds(50, 150, 1200, 400);
        ventana.add(scrollpane);

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
            inicio abrir = new inicio();
            abrir.crearComponentes(true);
        }
        

        if (a.getSource() == guardar) {
            //vaidar campos vacios sin incluir los passwordfields porque no se usan xd
            /*
            * -1 = cerrado
            * 0 = aceptar
            * 2 = cancelar
             */
            while (true) {
                String ps = "";
                int evaluar = JOptionPane.showConfirmDialog(null, agregar, "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                if (evaluar == -1) {
                    contrasenaConfirmacion.setText("");
                    break;
                }
                if (evaluar == 0) {
                    for (int i = 0; i < contrasenaConfirmacion.getPassword().length; i++) {
                        ps += contrasenaConfirmacion.getPassword()[i];
                    }
                    System.out.println(ps);
                    if (ps.equals("xd")) {//se obtiene la contraseña de la bd y se compara con lo que escribio el usr
                        JOptionPane.showMessageDialog(ventana, "Modificaciones guardadas correctamente");
                        configuracion abrir = new configuracion();
                        abrir.crearComponentes(true);
                        ventana.dispose();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(ventana, "Contraseña incorrecta");
                        contrasenaConfirmacion.setText("");
                    }
                }
                if (evaluar == 2) {
                    contrasenaConfirmacion.setText("");
                    break;
                }
            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if (tabla.getSelectedRow() == i) {
                String boletaOb = (String)modelo.getValueAt(i, 0);
                traerDatos td = new traerDatos();
                System.out.println(boletaOb);
                String datosOb[] = td.datoIndividual(boletaOb);
                int existe = Integer.parseInt(datosOb[0]);
                if(existe > 0){
                    ventana.dispose();
                    alumnos alumno = new alumnos(datosOb);
                    alumno.crearComponentes(true);
                }else{
                    JOptionPane.showMessageDialog(ventana, "No existe el alumno", "Error", JOptionPane.ERROR_MESSAGE);
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

}
