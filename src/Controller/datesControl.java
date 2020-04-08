/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.toedter.calendar.JDateChooser;
import conexion.ConexionDBOriginal;
import java.text.SimpleDateFormat;
import java.util.Scanner;


/**
 *
 * @author monit
 */
public class datesControl {
    
    static ConexionDBOriginal con2 = new ConexionDBOriginal();
//formato para date sql
    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
    static Scanner teclado = new Scanner(System.in);
    
    SimpleDateFormat formatoPrueba = new SimpleDateFormat("yyyy/MM/dd");
    
public String getFecha(JDateChooser jd){
    if(jd.getDate()!= null){
        return formato.format(jd.getDate());
    }else{
        return null;
    }
}

    
}
