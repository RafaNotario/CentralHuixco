/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickets.Jasper;

import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import conexion.ConexionDBOriginal;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author monit
 */
public class Reportes {
    
        ConexionDBOriginal con2 = new ConexionDBOriginal();

void imprim() throws JRException{
        Connection cn = con2.conexion();
      String  var = "C:/central/src/tickets/Jasper/ticket1.jasper";
      JasperReport reporte = null;


      reporte = (JasperReport) JRLoader.loadObjectFromFile(var);
      JasperPrint jp = JasperFillManager.fillReport(reporte, null, cn);

         JasperPrintManager.printReport(jp, false);
}//@endimrpim


 public void imprim80MM(String param,String[] datas, boolean print){
        Connection cn = con2.conexion();
        String  var = "C:/central/src/tickets/Jasper/ticket80MM.jasper";
        JasperReport reporte = null;
            try {
                 Map parametro = new HashMap();
                parametro.put("numTicket",param);
                parametro.put("paramTotal",datas[0]);
                parametro.put("paramEfectiv",datas[1]);
                parametro.put("paramCambio",datas[2]);
                parametro.put("nombArea",datas[4]);
                parametro.put("nombAtendio",datas[3]);
                
                reporte = (JasperReport) JRLoader.loadObjectFromFile(var);
                JasperPrint jp = JasperFillManager.fillReport(reporte, parametro, cn);

                //linea para mandar a imprimir
                if(print){
                    
                    JasperPrintManager.printReport(jp, false);
                }else{
                    JasperViewer jv = new JasperViewer(jp,false);
                    jv.setZoomRatio(new Float(1.5));
                   jv.setVisible(true);
                   jv.setTitle("Central Huixcolotla");
                }
            
            }  catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                 //   System.out.println( "cierra conexion a la base de datos" );    
                    try {
                        if(cn != null) cn.close();
                    } catch (SQLException ex) {
                        System.err.println( ex.getMessage() );    
                    }
                }
}//@imprim80MM

 /*Generar ticket de Ambulantes */
  public void imprim80MMAmbus(String param,String[] datas, boolean print){
        Connection cn = con2.conexion();
        String  var = "C:/central/src/tickets/Jasper/ticket80MM_Ambu.jasper";
        JasperReport reporte = null;
            try {
                 Map parametro = new HashMap();
                parametro.put("numTicket",param);
                parametro.put("paramTotal",datas[0]);
                parametro.put("paramEfectiv",datas[1]);
                parametro.put("paramCambio",datas[2]);
                parametro.put("nombAmbu",datas[4]);
                parametro.put("nombAtendio",datas[3]);
                
                reporte = (JasperReport) JRLoader.loadObjectFromFile(var);
                JasperPrint jp = JasperFillManager.fillReport(reporte, parametro, cn);

                //linea para mandar a imprimir
                if(print){
                    JasperPrintManager.printReport(jp, false);
                }else{
                    JasperViewer jv = new JasperViewer(jp,false);
                    jv.setZoomRatio(new Float(1.5));
                   jv.setVisible(true);
                   jv.setTitle("Central Huixcolotla \t Pago ambulantes.");
                }
            
            }  catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                 //   System.out.println( "cierra conexion a la base de datos" );    
                    try {
                        if(cn != null) cn.close();
                    } catch (SQLException ex) {
                        System.err.println( ex.getMessage() );    
                    }
                }
}//@imprim80MM

  
   /*Generar ticket de Cargadores */
  public void imprim80MMCargad(String param,String[] datas, boolean print){
        Connection cn = con2.conexion();
        String  var = "C:/central/src/tickets/Jasper/ticket80MM_Cargad.jasper";
        JasperReport reporte = null;
            try {
                 Map parametro = new HashMap();
                parametro.put("numTicket",param);
                parametro.put("paramTotal",datas[0]);
                parametro.put("paramEfectiv",datas[1]);
                parametro.put("paramCambio",datas[2]);
                parametro.put("nombAmbu",datas[4]);
                parametro.put("nombAtendio",datas[3]);
                
                reporte = (JasperReport) JRLoader.loadObjectFromFile(var);
                JasperPrint jp = JasperFillManager.fillReport(reporte, parametro, cn);

                //linea para mandar a imprimir
                if(print){
                    JasperPrintManager.printReport(jp, false);
                }else{
                    JasperViewer jv = new JasperViewer(jp,false);
                    jv.setZoomRatio(new Float(1.5));
                   jv.setVisible(true);
                   jv.setTitle("Central Huixcolotla \t Pago Cargadores.");
                }
            
            }  catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                 //   System.out.println( "cierra conexion a la base de datos" );    
                    try {
                        if(cn != null) cn.close();
                    } catch (SQLException ex) {
                        System.err.println( ex.getMessage() );    
                    }
                }
}//@imprim80MM
//codigos para mandar por parametro datos de consulta

