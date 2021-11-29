/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Hibernate_FX;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Esta clase aloja el SessionFactory, que se usará para todo el proyecto.
 * Lo sacaremos de manera estática.
 * @author Doctor32
 */
public class HibernateUtil {
    
    //Esta es nuestra constante
    private static final SessionFactory sf;
    
    static{
        try{
        sf = new Configuration().configure().buildSessionFactory();
        }catch(Exception e){
            System.out.println("Error al crear SessionFactory");
            System.out.println(e);
            //Aquí generamos una excepcion diciendo que no se va a
            //inicializar el SessionFactory
            throw new ExceptionInInitializerError(e);
        }
    }

    //Getter de la variable SessionFactory
    public static SessionFactory getSessionFactory() {
        return sf;
    }
    
    
    
}
