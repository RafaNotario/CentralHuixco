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
import javax.swing.JOptionPane;

/**
 *
 * @author monit
 */
public class controlInserts {

    ConexionDBOriginal con2 = new ConexionDBOriginal();


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
            rs.beforeFirst();
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

    public static void main(String []argv){
        controlInserts contrl = new controlInserts();
         System.out.println("Ultimo pagado: "+contrl.regLastTicket(19));
         
        String[][] mat = contrl.regLastSemanasType(contrl.regLastTicket(19));
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                      System.out.print("["+mat[i][j]+"] ");
            }
            System.err.println();
        }
       
        
    }

}
