/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Controller.datesControl;
import Controller.funciones;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import renderTable.TModel;
import tickets.Jasper.Reportes;

public class vistaReimprAll extends javax.swing.JFrame {

    funciones func = new funciones();
    datesControl datCtrl = new datesControl();
    Reportes rP = new Reportes();
    
    static int optins = -1;
    static String idPersomn = "";
    String[] cabHistor = {"Semana","Fecha","Folio"};
    
    String[] arrAreas = {"Mantenimiento","Basura","Policia","Resguardo"};//2,3,4,5
    String[] arrAmbus ={"Semanas","Resguardos","Inscripciones"};// 6,7,8,9,10
    String[] arrCargad ={"Semanas","Inscripciones","Rentas"};// 8,9,11,12

    public vistaReimprAll(int option, String idPerson) {
        initComponents();
        this.optins = option;
        this.idPersomn = idPerson;
        jYearChooser1.setStartYear(2018);
        
        cragaDinRubros(option);//para llenar el combo segun que querer mostrar
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabIconFinder = new javax.swing.JLabel();
        jLabNombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCmBOptions = new javax.swing.JComboBox<>();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabViewsPays = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(444, 472));

        jLabIconFinder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabIconFinder.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabNombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabNombre.setText("jLabel4");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Historial de pagos");

