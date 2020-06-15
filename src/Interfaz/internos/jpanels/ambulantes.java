/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.internos.jpanels;


import Controller.controlInserts;
import Interfaz.altaAmbulantes;
import conexion.ConexionDBOriginal;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import renderTable.TModel;

/**
 *
 * @author monit
 */
public class ambulantes extends javax.swing.JPanel {

        ConexionDBOriginal con2 = new ConexionDBOriginal();
         controlInserts contrl = new controlInserts();
        String[] cabAreasPays = {"#ID", "Nombre", "Direccion", "Telefono", "Giro","Tarifa","Ultima Sem. Pag","Sem. Adeudo","Venc. Inscrip"};
       
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
        jButton5 = new javax.swing.JButton();
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

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print32x32.png"))); // NOI18N

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excelicon.png"))); // NOI18N

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(285, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTabViewAllAmbs;
    private javax.swing.JTextField txtBusqParam;
    // End of variables declaration//GEN-END:variables
}
