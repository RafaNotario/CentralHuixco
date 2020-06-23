/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.internos.jpanels;

import java.text.SimpleDateFormat;
import Controller.datesControl;
import Controller.funciones;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import conexion.ConexionDBOriginal;
import Controller.controlInserts;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import renderTable.TModel;
import tickets.Jasper.Reportes;
import Interfaz.internos.jpanels.infracc_rw_DB;
import Interfaz.vistaReimprAll;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Calendar;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



/**
 *
 * @author Antonio R. Notario
 */
public class internoCaja extends javax.swing.JPanel {
    String User;
    String fech = "",fechCarg ="";
    datesControl datCtrl = new datesControl();
    ConexionDBOriginal con2 = new ConexionDBOriginal();
    controlInserts contrl = new controlInserts();
    funciones func = new funciones();
    Reportes rP = new Reportes();
    infracc_rw_DB infraccs= null;
    
     List<String> conten = new ArrayList<String>();//lista para guardar el id de cada area cobro de areas
     List<String> contenRubOths = new ArrayList<String>();
     List<String> contenRubGastos = new ArrayList<String>();      
     int bandC = 0,
           idSemMantini=0,//contador para ultimas semanas pagadas
           idSemBasura=0,
           idSemPolicia=0,
           idSemResguard=0,
           idSemambu=0,
           idResguardAmbu=0,idResguardAmbu2=0,
           idSemCargad=0
           ;
     
String[] cabAreasPays = {"# Ticket", "Hora", "Concepto", "Nombre", "Monto"},
        cabInfraccionesView = {"Folio", "Fecha","Placa/Licencia", "Vehiculo","Importe"},
        tarifas=null,//para tener las tarifas a cobrar a los ambulnates
        datasCarg = null,//para tener los datos a usar de cargador, trifas, diablo popio, descuentos
        infoUser = null,
        datasAmbUltSem
        ;

String[][] matResgVehiculo = null;//Matriz para cargar las tarifas de resguardo a ambulantes

SimpleDateFormat formatoPrueba = new SimpleDateFormat("dd/MM/yyyy");

public internoCaja(String usuarioN) {
        initComponents();
     this.User = usuarioN;
//        JOptionPane.showMessageDialog(null,"recibi user: "+User);
     infoUser = func.getnombreUsuario(Integer.parseInt(usuarioN));//obtener array de usuario logueado
     
     jButton2.doClick();
     jPanTableBusqView.setVisible(false);
     jPanRentCargador.setVisible(false);
     jPanCargadorInscr.setVisible(true);
     jPanSemCargad.setVisible(true);
     
     llenacombogetAreas();
     inhabilitaAreas();//llena la ,matriz de vista dia de idTruno
     AutoCompleteDecorator.decorate(jCmBxgetAreas);
     
     jCmBxgetAreas.setEditable(true);
    jCmBxgetAreas.requestFocus();
   // jDateChoInscripcion.getJCalendar().setMinSelectableDate(datCtrl.cargafecha());
/*Ambulantes*/    
    inicaComboAmbu();
//vista general para corte de caja
jCBBusqTicketall.setSelectedIndex(0);    
/*Gstos Caja*/
llenacombogetcuentaGasto();
mostrarGastosDia("",infoUser[6]);

//Cargadores fechas renta
dCCFechIniRentCarg.setDateFormat(formatoPrueba);
dCCFechFinRentCarg.setDateFormat(formatoPrueba);


jCmBxgetAreas.getEditor().getEditorComponent().addKeyListener(
        new KeyAdapter(){
        @Override
        public void keyPressed (java.awt.event.KeyEvent e){         
            int var = e.getKeyCode();
               if(var == KeyEvent.VK_ENTER ){
                    mostrarJpanNew();
                    bandC =0;
             }
               
                if(var==KeyEvent.VK_F5){
                    jButton15.doClick();
                }
            
        
    }} );

/*   
               this.addKeyListener(new KeyListener(){
                   @Override
              public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_F5){
                    JOptionPane.showMessageDialog(null, "Has pulsado Enter");
                }
           
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
            }
            public void keyTyped(KeyEvent e){
                //Aqui no funcionara
            }

            public void keyReleased(KeyEvent e){
                JOptionPane.showMessageDialog(null, "oprimio");
            }
        });
*/
 }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jDialCalendarMantenim = new javax.swing.JDialog();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jButton42 = new javax.swing.JButton();
        jFramePays = new javax.swing.JFrame();
        jPanel27 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTotCobro = new javax.swing.JTextField();
        txtEfect = new javax.swing.JTextField();
        txtCambio = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jFramCobroInfrac = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabPlaca = new javax.swing.JLabel();
        jLabFolio = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNamePagad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtDcto = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        txtCambioInfrac = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jFramExplanadaCob = new javax.swing.JFrame();
        jLabel32 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtFolFinExplanad = new javax.swing.JTextField();
        txtFolIniExplanad = new javax.swing.JTextField();
        txtFoltotExplanad = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtCoastExplan = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jFramAltaCliente = new javax.swing.JFrame();
        jLabel43 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtNombClient = new javax.swing.JTextField();
        txtDirClient = new javax.swing.JTextField();
        txtCorrClient = new javax.swing.JTextField();
        txtObsClient = new javax.swing.JTextField();
        txtTelClient = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtRfcClient = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jDialresProdOther = new javax.swing.JDialog();
        jLabel68 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        txtDescripProd = new javax.swing.JTextField();
        txtPrec = new javax.swing.JTextField();
        jCBRubroProd = new javax.swing.JComboBox<>();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jDialCancelaciones = new javax.swing.JDialog();
        jLabel80 = new javax.swing.JLabel();
        txtCancelTick = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jDialAltaGastos = new javax.swing.JDialog();
        jPanel16 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        txtConcept = new javax.swing.JTextField();
        txtsolict = new javax.swing.JTextField();
        txtObservs = new javax.swing.JTextField();
        txtMontoGasto = new javax.swing.JTextField();
        jCombBTypeRubros = new javax.swing.JComboBox<>();
        jButton39 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanInterncoborstivk = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jCBBusqTicketall = new javax.swing.JComboBox<>();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        txtBusqTickAll = new javax.swing.JTextField();
        jDatChoFechBusqtick = new com.toedter.calendar.JDateChooser();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTabviewPays = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jPanInternGastos = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTabViewGastos = new javax.swing.JTable();
        jLabel81 = new javax.swing.JLabel();
        jCombBOpcBusqGastos = new javax.swing.JComboBox<>();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        txtBusGasto = new javax.swing.JTextField();
        jDateChoGastos = new com.toedter.calendar.JDateChooser();
        jButton44 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanambulantes = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jCheckResguardAmb = new javax.swing.JCheckBox();
        jLabel49 = new javax.swing.JLabel();
        jLabUltimaResguardPay = new javax.swing.JLabel();
        txtIdResguardOcultoAmb = new javax.swing.JTextField();
        txtResgIniAmb = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jButMinusResgSemfin = new javax.swing.JButton();
        txtResgFinAmb = new javax.swing.JTextField();
        jButMostSemsPaysAmb2 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabContSemsResguard = new javax.swing.JLabel();
        jLabImporteResguard = new javax.swing.JLabel();
        jLabTarifaResguard = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabDstoResguard = new javax.swing.JLabel();
        jButMinusResgPaysAmb = new javax.swing.JButton();
        jButMostSemsPaysAmb1 = new javax.swing.JButton();
        jLaFechIniResguard = new javax.swing.JLabel();
        jLabAumResguard = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jCBoxResguardosOpc = new javax.swing.JComboBox<>();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jCheckInscripPaysAmb = new javax.swing.JCheckBox();
        jLabel60 = new javax.swing.JLabel();
        jLabVigenciaView = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabDstoInscripcion = new javax.swing.JLabel();
        jLabImporteInscripcion = new javax.swing.JLabel();
        jLabTarifaInscripcion = new javax.swing.JLabel();
        jDateChoInscripcion = new com.toedter.calendar.JDateChooser();
        jCBoxDuracInscripc = new javax.swing.JComboBox<>();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jCheckSemPaysAmb = new javax.swing.JCheckBox();
        jLabel71 = new javax.swing.JLabel();
        jLabUltimaSemanaPay = new javax.swing.JLabel();
        txtIdSemOcultoAmb = new javax.swing.JTextField();
        txtSeminiAmb = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jButMinusSemsPaysAmb = new javax.swing.JButton();
        txtSemFinAmb = new javax.swing.JTextField();
        jButMostSemsPaysAmb = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabContadorSemanas = new javax.swing.JLabel();
        jLabImporteSemanas = new javax.swing.JLabel();
        jLabTarifaSemanas = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabDstoSemanas = new javax.swing.JLabel();
        jLaFechIniSemana = new javax.swing.JLabel();
        jLabAumentaSemanas = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanDataambView1 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabNomAmb = new javax.swing.JLabel();
        jLabDirecc1 = new javax.swing.JLabel();
        jLabGiro1 = new javax.swing.JLabel();
        jLabObserva1 = new javax.swing.JLabel();
        jLabIdAmbu = new javax.swing.JLabel();
        jPanTableBusqView = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTabDatosAmbulante = new javax.swing.JTable();
        txtBuscAmbulante = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        txtResultAmbu = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jPanCargadores = new javax.swing.JPanel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jPanRentCargador = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabCondonacRentDiab = new javax.swing.JLabel();
        jLabTarifRentCarg = new javax.swing.JLabel();
        jLabImportRentDiab = new javax.swing.JLabel();
        txtnumDaysrentdiab = new javax.swing.JTextField();
        txtNumDiabRent = new javax.swing.JTextField();
        dCCFechIniRentCarg = new datechooser.beans.DateChooserCombo();
        dCCFechFinRentCarg = new datechooser.beans.DateChooserCombo();
        jPanCargadorInscr = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jSeparator8 = new javax.swing.JSeparator();
        jCkBoxInscripcion = new javax.swing.JCheckBox();
        jLabel53 = new javax.swing.JLabel();
        jLabVigencia = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabImportInscripc = new javax.swing.JLabel();
        jLabCoastTarifa = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabDescto = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jComBInscripc = new javax.swing.JComboBox<>();
        jDatChoIncripcion = new com.toedter.calendar.JDateChooser();
        jPanSemCargad = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        jCkBoxSemanas = new javax.swing.JCheckBox();
        jLabel100 = new javax.swing.JLabel();
        jLabnumSem = new javax.swing.JLabel();
        txtOcultiDSemana = new javax.swing.JTextField();
        txtcontSemsFin = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabcontSemanas = new javax.swing.JLabel();
        jLabImportSemanasCargad = new javax.swing.JLabel();
        jLabtarifSemanas = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabDtoSemanas = new javax.swing.JLabel();
        jButMinusSems = new javax.swing.JButton();
        jButMooreSems = new javax.swing.JButton();
        txtcontSemsIni = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jLabIniSemsViews = new javax.swing.JLabel();
        jLabFinSemsViews = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jPanDataambView2 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabNombreCargadores = new javax.swing.JLabel();
        jLabDirecCargad = new javax.swing.JLabel();
        jLabGiroCarg = new javax.swing.JLabel();
        jlabIdCargador = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jLabObservaCarg = new javax.swing.JTextArea();
        jPanTableBusqView1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTabCargadoresView = new javax.swing.JTable();
        txtBuscCargadores = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        txtTotalCarg = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jButton37 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jPanVarios = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        jLayeredPanConcept = new javax.swing.JLayeredPane();
        jPanvIEWcOAST = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel140 = new javax.swing.JLabel();
        jLabConceptName = new javax.swing.JLabel();
        txtIdOculto = new javax.swing.JTextField();
        jLabel142 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jButton34 = new javax.swing.JButton();
        txtCantidadOths = new javax.swing.JTextField();
        txtPreciooths = new javax.swing.JTextField();
        txtImportOths = new javax.swing.JTextField();
        jPanTableBusqView3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTabViewOtherProd = new javax.swing.JTable();
        jLayeredPanePerson = new javax.swing.JLayeredPane();
        jPanDataambView3 = new javax.swing.JPanel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jLabThisIs = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jLabNombre3 = new javax.swing.JLabel();
        jLabObserva3 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabFoli = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jLabDirecc3 = new javax.swing.JTextArea();
        jPanTableBusqView2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTabVariosView = new javax.swing.JTable();
        txtBusqOtros = new javax.swing.JTextField();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        txtTotOthers = new javax.swing.JTextField();
        jLabel165 = new javax.swing.JLabel();
        jButton49 = new javax.swing.JButton();
        jLabel166 = new javax.swing.JLabel();
        jButton50 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTabVistaVentaOthers = new javax.swing.JTable();
        jButton51 = new javax.swing.JButton();
        jLabel167 = new javax.swing.JLabel();
        txtBusqConcept = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanTransitoInfraccion = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jCBoxFilterBusq = new javax.swing.JComboBox<>();
        txtInFilterBusq = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTabInfraccView = new javax.swing.JTable();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel120 = new javax.swing.JLabel();
        jLabtotInfracc = new javax.swing.JLabel();
        jPanAreascobros = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCmBxgetAreas = new javax.swing.JComboBox<>();
        jPanMantenPay = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jChecMantSem = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabLastMantenim = new javax.swing.JLabel();
        txtMantenIdSem = new javax.swing.JTextField();
        txtIniManten = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButMantenSubstract = new javax.swing.JButton();
        txtFinManten = new javax.swing.JTextField();
        jLaFechFinManten = new javax.swing.JLabel();
        jButMantenMoore = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabTarifaMantenim = new javax.swing.JLabel();
        jlabImportMantenim = new javax.swing.JLabel();
        jLabSemsPaysManten = new javax.swing.JLabel();
        jLaFechIniManten = new javax.swing.JLabel();
        jPanBasuraContenPays = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jChecBasura = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jLabLastBasura = new javax.swing.JLabel();
        txtIniBasura = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabTarifaBasura = new javax.swing.JLabel();
        jlabImportBasura = new javax.swing.JLabel();
        jLabSemsPaysBasura = new javax.swing.JLabel();
        jLaFechFinBasura = new javax.swing.JLabel();
        jButBasuraMoore = new javax.swing.JButton();
        txtFinBasura = new javax.swing.JTextField();
        jButBasuraSubstract = new javax.swing.JButton();
        jLaFechIniBasura = new javax.swing.JLabel();
        txtBasurIdSem = new javax.swing.JTextField();
        jPanPiliceContenPays = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jChecPolicia = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        jLabLastPolicia = new javax.swing.JLabel();
        txtIniPolic = new javax.swing.JTextField();
        jLaFechIniPolicia = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButPolicSubstract = new javax.swing.JButton();
        txtFinPolicia = new javax.swing.JTextField();
        jLaFechFinPolicia = new javax.swing.JLabel();
        jButPolicMoore = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabTarifPolicia = new javax.swing.JLabel();
        jlabImportPolicia = new javax.swing.JLabel();
        jLabSemsPaysPolis = new javax.swing.JLabel();
        txtPoliciaIdSem = new javax.swing.JTextField();
        jPanResguardContenPays = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jChecResguardo = new javax.swing.JCheckBox();
        jLabel35 = new javax.swing.JLabel();
        jLabLastResguardo = new javax.swing.JLabel();
        txtResguardIdSem = new javax.swing.JTextField();
        txtIniResg = new javax.swing.JTextField();
        jLaFechIniResguardos = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButResguardSubstarct = new javax.swing.JButton();
        txtFinResguard = new javax.swing.JTextField();
        jLaFechFinResguard = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabTarifResguard = new javax.swing.JLabel();
        jlabImportResguard = new javax.swing.JLabel();
        jLabSemsPaysResg = new javax.swing.JLabel();
        jButResguardMoore = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        txtResultSum = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jButton45 = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");
        jPopupMenu1.add(jMenuItem1);

        jDialCalendarMantenim.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialCalendarMantenim.setTitle("Elija semana");
        jDialCalendarMantenim.setResizable(false);
        jDialCalendarMantenim.setSize(new java.awt.Dimension(450, 280));
        jDialCalendarMantenim.setType(java.awt.Window.Type.UTILITY);

        jCalendar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCalendar1.setDecorationBordersVisible(true);
        jCalendar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCalendar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCalendar1MouseClicked(evt);
            }
        });

        jButton42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton42.setText("Seleccionar");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialCalendarMantenimLayout = new javax.swing.GroupLayout(jDialCalendarMantenim.getContentPane());
        jDialCalendarMantenim.getContentPane().setLayout(jDialCalendarMantenimLayout);
        jDialCalendarMantenimLayout.setHorizontalGroup(
            jDialCalendarMantenimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialCalendarMantenimLayout.createSequentialGroup()
                .addGroup(jDialCalendarMantenimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialCalendarMantenimLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialCalendarMantenimLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jDialCalendarMantenimLayout.setVerticalGroup(
            jDialCalendarMantenimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialCalendarMantenimLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jDialCalendarMantenim.getAccessibleContext().setAccessibleParent(jLaFechFinManten);

        jFramePays.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jFramePays.setTitle("Cobrar");
        jFramePays.setMinimumSize(new java.awt.Dimension(400, 300));
        jFramePays.setType(java.awt.Window.Type.UTILITY);

        jLabel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total");
        jLabel3.setOpaque(true);

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Efectivo");
        jLabel6.setOpaque(true);

        jLabel10.setBackground(new java.awt.Color(153, 153, 153));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Cambio");
        jLabel10.setOpaque(true);

        txtTotCobro.setEditable(false);
        txtTotCobro.setBackground(new java.awt.Color(255, 255, 255));
        txtTotCobro.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtTotCobro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotCobro.setText("0.00");

        txtEfect.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtEfect.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEfect.setText("0.00");
        txtEfect.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEfectKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfectKeyReleased(evt);
            }
        });

        txtCambio.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtCambio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCambio.setText("0.00");
        txtCambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCambioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotCobro))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCambio))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEfect)))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotCobro, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEfect, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCambio, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nomina.png"))); // NOI18N
        jButton7.setText("COBRAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFramePaysLayout = new javax.swing.GroupLayout(jFramePays.getContentPane());
        jFramePays.getContentPane().setLayout(jFramePaysLayout);
        jFramePaysLayout.setHorizontalGroup(
            jFramePaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramePaysLayout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFramePaysLayout.createSequentialGroup()
                .addGap(0, 216, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jFramePaysLayout.setVerticalGroup(
            jFramePaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramePaysLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane8.setViewportView(jTextArea1);

        jFramCobroInfrac.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jFramCobroInfrac.setTitle("COBRO DE INFRACCION");
        jFramCobroInfrac.setResizable(false);
        jFramCobroInfrac.setSize(new java.awt.Dimension(437, 350));
        jFramCobroInfrac.setType(java.awt.Window.Type.POPUP);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel25.setText("Pagar Infracción");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel28.setText("Placa / Licencia");

        jLabPlaca.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabPlaca.setText("SL-65-750");
        jLabPlaca.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabFolio.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabFolio.setText("2020");
        jLabFolio.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jLabPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 27, Short.MAX_VALUE))
                    .addComponent(jLabFolio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabFolio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabPlaca)
                .addGap(23, 23, 23))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Persona que realiza el pago:");

        txtNamePagad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNamePagad.setNextFocusableComponent(txtDcto);
        txtNamePagad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamePagadKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Importe:");

        txtImporte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtImporte.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtImporte.setText("0.00");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Decuento:");

        txtDcto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDcto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDcto.setText("0.00");
        txtDcto.setNextFocusableComponent(txtEfectivo);
        txtDcto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDctoFocusGained(evt);
            }
        });
        txtDcto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDctoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDctoKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Total:");

        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0.00");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Efectivo:");

        txtEfectivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEfectivo.setText("0.00");
        txtEfectivo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEfectivoFocusGained(evt);
            }
        });
        txtEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyReleased(evt);
            }
        });

        txtCambioInfrac.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCambioInfrac.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCambioInfrac.setText("0.00");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Cambio:");

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nomina.png"))); // NOI18N
        jButton8.setText("COBRAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFramCobroInfracLayout = new javax.swing.GroupLayout(jFramCobroInfrac.getContentPane());
        jFramCobroInfrac.getContentPane().setLayout(jFramCobroInfracLayout);
        jFramCobroInfracLayout.setHorizontalGroup(
            jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramCobroInfracLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNamePagad, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jFramCobroInfracLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jFramCobroInfracLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtImporte))
                            .addGroup(jFramCobroInfracLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDcto))
                            .addGroup(jFramCobroInfracLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jFramCobroInfracLayout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEfectivo))
                                    .addGroup(jFramCobroInfracLayout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                                    .addGroup(jFramCobroInfracLayout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCambioInfrac))))))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jFramCobroInfracLayout.setVerticalGroup(
            jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFramCobroInfracLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNamePagad, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jFramCobroInfracLayout.createSequentialGroup()
                        .addGroup(jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtImporte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDcto, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEfectivo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jFramCobroInfracLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCambioInfrac, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jFramExplanadaCob.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jFramExplanadaCob.setTitle("COBRO EXPLANADA");
        jFramExplanadaCob.setResizable(false);
        jFramExplanadaCob.setSize(new java.awt.Dimension(471, 300));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Registrar cobro de Explanada");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Folio inicial");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Folio final");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Total recibos");

        txtFolFinExplanad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFolFinExplanad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFolFinExplanadFocusGained(evt);
            }
        });
        txtFolFinExplanad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFolFinExplanadKeyReleased(evt);
            }
        });

        txtFolIniExplanad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFolIniExplanad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFolIniExplanadKeyReleased(evt);
            }
        });

        txtFoltotExplanad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Costo");

        txtCoastExplan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCoastExplan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCoastExplan.setText("20.00");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFolFinExplanad, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFolIniExplanad, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFoltotExplanad, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCoastExplan)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFolIniExplanad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFolFinExplanad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFoltotExplanad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCoastExplan))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton11.setText("Cancelar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/uicheckOk.png"))); // NOI18N
        jButton12.setText("Aceptar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFramExplanadaCobLayout = new javax.swing.GroupLayout(jFramExplanadaCob.getContentPane());
        jFramExplanadaCob.getContentPane().setLayout(jFramExplanadaCobLayout);
        jFramExplanadaCobLayout.setHorizontalGroup(
            jFramExplanadaCobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramExplanadaCobLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFramExplanadaCobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFramExplanadaCobLayout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jFramExplanadaCobLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
            .addGroup(jFramExplanadaCobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFramExplanadaCobLayout.createSequentialGroup()
                    .addContainerGap(317, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)))
        );
        jFramExplanadaCobLayout.setVerticalGroup(
            jFramExplanadaCobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramExplanadaCobLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jFramExplanadaCobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFramExplanadaCobLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFramExplanadaCobLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(jFramExplanadaCobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFramExplanadaCobLayout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(185, Short.MAX_VALUE)))
        );

        jFramAltaCliente.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jFramAltaCliente.setTitle("SistemaCentral Huixcolotla");
        jFramAltaCliente.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jFramAltaCliente.setResizable(false);
        jFramAltaCliente.setSize(new java.awt.Dimension(543, 360));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel43.setText("Registrar cliente");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("Nombre");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("Dirección");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("Correo");

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel57.setText("Obs.");

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel58.setText("Teléfono");

        txtNombClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtDirClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCorrClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtObsClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtTelClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel59.setText("RFC");

        txtRfcClient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombClient, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDirClient, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCorrClient, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtObsClient, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTelClient, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRfcClient, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDirClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorrClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtObsClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRfcClient, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/uicheckOk.png"))); // NOI18N
        jButton13.setText("Guardar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton14.setText("Cancelar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFramAltaClienteLayout = new javax.swing.GroupLayout(jFramAltaCliente.getContentPane());
        jFramAltaCliente.getContentPane().setLayout(jFramAltaClienteLayout);
        jFramAltaClienteLayout.setHorizontalGroup(
            jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramAltaClienteLayout.createSequentialGroup()
                .addGroup(jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jFramAltaClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14))
                    .addGroup(jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jFramAltaClienteLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jFramAltaClienteLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jFramAltaClienteLayout.setVerticalGroup(
            jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFramAltaClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jFramAltaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jDialresProdOther.setTitle("Central de Abastos Huixcolotla");
        jDialresProdOther.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDialresProdOther.setResizable(false);
        jDialresProdOther.setSize(new java.awt.Dimension(549, 254));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel68.setText("Registrar producto");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel69.setText("Descripción");

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel70.setText("Rubro");

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel79.setText("Precio");

        txtDescripProd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtPrec.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jCBRubroProd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescripProd, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCBRubroProd, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrec, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtDescripProd, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBRubroProd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrec, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/uicheckOk.png"))); // NOI18N
        jButton20.setText("Aceptar");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton21.setText("Cancelar");

        javax.swing.GroupLayout jDialresProdOtherLayout = new javax.swing.GroupLayout(jDialresProdOther.getContentPane());
        jDialresProdOther.getContentPane().setLayout(jDialresProdOtherLayout);
        jDialresProdOtherLayout.setHorizontalGroup(
            jDialresProdOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialresProdOtherLayout.createSequentialGroup()
                .addGroup(jDialresProdOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialresProdOtherLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialresProdOtherLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialresProdOtherLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jDialresProdOtherLayout.setVerticalGroup(
            jDialresProdOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialresProdOtherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialresProdOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jDialCancelaciones.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialCancelaciones.setTitle("Cancelar Ticket");
        jDialCancelaciones.setSize(new java.awt.Dimension(486, 170));
        jDialCancelaciones.setType(java.awt.Window.Type.UTILITY);

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel80.setText("Ingrese el motivo de la cancelación");

        txtCancelTick.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCancelTick.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCancelTickKeyReleased(evt);
            }
        });

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton24.setText("Cancelar");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/uicheckOk.png"))); // NOI18N
        jButton25.setText("OK");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialCancelacionesLayout = new javax.swing.GroupLayout(jDialCancelaciones.getContentPane());
        jDialCancelaciones.getContentPane().setLayout(jDialCancelacionesLayout);
        jDialCancelacionesLayout.setHorizontalGroup(
            jDialCancelacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialCancelacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialCancelacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCancelTick, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialCancelacionesLayout.createSequentialGroup()
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialCancelacionesLayout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        jDialCancelacionesLayout.setVerticalGroup(
            jDialCancelacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialCancelacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCancelTick, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialCancelacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialAltaGastos.setTitle("Central Huixcolotla");
        jDialAltaGastos.setSize(new java.awt.Dimension(550, 330));
        jDialAltaGastos.setType(java.awt.Window.Type.UTILITY);
        jDialAltaGastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialAltaGastosKeyPressed(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel87.setText("Registrar gasto");

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel88.setText("Cuenta");

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel90.setText("Concepto");

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel96.setText("Solicitante");

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel98.setText("Obs.");

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel106.setText("Monto");

        txtConcept.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConceptKeyPressed(evt);
            }
        });

        txtsolict.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsolictKeyPressed(evt);
            }
        });

        txtObservs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservsKeyPressed(evt);
            }
        });

        txtMontoGasto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoGastoKeyPressed(evt);
            }
        });

        jCombBTypeRubros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCombBTypeRubros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtConcept)
                            .addComponent(txtsolict)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txtMontoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 261, Short.MAX_VALUE))
                            .addComponent(txtObservs))))
                .addGap(33, 33, 33))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCombBTypeRubros, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConcept, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsolict, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtObservs, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMontoGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jButton39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/uicheckOk.png"))); // NOI18N
        jButton39.setText("Guardar");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton43.setText("Cancelar");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialAltaGastosLayout = new javax.swing.GroupLayout(jDialAltaGastos.getContentPane());
        jDialAltaGastos.getContentPane().setLayout(jDialAltaGastosLayout);
        jDialAltaGastosLayout.setHorizontalGroup(
            jDialAltaGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialAltaGastosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialAltaGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialAltaGastosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDialAltaGastosLayout.setVerticalGroup(
            jDialAltaGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialAltaGastosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialAltaGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(1329, 921));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estación", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(400, 32767));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/diablito1.png"))); // NOI18N
        jButton1.setText("\n");
        jButton1.setText("Cargadores");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMaximumSize(new java.awt.Dimension(150, 59));
        jButton1.setPreferredSize(new java.awt.Dimension(65, 59));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/almacen2.png"))); // NOI18N
        jButton2.setText("\n");
        jButton2.setText("Areas");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(150, 59));
        jButton2.setMinimumSize(new java.awt.Dimension(60, 60));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/identifcacion.png"))); // NOI18N
        jButton3.setText("\n");
        jButton3.setText("Ambulantes");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setIconTextGap(0);
        jButton3.setMaximumSize(new java.awt.Dimension(150, 59));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/oficial-de-policia.png"))); // NOI18N
        jButton4.setText("\n");
        jButton4.setText("Transito");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setMaximumSize(new java.awt.Dimension(150, 59));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/caja-registradora.png"))); // NOI18N
        jButton5.setText("Varios");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setMaximumSize(new java.awt.Dimension(150, 59));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Caja", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        jPanel2.setAutoscrolls(true);
        jPanel2.setMaximumSize(new java.awt.Dimension(0, 0));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cajero-color.png"))); // NOI18N
        jButton6.setText("Corte de caja");
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setMaximumSize(new java.awt.Dimension(150, 57));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(432, 450));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(432, 450));

        jPanInterncoborstivk.setBackground(new java.awt.Color(255, 255, 255));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel47.setText("Buscar");

        jCBBusqTicketall.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jCBBusqTicketall.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ticket", "Fecha" }));
        jCBBusqTicketall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBBusqTicketallActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print32x32.png"))); // NOI18N
        jButton17.setText("Imprimir ticket");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton18.setText("Cancelar ticket");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Actualizar.png"))); // NOI18N
        jButton19.setText("Actualizar");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        txtBusqTickAll.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jDatChoFechBusqtick.setDateFormatString("dd/MM/yyyy");

        jLayeredPane5.setLayer(txtBusqTickAll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jDatChoFechBusqtick, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBusqTickAll, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jDatChoFechBusqtick, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addComponent(txtBusqTickAll, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
            .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane5Layout.createSequentialGroup()
                    .addComponent(jDatChoFechBusqtick, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabviewPays.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabviewPays.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTabviewPays.setRowHeight(30);
        jTabviewPays.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabviewPaysMousePressed(evt);
            }
        });
        jScrollPane12.setViewportView(jTabviewPays);

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goma-de-borrar.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanInterncoborstivkLayout = new javax.swing.GroupLayout(jPanInterncoborstivk);
        jPanInterncoborstivk.setLayout(jPanInterncoborstivkLayout);
        jPanInterncoborstivkLayout.setHorizontalGroup(
            jPanInterncoborstivkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanInterncoborstivkLayout.createSequentialGroup()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBBusqTicketall, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanInterncoborstivkLayout.createSequentialGroup()
                .addGroup(jPanInterncoborstivkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanInterncoborstivkLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanInterncoborstivkLayout.createSequentialGroup()
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanInterncoborstivkLayout.setVerticalGroup(
            jPanInterncoborstivkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanInterncoborstivkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanInterncoborstivkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanInterncoborstivkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCBBusqTicketall, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLayeredPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanInterncoborstivkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cobros", jPanInterncoborstivk);

        jPanInternGastos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanInternGastosFocusGained(evt);
            }
        });

        jTabViewGastos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabViewGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTabViewGastos.setRowHeight(30);
        jTabViewGastos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabViewGastosMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTabViewGastos);

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel81.setText("Buscar");

        jCombBOpcBusqGastos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCombBOpcBusqGastos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Concepto", "Fecha" }));
        jCombBOpcBusqGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombBOpcBusqGastosActionPerformed(evt);
            }
        });

        jButton28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas.png"))); // NOI18N
        jButton28.setText("Registrar");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lapiz.png"))); // NOI18N
        jButton29.setText("Modificar");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salida.png"))); // NOI18N
        jButton38.setToolTipText("Eliminar gasto");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jLayeredPane6.setPreferredSize(new java.awt.Dimension(196, 31));

        txtBusGasto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLayeredPane6.setLayer(txtBusGasto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jDateChoGastos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane6Layout = new javax.swing.GroupLayout(jLayeredPane6);
        jLayeredPane6.setLayout(jLayeredPane6Layout);
        jLayeredPane6Layout.setHorizontalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addComponent(txtBusGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jDateChoGastos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
        );
        jLayeredPane6Layout.setVerticalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBusGasto)
            .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jDateChoGastos, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
        );

        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-what.png"))); // NOI18N
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanInternGastosLayout = new javax.swing.GroupLayout(jPanInternGastos);
        jPanInternGastos.setLayout(jPanInternGastosLayout);
        jPanInternGastosLayout.setHorizontalGroup(
            jPanInternGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanInternGastosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanInternGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanInternGastosLayout.createSequentialGroup()
                        .addComponent(jButton28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanInternGastosLayout.createSequentialGroup()
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCombBOpcBusqGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanInternGastosLayout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanInternGastosLayout.setVerticalGroup(
            jPanInternGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanInternGastosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanInternGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLayeredPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jCombBOpcBusqGastos)
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanInternGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanInternGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(209, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Gastos", jPanInternGastos);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(814, 675));

        jPanambulantes.setBackground(new java.awt.Color(255, 255, 255));
        jPanambulantes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/viaje.png"))); // NOI18N
        jLabel14.setText("     Buscar ambulante:");

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel19.setBackground(new java.awt.Color(204, 204, 204));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jCheckResguardAmb.setBackground(new java.awt.Color(204, 204, 204));
        jCheckResguardAmb.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jCheckResguardAmb.setText("Resguardo");
        jCheckResguardAmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckResguardAmbActionPerformed(evt);
            }
        });
        jCheckResguardAmb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCheckResguardAmbKeyReleased(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setText("<html> Ultimo Pago: - <b> Semana</b>\n</html>");

        jLabUltimaResguardPay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabUltimaResguardPay.setText("n");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jCheckResguardAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabUltimaResguardPay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(txtIdResguardOcultoAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabUltimaResguardPay)
                    .addComponent(txtIdResguardOcultoAmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckResguardAmb)))
                .addContainerGap())
        );

        txtResgIniAmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtResgIniAmb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtResgIniAmb.setText("0");

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Semana Inicial");

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel52.setText("Semana Final");

        jButMinusResgSemfin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButMinusResgSemfin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menos.png"))); // NOI18N
        jButMinusResgSemfin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButMinusResgSemfinActionPerformed(evt);
            }
        });
        jButMinusResgSemfin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButMinusResgSemfinKeyReleased(evt);
            }
        });

        txtResgFinAmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtResgFinAmb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtResgFinAmb.setText("0");

        jButMostSemsPaysAmb2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButMostSemsPaysAmb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas.png"))); // NOI18N
        jButMostSemsPaysAmb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButMostSemsPaysAmb2ActionPerformed(evt);
            }
        });
        jButMostSemsPaysAmb2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButMostSemsPaysAmb2KeyReleased(evt);
            }
        });

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("Semanas");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setText("Tarifa");

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setText("Importe");

        jLabContSemsResguard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabContSemsResguard.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabContSemsResguard.setText("0.00");
        jLabContSemsResguard.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabContSemsResguard.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabImporteResguard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabImporteResguard.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabImporteResguard.setText("0.00");

        jLabTarifaResguard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabTarifaResguard.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabTarifaResguard.setText("0");
        jLabTarifaResguard.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel72.setText("Desc. (%)");

        jLabDstoResguard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabDstoResguard.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabDstoResguard.setText("0.00");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addComponent(jLabel56))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabImporteResguard, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jLabDstoResguard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabTarifaResguard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabContSemsResguard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabContSemsResguard, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabTarifaResguard, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabDstoResguard, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabImporteResguard, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jButMinusResgPaysAmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButMinusResgPaysAmb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menos.png"))); // NOI18N
        jButMinusResgPaysAmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButMinusResgPaysAmbActionPerformed(evt);
            }
        });
        jButMinusResgPaysAmb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButMinusResgPaysAmbKeyReleased(evt);
            }
        });

        jButMostSemsPaysAmb1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButMostSemsPaysAmb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas.png"))); // NOI18N
        jButMostSemsPaysAmb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButMostSemsPaysAmb1ActionPerformed(evt);
            }
        });
        jButMostSemsPaysAmb1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButMostSemsPaysAmb1KeyReleased(evt);
            }
        });

        jLaFechIniResguard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLaFechIniResguard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLaFechIniResguard.setText("Semana Inicial");
        jLaFechIniResguard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabAumResguard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabAumResguard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabAumResguard.setText("Semana Inicial");
        jLabAumResguard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel89.setText("Vehículo");

        jCBoxResguardosOpc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCBoxResguardosOpc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Resguardo general" }));
        jCBoxResguardosOpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBoxResguardosOpcActionPerformed(evt);
            }
        });
        jCBoxResguardosOpc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCBoxResguardosOpcKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButMinusResgSemfin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButMinusResgPaysAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtResgFinAmb)
                                    .addComponent(txtResgIniAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLaFechIniResguard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabAumResguard))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButMostSemsPaysAmb2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButMostSemsPaysAmb1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCBoxResguardosOpc, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButMinusResgPaysAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jButMostSemsPaysAmb1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtResgIniAmb)
                                .addComponent(jLaFechIniResguard, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButMinusResgSemfin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(txtResgFinAmb)
                                .addComponent(jButMostSemsPaysAmb2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabAumResguard, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jCBoxResguardosOpc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel22.setBackground(new java.awt.Color(204, 204, 204));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jCheckInscripPaysAmb.setBackground(new java.awt.Color(204, 204, 204));
        jCheckInscripPaysAmb.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jCheckInscripPaysAmb.setText("Inscripcion");
        jCheckInscripPaysAmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckInscripPaysAmbActionPerformed(evt);
            }
        });
        jCheckInscripPaysAmb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCheckInscripPaysAmbKeyReleased(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel60.setText("Vigente hasta el:-");

        jLabVigenciaView.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabVigenciaView.setText("--");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jCheckInscripPaysAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabVigenciaView, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jLabVigenciaView))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckInscripPaysAmb)
                .addContainerGap())
        );

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("Inicio");

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel63.setText("Duración");

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("Tarifa");

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel66.setText("Desc.(%)");

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel67.setText("Importe");

        jLabDstoInscripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabDstoInscripcion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabDstoInscripcion.setText("0.00");
        jLabDstoInscripcion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabDstoInscripcion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabImporteInscripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabImporteInscripcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabImporteInscripcion.setText("0.00");

        jLabTarifaInscripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabTarifaInscripcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabTarifaInscripcion.setText("0.00");
        jLabTarifaInscripcion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabDstoInscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(jLabTarifaInscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabImporteInscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabDstoInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabImporteInscripcion))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabTarifaInscripcion)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel67))))
        );

        jDateChoInscripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jDateChoInscripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChoInscripcionKeyReleased(evt);
            }
        });

        jCBoxDuracInscripc.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jCBoxDuracInscripc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Anual", "Semestral", "Trimestral" }));
        jCBoxDuracInscripc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBoxDuracInscripcActionPerformed(evt);
            }
        });
        jCBoxDuracInscripc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCBoxDuracInscripcKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChoInscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBoxDuracInscripc, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDateChoInscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(jCBoxDuracInscripc))
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel25.setBackground(new java.awt.Color(204, 204, 204));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jCheckSemPaysAmb.setBackground(new java.awt.Color(204, 204, 204));
        jCheckSemPaysAmb.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jCheckSemPaysAmb.setText("Semana");
        jCheckSemPaysAmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckSemPaysAmbActionPerformed(evt);
            }
        });
        jCheckSemPaysAmb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCheckSemPaysAmbKeyReleased(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel71.setText("Ultimo Pago: -");

        jLabUltimaSemanaPay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabUltimaSemanaPay.setText("Semana n");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jCheckSemPaysAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabUltimaSemanaPay, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(txtIdSemOcultoAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator7)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckSemPaysAmb)
                .addContainerGap())
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jLabUltimaSemanaPay)
                    .addComponent(txtIdSemOcultoAmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        txtSeminiAmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSeminiAmb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSeminiAmb.setText("0");

        jLabel73.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("Semana Inicial");

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel74.setText("Semana Final");

        jButMinusSemsPaysAmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButMinusSemsPaysAmb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menos.png"))); // NOI18N
        jButMinusSemsPaysAmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButMinusSemsPaysAmbActionPerformed(evt);
            }
        });
        jButMinusSemsPaysAmb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButMinusSemsPaysAmbKeyReleased(evt);
            }
        });

        txtSemFinAmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSemFinAmb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSemFinAmb.setText("0");

        jButMostSemsPaysAmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButMostSemsPaysAmb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas.png"))); // NOI18N
        jButMostSemsPaysAmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButMostSemsPaysAmbActionPerformed(evt);
            }
        });
        jButMostSemsPaysAmb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButMostSemsPaysAmbKeyReleased(evt);
            }
        });

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel76.setText("Semanas");

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel77.setText("Tarifa");

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel78.setText("Importe");

        jLabContadorSemanas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabContadorSemanas.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabContadorSemanas.setText("0.00");
        jLabContadorSemanas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabContadorSemanas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabImporteSemanas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabImporteSemanas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabImporteSemanas.setText("0.00");

        jLabTarifaSemanas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabTarifaSemanas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabTarifaSemanas.setText("0");
        jLabTarifaSemanas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel86.setText("Desc. (%)");

        jLabDstoSemanas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabDstoSemanas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabDstoSemanas.setText("0.00");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77)
                            .addComponent(jLabel78))
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabImporteSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabTarifaSemanas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabDstoSemanas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabContadorSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabContadorSemanas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabTarifaSemanas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(jLabDstoSemanas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(jLabImporteSemanas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLaFechIniSemana.setText("jLabel11");
        jLaFechIniSemana.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabAumentaSemanas.setText("jLabel12");
        jLabAumentaSemanas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(txtSeminiAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLaFechIniSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jButMinusSemsPaysAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtSemFinAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabAumentaSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jButMostSemsPaysAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButMinusSemsPaysAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtSeminiAmb)
                            .addComponent(txtSemFinAmb)
                            .addComponent(jButMostSemsPaysAmb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLaFechIniSemana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabAumentaSemanas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel92.setText("Nombre:");

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel93.setText("Direccion:");

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel94.setText("Giro:");

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel95.setText("Observaciones:");

        jLabNomAmb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabNomAmb.setText("Nombre:");

        jLabDirecc1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabDirecc1.setText("Direccion:");
        jLabDirecc1.setAutoscrolls(true);

        jLabGiro1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabGiro1.setText("Giro:");

        jLabObserva1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabObserva1.setText("Observaciones:");

        jLabIdAmbu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabIdAmbu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanDataambView1Layout = new javax.swing.GroupLayout(jPanDataambView1);
        jPanDataambView1.setLayout(jPanDataambView1Layout);
        jPanDataambView1Layout.setHorizontalGroup(
            jPanDataambView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanDataambView1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanDataambView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel93, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanDataambView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanDataambView1Layout.createSequentialGroup()
                        .addGroup(jPanDataambView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanDataambView1Layout.createSequentialGroup()
                                .addComponent(jLabIdAmbu, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabNomAmb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabDirecc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanDataambView1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabObserva1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25))
                    .addGroup(jPanDataambView1Layout.createSequentialGroup()
                        .addComponent(jLabGiro1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanDataambView1Layout.setVerticalGroup(
            jPanDataambView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanDataambView1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanDataambView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabNomAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanDataambView1Layout.createSequentialGroup()
                        .addGroup(jPanDataambView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(jLabIdAmbu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1)
                        .addGroup(jPanDataambView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabDirecc1, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                            .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanDataambView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabGiro1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanDataambView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabObserva1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabDatosAmbulante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "N'umero", "Nombre"
            }
        ));
        jTabDatosAmbulante.setRowHeight(20);
        jTabDatosAmbulante.setRowMargin(2);
        jTabDatosAmbulante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabDatosAmbulanteKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTabDatosAmbulante);
        if (jTabDatosAmbulante.getColumnModel().getColumnCount() > 0) {
            jTabDatosAmbulante.getColumnModel().getColumn(0).setMinWidth(80);
            jTabDatosAmbulante.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTabDatosAmbulante.getColumnModel().getColumn(0).setMaxWidth(180);
        }

        javax.swing.GroupLayout jPanTableBusqViewLayout = new javax.swing.GroupLayout(jPanTableBusqView);
        jPanTableBusqView.setLayout(jPanTableBusqViewLayout);
        jPanTableBusqViewLayout.setHorizontalGroup(
            jPanTableBusqViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanTableBusqViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanTableBusqViewLayout.setVerticalGroup(
            jPanTableBusqViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLayeredPane2.setLayer(jPanDataambView1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jPanTableBusqView, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanTableBusqView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(jPanDataambView1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 4, Short.MAX_VALUE)))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(jPanTableBusqView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanDataambView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtBuscAmbulante.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBuscAmbulante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscAmbulanteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscAmbulanteKeyReleased(evt);
            }
        });

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-what.png"))); // NOI18N

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reloj.png"))); // NOI18N
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        txtResultAmbu.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtResultAmbu.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtResultAmbu.setText("0.00");

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel91.setText("Total");

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cash.png"))); // NOI18N
        jButton22.setText("COBRAR");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanambulantesLayout = new javax.swing.GroupLayout(jPanambulantes);
        jPanambulantes.setLayout(jPanambulantesLayout);
        jPanambulantesLayout.setHorizontalGroup(
            jPanambulantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanambulantesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanambulantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanambulantesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtBuscAmbulante, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanambulantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanambulantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanambulantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtResultAmbu, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jButton22))
            .addGroup(jPanambulantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2))
        );
        jPanambulantesLayout.setVerticalGroup(
            jPanambulantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanambulantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanambulantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscAmbulante, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanambulantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanambulantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtResultAmbu)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(159, Short.MAX_VALUE))
        );

        jPanCargadores.setBackground(new java.awt.Color(255, 255, 255));
        jPanCargadores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setText("Fecha inicio");

        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel75.setText("Fecha final");

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel84.setText("Días");

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel102.setText("Diablito #");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel113.setText("Condonaci'on (%)");

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel111.setText("Tarifa");

        jLabel114.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel114.setText("Importe");

        jLabCondonacRentDiab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabCondonacRentDiab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabCondonacRentDiab.setText("50");

        jLabTarifRentCarg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabTarifRentCarg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabTarifRentCarg.setText("0.00");

        jLabImportRentDiab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabImportRentDiab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabImportRentDiab.setText("0.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabTarifRentCarg, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabCondonacRentDiab, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabImportRentDiab, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabTarifRentCarg, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabCondonacRentDiab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabImportRentDiab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        txtNumDiabRent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumDiabRentKeyReleased(evt);
            }
        });

        dCCFechIniRentCarg.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dCCFechIniRentCarg.setCalendarPreferredSize(new java.awt.Dimension(340, 230));
    dCCFechIniRentCarg.setNothingAllowed(false);
    dCCFechIniRentCarg.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 13));
    dCCFechIniRentCarg.setNavigateFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
    dCCFechIniRentCarg.addCommitListener(new datechooser.events.CommitListener() {
        public void onCommit(datechooser.events.CommitEvent evt) {
            dCCFechIniRentCargOnCommit(evt);
        }
    });

    dCCFechFinRentCarg.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dCCFechFinRentCarg.setCalendarPreferredSize(new java.awt.Dimension(340, 230));
