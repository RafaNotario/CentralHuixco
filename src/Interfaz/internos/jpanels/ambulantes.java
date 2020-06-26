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
public class ambulantes extends javax.swing.JPanel {

        ConexionDBOriginal con2 = new ConexionDBOriginal();
        controlInserts contrl = new controlInserts();
        datesControl datCtrl = new datesControl();
        String[] cabAreasPays = {"#ID", "Nombre", "Direccion", "Telefono", "Giro","Tarifa","Ultima Sem. Pag","Sem. Adeudo","Venc. Inscrip"};
        private final String ruta = System.getProperties().getProperty("user.dir");
        /**
     * Creates new form ambulantes
     */
    public ambulantes() {
        initComponents();
        
        cargaDatasAmbulantes();
                
        jTabViewAllAmbs.getParent().addComponentListener(new ComponentAdapter() {
    @Override
    public void componentResized(final ComponentEvent e) {
        if (jTabViewAllAmbs.getPreferredSize().width < jTabViewAllAmbs.getParent().getWidth()) {
            System.out.println("Resize"+jTabViewAllAmbs.getParent().getWidth());
            jTabViewAllAmbs.setAutoResizeMode(jTabViewAllAmbs.AUTO_RESIZE_ALL_COLUMNS);
        } else {
            jTabViewAllAmbs.setAutoResizeMode(jTabViewAllAmbs.AUTO_RESIZE_OFF);
        }
    }
});
    }    
    
