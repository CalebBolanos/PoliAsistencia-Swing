/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrada;

/**
 *
 * @author Ayax
 */
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import controlador.baseDeDatos;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.Timer;

public class pantallaHuella extends javax.swing.JFrame {

    public pantallaHuella() {

        setTitle("Entrada/Salida");
        setBounds(30, 30, 1300, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        setIconImage(icono);
        setResizable(false);
        setLayout(null);
        initComponents();
    }

    private void initComponents() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }

            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        blanco = new Color(255, 255, 255);
        azulAcento = new Color(0, 145, 234);
        azul = new Color(0, 176, 255);
        rojo = new Color(254, 0, 0);
        verde = new Color(87, 166, 57);
        subtitulos = new Font("Calibri", 0, 40);
        titulopb = new Font("Calibri", 1, 103);
        titulop = new Font("Calibri", 0, 90);
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

        subtitulo = new JLabel("Coloca el dedo en el sensor para entrar o salir del plantel");
        subtitulo.setIcon(new ImageIcon(getClass().getResource("/img/huella.png")));
        subtitulo.setFont(subtitulos);
        subtitulo.setForeground(azulAcento);
        subtitulo.setBounds(170, 350, 1000, 100);
        defauult.add(subtitulo);

        defauult.setVisible(true);
        add(defauult);

        //Entrada bien
        permitirEntrada = new JPanel();
        permitirEntrada.setLayout(null);
        permitirEntrada.setBackground(blanco);
        permitirEntrada.setBounds(0, 0, 1300, 650);

        titulo2 = new JLabel("Poli");
        titulo2.setBounds(400, 150, 119, 100);
        titulo2.setFont(tit);
        titulo2.setForeground(azul);
        permitirEntrada.add(titulo2);

        tituloBold2 = new JLabel("Asistencia");
        tituloBold2.setBounds(519, 147, 400, 100);
        tituloBold2.setFont(titb);
        tituloBold2.setForeground(azulAcento);
        permitirEntrada.add(tituloBold2);

        subtituloEntradaOk = new JLabel("Permiso Concedido");
        subtituloEntradaOk.setIcon(new ImageIcon(getClass().getResource("/img/aceptar.png")));
        subtituloEntradaOk.setFont(subtitulos);
        subtituloEntradaOk.setForeground(verde);
        subtituloEntradaOk.setBounds(250, 320, 1000, 100);
        permitirEntrada.add(subtituloEntradaOk);

        permitirEntrada.setVisible(false);
        add(permitirEntrada);

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
        add(permitirSalida);

        //entrada mal
        denegarEntrada = new JPanel();
        denegarEntrada.setLayout(null);
        denegarEntrada.setBackground(blanco);
        denegarEntrada.setBounds(0, 0, 1300, 650);

        titulo2 = new JLabel("Poli");
        titulo2.setBounds(400, 150, 119, 100);
        titulo2.setFont(tit);
        titulo2.setForeground(azul);
        denegarEntrada.add(titulo2);

        tituloBold2 = new JLabel("Asistencia");
        tituloBold2.setBounds(519, 147, 400, 100);
        tituloBold2.setFont(titb);
        tituloBold2.setForeground(azulAcento);
        denegarEntrada.add(tituloBold2);

        subtituloEntrada = new JLabel("Permiso denegado");
        subtituloEntrada.setIcon(new ImageIcon(getClass().getResource("/img/denegar.png")));
        subtituloEntrada.setFont(subtitulos);
        subtituloEntrada.setForeground(rojo);
        subtituloEntrada.setBounds(250, 320, 1000, 100);
        denegarEntrada.add(subtituloEntrada);

        denegarEntrada.setVisible(false);
        add(denegarEntrada);

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
        add(denegarSalida);

        //Contenido

