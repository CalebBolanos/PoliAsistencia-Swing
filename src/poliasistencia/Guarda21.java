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
import java.io.ByteArrayInputStream;
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

public class Guarda21 extends javax.swing.JFrame {

    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblImagenHuella;
    private javax.swing.JPanel panBtns;
    private javax.swing.JPanel panHuellas;
    private static int GEN, retID, TIPO, idPer;
    private static String NOM, PAT, MAT, FECH, BOL, MSJ;

    public Guarda21() {
        setTitle("Entrada/Salida");
        setBounds(30, 30, 1300, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        setIconImage(icono);
        setResizable(false);
        setLayout(null);
        initComponents();
        sesion ses = new sesion();
        idPer = ses.getID();
    }

    public Guarda21(int tip, int gen, String nom, String pat, String mat, String fech, String bol) {

        setTitle("Guarda Docente");
        setBounds(30, 30, 1300, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icono = new ImageIcon(getClass().getResource("/img/poliAsistencia.png")).getImage();
        setIconImage(icono);
        setResizable(false);
        setLayout(null);
        initComponents();
        GEN = gen;
        NOM = nom;
        PAT = pat;
        FECH = fech;
        BOL = bol;
        MAT = mat;
        TIPO = tip;
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

        subtitulo = new JLabel("Coloca el dedo en el sensor para registrar tu huella");
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

        subtituloEntradaOk = new JLabel("Plantilla añadida");
        subtituloEntradaOk.setIcon(new ImageIcon(getClass().getResource("/img/huella.png")));
        subtituloEntradaOk.setFont(tit);
        subtituloEntradaOk.setForeground(verde);
        subtituloEntradaOk.setBounds(300, 320, 1000, 100);
        permitirEntrada.add(subtituloEntradaOk);

        permitirEntrada.setVisible(false);
        add(permitirEntrada);

        //Contenido

        /*
         b2.setVerticalTextPosition(AbstractButton.BOTTOM);
         b2.setHorizontalTextPosition(AbstractButton.CENTER);
         */
        setVisible(true);
    }

    private void GHuella() {
        System.out.println("Procede1");
        guardarHuella();
        System.out.println("procede2");
        Reclutador.clear();
        start();
        stop();
        JOptionPane.showMessageDialog(Guarda21.this, "Usuario guardado correctamente", "Inscripcion de Huellas Dactilares", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();

    }

    private void entradaBien() {
        defauult.setVisible(false);
        permitirEntrada.setVisible(true);
        timer.start();
    }

    private void volver() {
        defauult.setVisible(true);
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

        if (featuresinscripcion != null) {
            try {
                System.out.println("Las Caracteristicas de la Huella han sido creada");
                Reclutador.addFeatures(featuresinscripcion);
                entradaBien();

            } catch (DPFPImageQualityException ex) {
                System.err.println("Error: " + ex.getMessage());
            } finally {
                switch (Reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:
                        stop();
                        setTemplate(Reclutador.getTemplate());
                        System.out.println("La Plantilla de la Huella ha Sido Creada, ya puede Verificarla o Identificarla");
                        
                        GHuella();
                        break;

                    case TEMPLATE_STATUS_FAILED:
                        Reclutador.clear();
                        stop();
                        setTemplate(null);
                        JOptionPane.showMessageDialog(Guarda21.this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
                        start();
                        break;
                }
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

    public void guardarHuella() {
//        System.out.println("Guarda 2");
//     ByteArrayInputStream datosHuella = new ByteArrayInputStream(template.serialize());
//     Integer tamanoHuella=template.serialize().length;
//        System.out.println(datosHuella);
//        char ho[]=new char[tamanoHuella];
//        byte bb[]=new byte[tamanoHuella];
//        datosHuella.read(bb, 0, tamanoHuella);
//     //Este es de prueba
//     for(int i=0;i<tamanoHuella;i++){
//        ho[i]=(char)bb[i];
//     }
//     String houy = new String(ho);
//        System.out.println("PANTALLA: "+houy);
//     registros reg = new registros();
//     reg.registroHuella(datosHuella, tamanoHuella);
//     stop();
        ByteArrayInputStream datosHuella = new ByteArrayInputStream(template.serialize());
        Integer tamanoHuella = template.serialize().length;
        boolean pasar = BOL !=null;
        int id = 0;
        try {
            baseDeDatos bd = new baseDeDatos();
            bd.conectar();
            //spGuardaDocente(in idT int,in g int, in pat nvarchar(250),in mat nvarchar(250), in nom nvarchar(250),
            //in fech date, in mail nvarchar(250),in numT nvarchar(15),in hu longblob)
            if(pasar){
                ResultSet rs = bd.ejecuta("call spGuardaDocente(" + TIPO + ", " + GEN + ", '" + PAT + "', '" + MAT
                    + "', '" + NOM + "', '" + FECH + "', 'sinasignar@gmail.com', '" + BOL + "');");
                while (rs.next()) {
                    id = rs.getInt("idP");
                    MSJ = rs.getString("msj");
                }
                System.out.println("IDP: " + id);
                retID = id;
            }
            else {
                id=idPer;
                retID = id;
            }
            
            
            if (id > 0) {
                Connection c = bd.getConexion();
                //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                try (PreparedStatement guardarStmt = c.prepareStatement("call fGuardaHuella(?,?)")) {
                    //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                    guardarStmt.setInt(1, id);
                    //Este si lo debes usar porque asi se guarda la huella, en la base esta como tipo BLOB
                    guardarStmt.setBinaryStream(2, datosHuella, tamanoHuella);
                    guardarStmt.execute();
                    guardarStmt.close();
                }
            }
            System.out.println("Huella Guarda");
            bd.cierraConexion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            MSJ = e.getMessage();
        }
    }
    
    public void guardaHuella2(){
        ByteArrayInputStream datosHuella = new ByteArrayInputStream(template.serialize());
        Integer tamanoHuella = template.serialize().length;
        baseDeDatos bd = new baseDeDatos();
        try{
            bd.conectar();
            Connection c = bd.getConexion();
            //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
            try (PreparedStatement guardarStmt = c.prepareStatement("call fGuardaHuella(?,?)")) {
                //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                guardarStmt.setInt(1, idPer);
                //Este si lo debes usar porque asi se guarda la huella, en la base esta como tipo BLOB
                guardarStmt.setBinaryStream(2, datosHuella, tamanoHuella);
                guardarStmt.execute();
                guardarStmt.close();
            }
            bd.cierraConexion();
        }catch(Exception e){
                System.out.println(e.getMessage());
            }
    }

    public int getID() {
        return retID;
    }

    public String getMsj() {
        return MSJ;
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
    Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            volver();
        }
    });

}
