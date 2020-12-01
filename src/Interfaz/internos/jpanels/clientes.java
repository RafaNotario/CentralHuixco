/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.internos.jpanels;


import Controller.controlInserts;
import Controller.datesControl;
import Controller.funciones;
import Interfaz.altaAmbulantes;
import conexion.ConexionDBOriginal;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import renderTable.TModel;

/**
 *
 * @author monit
 */
public class clientes extends javax.swing.JPanel {

        ConexionDBOriginal con2 = new ConexionDBOriginal();
        controlInserts contrl = new controlInserts();
        datesControl datCtrl = new datesControl();
        funciones func = new funciones();
        String[] cabAreasPays = {"#ID", "Nombre", "Direccion", "Telefono", "Observaciones","Status"},
                    cabGiros = {"Id","Descripcion"};
        private final String ruta = System.getProperties().getProperty("user.dir");
        /**
     * Creates new form ambulantes
     */
        
        String varFecReact ="";
    public clientes() {
        initComponents();
        cargaDatasClientes();
         jButton9.setVisible(false);
         
        jTabViewAllAmbs.getParent().addComponentListener(new ComponentAdapter() {
    @Override
    public void componentResized(final ComponentEvent e) {
        if (jTabViewAllAmbs.getPreferredSize().width < jTabViewAllAmbs.getParent().getWidth()) {
           // System.out.println("Resize"+jTabViewAllAmbs.getParent().getWidth());
            jTabViewAllAmbs.setAutoResizeMode(jTabViewAllAmbs.AUTO_RESIZE_ALL_COLUMNS);
        } else {
            jTabViewAllAmbs.setAutoResizeMode(jTabViewAllAmbs.AUTO_RESIZE_OFF);
        }
    }
});
    }    
    
    protected void cargaDatasClientes(){
            String[][] mat = matrizgetClients(0,"");
             jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays));        
    }
   
    //regresa matrizde vista tickets del dia
        public String[][] matrizgetClients(int opc,String param){
        Connection cn = con2.conexion();
          String sql ="",aux;
          switch (opc){
              case 0 : //todos 
       sql = "SELECT clientes.id,clientes.nombre,clientes.direccion,clientes.telefono,clientes.obs,IF(activo=1,'ACTIVO','BAJA') as sat "
             + "FROM central.clientes;";                        
          break;
              case 1:// activos
              sql = "SELECT clientes.id,clientes.nombre,clientes.direccion,clientes.telefono,clientes.obs,IF(activo=1,'ACTIVO','BAJA') as sat "
             + "FROM clientes WHERE clientes.activo = 1;";
                  break;
              case 2 :
                  sql = "SELECT clientes.id,clientes.nombre,clientes.direccion,clientes.telefono,clientes.obs,IF(activo=1,'ACTIVO','BAJA') as sat "
             + "FROM clientes WHERE clientes.activo = 0;";
              break;

                  case -1:
                      sql = "SELECT clientes.id,clientes.nombre,clientes.direccion,clientes.telefono,clientes.obs,IF(activo=1,'ACTIVO','BAJA') as sat\n" +
                        "FROM central.clientes where  (clientes.id LIKE '"+param+"%' OR clientes.nombre LIKE '%"+param+"%' );";
                  break;
                  
                  case -2:
                      sql = "SELECT clientes.id,clientes.nombre,clientes.direccion,clientes.telefono,clientes.obs,IF(activo=1,'ACTIVO','BAJA') as sat\n" +
                        "FROM clientes where clientes.activo = 1 AND (clientes.id LIKE '"+param+"%' OR clientes.nombre LIKE '%"+param+"%' );";
                  break;
                  
                 case -3:
                      sql = "SELECT clientes.id,clientes.nombre,clientes.direccion,clientes.telefono,clientes.obs,IF(activo=1,'ACTIVO','BAJA') as sat\n" +
                        "FROM clientes where clientes.activo = 0 AND (clientes.id LIKE '"+param+"%' OR clientes.nombre LIKE '%"+param+"%' );";
                  break;
          };
             int i =0,cantFilas=0, cont=1,cantColumnas=0;
             String[][] mat=null, mat2=null;
              int[] arrIdPedido = null;//int para usar hashMap
            Statement st = null;
            ResultSet rs = null;            
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                cantColumnas = rs.getMetaData().getColumnCount();
               if(rs.last()){//Nos posicionamos al final
                    cantFilas = rs.getRow();//sacamos la cantidad de filas/registros
                    rs.beforeFirst();//nos posicionamos antes del inicio (como viene por defecto)
                }
               mat = new String[cantFilas][cantColumnas];
               //aqui iria crear matriz
                while(rs.next())
                {//es necesario el for para llenar dinamicamente la lista, ya que varia el numero de columnas de las tablas
                 
                      for (int x=1;x<= rs.getMetaData().getColumnCount();x++) {
                           // System.out.print("| "+rs.getString(x)+" |");
                             mat[i][x-1]=rs.getString(x);
                      //System.out.print(x+" -> "+rs.getString(x));                   
                      }//for
                       i++;
                }//whilE
            } catch (SQLException ex) {
                Logger.getLogger(ambulantes.class.getName()).log(Level.SEVERE, null, ex);
            }finally{               
//             System.out.println("cierra conexion a la base de datos");    
             try {        
                 if(st != null) st.close();                
                 if(cn !=null) cn.close();
             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null,ex.getMessage()); 
             }
         }//finally        
           if (cantFilas == 0){
                mat=null;
                mat = new String[1][cantColumnas];
                
                for (int j = 0; j < mat[0].length; j++) {
                     mat[0][j]="NO DATA";
                }
           }
