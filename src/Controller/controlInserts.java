/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import conexion.ConexionDBOriginal;
import Controller.datesControl;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author monit
 */
public class controlInserts {

    ConexionDBOriginal con2 = new ConexionDBOriginal();
    static datesControl datCtrl = new datesControl();

/*Metodo que devuelve el ultimo ticket pagado*/
public int regLastTicket(int idT){
    int tic =0;
    Connection cn = con2.conexion();
       
        String ultimo="",consul="";
        int num=0,i=1;
        String sql = "SELECT  pagos_areas.id\n" +
                            "FROM pagos_areas\n" +
                            "INNER JOIN areas\n" +
                            "ON areas.id = pagos_areas.idArea AND pagos_areas.idarea = '"+idT+"'\n" +
                            "ORDER BY pagos_areas.fecha desc limit 1;";
        Statement st = null;
        ResultSet rs= null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            //rs.beforeFirst();
            while(rs.next())
            {
                tic = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                    try {
                        if(cn != null) cn.close();
                    } catch (SQLException ex) {
                        System.err.println( ex.getMessage() );    
                    }
                }
    return tic;
}//FinLastTicket
    
public String[][] regLastSemanasType(int ticket){
        Connection cn = con2.conexion();
          String sql ="",aux;
             sql = "SELECT  rubrospago.id,rubrospago.descripcion,pagos_areasdet.idSemana\n" +
                        "FROM rubrospago\n" +
                        "INNER JOIN pagos_areasdet\n" +
                        "ON pagos_areasdet.idRubroPago = rubrospago.id AND pagos_areasdet.idTicket = '"+ticket+"'\n" +
                        "group by rubrospago.id;";      
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
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
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
}//Fin ulimos pagos

/*Metodo que devuelve el ultimo ticket pagado*/
public String[] regSemanas(int idS){
    int tic =0;
    Connection cn = con2.conexion();
        String[] arr = new String[5];
        String sql = "SELECT * FROM semanas WHERE id = '"+idS+"';";
        Statement st = null;
        ResultSet rs= null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            //rs.beforeFirst();
            while(rs.next())
            {
                arr[0] = rs.getString(1);
                arr[1] = rs.getString(2);
                arr[2] = rs.getString(3);
                arr[3] = rs.getString(4);
                arr[4] = rs.getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                    try {
                        if(cn != null) cn.close();
                    } catch (SQLException ex) {
                        System.err.println( ex.getMessage() );    
                    }
                }
    return arr;
}//FinRegSemanas

/*---***  SEGUNDA FORMA PARA CARGAR ULTIMO PAGO AUNQUESEA DISTINTO TICKET*/
/*Metodo que devuelve el ultimo ticket pagado y segun campo devuelto mostramos el jpanel*/
public String[] regOpsareas(int idA){
    int tic =0;
    Connection cn = con2.conexion();
        String[] arr = new String[4];
        String sql = "SELECT areas.mantSem,areas.basura,areas.policia,areas.otros FROM areas WHERE id = '"+idA+"';";
        Statement st = null;
        ResultSet rs= null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
           // rs.beforeFirst();
            while(rs.next())
            {
                arr[0] = rs.getString(1);
                arr[1] = rs.getString(2);
                arr[2] = rs.getString(3);
                arr[3] = rs.getString(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                    try {
                        if(cn != null) cn.close();
                    } catch (SQLException ex) {
                        System.err.println( ex.getMessage() );    
                    }
                }
    return arr;
}//FinRegOpsareas

public int regLastTicket2(int idA,int idcuo){
    int tic =0;
    Connection cn = con2.conexion();    
        String ultimo="",consul="";
        int num=0,i=1;
        String sql = "SELECT  pagos_areas.id\n" +
                            "FROM pagos_areas\n" +
                            "INNER JOIN areas\n" +
                            "ON areas.id = pagos_areas.idArea AND pagos_areas.idarea = '"+idA+"'\n" +
                            "INNER JOIN\n" +
                            "pagos_areasdet\n" +
                            "ON pagos_areas.id = pagos_areasdet.idTicket AND pagos_areasdet.idRubroPago = '"+idcuo+"'\n" +
                            "ORDER BY pagos_areas.id desc limit 1;";
        Statement st = null;
        ResultSet rs= null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
          //  rs.beforeFirst();
            while(rs.next())
            {
                tic = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                    try {
                        if(cn != null) cn.close();
                        
                    } catch (SQLException ex) {
                        System.err.println( ex.getMessage() );    
                    }
                }
    return tic;
}//FinLastTicket

public String[] regPaysAreasdet(int idT, int idrub){
    int tic =0;
    Connection cn = con2.conexion();
       
        String[] arr = new String[5];
        String sql ="";
       
        sql = "SELECT * FROM pagos_areasdet WHERE pagos_areasdet.idTicket = '"+idT+"' AND idRubroPago = '"+idrub+"'; ";
        
        Statement st = null;
        ResultSet rs= null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
           // rs.beforeFirst();
            while(rs.next())
            {
                arr[0] = rs.getString(1);
                arr[1] = rs.getString(2);
                arr[2] = rs.getString(3);
                arr[3] = rs.getString(4);
                arr[4] = rs.getString(5);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                    try {
                        
                        if(cn != null) cn.close();
                    } catch (SQLException ex) {
                        System.err.println( ex.getMessage() );    
                    }
                }
    return arr;
}//FinRegpagosAreasdet

public String[] regpagosambdet(int idT, int idrub){
    int tic =0;
    Connection cn = con2.conexion();
        String[] arr = new String[8];
        String sql ="";
        sql = "SELECT * FROM pagos_ambdet WHERE pagos_ambdet.idTicket = '"+idT+"' AND idRubropago = '"+idrub+"' ;";
        Statement st = null;
        ResultSet rs= null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
           // rs.beforeFirst();
            while(rs.next())
            {
                arr[0] = rs.getString(1);
                arr[1] = rs.getString(2);
                arr[2] = rs.getString(3);
                arr[3] = rs.getString(4);
                arr[4] = rs.getString(5);
                arr[5] = rs.getString(6);
                arr[6] = rs.getString(7);
                arr[7] = rs.getString(8);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
                }
    return arr;
}//Finregpagosambdet

public void guardaTicketArea(String[] param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        

                SQL="INSERT INTO pagos_areas (id,idTurno,fecha,hora,idArea,total,efectivo) VALUES (?,?,?,?,?,?,?)";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.setString(6, param[5]);
                pps.setString(7, param[6]);
                
                pps.executeUpdate();
                //JOptionPane.showMessageDialog(null, "Pedido guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
    
} //guardaTicketArea

public void guardadetailTicketArea(String[] param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        
                SQL="INSERT INTO pagos_areasdet (idTicket,item,idSemana,idRubroPago,importe) VALUES (?,?,?,?,?)";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.executeUpdate();
            //    JOptionPane.showMessageDialog(null, "Pedido guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@endguardadetailTicketArea

//regresa matrizde vista tickets del dia
        public String[][] matrizgetTicketsDia(String fech, String idTurno){
        Connection cn = con2.conexion();
          String sql ="",aux;
          if(idTurno.isEmpty()){
              sql = "(SELECT  pagos_areas.id,DATE_FORMAT(pagos_areas.hora, \"%H : %i\") AS hor,'Pago Areas',areas.nombre,pagos_areas.total\n" +
                        "FROM pagos_areas\n" +
                        "INNER JOIN areas\n" +
                        "ON areas.id = pagos_areas.idArea AND pagos_areas.fecha = '"+fech+"'\n" +
                        "ORDER BY pagos_areas.id DESC)\n" +
                        "UNION\n" +
                        "(SELECT pagos_amb.id,DATE_FORMAT(pagos_amb.hora, \"%H : %i\") AS hor,'Pago Ambulantes',ambulantes.nombre,pagos_amb.total\n" +
                        "FROM pagos_amb\n" +
                        "INNER JOIN ambulantes\n" +
                        "ON ambulantes.id = pagos_amb.idAmb AND pagos_amb.fecha = '"+fech+"'\n" +
                        "ORDER BY pagos_amb.id DESC)\n" +
                        "UNION\n" +
                        "(SELECT  pagos_carg.id,DATE_FORMAT(pagos_carg.hora, \"%H : %i\") AS hor,'Pago Cargadores',cargadores.nombre,pagos_carg.total\n" +
                        "FROM pagos_carg\n" +
                        "INNER JOIN cargadores\n" +
                        "ON cargadores.id = pagos_carg.idcarg AND pagos_carg.fecha = '"+fech+"'\n" +
                        "ORDER BY pagos_carg.id DESC)\n" +
                        "UNION\n" +
                        "(SELECT  pagos_infrac.folio,DATE_FORMAT(pagos_infrac.horapag, \"%H : %i\") AS hor,'Pago Infraccion',pagos_infrac.quienpaga,pagos_infrac.monto - pagos_infrac.descuento\n" +
                        "FROM pagos_infrac\n" +
                        "WHERE pagos_infrac.fechapag = '"+fech+"')\n" +
                        "UNION\n" +
                        "(SELECT otros_venta.id,DATE_FORMAT(otros_venta.hora, \"%H : %i\") AS hor,\n" +
                        "IF(otros_venta.tipoPersona = 0,'Varios Amb.',IF(otros_venta.tipoPersona = 1,'Varios Carg.', IF(otros_venta.tipoPersona = 2,'Varios Cte.','NADON') ) ) AS quees,\n" +
                        "IF(otros_venta.tipoPersona = 0, (SELECT ambulantes.nombre FROM ambulantes WHERE ambulantes.id = otros_venta.idPersona ) ,IF(otros_venta.tipoPersona = 1,(SELECT cargadores.nombre FROM cargadores WHERE cargadores.id = otros_venta.idPersona ), IF(otros_venta.tipoPersona = 2,(SELECT clientes.nombre from clientes WHERE clientes.id = otros_venta.idPersona),'NADON') ) ) AS namquees,\n" +
                        "        otros_venta.efectivo\n" +
                        "FROM otros_venta\n" +
                        "WHERE otros_venta.fecha = '"+fech+"'\n" +
                        ")\n" +
                        "UNION\n" +
                        "(SELECT  pagos_cargrenta.id,DATE_FORMAT(pagos_cargrenta.hora, \"%H : %i\") AS hor,'Pago Renta Carg',cargadores.nombre,pagos_cargrenta.importe\n" +
                        "FROM pagos_cargrenta\n" +
                        "INNER JOIN cargadores\n" +
                        "ON pagos_cargrenta.idCarg = cargadores.id AND pagos_cargrenta.fecha = '"+fech+"' ORDER BY pagos_cargrenta.id DESC)\n" +
                        ";";  
          }
          if(fech.isEmpty()){
                      sql = "(SELECT pagos_areas.id,DATE_FORMAT(pagos_areas.hora, \"%H : %i\") AS hor,'Pago Areas',areas.nombre,pagos_areas.total\n" +
                            "FROM pagos_areas\n" +
                            "INNER JOIN areas\n" +
                            "ON areas.id = pagos_areas.idArea AND  pagos_areas.idTurno = "+idTurno+"\n" +
                            "ORDER BY pagos_areas.id DESC)\n" +
                            "UNION\n" +
                            "(SELECT pagos_amb.id,DATE_FORMAT(pagos_amb.hora, \"%H : %i\") AS hor,'Pago Ambulantes',ambulantes.nombre,pagos_amb.total\n" +
                            "FROM pagos_amb\n" +
                            "INNER JOIN ambulantes\n" +
                            "ON ambulantes.id = pagos_amb.idAmb AND  pagos_amb.idTurno = "+idTurno+"\n" +
                            "ORDER BY pagos_amb.id DESC)\n" +
                            "UNION\n" +
                            "(SELECT pagos_carg.id,DATE_FORMAT(pagos_carg.hora, \"%H : %i\") AS hor,'Pago Cargadores',cargadores.nombre,pagos_carg.total\n" +
                            "FROM pagos_carg\n" +
                            "INNER JOIN cargadores\n" +
                            "ON cargadores.id = pagos_carg.idcarg AND  pagos_carg.idTurno = "+idTurno+"\n" +
                            "ORDER BY pagos_carg.id DESC)\n" +
                            "UNION\n" +
                            "(SELECT pagos_infrac.folio,DATE_FORMAT(pagos_infrac.horapag, \"%H : %i\") AS hor,'Pago Infraccion',pagos_infrac.quienpaga,pagos_infrac.monto - pagos_infrac.descuento\n" +
                            "FROM pagos_infrac\n" +
                            "WHERE pagos_infrac.idTurno = "+idTurno+")\n" +
                            "UNION\n" +
                            "(SELECT otros_venta.id,DATE_FORMAT(otros_venta.hora, \"%H : %i\") AS hor,\n" +
                            "IF(otros_venta.tipoPersona = 0,'Varios Amb.',IF(otros_venta.tipoPersona = 1,'Varios Carg.', IF(otros_venta.tipoPersona = 2,'Varios Cte.','NADON') ) ) AS quees,\n" +
                            "IF(otros_venta.tipoPersona = 0, (SELECT ambulantes.nombre FROM ambulantes WHERE ambulantes.id = otros_venta.idPersona ) ,IF(otros_venta.tipoPersona = 1,(SELECT cargadores.nombre FROM cargadores WHERE cargadores.id = otros_venta.idPersona ), IF(otros_venta.tipoPersona = 2,(SELECT clientes.nombre from clientes WHERE clientes.id = otros_venta.idPersona),'NADON') ) ) AS namquees,\n" +
                            "        otros_venta.efectivo\n" +
                            "FROM otros_venta\n" +
                            "WHERE  otros_venta.idTurno = "+idTurno+"\n" +
                            ")\n" +
                            "UNION\n" +
                            "(SELECT pagos_cargrenta.id,DATE_FORMAT(pagos_cargrenta.hora, \"%H : %i\") AS hor,'Pago Renta Carg',cargadores.nombre,pagos_cargrenta.importe\n" +
                            "FROM pagos_cargrenta\n" +
                            "INNER JOIN cargadores\n" +
                            "ON pagos_cargrenta.idCarg = cargadores.id  AND pagos_cargrenta.idTurno = "+idTurno+"\n" +
                            "ORDER BY pagos_cargrenta.id DESC\n" +
                            ");";
                      }
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
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
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
}//@endmatrizgetTicketsDia
        
/*Prueba obtener ultimos 10 pago de semana ambulante*/
//regresa matrizde vista tickets del dia
        public String[][] matrizgetAmbuSemana(String idAmbu, String idRubro){
        Connection cn = con2.conexion();
          String sql ="",aux;
              sql = "SELECT pagos_amb.*\n" +//obtine todos los campos
                        "FROM pagos_amb\n" +
                        "INNER JOIN ambulantes\n" +
                        "ON ambulantes.id = pagos_amb.idAmb AND pagos_amb.idAmb = '"+idAmbu+"'\n" +
                        "INNER JOIN pagos_ambdet\n" +
                        "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_ambdet.idRubroPago = '"+idRubro+"'\n" +
                        "ORDER BY pagos_amb.id desc limit 10;";      
              
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
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
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

///*****METODOS PARA AMBULANTES
   public String getpagosAmbulante(String idAmbu, String idRubro){
        Connection cn = con2.conexion();
        String regresaTick = "";
        String sql = "SELECT pagos_amb.id\n" +
                        "FROM pagos_amb\n" +
                        "INNER JOIN ambulantes\n" +
                        "ON ambulantes.id = pagos_amb.idAmb AND pagos_amb.idAmb = '"+idAmbu+"'\n" +
                        "INNER JOIN pagos_ambdet\n" +
                        "ON pagos_amb.id = pagos_ambdet.idTicket AND pagos_ambdet.idRubroPago = '"+idRubro+"'\n" +
                        "ORDER BY pagos_amb.id desc limit 1;";
        Statement st = null;
        ResultSet rs= null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                regresaTick = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                    try {
                        if(st != null) st.close();                
                        if(cn !=null) cn.close();
                       if(rs !=null) rs.close();
                       
                    } catch (SQLException ex) {
                        System.err.println( ex.getMessage() );    
                    }
                }
    return regresaTick;
}//FinRegpagosAmbulante

public void guardaTicketAmbus(String[] param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        

                SQL="INSERT INTO pagos_amb (id,idTurno,fecha,hora,idAmb,total,efectivo) VALUES (?,?,?,?,?,?,?)";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.setString(6, param[5]);
                pps.setString(7, param[6]);
                
                pps.executeUpdate();
            //    JOptionPane.showMessageDialog(null, "Ticket Ambulante guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@endguardaTicketAmbus
  
public void guardadetailTicketAmbus(String[] param, int numParam){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";
            if(numParam == 6)
                SQL="INSERT INTO pagos_ambdet (idTicket,item,idRubroPago,idSemana,importe,descuento) VALUES (?,?,?,?,?,?)";                           
            
            if(numParam == 8)
                SQL="INSERT INTO pagos_ambdet (idTicket,item,idRubroPago,idSemana,importe,descuento,finicio,fvenc) VALUES (?,?,?,?,?,?,?,?)";                           
           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.setString(6, param[5]);
          if(numParam == 8){
              pps.setString(7, param[6]);
              pps.setString(8, param[7]);
          }
                
                pps.executeUpdate();
              //  JOptionPane.showMessageDialog(null, "Detail payAmbu guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@endguardadetailTicketAmbus


       
//metodo para actualizar idResg de ambullantes
       public void f5idResgAmbu(String id, String val, String opcColumn){
             Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        
                SQL="UPDATE ambulantes SET "+opcColumn+" =? WHERE id = '"+id+"' ";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, val);
                pps.executeUpdate();
             //   JOptionPane.showMessageDialog(null, "idresg actualizado correctamente."+id);
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,"C.I.-f5idResgAmbu"+ex.getMessage() );    
                    }
            }//finally catch
        }

public void guardaTicketCargad(String[] param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        
                SQL="INSERT INTO pagos_carg (id,idTurno,fecha,hora,idcarg,total,efectivo) VALUES (?,?,?,?,?,?,?)";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.setString(6, param[5]);
                pps.setString(7, param[6]);
                
                pps.executeUpdate();
              // JOptionPane.showMessageDialog(null, "Ticket Cargador guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@endguardaTicketCargad

public void guardadetailTicketCargad(String[] param, int numParam){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";
            if(numParam == 6)
                SQL="INSERT INTO pagos_cargdet (idTicket,item,idRubropago,idSemana,importe,descuento) VALUES (?,?,?,?,?,?)";                           
            
            if(numParam == 8)
                SQL="INSERT INTO pagos_cargdet (idTicket,item,idRubroPago,idSemana,importe,descuento,finicio,fvenc) VALUES (?,?,?,?,?,?,?,?)";                           
           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.setString(6, param[5]);
          if(numParam == 8){
              pps.setString(7, param[6]);
              pps.setString(8, param[7]);
          }
                
                pps.executeUpdate();
             //   JOptionPane.showMessageDialog(null, "Detail payCarg guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@endguardadetailTicketCargad
       
/*-----------++++++++++METODO PARA GUARDAR PAGO DE OTROS vENTA*/
public void guardaTicketOtherPays(String[] param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        
                SQL="INSERT INTO otros_venta (id,idTurno,fecha,hora,tipoPersona,idPersona,efectivo) VALUES (?,?,?,?,?,?,?)";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.setString(6, param[5]);
                pps.setString(7, param[6]);
                
                pps.executeUpdate();
            //    JOptionPane.showMessageDialog(null, "Ticket OtroPago guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
        }     //@end guardaTicketOtherPays
            
// ------ ++ GUARDAR CLIENTE NUEVO
public void guardCliente(String[] param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";
            SQL="INSERT INTO clientes (id,nombre,direccion,correo,telefono,rfc,obs) VALUES (?,?,?,?,?,?,?)";                           
          try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.setString(6, param[5]);
                pps.setString(7, param[6]);

                pps.executeUpdate();
          //      JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@end guardCliente

public void guardadetailOthsPays(List<String> param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";
            SQL="INSERT INTO otros_ventadet (idVenta,idProd,item,cant,precio) VALUES (?,?,?,?,?)";                           
          try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param.get(0));
                pps.setString(2, param.get(1));
                pps.setString(3, param.get(2));
                pps.setString(4, param.get(3));
                pps.setString(5, param.get(4));

                pps.executeUpdate();
         //       JOptionPane.showMessageDialog(null, "Detail payOths guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@end guardadetailOthsPays


//metodo para actualizar idResg de ambullantes
       public void f5postGuardCarg(String id, String val, String opcColumn){
             Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        
                SQL="UPDATE cargadores SET "+opcColumn+" =? WHERE id = '"+id+"' ";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, val);
                pps.executeUpdate();
          //      JOptionPane.showMessageDialog(null, "ultima sem vig actualizado correctamente."+id);
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,"C.I.-f5postGuardCarg"+ex.getMessage() );    
                    }
            }//finally catch
        }//@endf5postGuardCarg
       
       //metodo para guardar nueva infraccion
       public void guardaInfracc( List<String> param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        
                SQL="INSERT INTO pagos_infrac (folio,fecha,tipoDoc,documento,motivo,vehiculo,obs,idagente,monto) VALUES (?,?,?,?,?,?,?,?,?)";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param.get(0));
                pps.setString(2, param.get(1));
                pps.setString(3, param.get(2));
                pps.setString(4, param.get(3));
                pps.setString(5, param.get(4));
                pps.setString(6, param.get(5));
                pps.setString(7, param.get(6));
                pps.setString(8, param.get(7));
                pps.setString(9, param.get(8));
                pps.executeUpdate();
          //      JOptionPane.showMessageDialog(null, "Infraccion creada correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@endguardaInfracc
       
       public void actualizaInfrac(List<String> param,String id){
             Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        
SQL="UPDATE pagos_infrac SET folio = ?, fecha = ?, tipodoc = ?,documento = ?, motivo = ?, vehiculo = ?, obs = ?, idagente = ?,monto = ? WHERE folio = '"+id+"'; ";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param.get(0));
                pps.setString(2, param.get(1));
                pps.setString(3, param.get(2));
                pps.setString(4, param.get(3));
                pps.setString(5, param.get(4));
                pps.setString(6, param.get(5));
                pps.setString(7, param.get(6));
                pps.setString(8, param.get(7));
                pps.setString(9, param.get(8));
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Infraccion actualizada correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
        }//@end
       
       public void elimaRow(String table,String campo,String id){
            Connection cn = con2.conexion();
            PreparedStatement preparedStmt = null;
            if(!id.isEmpty()){
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "<html> "
                        + "Seguro que desea eliminar el registro con ID:<h1> "+id+" </h1>? </html>","Eliminar",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                try {
                String query = "delete from "+table+" where "+campo+" = '"+id+"' ";
                preparedStmt = cn.prepareStatement(query);
                preparedStmt.execute();
                JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
            } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    if(preparedStmt != null) preparedStmt.close();                
                    if(cn !=null) cn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }//catch
            }//finally 
                } else {
                    JOptionPane.showMessageDialog(null,"No se borro el registro: "+id);
                }
            }else
                JOptionPane.showMessageDialog(null,"Sin data a eliminar");
}
       
       /*METODO PARA ACTUALIZAR DATOS DE INFRACCIONAL HACER UN PAGO*/ 
              public void payInfracc(List<String> param,String fol){
             Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        
SQL="UPDATE pagos_infrac SET idTurno = ?, fechapag = ?, horapag = ?,quienpaga = ?, descuento = ?, efectivo = ? WHERE folio = '"+fol+"'; ";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param.get(0));
                pps.setString(2, param.get(1));
                pps.setString(3, param.get(2));
                pps.setString(4, param.get(3));
                pps.setString(5, param.get(4));
                pps.setString(6, param.get(5));
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Pago de infraccion realizado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
        }//@endpayInfracc
             
              
     public void guardOthsCatalog(String[] param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";
            SQL="INSERT INTO otros_catalogo (id,idrubro,descrip,precio,activo) VALUES (?,?,?,?,?)";                           
          try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, param[2]+" guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@end guardadetailOthsPays
              
//metodo para actualizar las cancelaciones en pagos_areas, pagos_amb,pagos_carg,otros_venta
       public void f5CancelTypesAll(String table,String opcColumn,String id, String val){
             Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        
                SQL="UPDATE "+table+" SET "+opcColumn+" =? WHERE id = '"+id+"' ";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, val);
                pps.executeUpdate();
     //           JOptionPane.showMessageDialog(null, "ultima semo vig actualizado correctamente."+id);
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,"C.I.-f5postGuardCarg"+ex.getMessage() );    
                    }
            }//finally catch
        }//@end f5CancelTypesAll
     
     public void guardInCancelaciones(String[] param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";
            SQL="INSERT INTO cancelaciones (id,idUsuario,autorizo,idTurno,fecha,hora,motivo) VALUES (?,?,?,?,?,?,?)";                           
          try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[1]);
                pps.setString(4, param[2]);
                pps.setString(5, param[3]);
                pps.setString(6, param[4]);
                pps.setString(7, param[5]);
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, param[5]+" guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@end guardInCancelaciones
     
     //guarda gasto de caja
     public void guardGastoCaja(String[] param, String idT){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="",band="";      
            try {
          if(idT.isEmpty()){
 SQL="INSERT INTO gastos_caja (id,idTurno,fecha,hora,idRubrocaja,concepto,solicitante,obs,monto) VALUES (?,?,?,?,?,?,?,?,?)";                           
  pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.setString(6, param[5]);
                pps.setString(7, param[6]);
                pps.setString(8, param[7]);
                pps.setString(9, param[8]);
                band = "creado";
          }else{
SQL="UPDATE gastos_caja SET concepto=?,solicitante=?,obs=?,monto=? WHERE id = '"+idT+"'; ";              
pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                band="actualizado";
          }      
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Gasto "+band+" correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@end guardGastoCaja
     
     ////   GUARDAR PAGO DE RENTA DIABLO CARGADORES
     /*-----------++++++++++METODO PARA GUARDAR PAGO DE OTROS vENTA*/
public void guardTickRentCarg(String[] param){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="";        
                SQL="INSERT INTO pagos_cargrenta (id,idTurno,fecha,hora,idCarg,idRubropago,fecharenta,numdiablo,importe) VALUES (?,?,?,?,?,?,?,?,?)";                           
            try {
                pps = cn.prepareStatement(SQL);
                pps.setString(1, param[0]);
                pps.setString(2, param[1]);
                pps.setString(3, param[2]);
                pps.setString(4, param[3]);
                pps.setString(5, param[4]);
                pps.setString(6, param[5]);
                pps.setString(7, param[6]);
                pps.setString(8, param[7]);
                pps.setString(9, param[8]);
                
                pps.executeUpdate();
             //   JOptionPane.showMessageDialog(null, "Ticket Renta Cargador guardado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
        }     //@end guardTickRentCarg
       
     //guarda Ambulante o actualiza MAMALONA LOGIC FUCK
     public void guardF5Ambu(List<String> param, String idT){
     Connection cn = con2.conexion();
            PreparedStatement pps=null;
            String SQL="",band="";      
            try {
          if(idT.isEmpty()){
 SQL="INSERT INTO ambulantes (nombre,direccion,telefono,obs,idGiro,idTarifa,idResg,condMemb,condDerecho,condResg,ultimaSem) VALUES (?,?,?,?,?,?,?,?,?,?,?)";                           
  pps = cn.prepareStatement(SQL);
                pps.setString(1, param.get(0));
                pps.setString(2, param.get(1));
                pps.setString(3, param.get(2));
                pps.setString(4, param.get(3));
                pps.setString(5, param.get(4));
                pps.setString(6, param.get(5));
                pps.setString(7, param.get(6));
                pps.setString(8, param.get(7));
                pps.setString(9, param.get(8));
                pps.setString(10, param.get(9));
                pps.setString(11, param.get(10));
                band = "creado";
          }else{
SQL="UPDATE ambulantes SET nombre=?,direccion=?,telefono=?,obs=?,idGiro=?,idTarifa=?,idResg=?,condMemb=?,condDerecho=?,condResg=? WHERE id = '"+idT+"'; ";              
pps = cn.prepareStatement(SQL);
                pps.setString(1, param.get(0));
                pps.setString(2, param.get(1));
                pps.setString(3, param.get(2));
                pps.setString(4, param.get(3));
                pps.setString(5, param.get(4));
                pps.setString(6, param.get(5));
                pps.setString(7, param.get(6));
                pps.setString(8, param.get(7));
                pps.setString(9, param.get(8));
                pps.setString(10, param.get(9));
                band="actualizado";
          }      
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Ambulante "+band+" correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(controlInserts.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error durante la transaccion.");
            }finally{
 //               System.out.println( "cierra conexion a la base de datos" );    
                try {
                    if(pps != null) pps.close();                
                    if(cn !=null) cn.close();
                    } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,ex.getMessage() );    
                    }
            }//finally catch
} //@end guardF5Ambu

    public static void main(String []argv){
        controlInserts contrl = new controlInserts();
         System.out.println("Ultimo pagado: "+contrl.regLastTicket(19));
       // String[][] mat = contrl.matrizgetAmbuSemana("258","6");
        String[] arr = contrl.regpagosambdet(16865,6);
        for (int i = 0; i < arr.length; i++) {
       //     System.out.println("["+arr[i]+"]");
    }
        contrl.f5CancelTypesAll("usuarios","turno","2","930");
    // System.out.println(contrl.getpagosAmbulante("258","6"));
}

}