package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Doctor32
 * Modelo Pedidos con Lombok
 * El strategy identity solo era necesario en Hibernate
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Pedido")
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue
    private Long idPedido;
    private Long productoId;
    private Integer precioPedido;
    private Date fechaPedido;
    
    @ManyToOne
    @JoinColumn(name = "productoId")
    private Producto productos;
}
