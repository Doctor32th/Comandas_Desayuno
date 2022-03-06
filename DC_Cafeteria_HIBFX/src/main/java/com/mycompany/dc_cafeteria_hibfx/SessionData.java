package com.mycompany.dc_cafeteria_hibfx;

import models.Pedido;
import models.Producto;

/**
 *
 * @author Doctor32
 */
public class SessionData {
    
    private static Producto productoActual;
    private static Pedido pedidoActual;

    public static Producto getProductoActual() {
        return productoActual;
    }

    public static void setProductoActual(Producto productoActual) {
        SessionData.productoActual = productoActual;
    }

    public static Pedido getPedidoActual() {
        return pedidoActual;
    }

    public static void setPedidoActual(Pedido pedidoActual) {
        SessionData.pedidoActual = pedidoActual;
    }
    
    
    
}
