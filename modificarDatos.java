/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaUsuarios;

import vistaGrupos.editarProfesores;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author PoliAsistencia
 */
public class modificarDatos implements ActionListener, MouseListener {

    JFrame ventana;
    Font titulop;
    Font titulopb;
    Font subtitulos;
    Color azul;
    Color azulAcento;
    Color blanco;
    JLabel titulo, tituloBold, dusuario, dcontrasena;
    JPanel arriba, abajo;
    JTextField usuario;
    JPasswordField contrasena;
    JSeparator lineaUsr, lineaCont;
    JButton alumnos, grupos, profesor, modificacion, configuracion, cerrar;

    public modificarDatos() {
        ventana = new JFrame("Modificación de Datos - PoliAsistencia");
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
        titulopb = new Font("Calibri", 1, 93);
        titulop = new Font("Calibri", 0, 50);

        //Titulos
        titulo = new JLabel("Modificación de Datos");
        titulo.setBounds(420, 5, 700, 100);
        titulo.setFont(titulop);
        titulo.setForeground(blanco);
        ventana.add(titulo);
        
        
        
        ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("/img/atras.png")).getImage());
        cerrar = new JButton("<html>&nbsp;Inicio</html>", atras);
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

        ImageIcon iconAlumno = new ImageIcon(new ImageIcon(getClass().getResource("/img/mortarboard.png")).getImage());
        alumnos = new JButton("Datos de Alumnos", iconAlumno);
        alumnos.setBounds(170, 270, 380, 150);
        alumnos.setBackground(blanco);
        alumnos.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        alumnos.setFont(new java.awt.Font("Arial", 0, 25));
        alumnos.setForeground(azulAcento);
        alumnos.setFocusPainted(false);
        alumnos.addMouseListener(this);
        alumnos.addActionListener(this);
        ventana.add(alumnos);

        

        ImageIcon iconProfe = new ImageIcon(new ImageIcon(getClass().getResource("/img/books.png")).getImage());
        profesor = new JButton("<html><center>Datos de<br>profesores</center></html>", iconProfe);
        profesor.setBounds(700, 270, 380, 150);
        profesor.setBackground(blanco);
        profesor.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        profesor.setFont(new java.awt.Font("Arial", 0, 25));
        profesor.setForeground(azulAcento);
        profesor.setFocusPainted(false);
        profesor.addMouseListener(this);
        profesor.addActionListener(this);
        ventana.add(profesor);

        

        abajo = new JPanel();
        abajo.setBounds(0, 100, 1300, 550);
        abajo.setBackground(blanco);
        ventana.add(abajo);

        ventana.setVisible(permiso);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        ventana.dispose();
        if (a.getSource() == cerrar) {
            ventana.dispose();
            inicio abrir = new inicio();
            abrir.crearComponentes(true);
        }
       
        if(a.getSource() == alumnos){
            editarAlumnos abrir = new editarAlumnos();
            abrir.crearComponentes(true);
        }
        if(a.getSource() == profesor){
            editarProfesores abrir = new editarProfesores();
            abrir.crearComponentes(true);
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
        if(me.getSource() == alumnos)
            alumnos.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
        if(me.getSource() == grupos)
            grupos.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
        if(me.getSource() == profesor)
            profesor.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
        if(me.getSource() == modificacion)
            modificacion.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if(me.getSource() == alumnos)
            alumnos.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        if(me.getSource() == grupos)
            grupos.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        if(me.getSource() == profesor)
            profesor.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        if(me.getSource() == modificacion)
            modificacion.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    }
}
