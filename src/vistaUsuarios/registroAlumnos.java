/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaUsuarios;

import controlador.registros;
import controlador.validaciones;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import poliasistencia.Guarda2;
import poliasistencia.HuellaGuarda;

/**
 *
 * @author PoliAsistencia
 */
public class registroAlumnos implements ActionListener {

    JFrame ventana;
    Font titulop;
    Font titulopb;
    Font subtitulos;
    Color azul;
    Color azulAcento;
    Color blanco;
    JLabel titulo, tituloBold, dnombre, dcontrasena, dcontrasena2, confirm = new JLabel("Datos correctos, confirmar registro");
    JPanel arriba, abajo;
    JTextField nombre, paterno, materno, boleta;
    JPasswordField contrasena, contrasenax2; 
    JSeparator lineaNombre, lineaCont, lineaCont2;
    JButton huella, cerrar, guardar;
    JCheckBox eleccion = new JCheckBox("No mostrar este dialogo mientras se esté registrando");
    int box = 0;
    Object[] agregar;
    JComboBox genero;
    DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
    JTextField nacimiento;
    

    public registroAlumnos() {
        ventana = new JFrame("Registro de Alumnos - PoliAsistencia");
        ventana.setBounds(30, 30, 1300, 650);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        ventana.setIconImage(icono);
        ventana.setResizable(false);
        ventana.setLayout(null);
    }

