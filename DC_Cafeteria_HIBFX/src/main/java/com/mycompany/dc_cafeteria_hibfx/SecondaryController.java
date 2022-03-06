package com.mycompany.dc_cafeteria_hibfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

/**
 * LoadContent carga contenido HTML. En cambio Load carga una página web.
 * @author Doctor32
 */
public class SecondaryController implements Initializable{

    @FXML
    private WebView htmlContent;
    @FXML
    private Label direccion;
    @FXML
    private ImageView btnVolver;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    /**
     * Aquí vamos a cargar la página web
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Esta es la dirección que lleva a un video de youtube
        //En la siguiente línea lo vamos a cargar para que aparezca en la pantalla de ayuda
        //String ur = "<iframe width='560' height='315' src='https://www.youtube.com/embed/jrTMMG0zJyI' title='YouTube video player' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>";
        //htmlContent.getEngine().loadContent(ur);
        //Este es el motor del webView. Con la siguiente línea de código podemos hacer un html.
        //htmlContent.getEngine().loadContent("<h1 style='color:black;background-color:green;padding:2em'>Probando</h1>");
        //Esta es la dirección que cargará el enlace y nos llevará a la documentación de openjfx
        //Mediante el load cargamos una página web
        /*String ul = "https://openjfx.io/javadoc/11/";
        htmlContent.getEngine().load(ul);
        
        //Para enlazar el label con el location mediante la propiedad bind
        direccion.textProperty().bind(htmlContent.getEngine().locationProperty());
        
        //En la siguiente línea le estamos añadiendo un listener
        direccion.textProperty().addListener((var l) -> {
            System.out.println("Define nuevo label");
        });*/
        
        
        //Aquí vamos a cargar un HTML del propio proyecto
        htmlContent.getEngine().load(getClass().getClassLoader().getResource("Help\\help.html").toExternalForm());
        
        
    }
}