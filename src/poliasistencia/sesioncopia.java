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
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sesioncopia extends javax.swing.JFrame {
    private int[] personaInt = new int[2];
    public sesioncopia() {
        initComponents();
    }
    
    private void initComponents() {

        panHuellas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblImagenHuella = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INICIAR SESION");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }

            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panHuellas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panHuellas.setPreferredSize(new java.awt.Dimension(400, 270));
        panHuellas.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(lblImagenHuella, java.awt.BorderLayout.CENTER);

        panHuellas.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panHuellas, java.awt.BorderLayout.NORTH);

        setSize(new java.awt.Dimension(469, 346));
        setLocationRelativeTo(null);
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
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        //Opcion 3: Sacar esto aqui con try y sin try :v

        //Opcion 3.1: :v
        //identificarHuella();
        if (featuresinscripcion != null) {
            try {
                System.out.println("Las Caracteristicas de la Huella han sido creada");
                Reclutador.addFeatures(featuresinscripcion);
                Image image = CrearImagenHuella(sample);
                DibujarHuella(image);
                identificarHuella();
                Reclutador.clear();
            } catch (DPFPImageQualityException ex) {
                System.err.println("Error: " + ex.getMessage());
            } catch (IOException ex) {
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

    public void DibujarHuella(Image image) {
        lblImagenHuella.setIcon(new ImageIcon(
                image.getScaledInstance(lblImagenHuella.getWidth(), lblImagenHuella.getHeight(), Image.SCALE_DEFAULT)));
        repaint();
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
                    if(idPersona>0){
                        switch(idTipoPer){
                            case 1:
                                
                                vistaUsuarios.inicio nuevo = new vistaUsuarios.inicio();
                                this.dispose();
                                nuevo.crearComponentes(true);
                                stop();
                                break;
                            case 4:
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
                    }else{
                        this.dispose();
                        stop();
                        JOptionPane.showMessageDialog(this, "No se encontro la huella", "Error", JOptionPane.ERROR_MESSAGE);
                        log.crearComponentes(true);
                    }
        
                    while(rs.next()){
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
    public int[] getIDS(){
        return personaInt;
    }
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblImagenHuella;
    private javax.swing.JPanel panHuellas;

}