    public void crearComponentes(boolean permiso) {

        eleccion.addActionListener(this);
        agregar = new Object[]{confirm, eleccion};
        
        blanco = new Color(255, 255, 255);
        azulAcento = new Color(0, 145, 234);
        azul = new Color(0, 176, 255);
        subtitulos = new Font("Arial", 0, 25);
        titulopb = new Font("Calibri", 1, 93);
        titulop = new Font("Calibri", 0, 60);

        //Titulos
        titulo = new JLabel("Registro de Alumnos");
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
        nombre.setCaretColor(azul);
        ventana.add(nombre);
        //50
        lineaNombre = new JSeparator(SwingConstants.HORIZONTAL);
        lineaNombre.setBounds(70, 230, 530, 10);
        lineaNombre.setBackground(azulAcento);
        lineaNombre.setForeground(azulAcento);
        ventana.add(lineaNombre);

        dnombre = new JLabel("Materno");
        dnombre.setFont(new java.awt.Font("Arial", 0, 17));
        dnombre.setBounds(70, 235, 300, 100);
        dnombre.setForeground(azulAcento);
        ventana.add(dnombre);
        //70
        materno = new JTextField();
        materno.setBounds(70, 305, 530, 50);
        materno.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        materno.setBackground(blanco);
        materno.setForeground(Color.BLACK);
        materno.setFont(subtitulos);
        materno.setCaretColor(azul);
        ventana.add(materno);
        //50
        lineaNombre = new JSeparator(SwingConstants.HORIZONTAL);
        lineaNombre.setBounds(70, 355, 530, 10);
        lineaNombre.setBackground(azulAcento);
        lineaNombre.setForeground(azulAcento);
        ventana.add(lineaNombre);

        dnombre = new JLabel("Genero");
        dnombre.setFont(new java.awt.Font("Arial", 0, 17));
        dnombre.setBounds(70, 360, 300, 100);
        dnombre.setForeground(azulAcento);
        ventana.add(dnombre);
        //70
        genero = new JComboBox();
        genero.addItem("Seleccione una opción");
        genero.addItem("Masculino");
        genero.addItem("Femenino");
        genero.addItem("Indefinido");
        genero.setBounds(70, 430, 530, 50);
        genero.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        genero.setBackground(blanco);
        genero.setForeground(Color.BLACK);
        genero.setFont(subtitulos);
        genero.addActionListener(this);
        ventana.add(genero);
        //50
        lineaNombre = new JSeparator(SwingConstants.HORIZONTAL);
        lineaNombre.setBounds(70, 480, 530, 10);
        lineaNombre.setBackground(azulAcento);
        lineaNombre.setForeground(azulAcento);
        ventana.add(lineaNombre);

        dnombre = new JLabel("Apellido Paterno");
        dnombre.setFont(new java.awt.Font("Arial", 0, 17));
        dnombre.setBounds(690, 110, 300, 100);
        dnombre.setForeground(azulAcento);
        ventana.add(dnombre);
        //70
        paterno = new JTextField();
        paterno.setBounds(690, 180, 530, 50);
        paterno.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        paterno.setBackground(blanco);
        paterno.setForeground(Color.BLACK);
        paterno.setFont(subtitulos);
        paterno.setCaretColor(azul);
        ventana.add(paterno);
        //50
        lineaNombre = new JSeparator(SwingConstants.HORIZONTAL);
        lineaNombre.setBounds(690, 230, 530, 10);
        lineaNombre.setBackground(azulAcento);
        lineaNombre.setForeground(azulAcento);
        ventana.add(lineaNombre);

        dcontrasena = new JLabel("Boleta/PM");
        dcontrasena.setFont(new java.awt.Font("Arial", 0, 17));
        dcontrasena.setBounds(690, 235, 500, 100);
        dcontrasena.setForeground(azulAcento);
        ventana.add(dcontrasena);

        //70
        boleta = new JTextField();
        boleta.setBounds(690, 305, 530, 50);
        boleta.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        boleta.setBackground(blanco);
        boleta.setForeground(Color.BLACK);
        boleta.setFont(subtitulos);
        boleta.setCaretColor(azul);
        ventana.add(boleta);
        //50
        lineaCont = new JSeparator(SwingConstants.HORIZONTAL);
        lineaCont.setBounds(690, 355, 530, 10);
        lineaCont.setBackground(azulAcento);
        lineaCont.setForeground(azulAcento);
        ventana.add(lineaCont);

        dcontrasena2 = new JLabel("Fecha de nacimiento: aaaa-mm-dd");
        dcontrasena2.setFont(new java.awt.Font("Arial", 0, 17));
        dcontrasena2.setBounds(690, 360, 500, 100);
        dcontrasena2.setForeground(azulAcento);
        ventana.add(dcontrasena2);
        //70
        nacimiento = new JTextField();
        nacimiento.setBounds(690, 430, 530, 50);
        nacimiento.setBorder(BorderFactory.createLineBorder(azulAcento, 0));
        nacimiento.setBackground(blanco);
        nacimiento.setForeground(Color.BLACK);
        nacimiento.setFont(subtitulos);
        nacimiento.setCaretColor(azul);
        ventana.add(nacimiento);
        //50
        lineaCont2 = new JSeparator(SwingConstants.HORIZONTAL);
        lineaCont2.setBounds(690, 480, 530, 10);
        lineaCont2.setBackground(azulAcento);
        lineaCont2.setForeground(azulAcento);
        ventana.add(lineaCont2);

        guardar = new JButton("Registrar Alumno");
        guardar.setBounds(600, 520, 200, 50);
        guardar.setBackground(blanco);
        guardar.setBorder(BorderFactory.createLineBorder(azulAcento, 2));
        guardar.setFont(new java.awt.Font("Arial", 0, 18));
        guardar.setForeground(azulAcento);
        guardar.addActionListener(this);
        ventana.add(guardar);
        
        huella = new JButton("Huella Digital");
        huella.setBounds(400, 520, 200, 50);
        huella.setBackground(blanco);
        huella.setBorder(BorderFactory.createLineBorder(azulAcento, 2));
        huella.setFont(new java.awt.Font("Arial", 0, 18));
        huella.setForeground(azulAcento);
        huella.addActionListener(this);
        //ventana.add(huella);
              

        abajo = new JPanel();
        abajo.setBounds(0, 100, 1300, 570);
        abajo.setBackground(blanco);
        ventana.add(abajo);

        ventana.setVisible(permiso);

    }

