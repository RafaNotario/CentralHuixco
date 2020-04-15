/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import conexion.ConexionDBOriginal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

 
/**
 *
 * @author monit
 */
public class datesControl {
    
    static ConexionDBOriginal con2 = new ConexionDBOriginal();
//formato para date sql
    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
    
    SimpleDateFormat formatoPrueba = new SimpleDateFormat("yyyy/MM/dd");
    
    public Date StringDate(String fecha){//tenia: java.util.Date
    //    SimpleDateFormat formato_texto = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaE = null;
        try{
            fechaE = formato.parse(fecha);
            return fechaE;
        }catch(ParseException ex){
            return null;
        }
}
   
    public String getFechaCal(JCalendar jd){
    if(jd.getDate()!= null){
        return formato.format(jd.getDate());
    }else{
        return null;
    }
}
    
public String getFecha(JDateChooser jd){
    if(jd.getDate()!= null){
        return formato.format(jd.getDate());
    }else{
        return null;
    }
}

    private Date cargafecha() {
        Date fechaAct = new Date();
        return fechaAct;
    }
    
public String setDateActual(){
//        DateFormat df = DateFormat.getDateInstance();
    Date fechaAct = new Date();   
//        jDateChooser1.setDate(fechaAct);
        return formato.format(fechaAct);
}

public int semanYear(String dat,int band){
    Calendar calendar = Calendar.getInstance();
    calendar.setFirstDayOfWeek( Calendar.MONDAY);//MONDAY
   calendar.setMinimalDaysInFirstWeek(1);
    calendar.setTime(StringDate(dat));
    
    int  ret=0;
    if(band == 0){//regresa numero de semana en el año
        //calendar.setFirstDayOfWeek( Calendar.SUNDAY);//MONDAY
        ret = calendar.get(Calendar.WEEK_OF_YEAR);      
    }
    if(band == 1){//regresa numero de dia se la semana
       calendar.setFirstDayOfWeek( 0);//MONDAY
        ret = calendar.get(Calendar.DAY_OF_WEEK);        
    } 
    //System.out.printf("%s %n", DayOfWeek.MONDAY);//
    
    return ret;
}

    public String numSemanaLocal(){
        String letrero = "Semana ";
        Locale l = new Locale("es","MX");//"es","MX" 
       DateFormat dfE = DateFormat.getDateInstance(DateFormat.FULL, l);//instancia para escribir fechotas jajaja
       Calendar cF = Calendar.getInstance(l);
           
        System.out.println("WeekofYear : "+ cF.get(Calendar.WEEK_OF_YEAR)+" Calendar"+Calendar.SUNDAY);
        return letrero + cF.get(Calendar.WEEK_OF_YEAR);
    }
    
    public void jLocalFechas (String fech){
//       DateFormat dfE = DateFormat.getDateInstance(DateFormat.FULL, l);//instancia para escribir fechotas jajaja
       //DayOfWeek lunes = DayOfWeek.MONDAY; 
       Locale l = new Locale("es","MX");//"es","MX" 
       DateFormat dfE = DateFormat.getDateInstance(DateFormat.FULL, l);//instancia para escribir fechotas jajaja
       Calendar cF = Calendar.getInstance(Locale.FRANCE);
      System.out.println("first dayWeek:" + cF.getFirstDayOfWeek());
       cF.setFirstDayOfWeek(Calendar.MONDAY);
      System.out.println("first dayWeek2:" + cF.getMinimalDaysInFirstWeek());
       
       cF.setTime(StringDate(fech));
        // 
        //
        
       System.out.println("TextStyle.FULL:" + cF.getDisplayName(Calendar.MONTH,Calendar.SHORT, l) + " "+cF.get(Calendar.DAY_OF_MONTH)); 
      // System.out.println("TextStyle.NARROW:" + lunes.getDisplayName(TextStyle.NARROW, l)); 
       //System.out.println("TextStyle.SHORT:" + lunes.getDisplayName(TextStyle.SHORT, l)); 
       
       System.out.println("FORMATOTE: "+dfE.format(StringDate(fech)));//new Date()
       
       System.out.println("DayWeek: "+ cF.get(Calendar.DAY_OF_WEEK) );
       
       System.out.println("WeekofYear : "+ cF.get(Calendar.WEEK_OF_YEAR)+" Calendar"+Calendar.SUNDAY);
       
       cF.add(Calendar.WEEK_OF_YEAR, 1);
       
       System.out.println("WeekofYear +1: "+ cF.get(Calendar.WEEK_OF_YEAR));
        }
    