        jCmBOptions.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jCmBOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCmBOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmBOptionsActionPerformed(evt);
            }
        });

        jYearChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jYearChooser1MouseClicked(evt);
            }
        });
        jYearChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jYearChooser1PropertyChange(evt);
            }
        });

        jTabViewsPays.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabViewsPays.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Semana", "Fecha", "Folio"
            }
        ));
        jTabViewsPays.setRowHeight(23);
        jTabViewsPays.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabViewsPaysMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTabViewsPays);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Doble click en la lista para mostrar el ticket seleccionado");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabIconFinder, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCmBOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabIconFinder, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCmBOptions, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jYearChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jYearChooser1MouseClicked
        
    }//GEN-LAST:event_jYearChooser1MouseClicked

    private void jCmBOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmBOptionsActionPerformed
        int eligio = jCmBOptions.getSelectedIndex();
        int yearB = jYearChooser1.getYear();
        //historPaysAmbs(int quees, String qBusca,String idBusq,String year){
       if(eligio > -1){
           switch(optins){
           case 0:    //Area
               if(eligio == 0){
                   String[][] mat = func.historPaysAmbs(0,"Mantenimiento",idPersomn,Integer.toString(yearB));
                   DefaultTableModel modelo = new TModel(mat, cabHistor);
                    jTabViewsPays.setModel(modelo);
               }
               if(eligio == 1){
                    String[][] mat = func.historPaysAmbs(0,"Basura",idPersomn,Integer.toString(yearB));
                   DefaultTableModel modelo = new TModel(mat, cabHistor);
                   jTabViewsPays.setModel(modelo);
               }
               if(eligio == 2){
                   String[][] mat = func.historPaysAmbs(0,"Policia",idPersomn,Integer.toString(yearB));
                   DefaultTableModel modelo = new TModel(mat, cabHistor);
                   jTabViewsPays.setModel(modelo);
               }           
             if(eligio == 3){
                   String[][] mat = func.historPaysAmbs(0,"Resguardo",idPersomn,Integer.toString(yearB));
                   DefaultTableModel modelo = new TModel(mat, cabHistor);
                   jTabViewsPays.setModel(modelo);
               } 
           break;
           case 1:    //Ambulante
               if(eligio == 0){
                   String[][] mat = func.historPaysAmbs(1,"Semanas",idPersomn,Integer.toString(yearB));
                   DefaultTableModel modelo = new TModel(mat, cabHistor);
                    jTabViewsPays.setModel(modelo);
               }
               if(eligio == 1){
                    String[][] mat = func.historPaysAmbs(1,"Resguardos",idPersomn,Integer.toString(yearB));
                   DefaultTableModel modelo = new TModel(mat, cabHistor);
                   jTabViewsPays.setModel(modelo);
               }
               if(eligio == 2){
                   String[][] mat = func.historPaysAmbs(1,"Inscripciones",idPersomn,Integer.toString(yearB));
                   DefaultTableModel modelo = new TModel(mat, cabHistor);
                   jTabViewsPays.setModel(modelo);
               }
           break;
           case 2:    //Cargador
              if(eligio == 0){
                   String[][] mat = func.historPaysAmbs(2,"Semanas",idPersomn,Integer.toString(yearB));
                   DefaultTableModel modelo = new TModel(mat, cabHistor);
                    jTabViewsPays.setModel(modelo);
               }
               if(eligio == 1){
                    String[][] mat = func.historPaysAmbs(2,"Inscripciones",idPersomn,Integer.toString(yearB));
                   DefaultTableModel modelo = new TModel(mat, cabHistor);
                   jTabViewsPays.setModel(modelo);
               }
               if(eligio == 2){
                   String[][] mat = func.historPaysAmbs(2,"Rentas",idPersomn,Integer.toString(yearB));
                   DefaultTableModel modelo = new TModel(mat, cabHistor);
                   jTabViewsPays.setModel(modelo);
               } 
           break;
       };
       
       jLabel2.setText(Integer.toString(jTabViewsPays.getRowCount()));
       }else{
           //System.out.println("vacion");
       }
    }//GEN-LAST:event_jCmBOptionsActionPerformed

    private void jYearChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jYearChooser1PropertyChange

    }//GEN-LAST:event_jYearChooser1PropertyChange

    private void jTabViewsPaysMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabViewsPaysMousePressed
         if (evt.getClickCount() > 1) {
            int fila = jTabViewsPays.getSelectedRow();
                if(fila >= 0){
                    String mostTic = jTabViewsPays.getValueAt(fila, 2).toString(),
                            concepto = this.getTitle();
                    if(concepto.equals("Area")){
                        String[] dat = rP.getUltimPagoarea(mostTic);
                        rP.imprim80MM(mostTic, dat,false);
                    }
                    if(concepto.equals("Ambulante")){
                        String[] dat = rP.getTickPagoAmbu(mostTic);
                        rP.imprim80MMAmbus(mostTic, dat,false);
                    }
                    if(concepto.equals("Cargador")){     
                        if(jCmBOptions.getSelectedIndex() == 2){
                            rP.imprim80MM_CargRent(mostTic,false);
                        }else{
                        String[] dat = rP.getTickPagoCargad(mostTic);
                        rP.imprim80MMCargad(mostTic, dat,false);
                    
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe elegir que mostrar");
                }
              }
    }//GEN-LAST:event_jTabViewsPaysMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaReimprAll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaReimprAll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaReimprAll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaReimprAll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaReimprAll(optins,idPersomn).setVisible(true);
            }
        });
    }

    public void cragaDinRubros(int opc){
switch(opc){
    case 0://areas
        jCmBOptions.removeAllItems();
        for (int i = 0; i < arrAreas.length; i++) {
            jCmBOptions.addItem(arrAreas[i]);
        }
        jLabIconFinder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/almacen.png")));
    break;
    case 1://Ambulantes
        jCmBOptions.removeAllItems();
        for (int i = 0; i < arrAmbus.length; i++) {
            jCmBOptions.addItem(arrAmbus[i]);
        }
        jLabIconFinder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/viaje.png")));
    break;
    case 2://Cargadores
        jCmBOptions.removeAllItems();
        for (int i = 0; i < arrCargad.length; i++) {
            jCmBOptions.addItem(arrCargad[i]);
        }
         jLabIconFinder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/diablito1.png")));
    break;
}; 
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jCmBOptions;
    public javax.swing.JLabel jLabIconFinder;
    public javax.swing.JLabel jLabNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabViewsPays;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}