    protected void cargaDatasAmbulantes(){
            String[][] mat = matrizgetAmbsAll(0,"");
             jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays));        
    }
    
    //regresa matrizde vista tickets del dia
        public String[][] matrizgetAmbsAll(int opc,String param){
        Connection cn = con2.conexion();
          String sql ="",aux;
          switch (opc){
              case 0 : //todos 
              sql = "SELECT ambulantes.id,ambulantes.nombre,ambulantes.direccion,ambulantes.telefono,giros.giro,tarifas.descripcion,\n" +
                "(SELECT CONCAT(semanas.anio, \" - \", semanas.semana) FROM semanas where ambulantes.ultimaSem = semanas.id) AS ultSem,\n" +
                "( cast( (SELECT semanas.id FROM semanas WHERE curdate() BETWEEN finicial AND ffinal) as signed ) - cast(ambulantes.ultimaSem as SIGNED) ) AS adeud,\n" +
                "ambulantes.vigMembresia\n" +
                "FROM giros\n" +
                "INNER JOIN ambulantes\n" +
                "ON ambulantes.idGiro = giros.id\n" +
                "INNER JOIN tarifas\n" +
                "ON tarifas.id = ambulantes.idTarifa\n" +
                "ORDER BY ambulantes.id;";                        
                  break;
              case 1:// activos
              sql = "SELECT ambulantes.id,ambulantes.nombre,ambulantes.direccion,ambulantes.telefono,giros.giro,tarifas.descripcion,\n" +
                    "(SELECT CONCAT(semanas.anio, \" - \", semanas.semana) FROM semanas where ambulantes.ultimaSem = semanas.id) AS ultSem,\n" +
                    "( cast( (SELECT semanas.id FROM semanas WHERE curdate() BETWEEN finicial AND ffinal) as signed ) - cast(ambulantes.ultimaSem as SIGNED) ) AS adeud,\n" +
                    "ambulantes.vigMembresia\n" +
                    "FROM giros\n" +
                    "INNER JOIN ambulantes\n" +
                    "ON ambulantes.idGiro = giros.id AND ambulantes.activo > 0\n" +
                    "INNER JOIN tarifas\n" +
                    "ON tarifas.id = ambulantes.idTarifa\n" +
                    "ORDER BY ambulantes.id;";
                  break;
              case 2 :
                  sql = "SELECT ambulantes.id,ambulantes.nombre,ambulantes.direccion,ambulantes.telefono,giros.giro,tarifas.descripcion,\n" +
                    "(SELECT CONCAT(semanas.anio, \" - \", semanas.semana) FROM semanas where ambulantes.ultimaSem = semanas.id) AS ultSem,\n" +
                    "( cast( (SELECT semanas.id FROM semanas WHERE curdate() BETWEEN finicial AND ffinal) as signed ) - cast(ambulantes.ultimaSem as SIGNED) ) AS adeud,\n" +
                    "ambulantes.vigMembresia\n" +
                    "FROM giros\n" +
                    "INNER JOIN ambulantes\n" +
                    "ON ambulantes.idGiro = giros.id AND ambulantes.activo = 0\n" +
                    "INNER JOIN tarifas\n" +
                    "ON tarifas.id = ambulantes.idTarifa\n" +
                    "ORDER BY ambulantes.id;";
                  break;
                  case 3:
                      sql ="SELECT ambulantes.id,ambulantes.nombre,ambulantes.direccion,ambulantes.telefono,giros.giro,tarifas.descripcion,\n" +
                    "(SELECT CONCAT(semanas.anio, \" - \", semanas.semana) FROM semanas where ambulantes.ultimaSem = semanas.id) AS ultSem,\n" +
                    "( cast( (SELECT semanas.id FROM semanas WHERE curdate() BETWEEN finicial AND ffinal) as signed ) - cast(ambulantes.ultimaSem as SIGNED) ) AS adeud,\n" +
                    "ambulantes.vigMembresia\n" +
                    "FROM giros\n" +
                    "INNER JOIN ambulantes\n" +
                    "ON ambulantes.idGiro = giros.id AND ambulantes.activo < 0\n" +
                    "INNER JOIN tarifas\n" +
                    "ON tarifas.id = ambulantes.idTarifa\n" +
                    "ORDER BY ambulantes.id;";
                  break;
                  case -1:
                      sql = "SELECT ambulantes.id,ambulantes.nombre,ambulantes.direccion,ambulantes.telefono,giros.giro,tarifas.descripcion,\n" +
                    "(SELECT CONCAT(semanas.anio, \" - \", semanas.semana) FROM semanas where ambulantes.ultimaSem = semanas.id) AS ultSem,\n" +
                    "( cast( (SELECT semanas.id FROM semanas WHERE curdate() BETWEEN finicial AND ffinal) as signed ) - cast(ambulantes.ultimaSem as SIGNED) ) AS adeud,\n" +
                    "ambulantes.vigMembresia\n" +
                    "FROM giros\n" +
                    "INNER JOIN ambulantes\n" +
                    "ON ambulantes.idGiro = giros.id AND (ambulantes.id LIKE '"+param+"%' OR ambulantes.nombre LIKE '"+param+"%' ) \n" +
                    "INNER JOIN tarifas\n" +
                    "ON tarifas.id = ambulantes.idTarifa\n" +
                    "ORDER BY ambulantes.id;";
                  break;
                  
                  case -2:
                      sql = "SELECT ambulantes.id,ambulantes.nombre,ambulantes.direccion,ambulantes.telefono,giros.giro,tarifas.descripcion,\n" +
                    "(SELECT CONCAT(semanas.anio, \" - \", semanas.semana) FROM semanas where ambulantes.ultimaSem = semanas.id) AS ultSem,\n" +
                    "( cast( (SELECT semanas.id FROM semanas WHERE curdate() BETWEEN finicial AND ffinal) as signed ) - cast(ambulantes.ultimaSem as SIGNED) ) AS adeud,\n" +
                    "ambulantes.vigMembresia\n" +
                    "FROM giros\n" +
                    "INNER JOIN ambulantes\n" +
                    "ON ambulantes.idGiro = giros.id AND ambulantes.activo > 0 AND (ambulantes.id LIKE '"+param+"%' OR ambulantes.nombre LIKE '"+param+"%' ) \n" +
                    "INNER JOIN tarifas\n" +
                    "ON tarifas.id = ambulantes.idTarifa\n" +
                    "ORDER BY ambulantes.id;";
                  break;
                  
                 case -3:
                      sql = "SELECT ambulantes.id,ambulantes.nombre,ambulantes.direccion,ambulantes.telefono,giros.giro,tarifas.descripcion,\n" +
                    "(SELECT CONCAT(semanas.anio, \" - \", semanas.semana) FROM semanas where ambulantes.ultimaSem = semanas.id) AS ultSem,\n" +
                    "( cast( (SELECT semanas.id FROM semanas WHERE curdate() BETWEEN finicial AND ffinal) as signed ) - cast(ambulantes.ultimaSem as SIGNED) ) AS adeud,\n" +
                    "ambulantes.vigMembresia\n" +
                    "FROM giros\n" +
                    "INNER JOIN ambulantes\n" +
                    "ON ambulantes.idGiro = giros.id AND ambulantes.activo = 0 AND (ambulantes.id LIKE '"+param+"%' OR ambulantes.nombre LIKE '"+param+"%' ) \n" +
                    "INNER JOIN tarifas\n" +
                    "ON tarifas.id = ambulantes.idTarifa\n" +
                    "ORDER BY ambulantes.id;";
                  break;
                  
                 case -4:
                      sql = "SELECT ambulantes.id,ambulantes.nombre,ambulantes.direccion,ambulantes.telefono,giros.giro,tarifas.descripcion,\n" +
                    "(SELECT CONCAT(semanas.anio, \" - \", semanas.semana) FROM semanas where ambulantes.ultimaSem = semanas.id) AS ultSem,\n" +
                    "( cast( (SELECT semanas.id FROM semanas WHERE curdate() BETWEEN finicial AND ffinal) as signed ) - cast(ambulantes.ultimaSem as SIGNED) ) AS adeud,\n" +
                    "ambulantes.vigMembresia\n" +
                    "FROM giros\n" +
                    "INNER JOIN ambulantes\n" +
                    "ON ambulantes.idGiro = giros.id AND ambulantes.activo < 0 AND (ambulantes.id LIKE '"+param+"%' OR ambulantes.nombre LIKE '"+param+"%' ) \n" +
                    "INNER JOIN tarifas\n" +
                    "ON tarifas.id = ambulantes.idTarifa\n" +
                    "ORDER BY ambulantes.id;";
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "ACTIVOS", "BAJA TEMPORAL", "BAJA DEFINITIVA" }));
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
                {"2", "ABEL BAUTISTA PALACIOS", "", null, null, null, "2.19 -16", "4", null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "Nombre", "Dirección", "Telefono", "Giro", "Tarifa", "Ult. Sem. Pag.", "Sem. Adeudo", "Venc. Inscripción"
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
            jTabViewAllAmbs.getColumnModel().getColumn(4).setMinWidth(80);
            jTabViewAllAmbs.getColumnModel().getColumn(4).setPreferredWidth(120);
            jTabViewAllAmbs.getColumnModel().getColumn(4).setMaxWidth(250);
            jTabViewAllAmbs.getColumnModel().getColumn(6).setMinWidth(90);
            jTabViewAllAmbs.getColumnModel().getColumn(6).setPreferredWidth(140);
            jTabViewAllAmbs.getColumnModel().getColumn(6).setMaxWidth(250);
            jTabViewAllAmbs.getColumnModel().getColumn(7).setMinWidth(50);
            jTabViewAllAmbs.getColumnModel().getColumn(7).setPreferredWidth(80);
            jTabViewAllAmbs.getColumnModel().getColumn(7).setMaxWidth(150);
            jTabViewAllAmbs.getColumnModel().getColumn(8).setMinWidth(80);
            jTabViewAllAmbs.getColumnModel().getColumn(8).setPreferredWidth(120);
            jTabViewAllAmbs.getColumnModel().getColumn(8).setMaxWidth(500);
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1246, Short.MAX_VALUE))
                .addGap(110, 110, 110))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
             altaAmbulantes altamb = new altaAmbulantes(varBus);
            altamb.setVisible(true);
            altamb.setEnabled(true);
            altamb.validate();
        }else{
            JOptionPane.showMessageDialog(null,"no data View");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabViewAllAmbsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabViewAllAmbsMousePressed
           if (evt.getClickCount() > 1) {
                  jButton1.doClick();
              }
    }//GEN-LAST:event_jTabViewAllAmbsMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            altaAmbulantes altamb = new altaAmbulantes("1");
            altamb.setVisible(true);
            altamb.setEnabled(true);
            altamb.jButton2.doClick();
            altamb.validate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int fila = jTabViewAllAmbs.getSelectedRow();
        int opc = jComboBox1.getSelectedIndex();
        if(fila > -1){
            if(opc == 1)
             contrl.f5ActualAmbu(0,0,jTabViewAllAmbs.getValueAt(fila, 0).toString());
            if(opc == 2)
             contrl.f5ActualAmbu(0,1,jTabViewAllAmbs.getValueAt(fila, 0).toString());
        }else{
            JOptionPane.showMessageDialog(null,"no data View");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    cargaDatasAmbulantes();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int opc = jComboBox1.getSelectedIndex();
        if(opc > -1)
        {
            if(opc == 0){//TODOS
                 jButton3.setEnabled(false);
                 String[][] mat = matrizgetAmbsAll(0,"");
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
            }
            if(opc == 1){//ACTIVOS
                String[][] mat = matrizgetAmbsAll(1,"");
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
                 jButton3.setEnabled(true);
                 jButton3.setIcon(null);
                 jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/abajo.png")));
            }
            if(opc == 2){//BAJA TEMPORAL
                String[][] mat = matrizgetAmbsAll(2,"");
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
                  jButton3.setEnabled(true);
                 jButton3.setIcon(null);
                 jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arriba.png")));
            }
             if(opc == 3){//BAJA PERMANENTE
                String[][] mat = matrizgetAmbsAll(3,"");
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
                  jButton3.setEnabled(false);
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
                 String[][] mat = matrizgetAmbsAll(-1,var);
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
            }
            if(opc == 1){//ACTIVOS
                String[][] mat = matrizgetAmbsAll(-2,var);
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
            }
            if(opc == 2){//BAJA TEMPORAL
                String[][] mat = matrizgetAmbsAll(-3,var);
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
            }
             if(opc == 3){//BAJA DEFINITIVA
                String[][] mat = matrizgetAmbsAll(-4,var);
                 jTabViewAllAmbs.setModel(new TModel(mat, cabAreasPays)); 
            }
             
             jLabel3.setText(Integer.toString(jTabViewAllAmbs.getRowCount()));
            }
    }//GEN-LAST:event_txtBusqParamKeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        
       int fila = jTabViewAllAmbs.getSelectedRow();
        if(fila > -1){
             contrl.f5ActualAmbu(0,-1,jTabViewAllAmbs.getValueAt(fila, 0).toString());
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
                        fila.createCell(0).setCellValue("Numero");
                        fila.createCell(1).setCellValue("Nombre");
                        fila.createCell(2).setCellValue("Direccion");
                        fila.createCell(3).setCellValue("Telefono");
                        fila.createCell(4).setCellValue("Giro");
                        fila.createCell(5).setCellValue("Tarifa");
                        fila.createCell(6).setCellValue("Ult. Sem Pag.");
                        fila.createCell(7).setCellValue("Sem. Adeudo");
                        fila.createCell(8).setCellValue("Venc. Inscrip");
                        
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
var6 = jTabViewAllAmbs.getValueAt(i, 6);
                            if (var6 != null && !var6.toString().isEmpty()) {
                                 filas.createCell(6).setCellValue(var6.toString());
                            }else{
                                filas.createCell(6).setCellValue("");
                            }
var7 = jTabViewAllAmbs.getValueAt(i, 7);
                            if (var7 != null && !var7.toString().isEmpty()) {
                                 filas.createCell(7).setCellValue(var7.toString());
                            }else{
                                filas.createCell(7).setCellValue("");
                            }
var8 = jTabViewAllAmbs.getValueAt(i, 8);
                            if (var8 != null && !var8.toString().isEmpty()) {
                                 filas.createCell(8).setCellValue(var8.toString());
                            }else{
                                filas.createCell(8).setCellValue("");
                            }
                            
                            hoja.autoSizeColumn(0);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(1);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(2);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(3);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(4);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(5);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(6);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(7);//autoajustar celdas al ancho de los datos
                            hoja.autoSizeColumn(8);//autoajustar celdas al ancho de los datos
                        }//for1
                        jProgressBar1.setValue(0);
                        jProgressBar1.setString("Abrieno Excel");
                        try{
                            FileOutputStream word = new FileOutputStream("ambulantes"+datCtrl.setDateActualGuion()+".xlsx");
                            workbook.write(word);
                            word.close();
                            File file = new File("ambulantes"+datCtrl.setDateActualGuion()+".xlsx");
                            Desktop.getDesktop().open(file);
                        }catch (Exception ex){
                            Logger.getLogger(ambulantes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//try
                };
           t.start();
       }catch(Exception e) {JOptionPane.showMessageDialog(null, e);} 
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTabViewAllAmbs;
    private javax.swing.JTextField txtBusqParam;
    // End of variables declaration//GEN-END:variables
}
