/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaUsuarios;

import poliasistencia.login;
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
public class inscribirAlumnosMenu implements ActionListener, MouseListener {

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
    JButton adentro, afuera, configuracion, cerrar, grupo;

    public inscribirAlumnosMenu() {
        ventana = new JFrame("Inscribir alumnos - PoliAsistencia");
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
        titulop = new Font("Calibri", 0, 57);

        //Titulos
        titulo = new JLabel("Elige una opción");
        titulo.setBounds(430, 5, 700, 100);
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

        ImageIcon iconRegular = new ImageIcon(new ImageIcon(getClass().getResource("/img/regular.png")).getImage());
        adentro = new JButton("<html>Inscribir individual</html>", iconRegular);
        adentro.setBounds(180, 250, 380, 150);
        adentro.setBackground(blanco);
        adentro.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        adentro.setFont(new java.awt.Font("Arial", 0, 25));
        adentro.setForeground(azulAcento);
        adentro.setFocusPainted(false);
        adentro.addMouseListener(this);
        adentro.addActionListener(this);
        ventana.add(adentro);

        ImageIcon iconIrregular = new ImageIcon(new ImageIcon(getClass().getResource("/img/grupo.png")).getImage());
        afuera = new JButton("<html><center>Inscribir muchos a<br>un grupo</center></html>", iconIrregular);
        afuera.setBounds(710, 250, 380, 150);
        afuera.setBackground(blanco);
        afuera.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        afuera.setFont(new java.awt.Font("Arial", 0, 25));
        afuera.setForeground(azulAcento);
        afuera.setFocusPainted(false);
        afuera.addMouseListener(this);
        afuera.addActionListener(this);
        ventana.add(afuera);

        abajo = new JPanel();
        abajo.setBounds(0, 100, 1300, 550);
        abajo.setBackground(blanco);
        ventana.add(abajo);

        
        ventana.setVisible(permiso);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        ventana.dispose();
        if(a.getSource() == cerrar){
            elegirOpcion abrir = new elegirOpcion();
            abrir.crearComponentes(true);
        }
        if(a.getSource() == adentro){
            alumnoRegular abrir = new alumnoRegular();
            abrir.crearComponentes(true);
        }
        if(a.getSource() == afuera){
            seleccionarGrupo abrir = new seleccionarGrupo();
            abrir.crearComponentes(true);
        }
        if(a.getSource() == grupo){
            grupos abrir = new grupos();
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
        if(me.getSource() == adentro)
            adentro.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
        if(me.getSource() == afuera)
            afuera.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
        if(me.getSource() == grupo)
            grupo.setBorder(BorderFactory.createLineBorder(azulAcento, 3));
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if(me.getSource() == adentro)
            adentro.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        if(me.getSource() == afuera)
            afuera.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        if(me.getSource() == grupo)
            grupo.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    }
}