/**** Para obtener total,efectivo y diferencia ticket semanal area **/
          public String[] getUltimPagoarea(String idTicket){
            Connection cn = con2.conexion();
            String[] totalesAreaSem = new String[5]; 
            String sql = "";
            sql = "SELECT pagos_areas.total,pagos_areas.efectivo, pagos_areas.efectivo - pagos_areas.total AS resta, usuarios.nombre, areas.nombre\n" +
                        "FROM pagos_areas\n" +
                        "INNER join areas\n" +
                        "ON pagos_areas.idArea = areas.id AND pagos_areas.id = '"+idTicket+"'\n" +
                        "JOIN turnos\n" +
                        "on pagos_areas.idTurno = turnos.id\n" +
                        "join usuarios\n" +
                        "ON usuarios.id = turnos.idusuario;";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                if(rs.next())
                {
                    totalesAreaSem[0] = rs.getString(1);
                    totalesAreaSem[1] = rs.getString(2);
                    totalesAreaSem[2] = rs.getString(3);
                    totalesAreaSem[3] = rs.getString(4);
                    totalesAreaSem[4] = rs.getString(5);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return totalesAreaSem;
    }//@endgetUltimPagoarea
    
/**** Para obtener total,efectivo y diferencia ticket semanal area **/
          public String[] getTickPagoAmbu(String idTicket){
            Connection cn = con2.conexion();
            String[] totalesAreaSem = new String[5]; 
            String sql = "";
            sql = "SELECT pagos_amb.total,pagos_amb.efectivo, pagos_amb.efectivo - pagos_amb.total AS resta, usuarios.nombre, ambulantes.nombre\n" +
                    "FROM pagos_amb\n" +
                    "INNER join ambulantes\n" +
                    "ON pagos_amb.idAmb = ambulantes.id AND pagos_amb.id = '"+idTicket+"'\n" +
                    "JOIN turnos\n" +
                    "on pagos_amb.idTurno = turnos.id\n" +
                    "join usuarios\n" +
                    "ON usuarios.id = turnos.idusuario;";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                if(rs.next())
                {
                    totalesAreaSem[0] = rs.getString(1);
                    totalesAreaSem[1] = rs.getString(2);
                    totalesAreaSem[2] = rs.getString(3);
                    totalesAreaSem[3] = rs.getString(4);
                    totalesAreaSem[4] = rs.getString(5);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return totalesAreaSem;
    }//@endgetTickPagoAmbu
      
// obtener datos para rellenar ticket de Cargadores
           public String[] getTickPagoCargad(String idTicket){
            Connection cn = con2.conexion();
            String[] totalesAreaSem = new String[5]; 
            String sql = "";
            sql = "SELECT pagos_carg.total,pagos_carg.efectivo, pagos_carg.efectivo - pagos_carg.total AS resta, usuarios.nombre, cargadores.nombre\n" +
                    "FROM pagos_carg\n" +
                    "INNER JOIN cargadores\n" +
                    "ON pagos_carg.idcarg = cargadores.id AND pagos_carg.id = '"+idTicket+"'\n" +
                    "JOIN turnos\n" +
                    "on pagos_carg.idTurno = turnos.id\n" +
                    "join usuarios\n" +
                    "ON usuarios.id = turnos.idusuario;";
            Statement st = null;
            ResultSet rs= null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                if(rs.next())
                {
                    totalesAreaSem[0] = rs.getString(1);
                    totalesAreaSem[1] = rs.getString(2);
                    totalesAreaSem[2] = rs.getString(3);
                    totalesAreaSem[3] = rs.getString(4);
                    totalesAreaSem[4] = rs.getString(5);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                        try {
                            if(cn != null) cn.close();
                        } catch (SQLException ex) {
                            System.err.println( ex.getMessage() );    
                        }
                    }
           return totalesAreaSem;
    }//@endgetTickPagoCargad        
          
public static void main(String []argv) throws JRException{
        Reportes rP = new Reportes();
        String[] dat = rP.getTickPagoCargad("64318");
         rP.imprim80MMCargad("64318", dat,false);
        System.out.println("Y atermino");
    }
    
}