    @Override
    public void actionPerformed(ActionEvent a) {
        validaciones val = new validaciones();
        if (a.getSource() == cerrar) {
            ventana.dispose();
            inicio abrir = new inicio();
            abrir.crearComponentes(true);
        }
        if(a.getSource() == huella){
            HuellaGuarda obj = new HuellaGuarda();
            obj.setVisible(true);
        }
        
        if (a.getSource() == guardar) {
            String nom, pat, mat, bol, fecha;
            int gen;
            nom = nombre.getText();
            pat = paterno.getText();
            mat = materno.getText();
            bol = boleta.getText();
            gen = genero.getSelectedIndex();
            fecha = nacimiento.getText();
            if(val.soloLet(nom, "el nombre", 250))
                if(val.soloLet(pat, "el apellido paterno", 250))
                    if(val.soloLet(mat, "el apellido materno", 250))
                        if(val.boleta(bol))
                            if(val.validarFecha(fecha))
                                if(gen != 0){
                                    registros reg = new registros();
                                    if(true){
                                        int idReg;

                                        /*
                                        * -1 = cerrado
                                        * 0 = si
                                        * 1 = no
                                         */
                                        if(box == 0){
                                            int evaluar = JOptionPane.showConfirmDialog(ventana, agregar, "Confirmar", JOptionPane.YES_NO_OPTION);
                                            if(eleccion.isSelected()){
                                                box = 1;
                                            }
                                            if(evaluar == 0){
                                                Guarda2 g2 = new Guarda2(gen, nom, pat, mat, fecha, bol);
                                               g2.setVisible(true);
                                                //idReg = reg.regAlumno(gen, nom, pat, mat, fecha, bol);
                                                if(g2.getID()>0){
                                                    boleta.setText("");
                                                    genero.setSelectedIndex(0);
                                                    materno.setText("");
                                                    nacimiento.setText("");
                                                    nombre.setText("");
                                                    paterno.setText("");
                                                    JOptionPane.showMessageDialog(ventana, "Alumno registrado");
                                                }
                                                
                                                boleta.setText("");
                                                genero.setSelectedIndex(0);
                                                materno.setText("");
                                                nacimiento.setText("");
                                                nombre.setText("");
                                                paterno.setText("");
                                            }
                                            else{
                                                eleccion.setSelected(false);
                                                box = 0; 
                                            }
                                        }
                                        else{
                                            Guarda2 g2 = new Guarda2(gen, nom, pat, mat, fecha, bol);
                                            g2.setVisible(true);
//                                            idReg = reg.regAlumno(gen, nom, pat, mat, fecha, bol);
                                            if(g2.getID()>0){
                                                boleta.setText("");
                                                genero.setSelectedIndex(0);
                                                materno.setText("");
                                                nacimiento.setText("");
                                                nombre.setText("");
                                                paterno.setText("");
                                                JOptionPane.showMessageDialog(ventana, "Alumno registrado");
                                            }
                                            boleta.setText("");
                                            genero.setSelectedIndex(0);
                                            materno.setText("");
                                            nacimiento.setText("");
                                            nombre.setText("");
                                            paterno.setText("");
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(ventana, "Registre la huella", "Error", JOptionPane.ERROR_MESSAGE);
                                        huella.requestFocus();
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(ventana, "Seleccione un genero", "Error", JOptionPane.ERROR_MESSAGE);
                                    genero.requestFocus();
                                }
                            else{
                                JOptionPane.showMessageDialog(ventana, "Introduzca una fecha valida, ejemplo: \"2000-01-01\"", "Error", JOptionPane.ERROR_MESSAGE);
                                nacimiento.requestFocus();
                            }
                        else{
                            JOptionPane.showMessageDialog(ventana, val.err(), "Error", JOptionPane.ERROR_MESSAGE);
                            boleta.requestFocus();
                        }
                    else{
                        JOptionPane.showMessageDialog(ventana, val.err(), "Error", JOptionPane.ERROR_MESSAGE);
                        materno.requestFocus();
                    }
                else{
                    JOptionPane.showMessageDialog(ventana, val.err(), "Error", JOptionPane.ERROR_MESSAGE);
                    paterno.requestFocus();
                }
            else{
                JOptionPane.showMessageDialog(ventana, val.err(), "Error", JOptionPane.ERROR_MESSAGE);
                nombre.requestFocus();
            }
            
        }
        
    }
}
