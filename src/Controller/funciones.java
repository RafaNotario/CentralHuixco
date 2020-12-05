
package Controller;

import conexion.ConexionDBOriginal;
import java.math.BigDecimal;
import Controller.datesControl;
import Interfaz.altaAmbulantes;
import Interfaz.internos.jpanels.internoCaja;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
                pps.executeUpdate();
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
       
       // Obtener ultimo turno creado
     public int getenTurno(int id){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
            //SELECT id FROM turnos ORDER BY id DESC LIMIT 1;
            sql = "SELECT turnos.id FROM turnos\n" +
                    "INNER JOIN usuarios\n" +
                    "on turnos.idusuario = usuarios.id AND usuarios.id = "+id+" and turnos.ffinal is null\n" +
                    "order by turnos.id DESC limit 1;";
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
     
     
          ///*** Obtener ultimo turno creado
    public int getenTurno(){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
           
            sql = "SELECT id FROM turnos ORDER BY id DESC LIMIT 1;";
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
     
     
          public String[] getTurnoData(int id){
            Connection cn = con2.conexion();
            String[] idUser = new String[5];
            String sql = "";
            sql = "SELECT * FROM turnos WHERE id = '"+id+"'";
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
    }//@end getTurnoData
     
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
           String[] lapso = new String[5];
            String sql = "";
            sql = "SELECT ambulantes.direccion, giros.giro, ambulantes.obs,ambulantes.ultimaSem,ambulantes.idResg \n" +
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
                        lapso[3] = rs.getString(4);
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
          
 /*//// OBTENER DATOS DE AMBULANTE para mostrar en panelInfo*/
       public String[] getCargad(String idCargad){
            Connection cn = con2.conexion();
           String[] lapso = new String[15];
            String sql = "";
            sql = "SELECT cargadores.id,cargadores.nombre,cargadores.direccion,cargadores.obs,cargadores.diablo,cargadores.condMemb,cargadores.condDerecho,\n" +
                    "cargadores.condRenta,if(cargadores.vigMembresia IS NULL, \"\",cargadores.vigMembresia) AS fechon,cargadores.ultimaSem,cargadores.idTarifa,\n" +
                    "tarifas.derechoSemanal,tarifas.membAnual,tarifas.membSemestral,tarifas.membTrimestral \n" +
                    "FROM central.cargadores\n" +
                    "INNER JOIN central.tarifas\n" +
                    "ON cargadores.idTarifa = tarifas.id AND cargadores.id = '"+idCargad+"';";
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
                        lapso[3] = rs.getString(4);
                        lapso[4] = rs.getString(5);
                        lapso[5] = rs.getString(6);
                        lapso[6] = rs.getString(7);
                        lapso[7] = rs.getString(8);
                        lapso[8] = rs.getString(9);
                        lapso[9] = rs.getString(10);
                        lapso[10] = rs.getString(11);
                        lapso[11] = rs.getString(12);
                        lapso[12] = rs.getString(13);
                        lapso[13] = rs.getString(14);
                        lapso[14] = rs.getString(15);
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
    }//@endgetCargad       
       
//******/*************   PAGO DE CARGADORES -----------------------------------------
       //OBTENER ULTIMO PAGO REALIZADO
          public int getUltimPagoCargad(){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
            sql = "SELECT id FROM pagos_carg ORDER BY id DESC LIMIT 1; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
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
    }//@endgetUltimPagoCargad
          
/*FUNCION PARA OBTENER DATOS DE OTROSV¿COBROS SEGUN TIPO */
          /*//// OBTENER DATOS DE AMBULANTE para mostrar en panelInfo*/
       public String[] getAllOtros(String id, String qEs){
            Connection cn = con2.conexion();
           String[] lapso = new String[2];
            String sql = "";
            if(qEs.equals("Ambulante"))
                sql = "select ambulantes.direccion,ambulantes.obs FROM ambulantes WHERE ambulantes.id = '"+id+"';";
            if(qEs.equals("Cargador"))
                sql = "select cargadores.direccion,cargadores.obs FROM cargadores WHERE cargadores.id = '"+id+"';";
            if(qEs.equals("Cliente"))
                sql = "select clientes.direccion,clientes.obs FROM clientes WHERE clientes.id = '"+id+"';";

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
    }//@end getAllOtros     
       
          /*//// OBTENER DATOS DE AMBULANTE para mostrar en panelInfo*/
       public String[] getOtros_catalogId(String id){
            Connection cn = con2.conexion();
           String[] lapso = new String[5];
            String sql = "";

                sql = "select * FROM otros_catalogo WHERE id = '"+id+"';";
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
                        lapso[2]= rs.getString(3);
                        lapso[3]= rs.getString(4);
                        lapso[4]= rs.getString(5);
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
    }//@endgetOtros_catalogId
       
              //OBTENER ULTIMO PAGO REALIZADO
          public int getUltimPagootros_venta(){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
            sql = "SELECT id FROM otros_venta ORDER BY id DESC LIMIT 1; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
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
    }//@endgetUltimPagootros_venta
          
              //OBTENER ULTIMO PAGO REALIZADO
          public int getUltimPagoCarg_Rent(){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
            sql = "SELECT id FROM pagos_cargrenta ORDER BY id DESC LIMIT 1; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
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
    }//@endgetUltimPagootros_venta
          
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
//            System.out.println(fAmountOne+" -> "+fAmountTwo);
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
     
            ///*** Obtener ultimo turno creado
     public String getMontoInfrac(String foli){
            Connection cn = con2.conexion();
            String montoIn = "";
            String sql = "";
            sql = "SELECT monto FROM pagos_infrac WHERE folio = '"+foli+"'; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                if(rs.next())
                {
                        montoIn = rs.getString(1);
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
           return montoIn;
    }//@endgetMontoInfrac
     
     //valida si existe ese id en alguna tabla Aun no se usa
         public boolean validaRelCompPed(String idBusq,String camp){
            Connection cn = con2.conexion();
            boolean existe =false;
            int num=0,i=1;
            String sql = "";
            switch(camp){
                case "id_compraProveed":
                    sql = "SELECT '1' FROM relcomprapedido WHERE id_compraProveed = '"+idBusq+"';";
                break;
                case "id_pedidoCli":
                    sql = "SELECT '1' FROM relcomprapedido WHERE id_pedidoCli = '"+idBusq+"';";
                break;
                case "id_fleteP":
                    sql = "SELECT '1' FROM relcomprapedido WHERE id_fleteP = '"+idBusq+"';";
                break;
                case "fleteenviado":
                    sql = "SELECT '1' FROM fleteenviado WHERE id_fleteE = '"+idBusq+"';";
                break;
                case "compramayoreo":
                    sql = "SELECT '1' FROM compramayoreo WHERE id_compraProveMin = '"+idBusq+"';";
                break;
            };
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        existe =true;
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
           return existe;
    }
         
//OBTENER ULTIMO PAGO OTROS_CATALOGO
          public int getUltimOtrosCatalog(){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
            sql = "SELECT id FROM otros_catalogo ORDER BY id DESC LIMIT 1; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
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
    }//@end getUltimOtrosCatalog
          
      public int getIdClient(){
            Connection cn = con2.conexion();
            int idUser= -1;
            String sql = "";
            sql = "SELECT id FROM clientes ORDER BY id DESC LIMIT 1";
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
    }//@end getIdClient
          
      
           public int getIdOthsCatalog(){
            Connection cn = con2.conexion();
            int idUser= -1;
            String sql = "";
            sql = "SELECT id FROM otros_catalogo ORDER BY id DESC LIMIT 1";
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
    }//@end getIdOthsCatalog
           
         public int getUltimCancelaciones(){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
            sql = "SELECT id FROM cancelaciones ORDER BY id DESC LIMIT 1; ";
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
           
                       //OBTENER ULTIMO GASTO DE CAJA REALIZADO
          public int getUltimGastCaja(){
            Connection cn = con2.conexion();
            int idTurno = -1;
            String sql = "";
            sql = "SELECT id FROM gastos_caja ORDER BY id DESC LIMIT 1; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
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
    }//@end getUltimGastCaja
         
                    /*//// OBTENER DATOS DE AMBULANTE para mostrar en panelInfo*/
       public String[] getDatsGasto(String id){
            Connection cn = con2.conexion();
           String[] lapso = new String[5];
            String sql = "";
                sql = "select idRubrocaja,concepto,solicitante,obs,monto FROM gastos_caja WHERE id = '"+id+"';";
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
                        lapso[2]= rs.getString(3);
                        lapso[3]= rs.getString(4);
                        lapso[4]= rs.getString(5);
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
    }//@endgetOtros_catalogId
       
            public int getIdRubGastos(){
            Connection cn = con2.conexion();
            int idUser= -1;
            String sql = "";
            sql = "SELECT id FROM rubroscaja ORDER BY id DESC LIMIT 1";
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
    }//@end getIdOthsCatalog
          
          public String[] getAllDataAmb(String id){
            Connection cn = con2.conexion();
            String[] idUser = new String[16];
            String sql = "";
            sql = "SELECT * FROM ambulantes WHERE id = '"+id+"'";
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
                        idUser[7]=rs.getString(8);
                        idUser[8]=rs.getString(9);
                        idUser[9]=rs.getString(10);
                        idUser[10]=rs.getString(11);
                        idUser[11]=rs.getString(12);
                        idUser[12]=rs.getString(13);
                        idUser[13]=rs.getString(14);
                        idUser[14]=rs.getString(15);
                        idUser[15]=rs.getString(16);
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
               
          public String getnombreTarifaAmb(String colum,String tabl,String idComp){
            Connection cn = con2.conexion();
          String nAmb = "";
            String sql = "";
            sql = "SELECT "+colum+" FROM "+tabl+" WHERE id = '"+idComp+"'";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        nAmb = rs.getString(1);
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
           return nAmb;
    }//@end getnombreTarifa
          
          public String[] getAllDataCargad(String id){
            Connection cn = con2.conexion();
            String[] idUser = new String[15];
            String sql = "";
            sql = "SELECT * FROM cargadores WHERE id = '"+id+"'";
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
                        idUser[7]=rs.getString(8);
                        idUser[8]=rs.getString(9);
                        idUser[9]=rs.getString(10);
                        idUser[10]=rs.getString(11);
                        idUser[11]=rs.getString(12);
                        idUser[12]=rs.getString(13);
                        idUser[13]=rs.getString(14);
                        idUser[14]=rs.getString(15);
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
    }//@end getAllDataCargad
          
          
         public String totalturno(int opc, String idT){
          Connection cn = con2.conexion();
          String nAmb = "";
            String sql = "";
            switch (opc){
                case 0://pagos_areas
                     sql = "SELECT sum(pagos_areasdet.importe) AS totl\n" +
                            "FROM pagos_areas\n" +
                            "INNER JOIN pagos_areasdet\n" +
                            "ON pagos_areas.id = pagos_areasdet.idTicket AND pagos_areas.idCancelacion = 0 AND pagos_areas.idTurno = '"+idT+"' \n" +
                            ";";
                break;
                case 1:// pagos_amb
                     sql = "SELECT sum(pagos_ambdet.importe - pagos_ambdet.descuento) AS totl\n" +
                            "FROM pagos_amb\n" +
                            "INNER JOIN pagos_ambdet\n" +
                            "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_amb.idCancelacion = 0 AND pagos_amb.idTurno = '"+idT+"' \n" +
                            ";";
                break;
                case 2://pagos_carg
                     sql = "SELECT sum(pagos_cargdet.importe - pagos_cargdet.descuento) AS totCarg\n" +
                            "FROM pagos_carg\n" +
                            "INNER JOIN pagos_cargdet\n" +
                            "ON pagos_carg.id = pagos_cargdet.idTicket  AND pagos_carg.idCancelacion = 0 AND pagos_carg.idTurno = '"+idT+"' \n" +
                            ";";
                break;
                case 3://pagos_cargrenta
                     sql = "SELECT sum(pagos_cargrenta.importe) AS totCargR\n" +
                            "FROM pagos_cargrenta\n" +
                            "WHERE pagos_cargrenta.idTurno = '"+idT+"' AND pagos_cargrenta.idCancelacion = 0\n" +
                            ";";
                break;
                case 4://pagos_infrac
                     sql = "SELECT IF(pagos_infrac.descuento IS NULL, sum(pagos_infrac.monto), sum(pagos_infrac.monto - pagos_infrac.descuento ))  AS totInfrac\n" +
                            "FROM pagos_infrac\n" +
                            "LEFT OUTER JOIN pagos_infraccancel\n" +
                            "ON pagos_infraccancel.idFolio = pagos_infrac.folio WHERE pagos_infraccancel.idFolio IS null AND pagos_infrac.idTurno = '"+idT+"'\n" +
                            ";";
                break;
                case 5://otros_venta
                     sql = "SELECT TRUNCATE(sum(otros_ventadet.cant * otros_ventadet.precio),2) AS totCarg\n" +
                        "FROM otros_venta\n" +
                        "INNER JOIN otros_ventadet\n" +
                        "ON otros_venta.id = otros_ventadet.idVenta AND otros_venta.idCancelacion = 0 AND otros_venta.idTurno = '"+idT+"' \n" +
                        ";";
                break;
               case 6://gastos_caja
                     sql = "SELECT sum(gastos_caja.monto)\n" +
                        "FROM gastos_caja\n" +
                        "WHERE idTurno = '"+idT+"' \n" +
                        ";";
               break;
         }
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0 && !(rs.getString(1) == null) ){
                        nAmb = rs.getString(1);
                    }else{
                        nAmb = "0";
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
           return nAmb;
         }
         
      public String totInfracPend(){
          Connection cn = con2.conexion();
          String nAmb = "";
            String sql = "";
                     sql = "SELECT sum(pagos_infrac.monto) AS totl\n" +
                            "FROM pagos_infrac\n" +
                            "WHERE pagos_infrac.idTurno = 0; "
                            ;
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0 && !(rs.getString(1) == null) ){
                        nAmb = rs.getString(1);
                    }else{
                        nAmb = "0";
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
           return nAmb;
         }//@END totInfracPend
         
         
          public String totalLapsoFechas(int opc,String ribro, String fech1, String fech2){
          Connection cn = con2.conexion();
          String nAmb = "";
            String sql = "";
            switch (opc){
                case 0://pagos_areas
                    if(ribro.isEmpty()){
                        sql = "SELECT sum(pagos_areasdet.importe) AS totl\n"
                                + "FROM pagos_areas\n"
                                + "INNER JOIN pagos_areasdet\n"
                                + "ON pagos_areas.id = pagos_areasdet.idTicket AND pagos_areas.idCancelacion = 0 AND (pagos_areas.fecha >= '" + fech1 + "' AND pagos_areas.fecha <= '" + fech2 + "') \n"
                                + ";";
                    }else{
//                        System.err.println("rubro areas: "+ribro);
          sql = "SELECT sum(pagos_areasdet.importe) AS totl\n"
                 + "FROM pagos_areas\n"
                 + "INNER JOIN pagos_areasdet\n"
 + "ON pagos_areas.id = pagos_areasdet.idTicket  AND pagos_areasdet.idRubroPago = '"+ribro+"' AND pagos_areas.idCancelacion = 0 AND (pagos_areas.fecha >= '" + fech1 + "' AND pagos_areas.fecha <= '" + fech2 + "') \n"
                                + ";";
                    }
                break;
                case 1:// pagos_amb
                 //   System.err.println("Llego rubro Ambul: "+ribro);
                    if(ribro.isEmpty()){
                     sql = "SELECT sum(pagos_ambdet.importe - pagos_ambdet.descuento) AS totl\n" +
                            "FROM pagos_amb\n" +
                            "INNER JOIN pagos_ambdet\n" +
                            "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_amb.idCancelacion = 0 AND (pagos_amb.fecha >= '"+fech1+"' AND pagos_amb.fecha <= '"+fech2+"') \n" +
                            ";";
                    }else{
                        if(ribro.equals("8")){
                     sql = "SELECT sum(pagos_ambdet.importe - pagos_ambdet.descuento) AS totl\n" +
                            "FROM pagos_amb\n" +
                            "INNER JOIN pagos_ambdet\n" +
                            "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_ambdet.idRubropago >= '"+ribro+"' AND pagos_amb.idCancelacion = 0 AND (pagos_amb.fecha >= '"+fech1+"' AND pagos_amb.fecha <= '"+fech2+"') \n" +
                            ";";
                        }else{
                     sql = "SELECT sum(pagos_ambdet.importe - pagos_ambdet.descuento) AS totl\n" +
                            "FROM pagos_amb\n" +
                            "INNER JOIN pagos_ambdet\n" +
                            "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_ambdet.idRubropago = '"+ribro+"' AND pagos_amb.idCancelacion = 0 AND (pagos_amb.fecha >= '"+fech1+"' AND pagos_amb.fecha <= '"+fech2+"') \n" +
                            ";";
                        }
                    }
                break;
                case 2://pagos_carg
                    if(ribro.isEmpty()){
                     sql = "SELECT sum(pagos_cargdet.importe - pagos_cargdet.descuento) AS totCarg\n" +//gdet.importe - pagos_cargdet.descuento) AS totCarg
                            "FROM pagos_cargdet\n" +
                            "INNER JOIN pagos_carg\n" +
                            "ON pagos_carg.id = pagos_cargdet.idTicket  AND pagos_carg.idCancelacion = 0 AND (pagos_carg.fecha >= '"+fech1+"' AND pagos_carg.fecha <= '"+fech2+"') \n" +
                            ";";
                    }else{
                         if(ribro.equals("8")){
                     sql = "SELECT sum(pagos_cargdet.importe - pagos_cargdet.descuento) AS totCarg\n" +
                            "FROM pagos_carg\n" +
                            "INNER JOIN pagos_cargdet\n" +
                            "ON pagos_carg.id = pagos_cargdet.idTicket  AND (pagos_cargdet.idRubropago >= 8 AND pagos_cargdet.idRubropago <= 10) AND pagos_carg.idCancelacion = 0 AND (pagos_carg.fecha >= '"+fech1+"' AND pagos_carg.fecha <= '"+fech2+"') \n" +
                            ";";
                         }
                       if(ribro.equals("11")){
                     sql = "SELECT sum(pagos_cargdet.importe - pagos_cargdet.descuento) AS totCarg\n" +
                            "FROM pagos_carg\n" +
                            "INNER JOIN pagos_cargdet\n" +
                            "ON pagos_carg.id = pagos_cargdet.idTicket  AND pagos_cargdet.idRubropago = '"+ribro+"' AND pagos_carg.idCancelacion = 0 AND (pagos_carg.fecha >= '"+fech1+"' AND pagos_carg.fecha <= '"+fech2+"') \n" +
                            ";";                           
                       }
                    }
                break;
                case 3://pagos_cargrenta
                    if(ribro.isEmpty()){
                     sql = "SELECT sum(pagos_cargrenta.importe) AS totCargR\n" +
                            "FROM pagos_cargrenta\n" +
                            "WHERE pagos_cargrenta.idCancelacion = 0 AND (pagos_cargrenta.fecha  >= '"+fech1+"' AND pagos_cargrenta.fecha  <= '"+fech2+"') \n" +
                            ";";
                    }else{
                        
                    }
                break;
                case 4://pagos_infrac
                     if(ribro.isEmpty()){
                     sql = "SELECT sum(pagos_infrac.monto - pagos_infrac.descuento) \n" + //IF(pagos_infrac.descuento IS NULL, sum(pagos_infrac.monto), sum(pagos_infrac.monto - pagos_infrac.descuento ))  AS totInfrac
                            "FROM pagos_infrac\n" +
                            "LEFT OUTER JOIN pagos_infraccancel\n" +
                            "ON pagos_infraccancel.idFolio = pagos_infrac.folio WHERE pagos_infraccancel.idFolio IS null AND (pagos_infrac.fechapag >= '"+fech1+"' AND pagos_infrac.fechapag <= '"+fech2+"') \n" +
                            ";";
                     }else{
                         
                     }
                break;
                case 5://otros_venta
                     if(ribro.isEmpty()){
                     sql = "SELECT TRUNCATE(sum(otros_ventadet.cant * otros_ventadet.precio),2) AS totCarg\n" +
                        "FROM otros_venta\n" +
                        "INNER JOIN otros_ventadet\n" +
                        "ON otros_venta.id = otros_ventadet.idVenta AND otros_venta.idCancelacion = 0 AND (otros_venta.fecha >= '"+fech1+"' AND otros_venta.fecha <= '"+fech2+"')  \n" +
                        ";";
                     }else{
          sql ="SELECT TRUNCATE(sum(otros_ventadet.cant * otros_ventadet.precio),2) AS totCarg\n" +
"FROM otros_venta\n" +
"INNER JOIN otros_ventadet\n" +
"ON otros_venta.id = otros_ventadet.idVenta AND otros_venta.idCancelacion = 0 AND (otros_venta.fecha >= '"+fech1+"' AND otros_venta.fecha <= '"+fech2+"')\n" +
"INNER JOIN otros_catalogo\n" +
"ON otros_catalogo.id = otros_ventadet.idProd\n" +
"INNER JOIN otros_rubros\n" +
"ON otros_rubros.id = otros_catalogo.idrubro AND otros_rubros.id = '"+ribro+"'\n" +
";";               
                     }
                break;
         }
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0 && !(rs.getString(1) == null) ){
                        nAmb = rs.getString(1);
                    }else{
                        nAmb = "0";
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
           return nAmb;
         }
         
/*TOTAL LAPSO FECHAS AREAS MAS RUBRO*/
          public String totalLapFechAreasRub(int opc,String ribro, String fech1, String fech2){
          Connection cn = con2.conexion();
          String nAmb = "";
            String sql = "";
            switch (opc){
                case 0://pagos_areas
                    if(ribro.isEmpty()){
                     sql = "SELECT sum(pagos_areasdet.importe) AS totl\n" +
                            "FROM pagos_areas\n" +
                            "INNER JOIN pagos_areasdet\n" +
                            "ON pagos_areas.id = pagos_areasdet.idTicket AND pagos_areas.idCancelacion = 0 AND (pagos_areas.fecha >= '"+fech1+"' AND pagos_areas.fecha <= '"+fech2+"') \n" +
                            ";";
                    }else{

                    }
                break;
                case 1:// pagos_amb
                    if(ribro.isEmpty()){
                     sql = "SELECT sum(pagos_ambdet.importe - pagos_ambdet.descuento) AS totl\n" +
                            "FROM pagos_amb\n" +
                            "INNER JOIN pagos_ambdet\n" +
                            "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_amb.idCancelacion = 0 AND (pagos_amb.fecha >= '"+fech1+"' AND pagos_amb.fecha <= '"+fech2+"') \n" +
                            ";";
                    }else{
                        
                    }
                break;
                case 2://pagos_carg
                    if(ribro.isEmpty()){
                     sql = "SELECT sum(pagos_cargdet.importe - pagos_cargdet.descuento) AS totCarg\n" +
                            "FROM pagos_carg\n" +
                            "INNER JOIN pagos_cargdet\n" +
                            "ON pagos_carg.id = pagos_cargdet.idTicket  AND pagos_carg.idCancelacion = 0 AND (pagos_carg.fecha >= '"+fech1+"' AND pagos_carg.fecha <= '"+fech2+"') \n" +
                            ";";
                    }else{
                        
                    }
                break;
                case 3://pagos_cargrenta
                    if(ribro.isEmpty()){
                     sql = "SELECT sum(pagos_cargrenta.importe) AS totCargR\n" +
                            "FROM pagos_cargrenta\n" +
                            "WHERE pagos_cargrenta.idCancelacion = 0 AND (pagos_cargrenta.fecha  >= '"+fech1+"' AND pagos_cargrenta.fecha  <= '"+fech2+"') \n" +
                            ";";
                    }else{
                        
                    }
                break;
                case 4://pagos_infrac
                     if(ribro.isEmpty()){
                     sql = "SELECT sum(pagos_infrac.monto - pagos_infrac.descuento) \n" + //IF(pagos_infrac.descuento IS NULL, sum(pagos_infrac.monto), sum(pagos_infrac.monto - pagos_infrac.descuento ))  AS totInfrac
                            "FROM pagos_infrac\n" +
                            "LEFT OUTER JOIN pagos_infraccancel\n" +
                            "ON pagos_infraccancel.idFolio = pagos_infrac.folio WHERE pagos_infraccancel.idFolio IS null AND (pagos_infrac.fechapag >= '"+fech1+"' AND pagos_infrac.fechapag <= '"+fech2+"') \n" +
                            ";";
                     }else{
                         
                     }
                break;
                case 5://otros_venta
                     if(ribro.isEmpty()){
                     sql = "SELECT TRUNCATE(sum(otros_ventadet.cant * otros_ventadet.precio),2) AS totCarg\n" +
                        "FROM otros_venta\n" +
                        "INNER JOIN otros_ventadet\n" +
                        "ON otros_venta.id = otros_ventadet.idVenta AND otros_venta.idCancelacion = 0 AND (otros_venta.fecha >= '"+fech1+"' AND otros_venta.fecha <= '"+fech2+"')  \n" +
                        ";";
                     }else{
                     }
                break;
         }
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0 && !(rs.getString(1) == null) ){
                        nAmb = rs.getString(1);
                    }else{
                        nAmb = "0";
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
           return nAmb;
         }

          
          public String[] getTarifCargad(){
            Connection cn = con2.conexion();
            String[] idUser = new String[8];
            String sql = "";
            sql = "SELECT * FROM tarifas WHERE rubro = 0; ";
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
                        idUser[7]=rs.getString(8);
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
    }//@end getTarifCargad
               
          
        public String[] getTarifRentCargad(){
            Connection cn = con2.conexion();
            String[] idUser = new String[8];
            String sql = "";
            sql = "SELECT * FROM tarifas WHERE rubro = 2; ";
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
                        idUser[7]=rs.getString(8);
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
    }//@end getTarifCargad
                    
    //metodo para llenar combo de Resgurados vehiculo
         public String[][] getResgVehiculo() {
            Connection cn = con2.conexion();
            int i =0,cantFilas=0, cont=1,cantColumnas=0;
             String[][] mat=null;
            String consul = "SELECT id, descripcion, derechoSemanal from tarifas where rubro = 3 ORDER BY id";
            Statement st = null;
            ResultSet rs = null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(consul);
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
                Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (st != null) {st.close(); }
                    if (cn != null) { cn.close(); }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
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
        }//Llena getResgVehiculo
         
/*      METODOS PARA HACER RPORTES DE JPANEL REPORTES*/
//obtener arr de semana de dia actual
          public String[] getSemanTableAct(String fech){
            Connection cn = con2.conexion();
            String[] idUser = new String[5];
            String sql = "";
            sql = "SELECT * FROM semanas WHERE '"+fech+"' BETWEEN finicial AND ffinal; ";
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
    }//@end getSemanTableAct
         
          //metodo para llenar combo de Resgurados vehiculo
         public String[][] getGastosCajTurn(String fech, String idTurn) {
            Connection cn = con2.conexion();
            int i =0,cantFilas=0, cont=1,cantColumnas=0;
             String[][] mat=null;
            String consul = "";
        if(idTurn.isEmpty()){
        consul = "SELECT gastos_caja.id,gastos_caja.hora,rubroscaja.concepto,gastos_caja.monto\n" +
                "FROM central.gastos_caja\n" +
                "INNER JOIN central.rubroscaja\n" +
                "ON gastos_caja.idRubrocaja = rubroscaja.id AND gastos_caja.fecha = '"+fech+"' ;";
        }else{
        consul = "SELECT gastos_caja.id,gastos_caja.hora,gastos_caja.concepto,gastos_caja.monto\n" +
                "FROM central.gastos_caja\n" +
                "INNER JOIN central.rubroscaja\n" +
                "ON gastos_caja.idRubrocaja = rubroscaja.id AND gastos_caja.idTurno = '"+idTurn+"';";            
        }
            Statement st = null;
            ResultSet rs = null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(consul);
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
                Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (st != null) {st.close(); }
                    if (cn != null) { cn.close(); }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
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
        }//Llena getResgVehiculo
          
               public String cargaConfig() {//List
                File archivo = null;
                FileReader fr = null;
                BufferedReader br = null;
                 String ruta="C:/central/respaldo.txt";//2700 los 4; solo dos: 1850del, 1450traseros, 
                 //List<String> contentL=new ArrayList<String>();
                 String cadena="",rgresa="";  
                try {
                 // Apertura del fichero y creacion de BufferedReader para poder
                 // hacer una lectura comoda (disponer del metodo readLine()).     
                fr = new FileReader(ruta);
                br = new BufferedReader(fr);
                while((cadena = br.readLine())!=null) {
                    //contentL.add(cadena.trim());
                    rgresa=cadena.trim();
                }
                br.close();
                }catch(Exception e){
                 e.printStackTrace();
              }finally{
                 // En el finally cerramos el fichero, para asegurarnos
                 // que se cierra tanto si todo va bien como si salta 
                 // una excepcion.
                 try{                    
                    if( null != fr){   
                       fr.close();     
                    }                  
                 }catch (Exception e2){ 
                    e2.printStackTrace();
                 }
              }
                return rgresa;
    }
         
                       //OBTENER ULTIMO GASTO DE CAJA REALIZADO
          public String getNumReutilizarCargad(){
            Connection cn = con2.conexion();
            String idTurno = "-1";
            String sql = "";
            sql = "SELECT id FROM cargadores_lugares ORDER BY id LIMIT 1; ";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        idTurno = rs.getString(1);
                    }else{
                         idTurno = "-1";
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
    }//@end getNumReutilizarCargad
               

          /*////     OBTENER DATOS DE AMBULANTE para mostrar en panelInfo*/
       public String[][] historPaysAmbs(int quees, String qBusca,String idBusq,String year){
            Connection cn = con2.conexion();
            int i =0,cantFilas=0, cont=1,cantColumnas=0;
             String[][] mat=null;
            String sql = "";
            if(quees == 0){//areas
             //   sql = "select ambulantes.direccion,ambulantes.obs FROM ambulantes WHERE ambulantes.id = '"+id+"';";
                switch(qBusca){
                    case "Mantenimiento":
                        sql = "SELECT semanas.semana,pagos_areas.fecha,pagos_areas.id\n" +
                                "FROM pagos_areas\n" +
                                "INNER JOIN pagos_areasdet\n" +
                                "ON pagos_areas.id = pagos_areasdet.idTicket AND pagos_areas.idCancelacion = 0 AND date_format(pagos_areas.fecha,'%Y') =  '"+year+"' AND pagos_areasdet.idRubropago = 2 AND pagos_areas.idArea = '"+idBusq+"' \n" +
                                "INNER JOIN semanas\n" +
                                "ON pagos_areasdet.idSemana = semanas.id\n" +
                                "order by semanas.id desc\n" +//pagos_areas.fecha
                                ";";
                    break;
                    case "Basura":
                        sql = "SELECT semanas.semana,pagos_areas.fecha,pagos_areas.id\n" +
                                "FROM pagos_areas\n" +
                                "INNER JOIN pagos_areasdet\n" +
                                "ON pagos_areas.id = pagos_areasdet.idTicket AND pagos_areas.idCancelacion = 0 AND date_format(pagos_areas.fecha,'%Y') =  '"+year+"'  AND pagos_areasdet.idRubropago = 3 AND pagos_areas.idArea = '"+idBusq+"'\n" +
                                "INNER JOIN semanas\n" +
                                "ON pagos_areasdet.idSemana = semanas.id\n" +
                                "order by pagos_areas.fecha desc\n" +
                                ";";//order by pagos_amb.fecha desc
                    break;
                    case "Policia":
                        sql = "SELECT semanas.semana,pagos_areas.fecha,pagos_areas.id\n" +
                                "FROM pagos_areas\n" +
                                "INNER JOIN pagos_areasdet\n" +
                                "ON pagos_areas.id = pagos_areasdet.idTicket AND pagos_areas.idCancelacion = 0 AND date_format(pagos_areas.fecha,'%Y') =  '"+year+"'  AND pagos_areasdet.idRubropago = 4 AND pagos_areas.idArea = '"+idBusq+"'\n" +
                                "INNER JOIN semanas\n" +
                                "ON pagos_areasdet.idSemana = semanas.id\n" +
                                "order by pagos_areas.fecha desc\n" +
                                ";";//order by pagos_amb.fecha desc
                    break;
                    case "Resguardo":
                        sql = "SELECT semanas.semana,pagos_areas.fecha,pagos_areas.id\n" +
                                "FROM pagos_areas\n" +
                                "INNER JOIN pagos_areasdet\n" +
                                "ON pagos_areas.id = pagos_areasdet.idTicket AND pagos_areas.idCancelacion = 0 AND date_format(pagos_areas.fecha,'%Y') =  '"+year+"'  AND pagos_areasdet.idRubropago = 5 AND pagos_areas.idArea = '"+idBusq+"'\n" +
                                "INNER JOIN semanas\n" +
                                "ON pagos_areasdet.idSemana = semanas.id\n" +
                                "order by pagos_areas.fecha desc\n" +
                                ";";//order by pagos_amb.fecha desc
                    break;
                };
            }
            
             if(quees == 1){//Ambulantes
                switch(qBusca){
                    case "Semanas":
                        sql = "SELECT semanas.semana,pagos_amb.fecha,pagos_amb.id \n" +
                                "FROM pagos_amb \n" +
                                "INNER JOIN pagos_ambdet\n" +
                                "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_amb.idCancelacion = 0 AND date_format(pagos_amb.fecha,'%Y') = '"+year+"' AND pagos_ambdet.idRubropago = 6 AND pagos_amb.idAmb = '"+idBusq+"' \n" +
                                "INNER JOIN semanas\n" +
                                "ON pagos_ambdet.idSemana = semanas.id\n" +
                                "order by semanas.id DESC;";//pagos_amb.fecha
                    break;
                    case "Resguardos":
                        sql = "SELECT semanas.semana,pagos_amb.fecha,pagos_amb.id\n" +
                            "FROM pagos_amb \n" +
                            "INNER JOIN pagos_ambdet\n" +
                            "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_amb.idCancelacion = 0 AND date_format(pagos_amb.fecha,'%Y') = '"+year+"' AND pagos_ambdet.idRubropago = 7 AND pagos_amb.idAmb = '"+idBusq+"' \n" +
                            "INNER JOIN semanas\n" +
                            "ON pagos_ambdet.idSemana = semanas.id\n" +
                            "GROUP BY pagos_amb.id order by semanas.id desc ;";
                    break;
                    case "Inscripciones":
                        sql = "SELECT semanas.semana,pagos_amb.fecha,pagos_amb.id\n" +
                                "FROM pagos_amb \n" +
                                "INNER JOIN pagos_ambdet\n" +
                                "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_amb.idCancelacion = 0 AND date_format(pagos_amb.fecha,'%Y') = '"+year+"' AND (pagos_ambdet.idRubropago > 7 AND pagos_ambdet.idRubropago < 11) AND pagos_amb.idAmb = '"+idBusq+"' \n" +
                                "INNER JOIN semanas\n" +
                                "ON pagos_ambdet.idSemana = semanas.id\n" +
                                "GROUP BY pagos_amb.id order by semanas.id desc;";//order by pagos_amb.fecha desc
                    break;
                };
            }    
             //    sql = "select cargadores.direccion,cargadores.obs FROM cargadores WHERE cargadores.id = '"+id+"';";
            if(quees == 2){//Cargadores
            //    sql = "select clientes.direccion,clientes.obs FROM clientes WHERE clientes.id = '"+id+"';";
                switch(qBusca){
                    case "Semanas":
                        sql = "SELECT semanas.semana,pagos_carg.fecha,pagos_carg.id\n" +
                                "FROM pagos_carg\n" +
                                "INNER JOIN pagos_cargdet\n" +
                                "ON pagos_carg.id = pagos_cargdet.idTicket AND pagos_carg.idCancelacion = 0 AND date_format(pagos_carg.fecha,'%Y') = '"+year+"' AND pagos_cargdet.idRubropago = 11 AND pagos_carg.idcarg = '"+idBusq+"' \n" +
                                "INNER JOIN semanas\n" +
                                "ON pagos_cargdet.idSemana = semanas.id\n" +
                                "order by semanas.id desc\n" +//pagos_carg.fecha
                                ";";
                    break;
                    case "Inscripciones":
                        sql = "SELECT pagos_cargdet.fvenc,pagos_carg.fecha,pagos_carg.id\n" +
                    "FROM pagos_carg\n" +
                    "INNER JOIN pagos_cargdet\n" +
                    "ON pagos_carg.id = pagos_cargdet.idTicket AND pagos_carg.idCancelacion = 0 AND date_format(pagos_carg.fecha,'%Y') = '"+year+"' AND  (pagos_cargdet.idRubropago > 7 AND pagos_cargdet.idRubropago < 11)  AND pagos_carg.idcarg = '"+idBusq+"' \n" +
                    "ORDER BY pagos_carg.fecha desc\n" +
                    ";";
                    break;
                    case "Rentas":
                        sql = "SELECT pagos_cargrenta.numdiablo,pagos_cargrenta.fecha,pagos_cargrenta.id,pagos_cargrenta.fecharenta\n" +
                "FROM pagos_cargrenta\n" +
                "WHERE pagos_cargrenta.idCancelacion = 0 AND date_format(pagos_cargrenta.fecha,'%Y') = '"+year+"'  AND pagos_cargrenta.idRubropago = 12  AND pagos_cargrenta.idCarg = '"+idBusq+"'\n" +
                "order by pagos_cargrenta.fecha DESC\n" +
                ";";
                    break;
                };
            }
            Statement st = null;
            ResultSet rs= null;
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
                Logger.getLogger(funciones.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
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
    }//@end getAllOtros
       
            public String getidUserConTicket(String colum,String table, String  id){
            Connection cn = con2.conexion();
            String idUser = "";
            String sql = "";
            sql = "SELECT "+colum+" FROM "+table+" WHERE id = '"+id+"'";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        idUser =rs.getString(1);
                    }else{
                            idUser = "NO-DATA";
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
    }//@end getidUserConTicket
       
     //valida si existe ese id en alguna tabla Aun no se usa
         public boolean validainfracCancel(String idBusq){
            Connection cn = con2.conexion();
            boolean existe =false;
            int num=0,i=1;
            String sql = "";
  
                    sql = "SELECT '1' FROM pagos_infraccancel WHERE idFolio = '"+idBusq+"';";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        existe =true;
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
           return existe;
    }      
         
 // OBENER ULTIMO ID DE AMBULANTE PARA MOSTRAR
        public int getUltAmbul(){
            Connection cn = con2.conexion();
            int idUser= -1;
            String sql = "";
            sql = "SELECT id FROM ambulantes ORDER BY id DESC LIMIT 1";
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
    }//@end getUltAmbul
       
        
         // OBENER ULTIMO ID DE TABLA TARIFAS
        public int getUltTarifResg(){
            Connection cn = con2.conexion();
            int idUser= -1;
            String sql = "";
            sql = "SELECT id FROM tarifas ORDER BY id DESC LIMIT 1";
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
    }//@end getUltTarifResg
        
            public String[] getAllDataClis(String id){
                    Connection cn = con2.conexion();
                    String[] idUser = new String[8];
                    String sql = "";
                    sql = "SELECT * FROM clientes WHERE id = '"+id+"'; ";
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
                                idUser[7]=rs.getString(8);
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
        
                      //metodo para llenar las tablas deCONFIGURACION > 
         public String[][] getOthsRub(int opc,String stat,String param) {//if(opc), stat=0||1,param=id&otros_rubros
            Connection cn = con2.conexion();
            int i =0,cantFilas=0, cont=1,cantColumnas=0;
             String[][] mat=null;
            String consul = "";
        if(opc == 0){
            consul = "SELECT * FROM otros_rubros ;";
        }
        if(opc == 1){
            consul = "SELECT otros_catalogo.id,otros_rubros.nombre,otros_catalogo.descrip,otros_catalogo.precio,IF(otros_catalogo.activo = 1,'activo','Inactivo') AS st"
                    + " FROM otros_catalogo"
                    + " INNER JOIN otros_rubros"
                    + " ON otros_rubros.id = otros_catalogo.idrubro";            
        }
        if(opc == 2){
            consul = "SELECT otros_catalogo.id,otros_rubros.nombre,otros_catalogo.descrip,otros_catalogo.precio,IF(otros_catalogo.activo = 1,'activo','Inactivo') AS st"
                    + " FROM otros_catalogo"
                    + " INNER JOIN otros_rubros"
                    + " ON otros_rubros.id = otros_catalogo.idrubro AND otros_rubros.id = '"+param+"' ";            
        }
        if(opc == 3){//busca por eventKeyUp
            consul = "SELECT otros_catalogo.id,otros_rubros.nombre,otros_catalogo.descrip,otros_catalogo.precio,IF(otros_catalogo.activo = 1,'activo','Inactivo') AS st"
                    + " FROM otros_catalogo"
                    + " INNER JOIN otros_rubros"
                    + " ON otros_rubros.id = otros_catalogo.idrubro AND (otros_catalogo.descrip LIKE '"+stat+"%' OR  otros_catalogo.id LIKE '"+stat+"%'); ";       
        }
            Statement st = null;
            ResultSet rs = null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(consul);
                cantColumnas = rs.getMetaData().getColumnCount();
               if(rs.last()){//Nos posicionamos al final
                    cantFilas = rs.getRow();//sacamos la cantidad de filas/registros
                    rs.beforeFirst();//nos posicionamos antes del inicio (como viene por defecto)
                }
                mat = new String[cantFilas][cantColumnas];
            while(rs.next())
                {//es necesario el for para llenar dinamicamente la lista, ya que varia el numero de columnas de las tablas
                      for (int x=1;x<= rs.getMetaData().getColumnCount();x++) {
                             mat[i][x-1]=rs.getString(x);
                      }//for
                       i++;
                }//whilE
            } catch (SQLException ex) {
                Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (st != null) {st.close(); }
                    if (cn != null) { cn.close(); }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
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
        }//Llena getResgVehiculo
          
                               //metodo para llenar las tablas deCONFIGURACION > Rubros Gastos
         public String[][] getRubGastos(int opc,String stat,String param) {//if(opc), stat=0||1,param=id&otros_rubros
            Connection cn = con2.conexion();
            int i =0,cantFilas=0, cont=1,cantColumnas=0;
            String[][] mat=null;
            String consul = "";
            if(opc == 0){
                consul = "SELECT id, concepto FROM rubroscaja ;";
            }
            Statement st = null;
            ResultSet rs = null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(consul);
                cantColumnas = rs.getMetaData().getColumnCount();
               if(rs.last()){//Nos posicionamos al final
                    cantFilas = rs.getRow();//sacamos la cantidad de filas/registros
                    rs.beforeFirst();//nos posicionamos antes del inicio (como viene por defecto)
                }
                mat = new String[cantFilas][cantColumnas];
            while(rs.next())
                {//es necesario el for para llenar dinamicamente la lista, ya que varia el numero de columnas de las tablas
                      for (int x=1;x<= rs.getMetaData().getColumnCount();x++) {
                             mat[i][x-1]=rs.getString(x);
                      }//for
                       i++;
                }//whilE
            } catch (SQLException ex) {
                Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (st != null) {st.close(); }
                    if (cn != null) { cn.close(); }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
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
        }//Llena getResgVehiculo
         
         
    public String[] getOthsRubs(){
            Connection cn = con2.conexion();
             int i =0,cantFilas=0, cont=1,cantColumnas=0;
             String[] idUser = null;
            String sql = "";
            sql = "SELECT nombre FROM otros_rubros;";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
               if(rs.last()){//Nos posicionamos al final
                    cantFilas = rs.getRow();//sacamos la cantidad de filas/registros
                    rs.beforeFirst();//nos posicionamos antes del inicio (como viene por defecto)
                }
                 idUser = new String[cantFilas];
                while(rs.next() && i < cantFilas)
                {
                        idUser[i] = rs.getString(1);
                     i++;
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
         
         //valida si existe ese id en alguna tabla Aun no se usa
         public boolean validaMembresiaInTick(int opc,String idTick,String rub){
            Connection cn = con2.conexion();
            boolean existe =false;
            int num=0,i=1;
            String sql = "";
            switch(opc){
                case 0://cargador
                    sql = "SELECT 1\n" +
                            "FROM pagos_carg\n" +
                            "INNER JOIN pagos_cargdet\n" +
                            "ON pagos_carg.id = pagos_cargdet.idTicket  AND pagos_carg.id = '"+idTick+"'\n" +
                            "AND pagos_cargdet.idRubropago = '"+rub+"'\n" +
                            ";";
                break;
                case 1://ambulante
                    sql = "SELECT 1\n" +
                             "FROM pagos_amb\n"
                            + "INNER JOIN pagos_ambdet\n"
                            + "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_amb.id = '"+idTick+"'\n"
                            + "AND pagos_ambdet.idRubropago = '"+rub+"';";
                break;
            };
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                rs.beforeFirst();
                if(rs.next())
                {
                    if(rs.getRow() > 0){
                        existe =true;
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
           return existe;
    }
    
       public static void main(String args[]){
           funciones aA =  new funciones();
           String[] var = aA.getOthsRubs();

               System.out.println("Valores "+aA.validaMembresiaInTick(0, "108686", "11"));

       }
           
}
