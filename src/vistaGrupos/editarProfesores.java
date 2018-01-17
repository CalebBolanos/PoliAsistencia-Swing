/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaGrupos;

import controlador.traerDatos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class editarProfesores implements ActionListener, MouseListener, KeyListener {

    JFrame ventana;
    Font titulop;
    Font titulopb;
    Font subtitulos;
    Color azul;
    Color azulAcento;
    Color blanco;
    JLabel sub, titulo, tituloBold, dnombre, dcontrasena, dcontrasena2, confirm = new JLabel("Escribe tu contraseña para guardar los cambios");
    JPanel arriba, abajo;
    JTextField nombre, paterno, materno, nTrabajador, buscador;
    JPasswordField contrasena, contrasenax2, contrasenaConfirmacion = new JPasswordField();
    JSeparator lineaNombre, lineaCont, lineaCont2;
    JButton cerrar, guardar;
    JTable tabla;
    JScrollPane scrollpane;
    DefaultTableModel modelo;
    Object[] agregar = {confirm, contrasenaConfirmacion};
    String[] columnNames = { "No. de Trabajador", "Nombre", "Apellido Paterno", "Apellido Metreno", "Genero"};
    Object[][] datos;

    public editarProfesores() {
        ventana = new JFrame("Editar Datos de Profesores - PoliAsistencia");
        ventana.setBounds(30, 30, 1300, 650);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setLayout(null);
    }

    public void crearComponentes(boolean permiso) {
        traerDatos td = new traerDatos();
        datos = td.datosProfesor();
        blanco = new Color(255, 255, 255);
        azulAcento = new Color(0, 145, 234);
        azul = new Color(0, 176, 255);
        subtitulos = new Font("Arial", 0, 25);
        titulopb = new Font("Calibri", 1, 17);
        titulop = new Font("Calibri", 0, 50);

        //Titulos
        titulo = new JLabel("Editar Datos del Profesor");
        titulo.setBounds(370, 5, 700, 100);
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

        sub = new JLabel("Filtrar con No. de trabajador");
        sub.setBounds(50, 140, 350, 40);
        sub.setFont(titulopb);
        sub.setOpaque(true);
        ventana.add(sub);
        
        buscador = new JTextField();
        buscador.setBounds(255, 145, 100, 30);
        buscador.addKeyListener(this);
        ventana.add(buscador);
        
        modelo = new DefaultTableModel(datos, columnNames);
        tabla = new JTable(modelo);
        tabla.addMouseListener(this);
        tabla.isCellEditable(0, 0);
        scrollpane = new JScrollPane(tabla);

        scrollpane.setBounds(50, 180, 1200, 400);
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
            profesoresInicio abrir = new profesoresInicio();
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
                String boletaOb = (String)tabla.getValueAt(i, 0);
                traerDatos td = new traerDatos();
                String datosOb[] = td.datoIndividualProf(boletaOb);
                int existe = Integer.parseInt(datosOb[0]);
                if(existe > 0){
                    ventana.dispose();
                    profesores prof = new profesores(datosOb);
                    prof.crearComponentes(true);
                }else{
                    JOptionPane.showMessageDialog(ventana, "No existe el profesor", "Error", JOptionPane.ERROR_MESSAGE);
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

}