return mat;            
}//@endmatrizgetAmbuSemana

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFramGirosView = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabGirosAmb = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton13 = new javax.swing.JButton();
        jFramAltaCliente = new javax.swing.JFrame();
        jLabel43 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtNombClient = new javax.swing.JTextField();
        txtDirClient = new javax.swing.JTextField();
        txtCorrClient = new javax.swing.JTextField();
        txtObsClient = new javax.swing.JTextField();
        txtTelClient = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtRfcClient = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jButton7 = new javax.swing.JButton();
        txtBusqParam = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabViewAllAmbs = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        jFramGirosView.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jFramGirosView.setTitle("Giros de ambulante");
        jFramGirosView.setSize(new java.awt.Dimension(693, 431));

        jTabGirosAmb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabGirosAmb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTabGirosAmb.setRowHeight(23);
        jTabGirosAmb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabGirosAmbMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTabGirosAmb);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Giros de ambulante");

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas.png"))); // NOI18N
        jButton10.setText("Guardar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lapiz.png"))); // NOI18N
        jButton11.setText("Modificar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton12.setText("Eliminar");
        jButton12.setPreferredSize(new java.awt.Dimension(105, 41));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Descripcion");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Id:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel7.setText("--");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextArea1);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goma-de-borrar.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFramGirosViewLayout = new javax.swing.GroupLayout(jFramGirosView.getContentPane());
        jFramGirosView.getContentPane().setLayout(jFramGirosViewLayout);
        jFramGirosViewLayout.setHorizontalGroup(
            jFramGirosViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramGirosViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFramGirosViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2))
                .addGroup(jFramGirosViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jFramGirosViewLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jFramGirosViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jFramGirosViewLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jFramGirosViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jFramGirosViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(jFramGirosViewLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jFramGirosViewLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jFramGirosViewLayout.setVerticalGroup(
            jFramGirosViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramGirosViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFramGirosViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addGroup(jFramGirosViewLayout.createSequentialGroup()
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFramGirosViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFramGirosViewLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(jFramGirosViewLayout.createSequentialGroup()
                        .addGroup(jFramGirosViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))))
        );

        jFramAltaCliente.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jFramAltaCliente.setTitle("SistemaCentral Huixcolotla");
        jFramAltaCliente.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jFramAltaCliente.setResizable(false);
        jFramAltaCliente.setSize(new java.awt.Dimension(543, 360));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel43.setText("Registrar cliente");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("Nombre");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("Dirección");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("Correo");

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setText("Obs.");

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel58.setText("Teléfono");

        txtNombClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtDirClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCorrClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtObsClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtTelClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel59.setText("RFC");

        txtRfcClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Status:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("--");
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombClient, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDirClient, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCorrClient, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtObsClient, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTelClient, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRfcClient, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDirClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorrClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtObsClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRfcClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/uicheckOk.png"))); // NOI18N
        jButton14.setText("Guardar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton15.setText("Cancelar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("--");

        javax.swing.GroupLayout jFramAltaClienteLayout = new javax.swing.GroupLayout(jFramAltaCliente.getContentPane());
        jFramAltaCliente.getContentPane().setLayout(jFramAltaClienteLayout);
        jFramAltaClienteLayout.setHorizontalGroup(
            jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramAltaClienteLayout.createSequentialGroup()
                .addGroup(jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jFramAltaClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15))
                    .addGroup(jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jFramAltaClienteLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel8))
                        .addGroup(jFramAltaClienteLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jFramAltaClienteLayout.setVerticalGroup(
            jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramAltaClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        jButton1.setText("Información");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/IconNewUser.png"))); // NOI18N
        jButton2.setText("Alta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/abajo.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Actualizar.png"))); // NOI18N
        jButton4.setText("Actualizar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excelicon.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Mostrar");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "ACTIVOS", "BAJA TEMPORAL" }));
        jComboBox1.setOpaque(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-what.png"))); // NOI18N

        txtBusqParam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBusqParam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusqParamKeyReleased(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goma-de-borrar.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTabViewAllAmbs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"2", "ABEL BAUTISTA PALACIOS", "", null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No.", "Nombre", "Dirección", "Telefono", "Observaciones"
            }
        ));
        jTabViewAllAmbs.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTabViewAllAmbs.setMaximumSize(new java.awt.Dimension(2147483647, 1500));
        jTabViewAllAmbs.setRowHeight(22);
        jTabViewAllAmbs.setRowMargin(2);
        jTabViewAllAmbs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabViewAllAmbsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTabViewAllAmbs);
        if (jTabViewAllAmbs.getColumnModel().getColumnCount() > 0) {
            jTabViewAllAmbs.getColumnModel().getColumn(0).setMinWidth(80);
            jTabViewAllAmbs.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTabViewAllAmbs.getColumnModel().getColumn(0).setMaxWidth(80);
            jTabViewAllAmbs.getColumnModel().getColumn(3).setMinWidth(100);
            jTabViewAllAmbs.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTabViewAllAmbs.getColumnModel().getColumn(3).setMaxWidth(300);
        }

        jButton9.setBackground(new java.awt.Color(255, 51, 51));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton9.setText("Baja definitiva");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Filas");

        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 657, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(2, 2, 2)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addGap(46, 46, 46)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBusqParam, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator1)
                        .addComponent(jSeparator2)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBusqParam)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int fila = jTabViewAllAmbs.getSelectedRow();
        if(fila > -1){
            String varBus = jTabViewAllAmbs.getValueAt(fila, 0).toString();
            jFramAltaCliente.setLocationRelativeTo(null);
            jFramAltaCliente.setVisible(true);
            jFramAltaCliente.setEnabled(true);
            llenaform(varBus);
            jButton14.setText("Modificar");
        }else{
            JOptionPane.showMessageDialog(null,"No data view");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabViewAllAmbsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabViewAllAmbsMousePressed
           if (evt.getClickCount() > 1) {
                  jButton1.doClick();
              }
    }//GEN-LAST:event_jTabViewAllAmbsMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiaFormAlta();
        int idCli = func.getIdClient();
        jLabel8.setText(Integer.toString((idCli + 1)));
        jFramAltaCliente.setLocationRelativeTo(null);
        jFramAltaCliente.setVisible(true);
        jFramAltaCliente.setEnabled(true);
        jButton14.setText("Guardar");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int fila = jTabViewAllAmbs.getSelectedRow();
        int opc = jComboBox1.getSelectedIndex();
        String[] semad = null;
            String varBus ="";
        if(fila > -1){
            if(opc == 1){
                    contrl.f5ActualAmbu(2,0,jTabViewAllAmbs.getValueAt(fila, 0).toString());
                    jButton4.doClick();
                    jComboBox1.setSelectedIndex(0);
            }
             if(opc >= 2){//aqui va reactivacion
                     contrl.f5ActualAmbu(2,1,jTabViewAllAmbs.getValueAt(fila, 0).toString());
                    jButton4.doClick();
                    jComboBox1.setSelectedIndex(0);
            }
        }else{
            JOptionPane.showMessageDialog(null,"no data View");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    cargaDatasClientes();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int opc = jComboBox1.getSelectedIndex();
        if(opc > -1)
        {
            if(opc == 0){//TODOS
                 jButton3.setEnabled(false);
                 String[][] mat = matrizgetClients(0,"");
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays));
                 jButton9.setVisible(false);
            }
            if(opc == 1){//ACTIVOS
                String[][] mat = matrizgetClients(1,"");
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
                 jButton3.setEnabled(true);
                 jButton3.setIcon(null);
                 jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/abajo.png")));
                 jButton9.setVisible(false);
            }
            if(opc == 2){//BAJA TEMPORAL
                String[][] mat = matrizgetClients(2,"");
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
                 jButton3.setEnabled(true);
                 jButton3.setIcon(null);
                 jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arriba.png")));
                 jButton9.setVisible(true);
            }

        }else{
            JOptionPane.showMessageDialog(null, "No Registros");
        }
            jLabel3.setText(Integer.toString(jTabViewAllAmbs.getRowCount()));
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtBusqParamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusqParamKeyReleased
   int opc = jComboBox1.getSelectedIndex();
         String var = txtBusqParam.getText();
            if(!var.isEmpty()){
                    if(opc == 0){//TODOS
                         String[][] mat = matrizgetClients(-1,var);
                         jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
                     }
                    if(opc == 1){//ACTIVOS
                        String[][] mat = matrizgetClients(-2,var);
                         jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
                    }
                    if(opc == 2){//BAJA TEMPORAL
                        String[][] mat = matrizgetClients(-3,var);
                         jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
                    }
                     jLabel3.setText(Integer.toString(jTabViewAllAmbs.getRowCount()));
            }
    }//GEN-LAST:event_txtBusqParamKeyReleased

    //boton baja definitiva
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       int fila = jTabViewAllAmbs.getSelectedRow();
        if(fila > -1){
             contrl.elimaRow("clientes", "id", jTabViewAllAmbs.getValueAt(fila, 0).toString());
             jButton4.doClick();
        }else{
            JOptionPane.showMessageDialog(null,"no data View");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        txtBusqParam.setText("");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try{
           Thread t;//Thread
                t = new Thread(){
                    public void run(){
        Object var = null,var1 = null, var2 = null,var3 = null,var4 = null,var5 = null,var6 = null,var7 = null,var8 = null;
                        XSSFWorkbook workbook = new XSSFWorkbook();
                        XSSFSheet hoja = workbook.createSheet();
                        XSSFRow fila = hoja.createRow(0);
                        fila.createCell(0).setCellValue("ID");
                        fila.createCell(1).setCellValue("Nombre");
                        fila.createCell(2).setCellValue("Direccion");
                        fila.createCell(3).setCellValue("Telefono");
                        fila.createCell(4).setCellValue("Observaciones");
                        fila.createCell(5).setCellValue("Status");
                        
                        XSSFCellStyle style =workbook.createCellStyle();
                        jProgressBar1.setMaximum(jTabViewAllAmbs.getRowCount());
                        XSSFRow filas = null;
                        Rectangle rect;
                        for(int i=0;i<jTabViewAllAmbs.getRowCount();i++){
                            rect =jTabViewAllAmbs.getCellRect(i, 0, true);
                            try{
                                jTabViewAllAmbs.scrollRectToVisible(rect);
                                
                            }catch(java.lang.ClassCastException e){ }//try
                            jTabViewAllAmbs.setRowSelectionInterval(i, i);
                            
                            jProgressBar1.setValue((i+1));
                            
                            filas = hoja.createRow((i+1));//LO CREMAOS A PARTIR DE LA 5TA FILA
                            //var2.isEmpty()) ? "/" : var2
                            var = jTabViewAllAmbs.getValueAt(i, 0);
                            if (var != null && !var.toString().isEmpty()) {
                                 filas.createCell(0).setCellValue(var.toString());
                            }else{
                                filas.createCell(0).setCellValue("");
                            }
                            var1 = jTabViewAllAmbs.getValueAt(i, 1);
                            if (var1 != null && !var1.toString().isEmpty()) {
                                 filas.createCell(1).setCellValue(var1.toString());
                            }else{
                                filas.createCell(1).setCellValue("");
                            }
                            var2 = jTabViewAllAmbs.getValueAt(i, 2);
                            if (var2 != null && !var2.toString().isEmpty()) {
                                 filas.createCell(2).setCellValue(var2.toString());
                            }else{
                                filas.createCell(2).setCellValue("");
                            }                           
                            var3 = jTabViewAllAmbs.getValueAt(i, 3);
                            if (var3 != null && !var3.toString().isEmpty()) {
                                 filas.createCell(3).setCellValue(var3.toString());
                            }else{
                                filas.createCell(3).setCellValue("");
                            }
                            var4 = jTabViewAllAmbs.getValueAt(i, 4);
                            if (var4 != null && !var4.toString().isEmpty()) {
                                 filas.createCell(4).setCellValue(var4.toString());
                            }else{
                                filas.createCell(4).setCellValue("");
                            }                    
var5 = jTabViewAllAmbs.getValueAt(i, 5);
                            if (var5 != null && !var5.toString().isEmpty()) {
                                 filas.createCell(5).setCellValue(var5.toString());
                            }else{
                                filas.createCell(5).setCellValue("");
                            }
                        }//for1
                            hoja.autoSizeColumn(0);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(1);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(2);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(3);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(4);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(5);//autoajustar celdas al ancho de los datos
                        jProgressBar1.setValue(0);
                        jProgressBar1.setString("Abrieno Excel");
                        try{
                            FileOutputStream word = new FileOutputStream("clientes"+datCtrl.setDateActualGuion()+".xlsx");
                            workbook.write(word);
                            word.close();
                            File file = new File("clientes"+datCtrl.setDateActualGuion()+".xlsx");
                            Desktop.getDesktop().open(file);
                        }catch (Exception ex){
                            Logger.getLogger(ambulantes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//try
                };
           t.start();
       }catch(Exception e) {JOptionPane.showMessageDialog(null, e);} 
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTabGirosAmbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabGirosAmbMouseClicked
          if (evt.getClickCount() > 1) {
            int selc = jTabGirosAmb.getSelectedRow();
            int ultimoReg = -1;
            if(selc > -1){
                jLabel7.setText(jTabGirosAmb.getValueAt(selc, 0).toString());
                jTextArea1.setText(jTabGirosAmb.getValueAt(selc, 1).toString());           
                jButton10.setEnabled(false);
                jButton11.setEnabled(true);
                 jButton12.setEnabled(true); 
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla");
                ultimoReg = Integer.parseInt(contrl.regLastNumGiro()) +1;
                 jLabel7.setText(Integer.toString(ultimoReg));
                 jTextArea1.setText("");    
                 jButton10.setEnabled(true);
                 jButton11.setEnabled(false);
                 jButton12.setEnabled(false);  
             }
          }
    }//GEN-LAST:event_jTabGirosAmbMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
       limpiaAltaGiro();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String idv = jLabel7.getText(), txtA = jTextArea1.getText();
        if(idv.equals("--") || txtA.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Descripcion no puede ser vacio");
        }else{
            contrl.guardaNewGiroAmbs(idv,txtA);
        }
          limpiaAltaGiro();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        String idv = jLabel7.getText(), txtA = jTextArea1.getText();
        if(idv.equals("--") || txtA.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Descripcion no puede ser vacio");
        }else{
            contrl.f5GirosAmbu(idv,txtA);
        }
          limpiaAltaGiro();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
         String idv = jLabel7.getText(), txtA = jTextArea1.getText();
         if(idv.equals("--") || txtA.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Id no existe");
        }else{
            contrl.elimaRow("giros","id",idv);
        }
          limpiaAltaGiro();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        String nombCli = txtNombClient.getText(),
        dirCli = txtDirClient.getText(),
        corCli = txtCorrClient.getText(),
        obsCli = txtObsClient.getText(),
        telCli = txtTelClient.getText(),
        rfcCli = txtRfcClient.getText(),
        act = jButton14.getText();
        
        if(!nombCli.isEmpty() && !obsCli.isEmpty()){
            String[] datcli = new String[7];
            //obtenemos el ultimo idCli
            datcli[0] = jLabel8.getText();
            datcli[1] = (nombCli.isEmpty()) ? "" : nombCli;
            datcli[2] = (dirCli.isEmpty()) ? "" : dirCli;
            datcli[3] = (corCli.isEmpty()) ? "" : corCli;
            datcli[4] = (telCli.isEmpty()) ? "" : telCli;
            datcli[5] = (rfcCli.isEmpty()) ? "" : rfcCli;
            datcli[6] = (obsCli.isEmpty()) ? "" : obsCli;
            if(act.equals("Modificar")){
                contrl.actualizaClients(datcli);
            }else{
                contrl.guardCliente(datcli);
            }
            jFramAltaCliente.dispose();
        //    txtTotOthers.requestFocus(true);
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar por lo menos Nombre y Obs");
        }
        cargaDatasClientes();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        jFramAltaCliente.dispose();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void cargaGiros(){
        String[][] matG = matrizgetGirosAmb();
        jTabGirosAmb.setModel(new TModel(matG, cabGiros));
        jTabGirosAmb.getColumnModel().getColumn(0).setMaxWidth(65);
        jTabGirosAmb.getColumnModel().getColumn(0).setMinWidth(65);
        jTabGirosAmb.getColumnModel().getColumn(0).setPreferredWidth(65);
    }
    
        //regresa matriz de vista tickets del dia
        public String[][] matrizgetGirosAmb(){
        Connection cn = con2.conexion();
          String sql ="",aux;
          sql = "SELECT * FROM giros";
             int i =0,cantFilas=0, cont=1,cantColumnas=0;
             String[][] mat=null, mat2=null;
              int[] arrIdPedido = null;//int para usar hashMap
            Statement st = null;
            ResultSet rs = null;            
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                cantColumnas = rs.getMetaData().getColumnCount();
               if(rs.last()){//Nos posicionamos al final
                    cantFilas = rs.getRow();//sacamos la cantidad de filas/registros
                    rs.beforeFirst();//nos posicionamos antes del inicio (como viene por defecto)
                }
               mat = new String[cantFilas][cantColumnas];
               //aqui iria crear matriz
                while(rs.next())
                {//es necesario el for para llenar dinamicamente la lista, ya que varia el numero de columnas de las tablas
                      for (int x=1;x<= rs.getMetaData().getColumnCount();x++) {
                           // System.out.print("| "+rs.getString(x)+" |");
                             mat[i][x-1]=rs.getString(x);
                      }//for
                       i++;
                }//whilE
            } catch (SQLException ex) {
                Logger.getLogger(ambulantes.class.getName()).log(Level.SEVERE, null, ex);
            }finally{               
//             System.out.println("cierra conexion a la base de datos");    
             try {        
                 if(st != null) st.close();                
                 if(cn !=null) cn.close();
             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null,ex.getMessage()); 
             }
         }//finally        
           if (cantFilas == 0){
                mat=null;
                mat = new String[1][cantColumnas];
                for (int j = 0; j < mat[0].length; j++) {
                     mat[0][j]="NO DATA";
                }
           }
   return mat;            
    }//@endmatrizgetAmbuSemana

        private void limpiaAltaGiro(){
            jTextArea1.setText("");
            jButton10.setEnabled(true);
            jButton11.setEnabled(false);
            jButton12.setEnabled(false);
            cargaGiros();
            int ultimReg = Integer.parseInt(contrl.regLastNumGiro()) +1;
            jLabel7.setText(Integer.toString(ultimReg));
        }
        
        private void llenaform(String idC){
            String[] arrC = func.getAllDataClis(idC);
            limpiaFormAlta();
            jLabel8.setText(arrC[0]);
            txtNombClient.setText(arrC[1]);   
            txtDirClient.setText(arrC[2]);
            txtCorrClient.setText(arrC[3]);
            txtObsClient.setText(arrC[6]);
            txtTelClient.setText(arrC[4]);
            txtRfcClient.setText(arrC[5]);
            if(arrC.equals("0")){
                jLabel10.setText("BAJA");
            }else{
                jLabel10.setText("ACTIVO");                
            }
        }
        
        private void limpiaFormAlta(){
            jLabel8.setText("--");
            txtNombClient.setText("");   
            txtDirClient.setText("");
            txtCorrClient.setText("");
            txtObsClient.setText("");
            txtTelClient.setText("");
            txtRfcClient.setText("");
            jLabel10.setText("--");     
        }
        
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFrame jFramAltaCliente;
    private javax.swing.JFrame jFramGirosView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTabGirosAmb;
    private javax.swing.JTable jTabViewAllAmbs;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtBusqParam;
    private javax.swing.JTextField txtCorrClient;
    private javax.swing.JTextField txtDirClient;
    private javax.swing.JTextField txtNombClient;
    private javax.swing.JTextField txtObsClient;
    private javax.swing.JTextField txtRfcClient;
    private javax.swing.JTextField txtTelClient;
    // End of variables declaration//GEN-END:variables
}
