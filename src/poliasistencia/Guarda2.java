package poliasistencia;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import controlador.baseDeDatos;
import controlador.registros;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;

public class Guarda2 extends javax.swing.JDialog {

    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblImagenHuella;
    private javax.swing.JPanel panBtns;
    private javax.swing.JPanel panHuellas;
    private static int GEN, retID;
    private static String NOM, PAT, MAT, FECH, BOL, MSJ;

    public Guarda2() {
        initComponents();
    }

    //Este constructor recibe todo para guardar y lo mandas en el metodo guardar huella que esta mas abajo
    public Guarda2(int gen, String nom, String pat, String mat, String fech, String bol) {
        
        GEN = gen;
        NOM = nom;
        PAT = pat;
        FECH = fech;
        BOL = bol;
        MAT=mat;
        initComponents();
    }

    //Crearias las variables de instancia                     
    private void initComponents() {

        panHuellas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblImagenHuella = new javax.swing.JLabel();
        panBtns = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Guarda Registro");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panHuellas.setBackground(new java.awt.Color(255, 255, 255));
        panHuellas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panHuellas.setPreferredSize(new java.awt.Dimension(400, 270));
        panHuellas.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setLayout(new java.awt.BorderLayout());

        lblImagenHuella.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lblImagenHuella, java.awt.BorderLayout.CENTER);

        panHuellas.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panHuellas, java.awt.BorderLayout.CENTER);

        panBtns.setBackground(new java.awt.Color(255, 255, 255));
        panBtns.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panBtns.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        panBtns.setPreferredSize(new java.awt.Dimension(400, 190));
        panBtns.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(366, 90));
        jPanel2.setForeground(new Color(255, 255, 255));

        btnSalir.setBackground(new java.awt.Color(255, 255, 255));
        btnSalir.setFont(new java.awt.Font("Arial Black", 0, 14));
        btnSalir.setForeground(new java.awt.Color(0, 145, 234));
        btnSalir.setText("Salir");
        btnSalir.setBorder(BorderFactory.createLineBorder(new Color(0, 145, 234), 2));
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Arial Black", 0, 14));
        btnGuardar.setForeground(new java.awt.Color(0, 145, 234));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(BorderFactory.createLineBorder(new Color(0, 145, 234), 2));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 23, Short.MAX_VALUE))
        );

        panBtns.add(jPanel2, java.awt.BorderLayout.NORTH);

        getContentPane().add(panBtns, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(465, 534));
        setLocationRelativeTo(null);
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        stop();
        this.dispose();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        guardarHuella();
        Reclutador.clear();
        lblImagenHuella.setIcon(null);
        start();
        stop();
        this.dispose();

    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        Iniciar();
        btnGuardar.setEnabled(false);
        start();
        btnSalir.grabFocus();
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
    }

    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;

    public void ProcesarCaptura(DPFPSample sample) {
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        if (featuresinscripcion != null) {
            try {
                System.out.println("Las Caracteristicas de la Huella han sido creada");
                Reclutador.addFeatures(featuresinscripcion);
                Image image = CrearImagenHuella(sample);
                DibujarHuella(image);

            } catch (DPFPImageQualityException ex) {
                System.err.println("Error: " + ex.getMessage());
            } finally {
                switch (Reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:
                        stop();
                        setTemplate(Reclutador.getTemplate());
                        System.out.println("La Plantilla de la Huella ha Sido Creada, ya puede Verificarla o Identificarla");
                        //btnIdentificar.setEnabled(false);
                        //btnVerificar.setEnabled(false);
                        btnGuardar.setEnabled(true);
                        btnGuardar.grabFocus();
                        break;

                    case TEMPLATE_STATUS_FAILED:
                        Reclutador.clear();
                        stop();
                        setTemplate(null);
                        JOptionPane.showMessageDialog(Guarda2.this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
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
    Integer tamanoHuella=template.serialize().length;
    int id=0;
        try{
                baseDeDatos bd = new baseDeDatos();
                bd.conectar();
                ResultSet rs = bd.ejecuta("call spGuardaAlumnos(" + GEN + ", '" + PAT +"', '" + MAT
                        + "', '" + NOM + "', '" + FECH + "', 'sinasignar', '" + BOL + "', 1);");
                while(rs.next()){
                    id = rs.getInt("idP");
                    MSJ = rs.getString("msj");
                }
                System.out.println("IDP: " + id);
                retID = id;
                if(id>0){
                    Connection c=bd.getConexion(); 
                    //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                    try (PreparedStatement guardarStmt = c.prepareStatement("call fGuardaHuella(?,?)")) {
                        //create function fGuardaHuella(idP int, hu longblob) returns nvarchar(100)
                        guardarStmt.setInt(1,id);
                        //Este si lo debes usar porque asi se guarda la huella, en la base esta como tipo BLOB
                        guardarStmt.setBinaryStream(2, datosHuella,tamanoHuella);
                        guardarStmt.execute();
                        guardarStmt.close();
                        char ho[]=new char[tamanoHuella];
        byte bb[]=new byte[tamanoHuella];
        datosHuella.read(bb, 0, tamanoHuella);
     //Este es de prueba
     for(int i=0;i<tamanoHuella;i++){
        ho[i]=(char)bb[i];
     }
     String houy = new String(ho);
        System.out.println("RESGISTROS 2: "+houy);
                    }
                }
                System.out.println("Huella Guarda");
                bd.cierraConexion();
            }catch(Exception e){
                System.out.println(e.getMessage());
                MSJ=e.getMessage();
            }
    }
    public int getID(){
        return retID;
    }
    public String getMsj(){
        return MSJ;
    }
//este pues borralo, era para correr la ventana alv :v

}
