/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poliasistencia;

import controlador.sesion;
import controlador.validaciones;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class login implements ActionListener{    
    
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
    JButton iniciar, iniciarHuella;
    
    
    
    public login(){
        ventana = new JFrame("PoliAsistencia");
        ventana.setBounds(30, 30, 1300, 650);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setLayout(null);
    }
    
    public void crearComponentes(boolean permiso){
        
        blanco = new Color(255, 255, 255);
        azulAcento = new Color(0, 145, 234);
        azul = new Color(0, 176, 255);
        subtitulos = new Font("Arial", 0, 25);
        titulopb =  new Font("Calibri", 1, 93);
        titulop =  new Font("Calibri", 0, 80);
        
        
        titulo = new JLabel("Poli");
        titulo.setBounds(400, 10, 119, 100);
        titulo.setFont(titulop);
        titulo.setForeground(azul);
        ventana.add(titulo);
        
        tituloBold = new JLabel("Asistencia");
        tituloBold.setBounds(519, 7, 400, 100);
        tituloBold.setFont(titulopb);
        tituloBold.setForeground(azulAcento);
        ventana.add(tituloBold);
      
        arriba = new JPanel();
        arriba.setBounds(0, 0, 1300, 100);
        arriba.setBackground(blanco);
        ventana.add(arriba);
        
        
        
        usuario = new JTextField();
        usuario.setBounds(320, 280, 675, 50);
        usuario.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        usuario.setBackground(azulAcento);
        usuario.setForeground(blanco);
        usuario.setFont(subtitulos);
        usuario.setCaretColor(blanco);
        ventana.add(usuario);
        
        lineaUsr = new JSeparator(SwingConstants.HORIZONTAL);
        lineaUsr.setBounds(320, 330, 675, 10);
        lineaUsr.setBackground(blanco);
        lineaUsr.setForeground(blanco);
        ventana.add(lineaUsr);
        
        dusuario = new JLabel("Usuario");
        dusuario.setFont(new java.awt.Font("Arial", 0, 17));
        dusuario.setBounds(320, 210, 300, 100);
        dusuario.setForeground(blanco);
        ventana.add(dusuario);
        
        contrasena = new JPasswordField();
        contrasena.setBounds(320, 400, 675, 50);
        contrasena.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        contrasena.setBackground(azulAcento);
        contrasena.setForeground(blanco);
        contrasena.setFont(subtitulos);
        contrasena.setCaretColor(blanco);
        ventana.add(contrasena);
        
        lineaCont = new JSeparator(SwingConstants.HORIZONTAL);
        lineaCont.setBounds(320, 450, 675, 10);
        lineaCont.setBackground(blanco);
        lineaCont.setForeground(blanco);
        ventana.add(lineaCont);
        
        dcontrasena = new JLabel("Contraseña");
        dcontrasena.setFont(new java.awt.Font("Arial", 0, 17));
        dcontrasena.setBounds(320, 335, 300, 100);
        dcontrasena.setForeground(blanco);
        ventana.add(dcontrasena);
        
        iniciar = new JButton("Iniciar Sesión");
        iniciar.setBounds(700, 520, 200, 50);
        iniciar.setBackground(azulAcento);
        iniciar.setBorder(BorderFactory.createLineBorder(blanco, 2));
        iniciar.setFont(new java.awt.Font("Arial", 0, 18));
        iniciar.setForeground(blanco);
        iniciar.addActionListener(this);
        ventana.add(iniciar);
        
        iniciarHuella = new JButton("Iniciar con huella");
        iniciarHuella.setBounds(350, 520, 200, 50);
        iniciarHuella.setBackground(azulAcento);
        iniciarHuella.setBorder(BorderFactory.createLineBorder(blanco, 2));
        iniciarHuella.setFont(new java.awt.Font("Arial", 0, 18));
        iniciarHuella.setForeground(blanco);
        iniciarHuella.addActionListener(this);
        ventana.add(iniciarHuella);
        
        abajo = new JPanel();
        abajo.setBounds(0, 100, 1300, 550);
        abajo.setBackground(azulAcento);
        ventana.add(abajo);
        
        ventana.setVisible(permiso);
    }
    
    @Override
    public void actionPerformed(ActionEvent a) {
        if(a.getSource() == iniciarHuella){
            sesionConHuella sessi = new sesionConHuella();
            sessi.setVisible(true);
            ventana.dispose();
//            int ids[] = sessi.getIDS();
//            if(ids[0]>0){
//                        switch(ids[1]){
//                            case 1:
//                                vistaUsuarios.inicio nuevo = new vistaUsuarios.inicio();
//                                ventana.dispose();
//                                nuevo.crearComponentes(true);
//                                break;
//                            case 4:
//                                vistaGrupos.inicio nuevo2 = new vistaGrupos.inicio();
//                                ventana.dispose();
//                                nuevo2.crearComponentes(true);
//                                break;
//                            default:
//                                JOptionPane.showMessageDialog(ventana, "No esta autorizado", "Error", JOptionPane.ERROR_MESSAGE);
//                                contrasena.setText("");
//                                usuario.requestFocus();
//                                break;
//                        }
//                    }else{
//                        JOptionPane.showMessageDialog(ventana, "No se encontro la huella", "Error", JOptionPane.ERROR_MESSAGE);
//                        contrasena.setText("");
//                        usuario.requestFocus();
//                    }
        }
        if(a.getSource() == iniciar){
            String usr = usuario.getText();
            String pass=new String(contrasena.getPassword());
            sesion ses = new sesion();
            int id[] = new int[2];
            controlador.validaciones val = new validaciones();
            if(val.sinVacios(usr, "el usuario", 40)){
                if(val.sinVacios(pass, "la contraseña", 60)){
                    id = ses.iniciaSesion(usr, pass);
                    if(id[0]>0){
                        switch(id[1]){
                            case 1:
                                vistaUsuarios.inicio nuevo = new vistaUsuarios.inicio();
                                ventana.dispose();
                                nuevo.crearComponentes(true);
                                break;
                            case 4:
                                vistaGrupos.inicio nuevo2 = new vistaGrupos.inicio();
                                ventana.dispose();
                                nuevo2.crearComponentes(true);
                                break;
                            default:
                                JOptionPane.showMessageDialog(ventana, "Contraseña o "
                                + "usuario incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                                contrasena.setText("");
                                usuario.requestFocus();
                                break;
                        }
                    }else{
                        JOptionPane.showMessageDialog(ventana, "Contraseña o "
                                + "usuario incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                        contrasena.setText("");
                        usuario.requestFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(ventana, val.err(), "Error", JOptionPane.ERROR_MESSAGE);
                    contrasena.requestFocus();
                }
            }else{
               JOptionPane.showMessageDialog(ventana, val.err(), "Error", JOptionPane.ERROR_MESSAGE); 
               usuario.requestFocus();
            }
        }
    }
}
