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
import java.sql.Connection;

/**
 *
 * @author monit
 */
public class Reportes {
    
        ConexionDBOriginal con2 = new ConexionDBOriginal();

    void imprim() throws JRException{
        Connection cn = con2.conexion();
    //  FileInputStream inputStream = null;
      //   inputStream = new FileInputStream("C:/central/src/tickets/Jasper/ticket1.jasper");
      String  var = "C:/central/src/tickets/Jasper/ticket1.jasper";
      JasperReport reporte = null;
      reporte = (JasperReport) JRLoader.loadObjectFromFile(var);
      JasperPrint jp = JasperFillManager.fillReport(reporte, null, cn);

         JasperPrintManager.printReport(jp, false);
        
            
 

    }//@endimrpim
    
    
    public static void main(String []argv) throws JRException{
        Reportes rP = new Reportes();
        rP.imprim();
        System.out.println("Y atermino");
    }
    
}
