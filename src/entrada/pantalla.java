/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrada;

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
public class pantalla implements ActionListener, MouseListener {

    JFrame ventana;
    Font titulop, tit;
    Font titulopb, titb;
    Font subtitulos;
    Color azul;
    Color azulAcento;
    Color blanco;
    JLabel titulo, tituloBold, subtitulo;
    JLabel titulo2, tituloBold2, subtituloEntradaOk, imagenEntradaOk;
    JLabel subtituloSalidaOk, imagenSalidaOk;
    JLabel subtituloEntrada, imagenEntrada;
    JLabel subtituloSalida, imagenSalida;
    JPanel arriba, abajo;
    JTextField usuario;
    JPasswordField contrasena;
    JSeparator lineaUsr, lineaCont;
    JButton alumnos, grupos, profesor, modificacion, configuracion, cerrar;
    JPanel defauult, permitirEntrada, permitirSalida, denegarEntrada, denegarSalida;

    public pantalla() {
        ventana = new JFrame("PoliAsistencia");
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
        subtitulos = new Font("Calibri", 0, 40);
        titulopb =  new Font("Calibri", 1, 103);
        titulop =  new Font("Calibri", 0, 90);
        titb = new Font("Calibri", 1, 93);
        tit = new Font("Calibri", 0, 80);
        
        defauult = new JPanel();
        defauult.setLayout(null);
        defauult.setBackground(blanco);
        defauult.setBounds(0, 0, 1300, 650);
        
        //Titulos
        titulo = new JLabel("Poli");
        titulo.setBounds(390, 240, 150, 100);
        titulo.setFont(titulop);
        titulo.setForeground(azul);
        defauult.add(titulo);
        
        tituloBold = new JLabel("Asistencia");
        tituloBold.setBounds(525, 237, 450, 100);
        tituloBold.setFont(titulopb);
        tituloBold.setForeground(azulAcento);
        defauult.add(tituloBold);
        
        
        configuracion = new JButton("Configuracion");
        configuracion.setBounds(20, 23, 170, 50);
        configuracion.setBackground(blanco);
        configuracion.setBorder(BorderFactory.createLineBorder(azulAcento, 2));
        configuracion.setFont(new java.awt.Font("Arial", 0, 19));
        configuracion.setForeground(azulAcento);
        configuracion.setFocusPainted(false);
        configuracion.addActionListener(this);
        defauult.add(configuracion);
        
        subtitulo = new JLabel("Coloca el dedo en el sensor para entrar o salir del plantel");
        subtitulo.setIcon(new ImageIcon(getClass().getResource("/img/huella.png")));
        subtitulo.setFont(subtitulos);
        subtitulo.setForeground(azulAcento);
        subtitulo.setBounds(170, 350, 1000, 100);
        defauult.add(subtitulo);
        
        defauult.setVisible(true);
        ventana.add(defauult);
        
        //Entrada bien
        
        permitirEntrada = new JPanel();
        permitirEntrada.setLayout(null);
        permitirEntrada.setBackground(blanco);
        permitirEntrada.setBounds(0, 0, 1300, 650);
        
        titulo2 = new JLabel("Poli");
        titulo2.setBounds(400, 10, 119, 100);
        titulo2.setFont(tit);
        titulo2.setForeground(azul);
        permitirEntrada.add(titulo2);
        
        tituloBold2 = new JLabel("Asistencia");
        tituloBold2.setBounds(519, 7, 400, 100);
        tituloBold2.setFont(titb);
        tituloBold2.setForeground(azulAcento);
        permitirEntrada.add(tituloBold2);
        
        subtituloEntradaOk = new JLabel("Bienvenido Obed");
        subtituloEntradaOk.setIcon(new ImageIcon(getClass().getResource("/img/aceptar.png")));
        subtituloEntradaOk.setFont(subtitulos);
        subtituloEntradaOk.setForeground(azulAcento);
        subtituloEntradaOk.setBounds(450, 480, 1000, 100);
        permitirEntrada.add(subtituloEntradaOk);
        
        imagenEntrada = new JLabel(new ImageIcon(getClass().getResource("/img/obed.jpg")));
        imagenEntrada.setBounds(490, 150, 300, 300);
        permitirEntrada.add(imagenEntrada);
        
        permitirEntrada.setVisible(false);
        ventana.add(permitirEntrada);
        
        //salida bien
        
        permitirSalida = new JPanel();
        permitirSalida.setLayout(null);
        permitirSalida.setBackground(blanco);
        permitirSalida.setBounds(0, 0, 1300, 650);
        
        titulo2 = new JLabel("Poli");
        titulo2.setBounds(400, 10, 119, 100);
        titulo2.setFont(tit);
        titulo2.setForeground(azul);
        permitirSalida.add(titulo2);
        
        tituloBold2 = new JLabel("Asistencia");
        tituloBold2.setBounds(519, 7, 400, 100);
        tituloBold2.setFont(titb);
        tituloBold2.setForeground(azulAcento);
        permitirSalida.add(tituloBold2);
        
        subtituloSalidaOk = new JLabel("Que tengas un buen día, Obed");
        subtituloSalidaOk.setIcon(new ImageIcon(getClass().getResource("/img/aceptar.png")));
        subtituloSalidaOk.setFont(subtitulos);
        subtituloSalidaOk.setForeground(azulAcento);
        subtituloSalidaOk.setBounds(350, 480, 1000, 100);
        permitirSalida.add(subtituloSalidaOk);
        
        imagenSalida = new JLabel(new ImageIcon(getClass().getResource("/img/obed.jpg")));
        imagenSalida.setBounds(490, 150, 300, 300);
        permitirSalida.add(imagenSalida);
        
        permitirSalida.setVisible(false);
        ventana.add(permitirSalida);
        
        //entrada mal
        
        denegarEntrada = new JPanel();
        denegarEntrada.setLayout(null);
        denegarEntrada.setBackground(blanco);
        denegarEntrada.setBounds(0, 0, 1300, 650);
        
        titulo2 = new JLabel("Poli");
        titulo2.setBounds(400, 10, 119, 100);
        titulo2.setFont(tit);
        titulo2.setForeground(azul);
        denegarEntrada.add(titulo2);
        
        tituloBold2 = new JLabel("Asistencia");
        tituloBold2.setBounds(519, 7, 400, 100);
        tituloBold2.setFont(titb);
        tituloBold2.setForeground(azulAcento);
        denegarEntrada.add(tituloBold2);
        
        subtituloEntrada = new JLabel("No puedes entrar más de una vez al día Obed");
        subtituloEntrada.setIcon(new ImageIcon(getClass().getResource("/img/denegar.png")));
        subtituloEntrada.setFont(subtitulos);
        subtituloEntrada.setForeground(azulAcento);
        subtituloEntrada.setBounds(250, 480, 1000, 100);
        denegarEntrada.add(subtituloEntrada);
        
        imagenEntrada = new JLabel(new ImageIcon(getClass().getResource("/img/obed.jpg")));
        imagenEntrada.setBounds(490, 150, 300, 300);
        denegarEntrada.add(imagenEntrada);
        
        denegarEntrada.setVisible(false);
        ventana.add(denegarEntrada);
        
        //salida mal
        
        denegarSalida = new JPanel();
        denegarSalida.setLayout(null);
        denegarSalida.setBackground(blanco);
        denegarSalida.setBounds(0, 0, 1300, 650);
        
        titulo2 = new JLabel("Poli");
        titulo2.setBounds(400, 10, 119, 100);
        titulo2.setFont(tit);
        titulo2.setForeground(azul);
        denegarSalida.add(titulo2);
        
        tituloBold2 = new JLabel("Asistencia");
        tituloBold2.setBounds(519, 7, 400, 100);
        tituloBold2.setFont(titb);
        tituloBold2.setForeground(azulAcento);
        denegarSalida.add(tituloBold2);
        
        subtituloSalida = new JLabel("Aún no puedes salir, Obed");
        subtituloSalida.setIcon(new ImageIcon(getClass().getResource("/img/aceptar.png")));
        subtituloSalida.setFont(subtitulos);
        subtituloSalida.setForeground(azulAcento);
        subtituloSalida.setBounds(350, 480, 1000, 100);
        denegarSalida.add(subtituloSalida);
        
        imagenSalida = new JLabel(new ImageIcon(getClass().getResource("/img/obed.jpg")));
        imagenSalida.setBounds(490, 150, 300, 300);
        denegarSalida.add(imagenSalida);
        
        denegarSalida.setVisible(false);
        ventana.add(denegarSalida);
        
        
        //Contenido

        /*
         b2.setVerticalTextPosition(AbstractButton.BOTTOM);
         b2.setHorizontalTextPosition(AbstractButton.CENTER);
         */

        

        ventana.setVisible(permiso);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        
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
