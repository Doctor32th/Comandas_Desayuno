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
 *
 * @author Doctor32
 */

@Entity
@Table(name="pedidos")
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long idPedido;
    private Integer precioPedido;
    private String estadoPedido;
    private Date fechaPedido;
    
    @ManyToOne
    @JoinColumn(name="productos_id")
    private Producto productoCarta;

    public Pedido() {
    }

    public Pedido(Long idPedido, Producto productoCarta, Integer precioPedido, String estadoPedido, Date fechaPedido) {
        this.idPedido = idPedido;
        this.productoCarta = productoCarta;
        this.precioPedido = precioPedido;
        this.estadoPedido = estadoPedido;
        this.fechaPedido = fechaPedido;
        
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getPrecioPedido() {
        return precioPedido;
    }

    public void setPrecioPedido(Integer precioPedido) {
        this.precioPedido = precioPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Producto getProductoCarta() {
        return productoCarta;
    }

    public void setProductoCarta(Producto productoCarta) {
        this.productoCarta = productoCarta;
    }
    

    @Override
    public String toString() {
        return "Pedido: " + "|idPedido| " + idPedido 
                + " |precioPedido| " + precioPedido 
                + " |estadoPedido| " + estadoPedido
                + " |fechaPedido| " + fechaPedido 
                + " |productoCarta| " + productoCarta.getNombreProducto();
    }
    
    
}
