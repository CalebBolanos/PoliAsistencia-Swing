/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaGrupos;

import controlador.sesion;
import controlador.traerDatos;
import vistaUsuarios.*;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author PoliAsistencia
 */
public class configuracion implements ActionListener, MouseListener {

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
    JButton alumnos, cuenta, personal, agregar, info, cerrar;

    public configuracion() {
        ventana = new JFrame("Configuración - PoliAsistencia");
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
        titulop = new Font("Calibri", 0, 60);

        //Titulos
        titulo = new JLabel("Configuración");
        titulo.setBounds(480, 5, 400, 100);
        titulo.setFont(titulop);
        titulo.setForeground(blanco);
        ventana.add(titulo);
        
        ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("/img/atras.png")).getImage());
        cerrar = new JButton("<html>&nbsp;Inicio</html>", atras);
        cerrar.setBounds(20, 23, 140, 50);
        cerrar.setBackground(azulAcento);
        cerrar.setBorder(BorderFactory.createLineBorder(blanco, 2));
        cerrar.setFont(new java.awt.Font("Arial", 0, 25));
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

        ImageIcon iconUsuario = new ImageIcon(new ImageIcon(getClass().getResource("/img/usuario.png")).getImage());
        cuenta = new JButton("Editar datos de esta cuenta", iconUsuario);
        cuenta.setBounds(390, 150, 500, 70);
        cuenta.setBackground(blanco);
        cuenta.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        cuenta.setFont(new java.awt.Font("Arial", 0, 25));
        cuenta.setForeground(azulAcento);
        cuenta.setFocusPainted(false);
        cuenta.addActionListener(this);
        cuenta.addMouseListener(this);
        ventana.add(cuenta);
        /*
        ImageIcon iconAgregar = new ImageIcon(new ImageIcon(getClass().getResource("/img/agregarUsuario.png")).getImage());
        agregar = new JButton("Agregar personal", iconAgregar);
        agregar.setBounds(390, 250, 500, 70);
        agregar.setBackground(blanco);
        agregar.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        agregar.setFont(new java.awt.Font("Arial", 0, 25));
        agregar.setForeground(azulAcento);
        agregar.setFocusPainted(false);
        agregar.addActionListener(this);
        agregar.addMouseListener(this);
        ventana.add(agregar);
        
        ImageIcon persona = new ImageIcon(new ImageIcon(getClass().getResource("/img/personal.png")).getImage());
        personal = new JButton("Editar datos de personal", persona);
        personal.setBounds(390, 350, 500, 70);
        personal.setBackground(blanco);
        personal.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        personal.setFont(new java.awt.Font("Arial", 0, 25));
        personal.setForeground(azulAcento);
        personal.setFocusPainted(false);
        personal.addActionListener(this);
        personal.addMouseListener(this);
        ventana.add(personal);*/
        
        ImageIcon acerca = new ImageIcon(new ImageIcon(getClass().getResource("/img/info.png")).getImage());
        info = new JButton("Acerca de PoliAsistencia", acerca);
        info.setBounds(390, 250, 500, 70);
        info.setBackground(blanco);
        info.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        info.setFont(new java.awt.Font("Arial", 0, 25));
        info.setForeground(azulAcento);
        info.setFocusPainted(false);
        info.addActionListener(this);
        info.addMouseListener(this);
        ventana.add(info);

        abajo = new JPanel();
        abajo.setBounds(0, 100, 1300, 550);
        abajo.setBackground(blanco);
        ventana.add(abajo);

        ventana.setVisible(permiso);
    }

    @Override
    public void actionPerformed(ActionEvent a) {

        if(a.getSource() == cerrar){
            inicio abrir = new inicio();
            abrir.crearComponentes(true);
        }
        if(a.getSource() == cuenta){
            traerDatos td = new traerDatos();
            sesion ss = new sesion();
            String[] dddat = td.datoIndividualProf(ss.getNumT());
            editarCuenta abrir = new editarCuenta(dddat);
            abrir.crearComponentes(true);
        }
        if(a.getSource() == agregar){
            agregarPersonal abrir = new agregarPersonal();
            abrir.crearComponentes(true);
        }
        if(a.getSource() == personal){
            editarPersonal abrir = new editarPersonal();
            abrir.crearComponentes(true);
        }
        if(a.getSource() == info){
            try{
                Desktop.getDesktop().browse(new URI("https://www.google.com"));
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Pagina no disponible por el momento");
            }
        }
        ventana.dispose();
    }
    //Es para hacer los botones mas bonitos xd
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
        if(me.getSource() == cuenta){
            cuenta.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
        }
        if(me.getSource() == agregar){
            agregar.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
        }
        if(me.getSource() == personal){
            personal.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
        }
        if(me.getSource() == info){
            info.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if(me.getSource() == cuenta){
            cuenta.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        }
        if(me.getSource() == agregar){
            agregar.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        }
        if(me.getSource() == personal){
            personal.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        }
        if(me.getSource() == info){
            info.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        }
    }
    
}
