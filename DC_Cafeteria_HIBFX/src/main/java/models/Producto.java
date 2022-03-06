/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * De una Carta surgirán muchos pedidos
 * @author Doctor32
 */

@Entity
@Table(name="carta")
public class Producto implements Serializable {
    
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long idProducto;
    private String nombreProducto;
    private Integer precioProducto;
    private String detalleProducto;
    
    @OneToMany(mappedBy="productoCarta", fetch=FetchType.EAGER)
    private Set<Pedido> pedidos;
    

    public Producto() {
        
    }

    public Producto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Producto(Long idProducto, String nombreProducto, Integer precioProducto, String detalleProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.detalleProducto = detalleProducto;
    }
    
    public Producto(String nombreProducto, Integer precioProducto, String detalleProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.detalleProducto = detalleProducto;
    } 

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Integer precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(String detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", precioProducto=" + precioProducto + ", detalleProducto=" + detalleProducto + '}';
    }
    
    
    
    
}
