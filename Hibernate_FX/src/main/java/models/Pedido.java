/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Modelo Usuario
 * @author Doctor32
 */

@Entity
@Table(name="pedidos2")
public class Pedido implements Serializable{
    
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id_pedido;
    
    private String producto;
    
    private Date fecha;
    
    private String estado;
    
    /**
     * Esto nos dice que la clase Pedido esta relacionada con la
     * clase Usuario.
     */
    @ManyToOne
    @JoinColumn(name="usuarios_id")
    private Usuario usuario;

    public Pedido() {
    }

    public Pedido(String producto, Date fecha, String estado) {
        this.producto = producto;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Pedido(Long id_pedido, String producto, Date fecha, String estado) {
        this.id_pedido = id_pedido;
        this.producto = producto;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Pedido(Long id_pedido, String producto, Date fecha, String estado, Usuario usuario) {
        this.id_pedido = id_pedido;
        this.producto = producto;
        this.fecha = fecha;
        this.estado = estado;
        this.usuario = usuario;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Pedido " + "id |" + id_pedido
                + "| producto |" + producto
                + "| fecha |" + fecha
                + "| estado|" + estado
                + "| usuario|" + usuario.getNombre();
    }
    
    

    
    
    
}
