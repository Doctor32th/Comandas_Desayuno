/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Hibernate_FX;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Pedido;
import models.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author Doctor32
 */
public class PrimaryController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Hyperlink enlace;
    @FXML
    private CheckBox mantenerAbierta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Llamamos a la clase HibernateUtil para que inicie un Session
        //Factory
        Session s = HibernateUtil.getSessionFactory().openSession();
        
        //En hibernate se hacen consultas a clase. Usuario es el nombre
        //de su clase y no el de la tabla en la base de datos.
        Query q = s.createQuery("FROM Usuario");
        q.list().forEach((e) -> System.out.println(e));

        //Esta consulta nos muestra todas las tareas
        Query q2 = s.createQuery("FROM Pedido");
        q2.list().forEach((e) -> System.out.println(e));


        Usuario usuario = new Usuario();
        usuario.setCiclo("2 DAW");
        usuario.setNombre("Kanye");
        usuario.setContrasenha("5445");
        

        /*Transaction t = s.beginTransaction();
        s.save(usuario);
        t.commit();

        Pedido pedido = new Pedido();
        pedido.setProducto("Chocolate caliente");
        pedido.setFecha(new Date());
        pedido.setEstado("pendiente");
        pedido.setUsuario((Usuario) q.list().get(0));
        
        Transaction t = s.beginTransaction();
        s.save(pedido);
        t.commit();
        
        //Imprime el pedido en consola
        System.out.println(pedido);
        System.out.println(pedido.getUsuario());*/
        
        
        

    }    

    @FXML
    private void aceptar(ActionEvent event) {
        System.out.println("Botón aceptar");
        System.out.println( txtUsuario.getText() );
        System.out.println( txtPassword.getText() );
        
        /**
         * Hemos hecho una sesión para entrar en la base de datos
         * tras iniciar sesión. Aquí ponemos la consulta en modo HQL.
         * Seguidamente estamos asociando el nombre de la variable de
         * la consulta al nombre del parámetro del evento aceptar
         */
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM Usuario u WHERE u.nombre=:nom AND u.contrasenha=:cont ");
        q.setParameter("nom", txtUsuario.getText());
        q.setParameter("cont", txtPassword.getText());
        
        /**
         * Consulta en SQL SELECT * FROM usuarios LIKE "us" AND contrasenha LIKE
         * "2332"
         */

        /**
         * El q.list devuelve al usuario. Para saber si existe o no
         * tenemos que hacer un if. Si no existe, nos devuelven un valor
         * vacio. En caso de
         * que existe, nos devuelve un elemento. Si devuelve algún elemento, el
         * usuario está registrado. 
         */
        if (q.list().size() > 0) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Aceptar");
            alerta.setHeaderText("Nuevo login");
            alerta.setContentText("El usuario " + txtUsuario.getText()
                    + " con contraseña " + txtPassword.getText() + " ha iniciado sesión.");
            alerta.showAndWait();
            
            
            try {
                App.setRoot("secondary");
            } catch (IOException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Usuario no válido");
            alerta.setContentText("El usuario " + txtUsuario.getText()
                    + " no existe ");
            alerta.showAndWait();
        }     
    }

    @FXML
    private void cancelar(ActionEvent event) {
        System.out.println("Botón cancelar");
    }

    @FXML
    private void recordar(ActionEvent event) {
        System.out.println("Enlace");
    }

    @FXML
    private void mantener(ActionEvent event) {
        System.out.println("Mantener abierta la sesión");
        System.out.println( mantenerAbierta.isSelected() );
    }
    
}
