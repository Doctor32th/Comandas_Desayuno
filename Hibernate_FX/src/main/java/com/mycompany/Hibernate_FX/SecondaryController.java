package com.mycompany.Hibernate_FX;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Pedido;
import models.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * 
 * @author Doctor32
 */
public class SecondaryController implements Initializable{

    @FXML
    private TableView<Pedido> tabla;
    @FXML
    private TableColumn<Pedido, Long> colId;
    @FXML
    private TableColumn<Pedido, Date> colFecha;
    @FXML
    private TableColumn<Pedido, String> colEstado;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnVer;
    @FXML
    private Button btnRecoger;
    @FXML
    private Button btnAcercaDe;
    @FXML
    private TableColumn<Pedido, String> colProducto;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //En un Observable almacenamos la información de listas y tablas
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
        tabla.setItems(pedidos);
        
        
        //Relación de la variable del controlador con el atributo del modelo.
        //colId.setCellValueFactory(new PropertyValueFactory<>("id_pedido"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        //Conversión de Date de java util a java sql
        java.util.Date hoy = new java.util.Date();
        java.sql.Date fechaDeHoy = new java.sql.Date(hoy.getTime());
        Session s = HibernateUtil.getSessionFactory().openSession();
        
        //Consulta a la base de datos para listar los pedidos pendientes
        Query q = s.createQuery("FROM Pedido p WHERE p.estado = 'pendiente' AND p.fecha =:fecha", Pedido.class);
        q.setParameter("fecha", hoy);
        
        ArrayList<Pedido> listado = (ArrayList<Pedido>) q.list();
        
        pedidos.addAll(listado);
        
        
        //Esto nos permitirá ver la hora actual. Se irá actualizando cada
        //segundo. El código no está completo y falta un label en la vista
        //para mostrarla
        /*Timer tiempo = new Timer();
        TimerTask tarea = new TimerTask(){

            @Override
            public void run() {
                Platform.runLater(() ->{
                });
            }
        };*/

        //Creacion de un pedido
        //pedidos.add(new Pedido(0L, new Date(), "pendiente", new Usuario()));
        
        /**
         * Borrará todo lo observable. Si añadimos algo, cuando volvemos a
         * arrancar el programa, se volverá a añadir lo mismo. El .clear 
         * impide que se repitan los datos.
         */
        //pedidos.clear();
    }

    /**
     * Método para seleccionar elementos sobre la tabla
     * @param event 
     */
    @FXML
    private void seleccionar(MouseEvent event) {
        //Así obtenemos el elemento seleccionado.
        /*Pedido pedido = tabla.getSelectionModel().getSelectedItem();
        if(pedido != null){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            //alerta.setHeaderText(pedido.getFecha());
            alerta.setHeaderText(pedido.getEstado());
            alerta.showAndWait();
            
        }*/
    }


    @FXML
    private void entregado(ActionEvent event) {
        /*Pedido pedido = tabla.getSelectionModel().getSelectedItem();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Pedido p = s.load(Pedido.class, pedido.getId_pedido());
        pedido.setEstado("recogido");
        s.update(p);
        t.commit();*/
    }

    @FXML
    private void masinfo(ActionEvent event) {
        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Comandas Desayuno. By: Luis Robbe Toichoa Sautoho"
                + "(Doctor32)");
        alerta.showAndWait();
    }
    
}