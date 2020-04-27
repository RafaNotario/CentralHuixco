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
       
        String sql = "SELECT * FROM pagos_areasdet WHERE pagos_areasdet.idTicket = '"+idT+"' AND idRubroPago = '"+idrub+"'; ";
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
                JOptionPane.showMessageDialog(null, "Pedido guardado correctamente.");
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
                JOptionPane.showMessageDialog(null, "Pedido guardado correctamente.");
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
        public String[][] matrizgetTicketsDia(String fech){
        Connection cn = con2.conexion();
          String sql ="",aux;
              sql = "SELECT  pagos_areas.id,DATE_FORMAT(pagos_areas.hora, \"%H : %i\") AS hor,'Pago semanal de area',areas.nombre,pagos_areas.total\n" +
                        "FROM pagos_areas\n" +
                        "INNER JOIN areas\n" +
                        "ON areas.id = pagos_areas.idArea AND pagos_areas.fecha = '"+fech+"'\n" +
                        "ORDER BY pagos_areas.id DESC;";      
              
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

    public static void main(String []argv){
        controlInserts contrl = new controlInserts();
         System.out.println("Ultimo pagado: "+contrl.regLastTicket(19));
         
        String[][] mat = contrl.regLastSemanasType(contrl.regLastTicket(19));
        String[] arr = contrl.regPaysAreasdet(1,3);
        
        for (int i = 0; i < arr.length; i++) {
           /* for (int j = 0; j < mat[0].length; j++) {
                      System.out.print("["+mat[i][j]+"] ");
            if(i >2)
                    System.out.println(datCtrl.getWeekStartDate(arr[i]));
                else
            }*/
                System.out.println(arr[i]);
        }
       
        
    }

}
