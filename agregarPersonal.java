/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaUsuarios;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author PoliAsistencia
 */
public class agregarPersonal implements ActionListener {

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
    Object[] agregar = {confirm, contrasenaConfirmacion};

    

    public agregarPersonal() {
        ventana = new JFrame("Agregar Personal - PoliAsistencia");
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
        titulo = new JLabel("Agregar Personal");
        titulo.setBounds(430, 5, 700, 100);
        titulo.setFont(titulop);
        titulo.setForeground(blanco);
        ventana.add(titulo);

        ImageIcon atras = new ImageIcon(new ImageIcon(getClass().getResource("/img/atras.png")).getImage());
        cerrar = new JButton("<html>&nbsp;Configuración</html>", atras);
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
        dnombre = new JLabel("Nombre");
        dnombre.setFont(new java.awt.Font("Arial", 0, 17));
        dnombre.setBounds(70, 110, 300, 100);
        dnombre.setForeground(azulAcento);
        ventana.add(dnombre);
        //70
        nombre = new JTextField();
        nombre.setBounds(70, 180, 530, 50);
        nombre.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        nombre.setBackground(blanco);
        nombre.setForeground(Color.BLACK);
        nombre.setFont(subtitulos);
        ventana.add(nombre);
        //50
        lineaNombre = new JSeparator(SwingConstants.HORIZONTAL);
        lineaNombre.setBounds(70, 230, 530, 10);
        lineaNombre.setBackground(azulAcento);
        lineaNombre.setForeground(azulAcento);
        ventana.add(lineaNombre);

        dnombre = new JLabel("Paterno");
        dnombre.setFont(new java.awt.Font("Arial", 0, 17));
        dnombre.setBounds(70, 235, 300, 100);
        dnombre.setForeground(azulAcento);
        ventana.add(dnombre);
        //70
        paterno = new JTextField();
        paterno.setBounds(70, 305, 530, 50);
        paterno.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        paterno.setBackground(blanco);
        paterno.setForeground(Color.BLACK);
        paterno.setFont(subtitulos);
        ventana.add(paterno);
        //50
        lineaNombre = new JSeparator(SwingConstants.HORIZONTAL);
        lineaNombre.setBounds(70, 355, 530, 10);
        lineaNombre.setBackground(azulAcento);
        lineaNombre.setForeground(azulAcento);
        ventana.add(lineaNombre);

        dnombre = new JLabel("Materno");
        dnombre.setFont(new java.awt.Font("Arial", 0, 17));
        dnombre.setBounds(70, 360, 300, 100);
        dnombre.setForeground(azulAcento);
        ventana.add(dnombre);
        //70
        materno = new JTextField();
        materno.setBounds(70, 430, 530, 50);
        materno.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        materno.setBackground(blanco);
        materno.setForeground(Color.BLACK);
        materno.setFont(subtitulos);
        ventana.add(materno);
        //50
        lineaNombre = new JSeparator(SwingConstants.HORIZONTAL);
        lineaNombre.setBounds(70, 480, 530, 10);
        lineaNombre.setBackground(azulAcento);
        lineaNombre.setForeground(azulAcento);
        ventana.add(lineaNombre);

        dnombre = new JLabel("No. de trabajador");
        dnombre.setFont(new java.awt.Font("Arial", 0, 17));
        dnombre.setBounds(690, 110, 300, 100);
        dnombre.setForeground(azulAcento);
        ventana.add(dnombre);
        //70
        nTrabajador = new JTextField();
        nTrabajador.setBounds(690, 180, 530, 50);
        nTrabajador.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        nTrabajador.setBackground(blanco);
        nTrabajador.setForeground(Color.BLACK);
        nTrabajador.setFont(subtitulos);
        ventana.add(nTrabajador);
        //50
        lineaNombre = new JSeparator(SwingConstants.HORIZONTAL);
        lineaNombre.setBounds(690, 230, 530, 10);
        lineaNombre.setBackground(azulAcento);
        lineaNombre.setForeground(azulAcento);
        ventana.add(lineaNombre);

        dcontrasena = new JLabel("Escribe una contraseña para el nuevo personal");
        dcontrasena.setFont(new java.awt.Font("Arial", 0, 17));
        dcontrasena.setBounds(690, 235, 500, 100);
        dcontrasena.setForeground(azulAcento);
        ventana.add(dcontrasena);

        //70
        contrasena = new JPasswordField();
        contrasena.setBounds(690, 305, 530, 50);
        contrasena.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        contrasena.setBackground(blanco);
        contrasena.setForeground(Color.BLACK);
        contrasena.setFont(subtitulos);
        ventana.add(contrasena);
        //50
        lineaCont = new JSeparator(SwingConstants.HORIZONTAL);
        lineaCont.setBounds(690, 355, 530, 10);
        lineaCont.setBackground(azulAcento);
        lineaCont.setForeground(azulAcento);
        ventana.add(lineaCont);

        dcontrasena2 = new JLabel("Escribe de nuevo la contraseña");
        dcontrasena2.setFont(new java.awt.Font("Arial", 0, 17));
        dcontrasena2.setBounds(690, 360, 500, 100);
        dcontrasena2.setForeground(azulAcento);
        ventana.add(dcontrasena2);
        //70
        contrasenax2 = new JPasswordField();
        contrasenax2.setBounds(690, 430, 530, 50);
        contrasenax2.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        contrasenax2.setBackground(blanco);
        contrasenax2.setForeground(Color.BLACK);
        contrasenax2.setFont(subtitulos);
        ventana.add(contrasenax2);
        //50
        lineaCont2 = new JSeparator(SwingConstants.HORIZONTAL);
        lineaCont2.setBounds(690, 480, 530, 10);
        lineaCont2.setBackground(azulAcento);
        lineaCont2.setForeground(azulAcento);
        ventana.add(lineaCont2);

        guardar = new JButton("Guardar Cambios");//boton que se utiliza para cambiar datos sin modificar contraseña
        guardar.setBounds(550, 520, 200, 50);
        guardar.setBackground(blanco);
        guardar.setBorder(BorderFactory.createLineBorder(azulAcento, 2));
        guardar.setFont(new java.awt.Font("Arial", 0, 18));
        guardar.setForeground(azulAcento);
        guardar.addActionListener(this);
        ventana.add(guardar);
              

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
            configuracion abrir = new configuracion();
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

}
