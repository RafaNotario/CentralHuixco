/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import conexion.ConexionDBOriginal;
import java.math.BigDecimal;
import Controller.datesControl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Antonio R. Notario Rodriguez
 */


public class funciones {
    
      //VARIABLES PARA CALCULO DE DINERO  
  private static int DECIMALS = 1;
  private static int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;
  private BigDecimal fAmountOne;
  private BigDecimal fAmountTwo;
  public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);


    
     ConexionDBOriginal con2 = new ConexionDBOriginal();
     datesControl datC = new datesControl();
     
            public int validaLoginUsers(String user, String pass){
            Connection cn = con2.conexion();
            boolean existe =false;
            int idUser= -1;
            int num=0,i=1;
            String sql = "";
            sql = "SELECT id FROM usuarios WHERE usuario = '"+user+"' AND password = '"+pass+"'";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        idUser=rs.getInt(1);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return idUser;
    }//validaloginUsers
    
     public String[] getnombreUsuario(int id){
            Connection cn = con2.conexion();
            String[] idUser = new String[7];
            String sql = "";
            sql = "SELECT * FROM usuarios WHERE id = '"+id+"'";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        idUser[0]=rs.getString(1);
                        idUser[1]=rs.getString(2);
                        idUser[2]=rs.getString(3);
                        idUser[3]=rs.getString(4);
                        idUser[4]=rs.getString(5);
                        idUser[5]=rs.getString(6);
                        idUser[6]=rs.getString(7);
                    }else{
                        for (int j = 0; j < idUser.length; j++) {
                            idUser[j] = "NO-DATA";
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return idUser;
    }//@endgetnombreUsuario
            
        public void GuardaTurno(List<String> param){
             java.util.Date date = new Date();
             String timeDate = new java.sql.Timestamp(date.getTime()).toString();
            Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";     
          SQL="INSERT INTO turnos (idusuario,saldo,finicial) VALUES (?,?,?)";                           
          try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param.get(0));
                pps.setString(2, param.get(1));
                pps.setString(3,timeDate);
               System.out.println("Guardare en TIMESTAMP: "+timeDate);
               
                pps.executeUpdate();
                System.out.println("Turno creado correctamente.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error durante creacion de Turno.");
                Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);               
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                      JOptionPane.showMessageDialog(null, ex.getMessage() );    
                    }                
            }//finally catch  
          }//@endGuardaTurno
            
       public String[] lapsoSemanasIni(String fech){
            Connection cn = con2.conexion();
           String[] lapso = new String[5];
            String sql = "";
            sql = "SELECT * FROM semanas WHERE '"+fech+"' BETWEEN finicial AND ffinal;  ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        lapso[0] = rs.getString(1);
                        lapso[1]= rs.getString(2);
                        lapso[2] = rs.getString(3);
                        lapso[3] = rs.getString(4);
                        lapso[4] = rs.getString(5);
                    }else{
                        for (int i = 0; i < lapso.length; i++) {
                            lapso[i] = "NO-DATA";
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return lapso;
    }//validaloginUsers

       
       ///*** Obtener ultimo turno creado
     public int getenTurno(){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
            sql = "SELECT id FROM turnos ORDER BY id DESC LIMIT 1; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        idTurno = rs.getInt(1);
                    }else{
                         idTurno = -1;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return idTurno;
    }//@endgetenTurno
     
          public int getUltimPagoarea(){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
            sql = "SELECT id FROM pagos_areas ORDER BY id DESC LIMIT 1; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        idTurno = rs.getInt(1);
                    }else{
                         idTurno = -1;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return idTurno;
    }//@endgetUltimPagoarea
     
/*//// OBTENER DATOS DE AMBULANTE para mostrar en panelInfo*/
       public String[] getAmbus1(String idAmbu){
            Connection cn = con2.conexion();
           String[] lapso = new String[3];
            String sql = "";
            sql = "SELECT ambulantes.direccion, giros.giro, ambulantes.obs \n" +
                    "FROM ambulantes\n" +
                    "INNER JOIN giros\n" +
                    "ON ambulantes.idGiro = giros.id AND ambulantes.id = '"+idAmbu+"'; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        lapso[0] = rs.getString(1);
                        lapso[1]= rs.getString(2);
                        lapso[2] = rs.getString(3);
                    }else{
                        for (int i = 0; i < lapso.length; i++) {
                            lapso[i] = "NO-DATA";
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return lapso;
    }//getAmbus1          
          
//obtener la condonacion y cuotas por el idAmbulante
          public String[] getAmbusCondonac(String idAmbu){ 
           Connection cn = con2.conexion();
           String[] lapso = new String[9];
           String sql = "";
           sql = "SELECT ambulantes.idResg, ambulantes.condMemb, ambulantes.condDerecho, ambulantes.condResg, ambulantes.vigMembresia, \n" +
                    "tarifas.derechoSemanal, tarifas.membAnual,tarifas.membSemestral,tarifas.membTrimestral\n" +
                    "FROM ambulantes\n" +
                    "INNER JOIN tarifas\n" +
                    "ON ambulantes.idTarifa = tarifas.id AND ambulantes.id = '"+idAmbu+"'; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
              while(rs.next())
                {
                    lapso[0] = rs.getString(1);
                    lapso[1] = rs.getString(2);
                    lapso[2] = rs.getString(3);
                    lapso[3] = rs.getString(4);
                    lapso[4] = rs.getString(5);
                    lapso[5] = rs.getString(6);
                    lapso[6] = rs.getString(7);
                    lapso[7] = rs.getString(8);
                    lapso[8] = rs.getString(9);
                }
            } catch (SQLException ex) {
                Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return lapso;
    }//getAmbusCondonac      
       
//Proceso para pago de ambulantes
          public int getUltimPagoambus(){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
            sql = "SELECT id FROM pagos_amb ORDER BY id DESC LIMIT 1; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        idTurno = rs.getInt(1);
                    }else{
                         idTurno = -1;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return idTurno;
    }//@endgetUltimPagoAmbus
          
    public void limpiar(JPanel Pn)
    {
        Pn.removeAll();
        Pn.validate();
        Pn.repaint();
    }
    public void limpiarLayer(JLayeredPane Pn)
    {
        Pn.removeAll();
        Pn.validate();
        Pn.repaint();
    }
    
                 //CALCULO DE DINERO $$ ***
     private BigDecimal rounded(BigDecimal aNumber){
            return aNumber.setScale(DECIMALS, ROUNDING_MODE);
     }
     
     public BigDecimal multiplicaAmount(BigDecimal aAmountOne, BigDecimal aAmountTwo){
            fAmountOne = rounded(aAmountOne);
            fAmountTwo = rounded(aAmountTwo);
            System.out.println(fAmountOne+" -> "+fAmountTwo);
        return fAmountOne.multiply(fAmountTwo);
    }
     
      public BigDecimal getDifference(BigDecimal aAmountOne, BigDecimal aAmountTwo){
            fAmountOne = rounded(aAmountOne);
            fAmountTwo = rounded(aAmountTwo);
        return fAmountOne.subtract(fAmountTwo);
    }
      public BigDecimal getSum(BigDecimal aAmountOne, BigDecimal aAmountTwo){
          fAmountOne = rounded(aAmountOne);
            fAmountTwo = rounded(aAmountTwo);
          return fAmountOne.add(fAmountTwo);
      }
      
     public BigDecimal percentage(BigDecimal base, BigDecimal pct){
         fAmountOne = rounded(base);
            fAmountTwo = rounded(pct);
       return fAmountOne.multiply(fAmountTwo).divide(ONE_HUNDRED);
    }
            
       public static void main(String args[]){
           BigDecimal amountOne = new BigDecimal(250);//monto a cobrar
           BigDecimal amountTwo = new BigDecimal(100);//cantidad recivida
           
           funciones fn =  new funciones();
              java.util.Date date = new Date();
                String[] prue = fn.getAmbus1("45");
              System.out.println("Guardare en TIMESTAMP: "+new java.sql.Timestamp(date.getTime() ) );
               for (int i = 0; i < prue.length; i++) {
                  // System.out.println(prue[i] ); 
              }
               
         System.out.println("ambus iultimo: "+fn.getenTurno());      
       }
           
}
