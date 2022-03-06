package com.mycompany.dc_cafeteria_hibfx;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Pedido;
import models.Producto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * 
 * @author Doctor32
 */
public class PrimaryController implements Initializable {
    
    @FXML
    private Label label1;
    @FXML
    private TableView<Pedido> tabla;
    @FXML
    private TableColumn<Pedido, Long> cIdPedido;
    @FXML
    private TableColumn<Pedido, String> cNombreProducto;
    @FXML
    private TableColumn<Pedido, Integer> cPrecioProducto;
    @FXML
    private TableColumn<Pedido, String> cEstadoPedido;
    @FXML
    private ImageView btnSalir;
    @FXML
    private TableColumn<Pedido, Date> cFechaPedido;
    @FXML
    private Button btnCarta;
    @FXML
    private Button btnPedidos;
    @FXML
    private Tooltip ttLabel;
    @FXML
    private ImageView btnAyuda;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cIdPedido.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        cNombreProducto.setCellValueFactory(
                (var fila) -> {
                    String show = fila.getValue().getProductoCarta().getNombreProducto();
                    return new SimpleStringProperty(show);
                }
        );
        cPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precioPedido"));
        cEstadoPedido.setCellValueFactory(new PropertyValueFactory<>("estadoPedido"));
        cFechaPedido.setCellValueFactory(new PropertyValueFactory<>("fechaPedido"));
        
        /*java.util.Date actual = new java.util.Date();
        java.sql.Date fechaActual = new java.sql.Date(actual.getTime());*/
        //Producto p = new Producto();
        
        ObservableList<Pedido> listado = FXCollections.observableArrayList();
        tabla.setItems(listado);
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM Pedido", Pedido.class);
        //q.setParameter("nombreProducto", p.getNombreProducto());
        //q.setParameter("fecha", fechaActual);
        ArrayList<Pedido> resultado = (ArrayList<Pedido>) q.list();
        label1.setText("Número de pedidos en pantalla: " + resultado.size() + " pedidos");
        
        listado.addAll(resultado);
        System.out.println(resultado.toString());
        
        
        //La siguiente línea nos permite crear un pedido desde código
        /*contenido.add(new Pedido(0l, new Producto("Pan"), 2, "entregado", new Date()));
        contenido.add(new Pedido(1l, new Producto("Chocolate caliente"), 3, "pendiente", new Date()));
        contenido.add(new Pedido(2l, new Producto("Brownie"), 4, "pendiente", new Date()));*/

        
        //Clear limpia la tabla
        //contenido.clear();
        
        
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Pedido pedido = tabla.getSelectionModel().getSelectedItem();
        if(pedido!=null){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(pedido.getEstadoPedido());
            alerta.setContentText("Precio: " + pedido.getPrecioPedido()
                    .toString() + " euros.");
            alerta.showAndWait();
    
        }
    }

    @FXML
    private void generarCartaJasper(ActionEvent event) {
        
        try {
            String carta = "CartaJ.jrxml";

            var indicaciones1 = new HashMap();
            
            JasperReport cartaPDF = JasperCompileManager.compileReport(carta);
            JasperPrint extraer1 = JasperFillManager.fillReport(cartaPDF, indicaciones1, JasperConnection.getConexion());
            
            JRViewer visor = new JRViewer(extraer1);
            javax.swing.JFrame contenidoCartaPDF = new javax.swing.JFrame("Documento1");
            contenidoCartaPDF.getContentPane().add(visor);
            
            contenidoCartaPDF.pack();
            contenidoCartaPDF.setVisible(true);
            
            var sacarPDF1 = new JRPdfExporter();
            sacarPDF1.setExporterInput(new SimpleExporterInput(extraer1));
            
            sacarPDF1.setExporterOutput(new SimpleOutputStreamExporterOutput("CartaJ.pdf"));

            var opciones1 = new SimplePdfExporterConfiguration();
            
            sacarPDF1.setConfiguration(opciones1);
            
            sacarPDF1.exportReport();
            
        } catch (JRException ex) {
            
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void generarPedidosJasper(ActionEvent event) {

        try {
            String pedidos = "PedidosJ.jrxml";
            
            var indicaciones2 = new HashMap();
            
            java.util.Date alMomento = new java.util.Date();
            java.sql.Date fechaActual = new java.sql.Date(alMomento.getTime());
            
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            java.sql.Date fechaActualSinhoras = new java.sql.Date(fechaActual.getTime());
            
            JasperReport pedidosPDF = JasperCompileManager.compileReport(pedidos);
            JasperPrint extraer2 = JasperFillManager.fillReport(pedidosPDF, indicaciones2, JasperConnection.getConexion());
            
            JRViewer visor = new JRViewer(extraer2);
            javax.swing.JFrame contenidoPedidosPDF = new javax.swing.JFrame("Documento2");
            contenidoPedidosPDF.getContentPane().add(visor);
            
            contenidoPedidosPDF.pack();
            contenidoPedidosPDF.setVisible(true);
            
            var sacarPDF2 = new JRPdfExporter();
            sacarPDF2.setExporterInput(new SimpleExporterInput(extraer2));
            
            sacarPDF2.setExporterOutput(new SimpleOutputStreamExporterOutput("PedidosJ.pdf"));
            
            var opciones2 = new SimplePdfExporterConfiguration();
            
            sacarPDF2.setConfiguration(opciones2);
            
            sacarPDF2.exportReport();
            
        } catch (JRException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void salir(MouseEvent event) {
        Alert salir = new Alert(Alert.AlertType.CONFIRMATION);
        salir.setContentText("¿Está seguro de que desea salir?");
        
        var variableSalida = salir.showAndWait();
        
        if((variableSalida.get().getText()).equals("Aceptar")){
            System.out.println("Saliendo...");
        }
        
        System.exit(0);
    }
}
