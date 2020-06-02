/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickets.Jasper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import conexion.ConexionDBOriginal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author monit
 */
public class Reportes {
    
        ConexionDBOriginal con2 = new ConexionDBOriginal();
        JButton b1 = new JButton("CerrarTurno");
        
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
                parametro.put("fechHorArea",datas[5]);//Hora y fecha de ticket no del sistema
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
                parametro.put("fechHorTicAmb",datas[5]);
                
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
                parametro.put("fecHorTicCarg",datas[5]);
                
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

    /*Generar ticket de otros_pagos */
  public void imprim80MMOthers(String param,String[] datas, boolean print){
        Connection cn = con2.conexion();
        String  var = "C:/central/src/tickets/Jasper/ticket80MM_Others.jasper";
        JasperReport reporte = null;
            try {
                 Map parametro = new HashMap();
                parametro.put("numTicket",param);
                parametro.put("paramTotal",datas[0]);
                parametro.put("fechcomp",datas[1]);
                parametro.put("horComp",datas[2]);
                parametro.put("nombAtendio",datas[3]);
                parametro.put("whoes",datas[4]);
                parametro.put("nombAmbu",datas[5]);
                
                reporte = (JasperReport) JRLoader.loadObjectFromFile(var);
                JasperPrint jp = JasperFillManager.fillReport(reporte, parametro, cn);

                //linea para mandar a imprimir
                if(print){
                    JasperPrintManager.printReport(jp, false);
                }else{
                    JasperViewer jv = new JasperViewer(jp,false);
                    jv.setZoomRatio(new Float(1.5));
                   jv.setVisible(true);
                   jv.setTitle("Central Huixcolotla \t Pago otros rubros.");
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
  
  
//imprimir tickets de infracciones
   public void imprim80MM_Infrac(String param, boolean print){
        Connection cn = con2.conexion();
        String  var = "C:/central/src/tickets/Jasper/ticket80MM_Infraccion.jasper";
        JasperReport reporte = null;
            try {
                 Map parametro = new HashMap();
                parametro.put("numTicket",param);
                
                reporte = (JasperReport) JRLoader.loadObjectFromFile(var);
                JasperPrint jp = JasperFillManager.fillReport(reporte, parametro, cn);

                //linea para mandar a imprimir
                if(print){
                    
       JasperPrintManager.printReport(jp, false);//imprimir sin mostrar cuadro de dialogo
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
}//@endimprim80MM_Infrac

 public void imprim80MM_CargRent(String param,boolean print){
        Connection cn = con2.conexion();
        String  var = "C:/central/src/tickets/Jasper/ticket80MM_RentCarg.jasper";
        JasperReport reporte = null;
            try {
                 Map parametro = new HashMap();
                parametro.put("numTicket",param);
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
}//@imprim80MM_CargRent
 
 // REPORTE PARA CORTE DE CAJA
  public void imprim80MM_corteCaja(String param, boolean print){
        Connection cn = con2.conexion();
        String  var = "C:/central/src/tickets/Jasper/ticket80MM_CorteCaja.jasper";
        JasperReport reporte = null;
            try {
                 Map parametro = new HashMap();
                parametro.put("fechCorte",param);
                reporte = (JasperReport) JRLoader.loadObjectFromFile(var);
                JasperPrint jp = JasperFillManager.fillReport(reporte, parametro, cn);

                net.sf.jasperreports.swing.JRViewer jv = new net.sf.jasperreports.swing.JRViewer(jp);
                JFrame  jf = new JFrame();
                
           jf.getContentPane().setLayout(new BorderLayout());//FlowLayout()
           jf.getContentPane().add(jv,BorderLayout.CENTER);
//           jf.getContentPane().setSize();

           jf.add(b1,BorderLayout.SOUTH);
          b1.setBounds(0,0, 97, 25);
          b1.setBackground(Color.CYAN);
           jf.validate();
           jf.setVisible(true);
           jf.setSize(new Dimension(800,600));
           jf.setLocation(300,100);
           jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           jf.setTitle("CORTE DE CAJA");
                
                //linea para mandar a imprimir
  /*              if(print){
                    
                    JasperPrintManager.printReport(jp, false);
                }else{
                    JasperViewer jv = new JasperViewer(jp,false);
                    jv.setZoomRatio(new Float(1.5));
                   jv.setVisible(true);
                   jv.setTitle("Central Huixcolotla");
                }
     */       
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
}//@end imprim80MM_corteCaja

//codigos para mandar por parametro datos de consulta
/**** Para obtener total,efectivo y diferencia ticket semanal area **/
          public String[] getUltimPagoarea(String idTicket){
            Connection cn = con2.conexion();
            String[] totalesAreaSem = new String[6]; 
            String sql = "";
            sql = "SELECT pagos_areas.total,pagos_areas.efectivo, pagos_areas.efectivo - pagos_areas.total AS resta, usuarios.nombre, areas.nombre,CONCAT(date_format(pagos_areas.fecha,'%d-%m-%Y'),' ',date_format(pagos_areas.hora,'%H : %i')  )\n" +
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
                    totalesAreaSem[5] = rs.getString(6);
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
            String[] totalesAreaSem = new String[6]; 
            String sql = "";
            sql = "SELECT pagos_amb.total,pagos_amb.efectivo, pagos_amb.efectivo - pagos_amb.total AS resta, usuarios.nombre, ambulantes.nombre,CONCAT(date_format(pagos_amb.fecha,'%d-%m-%Y'),' ',date_format(pagos_amb.hora,'%H : %i')  )\n" +
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
                    totalesAreaSem[5] = rs.getString(6);
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
            String[] totalesAreaSem = new String[6]; 
            String sql = "";
            sql = "SELECT pagos_carg.total,pagos_carg.efectivo, pagos_carg.efectivo - pagos_carg.total AS resta, usuarios.nombre, cargadores.nombre,CONCAT(date_format(pagos_carg.fecha,'%d-%m-%Y'),' ',date_format(pagos_carg.hora,'%H : %i')  )\n" +
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
                    totalesAreaSem[5] = rs.getString(6);
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
           
/**** Para obtener total,efectivo y diferencia ticket semanal area **/
          public String[] getTickOthers(String idTicket){
            Connection cn = con2.conexion();
            String[] totalesAreaSem = new String[6]; 
            String sql = "";
            sql = "SELECT otros_venta.efectivo,date_format(otros_venta.fecha,'%d-%m-%Y') AS fech, DATE_FORMAT(otros_venta.hora, \"%H:%i\") AS hor, usuarios.nombre,\n" +
                    "IF(otros_venta.tipoPersona = 0,'Ambulante',IF(otros_venta.tipoPersona = 1,'Cargador', IF(otros_venta.tipoPersona = 2,'Cliente','NADON') ) ) AS whoes,\n" +
                    "IF(otros_venta.tipoPersona = 0, (SELECT ambulantes.nombre FROM ambulantes WHERE ambulantes.id = otros_venta.idPersona ) ,IF(otros_venta.tipoPersona = 1,(SELECT cargadores.nombre FROM cargadores WHERE cargadores.id = otros_venta.idPersona ), IF(otros_venta.tipoPersona = 2,(SELECT clientes.nombre from clientes WHERE clientes.id = otros_venta.idPersona),'NADON') ) ) AS namquees\n" +
                    "FROM otros_venta\n" +
                    "JOIN turnos\n" +
                    "on otros_venta.idTurno = turnos.id AND otros_venta.id = '"+idTicket+"'\n" +
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
                    totalesAreaSem[5] = rs.getString(6);
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
    }//@end getTickOthers
          
public static void main(String []argv) throws JRException{
        Reportes rP = new Reportes();
        String[] dat = rP.getTickOthers("2");
        rP.imprim80MM_corteCaja("2020-03-25",false);
        System.out.println("Y atermino");
    }
    
}