    public String getWeekStartDate(String fech) {
         Locale l = new Locale("es","MX");//"es","MX" 
        Calendar calendar = Calendar.getInstance();
       // calendar.setTime(StringDate(fech));
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
        }
    //return calendar.getTime();
        return calendar.get(Calendar.DAY_OF_MONTH)+ ". "+calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT, l) ;
    }

public String getWeekEndDate(String fech) {
    Locale l = new Locale("es","MX");
    Calendar calendar = Calendar.getInstance(l);
    //calendar.setTime(StringDate(fech));
    System.out.println("Dia es: "+ Calendar.DAY_OF_WEEK +"Llegra"+ Calendar.MONDAY);
    
    if(calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
    {
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        calendar.add(Calendar.DATE, -1);
    }else {
        calendar.add(Calendar.DATE,  6);
    }    
    //return calendar.getTime();
    return calendar.get(Calendar.DAY_OF_MONTH) + " "+calendar.getDisplayName(Calendar.MONTH,Calendar.SHORT, l) +". "+calendar.get(Calendar.YEAR);
}

//funcion que dado un numero devuelva lapso de semana
public String lapsoSem(String fech,int numSem){
    Calendar c = Calendar.getInstance();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.setMinimalDaysInFirstWeek(1);
    String var = "";
    int mes =0;
    String mesU ="";
/*
    c.clear();
    c.set(Calendar.YEAR, 2014);
    c.set(Calendar.WEEK_OF_YEAR, 1);
    System.out.printf("Fecha del Lunes: %te-%<tm-%<ty%n", c.getTime());
    System.out.printf("Semana del mes (%tB %<tY): %d%n", c.getTime(), c.get(Calendar.WEEK_OF_MONTH));
    c.add(Calendar.DAY_OF_YEAR, 6);
    System.out.printf("Fecha del Domingo: %te-%<tm-%<ty%n", c.getTime());
    System.out.printf("Semana del mes (%tB %<tY): %d%n", c.getTime(), c.get(Calendar.WEEK_OF_MONTH));
*/
    c.clear();
    c.set(Calendar.YEAR, 2020);
    c.set(Calendar.WEEK_OF_YEAR, 13);
    mes =c.get(Calendar.MONTH);
    mes+=1;
    if(Integer.toString(mes).length() ==1){
        mesU="0"+Integer.toString(mes);
    }else{
        mesU=Integer.toString(mes);
    }
    var= Integer.toString(c.get(Calendar.DAY_OF_MONTH))+"/"+mesU+"/"+Integer.toString(c.get(Calendar.YEAR));
   // System.out.printf("Fecha del Lunes: %te-%<tm-%<ty %n", c.getTime());
 System.out.println("quedo "+var);
    // System.out.printf("Semana del mes (%tB %<tY): %d%n", c.getTime(), c.get(Calendar.WEEK_OF_YEAR));
    c.add(Calendar.DAY_OF_YEAR, 6);
    //System.out.printf("Fecha del Domingo: %te - %<tm - %<ty %n", c.getTime());
    //System.out.printf("Semana del mes (%tB %<tY): %d%n", c.getTime(), c.get(Calendar.WEEK_OF_YEAR));
    return var;
}
    
    public static void main(String argv[]){
        datesControl dC = new datesControl();
        
//        System.out.println("Semana del año es: "+dC.semanYear("2020/04/13",0)+"\t dia de la semana: "+dC.semanYear("2020/04/13",1));
          
    //    dC.jLocalFechas("2020/04/13");
        System.out.println("PRUEBAS DE STACKOVERFLOW");
        System.out.println("Semana ini"+dC.getWeekStartDate("2020/03/23"));
        System.out.println("Semana fin"+dC.getWeekEndDate("2020/03/23"));
        System.out.println("Prue"+dC.numSemanaLocal());
       // dC.lapsoSem("",0);
        
    }//finMain

}//finClass
/*
run:
THURSDAY
THURSDAY
Semana del año es: 16	 dia de la semana: 2
*/