dCCFechFinRentCarg.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 13));
dCCFechFinRentCarg.setNavigateFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
dCCFechFinRentCarg.setCurrentNavigateIndex(0);
dCCFechFinRentCarg.addCommitListener(new datechooser.events.CommitListener() {
    public void onCommit(datechooser.events.CommitEvent evt) {
        dCCFechFinRentCargOnCommit(evt);
    }
    });

    javax.swing.GroupLayout jPanRentCargadorLayout = new javax.swing.GroupLayout(jPanRentCargador);
    jPanRentCargador.setLayout(jPanRentCargadorLayout);
    jPanRentCargadorLayout.setHorizontalGroup(
        jPanRentCargadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanRentCargadorLayout.createSequentialGroup()
            .addGap(47, 47, 47)
            .addGroup(jPanRentCargadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanRentCargadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanRentCargadorLayout.createSequentialGroup()
                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumDiabRent))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanRentCargadorLayout.createSequentialGroup()
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumDaysrentdiab, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanRentCargadorLayout.createSequentialGroup()
                    .addGroup(jPanRentCargadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanRentCargadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dCCFechIniRentCarg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dCCFechFinRentCarg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(50, Short.MAX_VALUE))
    );
    jPanRentCargadorLayout.setVerticalGroup(
        jPanRentCargadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanRentCargadorLayout.createSequentialGroup()
            .addGap(32, 32, 32)
            .addGroup(jPanRentCargadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanRentCargadorLayout.createSequentialGroup()
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanRentCargadorLayout.createSequentialGroup()
                    .addComponent(dCCFechIniRentCarg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dCCFechFinRentCarg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanRentCargadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtnumDaysrentdiab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanRentCargadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtNumDiabRent, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(72, 72, 72))
        .addGroup(jPanRentCargadorLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanCargadorInscr.setBackground(new java.awt.Color(255, 255, 255));
    jPanCargadorInscr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jPanel28.setBackground(new java.awt.Color(204, 204, 204));

    jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

    jCkBoxInscripcion.setBackground(new java.awt.Color(204, 204, 204));
    jCkBoxInscripcion.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
    jCkBoxInscripcion.setText("Inscripcion");
    jCkBoxInscripcion.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCkBoxInscripcionActionPerformed(evt);
        }
    });
    jCkBoxInscripcion.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jCkBoxInscripcionKeyReleased(evt);
        }
    });

    jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel53.setText("Vigencia:-");

    jLabVigencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabVigencia.setText("N/A");

    javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
    jPanel28.setLayout(jPanel28Layout);
    jPanel28Layout.setHorizontalGroup(
        jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel28Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jCkBoxInscripcion)
            .addGap(22, 22, 22)
            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabVigencia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel28Layout.setVerticalGroup(
        jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator8)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jCkBoxInscripcion)
            .addContainerGap())
        .addGroup(jPanel28Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel53)
                .addComponent(jLabVigencia))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabel64.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel64.setText("Inicio");

    jPanel29.setBackground(new java.awt.Color(255, 255, 255));

    jLabel82.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel82.setText("Tarifa");

    jLabel83.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel83.setText("Importe");

    jLabImportInscripc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabImportInscripc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabImportInscripc.setText("0.00");

    jLabCoastTarifa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabCoastTarifa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabCoastTarifa.setText("0");
    jLabCoastTarifa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

    jLabel97.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel97.setText("Desc. (%)");

    jLabDescto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabDescto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabDescto.setText("0.00");

    javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
    jPanel29.setLayout(jPanel29Layout);
    jPanel29Layout.setHorizontalGroup(
        jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel29Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabCoastTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addComponent(jLabel83)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                    .addComponent(jLabImportInscripc, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addComponent(jLabel82)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addComponent(jLabel97)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabDescto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );
    jPanel29Layout.setVerticalGroup(
        jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel29Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabCoastTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabDescto, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel97, javax.swing.GroupLayout.Alignment.TRAILING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabImportInscripc, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabel99.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel99.setText("Duración");

    jComBInscripc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jComBInscripc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Anual", "Semestral", "Trimestral" }));
    jComBInscripc.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComBInscripcActionPerformed(evt);
        }
    });
    jComBInscripc.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jComBInscripcKeyReleased(evt);
        }
    });

    jDatChoIncripcion.setDateFormatString("dd/MM/yyyy");
    jDatChoIncripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jDatChoIncripcion.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jDatChoIncripcionKeyReleased(evt);
        }
    });

    javax.swing.GroupLayout jPanCargadorInscrLayout = new javax.swing.GroupLayout(jPanCargadorInscr);
    jPanCargadorInscr.setLayout(jPanCargadorInscrLayout);
    jPanCargadorInscrLayout.setHorizontalGroup(
        jPanCargadorInscrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanCargadorInscrLayout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addGroup(jPanCargadorInscrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanCargadorInscrLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jDatChoIncripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComBInscripc, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE))
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanCargadorInscrLayout.setVerticalGroup(
        jPanCargadorInscrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanCargadorInscrLayout.createSequentialGroup()
            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanCargadorInscrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDatChoIncripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComBInscripc, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    jPanSemCargad.setBackground(new java.awt.Color(255, 255, 255));
    jPanSemCargad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jPanel31.setBackground(new java.awt.Color(204, 204, 204));

    jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

    jCkBoxSemanas.setBackground(new java.awt.Color(204, 204, 204));
    jCkBoxSemanas.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
    jCkBoxSemanas.setText("Semana");
    jCkBoxSemanas.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCkBoxSemanasActionPerformed(evt);
        }
    });
    jCkBoxSemanas.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jCkBoxSemanasKeyReleased(evt);
        }
    });

    jLabel100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel100.setText("<html> \nUltimo Pago: -<b>Semana</b>\n</html>");

    jLabnumSem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabnumSem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabnumSem.setText("N/A");

    javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
    jPanel31.setLayout(jPanel31Layout);
    jPanel31Layout.setHorizontalGroup(
        jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel31Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jCkBoxSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(31, 31, 31)
            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabnumSem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(34, 34, 34)
            .addComponent(txtOcultiDSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel31Layout.setVerticalGroup(
        jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator9)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jCkBoxSemanas)
            .addContainerGap())
        .addGroup(jPanel31Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabnumSem)
                .addComponent(txtOcultiDSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, Short.MAX_VALUE))
    );

    txtcontSemsFin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    txtcontSemsFin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtcontSemsFin.setText("0");

    jLabel101.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel101.setText("Semana Inicial");

    jPanel32.setBackground(new java.awt.Color(255, 255, 255));

    jLabel103.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel103.setText("Semanas");

    jLabel104.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel104.setText("Tarifa");

    jLabel105.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel105.setText("Importe");

    jLabcontSemanas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabcontSemanas.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabcontSemanas.setText("0");
    jLabcontSemanas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    jLabcontSemanas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

    jLabImportSemanasCargad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabImportSemanasCargad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabImportSemanasCargad.setText("0.00");

    jLabtarifSemanas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabtarifSemanas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabtarifSemanas.setText("0.00");
    jLabtarifSemanas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

    jLabel109.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel109.setText("Desc. (%)");

    jLabDtoSemanas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabDtoSemanas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabDtoSemanas.setText("0.00");

    javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
    jPanel32.setLayout(jPanel32Layout);
    jPanel32Layout.setHorizontalGroup(
        jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                    .addComponent(jLabel109)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabDtoSemanas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabtarifSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addComponent(jLabel104)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addComponent(jLabel105)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabImportSemanasCargad, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabcontSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );
    jPanel32Layout.setVerticalGroup(
        jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel32Layout.createSequentialGroup()
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jLabcontSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabtarifSemanas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabDtoSemanas, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel109, javax.swing.GroupLayout.Alignment.TRAILING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabImportSemanasCargad, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.TRAILING))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jButMinusSems.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButMinusSems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menos.png"))); // NOI18N
    jButMinusSems.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButMinusSemsActionPerformed(evt);
        }
    });
    jButMinusSems.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jButMinusSemsKeyReleased(evt);
        }
    });

    jButMooreSems.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButMooreSems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas.png"))); // NOI18N
    jButMooreSems.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButMooreSemsActionPerformed(evt);
        }
    });
    jButMooreSems.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jButMooreSemsKeyReleased(evt);
        }
    });

    txtcontSemsIni.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    txtcontSemsIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtcontSemsIni.setText("0");

    jLabel112.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel112.setText("Semana Final");

    jLabIniSemsViews.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabIniSemsViews.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabIniSemsViews.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

    jLabFinSemsViews.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabFinSemsViews.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabFinSemsViews.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

    javax.swing.GroupLayout jPanSemCargadLayout = new javax.swing.GroupLayout(jPanSemCargad);
    jPanSemCargad.setLayout(jPanSemCargadLayout);
    jPanSemCargadLayout.setHorizontalGroup(
        jPanSemCargadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanSemCargadLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanSemCargadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanSemCargadLayout.createSequentialGroup()
                    .addComponent(txtcontSemsIni, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jLabIniSemsViews, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButMinusSems, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(txtcontSemsFin, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jLabFinSemsViews, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jButMooreSems, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanSemCargadLayout.createSequentialGroup()
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(74, 74, 74)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanSemCargadLayout.setVerticalGroup(
        jPanSemCargadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanSemCargadLayout.createSequentialGroup()
            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanSemCargadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel101)
                .addComponent(jLabel112))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanSemCargadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButMooreSems, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtcontSemsIni)
                .addComponent(txtcontSemsFin, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jButMinusSems, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabIniSemsViews, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabFinSemsViews, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    jLayeredPane4.setLayer(jPanRentCargador, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane4.setLayer(jPanCargadorInscr, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane4.setLayer(jPanSemCargad, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
    jLayeredPane4.setLayout(jLayeredPane4Layout);
    jLayeredPane4Layout.setHorizontalGroup(
        jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPane4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanSemCargad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(15, Short.MAX_VALUE))
        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanCargadorInscr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE)))
        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanRentCargador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()))
    );
    jLayeredPane4Layout.setVerticalGroup(
        jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPane4Layout.createSequentialGroup()
            .addComponent(jPanSemCargad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 121, Short.MAX_VALUE))
        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(jPanCargadorInscr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()))
        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanRentCargador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/diablito1.png"))); // NOI18N
    jLabel50.setText("     Buscar cargador:");

    jLabel121.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel121.setText("Nombre:");

    jLabel122.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel122.setText("Direccion:");

    jLabel123.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel123.setText("Diablo:");

    jLabel124.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel124.setText("Observaciones:");

    jLabNombreCargadores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabNombreCargadores.setText("125 Antonio Notario");

    jLabDirecCargad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabDirecCargad.setText("Tecamachalco");

    jLabGiroCarg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabGiroCarg.setText("Frutas");

    jlabIdCargador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jlabIdCargador.setText("jLabel11");

    jLabObservaCarg.setEditable(false);
    jLabObservaCarg.setBackground(new java.awt.Color(240, 240, 240));
    jLabObservaCarg.setColumns(20);
    jLabObservaCarg.setLineWrap(true);
    jLabObservaCarg.setRows(3);
    jLabObservaCarg.setOpaque(false);
    jScrollPane9.setViewportView(jLabObservaCarg);

    javax.swing.GroupLayout jPanDataambView2Layout = new javax.swing.GroupLayout(jPanDataambView2);
    jPanDataambView2.setLayout(jPanDataambView2Layout);
    jPanDataambView2Layout.setHorizontalGroup(
        jPanDataambView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanDataambView2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanDataambView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanDataambView2Layout.createSequentialGroup()
                    .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanDataambView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanDataambView2Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jlabIdCargador, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabNombreCargadores, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
                        .addGroup(jPanDataambView2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabDirecCargad, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanDataambView2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabGiroCarg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(25, 25, 25))
                .addGroup(jPanDataambView2Layout.createSequentialGroup()
                    .addGroup(jPanDataambView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel122, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel123, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel124, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane9)
                    .addContainerGap())))
    );
    jPanDataambView2Layout.setVerticalGroup(
        jPanDataambView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanDataambView2Layout.createSequentialGroup()
            .addGap(3, 3, 3)
            .addGroup(jPanDataambView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanDataambView2Layout.createSequentialGroup()
                    .addGroup(jPanDataambView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlabIdCargador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabNombreCargadores, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                .addGroup(jPanDataambView2Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jLabDirecCargad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabGiroCarg, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanDataambView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    jTabCargadoresView.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null},
            {null, null},
            {null, null},
            {null, null}
        },
        new String [] {
            "N'umero", "Nombre"
        }
    ));
    jTabCargadoresView.setRowHeight(20);
    jTabCargadoresView.setRowMargin(2);
    jTabCargadoresView.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            jTabCargadoresViewKeyPressed(evt);
        }
    });
    jScrollPane3.setViewportView(jTabCargadoresView);
    if (jTabCargadoresView.getColumnModel().getColumnCount() > 0) {
        jTabCargadoresView.getColumnModel().getColumn(0).setMinWidth(80);
        jTabCargadoresView.getColumnModel().getColumn(0).setPreferredWidth(120);
        jTabCargadoresView.getColumnModel().getColumn(0).setMaxWidth(180);
    }

    javax.swing.GroupLayout jPanTableBusqView1Layout = new javax.swing.GroupLayout(jPanTableBusqView1);
    jPanTableBusqView1.setLayout(jPanTableBusqView1Layout);
    jPanTableBusqView1Layout.setHorizontalGroup(
        jPanTableBusqView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanTableBusqView1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane3)
            .addContainerGap())
    );
    jPanTableBusqView1Layout.setVerticalGroup(
        jPanTableBusqView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanTableBusqView1Layout.createSequentialGroup()
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    jLayeredPane3.setLayer(jPanDataambView2, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane3.setLayer(jPanTableBusqView1, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
    jLayeredPane3.setLayout(jLayeredPane3Layout);
    jLayeredPane3Layout.setHorizontalGroup(
        jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPane3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanTableBusqView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanDataambView2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );
    jLayeredPane3Layout.setVerticalGroup(
        jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPane3Layout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addComponent(jPanTableBusqView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addComponent(jPanDataambView2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE)))
    );

    txtBuscCargadores.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            txtBuscCargadoresKeyPressed(evt);
        }
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtBuscCargadoresKeyReleased(evt);
        }
    });

    jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-what.png"))); // NOI18N

    jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reloj.png"))); // NOI18N
    jButton36.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton36ActionPerformed(evt);
        }
    });

    txtTotalCarg.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    txtTotalCarg.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    txtTotalCarg.setText("0.00");

    jLabel125.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel125.setText("Total");

    jButton37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cash.png"))); // NOI18N
    jButton37.setText("COBRAR");
    jButton37.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton37ActionPerformed(evt);
        }
    });

    jButton40.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
    jButton40.setText("Semana");
    jButton40.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton40ActionPerformed(evt);
        }
    });

    jButton41.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
    jButton41.setText("Renta");
    jButton41.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton41ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanCargadoresLayout = new javax.swing.GroupLayout(jPanCargadores);
    jPanCargadores.setLayout(jPanCargadoresLayout);
    jPanCargadoresLayout.setHorizontalGroup(
        jPanCargadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanCargadoresLayout.createSequentialGroup()
            .addGroup(jPanCargadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanCargadoresLayout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(jPanCargadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanCargadoresLayout.createSequentialGroup()
                            .addComponent(jButton40)
                            .addGap(27, 27, 27)
                            .addComponent(jButton41))
                        .addGroup(jPanCargadoresLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(txtBuscCargadores, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanCargadoresLayout.createSequentialGroup()
                            .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(171, 171, 171)
                            .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTotalCarg, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton37))))
                .addGroup(jPanCargadoresLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(jPanCargadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanCargadoresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );
    jPanCargadoresLayout.setVerticalGroup(
        jPanCargadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanCargadoresLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanCargadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtBuscCargadores))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanCargadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanCargadoresLayout.createSequentialGroup()
                    .addGroup(jPanCargadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                    .addGroup(jPanCargadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotalCarg)
                        .addComponent(jLabel125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanCargadoresLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(214, Short.MAX_VALUE))
        .addGroup(jPanCargadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanCargadoresLayout.createSequentialGroup()
                .addContainerGap(302, Short.MAX_VALUE)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE)))
    );

    jPanVarios.setBackground(new java.awt.Color(255, 255, 255));
    jPanVarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jLabel127.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel127.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dinero-cobros.png"))); // NOI18N
    jLabel127.setText("     Otros cobros:");

    jPanvIEWcOAST.setBackground(new java.awt.Color(255, 255, 255));
    jPanvIEWcOAST.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jPanel35.setBackground(new java.awt.Color(204, 204, 204));

    jLabel140.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel140.setText("Concepto:");

    jLabConceptName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabConceptName.setText("N/A");

    javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
    jPanel35.setLayout(jPanel35Layout);
    jPanel35Layout.setHorizontalGroup(
        jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel35Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabConceptName, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(txtIdOculto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19))
    );
    jPanel35Layout.setVerticalGroup(
        jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel140)
                .addComponent(jLabConceptName)
                .addComponent(txtIdOculto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    jLabel142.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel142.setText("Cant.");

    jLabel150.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel150.setText("Precio");

    jLabel151.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel151.setText("Importe");

    jButton34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jButton34.setText("Agregar");
    jButton34.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton34ActionPerformed(evt);
        }
    });

    txtCantidadOths.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    txtCantidadOths.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtCantidadOthsKeyReleased(evt);
        }
    });

    txtPreciooths.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    txtPreciooths.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtPrecioothsKeyReleased(evt);
        }
    });

    txtImportOths.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    javax.swing.GroupLayout jPanvIEWcOASTLayout = new javax.swing.GroupLayout(jPanvIEWcOAST);
    jPanvIEWcOAST.setLayout(jPanvIEWcOASTLayout);
    jPanvIEWcOASTLayout.setHorizontalGroup(
        jPanvIEWcOASTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanvIEWcOASTLayout.createSequentialGroup()
            .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtCantidadOths, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtPreciooths, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtImportOths, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanvIEWcOASTLayout.setVerticalGroup(
        jPanvIEWcOASTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanvIEWcOASTLayout.createSequentialGroup()
            .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanvIEWcOASTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(jPanvIEWcOASTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel150, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel151, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel142, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addComponent(txtCantidadOths, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtPreciooths)
                .addComponent(txtImportOths, javax.swing.GroupLayout.Alignment.LEADING))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanTableBusqView3.setBackground(new java.awt.Color(255, 255, 255));

    jTabViewOtherProd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jTabViewOtherProd.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null},
            {null, null},
            {null, null},
            {null, null}
        },
        new String [] {
            "Número", "Nombre"
        }
    ));
    jTabViewOtherProd.setRowHeight(20);
    jTabViewOtherProd.setRowMargin(2);
    jTabViewOtherProd.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            jTabViewOtherProdKeyPressed(evt);
        }
    });
    jScrollPane6.setViewportView(jTabViewOtherProd);
    if (jTabViewOtherProd.getColumnModel().getColumnCount() > 0) {
        jTabViewOtherProd.getColumnModel().getColumn(0).setMinWidth(80);
        jTabViewOtherProd.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTabViewOtherProd.getColumnModel().getColumn(0).setMaxWidth(180);
    }

    javax.swing.GroupLayout jPanTableBusqView3Layout = new javax.swing.GroupLayout(jPanTableBusqView3);
    jPanTableBusqView3.setLayout(jPanTableBusqView3Layout);
    jPanTableBusqView3Layout.setHorizontalGroup(
        jPanTableBusqView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanTableBusqView3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanTableBusqView3Layout.setVerticalGroup(
        jPanTableBusqView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanTableBusqView3Layout.createSequentialGroup()
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 8, Short.MAX_VALUE))
    );

    jLayeredPanConcept.setLayer(jPanvIEWcOAST, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPanConcept.setLayer(jPanTableBusqView3, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout jLayeredPanConceptLayout = new javax.swing.GroupLayout(jLayeredPanConcept);
    jLayeredPanConcept.setLayout(jLayeredPanConceptLayout);
    jLayeredPanConceptLayout.setHorizontalGroup(
        jLayeredPanConceptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPanConceptLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanTableBusqView3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(jLayeredPanConceptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPanConceptLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanvIEWcOAST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE)))
    );
    jLayeredPanConceptLayout.setVerticalGroup(
        jLayeredPanConceptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPanConceptLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanTableBusqView3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(43, 43, 43))
        .addGroup(jLayeredPanConceptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPanConceptLayout.createSequentialGroup()
                .addComponent(jPanvIEWcOAST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 68, Short.MAX_VALUE)))
    );

    jPanDataambView3.setBackground(new java.awt.Color(255, 255, 255));
    jPanDataambView3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

    jLabel161.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel161.setText("Nombre:");

    jLabel162.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel162.setText("Direccion:");

    jLabThisIs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabThisIs.setText("Amb.");

    jLabel164.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel164.setText("Observaciones:");

    jLabNombre3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabNombre3.setText("2 ABEL BAUTISTA PALACIOS");

    jLabObserva3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabObserva3.setText("Grosero");

    jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

    jLabFoli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabFoli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabFoli.setText("1");

    jLabDirecc3.setEditable(false);
    jLabDirecc3.setColumns(20);
    jLabDirecc3.setLineWrap(true);
    jLabDirecc3.setRows(2);
    jLabDirecc3.setWrapStyleWord(true);
    jScrollPane10.setViewportView(jLabDirecc3);

    javax.swing.GroupLayout jPanDataambView3Layout = new javax.swing.GroupLayout(jPanDataambView3);
    jPanDataambView3.setLayout(jPanDataambView3Layout);
    jPanDataambView3Layout.setHorizontalGroup(
        jPanDataambView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanDataambView3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanDataambView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jLabel162, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel161, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel164, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanDataambView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanDataambView3Layout.createSequentialGroup()
                    .addGroup(jPanDataambView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanDataambView3Layout.createSequentialGroup()
                            .addComponent(jLabFoli, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabThisIs, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabObserva3, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(3, 3, 3))
    );
    jPanDataambView3Layout.setVerticalGroup(
        jPanDataambView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanDataambView3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanDataambView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabThisIs, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanDataambView3Layout.createSequentialGroup()
                    .addGroup(jPanDataambView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel161, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(jLabNombre3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(jLabFoli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, 0)
                    .addGroup(jPanDataambView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel162, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3)
                    .addGroup(jPanDataambView3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabObserva3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel164, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, 0))
    );

    jPanTableBusqView2.setBackground(new java.awt.Color(255, 255, 255));

    jTabVariosView.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jTabVariosView.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null}
        },
        new String [] {
            "Número", "Nombre", "Tipo"
        }
    ));
    jTabVariosView.setRowHeight(20);
    jTabVariosView.setRowMargin(2);
    jTabVariosView.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            jTabVariosViewKeyPressed(evt);
        }
    });
    jScrollPane5.setViewportView(jTabVariosView);
    if (jTabVariosView.getColumnModel().getColumnCount() > 0) {
        jTabVariosView.getColumnModel().getColumn(0).setMinWidth(80);
        jTabVariosView.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTabVariosView.getColumnModel().getColumn(0).setMaxWidth(180);
        jTabVariosView.getColumnModel().getColumn(2).setMinWidth(80);
        jTabVariosView.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTabVariosView.getColumnModel().getColumn(2).setMaxWidth(200);
    }

    javax.swing.GroupLayout jPanTableBusqView2Layout = new javax.swing.GroupLayout(jPanTableBusqView2);
    jPanTableBusqView2.setLayout(jPanTableBusqView2Layout);
    jPanTableBusqView2Layout.setHorizontalGroup(
        jPanTableBusqView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 591, Short.MAX_VALUE)
        .addGroup(jPanTableBusqView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanTableBusqView2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );
    jPanTableBusqView2Layout.setVerticalGroup(
        jPanTableBusqView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 122, Short.MAX_VALUE)
        .addGroup(jPanTableBusqView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanTableBusqView2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );

    jLayeredPanePerson.setLayer(jPanDataambView3, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPanePerson.setLayer(jPanTableBusqView2, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout jLayeredPanePersonLayout = new javax.swing.GroupLayout(jLayeredPanePerson);
    jLayeredPanePerson.setLayout(jLayeredPanePersonLayout);
    jLayeredPanePersonLayout.setHorizontalGroup(
        jLayeredPanePersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPanePersonLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanTableBusqView2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(jLayeredPanePersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPanePersonLayout.createSequentialGroup()
                .addComponent(jPanDataambView3, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE)))
    );
    jLayeredPanePersonLayout.setVerticalGroup(
        jLayeredPanePersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanTableBusqView2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(jLayeredPanePersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPanePersonLayout.createSequentialGroup()
                .addComponent(jPanDataambView3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE)))
    );

    txtBusqOtros.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtBusqOtrosKeyReleased(evt);
        }
    });

    jButton47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-what.png"))); // NOI18N

    jButton48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jButton48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goma-de-borrar.png"))); // NOI18N
    jButton48.setText("Nueva");
    jButton48.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton48ActionPerformed(evt);
        }
    });

    txtTotOthers.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
    txtTotOthers.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    txtTotOthers.setText("0.00");

    jLabel165.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel165.setText("Total");

    jButton49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jButton49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cash.png"))); // NOI18N
    jButton49.setText("COBRAR");
    jButton49.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton49ActionPerformed(evt);
        }
    });

    jLabel166.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel166.setText("     Buscar ambulante, cargador o cliente");

    jButton50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButton50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas-color.png"))); // NOI18N
    jButton50.setText("  Alta");
    jButton50.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton50ActionPerformed(evt);
        }
    });

    jTabVistaVentaOthers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jTabVistaVentaOthers.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Id", "Cant.", "Descripcion", "Precio", "Importe"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTabVistaVentaOthers.setRowHeight(20);
    jTabVistaVentaOthers.setRowMargin(2);
    jTabVistaVentaOthers.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            jTabVistaVentaOthersKeyPressed(evt);
        }
    });
    jScrollPane7.setViewportView(jTabVistaVentaOthers);
    if (jTabVistaVentaOthers.getColumnModel().getColumnCount() > 0) {
        jTabVistaVentaOthers.getColumnModel().getColumn(0).setMinWidth(50);
        jTabVistaVentaOthers.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTabVistaVentaOthers.getColumnModel().getColumn(0).setMaxWidth(90);
        jTabVistaVentaOthers.getColumnModel().getColumn(1).setMinWidth(60);
        jTabVistaVentaOthers.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTabVistaVentaOthers.getColumnModel().getColumn(1).setMaxWidth(100);
        jTabVistaVentaOthers.getColumnModel().getColumn(3).setMinWidth(80);
        jTabVistaVentaOthers.getColumnModel().getColumn(3).setPreferredWidth(85);
        jTabVistaVentaOthers.getColumnModel().getColumn(3).setMaxWidth(140);
        jTabVistaVentaOthers.getColumnModel().getColumn(4).setMinWidth(90);
        jTabVistaVentaOthers.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTabVistaVentaOthers.getColumnModel().getColumn(4).setMaxWidth(150);
    }

    jButton51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jButton51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/almacen.png"))); // NOI18N
    jButton51.setText("Explanada");
    jButton51.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton51ActionPerformed(evt);
        }
    });

    jLabel167.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel167.setText("Bucar concepto");

    txtBusqConcept.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    txtBusqConcept.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtBusqConceptKeyReleased(evt);
        }
    });

    jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-what.png"))); // NOI18N

    jButton10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas-color.png"))); // NOI18N
    jButton10.setText("Agregar");
    jButton10.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton10ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanVariosLayout = new javax.swing.GroupLayout(jPanVarios);
    jPanVarios.setLayout(jPanVariosLayout);
    jPanVariosLayout.setHorizontalGroup(
        jPanVariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanVariosLayout.createSequentialGroup()
            .addGap(59, 59, 59)
            .addComponent(jLabel166, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(jPanVariosLayout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addGroup(jPanVariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanVariosLayout.createSequentialGroup()
                    .addComponent(txtBusqOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanVariosLayout.createSequentialGroup()
                    .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton51)
                    .addGap(32, 32, 32)
                    .addComponent(jButton49)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel165, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtTotOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addComponent(jLayeredPanConcept)
                .addGroup(jPanVariosLayout.createSequentialGroup()
                    .addComponent(jLabel167, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtBusqConcept, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton10))
                .addComponent(jLayeredPanePerson)))
        .addGroup(jPanVariosLayout.createSequentialGroup()
            .addGap(32, 32, 32)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanVariosLayout.setVerticalGroup(
        jPanVariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanVariosLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(3, 3, 3)
            .addComponent(jLabel166, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanVariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtBusqOtros))
            .addGap(5, 5, 5)
            .addComponent(jLayeredPanePerson, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanVariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanVariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanVariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBusqConcept, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel167, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(5, 5, 5)
            .addComponent(jLayeredPanConcept, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanVariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanVariosLayout.createSequentialGroup()
                    .addGroup(jPanVariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotOthers, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel165, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(228, Short.MAX_VALUE))
                .addGroup(jPanVariosLayout.createSequentialGroup()
                    .addGroup(jPanVariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );

    jPanTransitoInfraccion.setBackground(new java.awt.Color(255, 255, 255));

    jLabel118.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/escudo-sheriff.png"))); // NOI18N
    jLabel118.setText("         Cobro de Infracciones");

    jLabel119.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel119.setText("Buscar");

    jCBoxFilterBusq.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jCBoxFilterBusq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Folio", "Placa o licencia", "Automovil" }));

    txtInFilterBusq.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            txtInFilterBusqFocusGained(evt);
        }
    });
    txtInFilterBusq.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            txtInFilterBusqKeyReleased(evt);
        }
    });

    jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goma-de-borrar.png"))); // NOI18N

    jTabInfraccView.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {"2572", "14-12-2018", "LRL-73-7", null, "300.00"},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
        },
        new String [] {
            "Folio", "Fecha", "Placa / Licencia", "Vehículo", "Importe"
        }
    ));
    jTabInfraccView.setRowHeight(20);
    jTabInfraccView.setRowMargin(2);
    jScrollPane4.setViewportView(jTabInfraccView);
    if (jTabInfraccView.getColumnModel().getColumnCount() > 0) {
        jTabInfraccView.getColumnModel().getColumn(0).setMinWidth(80);
        jTabInfraccView.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTabInfraccView.getColumnModel().getColumn(0).setMaxWidth(180);
        jTabInfraccView.getColumnModel().getColumn(1).setMinWidth(100);
        jTabInfraccView.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTabInfraccView.getColumnModel().getColumn(1).setMaxWidth(220);
        jTabInfraccView.getColumnModel().getColumn(2).setMinWidth(100);
        jTabInfraccView.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTabInfraccView.getColumnModel().getColumn(2).setMaxWidth(250);
        jTabInfraccView.getColumnModel().getColumn(4).setMinWidth(100);
        jTabInfraccView.getColumnModel().getColumn(4).setPreferredWidth(150);
        jTabInfraccView.getColumnModel().getColumn(4).setMaxWidth(250);
    }

    jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas-color.png"))); // NOI18N
    jButton30.setText("Registrar");
    jButton30.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton30ActionPerformed(evt);
        }
    });

    jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lapiz.png"))); // NOI18N
    jButton31.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton31ActionPerformed(evt);
        }
    });

    jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salida.png"))); // NOI18N
    jButton32.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton32ActionPerformed(evt);
        }
    });

    jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/moneda.png"))); // NOI18N
    jButton33.setText("Cobrar");
    jButton33.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton33ActionPerformed(evt);
        }
    });

    jLabel120.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel120.setText("total infracciones:  $");

    jLabtotInfracc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    jLabtotInfracc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabtotInfracc.setText("0.00");
    jLabtotInfracc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

    javax.swing.GroupLayout jPanTransitoInfraccionLayout = new javax.swing.GroupLayout(jPanTransitoInfraccion);
    jPanTransitoInfraccion.setLayout(jPanTransitoInfraccionLayout);
    jPanTransitoInfraccionLayout.setHorizontalGroup(
        jPanTransitoInfraccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanTransitoInfraccionLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jButton30)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel120)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabtotInfracc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(jPanTransitoInfraccionLayout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(jPanTransitoInfraccionLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane4)
            .addGap(5, 5, 5))
        .addGroup(jPanTransitoInfraccionLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(6, 6, 6)
            .addComponent(jCBoxFilterBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtInFilterBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanTransitoInfraccionLayout.setVerticalGroup(
        jPanTransitoInfraccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanTransitoInfraccionLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanTransitoInfraccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanTransitoInfraccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInFilterBusq, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jCBoxFilterBusq, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(jPanTransitoInfraccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanTransitoInfraccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabtotInfracc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(226, Short.MAX_VALUE))
    );

    jPanAreascobros.setBackground(new java.awt.Color(255, 255, 255));
    jPanAreascobros.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    jPanAreascobros.setPreferredSize(new java.awt.Dimension(625, 775));

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/almacen.png"))); // NOI18N
    jLabel1.setText("       Cobro de mantenimiento semanal de areas");

    jCmBxgetAreas.setEditable(true);
    jCmBxgetAreas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jCmBxgetAreas.setMaximumRowCount(15);
    jCmBxgetAreas.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCmBxgetAreasActionPerformed(evt);
        }
    });
    jCmBxgetAreas.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jCmBxgetAreasKeyReleased(evt);
        }
    });

    jPanMantenPay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jPanel8.setBackground(new java.awt.Color(204, 204, 204));

    jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

    jChecMantSem.setBackground(new java.awt.Color(204, 204, 204));
    jChecMantSem.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
    jChecMantSem.setText("Mantenimiento Semanal");
    jChecMantSem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jChecMantSemActionPerformed(evt);
        }
    });
    jChecMantSem.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jChecMantSemKeyReleased(evt);
        }
    });

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel2.setText("Ultimo Pago: -");

    jLabLastMantenim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jChecMantSem, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabLastMantenim, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtMantenIdSem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator1)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jChecMantSem))
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabLastMantenim, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtMantenIdSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))))
    );

    txtIniManten.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    txtIniManten.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtIniManten.setText("0");

    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel4.setText("Semana Inicial");

    jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel5.setText("Semana Final");

    jButMantenSubstract.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButMantenSubstract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menos.png"))); // NOI18N
    jButMantenSubstract.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButMantenSubstractActionPerformed(evt);
        }
    });
    jButMantenSubstract.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jButMantenSubstractKeyReleased(evt);
        }
    });

    txtFinManten.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    txtFinManten.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtFinManten.setText("0");

    jLaFechFinManten.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLaFechFinManten.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLaFechFinManten.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    jLaFechFinManten.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLaFechFinMantenMouseClicked(evt);
        }
        public void mousePressed(java.awt.event.MouseEvent evt) {
            jLaFechFinMantenMousePressed(evt);
        }
    });

    jButMantenMoore.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButMantenMoore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas.png"))); // NOI18N
    jButMantenMoore.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButMantenMooreActionPerformed(evt);
        }
    });
    jButMantenMoore.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jButMantenMooreKeyReleased(evt);
        }
    });

    jPanel9.setBackground(new java.awt.Color(255, 255, 255));

    jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel7.setText("Semanas");

    jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel8.setText("Tarifa");

    jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel9.setText("Importe");

    jLabTarifaMantenim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabTarifaMantenim.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabTarifaMantenim.setText("0.00");
    jLabTarifaMantenim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    jLabTarifaMantenim.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

    jlabImportMantenim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jlabImportMantenim.setText("0.00");

    jLabSemsPaysManten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabSemsPaysManten.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabSemsPaysManten.setText("0");
    jLabSemsPaysManten.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabSemsPaysManten, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlabImportMantenim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabTarifaMantenim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap())
    );
    jPanel9Layout.setVerticalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addComponent(jLabTarifaMantenim, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jlabImportMantenim))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabSemsPaysManten))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel9)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLaFechIniManten.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLaFechIniManten.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLaFechIniManten.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    javax.swing.GroupLayout jPanMantenPayLayout = new javax.swing.GroupLayout(jPanMantenPay);
    jPanMantenPay.setLayout(jPanMantenPayLayout);
    jPanMantenPayLayout.setHorizontalGroup(
        jPanMantenPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanMantenPayLayout.createSequentialGroup()
            .addGroup(jPanMantenPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanMantenPayLayout.createSequentialGroup()
                    .addGroup(jPanMantenPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanMantenPayLayout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(13, 13, 13))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanMantenPayLayout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(txtIniManten, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLaFechIniManten, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanMantenPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanMantenPayLayout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanMantenPayLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButMantenSubstract, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(txtFinManten, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jLaFechFinManten, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButMantenMoore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, 0))
    );
    jPanMantenPayLayout.setVerticalGroup(
        jPanMantenPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanMantenPayLayout.createSequentialGroup()
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanMantenPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanMantenPayLayout.createSequentialGroup()
                    .addGroup(jPanMantenPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanMantenPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButMantenSubstract, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtIniManten)
                        .addComponent(jLaFechFinManten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFinManten)
                        .addComponent(jButMantenMoore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLaFechIniManten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
    );

    jPanBasuraContenPays.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jPanel10.setBackground(new java.awt.Color(204, 204, 204));

    jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

    jChecBasura.setBackground(new java.awt.Color(204, 204, 204));
    jChecBasura.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
    jChecBasura.setText("Basura");
    jChecBasura.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jChecBasuraActionPerformed(evt);
        }
    });
    jChecBasura.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jChecBasuraKeyReleased(evt);
        }
    });

    jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel13.setText("Ultimo Pago: -");

    jLabLastBasura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jChecBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel13)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabLastBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator2)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabLastBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jChecBasura))))
    );

    txtIniBasura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    txtIniBasura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtIniBasura.setText("0");

    jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel15.setText("Semana Inicial");

    jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel16.setText("Semana Final");

    jPanel11.setBackground(new java.awt.Color(255, 255, 255));
    jPanel11.setMaximumSize(new java.awt.Dimension(200, 32767));

    jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel18.setText("Semanas");

    jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel19.setText("Tarifa");

    jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel20.setText("Importe");

    jLabTarifaBasura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabTarifaBasura.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabTarifaBasura.setText("0.00");
    jLabTarifaBasura.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    jLabTarifaBasura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

    jlabImportBasura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jlabImportBasura.setText("0.00");

    jLabSemsPaysBasura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabSemsPaysBasura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabSemsPaysBasura.setText("0");

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel18)
                .addComponent(jLabel19)
                .addComponent(jLabel20))
            .addGap(23, 23, 23)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabSemsPaysBasura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlabImportBasura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabTarifaBasura))))
            .addContainerGap())
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                    .addComponent(jLabTarifaBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jlabImportBasura))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabSemsPaysBasura))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel20)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLaFechFinBasura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLaFechFinBasura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLaFechFinBasura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jButBasuraMoore.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButBasuraMoore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas.png"))); // NOI18N
    jButBasuraMoore.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButBasuraMooreActionPerformed(evt);
        }
    });
    jButBasuraMoore.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jButBasuraMooreKeyReleased(evt);
        }
    });

    txtFinBasura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    txtFinBasura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtFinBasura.setText("0");

    jButBasuraSubstract.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButBasuraSubstract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menos.png"))); // NOI18N
    jButBasuraSubstract.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButBasuraSubstractActionPerformed(evt);
        }
    });
    jButBasuraSubstract.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jButBasuraSubstractKeyReleased(evt);
        }
    });

    jLaFechIniBasura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLaFechIniBasura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLaFechIniBasura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    javax.swing.GroupLayout jPanBasuraContenPaysLayout = new javax.swing.GroupLayout(jPanBasuraContenPays);
    jPanBasuraContenPays.setLayout(jPanBasuraContenPaysLayout);
    jPanBasuraContenPaysLayout.setHorizontalGroup(
        jPanBasuraContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanBasuraContenPaysLayout.createSequentialGroup()
            .addGroup(jPanBasuraContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(jPanBasuraContenPaysLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanBasuraContenPaysLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtIniBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLaFechIniBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanBasuraContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanBasuraContenPaysLayout.createSequentialGroup()
                    .addGap(83, 83, 83)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanBasuraContenPaysLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButBasuraSubstract, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(txtFinBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jLaFechFinBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jButBasuraMoore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(jPanBasuraContenPaysLayout.createSequentialGroup()
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtBasurIdSem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanBasuraContenPaysLayout.setVerticalGroup(
        jPanBasuraContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanBasuraContenPaysLayout.createSequentialGroup()
            .addGroup(jPanBasuraContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtBasurIdSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanBasuraContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanBasuraContenPaysLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanBasuraContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanBasuraContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanBasuraContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIniBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLaFechIniBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanBasuraContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButBasuraSubstract, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLaFechFinBasura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFinBasura, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButBasuraMoore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanBasuraContenPaysLayout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 0, 0))
    );

    jPanPiliceContenPays.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jPanel12.setBackground(new java.awt.Color(204, 204, 204));

    jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

    jChecPolicia.setBackground(new java.awt.Color(204, 204, 204));
    jChecPolicia.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
    jChecPolicia.setText("Policia");
    jChecPolicia.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jChecPoliciaActionPerformed(evt);
        }
    });

    jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel24.setText("Ultimo Pago: -");

    jLabLastPolicia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
    jPanel12.setLayout(jPanel12Layout);
    jPanel12Layout.setHorizontalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jChecPolicia, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel24)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabLastPolicia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel12Layout.setVerticalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator3)
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(jLabLastPolicia, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jChecPolicia))))
    );

    txtIniPolic.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    txtIniPolic.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtIniPolic.setText("0");

    jLaFechIniPolicia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLaFechIniPolicia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLaFechIniPolicia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel26.setText("Semana Inicial");

    jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel27.setText("Semana Final");

    jButPolicSubstract.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButPolicSubstract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menos.png"))); // NOI18N
    jButPolicSubstract.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButPolicSubstractActionPerformed(evt);
        }
    });

    txtFinPolicia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    txtFinPolicia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtFinPolicia.setText("0");

    jLaFechFinPolicia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLaFechFinPolicia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLaFechFinPolicia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jButPolicMoore.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButPolicMoore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas.png"))); // NOI18N
    jButPolicMoore.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButPolicMooreActionPerformed(evt);
        }
    });

    jPanel13.setBackground(new java.awt.Color(255, 255, 255));

    jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel29.setText("Semanas");

    jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel30.setText("Tarifa");

    jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel31.setText("Importe");

    jLabTarifPolicia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabTarifPolicia.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabTarifPolicia.setText("0.00");
    jLabTarifPolicia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    jLabTarifPolicia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

    jlabImportPolicia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jlabImportPolicia.setText("0.00");

    jLabSemsPaysPolis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabSemsPaysPolis.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabSemsPaysPolis.setText("0");

    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
    jPanel13.setLayout(jPanel13Layout);
    jPanel13Layout.setHorizontalGroup(
        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel13Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel29)
                .addComponent(jLabel30)
                .addComponent(jLabel31))
            .addGap(23, 23, 23)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabSemsPaysPolis, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlabImportPolicia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabTarifPolicia))))
            .addContainerGap())
    );
    jPanel13Layout.setVerticalGroup(
        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel13Layout.createSequentialGroup()
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                    .addComponent(jLabTarifPolicia, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jlabImportPolicia))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabSemsPaysPolis))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel31)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanPiliceContenPaysLayout = new javax.swing.GroupLayout(jPanPiliceContenPays);
    jPanPiliceContenPays.setLayout(jPanPiliceContenPaysLayout);
    jPanPiliceContenPaysLayout.setHorizontalGroup(
        jPanPiliceContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanPiliceContenPaysLayout.createSequentialGroup()
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtPoliciaIdSem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(jPanPiliceContenPaysLayout.createSequentialGroup()
            .addGroup(jPanPiliceContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(jPanPiliceContenPaysLayout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanPiliceContenPaysLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtIniPolic, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLaFechIniPolicia, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanPiliceContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanPiliceContenPaysLayout.createSequentialGroup()
                    .addGap(83, 83, 83)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanPiliceContenPaysLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButPolicSubstract, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(txtFinPolicia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jLaFechFinPolicia, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jButPolicMoore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanPiliceContenPaysLayout.setVerticalGroup(
        jPanPiliceContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanPiliceContenPaysLayout.createSequentialGroup()
            .addGroup(jPanPiliceContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPoliciaIdSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanPiliceContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanPiliceContenPaysLayout.createSequentialGroup()
                    .addGroup(jPanPiliceContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanPiliceContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButPolicSubstract, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtIniPolic)
                        .addComponent(jLaFechIniPolicia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLaFechFinPolicia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFinPolicia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButPolicMoore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
    );

    jPanResguardContenPays.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jPanel14.setBackground(new java.awt.Color(204, 204, 204));

    jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

    jChecResguardo.setBackground(new java.awt.Color(204, 204, 204));
    jChecResguardo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
    jChecResguardo.setText("Resguardos");
    jChecResguardo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jChecResguardoActionPerformed(evt);
        }
    });

    jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel35.setText("Ultimo Pago: -");

    jLabLastResguardo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
    jPanel14.setLayout(jPanel14Layout);
    jPanel14Layout.setHorizontalGroup(
        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel14Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jChecResguardo, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel35)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabLastResguardo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addComponent(txtResguardIdSem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanel14Layout.setVerticalGroup(
        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator4)
        .addGroup(jPanel14Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(jLabLastResguardo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jChecResguardo))))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(txtResguardIdSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    txtIniResg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    txtIniResg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtIniResg.setText("0");

    jLaFechIniResguardos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLaFechIniResguardos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLaFechIniResguardos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel37.setText("Semana Inicial");

    jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel38.setText("Semana Final");

    jButResguardSubstarct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButResguardSubstarct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menos.png"))); // NOI18N
    jButResguardSubstarct.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButResguardSubstarctActionPerformed(evt);
        }
    });

    txtFinResguard.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    txtFinResguard.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtFinResguard.setText("0");

    jLaFechFinResguard.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLaFechFinResguard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLaFechFinResguard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

    jPanel15.setBackground(new java.awt.Color(255, 255, 255));

    jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel40.setText("Semanas");

    jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel41.setText("Tarifa");

    jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel42.setText("Importe");

    jLabTarifResguard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabTarifResguard.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabTarifResguard.setText("0.00");
    jLabTarifResguard.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
    jLabTarifResguard.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

    jlabImportResguard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jlabImportResguard.setText("0.00");

    jLabSemsPaysResg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabSemsPaysResg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabSemsPaysResg.setText("0");

    javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
    jPanel15.setLayout(jPanel15Layout);
    jPanel15Layout.setHorizontalGroup(
        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel15Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabSemsPaysResg, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel41)
                        .addComponent(jLabel42))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlabImportResguard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabTarifResguard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap())
    );
    jPanel15Layout.setVerticalGroup(
        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel15Layout.createSequentialGroup()
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                    .addComponent(jLabTarifResguard, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jlabImportResguard))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabSemsPaysResg))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel42)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jButResguardMoore.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButResguardMoore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mas.png"))); // NOI18N
    jButResguardMoore.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButResguardMooreActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanResguardContenPaysLayout = new javax.swing.GroupLayout(jPanResguardContenPays);
    jPanResguardContenPays.setLayout(jPanResguardContenPaysLayout);
    jPanResguardContenPaysLayout.setHorizontalGroup(
        jPanResguardContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanResguardContenPaysLayout.createSequentialGroup()
            .addGroup(jPanResguardContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanResguardContenPaysLayout.createSequentialGroup()
                    .addGroup(jPanResguardContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanResguardContenPaysLayout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanResguardContenPaysLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(txtIniResg, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLaFechIniResguardos, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanResguardContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanResguardContenPaysLayout.createSequentialGroup()
                            .addGap(83, 83, 83)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanResguardContenPaysLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButResguardSubstarct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(txtFinResguard, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jLaFechFinResguard, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jButResguardMoore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(1, 1, 1))
    );
    jPanResguardContenPaysLayout.setVerticalGroup(
        jPanResguardContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanResguardContenPaysLayout.createSequentialGroup()
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanResguardContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanResguardContenPaysLayout.createSequentialGroup()
                    .addGroup(jPanResguardContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanResguardContenPaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButResguardSubstarct, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtIniResg)
                        .addComponent(jLaFechIniResguardos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLaFechFinResguard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFinResguard, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButResguardMoore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 1, Short.MAX_VALUE))
    );

    jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cash.png"))); // NOI18N
    jButton15.setText("Cobrar");
    jButton15.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton15ActionPerformed(evt);
        }
    });

    txtResultSum.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    txtResultSum.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    txtResultSum.setText("0.00");

    jLabel46.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel46.setText("Total");

    jButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reloj.png"))); // NOI18N
    jButton45.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton45ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanAreascobrosLayout = new javax.swing.GroupLayout(jPanAreascobros);
    jPanAreascobros.setLayout(jPanAreascobrosLayout);
    jPanAreascobrosLayout.setHorizontalGroup(
        jPanAreascobrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanAreascobrosLayout.createSequentialGroup()
            .addGap(90, 90, 90)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
        .addGroup(jPanAreascobrosLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanAreascobrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanResguardContenPays, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanPiliceContenPays, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanBasuraContenPays, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanMantenPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(jPanAreascobrosLayout.createSequentialGroup()
            .addGap(23, 23, 23)
            .addComponent(jCmBxgetAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(jPanAreascobrosLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtResultSum, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(64, 64, 64))
    );
    jPanAreascobrosLayout.setVerticalGroup(
        jPanAreascobrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanAreascobrosLayout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jCmBxgetAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanMantenPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanBasuraContenPays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanPiliceContenPays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanResguardContenPays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanAreascobrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanAreascobrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtResultSum)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(121, Short.MAX_VALUE))
    );

    jLayeredPane1.setLayer(jPanambulantes, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(jPanCargadores, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(jPanVarios, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(jPanTransitoInfraccion, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(jPanAreascobros, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
    jLayeredPane1.setLayout(jLayeredPane1Layout);
    jLayeredPane1Layout.setHorizontalGroup(
        jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanAreascobros, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap()))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanambulantes, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap()))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanTransitoInfraccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanCargadores, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addContainerGap()))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanVarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)))
    );
    jLayeredPane1Layout.setVerticalGroup(
        jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanAreascobros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(144, 144, 144)))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanambulantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(134, 134, 134)))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanTransitoInfraccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(73, 73, 73)))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanCargadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(104, 104, 104)))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanVarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(94, 94, 94)))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
            .addGap(0, 0, 0)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(5, 5, 5)))
            .addGap(107, 107, 107))
        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
    );
    }// </editor-fold>//GEN-END:initComponents

    private void jCBoxDuracInscripcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBoxDuracInscripcActionPerformed
       int var = jCBoxDuracInscripc.getSelectedIndex();
        if(var > -1){
            jLabTarifaInscripcion.setText(tarifas[var+6]);
           String multi1 = jLabDstoInscripcion.getText(),
                   multi2 = jLabTarifaInscripcion.getText(),
                   resAmbu = jLabImporteInscripcion.getText(),
                   tot = txtResultAmbu.getText(),
                    aux="",auxSems="",auxInsc="";
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                   BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                   BigDecimal amountAmbusMenos = new BigDecimal(resAmbu);//cantidad recivida
                   BigDecimal totalAll = new BigDecimal(tot);//cantidad recivida
              //     BigDecimal amountNumSems = new BigDecimal(numSems);//cantidad recivida
aux = func.getDifference(amountTwo, func.percentage(amountTwo, amountOne)).toString();
jLabImporteInscripcion.setText(aux);
if(jCheckSemPaysAmb.isSelected()){
    auxSems = jLabImporteSemanas.getText();
   aux = func.getSum(new BigDecimal(auxSems), new BigDecimal(aux)).toString();
}
if(jCheckResguardAmb.isSelected()){
    auxInsc = jLabImporteResguard.getText();
   aux = func.getSum(new BigDecimal(auxInsc), new BigDecimal(aux)).toString();
}
txtResultAmbu.setText(aux);
        }      
    }//GEN-LAST:event_jCBoxDuracInscripcActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jPanambulantes.setVisible(false);
        jPanCargadores.setVisible(false);
        jPanVarios.setVisible(false);
        jPanTransitoInfraccion.setVisible(false);
        jPanAreascobros .setVisible(true);  
        jCmBxgetAreas.requestFocus(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jPanambulantes.setVisible(true);
        jPanCargadores.setVisible(false);
        jPanVarios.setVisible(false);
        jPanTransitoInfraccion.setVisible(false);
        jPanAreascobros .setVisible(false); 
        txtBuscAmbulante.requestFocus(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jPanambulantes.setVisible(false);
        jPanCargadores.setVisible(true);
        jPanVarios.setVisible(false);
        jPanTransitoInfraccion.setVisible(false);
        jPanAreascobros .setVisible(false); 
        txtBuscCargadores.requestFocus(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jPanambulantes.setVisible(false);
        jPanCargadores.setVisible(false);
        jPanVarios.setVisible(false);
        jPanTransitoInfraccion.setVisible(true);
        mostrarTabInfrac("folio","");
        jPanAreascobros .setVisible(false); 
        txtInFilterBusq.requestFocus(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         jPanambulantes.setVisible(false);
        jPanCargadores.setVisible(false);
        jPanVarios.setVisible(true);
        jPanTransitoInfraccion.setVisible(false);
        jPanAreascobros .setVisible(false); 
       txtBusqOtros.requestFocus(true);
 
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
     jPanRentCargador.setVisible(false);
     jPanCargadorInscr.setVisible(true);
     jPanSemCargad.setVisible(true);  
     txtTotalCarg.setText("0.00");
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
           if( jCkBoxInscripcion.isSelected() ){
           jCkBoxInscripcion.doClick();
       }
       
       if( jCkBoxSemanas.isSelected() ){
           jCkBoxSemanas.doClick();
       }
       
     jPanRentCargador.setVisible(true);
     jPanCargadorInscr.setVisible(false);
     jPanSemCargad.setVisible(false);
     comoNewRentCarg();
                String tarif = jLabTarifRentCarg.getText(),
                    condon = jLabCondonacRentDiab.getText(),
                    auxD ="";
            BigDecimal tari = new BigDecimal(tarif);
            BigDecimal condo = new BigDecimal(condon);
            auxD = func.percentage(tari, condo).toString();
        jLabImportRentDiab.setText(func.getDifference(tari,new BigDecimal(auxD)).toString());
        txtTotalCarg.setText(jLabImportRentDiab.getText());
        

    }//GEN-LAST:event_jButton41ActionPerformed

    private void jLaFechFinMantenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLaFechFinMantenMousePressed
        jDialCalendarMantenim.setLocationRelativeTo(this);
        jDialCalendarMantenim.setVisible(true);
        jDialCalendarMantenim.setEnabled(true);
    }//GEN-LAST:event_jLaFechFinMantenMousePressed

    private void jCalendar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCalendar1MouseClicked

        
    }//GEN-LAST:event_jCalendar1MouseClicked

    private void jLaFechFinMantenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLaFechFinMantenMouseClicked
         
    }//GEN-LAST:event_jLaFechFinMantenMouseClicked

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        String var = datCtrl.getFechaCal(jCalendar1);
        jLaFechFinManten.setText(var);
        jDialCalendarMantenim.dispose();       
    }//GEN-LAST:event_jButton42ActionPerformed

    private void txtBuscAmbulanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscAmbulanteKeyPressed
        int oprime = evt.getKeyCode();
        String var = txtBuscAmbulante.getText();     
        if(!var.isEmpty()){
           mostrarTabla1(var);
           jPanTableBusqView.setVisible(true);
           jPanDataambView1.setVisible(false);
           if(oprime == KeyEvent.VK_ENTER){
                jTabDatosAmbulante.requestFocus(true);
            }
        }else{
            jPanTableBusqView.setVisible(false);
        }
    }//GEN-LAST:event_txtBuscAmbulanteKeyPressed

    private void jTabDatosAmbulanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabDatosAmbulanteKeyPressed
        int oprime = evt.getKeyCode(),
                fila=jTabDatosAmbulante.getSelectedRow();
         if(oprime == KeyEvent.VK_ENTER && fila > -1 ){
             String opc = jTabDatosAmbulante.getValueAt(fila, 0).toString();
             datasAmbUltSem = func.getAmbus1(opc);//arreglo para mostra info en jpanelInfo
             mostrarJpanAmbulantes(opc);
  //           JOptionPane.showMessageDialog(null, "Obtener ultimo pago semana: "+opc);
            jLabIdAmbu.setText(jTabDatosAmbulante.getValueAt(fila, 0).toString());
             jLabNomAmb.setText(jTabDatosAmbulante.getValueAt(fila, 1).toString());
           jLabDirecc1.setText(datasAmbUltSem[0]);
            jLabGiro1.setText(datasAmbUltSem[1]);
            jLabObserva1.setText(datasAmbUltSem[2]);
            // System.out.println("UltimaSem AMb "+datasAmbUltSem[3]+"********-----");
            jPanTableBusqView.setVisible(false);
            jPanDataambView1.setVisible(true);
            txtBuscAmbulante.setText("");
            txtBuscAmbulante.requestFocus(true);
            
            jCheckSemPaysAmb.doClick();
           
    }
    }//GEN-LAST:event_jTabDatosAmbulanteKeyPressed

    private void txtBuscCargadoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscCargadoresKeyPressed
            int oprime = evt.getKeyCode();
            String var = txtBuscCargadores.getText();
            if(!var.isEmpty()){
                mostrarTablaCargadores(var);
                jPanDataambView2.setVisible(false);
                jPanTableBusqView1.setVisible(true);
                if(oprime == KeyEvent.VK_ENTER){
                    jTabCargadoresView.requestFocus(true);
                }
            }else{
                jPanTableBusqView1.setVisible(false);
            }
    }//GEN-LAST:event_txtBuscCargadoresKeyPressed

    private void jTabCargadoresViewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabCargadoresViewKeyPressed
       int oprime = evt.getKeyCode(),
             fila=jTabCargadoresView.getSelectedRow();
        
         if(oprime == KeyEvent.VK_ENTER && fila >  -1){
             String opc = jTabCargadoresView.getValueAt(fila, 0).toString();
             datasCarg = func.getCargad(opc);//arreglo para mostra info en jpanelInfo
//aca va metodo para cargar datos en jPaneles
            jlabIdCargador.setText(jTabCargadoresView.getValueAt(fila, 0).toString());
             jLabNombreCargadores.setText(jTabCargadoresView.getValueAt(fila, 1).toString());
             
             jLabDirecCargad.setText(datasCarg[2]);
              jLabObservaCarg.setText(datasCarg[3]);
             if(datasCarg[4].equals("0"))
                jLabGiroCarg.setText("Propio");
             else if(datasCarg[4].equals("1"))
                jLabGiroCarg.setText("Renta");
           
            jPanTableBusqView1.setVisible(false);
            jPanDataambView2.setVisible(true);
            txtBuscCargadores.setText("");
            txtBuscCargadores.requestFocus(true);
            mostrarJpanCargadores(datasCarg);
            jCkBoxSemanas.doClick();
        }
    }//GEN-LAST:event_jTabCargadoresViewKeyPressed

    private void jCmBxgetAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmBxgetAreasActionPerformed
        bandC++;
        if(bandC == 1){
           //METER CONSULTA ACA
          // mostrarJpanNew();
     
       }else{
           bandC=0;
       }
    }//GEN-LAST:event_jCmBxgetAreasActionPerformed

    private void jButMantenSubstractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButMantenSubstractActionPerformed
        String ini = txtIniManten.getText(),
                fin = txtFinManten.getText(),
                multi1 ="",
                multi2 = "",
                tot="";
        String[] arr = null;
        String aux="";
        int numeradorSem=Integer.parseInt(jLabSemsPaysManten.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
             if(Integer.parseInt(ini) < Integer.parseInt(fin)){
                
                    idSemMantini--;
                    arr = contrl.regSemanas(idSemMantini);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtFinManten.setText(arr[2]);
                   jLaFechFinManten.setText(aux);       
                   jLabSemsPaysManten.setText(Integer.toString(numeradorSem-1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jlabImportMantenim.getText();
                    multi2 = jLabTarifaMantenim.getText();
                     tot = txtResultSum.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                    BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                    BigDecimal amountBasuraMenos = new BigDecimal(tot);//cantidad recivida
                    
                   jlabImportMantenim.setText(func.getDifference(amountOne, amountTwo).toString());
                   txtResultSum.setText(func.getDifference(amountBasuraMenos,amountTwo).toString());
           
             }else{
                 JOptionPane.showMessageDialog(null, "Inicio no pude ser mayor que Fecha fin");
             }
    }//GEN-LAST:event_jButMantenSubstractActionPerformed

    private void jButMantenMooreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButMantenMooreActionPerformed
        String ini = txtIniManten.getText(),
        fin = txtFinManten.getText(),
        multi1 = "",
        multi2 = "",
        tot="";
        String[] arr = null;
        String aux="";
        int numeradorSem=Integer.parseInt(jLabSemsPaysManten.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
                    idSemMantini++;
                    arr = contrl.regSemanas(idSemMantini);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                   txtFinManten.setText(arr[2]);
                   jLaFechFinManten.setText(aux);       
                   jLabSemsPaysManten.setText(Integer.toString(numeradorSem+1));
                   /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jLabSemsPaysManten.getText();
                    multi2 = jLabTarifaMantenim.getText();
                    tot = txtResultSum.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                    BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                    BigDecimal amountTres = new BigDecimal(tot);//total recabado
                   jlabImportMantenim.setText(func.multiplicaAmount(amountOne, amountTwo).toString());
                   txtResultSum.setText(func.getSum(amountTres,amountTwo).toString());
    }//GEN-LAST:event_jButMantenMooreActionPerformed

    private void jButBasuraSubstractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButBasuraSubstractActionPerformed
                String ini = txtIniBasura.getText(),
                fin = txtFinBasura.getText(),
                multi1 ="",
                multi2 = "",
                tot="";
        String[] arr = null;
        String aux="";
        int numeradorSem=Integer.parseInt(jLabSemsPaysBasura.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
             if(Integer.parseInt(ini) < Integer.parseInt(fin)){
               
                    idSemBasura--;
                    arr = contrl.regSemanas(idSemBasura);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtFinBasura.setText(arr[2]);
                   jLaFechFinBasura.setText(aux);       
                   jLabSemsPaysBasura.setText(Integer.toString(numeradorSem-1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jlabImportBasura.getText();
                   multi2 = jLabTarifaBasura.getText();
                   tot = txtResultSum.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                   BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                   BigDecimal amountTresBasurMenos = new BigDecimal(tot);//cantidad recivida
                    
                   jlabImportBasura.setText(func.getDifference(amountOne, amountTwo).toString());
                   txtResultSum.setText(func.getDifference(amountTresBasurMenos,amountTwo).toString());
             }else{
                 JOptionPane.showMessageDialog(null, "Inicio no pude ser mayor que Fecha fin");
             }
    }//GEN-LAST:event_jButBasuraSubstractActionPerformed

    private void jButBasuraMooreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButBasuraMooreActionPerformed
                String ini = txtIniBasura.getText(),
                fin = txtFinBasura.getText(),
                multi1 ="",
                multi2 = "",
                tot="";
                String[] arr = null;
                String aux="";
                int numeradorSem=Integer.parseInt(jLabSemsPaysBasura.getText());
                   //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
         
                    idSemBasura++;
                    arr = contrl.regSemanas(idSemBasura);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtFinBasura.setText(arr[2]);
                   jLaFechFinBasura.setText(aux);       
                   jLabSemsPaysBasura.setText(Integer.toString(numeradorSem+1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jLabSemsPaysBasura.getText();
                    multi2 = jLabTarifaBasura.getText();
                     tot = txtResultSum.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                    BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                    BigDecimal amountTresBasur = new BigDecimal(tot);//cantidad recivida
                   jlabImportBasura.setText(func.multiplicaAmount(amountOne, amountTwo).toString());
                       txtResultSum.setText(func.getSum(amountTresBasur,amountTwo).toString());
    }//GEN-LAST:event_jButBasuraMooreActionPerformed

    private void jButPolicSubstractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButPolicSubstractActionPerformed
        String ini = txtIniPolic.getText(),
                fin = txtFinPolicia.getText(),
                multi1 = "",
                multi2 = "",
                tot="";
        String[] arr = null;
        String aux="";
        int numeradorSem=Integer.parseInt(jLabSemsPaysPolis.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
             if(Integer.parseInt(ini) < Integer.parseInt(fin)){
         
                    idSemPolicia--;
                    arr = contrl.regSemanas(idSemPolicia);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtFinPolicia.setText(arr[2]);
                   jLaFechFinPolicia.setText(aux);       
                   jLabSemsPaysPolis.setText(Integer.toString(numeradorSem-1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jlabImportPolicia.getText();
                    multi2 = jLabTarifPolicia.getText();
                    tot = txtResultSum.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                    BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                    BigDecimal amountPoliceMenos = new BigDecimal(tot);//cantidad recivida
                    
                   jlabImportPolicia.setText(func.getDifference(amountOne, amountTwo).toString());
                   txtResultSum.setText(func.getDifference(amountPoliceMenos,amountTwo).toString());
             }else{
                 JOptionPane.showMessageDialog(null, "Inicio no pude ser mayor que Fecha fin");
             }
    }//GEN-LAST:event_jButPolicSubstractActionPerformed

    private void jButPolicMooreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButPolicMooreActionPerformed
                 String ini = txtIniPolic.getText(),
                fin = txtFinPolicia.getText(),
                multi1 ="",
                multi2 = "",
                tot="";
        String[] arr = null;
        String aux="";
        int numeradorSem=Integer.parseInt(jLabSemsPaysPolis.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
                    idSemPolicia++;
                    arr = contrl.regSemanas(idSemPolicia);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtFinPolicia.setText(arr[2]);
                   jLaFechFinPolicia.setText(aux);       
                   jLabSemsPaysPolis.setText(Integer.toString(numeradorSem+1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jlabImportPolicia.getText();
                    multi2 = jLabTarifPolicia.getText();
                    tot = txtResultSum.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                    BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                    BigDecimal amountTresPolice = new BigDecimal(tot);//cantidad total
                   jlabImportPolicia.setText(func.getSum(amountOne, amountTwo).toString());
                   txtResultSum.setText(func.getSum(amountTresPolice,amountTwo).toString());
    }//GEN-LAST:event_jButPolicMooreActionPerformed

    private void jButResguardSubstarctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButResguardSubstarctActionPerformed
                String ini = txtIniResg.getText(),
                fin = txtFinResguard.getText(),
                multi1 ="",
                multi2 = "",multi3="",
                tot ="";
        String[] arr = null;
        String aux="";
        int numeradorSem=Integer.parseInt(jLabSemsPaysResg.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
             if(Integer.parseInt(ini) < Integer.parseInt(fin)){
              
                    idSemResguard--;
                    arr = contrl.regSemanas(idSemResguard);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtFinResguard.setText(arr[2]);
                   jLaFechFinResguard.setText(aux);//ponemos el formato 3 may. - 11 may.   
                   jLabSemsPaysResg.setText(Integer.toString(numeradorSem-1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                    multi1 = jlabImportResguard.getText();
                    multi2 = jLabTarifResguard.getText();
                    multi3 = jLabSemsPaysResg.getText();
                    if(multi3.equals("0")){
                        JOptionPane.showMessageDialog(null, "Semanas a pagar = 0, \n Verifique por favor");
                    }else{
                        tot = txtResultSum.getText();//obtenemos el total a cobrar
                        BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                        BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                        BigDecimal amountTresResg = new BigDecimal(tot);//cantidad recivida
                        
                        jlabImportResguard.setText(func.getDifference(amountOne, amountTwo).toString());
                         txtResultSum.setText(func.getDifference(amountTresResg,amountTwo).toString());
                    }
             }else{
                 JOptionPane.showMessageDialog(null, "Inicio no pude ser mayor que Fecha fin");
             }
             
    }//GEN-LAST:event_jButResguardSubstarctActionPerformed

    private void jButResguardMooreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButResguardMooreActionPerformed
         String ini = txtIniResg.getText(),
                fin = txtFinResguard.getText(),
                multi1 ="",
                multi2 = "",
                 tot="";
        String[] arr = null;
        String aux="";
        int numeradorSem=Integer.parseInt(jLabSemsPaysResg.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
       
                    idSemResguard++;
                    arr = contrl.regSemanas(idSemResguard);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtFinResguard.setText(arr[2]);
                   jLaFechFinResguard.setText(aux);       
                   jLabSemsPaysResg.setText(Integer.toString(numeradorSem+1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                    multi1 = jLabSemsPaysResg.getText();
                    multi2 = jLabTarifResguard.getText();
                    tot = txtResultSum.getText();
                    BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                    BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                    BigDecimal amountTresResg = new BigDecimal(tot);//cantidad recivida
                   jlabImportResguard.setText(func.multiplicaAmount(amountOne, amountTwo).toString());
                   txtResultSum.setText(func.getSum(amountTresResg,amountTwo).toString());
    }//GEN-LAST:event_jButResguardMooreActionPerformed

    private void jChecMantSemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChecMantSemActionPerformed
            String tot = txtResultSum.getText(),
                    costMant = jLabTarifaMantenim.getText(),
                    numSemanas = "";/*Hacer suma de total Total*/
            
            BigDecimal amountOne = new BigDecimal(tot);
            BigDecimal amountTwo = new BigDecimal(costMant);//cantidad recivida
            if(jChecMantSem.isSelected()){
                jButMantenSubstract.setEnabled(true);
                jButMantenMoore.setEnabled(true);
                 jLabSemsPaysManten.setText("1");
                numSemanas = jLabSemsPaysManten.getText();
                BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad recivida
                txtResultSum.setText(func.getSum(amountOne, func.multiplicaAmount(amountTwo, amountTres)).toString());
                 jlabImportMantenim.setText(jLabTarifaMantenim.getText());
            }else{
                jButMantenSubstract.setEnabled(false);
                jButMantenMoore.setEnabled(false);
                numSemanas = jLabSemsPaysManten.getText();
                BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad recivida
                txtResultSum.setText(func.getDifference(amountOne, func.multiplicaAmount(amountTwo, amountTres)).toString());
                jLabSemsPaysManten.setText("1");
                jlabImportMantenim.setText("0.00");
          }
    }//GEN-LAST:event_jChecMantSemActionPerformed

    private void jChecBasuraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChecBasuraActionPerformed
         String tot = txtResultSum.getText(),
                    costMant = jLabTarifaBasura.getText(),
                 numSemanas = "";/*Hacer suma de total Total*/

         BigDecimal amountOne = new BigDecimal(tot);
            BigDecimal amountTwo = new BigDecimal(costMant);//cantidad recivida
 
         if(jChecBasura.isSelected()){
                jButBasuraSubstract.setEnabled(true);
                jButBasuraMoore.setEnabled(true);
                 jLabSemsPaysBasura.setText("1");
                 numSemanas = jLabSemsPaysBasura.getText();
                 BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad totalonas
                 txtResultSum.setText(func.getSum(amountOne, func.multiplicaAmount(amountTwo, amountTres)).toString());
                 jlabImportBasura.setText(jLabTarifaBasura.getText());
            }else{
                jButBasuraSubstract.setEnabled(false);
                jButBasuraMoore.setEnabled(false);
                numSemanas = jLabSemsPaysBasura.getText();
                BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad totalonas
                txtResultSum.setText(func.getDifference(amountOne, func.multiplicaAmount(amountTwo, amountTres)).toString());
                jLabSemsPaysBasura.setText("0");
                jlabImportBasura.setText("0.00");
            }
    }//GEN-LAST:event_jChecBasuraActionPerformed

    private void jChecPoliciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChecPoliciaActionPerformed
            String tot = txtResultSum.getText(),
            costMant = jLabTarifPolicia.getText(),
            numSemanas = "";/*Hacer suma de total Total*/
            
            BigDecimal amountOne = new BigDecimal(tot);
            BigDecimal amountTwo = new BigDecimal(costMant);//cantidad recivida

        if(jChecPolicia.isSelected()){
                 jButPolicSubstract.setEnabled(true);
                 jButPolicMoore.setEnabled(true);
                 jLabSemsPaysPolis.setText("1");
                 
                 numSemanas = jLabSemsPaysPolis.getText();
                 BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad recivida
                 txtResultSum.setText(func.getSum(amountOne, func.multiplicaAmount(amountTwo, amountTres)).toString());
                 jlabImportPolicia.setText(jLabTarifPolicia.getText());
        }else{
                jButPolicSubstract.setEnabled(false);
                jButPolicMoore.setEnabled(false);
                
                 numSemanas = jLabSemsPaysPolis.getText();
                 BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad recivida
                txtResultSum.setText(func.getDifference(amountOne, func.multiplicaAmount(amountTwo, amountTres)).toString());
                jLabSemsPaysPolis.setText("0");
                jlabImportPolicia.setText("0.00");
            }
    }//GEN-LAST:event_jChecPoliciaActionPerformed

    private void jChecResguardoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChecResguardoActionPerformed
          String tot = txtResultSum.getText(),
                    costMant = jLabTarifResguard.getText(),
                    numSemanas = "";/*Hacer suma de total Total*/
          
            BigDecimal amountOne = new BigDecimal(tot);
            BigDecimal amountTwo = new BigDecimal(costMant);//cantidad recivida
  
         if(jChecResguardo.isSelected()){
              
                jButResguardSubstarct.setEnabled(true);
                jButResguardMoore.setEnabled(true);
                 jLabSemsPaysResg.setText("1");
                 
                  numSemanas = jLabSemsPaysResg.getText();
                  BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad recivida
                  txtResultSum.setText(func.getSum(amountOne, func.multiplicaAmount(amountTwo, amountTres)).toString());
                  jlabImportResguard.setText(jLabTarifResguard.getText());
            }else{
                jButResguardSubstarct.setEnabled(false);
                jButResguardMoore.setEnabled(false);
                  numSemanas = jLabSemsPaysResg.getText();
                  BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad recivida
                txtResultSum.setText(func.getDifference(amountOne, func.multiplicaAmount(amountTwo, amountTres)).toString());
                jLabSemsPaysResg.setText("0");
                jlabImportResguard.setText("0.00");
            }
    }//GEN-LAST:event_jChecResguardoActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if( !jChecMantSem.isSelected() && !jChecBasura.isSelected() &&
        !jChecPolicia.isSelected() && !jChecResguardo.isSelected()){
            JOptionPane.showMessageDialog(null, "Debe elegir almenos un pago.");
        }else{
            jFramePays.setLocationRelativeTo(this);
            jFramePays.setVisible(true);
            jFramePays.setEnabled(true);
            jFramePays.setTitle("Areas");
            String total = txtResultSum.getText();//es el total
            txtTotCobro.setText(total);
            txtEfect.setText(total);
            txtEfect.requestFocus(true);
            txtEfect.selectAll();
            jButton7.setEnabled(false);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String var = jFramePays.getTitle();
        switch(var){
           case "Areas":
               cobraAreas();
           break;
           
           case "Ambulantes":
               cobraAmbulantes();
           break;
           
           case "Cargadores":
               cobraCargadores();
               jPanDataambView2.setVisible(false);
               
           break;

            case "Otros":
               cobraOtrosPays();
           break;
           case "Cargadores Renta":
               cobraRentCarg();
                   jButton40.doClick();
          jPanDataambView2.setVisible(false);
          txtBuscCargadores.requestFocus(true);
           break;
        };
        JOptionPane.showMessageDialog(null, "Cobrar: "+var);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtEfectKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectKeyPressed
 
    }//GEN-LAST:event_txtEfectKeyPressed

    private void txtEfectKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectKeyReleased
            int var = evt.getKeyCode();
            //System.out.println("Oprimio #"+ var);
         if(var == 27 ){
                    jFramePays.dispose();
                    txtBuscAmbulante.requestFocus(true);
            }else{
         
            if ( (var > 47 && var < 58) || (var > 95 && var < 106) || var == 10 || var ==8 || var ==127 || var == 37 || var == 39
                    || var == 46 || var == 110) {//si oprime numero o Enter

                String difer = txtEfect.getText(),
                        total = txtTotCobro.getText();
                BigDecimal amountOne = new BigDecimal(total);
                BigDecimal amountTwo = new BigDecimal(difer);
/*CompareTo: 
                0 : Si amountOne == parametro
                1: Si amountOne > parametro 
                -1: Si amountOne < parametro
*/
                if(amountOne.compareTo(amountTwo) <= 0){
                    jButton7.setEnabled(true);
                    txtCambio.setText(func.getDifference(amountTwo, amountOne).toString());
   if(var == KeyEvent.VK_ENTER ){
                    jButton7.doClick();
                    jFramePays.dispose();
            }  
  
                }else{
                     txtCambio.setText(func.getDifference(amountTwo, amountOne).toString());
                     jButton7.setEnabled(false);
                }
            }else{
                    // JOptionPane.showMessageDialog(null, "Solo acepta numeros");
                     txtEfect.setText(txtTotCobro.getText());
             }
            
         }
    }//GEN-LAST:event_txtEfectKeyReleased

    private void txtCambioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCambioKeyReleased
            int var = evt.getKeyCode();
    }//GEN-LAST:event_txtCambioKeyReleased

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
     llenacombogetAreas();
     inhabilitaAreas();
     jDatChoFechBusqtick.setDate(datCtrl.cargafecha());
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
         int fila = jTabviewPays.getSelectedRow();
                if(fila >= 0){
                    String mostTic = jTabviewPays.getValueAt(fila, 0).toString(),
                            concepto = jTabviewPays.getValueAt(fila, 2).toString();
                    if(concepto.equals("Pago Areas")){
                        String[] dat = rP.getUltimPagoarea(mostTic);
                        rP.imprim80MM(mostTic, dat,true);
                    }
                    if(concepto.equals("Pago Ambulantes")){
                        String[] dat = rP.getTickPagoAmbu(mostTic);
                        rP.imprim80MMAmbus(mostTic, dat,true);
                    }
                    if(concepto.equals("Pago Cargadores")){                    
                        String[] dat = rP.getTickPagoCargad(mostTic);
                        rP.imprim80MMCargad(mostTic, dat,true);
                    }
                    if(concepto.equals("Pago Infraccion")){                    
                         rP.imprim80MM_Infrac(mostTic,true);
                    }
                    if(concepto.equals("Varios Amb.") || concepto.equals("Varios Cte.") || concepto.equals("Varios Carg.")){                    
                         String[] dat = rP.getTickOthers(mostTic);
                         rP.imprim80MMOthers(mostTic,dat,true);
                    }
                    if(concepto.equals("Pago Renta Carg")){                    
                         rP.imprim80MM_CargRent(mostTic,true);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe elegir que mostrar");
                }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jCheckResguardAmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckResguardAmbActionPerformed
                String  granTotal = txtResultAmbu.getText(),
                    importResguard = jLabImporteResguard.getText(),
                    tarif = jLabTarifaResguard.getText(),
                    dto = jLabDstoResguard.getText(),
                    numSemanas=jLabContSemsResguard.getText(),
                        auxtot=""
                    ;
            BigDecimal amountOne = new BigDecimal(importResguard);
            BigDecimal amountTwo = new BigDecimal(granTotal);
            BigDecimal amTarif = new BigDecimal(tarif);
        String ultimaSem = jLabUltimaResguardPay.getText();
        
        if(ultimaSem.equals("N/A")){
            String[] semA = func.lapsoSemanasIni(datCtrl.setDateActual());
            jLabUltimaResguardPay.setText(semA[2]);
            txtIdResguardOcultoAmb.setText(semA[0]);
            idResguardAmbu = Integer.parseInt(semA[0]);//idSemana de ultimo pago si es N/A obtenemos semana actual
            idResguardAmbu2=idResguardAmbu;
//            System.err.println("idAmbu noData= "+idResguardAmbu);
            txtResgIniAmb.setText(semA[2]);
            txtResgFinAmb.setText(semA[2]);
            
            jLaFechIniResguard.setText(datCtrl.getWeekStartDate(semA[3]) +" - "+datCtrl.getWeekStartDate(semA[4]));
            jLabAumResguard.setText(datCtrl.getWeekStartDate(semA[3]) +" - "+datCtrl.getWeekStartDate(semA[4]));
       
            BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad recivida
            BigDecimal amountFour = new BigDecimal(dto);//cantidad recivida
//func.getDifference(amountTwo, func.percentage(amountTwo, amountFour)).toString()
jLabImporteResguard.setText(func.getDifference(amTarif, func.percentage(amTarif, amountFour)).toString() );
    importResguard = jLabImporteResguard.getText();
txtResultAmbu.setText( func.getSum(amountTwo,new BigDecimal(importResguard)).toString() );
jCBoxResguardosOpc.setEnabled(true);
        }else{
                if(jCheckResguardAmb.isSelected()){
                    jButMinusResgPaysAmb.setEnabled(true);
                    jButMostSemsPaysAmb1.setEnabled(true);
                    jButMinusResgSemfin.setEnabled(true);
                    jButMostSemsPaysAmb2.setEnabled(true);
                    jLabContSemsResguard.setText("1");
                    BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad recivida
                    BigDecimal amountFour = new BigDecimal(dto);//cantidad recivida
          //calculamos la cantidad a dscontar %de cant          
                    BigDecimal amounPorcint =  func.percentage(amTarif, amountFour);
auxtot = func.getDifference( func.multiplicaAmount(amTarif, amountTres), func.multiplicaAmount(amountTres, amounPorcint) ).toString();
jLabImporteResguard.setText(auxtot);
                       BigDecimal amountFive = new BigDecimal(auxtot);//cantidad recivida
txtResultAmbu.setText(func.getSum(amountTwo, amountFive).toString());
jCBoxResguardosOpc.setEnabled(true);
                }else{
                    jButMinusResgPaysAmb.setEnabled(false);
                    jButMostSemsPaysAmb1.setEnabled(false);
                    jButMinusResgSemfin.setEnabled(false);
                    jButMostSemsPaysAmb2.setEnabled(false);
                       BigDecimal amountTres = new BigDecimal(jLabImporteResguard.getText());//cantidad recivida
                       txtResultAmbu.setText(func.getDifference(amountTwo, amountTres).toString());
                       jLabContSemsResguard.setText("1");
                       jLabImporteResguard.setText("0.00");
                       jCBoxResguardosOpc.setEnabled(false);
                }
        }
 


    }//GEN-LAST:event_jCheckResguardAmbActionPerformed

    private void jCheckInscripPaysAmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckInscripPaysAmbActionPerformed
         String ultimaSem = jLabel60.getText(),
                tarif = jLabTarifaInscripcion.getText(),
                dcto = jLabDstoInscripcion.getText(),
                totalAmbu = jLabImporteInscripcion.getText(),
                 totAll = txtResultAmbu.getText(),
                auxtot ="";/*Hacer suma de total Total*/
        BigDecimal amountOne = new BigDecimal(tarif);
        BigDecimal amountTwo = new BigDecimal(dcto);//cantidad recivida
        BigDecimal amountFour = new BigDecimal(totAll);//cantidad recivida
         jDateChoInscripcion.setDate(datCtrl.cargafecha());
           if(ultimaSem.equals("N/A")){
                    jLabel60.setText(datCtrl.getFecha(jDateChoInscripcion));
                    jCBoxDuracInscripc.setEnabled(true);
                    jDateChoInscripcion.setEnabled(true);
                    jDateChoInscripcion.setDate(datCtrl.cargafecha());
                    auxtot = func.percentage(amountOne,amountTwo ).toString();
                    jLabImporteInscripcion.setText(auxtot);
                    BigDecimal amountFive = new BigDecimal(auxtot);//cantidad recivida
                    txtResultAmbu.setText(func.getSum(amountFour,amountFive).toString());
                      jCBoxDuracInscripc.setSelectedIndex(0);
            }else{      
                if(jCheckInscripPaysAmb.isSelected()){
                    jCBoxDuracInscripc.setEnabled(true);
                    auxtot = func.percentage(amountOne,amountTwo).toString();
                    jLabImporteInscripcion.setText(auxtot);
                    BigDecimal amountsix = new BigDecimal(auxtot);//cantidad recivida
                    txtResultAmbu.setText(func.getSum(amountFour, amountsix).toString());
                    jDateChoInscripcion.setEnabled(true);
                   jDateChoInscripcion.setDate(datCtrl.StringDate(jLabVigenciaView.getText().replace('-', '/')));
                   jCBoxDuracInscripc.setSelectedIndex(0);
                   
                }else{
                    jCBoxDuracInscripc.setEnabled(false);
                    BigDecimal amountTres = new BigDecimal(jLabImporteInscripcion.getText());//cantidad recivida
                    txtResultAmbu.setText(func.getDifference(amountFour, amountTres).toString());
                    jDateChoInscripcion.setEnabled(false);
                 }
           }
           
       
    }//GEN-LAST:event_jCheckInscripPaysAmbActionPerformed

    private void jCheckSemPaysAmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckSemPaysAmbActionPerformed
         jLabContadorSemanas.setText("1");
         String ultimaSem = jLabUltimaSemanaPay.getText(),
                tarif = jLabTarifaSemanas.getText(),
                costMant = jLabTarifaMantenim.getText(),
                dcto = jLabDstoSemanas.getText(),
                numSemanas = jLabContadorSemanas.getText(),
                totalAmbu = txtResultAmbu.getText(),
                auxtot ="";/*Hacer suma de total Total*/
        BigDecimal amountOne = new BigDecimal(totalAmbu);
        BigDecimal amountTwo = new BigDecimal(tarif);//cantidad recivida
        if(ultimaSem.equals("N/A")){
            String[] semA = func.lapsoSemanasIni(datCtrl.setDateActual());
            jLabUltimaSemanaPay.setText(semA[2]);
            txtIdSemOcultoAmb.setText(semA[0]);
            idSemambu = Integer.parseInt(semA[0]);
            txtSeminiAmb.setText(semA[2]);
            txtSemFinAmb.setText(semA[2]);
            jLaFechIniSemana.setText(datCtrl.getWeekStartDate(semA[3]) +" - "+datCtrl.getWeekStartDate(semA[4]));            
            BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad recivida
            BigDecimal amountFour = new BigDecimal(dcto);//cantidad recivida
jLabImporteSemanas.setText(func.getDifference(amountTwo, func.percentage(amountTwo, amountFour)).toString());//func.getDifference(amountTwo,amountFour).toString()
            totalAmbu = jLabImporteSemanas.getText();
txtResultAmbu.setText(func.getSum(amountOne, new BigDecimal(totalAmbu)).toString());

        }else{
                if(jCheckSemPaysAmb.isSelected()){
                       jButMinusSemsPaysAmb.setEnabled(true);
                       jButMostSemsPaysAmb.setEnabled(true);
                       jLabContadorSemanas.setText("1");
                       BigDecimal amountTres = new BigDecimal(numSemanas);//cantidad recivida
                       BigDecimal amountFour = new BigDecimal(dcto);//cantidad recivida
                       BigDecimal amounPorcint =  func.percentage(amountTwo, amountFour);
     auxtot = func.getDifference( func.multiplicaAmount(amountTwo, amountTres), func.multiplicaAmount(amountTres, amounPorcint) ).toString();
 jLabImporteSemanas.setText(auxtot);
                       BigDecimal amountFive = new BigDecimal(auxtot);//cantidad recivida
txtResultAmbu.setText(func.getSum(amountOne, amountFive).toString());
                }else{
                       jButMinusSemsPaysAmb.setEnabled(false);
                       jButMostSemsPaysAmb.setEnabled(false);
                       BigDecimal amountTres = new BigDecimal(jLabImporteSemanas.getText());//cantidad recivida
                       txtResultAmbu.setText(func.getDifference(amountOne, amountTres).toString());
                       jLabContadorSemanas.setText("1");
                       jLabImporteSemanas.setText("0.00");
                 }
        }
    }//GEN-LAST:event_jCheckSemPaysAmbActionPerformed

    private void jButMinusSemsPaysAmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButMinusSemsPaysAmbActionPerformed
        String ini = txtSeminiAmb.getText(),
                fin = txtSemFinAmb.getText(),
                multi1 ="",
                multi2 = "",
                resAmbu="",
                tot="";
        String[] arr = null;
        String aux="";
        BigDecimal auxBig;
        int numeradorSem=Integer.parseInt(jLabContadorSemanas.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
             if(Integer.parseInt(ini) < Integer.parseInt(fin)){
                    idSemambu--;
                    arr = contrl.regSemanas(idSemambu);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtSemFinAmb.setText(arr[2]);//en #semana 1-52
                   jLabAumentaSemanas.setText(aux);       
                   jLabContadorSemanas.setText(Integer.toString(numeradorSem-1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jLabDstoSemanas.getText();
                   multi2 = jLabTarifaSemanas.getText();
                   resAmbu = jLabImporteSemanas.getText();
                   tot = txtResultAmbu.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                   BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                   BigDecimal amountAmbusMenos = new BigDecimal(resAmbu);//cantidad recivida
                   BigDecimal totalAll = new BigDecimal(tot);//cantidad recivida 
//auxiliar para no tener tan anidado el llamado a funciones Bigdecimal por percentage                   
                  auxBig = func.getDifference(amountTwo, func.percentage(amountTwo, amountOne));
jLabImporteSemanas.setText(func.getDifference(amountAmbusMenos, auxBig).toString()  );
txtResultAmbu.setText( func.getDifference(totalAll, auxBig).toString() );           
             }else{
                 JOptionPane.showMessageDialog(null, "Inicio no pude ser mayor que Fecha fin");
             }

    }//GEN-LAST:event_jButMinusSemsPaysAmbActionPerformed

    private void jButMostSemsPaysAmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButMostSemsPaysAmbActionPerformed
        String ini = txtSeminiAmb.getText(),
                fin = txtSemFinAmb.getText(),
                multi1 ="",
                multi2 = "",
                resAmbu="",
                tot="";
        String[] arr = null;
        String aux="";
        BigDecimal auxBig;
        int numeradorSem=Integer.parseInt(jLabContadorSemanas.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
                    idSemambu++;
                    arr = contrl.regSemanas(idSemambu);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                   txtSemFinAmb.setText(arr[2]);//en #semana 1-52
                   jLabAumentaSemanas.setText(aux);       
                   jLabContadorSemanas.setText(Integer.toString(numeradorSem+1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jLabDstoSemanas.getText();
                   multi2 = jLabTarifaSemanas.getText();
                   resAmbu = jLabImporteSemanas.getText();
                   tot = txtResultAmbu.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                   BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                   BigDecimal amountAmbusMenos = new BigDecimal(resAmbu);//cantidad recivida
                   BigDecimal totalAll = new BigDecimal(tot);//cantidad recivida 
//auxiliar para no tener tan anidado el llamado a funciones Bigdecimal por percentage                   
                  auxBig = func.getDifference(amountTwo, func.percentage(amountTwo, amountOne));
jLabImporteSemanas.setText(func.getSum(amountAmbusMenos, auxBig).toString());//func.getSum(amountAmbusMenos, func.getDifference(amountTwo, amountOne)).toString() 
txtResultAmbu.setText( func.getSum(totalAll,auxBig ).toString() );
    }//GEN-LAST:event_jButMostSemsPaysAmbActionPerformed

    private void jCBoxResguardosOpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBoxResguardosOpcActionPerformed
        int var = jCBoxResguardosOpc.getSelectedIndex();
        if(var > -1){
            jLabTarifaResguard.setText(matResgVehiculo[var][2]);
            String multi1 = jLabDstoResguard.getText(),
                   multi2 = jLabTarifaResguard.getText(),
                   resAmbu = jLabImporteResguard.getText(),
                   tot = txtResultAmbu.getText(),
                   numSems = jLabContSemsResguard.getText(),
                    aux="",auxSems="",auxInsc="";
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                   BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                   BigDecimal amountAmbusMenos = new BigDecimal(resAmbu);//cantidad recivida
                   BigDecimal totalAll = new BigDecimal(tot);//cantidad recivida
                   BigDecimal amountNumSems = new BigDecimal(numSems);//cantidad recivida
                   BigDecimal amounPorcint =  func.percentage(amountTwo, amountOne);
aux = func.multiplicaAmount(amountNumSems, func.getDifference(amountTwo, amounPorcint)).toString();
//System.out.println("Producto tarifa Resguard: "+aux);
jLabImporteResguard.setText(aux);

if(jCheckSemPaysAmb.isSelected()){
    auxSems = jLabImporteSemanas.getText();
   aux = func.getSum(new BigDecimal(auxSems), new BigDecimal(aux)).toString();
}
    
if(jCheckInscripPaysAmb.isSelected()){
    auxInsc = jLabImporteInscripcion.getText();
   aux = func.getSum(new BigDecimal(auxInsc), new BigDecimal(aux)).toString();
}
txtResultAmbu.setText(aux);
        }
    }//GEN-LAST:event_jCBoxResguardosOpcActionPerformed

    private void jButMinusResgPaysAmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButMinusResgPaysAmbActionPerformed
        String ini = txtResgIniAmb.getText(),
                fin = txtResgFinAmb.getText(),
                multi1 ="",
                multi2 = "",
                resAmbu="",
                tot="";
        String[] arr = null;
        String aux="";
        int numeradorSem=Integer.parseInt(jLabContSemsResguard.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
                    idResguardAmbu--;
                    idResguardAmbu2 = idResguardAmbu;
                    arr = contrl.regSemanas(idResguardAmbu);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtResgIniAmb.setText(arr[2]);//en #semana 1-52
                   txtResgFinAmb.setText(arr[2]);
                   
                   jLaFechIniResguard.setText(aux);
                   jLabAumResguard.setText(aux);
                   
      //             System.err.println("Valen--  ="+idResguardAmbu+" 2: "+idResguardAmbu2);
                   jLabContSemsResguard.setText("1");
    }//GEN-LAST:event_jButMinusResgPaysAmbActionPerformed

    private void jButMostSemsPaysAmb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButMostSemsPaysAmb1ActionPerformed
        String ini = txtResgIniAmb.getText(),
                fin = txtResgFinAmb.getText(),
                multi1 ="",
                multi2 = "",
                resAmbu="",
                tot="";
        String[] arr = null;
        String aux="";
        int numeradorSem=Integer.parseInt(jLabContSemsResguard.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
                    idResguardAmbu++;
                    idResguardAmbu2=idResguardAmbu;
                    arr = contrl.regSemanas(idResguardAmbu);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtResgIniAmb.setText(arr[2]);//en #semana 1-52
                   txtResgFinAmb.setText(arr[2]);
                   
                   jLaFechIniResguard.setText(aux); 
                   jLabAumResguard.setText(aux);
     //              System.err.println("Valen++ ="+idResguardAmbu+" 2: "+idResguardAmbu2); 
                  jLabContSemsResguard.setText("1");
    }//GEN-LAST:event_jButMostSemsPaysAmb1ActionPerformed

    private void jButMinusResgSemfinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButMinusResgSemfinActionPerformed
        String ini = txtResgIniAmb.getText(),
                fin = txtResgFinAmb.getText(),
                multi1 ="",
                multi2 = "",
                resAmbu="",
                tot="";
        String[] arr = null;
        String aux="";
        BigDecimal auxBig;
        int numeradorSem=Integer.parseInt(jLabContSemsResguard.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
             if(Integer.parseInt(ini) < Integer.parseInt(fin)){
                    idResguardAmbu2--;
                    arr = contrl.regSemanas(idResguardAmbu2);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                   txtResgFinAmb.setText(arr[2]);//en #semana 1-52
                   jLabAumResguard.setText(aux);       
                   jLabContSemsResguard.setText(Integer.toString(numeradorSem-1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jLabDstoResguard.getText();
                   multi2 = jLabTarifaResguard.getText();
                   resAmbu = jLabImporteResguard.getText();
                   tot = txtResultAmbu.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                   BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                   BigDecimal amountAmbusMenos = new BigDecimal(resAmbu);//cantidad recivida
                   BigDecimal totalAll = new BigDecimal(tot);//cantidad recivida
 //auxiliar para no tener tan anidado el llamado a funciones Bigdecimal por percentage                   
                  auxBig = func.percentage(amountTwo, amountOne);                  
jLabImporteResguard.setText(func.getDifference(amountAmbusMenos, auxBig).toString()  );
txtResultAmbu.setText( func.getDifference(totalAll, auxBig).toString() );     
              //  System.err.println("ValeMinus Resguard-- ="+idResguardAmbu+" 2: "+idResguardAmbu2); 
             }else{
                 JOptionPane.showMessageDialog(null, "Inicio no pude ser mayor que Fecha fin");
             }

    }//GEN-LAST:event_jButMinusResgSemfinActionPerformed

    private void jButMostSemsPaysAmb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButMostSemsPaysAmb2ActionPerformed
        String ini = txtResgIniAmb.getText(),
                fin = txtResgFinAmb.getText(),
                multi1 ="",
                multi2 = "",
                resAmbu="",
                tot="";
        String[] arr = null;
        String aux="";
        BigDecimal auxBig;
        int numeradorSem=Integer.parseInt(jLabContSemsResguard.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
                    idResguardAmbu2++;
                    arr = contrl.regSemanas(idResguardAmbu2);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                   txtResgFinAmb.setText(arr[2]);//en #semana 1-52
                   jLabAumResguard.setText(aux);       
                   jLabContSemsResguard.setText(Integer.toString(numeradorSem+1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jLabDstoResguard.getText();
                   multi2 = jLabTarifaResguard.getText();
                   resAmbu = jLabImporteResguard.getText();
                   tot = txtResultAmbu.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                   BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                   BigDecimal amountAmbusMenos = new BigDecimal(resAmbu);//cantidad recivida
                   BigDecimal totalAll = new BigDecimal(tot);//cantidad recivida
           //auxiliar para no tener tan anidado el llamado a funciones Bigdecimal por percentage                   
                  auxBig = func.percentage(amountTwo, amountOne);        
jLabImporteResguard.setText(func.getSum(amountAmbusMenos, auxBig).toString()  );
txtResultAmbu.setText( func.getSum(totalAll, auxBig).toString() );   
 //  System.err.println("ValenMost Resguard++ ="+idResguardAmbu+" 2: "+idResguardAmbu2); 
    }//GEN-LAST:event_jButMostSemsPaysAmb2ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        if( !jCheckSemPaysAmb.isSelected() && !jCheckResguardAmb.isSelected() &&
        !jCheckInscripPaysAmb.isSelected() ){
            JOptionPane.showMessageDialog(null, "Debe elegir almenos un pago.");
        }else{
            jFramePays.setLocationRelativeTo(null);
            jFramePays.setVisible(true);
            jFramePays.setEnabled(true);
            jFramePays.setTitle("Ambulantes");
            String total = txtResultAmbu.getText();//es el total
            txtTotCobro.setText(total);
            txtEfect.setText(total);
            txtEfect.requestFocus(true);
            txtEfect.selectAll();
            jButton7.setEnabled(false);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jCkBoxSemanasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCkBoxSemanasActionPerformed
        String totalCarg = txtTotalCarg.getText(),
                importSems = jLabImportSemanasCargad.getText()
                ;
        BigDecimal totalGran,
                importSemCargad
                ;
        totalGran =  new BigDecimal(totalCarg);
        importSemCargad =  new BigDecimal(importSems);
        if(jCkBoxSemanas.isSelected()){
      //      System.out.println("comezar SemCargador:"+idSemCargad);
            jButMinusSems.setEnabled(true);
            jButMooreSems.setEnabled(true);
            txtTotalCarg.setText(func.getSum(totalGran, importSemCargad).toString());
        }else{
            jButMinusSems.setEnabled(false);
            jButMooreSems.setEnabled(false);
            txtTotalCarg.setText(func.getDifference(totalGran, importSemCargad).toString());
        }
    }//GEN-LAST:event_jCkBoxSemanasActionPerformed

    private void jComBInscripcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComBInscripcActionPerformed
        int var = jComBInscripc.getSelectedIndex();
        if(var > -1){
            jLabCoastTarifa.setText(datasCarg[jComBInscripc.getSelectedIndex() + 12]);

           String multi1 = jLabDescto.getText(),
                   multi2 = jLabCoastTarifa.getText(),
                   resAmbu = jLabImportInscripc.getText(),
                   tot = txtTotalCarg.getText(),
                    aux="",auxSems="",auxInsc="";
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                   BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                   BigDecimal amountAmbusMenos = new BigDecimal(resAmbu);//cantidad recivida
                   BigDecimal totalAll = new BigDecimal(tot);//cantidad recivida
              //     BigDecimal amountNumSems = new BigDecimal(numSems);//cantidad recivida
aux = func.getDifference(amountTwo, func.percentage(amountTwo, amountOne)).toString();

                    jLabImportInscripc.setText(aux);

                    if(jCkBoxSemanas.isSelected()){
                        auxSems = jLabImportSemanasCargad.getText();
                       aux = func.getSum(new BigDecimal(auxSems), new BigDecimal(aux)).toString();
                    }
              txtTotalCarg.setText(aux);
        }
        

    }//GEN-LAST:event_jComBInscripcActionPerformed

    private void jButMinusSemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButMinusSemsActionPerformed
                String ini = txtcontSemsIni.getText(),
                fin = txtcontSemsFin.getText(),
                multi1 ="",
                multi2 = "",
                resAmbu="",
                tot="";
        String[] arr = null;
        String aux="";
        BigDecimal auxBig;
        int numeradorSem=Integer.parseInt(jLabcontSemanas.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
             if(Integer.parseInt(ini) < Integer.parseInt(fin)){
                    idSemCargad--;
                    arr = contrl.regSemanas(idSemCargad);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtcontSemsFin.setText(arr[2]);//en #semana 1-52
                   jLabFinSemsViews.setText(aux);       
                   jLabcontSemanas.setText(Integer.toString(numeradorSem-1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jLabDtoSemanas.getText();
                   multi2 = jLabtarifSemanas.getText();
                   resAmbu = jLabImportSemanasCargad.getText();
                   tot = txtTotalCarg.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                   BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                   BigDecimal amountAmbusMenos = new BigDecimal(resAmbu);//cantidad recivida
                   BigDecimal totalAll = new BigDecimal(tot);//cantidad lista para cobrar
                   
                    auxBig = func.getDifference(amountTwo, func.percentage(amountTwo, amountOne));
                    
jLabImportSemanasCargad.setText(func.getDifference(amountAmbusMenos, auxBig).toString()  );
txtTotalCarg.setText( func.getDifference(totalAll, auxBig).toString() );           
             }else{
                 JOptionPane.showMessageDialog(null, "Inicio no pude ser mayor que Fecha fin");
             }
    }//GEN-LAST:event_jButMinusSemsActionPerformed

    private void jButMooreSemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButMooreSemsActionPerformed
               String ini = txtcontSemsIni.getText(),
                fin = txtcontSemsFin.getText(),
                multi1 ="",
                multi2 = "",
                resAmbu="",
                tot="";
        String[] arr = null;
        String aux="";
        BigDecimal auxBig;
        int numeradorSem=Integer.parseInt(jLabcontSemanas.getText());
           //amountThree.compareTo(func.getSum(amountOne, amountTwo)) >= 0
                    idSemCargad++;
                    arr = contrl.regSemanas(idSemCargad);
                    aux = datCtrl.getWeekStartDate(arr[3]) + " - "+ datCtrl.getWeekStartDate(arr[4]);
                  
                   txtcontSemsFin.setText(arr[2]);//en #semana 1-52
                   jLabFinSemsViews.setText(aux);       
                   jLabcontSemanas.setText(Integer.toString(numeradorSem+1));
          /*obtenemos el producto del numero de tickets por semana que pagara*/
                   multi1 = jLabDtoSemanas.getText();
                   multi2 = jLabtarifSemanas.getText();
                   resAmbu = jLabImportSemanasCargad.getText();
                   tot = txtTotalCarg.getText();
                   BigDecimal amountOne = new BigDecimal(multi1);//monto a cobrar
                   BigDecimal amountTwo = new BigDecimal(multi2);//cantidad recivida
                   BigDecimal amountAmbusMenos = new BigDecimal(resAmbu);//cantidad recivida
                   BigDecimal totalAll = new BigDecimal(tot);//cantidad lista para cobrar
//auxiliar para no tener tan anidado el llamado a funciones Bigdecimal por percentage                   
                  auxBig = func.getDifference(amountTwo, func.percentage(amountTwo, amountOne));
                          
jLabImportSemanasCargad.setText(func.getSum(amountAmbusMenos, auxBig).toString()  );
txtTotalCarg.setText( func.getSum(totalAll, auxBig).toString() );           
    }//GEN-LAST:event_jButMooreSemsActionPerformed

    private void jCkBoxInscripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCkBoxInscripcionActionPerformed
        String totalCarg = txtTotalCarg.getText(),
                importInscrip = jLabImportInscripc.getText()
                ;
        BigDecimal totalGran,
                importInscCargad
                ;
        totalGran =  new BigDecimal(totalCarg);
        importInscCargad =  new BigDecimal(importInscrip);
        if(jCkBoxInscripcion.isSelected()){
            jDatChoIncripcion.setEnabled(true);
            jComBInscripc.setEnabled(true);
            txtTotalCarg.setText(func.getSum(totalGran, importInscCargad).toString());
        }else{
            jDatChoIncripcion.setEnabled(false);
            jComBInscripc.setEnabled(false);
            txtTotalCarg.setText(func.getDifference(totalGran, importInscCargad).toString());
        }

    }//GEN-LAST:event_jCkBoxInscripcionActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
       if(jPanRentCargador.isVisible()){
            jFramePays.setLocationRelativeTo(null);
            jFramePays.setVisible(true);
            jFramePays.setEnabled(true);
            jFramePays.setTitle("Cargadores Renta");
            String total = txtTotalCarg.getText();//es el total
            txtTotCobro.setText(total);
            txtEfect.setText(total);
            txtEfect.requestFocus(true);
            txtEfect.selectAll();
            jButton7.setEnabled(false);
       }else{
        if( !jCkBoxSemanas.isSelected() && !jCkBoxInscripcion.isSelected()){
            JOptionPane.showMessageDialog(null, "Debe elegir almenos una opcion pago.");
        }else{
            jFramePays.setLocationRelativeTo(null);
            jFramePays.setVisible(true);
            jFramePays.setEnabled(true);
            jFramePays.setTitle("Cargadores");
            String total = txtTotalCarg.getText();//es el total
            txtTotCobro.setText(total);
            txtEfect.setText(total);
            txtEfect.requestFocus(true);
            txtEfect.selectAll();
            jButton7.setEnabled(false);
        }
       }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void txtInFilterBusqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInFilterBusqKeyReleased
         String var = txtInFilterBusq.getText();
        int opc = jCBoxFilterBusq.getSelectedIndex();
        if(opc == 0)
        {
            mostrarTabInfrac("folio",var);
        }
        if (opc == 1){
            mostrarTabInfrac("placa",var);
        }
        
    }//GEN-LAST:event_txtInFilterBusqKeyReleased

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
            infraccs = new infracc_rw_DB(0,"");//alta de nueva infraccion
            infraccs.setVisible(true);
            infraccs.setEnabled(true);
            infraccs.validate();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void txtInFilterBusqFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtInFilterBusqFocusGained
        txtInFilterBusq.setText("");
        mostrarTabInfrac("folio","");
    }//GEN-LAST:event_txtInFilterBusqFocusGained

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        int filN = jTabInfraccView.getSelectedRow();
        if(filN > -1){
            String folParam = jTabInfraccView.getValueAt(filN, 0).toString();
            infraccs = new infracc_rw_DB(1,folParam);//alta de nueva infraccion
            infraccs.setVisible(true);
            infraccs.setEnabled(true);
            infraccs.validate();
            infraccs.txtfolInfrac.setEnabled(false);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla.");
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
       int filN = jTabInfraccView.getSelectedRow();
        if(filN > -1){
            String folParam = jTabInfraccView.getValueAt(filN, 0).toString();
            contrl.elimaRow("pagos_infrac", "folio", folParam);
            txtInFilterBusq.requestFocus(true);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla.");
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
       int filN = jTabInfraccView.getSelectedRow();
       if(filN > -1){
            String folParam = jTabInfraccView.getValueAt(filN, 0).toString(),
                    placa = jTabInfraccView.getValueAt(filN, 2).toString(),
                    mont = jTabInfraccView.getValueAt(filN, 4).toString()
                    ;
                    jFramCobroInfrac.setLocationRelativeTo(null);
                    jFramCobroInfrac.setVisible(true);
                    jFramCobroInfrac.setEnabled(true);
                    txtImporte.setText(mont);
                    txtTotal.setText(mont);
                    txtEfectivo.setText(mont);
                    jLabFolio.setText(folParam);
                    jLabPlaca.setText(placa);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla.");
        }

    }//GEN-LAST:event_jButton33ActionPerformed

    private void txtDctoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDctoKeyReleased

        String dto = txtDcto.getText(),
                toI = txtImporte.getText()
                ;
if(!dto.isEmpty() && !toI.isEmpty()){
        BigDecimal dscto = new BigDecimal(dto);
        BigDecimal importe = new BigDecimal(toI);
                
        /*CompareTo: 
                0 : Si amountOne == parametro
                1: Si amountOne > parametro 
                -1: Si amountOne < parametro
        */
                if(dscto.compareTo(importe) <= 0){
                    txtTotal.setText(func.getDifference(importe, dscto).toString());
                    txtEfectivo.setText(func.getDifference(importe, dscto).toString());
                    txtCambioInfrac.setText("0.00");
                }else{
                    JOptionPane.showMessageDialog(null, "Importe debe ser mayor que descuento \n Verifique porfavor.");
                    txtDcto.setText("0.00");
                    txtDcto.setSelectionStart(0);
                }
}     
    }//GEN-LAST:event_txtDctoKeyReleased

    private void txtEfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyReleased
       
               String efec = txtEfectivo.getText(),
                toI = txtTotal.getText()
                ;
    if(!efec.isEmpty() && !toI.isEmpty()){
        BigDecimal efectiv = new BigDecimal(efec);
        BigDecimal importe = new BigDecimal(toI);
                
        /*CompareTo: 
                0 : Si amountOne == parametro
                1: Si amountOne > parametro 
                -1: Si amountOne < parametro
        */
           txtCambioInfrac.setText(func.getDifference(efectiv, importe).toString());
}
    }//GEN-LAST:event_txtEfectivoKeyReleased

    private void txtDctoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDctoFocusGained
    txtDcto.setSelectionStart(0);
    }//GEN-LAST:event_txtDctoFocusGained

    private void txtEfectivoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEfectivoFocusGained
        txtEfectivo.setSelectionStart(0);
    }//GEN-LAST:event_txtEfectivoFocusGained

    private void txtDctoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDctoKeyPressed
        int var = evt.getKeyCode();
        if(var == KeyEvent.VK_ENTER){
            jButton8.doClick();
        }
    }//GEN-LAST:event_txtDctoKeyPressed

    private void txtEfectivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyPressed
        int var = evt.getKeyCode();
        if(var == KeyEvent.VK_ENTER){
            jButton8.doClick();
        }
    }//GEN-LAST:event_txtEfectivoKeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String totA = txtTotal.getText(),
                efectV = txtEfectivo.getText(),
                namePag = txtNamePagad.getText();
        
        if(!totA.isEmpty() && !efectV.isEmpty() && !namePag.isEmpty()){
        BigDecimal totAll = new BigDecimal(totA);
        BigDecimal efectivale = new BigDecimal(efectV);
                
        /*CompareTo: 
                0 : Si amountOne == parametro
                1: Si amountOne > parametro 
                -1: Si amountOne < parametro
        */
                if(totAll.compareTo(efectivale) <= 0){
                    List<String> enviaData = new ArrayList<String>();//lista para guardar el id de cada area cobro de areas
                      //  String urno = Integer.toString(func.getenTurno()),
                          String fech = datCtrl.setDateActual(),
                                horP = datCtrl.getHour(),
                                dscto = txtDcto.getText() ;
                                enviaData.add(infoUser[6]);//tenia urno
                                enviaData.add(fech);
                                enviaData.add(horP);
                                enviaData.add(namePag);
                                enviaData.add(dscto);
                                enviaData.add(efectV);
                                contrl.payInfracc(enviaData,jLabFolio.getText());
                               // contrl.matrizgetTicketsDia("",infoUser[6]);
                                
                                String[][] mat = contrl.matrizgetTicketsDia("",infoUser[6]);
                                DefaultTableModel modelo = new TModel(mat, cabAreasPays);
        jTabviewPays.setModel(modelo);
        TableRowSorter<TableModel> ordenon = new TableRowSorter<TableModel>(modelo);
        jTabviewPays.setRowSorter(ordenon);
//                                jTabviewPays.setModel(new TModel(mat, cabAreasPays));
                                tamColViwePays();
                                
                                
                                rP.imprim80MM_Infrac(jLabFolio.getText(),true);
                                limpiPayInfrac();
                                jFramCobroInfrac.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Efectivo debe ser mayor que total \n Verifique porfavor.");
                    txtEfectivo.setText(txtTotal.getText());
                }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtBusqOtrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusqOtrosKeyReleased
        
        int oprime = evt.getKeyCode();
         String var = txtBusqOtros.getText();
        if(!var.isEmpty()){
            allClisAmbCarg(var);
            jPanTableBusqView2.setVisible(true);
            jPanDataambView3.setVisible(false);
            
            if(oprime == KeyEvent.VK_ENTER){
             jTabVariosView.requestFocus(true);
            }
        }else{
            jPanTableBusqView2.setVisible(false);
        }
        
    }//GEN-LAST:event_txtBusqOtrosKeyReleased

    private void jTabVariosViewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabVariosViewKeyPressed
         int oprime = evt.getKeyCode(),
                fila=jTabVariosView.getSelectedRow();
         if(oprime == KeyEvent.VK_ENTER && fila > -1 ){
             String opc = jTabVariosView.getValueAt(fila, 0).toString(),
                     queEs = jTabVariosView.getValueAt(fila, 2).toString();
             
             String[] datasAmb=func.getAllOtros(opc,queEs);
             
            jLabFoli.setText(jTabVariosView.getValueAt(fila, 0).toString());
            jLabNombre3.setText(jTabVariosView.getValueAt(fila, 1).toString());
            jLabDirecc3.setText(datasAmb[0]);
            jLabObserva3.setText(datasAmb[1]);
            jLabThisIs.setText(jTabVariosView.getValueAt(fila, 2).toString());

            jPanTableBusqView2.setVisible(false);
            jPanDataambView3.setVisible(true);
            txtBusqOtros.setText("");
            txtBusqConcept.requestFocus(true);
         }
    }//GEN-LAST:event_jTabVariosViewKeyPressed

    private void txtBusqConceptKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusqConceptKeyReleased
        int oprime = evt.getKeyCode();
         String var = txtBusqConcept.getText();
        if(!var.isEmpty()){
             getProductsOthers(var);
            jPanTableBusqView3.setVisible(true);
            jPanvIEWcOAST.setVisible(false);

            if(oprime == KeyEvent.VK_ENTER){
             jTabViewOtherProd.requestFocus(true);
            }
        }else{
            jPanTableBusqView3.setVisible(false);
        }
    }//GEN-LAST:event_txtBusqConceptKeyReleased

    private void jTabViewOtherProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabViewOtherProdKeyPressed
         int oprime = evt.getKeyCode(),
                fila=jTabViewOtherProd.getSelectedRow();
         if(oprime == KeyEvent.VK_ENTER && fila > -1 ){
             String opc = jTabViewOtherProd.getValueAt(fila, 0).toString();
             
             String[] datasAmb=func.getOtros_catalogId(opc);
             
            jLabConceptName.setText(jTabViewOtherProd.getValueAt(fila, 1).toString());
            txtIdOculto.setText(datasAmb[0]);
           txtCantidadOths.setText("1");
            txtPreciooths.setText(datasAmb[3]);
            txtImportOths.setText(datasAmb[3]);

            jPanTableBusqView3.setVisible(false);
            jPanvIEWcOAST.setVisible(true);
            txtBusqConcept.setText("");
         //   txtCantidadOths.requestFocus(true);
                 txtCantidadOths.setSelectionStart(0);
                jButton34.setEnabled(true);
         }
    }//GEN-LAST:event_jTabViewOtherProdKeyPressed

    private void txtCantidadOthsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadOthsKeyReleased
  
       String cant = txtCantidadOths.getText(),
                cos = txtPreciooths.getText();
                  int oprime = evt.getKeyCode();
        if (cant.isEmpty() || cos.isEmpty()) {
           // JOptionPane.showMessageDialog(null, "Campo cantidad o monto vacios\n Verifique Por favor");
             txtCantidadOths.requestFocus(true);
             txtImportOths.setText("0.00");
        } else {
            BigDecimal amountOne = new BigDecimal(cant);//monto a cobrar
            BigDecimal amountTwo = new BigDecimal(cos);//cantidad recivida
            txtImportOths.setText(func.multiplicaAmount(amountOne, amountTwo).toString());
            
            if(oprime == KeyEvent.VK_ENTER){
                jButton34.doClick();
            }       
        }
    }//GEN-LAST:event_txtCantidadOthsKeyReleased

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed

        String codPro = txtIdOculto.getText(),//idProducto devuelto de la base de datos

                costUnit = txtPreciooths.getText(),
                cantidad = txtCantidadOths.getText(),
                descrip = jLabConceptName.getText(),
                costProdtot = txtImportOths.getText(),
                totAll = txtTotOthers.getText();
        
        BigDecimal totAl = new BigDecimal(totAll);
         BigDecimal totProd = new BigDecimal(costProdtot);
        // System.out.println("IdCliente: " + id_cli + "\t idProd: " + codPro + "Fecha: " + dateVentP);

        List<String> dataVentPiso = new ArrayList<String>();
        if (cantidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad");
        } else {
            DefaultTableModel dtm = (DefaultTableModel) jTabVistaVentaOthers.getModel();

            int filas = dtm.getRowCount();
            int column = dtm.getColumnCount();
            if (filas == 0) {
  /*              dataVentPiso.add(id_cli);
                dataVentPiso.add(dateVentP);
                dataVentPiso.add(nota);
                controlInserts.guardaVentaPiso(dataVentPiso);
      */      }
            dtm.addRow(new Object[]{codPro, cantidad, descrip, costUnit, costProdtot});
            jTabVistaVentaOthers.setModel(dtm);
            txtTotOthers.setText(func.getSum(totAl, totProd).toString());
            
            limpiaConcept();
            jButton34.setEnabled(false);
        }//else vacio

    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
            jDialresProdOther.setLocationRelativeTo(null);
            jDialresProdOther.setVisible(true);
            jDialresProdOther.setEnabled(true);
            llenaCBRubrosOths();
            
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTabVistaVentaOthersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabVistaVentaOthersKeyPressed
        int oprime = evt.getKeyCode(),
                fila = jTabVistaVentaOthers.getRowCount(),
                selec = jTabVistaVentaOthers.getSelectedRow();
