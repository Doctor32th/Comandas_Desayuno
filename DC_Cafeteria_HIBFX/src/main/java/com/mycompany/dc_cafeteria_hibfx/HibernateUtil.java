package com.mycompany.dc_cafeteria_hibfx;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Doctor32
 */
public class HibernateUtil {
    
    private static final SessionFactory sf;
    
    static{
        
        try{
            sf = new Configuration().configure().buildSessionFactory();               
        } catch(HibernateException e) {
            System.out.println("Error al crear SessionFactory");
            System.out.println(e);
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sf;
    }
}
