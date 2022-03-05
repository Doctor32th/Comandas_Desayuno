/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Doctor32
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Producto")
public class Producto implements Serializable {
    
    @Id
    @GeneratedValue
    private Long idProducto;
    private String nombreProducto;
    private Integer precioProducto;
    private String detalleProducto;
   
}