//obtenemos modelo de tablaVista Compra a Mayorista
if(selec > -1){     
    
        if(oprime == KeyEvent.VK_DELETE){
            DefaultTableModel dtmAux = (DefaultTableModel) jTabVistaVentaOthers.getModel();
               String importelim = jTabVistaVentaOthers.getValueAt(selec, 4).toString(),
               totAllA = txtTotOthers.getText();
               
               BigDecimal totAl = new BigDecimal(totAllA);
               BigDecimal totProd = new BigDecimal(importelim);
               txtTotOthers.setText(func.getDifference(totAl, totProd).toString());
   
            dtmAux.removeRow(selec);
        }
        
}else{
    JOptionPane.showMessageDialog(null, "Debe existir almenos un poducto agregado");
}
    }//GEN-LAST:event_jTabVistaVentaOthersKeyPressed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        int fila = jTabVistaVentaOthers.getRowCount();
    //    System.out.println("Teien filas = "+fila);
       if(fila > 0){ 
        DefaultTableModel   dtmAux = (DefaultTableModel) jTabVistaVentaOthers.getModel();//obtenemos modelo de tablaVista Compra a Mayorista
        int row = dtmAux.getRowCount();
        for (int i = 0; i < row; i++) {
            dtmAux.removeRow(0);
        }
        }else{
    JOptionPane.showMessageDialog(null, "Debe existir almenos un poducto agregado");
}
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
     int fila = jTabVistaVentaOthers.getRowCount();
   
        if(fila > 0){   
            jFramePays.setLocationRelativeTo(null);
            jFramePays.setVisible(true);
            jFramePays.setEnabled(true);
            jFramePays.setTitle("Otros");
            String total = txtTotOthers.getText();//es el total
            txtTotCobro.setText(total);
            txtEfect.setText(total);
            txtEfect.requestFocus(true);
            txtEfect.selectAll();
            txtCambio.setText("0.00");
            jButton7.setEnabled(false);
        }else{
            JOptionPane.showMessageDialog(null, "Debe existir almenos un poducto agregado");
        }
    }//GEN-LAST:event_jButton49ActionPerformed

    private void txtPrecioothsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioothsKeyReleased
       String cant = txtCantidadOths.getText(),
                cos = txtPreciooths.getText();
                  int oprime = evt.getKeyCode();
        if (cant.isEmpty() || cos.isEmpty()) {
           // JOptionPane.showMessageDialog(null, "Campo cantidad o monto vacios\n Verifique Por favor");
             txtPreciooths.requestFocus(true);
             txtImportOths.setText("0.00");
        } else {
            BigDecimal amountOne = new BigDecimal(cant);//monto a cobrar
            BigDecimal amountTwo = new BigDecimal(cos);//cantidad recivida
            txtImportOths.setText(func.multiplicaAmount(amountOne, amountTwo).toString());
            if(oprime == KeyEvent.VK_ENTER){
                jButton34.doClick();
            }       
        }
    }//GEN-LAST:event_txtPrecioothsKeyReleased

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
            jFramExplanadaCob.setLocationRelativeTo(null);
            jFramExplanadaCob.setVisible(true);
            jFramExplanadaCob.setEnabled(true);
            jFramExplanadaCob.setTitle("Otros");
            txtFolIniExplanad.setText("1");
            txtFolFinExplanad.setText("1");
            txtFoltotExplanad.setText("1");
            txtFolIniExplanad.setSelectionStart(0);
    }//GEN-LAST:event_jButton51ActionPerformed

    private void txtFolIniExplanadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFolIniExplanadKeyReleased
       String cant = txtFolIniExplanad.getText(),
                cos = txtFolFinExplanad.getText()
              ;
        if (cant.isEmpty() || cos.isEmpty()) {
           // JOptionPane.showMessageDialog(null, "Campo cantidad o monto vacios\n Verifique Por favor");
             txtFolIniExplanad.requestFocus(true);
        } else {
            int rest = Integer.parseInt(cant),
                    tenRest = Integer.parseInt(cos),
                     result = tenRest - rest;
                txtFoltotExplanad.setText(Integer.toString(result +1));
        }
    }//GEN-LAST:event_txtFolIniExplanadKeyReleased

    private void txtFolFinExplanadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFolFinExplanadKeyReleased
       String cant = txtFolIniExplanad.getText(),
                cos = txtFolFinExplanad.getText();
        if (cant.isEmpty() || cos.isEmpty()) {
           // JOptionPane.showMessageDialog(null, "Campo cantidad o monto vacios\n Verifique Por favor");
             txtFolFinExplanad.requestFocus(true);
        } else {
            int rest = Integer.parseInt(cant),
                    tenRest = Integer.parseInt(cos),
                    result = tenRest - rest;
            txtFoltotExplanad.setText(Integer.toString(result +1));
        }
    }//GEN-LAST:event_txtFolFinExplanadKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        jFramExplanadaCob.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String totNumtic = txtFoltotExplanad.getText(),
                iniTic = txtFolIniExplanad.getText(),
                finTic = txtFolFinExplanad.getText(),
                coast = txtCoastExplan.getText(),
                cad = "";
        String[] arreg = new String[5];
        int numer = Integer.parseInt(totNumtic),
                idMasUno=func.getUltimOtrosCatalog();
        if(numer < 0){
            JOptionPane.showMessageDialog(null, "Folio final debe ser mayor a folio inicial");
        }else{
            cad = "EXPLANADA - TICKET "+iniTic+" AL "+finTic;
            arreg[0] = Integer.toString((idMasUno+1));
            arreg[1] = "4";
            arreg[2] = cad;
            arreg[3] = coast;
            arreg[4] = "1";
            contrl.guardOthsCatalog(arreg);
              jLabConceptName.setText(cad);
              txtIdOculto.setText(arreg[0]);
              txtCantidadOths.setText(totNumtic);
              txtPreciooths.setText(coast);
              txtImportOths.setText(func.multiplicaAmount(new BigDecimal(totNumtic), new BigDecimal(coast)).toString());
              jPanTableBusqView3.setVisible(false);
              jPanvIEWcOAST.setVisible(true);
             jFramExplanadaCob.dispose();
             jButton34.setEnabled(true);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void txtFolFinExplanadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFolFinExplanadFocusGained
        txtFolFinExplanad.setSelectionStart(0);
    }//GEN-LAST:event_txtFolFinExplanadFocusGained

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String nombCli = txtNombClient.getText(),
                dirCli = txtDirClient.getText(),
                corCli = txtCorrClient.getText(),
                obsCli = txtObsClient.getText(),
                telCli = txtTelClient.getText(),
                rfcCli = txtRfcClient.getText();
        
        if(!nombCli.isEmpty() && !obsCli.isEmpty()){
            String[] datcli = new String[7];
             int idCli = func.getIdClient();
             
          datcli[0] = Integer.toString((idCli + 1)); 
          datcli[1] = (nombCli.isEmpty()) ? "" : nombCli;
          datcli[2] = (dirCli.isEmpty()) ? "" : dirCli;
          datcli[3] = (corCli.isEmpty()) ? "" : corCli;
          datcli[4] = (telCli.isEmpty()) ? "" : telCli;
          datcli[5] = (rfcCli.isEmpty()) ? "" : rfcCli;
          datcli[6] = (obsCli.isEmpty()) ? "" : obsCli;
                  contrl.guardCliente(datcli);
          jLabFoli.setText(datcli[0]);
          jLabNombre3.setText(datcli[1]);
          jLabDirecc3.setText(datcli[2]);
          jLabObserva3.setText(datcli[6]);
          jLabThisIs.setText("Cliente");

          txtBusqOtros.setText("");
          jPanDataambView3.setVisible(true);
          jPanTableBusqView2.setVisible(false);
          limpiaRegClient();
          jFramAltaCliente.dispose();
          txtTotOthers.requestFocus(true);
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar por lo menos Nombre y Obs");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
            jFramAltaCliente.setLocationRelativeTo(null);
            jFramAltaCliente.setVisible(true);
            jFramAltaCliente.setEnabled(true);
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        jFramAltaCliente.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        String desc = txtDescripProd.getText(),
                prec = txtPrec.getText();
                
        if(!desc.isEmpty() && !prec.isEmpty()){
            String[] arreg = new String[5];
            String idRub = contenRubOths.get(jCBRubroProd.getSelectedIndex());
            int ultimoC = func.getIdOthsCatalog();
            arreg[0] = Integer.toString((ultimoC + 1));
            arreg[1] = idRub;
            arreg[2] = desc;
            arreg[3] = prec;
            arreg[4] = "1";
            contrl.guardOthsCatalog(arreg);
            jPanTableBusqView3.setVisible(false);
            jPanvIEWcOAST.setVisible(true);
            txtBusqConcept.setText("");
            jLabConceptName.setText(desc);
            txtIdOculto.setText(arreg[0]);
            txtCantidadOths.setText("1");
            txtPreciooths.setText(prec);
            txtImportOths.setText(prec);
            jDialresProdOther.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
         String desc = txtCancelTick.getText();
          int fila = jTabviewPays.getSelectedRow();
       
         if(fila >= 0){
            if(!desc.isEmpty()){
                String[] arregCan = new String[6]; 
                int ultCanc = func.getUltimCancelaciones();
              //  int ultTurno = func.getenTurno(); *
                String fech = datCtrl.setDateActual(),
                        ora = datCtrl.getHour(),
                       mostTic = jTabviewPays.getValueAt(fila, 0).toString(),
                       concepto = jTabviewPays.getValueAt(fila, 2).toString(),
                        aux = jTabviewPays.getValueAt(fila, 4).toString();
               
                 arregCan[0] = Integer.toString((ultCanc+1));
                 arregCan[1] = User; 
                 arregCan[2] = infoUser[6];//Integer.toString(ultTurno); *
                 arregCan[3] = fech;
                 arregCan[4] = ora;
                 arregCan[5] = desc;
              
                    if(concepto.equals("Pago Areas")){
                         contrl.guardInCancelaciones(arregCan);
                        contrl.f5CancelTypesAll("pagos_areas","idCancelacion",mostTic,arregCan[0]);
                    }

                    if(concepto.equals("Pago Ambulantes")){
                        contrl.guardInCancelaciones(arregCan);
                        contrl.f5CancelTypesAll("pagos_amb","idCancelacion",mostTic,arregCan[0]);
                        
                        String idambB = func.getidUserConTicket("idAmb","pagos_amb",mostTic);
                        System.out.println("Cancelar de amb"+idambB);
                        String[] arR = contrl.regpagosambdet(Integer.parseInt(contrl.getpagosAmbulante(idambB,"6")),6);
                        System.out.println("IdSemana"+arR[3]);
                        
                        contrl.f5CancelTypesAll("ambulantes","ultimaSem",idambB,arR[3]);
                    }

                    if(concepto.equals("Pago Cargadores")){     
                        contrl.guardInCancelaciones(arregCan);
                        contrl.f5CancelTypesAll("pagos_carg","idCancelacion",mostTic,arregCan[3]);
                    }

                    if(concepto.equals("Pago Infraccion")){   
                        String[] arrCancInfracc = new String[7];
                        arrCancInfracc[0] = mostTic;
                        arrCancInfracc[1] = infoUser[6];
                        arrCancInfracc[2] = User;
                        arrCancInfracc[3] = fech;
                        arrCancInfracc[4] = ora;
                        arrCancInfracc[5] = desc;
                        arrCancInfracc[6] = aux;
                        
                        contrl.guardInCancelInfracc(arrCancInfracc);
                    }
                    if(concepto.equals("Varios Amb.") || concepto.equals("Varios Cte.") || concepto.equals("Varios Carg.")){                    
                        contrl.guardInCancelaciones(arregCan);
                        contrl.f5CancelTypesAll("otros_venta","idCancelacion",mostTic,arregCan[0]);
                    }
                    if(concepto.equals("Pago Renta Carg")){                    
                        contrl.guardInCancelaciones(arregCan);
                        contrl.f5CancelTypesAll("pagos_cargrenta","idCancelacion",mostTic,arregCan[0]);
                    }
                    
            }else{
                JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
            }//IS ISEMPTY
        }else{
                    JOptionPane.showMessageDialog(null, "Debe elegir que mostrar");
          }//IFFILA >=0
         jDialCancelaciones.dispose();
         jButton19.doClick();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        jDialCancelaciones.setLocationRelativeTo(null);
        jDialCancelaciones.setVisible(true);
        jDialCancelaciones.setEnabled(true);
    //    jDialCancelaciones.setTitle("Cancelacion de Renta cargador");
        txtCancelTick.setText("");
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
     jDialCancelaciones.dispose();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jPanInternGastosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanInternGastosFocusGained
        JOptionPane.showMessageDialog(null, "elijio y cargar");
    }//GEN-LAST:event_jPanInternGastosFocusGained

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
                String[] arregCan = new String[9];
                int ultGast = func.getUltimGastCaja();
               // int ultTurno = func.getenTurno(); *

                String[] arregModif = new String[4];

                 String fech = datCtrl.setDateActual(),
                        ora = datCtrl.getHour(),
                        idRub = contenRubGastos.get(jCombBTypeRubros.getSelectedIndex()),
                        concept = txtConcept.getText(),
                        solicit = txtsolict.getText(),
                        obserGast = txtObservs.getText(),
                        mont = txtMontoGasto.getText(),
                        stButn = jButton39.getText();
                if(!concept.isEmpty() && !mont.isEmpty()){
                    arregCan[0] = Integer.toString((ultGast + 1 ));
                    arregCan[1] = infoUser[6];//Integer.toString(ultTurno); *
                    arregCan[2] = fech;
                    arregCan[3] = ora;
                    arregCan[4] = idRub; 
                    arregCan[5] = concept; arregModif[0] = concept;
                    arregCan[6] = solicit; arregModif[1] = solicit;
                    arregCan[7] = obserGast; arregModif[2] = obserGast;
                    arregCan[8] = mont; arregModif[3] = mont;
                    
                    if(stButn.equals("Guardar"))
                        contrl.guardGastoCaja(arregCan,"");
                    if(stButn.equals("Modi"
                            + "ficar")){
                         int fila = jTabViewGastos.getSelectedRow();
                         String mostTic = jTabViewGastos.getValueAt(fila, 0).toString();
                        contrl.guardGastoCaja(arregModif,mostTic);
                    }
                    limpiaguardGasto();
                    jDialAltaGastos.dispose();
                    mostrarGastosDia("", infoUser[6]);
                }else{
                    JOptionPane.showMessageDialog(null, "Campo Concepto y Obs no pueden ser vacios");
                }
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
         int fila = jTabViewGastos.getSelectedRow();
                if(fila >= 0){
                    String mostTic = jTabViewGastos.getValueAt(fila, 0).toString(),
                            concepto = jTabViewGastos.getValueAt(fila, 2).toString();
                    String[] muestra = func.getDatsGasto(mostTic);
                    jDialAltaGastos.setLocationRelativeTo(null);
                        jDialAltaGastos.setVisible(true);
                        jDialAltaGastos.setEnabled(true);
                        jButton39.setText("Modificar");
                        jCombBTypeRubros.setSelectedItem(concepto);
                        txtConcept.setText(muestra[1]);
                        txtsolict.setText(muestra[2]);
                        txtObservs.setText(muestra[3]);
                        txtMontoGasto.setText(muestra[4]);
                }else{
                    JOptionPane.showMessageDialog(null, "Debe elegir que mostrar");
                }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
           jDialAltaGastos.setLocationRelativeTo(null);
            jDialAltaGastos.setVisible(true);
            jDialAltaGastos.setEnabled(true);
            jButton39.setText("Guardar");
            limpiaguardGasto();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        jDialAltaGastos.dispose();
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
         int fila = jTabViewGastos.getSelectedRow();
                if(fila >= 0){
                    String mostTic = jTabViewGastos.getValueAt(fila, 0).toString()
                          ;
                    contrl.elimaRow("gastos_caja","id",mostTic); 
                    mostrarGastosDia("",infoUser[6]);
                }else{
                    JOptionPane.showMessageDialog(null, "Debe elegir que mostrar");
                }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jCBBusqTicketallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBBusqTicketallActionPerformed
        int opc = jCBBusqTicketall.getSelectedIndex();
        txtBusqTickAll.setText("");
        jDatChoFechBusqtick.setDate(datCtrl.cargafecha());
        if(opc == 0){
            txtBusqTickAll.setVisible(true);
            jDatChoFechBusqtick.setVisible(false);
        }else{
            txtBusqTickAll.setVisible(false);
            jDatChoFechBusqtick.setVisible(true);
        }
    }//GEN-LAST:event_jCBBusqTicketallActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int opc = jCBBusqTicketall.getSelectedIndex();
        if(opc == 1){
         String fechin = datCtrl.getFecha(jDatChoFechBusqtick);

         String[][] mat = contrl.matrizgetTicketsDia(fechin,"");
         DefaultTableModel modelo = new TModel(mat, cabAreasPays);
         jTabviewPays.setModel(modelo);
         TableRowSorter<TableModel> ordenon = new TableRowSorter<TableModel>(modelo);
         jTabviewPays.setRowSorter(ordenon);
        
//         jTabviewPays.setModel(new TModel(mat, cabAreasPays));
         tamColViwePays();
//para cargar gastos de la misma fecha de busqueda    
          jCombBOpcBusqGastos.setSelectedIndex(1);
          jDateChoGastos.setDate(datCtrl.StringDate(fechin));
          jButton44.doClick();
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void dCCFechIniRentCargOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dCCFechIniRentCargOnCommit
         String var = datCtrl.volteaFecha(dCCFechIniRentCarg.getText(),0);
         Calendar cal  = Calendar.getInstance();
         cal.setTime(datCtrl.StringDate(var));
         dCCFechFinRentCarg.setSelectedDate(cal);
         txtnumDaysrentdiab.setText("1");
         txtNumDiabRent.setText("1");
         jLabImportRentDiab.setText(jLabTarifRentCarg.getText());
         txtTotalCarg.setText(jLabTarifRentCarg.getText());
    }//GEN-LAST:event_dCCFechIniRentCargOnCommit

    private void dCCFechFinRentCargOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dCCFechFinRentCargOnCommit
        String fechInR = datCtrl.volteaFecha(dCCFechIniRentCarg.getText(),0),
                fechFnR = datCtrl.volteaFecha(dCCFechFinRentCarg.getText(),0),
                numd ="";
        int dias=0;
        //Calcula el numero de dias desde la fecha actual a la de ultima regla
        dias =(int)datCtrl.getDiffDates(datCtrl.StringDate(fechInR), datCtrl.StringDate(fechFnR), 2);
        txtnumDaysrentdiab.setText(Integer.toString(dias +1));
        numd=txtnumDaysrentdiab.getText();
        if(numd.equals("-1")){
            JOptionPane.showMessageDialog(null, "Fecha inicio debe ser mayor a fecha final");
        }else{
            String tarif = jLabTarifRentCarg.getText(),
                    condon = jLabCondonacRentDiab.getText(),
                    auxD ="";
            BigDecimal tari = new BigDecimal(tarif);
            BigDecimal condo = new BigDecimal(condon);
            BigDecimal nud = new BigDecimal(numd);
            
            auxD = func.percentage(tari, condo).toString();
jLabImportRentDiab.setText(func.getDifference( func.multiplicaAmount(tari, nud), func.multiplicaAmount(nud, new BigDecimal(auxD))).toString());
txtTotalCarg.setText(jLabImportRentDiab.getText());
        }

    }//GEN-LAST:event_dCCFechFinRentCargOnCommit

    private void jTabviewPaysMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabviewPaysMousePressed
          if (evt.getClickCount() > 1) {
            int fila = jTabviewPays.getSelectedRow();
                if(fila >= 0){
                    String mostTic = jTabviewPays.getValueAt(fila, 0).toString(),
                            concepto = jTabviewPays.getValueAt(fila, 2).toString();
                    if(concepto.equals("Pago Areas")){
                        String[] dat = rP.getUltimPagoarea(mostTic);
                        rP.imprim80MM(mostTic, dat,false);
                    }
                    if(concepto.equals("Pago Ambulantes")){
                        String[] dat = rP.getTickPagoAmbu(mostTic);
                        rP.imprim80MMAmbus(mostTic, dat,false);
                    }
                    if(concepto.equals("Pago Cargadores")){                    
                        String[] dat = rP.getTickPagoCargad(mostTic);
                        rP.imprim80MMCargad(mostTic, dat,false);
                    }
                    if(concepto.equals("Pago Infraccion")){                    
                         rP.imprim80MM_Infrac(mostTic,false);
                    }
                    if(concepto.equals("Varios Amb.") || concepto.equals("Varios Cte.") || concepto.equals("Varios Carg.")){                    
                         String[] dat = rP.getTickOthers(mostTic);
                         rP.imprim80MMOthers(mostTic,dat,false);
                    }
                    if(concepto.equals("Pago Renta Carg")){                    
                         rP.imprim80MM_CargRent(mostTic,false);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe elegir que mostrar");
                }
              }
    }//GEN-LAST:event_jTabviewPaysMousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String[] turns = func.getTurnoData(Integer.parseInt(infoUser[6]));
        String[] paramDats = new String[7];
        
        paramDats[0] = infoUser[1];
        paramDats[1] = turns[3];
        paramDats[2] = turns[4];
        paramDats[3] = turns[2];
        paramDats[4] = sumCorteCaj(infoUser[6]);
        paramDats[5] = func.totalturno(6,infoUser[6]);
        
        BigDecimal salInic = new BigDecimal(turns[2]);
        BigDecimal Cobros = new BigDecimal(paramDats[4]);
        BigDecimal gastosCaj = new BigDecimal(paramDats[5]);
        
        paramDats[6] = func.getDifference(func.getSum(Cobros, salInic), gastosCaj).toString();
        
        rP.imprim80MM_corteCaja(infoUser[6],false,paramDats);
        rP.creListenerButton(Integer.parseInt(infoUser[6]), infoUser[2],infoUser[0]);
       // jDateChoCorte.setDate(datCtrl.cargafecha());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jCombBOpcBusqGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombBOpcBusqGastosActionPerformed
        int opc = jCombBOpcBusqGastos.getSelectedIndex();
        txtBusGasto.setText("");
        jDateChoGastos.setDate(datCtrl.cargafecha());
        if(opc == 0){
            txtBusGasto.setVisible(true);
            jDateChoGastos.setVisible(false);
        }else{
            txtBusGasto.setVisible(false);
            jDateChoGastos.setVisible(true);
        }
    }//GEN-LAST:event_jCombBOpcBusqGastosActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        int opc = jCombBOpcBusqGastos.getSelectedIndex();
        if(opc == 1){
         String fechin = datCtrl.getFecha(jDateChoGastos);
            mostrarGastosDia(fechin,"");
        }
    }//GEN-LAST:event_jButton44ActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
     if (evt.getKeyCode()==KeyEvent.VK_ENTER){
          JOptionPane.showMessageDialog(null, "ora");
       }
        
    }//GEN-LAST:event_formKeyReleased

    private void txtBuscAmbulanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscAmbulanteKeyReleased
         if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_txtBuscAmbulanteKeyReleased

    private void jCheckResguardAmbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckResguardAmbKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jCheckResguardAmbKeyReleased

    private void jButMinusSemsPaysAmbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButMinusSemsPaysAmbKeyReleased
         if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jButMinusSemsPaysAmbKeyReleased

    private void jButMostSemsPaysAmbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButMostSemsPaysAmbKeyReleased
         if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jButMostSemsPaysAmbKeyReleased

    private void jCheckSemPaysAmbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckSemPaysAmbKeyReleased
         if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jCheckSemPaysAmbKeyReleased

    private void jButMinusResgPaysAmbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButMinusResgPaysAmbKeyReleased
                if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jButMinusResgPaysAmbKeyReleased

    private void jButMostSemsPaysAmb1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButMostSemsPaysAmb1KeyReleased
                if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jButMostSemsPaysAmb1KeyReleased

    private void jButMinusResgSemfinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButMinusResgSemfinKeyReleased
                if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jButMinusResgSemfinKeyReleased

    private void jButMostSemsPaysAmb2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButMostSemsPaysAmb2KeyReleased
                if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jButMostSemsPaysAmb2KeyReleased

    private void jCBoxResguardosOpcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCBoxResguardosOpcKeyReleased
                if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jCBoxResguardosOpcKeyReleased

    private void jCheckInscripPaysAmbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckInscripPaysAmbKeyReleased
                if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jCheckInscripPaysAmbKeyReleased

    private void jDateChoInscripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChoInscripcionKeyReleased
                if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
    }//GEN-LAST:event_jDateChoInscripcionKeyReleased

    private void jCBoxDuracInscripcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCBoxDuracInscripcKeyReleased
                if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton22.doClick();
                }
              
    }//GEN-LAST:event_jCBoxDuracInscripcKeyReleased

    private void jCmBxgetAreasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCmBxgetAreasKeyReleased
                   int var = evt.getKeyCode();
     //      System.out.println("Oprimio #"+ var);
    }//GEN-LAST:event_jCmBxgetAreasKeyReleased

    private void jChecMantSemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jChecMantSemKeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton15.doClick();
                }
    }//GEN-LAST:event_jChecMantSemKeyReleased

    private void jChecBasuraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jChecBasuraKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton15.doClick();
                }
    }//GEN-LAST:event_jChecBasuraKeyReleased

    private void jButMantenSubstractKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButMantenSubstractKeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton15.doClick();
                }
    }//GEN-LAST:event_jButMantenSubstractKeyReleased

    private void jButMantenMooreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButMantenMooreKeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton15.doClick();
                }
    }//GEN-LAST:event_jButMantenMooreKeyReleased

    private void jButBasuraSubstractKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButBasuraSubstractKeyReleased
       if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton15.doClick();
                }
    }//GEN-LAST:event_jButBasuraSubstractKeyReleased

    private void jButBasuraMooreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButBasuraMooreKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton15.doClick();
                }
    }//GEN-LAST:event_jButBasuraMooreKeyReleased

    private void txtBuscCargadoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscCargadoresKeyReleased
      if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton37.doClick();
                }
    }//GEN-LAST:event_txtBuscCargadoresKeyReleased

    private void jCkBoxSemanasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCkBoxSemanasKeyReleased
              if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton37.doClick();
                }
    }//GEN-LAST:event_jCkBoxSemanasKeyReleased

    private void jButMinusSemsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButMinusSemsKeyReleased
              if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton37.doClick();
                }
    }//GEN-LAST:event_jButMinusSemsKeyReleased

    private void jButMooreSemsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButMooreSemsKeyReleased
              if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton37.doClick();
                }
    }//GEN-LAST:event_jButMooreSemsKeyReleased

    private void jCkBoxInscripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCkBoxInscripcionKeyReleased
              if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton37.doClick();
                }
    }//GEN-LAST:event_jCkBoxInscripcionKeyReleased

    private void jComBInscripcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComBInscripcKeyReleased
              if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton37.doClick();
                }
    }//GEN-LAST:event_jComBInscripcKeyReleased

    private void jDatChoIncripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDatChoIncripcionKeyReleased
              if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton37.doClick();
                }
    }//GEN-LAST:event_jDatChoIncripcionKeyReleased

    private void txtNumDiabRentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumDiabRentKeyReleased
             if(evt.getKeyCode()==KeyEvent.VK_F5){
                    jButton37.doClick();
                }
    }//GEN-LAST:event_txtNumDiabRentKeyReleased

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
            String nomb = jLabNomAmb.getText(),
                    idBusq = jLabIdAmbu.getText();
            vistaReimprAll altamb = new vistaReimprAll(1,idBusq);
            altamb.setLocationRelativeTo(null);
            altamb.setVisible(true);
            altamb.setEnabled(true);
            altamb.validate();
            altamb.setTitle("Ambulante");
            altamb.jLabNombre.setText(nomb);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
            String nomb = jLabNombreCargadores.getText(),
            idBusq = jlabIdCargador.getText();
            vistaReimprAll altamb = new vistaReimprAll(2,idBusq);
            altamb.setLocationRelativeTo(null);
            altamb.setVisible(true);
            altamb.setEnabled(true);
            altamb.validate();
            altamb.setTitle("Cargador");
            altamb.jLabNombre.setText(nomb);
    }//GEN-LAST:event_jButton36ActionPerformed

    private void txtCancelTickKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCancelTickKeyReleased
          if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                  jButton25.doClick();
          }
    }//GEN-LAST:event_txtCancelTickKeyReleased

    private void jTabViewGastosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabViewGastosMouseClicked
          if (evt.getClickCount() > 1) {
                 jButton29.doClick();
          }
    }//GEN-LAST:event_jTabViewGastosMouseClicked

    private void txtNamePagadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamePagadKeyPressed
      int var = evt.getKeyCode();
        if(var == KeyEvent.VK_ENTER){
            jButton8.doClick();
        }
    }//GEN-LAST:event_txtNamePagadKeyPressed

    private void jDialAltaGastosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialAltaGastosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDialAltaGastosKeyPressed

    private void txtConceptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConceptKeyPressed
         int var = evt.getKeyCode();
         if(var == 27 ){
                    jDialAltaGastos.dispose();
            }else{
            if(var == KeyEvent.VK_ENTER){
                jButton39.doClick();
            }
         }
    }//GEN-LAST:event_txtConceptKeyPressed

    private void txtsolictKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsolictKeyPressed
                int var = evt.getKeyCode();
         if(var == 27 ){
                    jDialAltaGastos.dispose();
            }else{
            if(var == KeyEvent.VK_ENTER){
                jButton39.doClick();
            }
         }
    }//GEN-LAST:event_txtsolictKeyPressed

    private void txtObservsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservsKeyPressed
                int var = evt.getKeyCode();
         if(var == 27 ){
                    jDialAltaGastos.dispose();
            }else{
            if(var == KeyEvent.VK_ENTER){
                jButton39.doClick();
            }
         }
    }//GEN-LAST:event_txtObservsKeyPressed

    private void txtMontoGastoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoGastoKeyPressed
               int var = evt.getKeyCode();
         if(var == 27 ){
                    jDialAltaGastos.dispose();
            }else{
            if(var == KeyEvent.VK_ENTER){
                jButton39.doClick();
            }
         }
    }//GEN-LAST:event_txtMontoGastoKeyPressed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
          String nomb = jCmBxgetAreas.getSelectedItem().toString(),
              idBusq = conten.get(jCmBxgetAreas.getSelectedIndex());//idArea
          System.out.println("IdArea: "+idBusq);
            vistaReimprAll altamb = new vistaReimprAll(0,idBusq);
            altamb.setLocationRelativeTo(null);
            altamb.setVisible(true);
            altamb.setEnabled(true);
            altamb.validate();
            altamb.setTitle("Area");
            altamb.jLabNombre.setText(nomb);
    }//GEN-LAST:event_jButton45ActionPerformed

    //metodo para llenar combo de areas
        private void llenacombogetAreas() {
            Connection cn = con2.conexion();
            conten.clear();
            jCmBxgetAreas.removeAllItems();
            String consul = "SELECT id, nombre from areas ORDER BY nombre";
            Statement st = null;
            ResultSet rs = null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(consul);
                while (rs.next()) {
                    conten.add(rs.getString(1));
                    jCmBxgetAreas.addItem(rs.getString(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (cn != null) {
                        cn.close();
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
    }//Llena getAreas 

    //metodo para llenar combo de areas
        private void llenacombogetcuentaGasto() {
            Connection cn = con2.conexion();
            contenRubGastos.clear();
            jCombBTypeRubros.removeAllItems();
            String consul = "SELECT id, concepto from rubroscaja ORDER BY concepto";
            Statement st = null;
            ResultSet rs = null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(consul);
                while (rs.next()) {
                    contenRubGastos.add(rs.getString(1));
                    jCombBTypeRubros.addItem(rs.getString(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (cn != null) {
                        cn.close();
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
    }//Llena llenacombogetcuentaGasto 
        

        
       //funcion para busqueda automatica ambulantes
        void mostrarTabla1(String var){
        int band = 0;
        Connection cn = con2.conexion();
        DefaultTableModel modelo = new DefaultTableModel()
        { 
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        String consul="";
         //   if(atributo.equals("nombre") || atributo.equals("")){
           consul ="SELECT ambulantes.id,ambulantes.nombre,ambulantes.direccion,giros.giro,ambulantes.obs \n" +
                            "FROM central.ambulantes\n" +
                            "INNER JOIN central.giros\n" +
                            "ON ambulantes.idGiro = giros.id AND ambulantes.activo > 0 AND (ambulantes.id LIKE '"+var+"%'  OR ambulantes.nombre LIKE '"+var+"%') ORDER BY ambulantes.id;";   
         //consul = "SELECT id, nombre from ambulantes WHERE id LIKE '"+var+"%'  OR nombre LIKE '"+var+"%' ORDER BY id";
                modelo.addColumn("ID");
                modelo.addColumn("NOMBRE");
        jTabDatosAmbulante.setModel(modelo);
        TableColumnModel columnModel = jTabDatosAmbulante.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(50);
        /*jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        */
        String datos[] =  new String[2];//tenia 4
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(consul);
            
            while(rs.next()){
                datos[0] =rs.getString(1);
                datos[1] = rs.getString(2);
                modelo.addRow(datos);
            }
            jTabDatosAmbulante.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             try {
                    if(rs != null) rs.close();              
                    if(st != null) st.close();                
                    if(cn !=null) cn.close();
             } catch (SQLException ex) {
             }
         }
    }
       
    //funcion para busqueda automatica Cargadores
        void mostrarTablaCargadores(String var){
        int band = 0;
        Connection cn = con2.conexion();
        
        DefaultTableModel modelo = new DefaultTableModel()
        { 
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        String consul="";
         //   if(atributo.equals("nombre") || atributo.equals("")){
consul = "SELECT id, nombre from cargadores WHERE cargadores.activo > 0 AND (id LIKE '"+var+"%'  OR nombre LIKE '"+var+"%') ORDER BY id";
                modelo.addColumn("ID");
                modelo.addColumn("NOMBRE");

      
        jTabCargadoresView.setModel(modelo);
        TableColumnModel columnModel = jTabCargadoresView.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(50);

        String datos[] =  new String[2];//tenia 4
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(consul);

            while(rs.next()){
                datos[0] =rs.getString(1);
                datos[1] = rs.getString(2);
                modelo.addRow(datos);
            }
            jTabCargadoresView.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             try {
                    if(rs != null) rs.close();              
                    if(st != null) st.close();                
                    if(cn !=null) cn.close();
             } catch (SQLException ex) {
             }
         }
    }    //busqueda cargadores

        /*** METODO PARA CARGAR DINAMICAMENTE DATOS EN JPANELES*/
          protected void mostrarJpanNew(){
            jPanMantenPay.setVisible(false);
            jPanBasuraContenPays.setVisible(false);
            jPanPiliceContenPays.setVisible(false);
            jPanResguardContenPays.setVisible(false);   
      //      System.out.print("\t***** contenSize: "+conten.size());
            int idA=Integer.parseInt(conten.get(jCmBxgetAreas.getSelectedIndex())),
                      numtic=0,numoriginal=0;
            String[] arr = contrl.regOpsareas(idA);//array para obtener cuotas a cobrar segun area seleccionada
//            System.out.println();
            txtResultSum.setText("0.00");
          String aux ="";
            if(!arr[0].equals("0.00")){//cobrar Mantenimiento Semanal
               jPanMantenPay.setVisible(true); 
               jChecMantSem.setSelected(false);
               jLabSemsPaysManten.setText("1");
               numtic=contrl.regLastTicket2(idA,2);//Obtener #ticket ultimo con pago de mantenimiento
               String[] arr1M = contrl.regPaysAreasdet(numtic,2);
               numoriginal = Integer.parseInt(arr1M[2]);//obtenemos id de semana pagada con #ticket y #rubro
               String[] arr2M = contrl.regSemanas(numoriginal);//datos de semana id
               txtMantenIdSem.setText(arr2M[0]);//idSemana Oculto
              jLabLastMantenim.setText("Semana "+arr2M[2]);
              arr2M =null;
              arr2M = contrl.regSemanas(numoriginal+1);
              idSemMantini = Integer.parseInt(arr2M[0]);  
               aux = datCtrl.getWeekStartDate(arr2M[3]) + " - "+ datCtrl.getWeekStartDate(arr2M[4]);
                txtIniManten.setText(arr2M[2]);
                jLaFechIniManten.setText(aux);
                txtFinManten.setText(arr2M[2]);
                jLaFechFinManten.setText(aux);
                jLabTarifaMantenim.setText(arr[0]);
                jlabImportMantenim.setText(arr[0]);                    
            }  
            if(!arr[1].equals("0.00")){//cobrar Basura Semanal
                jPanBasuraContenPays.setVisible(true);
                jChecBasura.setSelected(false);
                jLabSemsPaysBasura.setText("1");
                 numtic=contrl.regLastTicket2(idA,3);
                 String[] arr1B = contrl.regPaysAreasdet(numtic,3);
               numoriginal = Integer.parseInt(arr1B[2]);//obtenemos id de semana pagada con #ticket y #rubro
               String[] arr2B = contrl.regSemanas(numoriginal);//datos de semana id
               
               txtBasurIdSem.setText(arr2B[0]);//idSemana Oculto
               
             jLabLastBasura.setText("Semana "+arr2B[2]);
              arr2B =null;
              arr2B = contrl.regSemanas(numoriginal+1);
              idSemBasura = Integer.parseInt(arr2B[0]);
             
                aux = datCtrl.getWeekStartDate(arr2B[3]) + " - "+ datCtrl.getWeekStartDate(arr2B[4]);
                 
                   txtIniBasura.setText(arr2B[2]);
                   jLaFechIniBasura.setText(aux);
                   txtFinBasura.setText(arr2B[2]);
                   jLaFechFinBasura.setText(aux);
                   
                   jLabTarifaBasura.setText(arr[1]);
                   jlabImportBasura.setText(arr[1]);
            }  
            if(!arr[2].equals("0.00")){//cobrar policia
                jPanPiliceContenPays.setVisible(true);
                jChecPolicia.setSelected(false);
                jLabSemsPaysPolis.setText("1");
                 numtic=contrl.regLastTicket2(idA,4);
                 String[] arr1P = contrl.regPaysAreasdet(numtic,4);
               numoriginal = Integer.parseInt(arr1P[2]);//obtenemos id de semana pagada con #ticket y #rubro
               String[] arr2P = contrl.regSemanas(numoriginal);//datos de semana id
               
               txtPoliciaIdSem.setText(arr2P[0]);//idSemana Oculto
               
                 jLabLastPolicia.setText("Semana "+arr2P[2]);//mostramos #semana NO el id
              arr2P =null;
              arr2P = contrl.regSemanas(numoriginal+1);
              idSemPolicia= Integer.parseInt(arr2P[0]);
            
              
                aux = datCtrl.getWeekStartDate(arr2P[3]) + " - "+ datCtrl.getWeekStartDate(arr2P[4]);
                txtIniPolic.setText(arr2P[2]);
                jLaFechIniPolicia.setText(aux);
                txtFinPolicia.setText(arr2P[2]);
                jLaFechFinPolicia.setText(aux);
                
                jLabTarifPolicia.setText(arr[2]);
                jlabImportPolicia.setText(arr[2]);
            }  
            if(!arr[3].equals("0.00")){//cobrar otros o resgurado
              jPanResguardContenPays.setVisible(true);     
              jChecResguardo.setSelected(false);
              jLabSemsPaysResg.setText("1");
               numtic=contrl.regLastTicket2(idA,5);
               String[] arr1R = contrl.regPaysAreasdet(numtic,5);
               numoriginal = Integer.parseInt(arr1R[2]);//obtenemos id de semana pagada con #ticket y #rubro
               String[] arr2R = contrl.regSemanas(numoriginal);//datos de semana id
               
               txtResguardIdSem.setText(arr2R[0]);//idSemana Oculto
               
             jLabLastResguardo.setText("Semana "+arr2R[2]);//mostramos #semana NO el id
              arr2R =null;
              arr2R = contrl.regSemanas(numoriginal+1);
              
              idSemResguard= Integer.parseInt(arr2R[0]);//variable para tener semanaidUltima+1
           
                aux = datCtrl.getWeekStartDate(arr2R[3]) + " - " + datCtrl.getWeekStartDate(arr2R[4]);
                txtIniResg.setText(arr2R[2]);
                jLaFechIniResguardos.setText(aux);
                txtFinResguard.setText(arr2R[2]);
                jLaFechFinResguard.setText(aux);
                
                jLabTarifResguard.setText(arr[3]);
                jlabImportResguard.setText(arr[3]);
            }
            
            jChecMantSem.doClick();
            jChecBasura.doClick();
          }//mostrarJpanNew
          
        /*** METODO PARA CARGAR DINAMICAMENTE DATOS EN JPANELES ambulantes*/
      protected void mostrarJpanAmbulantes(String idAmb){
 //1. Obtener datos de tabla ambulantes, idResg, tarifa, condonaciones, vigMembresia
                jCheckSemPaysAmb.setSelected(false);
                jCheckResguardAmb.setSelected(false);
                jCheckInscripPaysAmb.setSelected(false);
                txtResultAmbu.setText("0.00");
     tarifas = func.getAmbusCondonac(idAmb);//obtenemos tarifas y descuentos a cobrar de ambulante
            String idTicultimaSem = "",
                    idresguardUltm="";
            String aux ="";
            int numSemanaOrigin =0,
                    numSemanaResguard=0
                    ;
            
            idTicultimaSem = datasAmbUltSem[3];//contrl.getpagosAmbulante(idAmb, "6");//ulimopago de #ambulante,Semana
            if(idTicultimaSem.isEmpty() || idTicultimaSem.equals("")  || idTicultimaSem == null){
                JOptionPane.showMessageDialog(null, "Verificar ultima semana Ambualnte.");
            }else{
        //    JOptionPane.showMessageDialog(null, "Regreso de ribropag=6: "+idTicultimaSem); (idTicultimaSem),6)
        //    String[] arr = contrl.regpagosambdet(Integer.parseInt(idTicultimaSem),6);//array para obtener cuotas a cobrar segun area seleccionada

            txtIdSemOcultoAmb.setText(idTicultimaSem);//mostrar idSemana ultimo pago tenia arr[3]
            numSemanaOrigin = Integer.parseInt(idTicultimaSem);//ALMACENAMOS id de semana pagada con #ticket y #rubro
            String[] arrSem = contrl.regSemanas(numSemanaOrigin);//datos de semana id pagada;
            jLabUltimaSemanaPay.setText("Semana : "+arrSem[2]);//mostramos numero de semana original
            String[] arrSemPlus =null;
            arrSemPlus = contrl.regSemanas(numSemanaOrigin+1);
            numSemanaOrigin  = Integer.parseInt(arrSemPlus[0]);  //guardamos idSemana mas uno que se va a ´pagar
            idSemambu = Integer.parseInt(arrSemPlus[0]);//idSemanaPagada+1
            
            aux = datCtrl.getWeekStartDate(arrSemPlus[3]) + " - "+ datCtrl.getWeekStartDate(arrSemPlus[4]);
            
            txtSeminiAmb.setText(arrSemPlus[2]);
            jLaFechIniSemana.setText(aux);
            txtSemFinAmb.setText(arrSemPlus[2]);
            jLabAumentaSemanas.setText(aux);
            }
              jLabContadorSemanas.setText("1");
              jLabDstoSemanas.setText(tarifas[2]);
              jLabTarifaSemanas.setText(tarifas[5]);
//Llenar campos de resguardo
 idresguardUltm=contrl.getpagosAmbulante(idAmb, "7");//rubropago=7, Resguardo ambulante
    if(tarifas[0].equals("0") || idresguardUltm.isEmpty() || idresguardUltm.equals("")  || idresguardUltm == null){
        jLabUltimaResguardPay.setText("N/A");
    }else{     
     String[] arrResg = contrl.regpagosambdet(Integer.parseInt(idresguardUltm),7);
     
     txtIdResguardOcultoAmb.setText(arrResg[3]);
     numSemanaResguard= Integer.parseInt(arrResg[3]);
     
      String[] arrSemResg = contrl.regSemanas(numSemanaResguard);//datos de semana id pagada;
      jLabUltimaResguardPay.setText(arrSemResg[2]);//mostramos numero de semana original
      String[] arrSemPlusResg =null;
      arrSemPlusResg = contrl.regSemanas(numSemanaResguard+1);
      numSemanaResguard  = Integer.parseInt(arrSemPlusResg[0]);
      
      idResguardAmbu = Integer.parseInt(arrSemPlusResg[0]);//idSemana ultimoPay +1
      idResguardAmbu2 = idResguardAmbu;//variables para los dos campos incrementables
      
   //     System.err.println("idAmbu yesData= "+idResguardAmbu);
      aux = datCtrl.getWeekStartDate(arrSemPlusResg[3]) + " - "+ datCtrl.getWeekStartDate(arrSemPlusResg[4]);
      
       txtResgIniAmb.setText(arrSemPlusResg[2]);
         jLaFechIniResguard.setText(aux);
         txtResgFinAmb.setText(arrSemPlusResg[2]);
         jLabAumResguard.setText(aux);
    }
     jLabContSemsResguard.setText("1");
     jLabDstoResguard.setText(tarifas[3]);
         if (tarifas[4] == null || tarifas[4].equals("0000-00-00") || tarifas[4].isEmpty()) {
              jLabel60.setText("N/A");
               jLabVigenciaView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png")));
              jLabVigenciaView.setText("No hay pagos");
          }else{
              jLabel60.setText("Vigente al");
              jLabVigenciaView.setIcon(null);
              jLabVigenciaView.setText(tarifas[4]);
              jDateChoInscripcion.setDate(datCtrl.cargafecha());
          }
        jLabDstoInscripcion.setText(tarifas[1]);
          }//mostrarJpanAmbulantes
      
        
        /*** METODO PARA CARGAR DINAMICAMENTE DATOS EN JPANELES cargadores*/
      protected void mostrarJpanCargadores(String[] datAMB){
          limpiPreDatCargad();
          String idSem = "",aux="";
          String[] sem1 = null,
                  sem2 =null,
                  sem3 = null;
          BigDecimal amTarif, amDtoTarif,
                  inscDto,inscimport,auxBig;
          
          if(datAMB[4].equals("0")){// 0 = propio
              jButton40.setVisible(true);
              jButton41.setVisible(true);
              jButton40.doClick();

/////PARA PANEL PAGO DE SEMANAS
              jLabcontSemanas.setText("1");
              jLabtarifSemanas.setText(datAMB[11]);
              jLabDtoSemanas.setText(datAMB[6]);
              
              amTarif = new BigDecimal(datAMB[11]);
              amDtoTarif = new BigDecimal(datAMB[6]);
              jLabImportSemanasCargad.setText(func.getDifference(amTarif, func.percentage(amTarif, amDtoTarif)).toString());

              if( datAMB[0].equals("NO-DATA") || datAMB[9].equals("0") ){
                   jLabel100.setText("New User");
 //obtenemos semana dada la fecha actual, caso no tener niunpago registrado
                   sem1 = func.lapsoSemanasIni(datCtrl.setDateActual());
                   txtOcultiDSemana.setText(sem1[0]);
                   jLabnumSem.setText(sem1[2]);
              }else{
                  txtOcultiDSemana.setText(datasCarg[9]);
                  sem1 = contrl.regSemanas(Integer.parseInt(datasCarg[9]));
                  jLabnumSem.setText(sem1[2]);
              }
//obtenemos datos de la semana siguiente al ultimo pago es igual a partir de aca
               sem2 = contrl.regSemanas(Integer.parseInt(sem1[0])+1);
                   idSemCargad = Integer.parseInt(sem2[0]);
                   txtcontSemsIni.setText(sem2[2]);
                   aux = datCtrl.getWeekStartDate(sem2[3]) + " - "+ datCtrl.getWeekStartDate(sem2[4]);
                   jLabIniSemsViews.setText(aux);
                   txtcontSemsFin.setText(sem2[2]);
                   jLabFinSemsViews.setText(aux);
//jLabnumSem.setText( (datasCarg[0].equals("NO-DATA") || datasCarg[9].equals("0")) ? "N/A" :datasCarg[9]); 
     // cargar datos de menbresia
        if(datAMB[8].equals("") ||  datAMB[8].isEmpty() || datAMB[8].equals("0000-00-00")){
           jLabel53.setText("Sin Inscripcion");
           jDatChoIncripcion.setDate(datCtrl.cargafecha());
        }else{
            jLabel53.setText("Vigencia :- ");
            jLabVigencia.setText(datAMB[8]);
            jDatChoIncripcion.setDate(datCtrl.StringDate(datAMB[8].replace('-', '/')));
        }
 //@end cargar datos de membresia  
            jComBInscripc.setSelectedIndex(0);
            jLabImportInscripc.setText(datAMB[12]);
            jLabDescto.setText(datAMB[5]);
            inscimport = new BigDecimal(jLabCoastTarifa.getText());
            inscDto = new BigDecimal(datAMB[5]);
            auxBig = func.getDifference(inscimport, func.percentage(inscimport, inscDto));
            jLabImportInscripc.setText(auxBig.toString());
            
            txtTotalCarg.setText("0.00");//par que no agregue el cobro de inscripcion uaq este desmarcado
          }else if(datAMB[4].equals("1")){// 1 = Renta
              jButton40.setVisible(false);
              jButton41.doClick();
          }

 /*         for (int i = 0; i < datAMB.length; i++) {
              System.err.print("["+datAMB[i]+"]");
          }
          System.out.println("");
            */
          }//@endmostrarJpanCargadores

      
    //metodo para llenar combo de areas
        private void llenaCBRubrosOths() {
            Connection cn = con2.conexion();
            contenRubOths.clear();
            jCBRubroProd.removeAllItems();
            String consul = "SELECT id, nombre from otros_rubros ORDER BY nombre";
            Statement st = null;
            ResultSet rs = null;
            try {
                st = cn.createStatement();
                rs = st.executeQuery(consul);
                while (rs.next()) {
                    contenRubOths.add(rs.getString(1));
                    jCBRubroProd.addItem(rs.getString(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (cn != null) {
                        cn.close();
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
         //  return conten;
    }//Llena llenaCBRubrosOths
      
        public void inhabilitaAreas(){
            jChecMantSem.setSelected(false);
            jChecBasura.setSelected(false);
            jChecPolicia.setSelected(false);
            jChecResguardo.setSelected(false);
	jButMantenSubstract.setEnabled(false);
	jButMantenMoore.setEnabled(false);
	jButBasuraSubstract.setEnabled(false);
	jButBasuraMoore.setEnabled(false);
	jButPolicSubstract.setEnabled(false);
	jButPolicMoore.setEnabled(false);
	jButResguardSubstarct.setEnabled(false);
	jButResguardMoore.setEnabled(false);            
        txtResultSum.setText("0.00");
        
 
        jLabLastMantenim.setText("--");
        jLabLastBasura.setText("--");
        jLabLastPolicia.setText("--");
        jLabLastResguardo.setText("--");
        
               jCmBxgetAreas.requestFocus(true);
               
        String[][] mat = contrl.matrizgetTicketsDia("",infoUser[6]);
        DefaultTableModel modelo = new TModel(mat, cabAreasPays);
        jTabviewPays.setModel(modelo);
        TableRowSorter<TableModel> ordenon = new TableRowSorter<TableModel>(modelo);
        jTabviewPays.setRowSorter(ordenon);
     
    // JOptionPane.showMessageDialog(null, "Esla primera llenada");
     tamColViwePays();//para ajustar el tamaño de las columnas de vista tickets dia
        }
        
        public void inhabilitaAmbus(){//limpiar todod despues de guardar
            jCheckSemPaysAmb.setSelected(false);
            jCheckResguardAmb.setSelected(false);
            jCheckInscripPaysAmb.setSelected(false);
            
            txtIdSemOcultoAmb.setText("");
            txtIdResguardOcultoAmb.setText("");
            
            jButMinusSemsPaysAmb.setEnabled(false);
            jButMostSemsPaysAmb.setEnabled(false);
            jButMinusResgPaysAmb.setEnabled(false);
            jButMinusResgSemfin.setEnabled(false);
            jButMostSemsPaysAmb1.setEnabled(false);
            jButMostSemsPaysAmb2.setEnabled(false);
            
            jCBoxResguardosOpc.setEnabled(false);
            jCBoxDuracInscripc.setEnabled(false);
            jDateChoInscripcion.setEnabled(false);
            
        txtResultAmbu.setText("0.00");
        
        jLabIdAmbu.setText("--");
        jLabNomAmb.setText("--");
        jLabDirecc1.setText("--");
        jLabGiro1.setText("--");
        jLabObserva1.setText("--");
        jPanTableBusqView.setVisible(false);
        
        txtBuscAmbulante.requestFocus(true);
        
         String[][] mat = contrl.matrizgetTicketsDia("",infoUser[6]);
        DefaultTableModel modelo = new TModel(mat, cabAreasPays);
        jTabviewPays.setModel(modelo);
        TableRowSorter<TableModel> ordenon = new TableRowSorter<TableModel>(modelo);
        jTabviewPays.setRowSorter(ordenon);           
//         jTabviewPays.setModel(new TModel(mat, cabAreasPays));
         tamColViwePays();
//        llenacombogetAreas();
        }

        void inicaComboAmbu(){
           jCBoxResguardosOpc.removeAllItems();
            matResgVehiculo = func.getResgVehiculo();//obtenemos las cuotas de cada veiculo para ambulante
            for (int i = 0; i < matResgVehiculo.length; i++) {
                for (int j = 0; j < matResgVehiculo[0].length; j++) {
                    if(j==1){
                        jCBoxResguardosOpc.addItem(matResgVehiculo[i][j]);
                    }
            //        System.out.print("["+matResgVehiculo[i][j]+"]");
                }//foyCol
              //  System.out.println("");  
            }//forFil

            jCBoxResguardosOpc.setSelectedIndex(0);
            jLabTarifaResguard.setText(matResgVehiculo[0][2]);
        }

        protected void cobraAreas(){
                String[] arr = new String[7]; 
                int isUltimTick = func.getUltimPagoarea(),//Se aumenta porq la columna no es increment
                    //       isTurnoUlt = func.getenTurno(), *
                        contadorGuard =0;
                  String fech = datCtrl.setDateActual(),
                           ora = datCtrl.getHour(),
                           total = txtResultSum.getText(),
                           idAr = conten.get(jCmBxgetAreas.getSelectedIndex()),
                          efectiv = txtEfect.getText();
                  arr[0] = Integer.toString(isUltimTick+1);
                  arr[1] = infoUser[6];//Integer.toString(isTurnoUlt); *
                  arr[2] = fech;
                  arr[3] = ora;
                  arr[4] = idAr;
                  arr[5] = total;
                  arr[6] = efectiv;
               contrl.guardaTicketArea(arr);//despues de guardar hay que refrescar todo y mostar el ultimo ticket

         if(jChecMantSem.isSelected())
                 contadorGuard+= Integer.parseInt(jLabSemsPaysManten.getText());
          if(jChecBasura.isSelected())
                contadorGuard+= Integer.parseInt(jLabSemsPaysBasura.getText());
        if(jChecPolicia.isSelected())
                contadorGuard+= Integer.parseInt(jLabSemsPaysPolis.getText());
         if(jChecResguardo.isSelected())
                contadorGuard+= Integer.parseInt(jLabSemsPaysResg.getText());     

         int j=1;//incializar contador para igualar al contador de arriba    
         do {

            if(jChecMantSem.isSelected()){
                String[] guard = new String[5];
                int idS = Integer.parseInt(txtMantenIdSem.getText());
                int numVM = Integer.parseInt(jLabSemsPaysManten.getText());//#Semanas a pagar
                int i=1;
                do{
                guard[0] = arr[0];
                guard[1] = Integer.toString(j);
                guard[2] = Integer.toString(idS+1);
                guard[3] = "2";
                guard[4] = jLabTarifaMantenim.getText();
        contrl.guardadetailTicketArea(guard); 
               // System.out.println("j = "+j);
                i++;
                j++;
                idS++;
                }while(i <= numVM);
            }
            if(jChecBasura.isSelected()){
                int iB = 1;
                String[] guardB = new String[5];
                int idSB = Integer.parseInt(txtBasurIdSem.getText());
                int numVB = Integer.parseInt(jLabSemsPaysBasura.getText());
                do{
                    guardB[0] = arr[0];
                    guardB[1] = Integer.toString(j);
                    guardB[2] = Integer.toString(idSB+1);
                    guardB[3] = "3";
                    guardB[4] = jLabTarifaBasura.getText();
        contrl.guardadetailTicketArea(guardB); 
                    //System.out.println("j = "+j);
                    iB++;
                    j++;
                    idSB++;
                }while(iB <= numVB);
            }
            if(jChecPolicia.isSelected()){
                int iP = 1;
                String[] guardP = new String[5];
                int idSP = Integer.parseInt(txtPoliciaIdSem.getText());
                int numVP = Integer.parseInt(jLabSemsPaysPolis.getText());
                do{
                    guardP[0] = arr[0];
                    guardP[1] = Integer.toString(j);
                    guardP[2] = Integer.toString(idSP+1);
                    guardP[3] = "4";
                    guardP[4] = jLabTarifPolicia.getText();
        contrl.guardadetailTicketArea(guardP);
                    //System.out.println("j = "+j);
                    iP++;
                    idSP++;
                    j++;
                }while(iP <= numVP);
            }
            if(jChecResguardo.isSelected()){
                int iR = 1;
                String[] guardR = new String[5];
                int idSR = Integer.parseInt(txtResguardIdSem.getText());
                int numVR = Integer.parseInt(jLabSemsPaysResg.getText());
                do{
                    guardR[0] = arr[0];
                    guardR[1] = Integer.toString(j);
                    guardR[2] = Integer.toString(idSR+1);
                    guardR[3] = "5";
                    guardR[4] = jLabTarifResguard.getText();
        contrl.guardadetailTicketArea(guardR);
                    //System.out.println("j = "+j);
                    iR++;
                    idSR++;
                    j++;
                }while(iR <= numVR);
            }
           }while( j <= contadorGuard);

        String[][] mat = contrl.matrizgetTicketsDia("",infoUser[6]);//solo regresara lo registrado durante ese turno
        DefaultTableModel modelo = new TModel(mat, cabAreasPays);
        jTabviewPays.setModel(modelo);
        TableRowSorter<TableModel> ordenon = new TableRowSorter<TableModel>(modelo);
        jTabviewPays.setRowSorter(ordenon);        
//            jTabviewPays.setModel(new TModel(mat, cabAreasPays));    

            tamColViwePays();
            String[] dat = rP.getUltimPagoarea(arr[0]);
            rP.imprim80MM(arr[0], dat,true);   
            inhabilitaAreas();
        }//@endCobroArea
        
        protected void cobraAmbulantes(){
                String[] arr = new String[7]; 
                int isUltimTick = func.getUltimPagoambus(),//Se aumenta porq la columna no es increment
                           //isTurnoUlt = func.getenTurno(), *
                        contadorGuard =0;
                  String fech = datCtrl.setDateActual(),
                           ora = datCtrl.getHour(),
                           total = txtTotCobro.getText(),
                           idAmbu = jLabIdAmbu.getText(),
                          efectiv = txtEfect.getText();
                  arr[0] = Integer.toString(isUltimTick+1);
                  arr[1] = infoUser[6];//Integer.toString(isTurnoUlt); *
                  arr[2] = fech;
                  arr[3] = ora;
                  arr[4] = idAmbu;
                  arr[5] = total;
                  arr[6] = efectiv;
              contrl.guardaTicketAmbus(arr);//despues de guardar hay que refrescar todo y mostar el ultimo ticket

         if(jCheckSemPaysAmb.isSelected())
                 contadorGuard+= Integer.parseInt(jLabContadorSemanas.getText());
          if(jCheckResguardAmb.isSelected())
                contadorGuard+= Integer.parseInt(jLabContSemsResguard.getText());
        if(jCheckInscripPaysAmb.isSelected())
                contadorGuard+= 1;
         int j=1;//incializar contador para igualar al contador de arriba    
         do {
            if(jCheckSemPaysAmb.isSelected()){
                String dcto = jLabDstoSemanas.getText(),
                        tarif = jLabTarifaSemanas.getText();
                String[] guard = new String[6];
                int idS = Integer.parseInt(txtIdSemOcultoAmb.getText());
                int numVM = Integer.parseInt(jLabContadorSemanas.getText());//#Semanas a pagar
                int i=1;
                do{
                guard[0] = arr[0];//idTicketguardado pagos_amb
                guard[1] = Integer.toString(j);//item
                guard[2] = "6";//idRubroPago
                guard[3] = Integer.toString(idS+1);//rubrospago.id = Semana Ambulantes
                guard[4] = jLabTarifaSemanas.getText();
                guard[5] = func.percentage(new BigDecimal(tarif),new BigDecimal(dcto)).toString();
        contrl.guardadetailTicketAmbus(guard,guard.length); 
                //System.out.println("Semana j = "+j);
                i++;
                j++;
                idS++;
                }while(i <= numVM);
          contrl.f5idResgAmbu(idAmbu, guard[3], "ultimaSem");
            }
            if(jCheckResguardAmb.isSelected()){
                 String dctoResg = jLabDstoResguard.getText(),
                        tarifResg = jLabTarifaResguard.getText();
                int iB = 1;
                String[] guardB = new String[6];
              //  int idSB = Integer.parseInt(txtBasurIdSem.getText());
                int numVB = Integer.parseInt(jLabContSemsResguard.getText());
                do{
                    guardB[0] = arr[0];
                    guardB[1] = Integer.toString(j);
                    guardB[2] = "7";
                    guardB[3] = Integer.toString(idResguardAmbu);
                    guardB[4] = jLabTarifaResguard.getText();
                    guardB[5] = func.percentage(new BigDecimal(tarifResg),new BigDecimal(dctoResg)).toString();
        contrl.guardadetailTicketAmbus(guardB,guardB.length); 
                    //System.out.println("Resguardo j = "+j);
                    iB++;
                    j++;
                    //idSB++;
                    idResguardAmbu++;
                }while(iB <= numVB);
                contrl.f5idResgAmbu(idAmbu,matResgVehiculo[jCBoxResguardosOpc.getSelectedIndex()][0],"idResg");
            }
            if(jCheckInscripPaysAmb.isSelected()){
                
                int eligio = jCBoxDuracInscripc.getSelectedIndex()+8;
                String[] guardP = new String[8];
                String dctoIns = jLabDstoInscripcion.getText(),
                        tarifIns = jLabTarifaInscripcion.getText();
                    guardP[0] = arr[0];
                    guardP[1] = Integer.toString(j);
                    guardP[2] = Integer.toString(eligio);//rubrospago.id: Anual Semestral Trimestral 8,9,10
                    guardP[3] = "1";
                    guardP[4] = jLabTarifaInscripcion.getText();
                    guardP[5] = func.percentage(new BigDecimal(tarifIns),new BigDecimal(dctoIns)).toString();
                    guardP[6] = datCtrl.getFecha(jDateChoInscripcion);
                    if(eligio == 8)
                        guardP[7] = datCtrl.getsumaFecha(jDateChoInscripcion,12);
      
                    if(eligio == 9)
                        guardP[7] = datCtrl.getsumaFecha(jDateChoInscripcion,6);
                    if(eligio == 10)
                        guardP[7] = datCtrl.getsumaFecha(jDateChoInscripcion,3);
                 contrl.guardadetailTicketAmbus(guardP,guardP.length);
                //    System.out.println("Inscrip j = "+j);
                contrl.f5idResgAmbu(idAmbu,guardP[7],"vigMembresia");
                    j++;
            }
           }while( j <= contadorGuard);
         
        String[][] mat = contrl.matrizgetTicketsDia("",infoUser[6]);
        DefaultTableModel modelo = new TModel(mat, cabAreasPays);
        jTabviewPays.setModel(modelo);
        TableRowSorter<TableModel> ordenon = new TableRowSorter<TableModel>(modelo);
        jTabviewPays.setRowSorter(ordenon);            
//            jTabviewPays.setModel(new TModel(mat, cabAreasPays));

            tamColViwePays();
            String[] dat = rP.getTickPagoAmbu(arr[0]);
            rP.imprim80MMAmbus(arr[0], dat,true);   
             inhabilitaAmbus();
    }//@endCobroAmbulantes
        
        protected void cobraCargadores(){
                String[] arr = new String[7]; 
                BigDecimal coast,
                            dscto,
                            totalimport;
                int isUltimTick = func.getUltimPagoCargad(),//Se aumenta porq la columna no es increment
                           //isTurnoUlt = func.getenTurno(),
                        contadorGuard =0;
                  String fech = datCtrl.setDateActual(),
                           ora = datCtrl.getHour(),
                           total = txtTotCobro.getText(),
                           idAmbu = jlabIdCargador.getText(),
                          efectiv = txtEfect.getText(),
                          aux="";
                  arr[0] = Integer.toString(isUltimTick+1);
                  arr[1] = infoUser[6];//Integer.toString(isTurnoUlt);
                  arr[2] = fech;
                  arr[3] = ora;
                  arr[4] = idAmbu;
                  arr[5] = total;
                  arr[6] = efectiv;
   contrl.guardaTicketCargad(arr);//despues de guardar hay que refrescar todo y mostar el ultimo ticket
   
         if(jCkBoxSemanas.isSelected())
                 contadorGuard+= Integer.parseInt(jLabcontSemanas.getText());
        if(jCkBoxInscripcion.isSelected())
                contadorGuard+= 1;
         
         int j=1;//incializar contador para igualar al contador de arriba    
         do {
            if(jCkBoxSemanas.isSelected()){
                String dcto = jLabDtoSemanas.getText(),
                        tarif = jLabtarifSemanas.getText(),
                        dtofinal="";
                coast = new BigDecimal(tarif);
                dscto = new BigDecimal(dcto);
aux = func.percentage(coast, dscto).toString();
//System.err.println("el Dcto final:"+aux);
                String[] guard = new String[6];
                int idS = Integer.parseInt(txtOcultiDSemana.getText());
                int numVM = Integer.parseInt(jLabcontSemanas.getText());//#Semanas a pagar
                int i=1;
                do{
                guard[0] = arr[0];//idTicketguardado pagos_amb
                guard[1] = Integer.toString(j);//item
                guard[2] = "11";//idRubroPago
                guard[3] = Integer.toString(idS+1);//rubrospago.id = Semana Ambulantes
                guard[4] = tarif;
                guard[5] =aux;
        contrl.guardadetailTicketCargad(guard,guard.length); 
                //System.out.println("Semana j = "+j);
                i++;
                j++;
                idS++;
                }while(i <= numVM);
    contrl.f5postGuardCarg(idAmbu,Integer.toString(idS),"ultimaSem");
            }
            
            if(jCkBoxInscripcion.isSelected()){
                int eligio = jComBInscripc.getSelectedIndex()+8;
                String tarifInCarg = jLabCoastTarifa.getText(),//jLabCoastTarifa
                        dtoCrag = jLabDescto.getText();
                coast = new BigDecimal(tarifInCarg);
                dscto = new BigDecimal(dtoCrag);
                aux = func.percentage(coast, dscto).toString();
                String[] guardP = new String[8];
                    guardP[0] = arr[0];
                    guardP[1] = Integer.toString(j);
                    guardP[2] = Integer.toString(eligio);//rubrospago.id: Anual Semestral Trimestral 8,9,10
                    guardP[3] = "1";//idSemana siempre uno para este caso
                    guardP[4] = tarifInCarg;
                    guardP[5] = aux;
                    
                    guardP[6] = datCtrl.getFecha(jDatChoIncripcion);
                    if(eligio == 8)
                        guardP[7] = datCtrl.getsumaFecha(jDatChoIncripcion,12);
      
                    if(eligio == 9)
                        guardP[7] = datCtrl.getsumaFecha(jDatChoIncripcion,6);

                    if(eligio == 10)
                        guardP[7] = datCtrl.getsumaFecha(jDatChoIncripcion,3);
                    
                 contrl.guardadetailTicketCargad(guardP,guardP.length);
                //    System.out.println("Inscrip j = "+j);
                 contrl.f5postGuardCarg(idAmbu,guardP[7],"vigMembresia");
                    j++;
           }
}while( j <= contadorGuard);//<- while para controlar el numero total de inserciones

        String[][] mat = contrl.matrizgetTicketsDia("",infoUser[6]);
        DefaultTableModel modelo = new TModel(mat, cabAreasPays);
        jTabviewPays.setModel(modelo);
        TableRowSorter<TableModel> ordenon = new TableRowSorter<TableModel>(modelo);
        jTabviewPays.setRowSorter(ordenon);            
//             jTabviewPays.setModel(new TModel(mat, cabAreasPays));        

        tamColViwePays();

        String[] dat = rP.getTickPagoCargad(arr[0]);
        rP.imprim80MMCargad(arr[0], dat,true);
           limpiPreDatCargad();
           txtBuscCargadores.requestFocus(true);
    }//@endCobroCargadores
        
        
protected void cobraOtrosPays(){
          String[] arr = new String[7]; 
          BigDecimal coast,
                    dscto,
                    totalimport;
          int isUltimTick = func.getUltimPagootros_venta(),//Se aumenta porq la columna no es increment
                    //isTurnoUlt = func.getenTurno(), *
                    contadorGuard =0;
          String fech = datCtrl.setDateActual(),
                    ora = datCtrl.getHour(),
                    total = txtTotCobro.getText(),
                    idAmbu = jLabFoli.getText(),
                    efectiv = txtEfect.getText(),
                    aux="",
                    tipoEs = jLabThisIs.getText();
                  
           if(tipoEs.equals("Cliente"))
                   aux = "2";
           if(tipoEs.equals("Ambulante"))
                   aux = "0";
           if(tipoEs.equals("Cargador"))
                  aux = "1";
                  
           arr[0] = Integer.toString(isUltimTick+1);
           arr[1] = infoUser[6];//Integer.toString(isTurnoUlt); *
           arr[2] = fech;
           arr[3] = ora;
           arr[4] = aux;
           arr[5] = idAmbu;
           arr[6] = total;
   contrl.guardaTicketOtherPays(arr);//despues de guardar hay que refrescar todo y mostar el ultimo ticket
 
   int filas = jTabVistaVentaOthers.getRowCount(),
                column = jTabVistaVentaOthers.getColumnCount();
        List<String> datos = new ArrayList<String>();
        
        if (filas > 0) {
            for (int i = 0; i < filas; i++) {
                datos.add(arr[0]);
                for (int j = 0; j < column; j++) {
                    if (j == 0) {
                        datos.add(jTabVistaVentaOthers.getValueAt(i, j).toString());
                        datos.add(Integer.toString(i));
                    }
                    if (j == 1) {
                        datos.add(jTabVistaVentaOthers.getValueAt(i, j).toString());
                    }
                    if (j == 3) {
                        datos.add(jTabVistaVentaOthers.getValueAt(i, j).toString());
                    }
                }
                contrl.guardadetailOthsPays(datos);
                datos.clear();
            }
        }
          
          String[][] mat = contrl.matrizgetTicketsDia("",infoUser[6]);
          DefaultTableModel modelo = new TModel(mat, cabAreasPays);
        jTabviewPays.setModel(modelo);
        TableRowSorter<TableModel> ordenon = new TableRowSorter<TableModel>(modelo);
        jTabviewPays.setRowSorter(ordenon);
        
//          jTabviewPays.setModel(new TModel(mat, cabAreasPays)); 
          
          String[] dat = rP.getTickOthers(arr[0]);
          rP.imprim80MMOthers(arr[0],dat,true);
          tamColViwePays();
           limpiaOthsPays();
    }//@end cobroOthers
        
//cobra renta de diablito cargadores
protected void cobraRentCarg(){
          String[] arr = new String[9]; 
          BigDecimal coast,
                    dscto
                  ;
          int isUltimTick = func.getUltimPagoCarg_Rent(),//Se aumenta porq la columna no es increment
                    //isTurnoUlt = func.getenTurno(), *
                    contadorGuard =Integer.parseInt(txtnumDaysrentdiab.getText()),
                  auxIdTic = 1
                  ;
         String fech = datCtrl.setDateActual(),
                    ora = datCtrl.getHour(),
                    total = txtTotCobro.getText(),
                    idCarg = jlabIdCargador.getText(),
                    efectiv = txtEfect.getText(),
                    tarif=jLabTarifRentCarg.getText(),
                 dscnto = jLabCondonacRentDiab.getText(),
                 startDat = datCtrl.volteaFecha(dCCFechIniRentCarg.getText(),0),
                 numDiab = txtNumDiabRent.getText(),
                  percnt = "",auxFech =""
                 ;
              coast = new BigDecimal(tarif);
              dscto = new BigDecimal(dscnto);
              percnt = func.getDifference(coast, func.percentage(coast, dscto)).toString();
           
           arr[0] = Integer.toString(isUltimTick+1);
           arr[1] = infoUser[6];//Integer.toString(isTurnoUlt); *
           arr[2] = fech;
           arr[3] = ora;
           arr[4] = idCarg;
           arr[5] = "12";
           arr[6] = startDat;
           arr[7] = numDiab;
           arr[8] = percnt;
           
           for(int i = 1;i <= contadorGuard; i++){
                contrl.guardTickRentCarg(arr);//despues de guardar hay que refrescar todo y mostar el ultimo ticket
                auxFech = arr[6];auxIdTic=Integer.parseInt(arr[0]);
                arr[0] = Integer.toString(auxIdTic+1);
                arr[6] = datCtrl.getSumFechDay(auxFech,1);
                
                 rP.imprim80MM_CargRent(Integer.toString(auxIdTic),true);
           }
           comoNewRentCarg();
          String[][] mat = contrl.matrizgetTicketsDia("",infoUser[6]);
          //jTabviewPays.setModel(new TModel(mat, cabAreasPays));  
          DefaultTableModel modelo = new TModel(mat, cabAreasPays);
          jTabviewPays.setModel(modelo);
          TableRowSorter<TableModel> ordenon = new TableRowSorter<TableModel>(modelo);
        jTabviewPays.setRowSorter(ordenon);
          
          tamColViwePays();
         
    }//@end cobraRentCarg

        public void limpiPreDatCargad(){
                    jLabnumSem.setText("N/A");
	jCkBoxSemanas.setSelected(false);
	jLabcontSemanas.setText("0");
	jLabtarifSemanas.setText("0.00");
	jLabDtoSemanas.setText("0.00");
	jLabImportSemanasCargad.setText("0.00");
	txtcontSemsIni.setText("1");
	jLabIniSemsViews.setText("--");
	jButMinusSems.setEnabled(false);
	txtcontSemsFin.setText("1");
	jLabFinSemsViews.setText("--");
	jButMooreSems.setEnabled(false);
        
                    jCkBoxInscripcion.setSelected(false);
	jLabVigencia.setText("N/A");
	jLabCoastTarifa.setText("0.00");
	jLabDescto.setText("0.00");
	jLabImportInscripc.setText("0.00");
	jDatChoIncripcion.setEnabled(false);
	jComBInscripc.setEnabled(false);
                    txtTotalCarg.setText("0.00");
                    
                   
        }
        
        public void limpiaOthsPays(){
            limpiaConcept();
            jButton48.doClick();
            txtTotOthers.setText("0.00");
            jPanDataambView3.setVisible(true);
            jPanTableBusqView2.setVisible(false);
            jLabFoli.setText("");
            jLabNombre3.setText("");
            jLabDirecc3.setText("");
            jLabObserva3.setText("");
            jLabThisIs.setText("");
        }
        
//CODE PARA INFRACCIONES
      void mostrarTabInfrac(String atributo, String var){
        int band = 0;
        Connection cn = con2.conexion();
        
        DefaultTableModel modelo = new DefaultTableModel()
        { 
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        String consul="";
            if(atributo.equals("folio") || atributo.equals("")){
consul = "SELECT folio, date_format(fecha,'%d - %m - %Y') AS fech,documento,vehiculo,monto FROM central.pagos_infrac WHERE folio LIKE '"+var+"%' ORDER BY fecha DESC LIMIT 30;";
            }else{
               if(atributo.equals("placa")){
consul = "SELECT folio, date_format(fecha,'%d - %m - %Y') AS fech,documento,vehiculo,monto FROM central.pagos_infrac WHERE documento LIKE '"+var+"%' ORDER BY fecha DESC LIMIT 30;";
               }
         }
        modelo.addColumn("Folio");
        modelo.addColumn("Fecha");
        modelo.addColumn("Placa/Licencia");    
        modelo.addColumn("Vehiculo");    
        modelo.addColumn("Importe");    
      jTabInfraccView.setModel(modelo);
    //    TableColumnModel columnModel = jTabInfraccView.getColumnModel();
       // columnModel.getColumn(0).setPreferredWidth(10);
       // columnModel.getColumn(1).setPreferredWidth(50);
     //   jTabInfraccView.getColumnModel().getColumn(0).setMaxWidth(0);
       // jTabInfraccView.getColumnModel().getColumn(0).setMinWidth(0);
       // jTabInfraccView.getColumnModel().getColumn(0).setPreferredWidth(0);

        String datos[] =  new String[5];//tenia 4
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(consul);
            while(rs.next()){
                datos[0] =rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                modelo.addRow(datos);
            jTabInfraccView.setModel(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
//             System.out.println("cierra conexion a la base de datos");    
             try {
                    if(rs != null) rs.close();              
                    if(st != null) st.close();                
                    if(cn !=null) cn.close();
             } catch (SQLException ex) {
             }
         }
    }    
      
      //limpiar campos de pagoInfraccion
      public void limpiPayInfrac(){
          txtNamePagad.setText("");
	txtImporte.setText("0.00");
	txtDcto.setText("0.00");
	txtTotal.setText("0.00");
	txtEfectivo.setText("0.00");
	txtCambioInfrac.setText("0.00");
	jLabFolio.setText("--");
	jLabPlaca.setText("--");
      }
      
      //limpiar campos de concepto otro ventas
      public void limpiaConcept(){
          jLabConceptName.setText("N/A");
          txtIdOculto.setText("");
          txtCantidadOths.setText("");
          txtPreciooths.setText("");
          txtImportOths.setText("");
      }
      
      public void limpiaRegClient(){
          txtNombClient.setText("");
          txtDirClient.setText("");
          txtCorrClient.setText("");
          txtObsClient.setText("");
          txtTelClient.setText("");
          txtRfcClient.setText("");
      }
      
      public void limpiaguardGasto(){
          jCombBTypeRubros.setSelectedIndex(0);
          txtConcept.setText("");
          txtsolict.setText("");	
          txtObservs.setText("");
          txtMontoGasto.setText("");
      }
      
      public void comoNewRentCarg(){
          Calendar cal  = Calendar.getInstance();
          String[] coastRentCarg = func.getTarifRentCargad(); //datos costo renta de cargador
         cal.setTime(datCtrl.cargafecha());
         dCCFechIniRentCarg.setSelectedDate(cal);
         dCCFechFinRentCarg.setSelectedDate(cal);
          txtnumDaysrentdiab.setText("1");
          txtNumDiabRent.setText("1");
          jLabTarifRentCarg.setText(coastRentCarg[3]);
          jLabCondonacRentDiab.setText("0.00");
          jLabImportRentDiab.setText(coastRentCarg[3]);
          

      }
      
             //funcion para busqueda automatica ambulantes
        void allClisAmbCarg(String var){
        int band = 0;
        Connection cn = con2.conexion();
        DefaultTableModel modelo = new DefaultTableModel()
        { 
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        String consul="";
         //   if(atributo.equals("nombre") || atributo.equals("")){
           consul ="(SELECT clientes.id,clientes.nombre,\"Cliente\" as Iss\n" +
                        "FROM clientes \n" +
                        "WHERE (clientes.id LIKE '"+var+"%'  OR clientes.nombre LIKE '"+var+"%') ORDER BY clientes.id)\n" +
                        "UNION\n" +
                        "(SELECT ambulantes.id,ambulantes.nombre,\"Ambulante\" as Iss \n" +
                        "FROM ambulantes\n" +
                        "WHERE (ambulantes.id LIKE '"+var+"%'  OR ambulantes.nombre LIKE '"+var+"%') ORDER BY ambulantes.id)\n" +
                        "union\n" +
                        "(SELECT cargadores.id,cargadores.nombre,\"Cargador\" as Iss\n" +
                        "FROM cargadores\n" +
                        "WHERE (cargadores.id LIKE '"+var+"%'  OR cargadores.nombre LIKE '"+var+"%') ORDER BY cargadores.id);";   
         //consul = "SELECT id, nombre from ambulantes WHERE id LIKE '"+var+"%'  OR nombre LIKE '"+var+"%' ORDER BY id";
                modelo.addColumn("Número");
                modelo.addColumn("Nombre");
                modelo.addColumn("Tipo");
        jTabVariosView.setModel(modelo);
        TableColumnModel columnModel = jTabVariosView.getColumnModel();
         jTabVariosView.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTabVariosView.getColumnModel().getColumn(0).setMaxWidth(80);
        jTabVariosView.getColumnModel().getColumn(0).setMinWidth(80);
        jTabVariosView.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTabVariosView.getColumnModel().getColumn(2).setMaxWidth(80);
        jTabVariosView.getColumnModel().getColumn(2).setMinWidth(80);
        /*jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        */
        String datos[] =  new String[3];//tenia 4
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(consul);
            while(rs.next()){
                datos[0] =rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                modelo.addRow(datos);
            }
            jTabVariosView.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             try {
                    if(rs != null) rs.close();              
                    if(st != null) st.close();                
                    if(cn !=null) cn.close();
             } catch (SQLException ex) {
             }
         }
    }//@endallClisAmbCarg
        
//funcion para busqueda automatica de otros conceptos
        void getProductsOthers(String var){
        Connection cn = con2.conexion();
        DefaultTableModel modelo = new DefaultTableModel()
        { 
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        String consul="";
           consul ="SELECT otros_catalogo.id, otros_catalogo.descrip, IF(otros_catalogo.activo = 0,\"Inactivo\",\"activo\")\n" +
"AS band FROM otros_catalogo WHERE otros_catalogo.descrip LIKE '"+var+"%' AND otros_catalogo.activo = 1;";   
         
         //consul = "SELECT id, nombre from ambulantes WHERE id LIKE '"+var+"%'  OR nombre LIKE '"+var+"%' ORDER BY id";
                modelo.addColumn("Número");
                modelo.addColumn("Nombre");
                modelo.addColumn("Status");
        
        jTabViewOtherProd.setModel(modelo);
        TableColumnModel columnModel = jTabViewOtherProd.getColumnModel();
        
         jTabViewOtherProd.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTabViewOtherProd.getColumnModel().getColumn(0).setMaxWidth(80);
        jTabViewOtherProd.getColumnModel().getColumn(0).setMinWidth(80);
     
        jTabViewOtherProd.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTabViewOtherProd.getColumnModel().getColumn(2).setMaxWidth(80);
        jTabViewOtherProd.getColumnModel().getColumn(2).setMinWidth(80);

        String datos[] =  new String[3];//tenia 4
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(consul);

            while(rs.next()){
                datos[0] =rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                modelo.addRow(datos);
            }
            jTabViewOtherProd.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             try {
                    if(rs != null) rs.close();              
                    if(st != null) st.close();                
                    if(cn !=null) cn.close();
             } catch (SQLException ex) {
             }
         }
    }//@endgetProductsOthers
        
        //CODE PARA INFRACCIONES
      void mostrarGastosDia(String fech, String idTurn){
        Connection cn = con2.conexion();
        DefaultTableModel modelo = new DefaultTableModel()
        { 
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        String consul="";
        if(idTurn.isEmpty()){
        consul = "SELECT gastos_caja.id,gastos_caja.hora,gastos_caja.concepto,gastos_caja.monto\n" +
                "FROM central.gastos_caja\n" +
                "INNER JOIN central.rubroscaja\n" +
                "ON gastos_caja.idRubrocaja = rubroscaja.id AND gastos_caja.fecha = '"+fech+"' ;";
        }else{
        consul = "SELECT gastos_caja.id,gastos_caja.hora,gastos_caja.concepto,gastos_caja.monto\n" +
                "FROM central.gastos_caja\n" +
                "INNER JOIN central.rubroscaja\n" +
                "ON gastos_caja.idRubrocaja = rubroscaja.id AND gastos_caja.idTurno = '"+idTurn+"';";            
        }
            modelo.addColumn("Folio");
        modelo.addColumn("Hora");
        modelo.addColumn("Concepto");    
        modelo.addColumn("Importe");    
        jTabViewGastos.setModel(modelo);
        jTabViewGastos.getColumnModel().getColumn(0).setMaxWidth(60);
        jTabViewGastos.getColumnModel().getColumn(0).setMinWidth(60);
        jTabViewGastos.getColumnModel().getColumn(0).setPreferredWidth(60);
       
        jTabViewGastos.getColumnModel().getColumn(1).setMaxWidth(85);
        jTabViewGastos.getColumnModel().getColumn(1).setMinWidth(85);
        jTabViewGastos.getColumnModel().getColumn(1).setPreferredWidth(85);
       
        jTabViewGastos.getColumnModel().getColumn(3).setMaxWidth(60);
        jTabViewGastos.getColumnModel().getColumn(3).setMinWidth(60);
       jTabViewGastos.getColumnModel().getColumn(3).setPreferredWidth(60);

        String datos[] =  new String[4];//tenia 4
        Statement st = null;
        ResultSet rs = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(consul);
            while(rs.next()){
                datos[0] =rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            jTabViewGastos.setModel(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(internoCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             try {
                    if(rs != null) rs.close();              
                    if(st != null) st.close();                
                    if(cn !=null) cn.close();
             } catch (SQLException ex) {
             }
         }
    }    //@end mostrarGastosDia
      
      public void tamColViwePays(){
        jTabviewPays.getColumnModel().getColumn(0).setMaxWidth(55);
        jTabviewPays.getColumnModel().getColumn(0).setMinWidth(55);
        jTabviewPays.getColumnModel().getColumn(0).setPreferredWidth(55);
       
        jTabviewPays.getColumnModel().getColumn(1).setMaxWidth(60);
        jTabviewPays.getColumnModel().getColumn(1).setMinWidth(60);
        jTabviewPays.getColumnModel().getColumn(1).setPreferredWidth(60);
       
        jTabviewPays.getColumnModel().getColumn(4).setMaxWidth(65);
        jTabviewPays.getColumnModel().getColumn(4).setMinWidth(65);
        jTabviewPays.getColumnModel().getColumn(4).setPreferredWidth(65);
      }
      
      private String sumCorteCaj(String idTurnon){
              String most = "";
              BigDecimal totAreas = new BigDecimal(func.totalturno(0, idTurnon));
              BigDecimal totAmbus = new BigDecimal(func.totalturno(1, idTurnon));
              BigDecimal totCarg= new BigDecimal(func.totalturno(2, idTurnon));
              BigDecimal totCargRent = new BigDecimal(func.totalturno(3, idTurnon));
              BigDecimal totInfrc = new BigDecimal(func.totalturno(4, idTurnon));
              BigDecimal totOthsVenta = new BigDecimal(func.totalturno(5, idTurnon));
return most = func.getSum(totAreas, func.getSum(totAmbus, func.getSum(totCarg, func.getSum(totCargRent, func.getSum(totInfrc, totOthsVenta))))).toString();
          }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public datechooser.beans.DateChooserCombo dCCFechFinRentCarg;
    public datechooser.beans.DateChooserCombo dCCFechIniRentCarg;
    private javax.swing.JButton jButBasuraMoore;
    private javax.swing.JButton jButBasuraSubstract;
    private javax.swing.JButton jButMantenMoore;
    private javax.swing.JButton jButMantenSubstract;
    private javax.swing.JButton jButMinusResgPaysAmb;
    private javax.swing.JButton jButMinusResgSemfin;
    private javax.swing.JButton jButMinusSems;
    private javax.swing.JButton jButMinusSemsPaysAmb;
    private javax.swing.JButton jButMooreSems;
    private javax.swing.JButton jButMostSemsPaysAmb;
    private javax.swing.JButton jButMostSemsPaysAmb1;
    private javax.swing.JButton jButMostSemsPaysAmb2;
    private javax.swing.JButton jButPolicMoore;
    private javax.swing.JButton jButPolicSubstract;
    private javax.swing.JButton jButResguardMoore;
    private javax.swing.JButton jButResguardSubstarct;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jCBBusqTicketall;
    private javax.swing.JComboBox<String> jCBRubroProd;
    private javax.swing.JComboBox<String> jCBoxDuracInscripc;
    private javax.swing.JComboBox<String> jCBoxFilterBusq;
    private javax.swing.JComboBox<String> jCBoxResguardosOpc;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JCheckBox jChecBasura;
    private javax.swing.JCheckBox jChecMantSem;
    private javax.swing.JCheckBox jChecPolicia;
    private javax.swing.JCheckBox jChecResguardo;
    private javax.swing.JCheckBox jCheckInscripPaysAmb;
    private javax.swing.JCheckBox jCheckResguardAmb;
    private javax.swing.JCheckBox jCheckSemPaysAmb;
    private javax.swing.JCheckBox jCkBoxInscripcion;
    private javax.swing.JCheckBox jCkBoxSemanas;
    private javax.swing.JComboBox<String> jCmBxgetAreas;
    private javax.swing.JComboBox<String> jComBInscripc;
    private javax.swing.JComboBox<String> jCombBOpcBusqGastos;
    private javax.swing.JComboBox<String> jCombBTypeRubros;
    private com.toedter.calendar.JDateChooser jDatChoFechBusqtick;
    private com.toedter.calendar.JDateChooser jDatChoIncripcion;
    private com.toedter.calendar.JDateChooser jDateChoGastos;
    private com.toedter.calendar.JDateChooser jDateChoInscripcion;
    private javax.swing.JDialog jDialAltaGastos;
    private javax.swing.JDialog jDialCalendarMantenim;
    private javax.swing.JDialog jDialCancelaciones;
    private javax.swing.JDialog jDialresProdOther;
    private javax.swing.JFrame jFramAltaCliente;
    private javax.swing.JFrame jFramCobroInfrac;
    private javax.swing.JFrame jFramExplanadaCob;
    private javax.swing.JFrame jFramePays;
    private javax.swing.JLabel jLaFechFinBasura;
    private javax.swing.JLabel jLaFechFinManten;
    private javax.swing.JLabel jLaFechFinPolicia;
    private javax.swing.JLabel jLaFechFinResguard;
    private javax.swing.JLabel jLaFechIniBasura;
    private javax.swing.JLabel jLaFechIniManten;
    private javax.swing.JLabel jLaFechIniPolicia;
    private javax.swing.JLabel jLaFechIniResguard;
    private javax.swing.JLabel jLaFechIniResguardos;
    private javax.swing.JLabel jLaFechIniSemana;
    private javax.swing.JLabel jLabAumResguard;
    private javax.swing.JLabel jLabAumentaSemanas;
    private javax.swing.JLabel jLabCoastTarifa;
    private javax.swing.JLabel jLabConceptName;
    private javax.swing.JLabel jLabCondonacRentDiab;
    private javax.swing.JLabel jLabContSemsResguard;
    private javax.swing.JLabel jLabContadorSemanas;
    private javax.swing.JLabel jLabDescto;
    private javax.swing.JLabel jLabDirecCargad;
    private javax.swing.JLabel jLabDirecc1;
    private javax.swing.JTextArea jLabDirecc3;
    private javax.swing.JLabel jLabDstoInscripcion;
    private javax.swing.JLabel jLabDstoResguard;
    private javax.swing.JLabel jLabDstoSemanas;
    private javax.swing.JLabel jLabDtoSemanas;
    private javax.swing.JLabel jLabFinSemsViews;
    private javax.swing.JLabel jLabFoli;
    private javax.swing.JLabel jLabFolio;
    private javax.swing.JLabel jLabGiro1;
    private javax.swing.JLabel jLabGiroCarg;
    private javax.swing.JLabel jLabIdAmbu;
    private javax.swing.JLabel jLabImportInscripc;
    private javax.swing.JLabel jLabImportRentDiab;
    private javax.swing.JLabel jLabImportSemanasCargad;
    private javax.swing.JLabel jLabImporteInscripcion;
    private javax.swing.JLabel jLabImporteResguard;
    private javax.swing.JLabel jLabImporteSemanas;
    private javax.swing.JLabel jLabIniSemsViews;
    private javax.swing.JLabel jLabLastBasura;
    private javax.swing.JLabel jLabLastMantenim;
    private javax.swing.JLabel jLabLastPolicia;
    private javax.swing.JLabel jLabLastResguardo;
    private javax.swing.JLabel jLabNomAmb;
    private javax.swing.JLabel jLabNombre3;
    private javax.swing.JLabel jLabNombreCargadores;
    private javax.swing.JLabel jLabObserva1;
    private javax.swing.JLabel jLabObserva3;
    private javax.swing.JTextArea jLabObservaCarg;
    private javax.swing.JLabel jLabPlaca;
    private javax.swing.JLabel jLabSemsPaysBasura;
    private javax.swing.JLabel jLabSemsPaysManten;
    private javax.swing.JLabel jLabSemsPaysPolis;
    private javax.swing.JLabel jLabSemsPaysResg;
    private javax.swing.JLabel jLabTarifPolicia;
    private javax.swing.JLabel jLabTarifRentCarg;
    private javax.swing.JLabel jLabTarifResguard;
    private javax.swing.JLabel jLabTarifaBasura;
    private javax.swing.JLabel jLabTarifaInscripcion;
    private javax.swing.JLabel jLabTarifaMantenim;
    private javax.swing.JLabel jLabTarifaResguard;
    private javax.swing.JLabel jLabTarifaSemanas;
    private javax.swing.JLabel jLabThisIs;
    private javax.swing.JLabel jLabUltimaResguardPay;
    private javax.swing.JLabel jLabUltimaSemanaPay;
    private javax.swing.JLabel jLabVigencia;
    private javax.swing.JLabel jLabVigenciaView;
    private javax.swing.JLabel jLabcontSemanas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabnumSem;
    private javax.swing.JLabel jLabtarifSemanas;
    private javax.swing.JLabel jLabtotInfracc;
    private javax.swing.JLayeredPane jLayeredPanConcept;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPanePerson;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanAreascobros;
    private javax.swing.JPanel jPanBasuraContenPays;
    private javax.swing.JPanel jPanCargadorInscr;
    private javax.swing.JPanel jPanCargadores;
    private javax.swing.JPanel jPanDataambView1;
    private javax.swing.JPanel jPanDataambView2;
    private javax.swing.JPanel jPanDataambView3;
    private javax.swing.JPanel jPanInternGastos;
    private javax.swing.JPanel jPanInterncoborstivk;
    private javax.swing.JPanel jPanMantenPay;
    private javax.swing.JPanel jPanPiliceContenPays;
    private javax.swing.JPanel jPanRentCargador;
    private javax.swing.JPanel jPanResguardContenPays;
    private javax.swing.JPanel jPanSemCargad;
    private javax.swing.JPanel jPanTableBusqView;
    private javax.swing.JPanel jPanTableBusqView1;
    private javax.swing.JPanel jPanTableBusqView2;
    private javax.swing.JPanel jPanTableBusqView3;
    private javax.swing.JPanel jPanTransitoInfraccion;
    private javax.swing.JPanel jPanVarios;
    private javax.swing.JPanel jPanambulantes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanvIEWcOAST;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTabCargadoresView;
    private javax.swing.JTable jTabDatosAmbulante;
    private javax.swing.JTable jTabInfraccView;
    private javax.swing.JTable jTabVariosView;
    private javax.swing.JTable jTabViewGastos;
    private javax.swing.JTable jTabViewOtherProd;
    private javax.swing.JTable jTabVistaVentaOthers;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTabviewPays;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel jlabIdCargador;
    private javax.swing.JLabel jlabImportBasura;
    private javax.swing.JLabel jlabImportMantenim;
    private javax.swing.JLabel jlabImportPolicia;
    private javax.swing.JLabel jlabImportResguard;
    private javax.swing.JTextField txtBasurIdSem;
    private javax.swing.JTextField txtBusGasto;
    private javax.swing.JTextField txtBuscAmbulante;
    private javax.swing.JTextField txtBuscCargadores;
    private javax.swing.JTextField txtBusqConcept;
    private javax.swing.JTextField txtBusqOtros;
    private javax.swing.JTextField txtBusqTickAll;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCambioInfrac;
    private javax.swing.JTextField txtCancelTick;
    private javax.swing.JTextField txtCantidadOths;
    private javax.swing.JTextField txtCoastExplan;
    private javax.swing.JTextField txtConcept;
    private javax.swing.JTextField txtCorrClient;
    private javax.swing.JTextField txtDcto;
    private javax.swing.JTextField txtDescripProd;
    private javax.swing.JTextField txtDirClient;
    private javax.swing.JTextField txtEfect;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtFinBasura;
    private javax.swing.JTextField txtFinManten;
    private javax.swing.JTextField txtFinPolicia;
    private javax.swing.JTextField txtFinResguard;
    private javax.swing.JTextField txtFolFinExplanad;
    private javax.swing.JTextField txtFolIniExplanad;
    private javax.swing.JTextField txtFoltotExplanad;
    private javax.swing.JTextField txtIdOculto;
    private javax.swing.JTextField txtIdResguardOcultoAmb;
    private javax.swing.JTextField txtIdSemOcultoAmb;
    private javax.swing.JTextField txtImportOths;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtInFilterBusq;
    private javax.swing.JTextField txtIniBasura;
    private javax.swing.JTextField txtIniManten;
    private javax.swing.JTextField txtIniPolic;
    private javax.swing.JTextField txtIniResg;
    private javax.swing.JTextField txtMantenIdSem;
    private javax.swing.JTextField txtMontoGasto;
    private javax.swing.JTextField txtNamePagad;
    private javax.swing.JTextField txtNombClient;
    private javax.swing.JTextField txtNumDiabRent;
    private javax.swing.JTextField txtObsClient;
    private javax.swing.JTextField txtObservs;
    private javax.swing.JTextField txtOcultiDSemana;
    private javax.swing.JTextField txtPoliciaIdSem;
    private javax.swing.JTextField txtPrec;
    private javax.swing.JTextField txtPreciooths;
    private javax.swing.JTextField txtResgFinAmb;
    private javax.swing.JTextField txtResgIniAmb;
    private javax.swing.JTextField txtResguardIdSem;
    private javax.swing.JTextField txtResultAmbu;
    private javax.swing.JTextField txtResultSum;
    private javax.swing.JTextField txtRfcClient;
    private javax.swing.JTextField txtSemFinAmb;
    private javax.swing.JTextField txtSeminiAmb;
    private javax.swing.JTextField txtTelClient;
    private javax.swing.JTextField txtTotCobro;
    private javax.swing.JTextField txtTotOthers;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalCarg;
    private javax.swing.JTextField txtcontSemsFin;
    private javax.swing.JTextField txtcontSemsIni;
    private javax.swing.JTextField txtnumDaysrentdiab;
    private javax.swing.JTextField txtsolict;
    // End of variables declaration//GEN-END:variables


}