        /*
         b2.setVerticalTextPosition(AbstractButton.BOTTOM);
         b2.setHorizontalTextPosition(AbstractButton.CENTER);
         */
        setVisible(true);
    }

    private void entradaBien(String nombre, String fecha) {
        subtituloEntradaOk.setText(nombre+" "+fecha);
        defauult.setVisible(false);
        denegarEntrada.setVisible(false);
        denegarSalida.setVisible(false);
        permitirSalida.setVisible(false);
        permitirEntrada.setVisible(true);        
        timer.start();
    }

    private void entradaMal() {
        defauult.setVisible(false);
        denegarEntrada.setVisible(true);
        denegarSalida.setVisible(false);
        permitirSalida.setVisible(false);
        permitirEntrada.setVisible(false);
        timer.start();
    }

    private void volver() {
        defauult.setVisible(true);
        denegarEntrada.setVisible(false);
        denegarSalida.setVisible(false);
        permitirSalida.setVisible(false);
        permitirEntrada.setVisible(false);
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        Iniciar();
        start();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        stop();
    }

    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";

    protected void Iniciar() {
        Lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ProcesarCaptura(e.getSample());
                    }
                });
            }
        });

        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        System.out.println("Lector conectado");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        System.out.println("Lector desconectado");
                    }
                });
            }
        });

        Lector.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        System.out.println("Escaneando");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        System.out.println("Ha levantado el dedo del lector");
                        //Opcion 1: pasar esto con try y catch

                        //Opcion 2: Lo mismo pero sin try y catch:V
                        //identificarHuella();
                    }
                });
            }
        });

        Lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        System.out.println(e.toString());
                    }
                });
            }
        });
    }

    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;

    public void ProcesarCaptura(DPFPSample sample) {
        volver();
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        //Opcion 3: Sacar esto aqui con try y sin try :v

        //Opcion 3.1: :v
        //identificarHuella();
        if (featuresinscripcion != null) {
            try {
                System.out.println("Ya se puede usar la huella");
                Reclutador.addFeatures(featuresinscripcion);
                entradaBien("Ayax", "fecha mamalona");
                identificarHuella();
                Reclutador.clear();
            } catch (DPFPImageQualityException ex) {
                System.err.println("Error: " + ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(pantallaHuella.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    public Image CrearImagenHuella(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

    public void start() {
        Lector.startCapture();
    }

    public void stop() {
        Lector.stopCapture();
    }

    public void identificarHuella() throws IOException {
        try {
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            Connection c = bd.getConexion();
            //Igual aqui iria lo de nuestra base
            PreparedStatement identificarStmt = c.prepareStatement("select * from vwHuellasPersonas");
            //PreparedStatement identificarStmt = c.prepareStatement("SELECT nombre,huella FROM personas");
            ResultSet rs = identificarStmt.executeQuery();
            int idPersona = 0;
            while (rs.next()) {
                byte templateBuffer[] = rs.getBytes("huella");
                String nombre = rs.getString("nombre");
                idPersona = rs.getInt("idPersona");
//Vaia vaia, esto suena interesante... 
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                setTemplate(referenceTemplate);
                //con eso identifica la huella
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());

                if (result.isVerified()) {
                    //spAsistencia(in idP int)
                    rs = bd.ejecuta("call spAsistencia(" + idPersona + ");");
                    while (rs.next()) {
                        System.out.println(rs.getString("msj"));
                    }
                    DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                    entradaBien(nombre, hourdateFormat.format(date));
                    JOptionPane.showMessageDialog(null, "La huella es de: " + nombre, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    //Aqui se pondria la pantalla donde despliega la informacion del alumno para saber si pasa o nel

                    return;
                }

            }
            JOptionPane.showMessageDialog(null, "Persona no registrada", "Mensaje", JOptionPane.ERROR_MESSAGE);
            setTemplate(null);
            entradaMal();

        } catch (SQLException e) {
            System.err.println("Error al identificar huella dactilar." + e.getMessage());
        }
    }
    //Quitale el hilo si no lo estas probando solo >:v
    Date date=new Date();
    Font titulop, tit;
    Font titulopb, titb;
    Font subtitulos;
    Color azul;
    Color azulAcento;
    Color blanco;
    Color verde;
    Color rojo;
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
    Timer timer = new Timer(4000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });

}
