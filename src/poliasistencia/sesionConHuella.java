/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poliasistencia;

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
import controlador.sesion;
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
import poliasistencia.login;

public class sesionConHuella extends javax.swing.JFrame {

    private int[] personaInt = new int[2];

    public sesionConHuella() {

        setTitle("Iniciar Sesion");
        setBounds(30, 30, 1300, 650);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        setIconImage(icono);
        setResizable(false);
        setLayout(null);
        initComponents();
    }

    private void initComponents() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                login loge = new login();
                loge.crearComponentes(true);
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

        subtitulo = new JLabel("Coloca el dedo en el sensor para iniciar sesion");
        subtitulo.setIcon(new ImageIcon(getClass().getResource("/img/huella.png")));
        subtitulo.setFont(subtitulos);
        subtitulo.setForeground(azulAcento);
        subtitulo.setBounds(170, 350, 1000, 100);
        defauult.add(subtitulo);

        defauult.setVisible(true);
        add(defauult);

   
        

        

       

        //Contenido

        /*
         b2.setVerticalTextPosition(AbstractButton.BOTTOM);
         b2.setHorizontalTextPosition(AbstractButton.CENTER);
         */
        setVisible(true);
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
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        //Opcion 3: Sacar esto aqui con try y sin try :v

        //Opcion 3.1: :v
        //identificarHuella();
        if (featuresinscripcion != null) {
            try {
                System.out.println("Ya se puede usar la huella");
                Reclutador.addFeatures(featuresinscripcion);
                identificarHuella();
                Reclutador.clear();
            } catch (DPFPImageQualityException ex) {
                System.err.println("Error: " + ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(sesionConHuella.class.getName()).log(Level.SEVERE, null, ex);
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
            int idPersona = 0, idTipoPer = 0;
            while (rs.next()) {
                byte templateBuffer[] = rs.getBytes("huella");
                String nombre = rs.getString("nombre");
                idPersona = rs.getInt("idPersona");
                idTipoPer = rs.getInt("idTipo");
//Vaia vaia, esto suena interesante... 
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                setTemplate(referenceTemplate);
                //con eso identifica la huella
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());

                if (result.isVerified()) {
                    login log = new login();
                    //spAsistencia(in idP int)
                    personaInt[0] = idPersona;
                    personaInt[1] = idTipoPer;
                    if (idPersona > 0) {
                        sesion ses;
                        switch (idTipoPer) {
                            case 1:
                                ses = new sesion(idPersona, 1);
                                vistaUsuarios.inicio nuevo = new vistaUsuarios.inicio();
                                this.dispose();
                                nuevo.crearComponentes(true);
                                stop();
                                break;
                            case 4:
                                ses = new sesion(idPersona, 4);
                                vistaGrupos.inicio nuevo2 = new vistaGrupos.inicio();
                                this.dispose();
                                nuevo2.crearComponentes(true);
                                stop();
                                break;
                            default:
                                JOptionPane.showMessageDialog(this, "No esta autorizado", "Error", JOptionPane.ERROR_MESSAGE);
                                stop();
                                this.dispose();
                                log.crearComponentes(true);
                        }
                    } else {
                        this.dispose();
                        stop();
                        JOptionPane.showMessageDialog(this, "No se encontro la huella", "Error", JOptionPane.ERROR_MESSAGE);
                        log.crearComponentes(true);
                    }

                    while (rs.next()) {
                        System.out.println(rs.getString("msj"));
                    }
                    stop();
                    this.dispose();
                    return;
                }

            }
            JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            setTemplate(null);

        } catch (SQLException e) {
            System.err.println("Error al identificar huella dactilar." + e.getMessage());
        } finally {

        }
    }

    public int[] getIDS() {
        return personaInt;
    }
    //Quitale el hilo si no lo estas probando solo >:v
    Date date = new Date();
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
    

}
