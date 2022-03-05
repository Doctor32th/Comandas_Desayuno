package com.mycompany.dc_comandas.odb;

import static java.time.MonthDay.now;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import models.Pedido;
import models.Producto;

/**
 * @author Doctor32 
 * En vez de hacer un DAO vamos a meter los métodos necesarios aquí.
 * OBSERVACIONES. Solo hay que establecer una única conexión a la base de datos.
 * De manera, que con una sola podamos guardar tanto la carta de productos como
 * los pedidos que se gestionen.
 * Las clases modelo deben ir en singular
 */
public class CafeteriaRoom23 {

    //private static EntityManagerFactory emfPedido;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {

        try {
            //Si no existe lo crea, si existe lo actualiza
            emf = Persistence.createEntityManagerFactory("cartaR23.odb");
        } catch (Exception e) {
            System.out.println("Error al iniciar el EntityManagerFactory");
        }
        
        //Creación de la sesión
        EntityManager emC = emf.createEntityManager();
        
        //Aquí vamos a añadir los productos
        Producto prod1 = new Producto();
        prod1.setNombreProducto("Chocolate");
        prod1.setPrecioProducto(1);
        prod1.setDetalleProducto("El mejor, de la marca VALOR");
        
        Producto prod2 = new Producto();
        prod2.setNombreProducto("Churros");
        prod2.setPrecioProducto(2);
        prod2.setDetalleProducto("Cada pedido son 10 unidades");
        
        Producto prod3 = new Producto();
        prod3.setNombreProducto("Brownie");
        prod3.setPrecioProducto(4);
        prod3.setDetalleProducto("Nuestro producto estrella");
        
        Producto prod4 = new Producto();
        prod4.setNombreProducto("Muffin");
        prod4.setPrecioProducto(3);
        prod4.setDetalleProducto("Es una bomba de chocolate");
        
        //Antes de grabar los datos debemos hacer una transacción
        //Iniciamos la transacción, grabamos (con persist) y comiteamos
        emC.getTransaction().begin();
        emC.persist(prod1);
        emC.persist(prod2);
        emC.persist(prod3);
        emC.persist(prod4);
        emC.getTransaction().commit();
        
        Scanner sc = new Scanner(System.in);
        
        boolean salir = false;
        int opcion;

        while (!salir) {

            System.out.println("****************************");
            System.out.println("      CAFETERÍA ROOM23     ");
            System.out.println("****************************");
            System.out.println("        BIENVENIDO!!        ");
            System.out.println("============================");
            System.out.println("-----1. Crear pedido-----");
            System.out.println("-----2. Eliminar pedido-----");
            System.out.println("-----3. Listar pedidos-----");
            System.out.println("-----4. Listar pedidos de hoy-----");
            System.out.println("-----5. Mostrar Carta-----");
            System.out.println("-----6. Salir-----");
            System.out.println("============================");

            try {
                System.out.println("Elige una opción, por favor");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Por favor, escriba el número del producto que desea");
                        Scanner crear = new Scanner(System.in);
                        Long idProd = crear.nextLong();
                        
                        TypedQuery<models.Producto> qCrear = emC.createQuery("SELECT p FROM Producto p WHERE p.idProducto = :nuevo", models.Producto.class);
                        qCrear.setParameter("nuevo", idProd);   
                        
                        var resId = qCrear.getSingleResult();                    
                        Pedido pedidoNuevo = new Pedido();
                        pedidoNuevo.setPrecioPedido(resId.getPrecioProducto());
                       
                        java.util.Date ahora = new java.util.Date();
                        java.sql.Date fechaPedido = new java.sql.Date(ahora.getTime());
                        pedidoNuevo.setFechaPedido(fechaPedido);
                        pedidoNuevo.setProductoId(resId.getIdProducto());
                        pedidoNuevo.setProductos(resId);
                        
                        emC.getTransaction().begin();
                        emC.persist(pedidoNuevo);
                        emC.getTransaction().commit();
                        
                        System.out.println("Creando pedido...");
                        Thread.sleep(2000);
                        System.out.println("Su pedido se ha creado con éxito");
                        System.out.println(pedidoNuevo);
                        //System.out.println(resId);
                        break;
                    case 2:
                        System.out.println("Por favor, escriba el número del pedido que desea eliminar");
                        Scanner eliminar = new Scanner(System.in);
                        Long idPedido = eliminar.nextLong();
                        
                        emC.getTransaction().begin();
                        
                        TypedQuery<models.Pedido> qEliminar = emC.createQuery("DELETE FROM Pedido p WHERE p.idPedido = :p", models.Pedido.class);
                        qEliminar.setParameter("p", idPedido).executeUpdate();
                        
                        Pedido pedidoABorrar = new Pedido();
                        
                        emC.remove(pedidoABorrar);
                        emC.getTransaction().commit();
                        
                        System.out.println("Eliminando pedido...");
                        Thread.sleep(2000);
                        System.out.println("Su pedido ha sido eliminado con éxito");
                        break;

                    case 3:
                        System.out.println("Listando todos sus pedidos...");
                        TypedQuery<models.Pedido> queryListarPedidos = emC.createQuery("SELECT p FROM Pedido p", models.Pedido.class);
                        var resultadoPedidos = queryListarPedidos.getResultList();
                        Thread.sleep(2000);
                        resultadoPedidos.forEach((rp) -> System.out.println(rp.getIdPedido() + ", " + rp.getProductos() + ", " + rp.getFechaPedido()));
                        //System.out.println(resultadoPedidos);
                        break;
                    case 4:
                        System.out.println("Listando todos sus pedidos de hoy...");
                        java.util.Date actual = new java.util.Date();
                        java.sql.Date fechaActual = new java.sql.Date(actual.getTime());
                        TypedQuery<models.Pedido> queryListarPedidosHoy = emC.createQuery("SELECT p FROM Pedido p WHERE p.fechaPedido=:hoy", models.Pedido.class);
                        queryListarPedidosHoy.setParameter("hoy", now().getDayOfMonth());
                        var resultadoPedidosHoy = queryListarPedidosHoy.getResultList();
                        Thread.sleep(2000);
                        resultadoPedidosHoy.forEach((rp) -> System.out.println(rp.getIdPedido() + ", " + rp.getProductos() + ", " + rp.getFechaPedido()));
                        break;
                    case 5:
                        TypedQuery<models.Producto> queryMostrar = emC.createQuery("SELECT p FROM Producto p", models.Producto.class);
                        var resultado = queryMostrar.getResultList();
                        System.out.println("Mostrando carta de desayunos...");                  
                        Thread.sleep(2000);
                        resultado.forEach((r) -> System.out.println(r.getIdProducto() + ", " + r.getNombreProducto() 
                                + ", " + r.getPrecioProducto() + ", " + r.getDetalleProducto()));
                        break;
                    case 6:
                        System.out.println("Saliendo del programa. Por favor, espere...");
                        Thread.sleep(1000);
                        salir = true;
                        break;
                    default:
                        System.out.println("La opción a elegir no figura en el menú actual. " +
                                "Inténtelo de nuevo");
                }
            } catch (InputMismatchException | InterruptedException ex) {
                System.out.println("Debe insertar un número para elegir");
                sc.next();
            }
        }
        

    }

}
