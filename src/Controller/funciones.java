/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import conexion.ConexionDBOriginal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author monit
 */
public class funciones {
    
     ConexionDBOriginal con2 = new ConexionDBOriginal();  
            public boolean validaLoginUsers(String user, String pass){
            Connection cn = con2.conexion();
            boolean existe =false;
            int num=0,i=1;
            String sql = "";
            sql = "SELECT '1' FROM usuarios WHERE usuario = '"+user+"' AND password = '"+pass+"'";
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
    }//validaloginUsers
    
    public void limpiar(JPanel Pn)
    {
        Pn.removeAll();
        Pn.validate();
        Pn.repaint();
    }
            
           public static void main(String args[]){
             //  funciones fn =  new funciones();
              
            //System.out.println(fn.validaLoginUsers("root", "priia08"));
           }
}
