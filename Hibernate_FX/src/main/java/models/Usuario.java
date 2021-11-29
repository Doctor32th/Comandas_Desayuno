/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Doctor32
 */

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String ciclo;
    private String nombre;
    private String contrasenha;
    
    /**
     * Esta es la parte del uno. Hace referencia al atributo declarado
     * en el modelo pedido (en este caso, usuario)
     */
    @OneToMany(mappedBy="usuario")
    private List<Pedido> pedidos;

    public Usuario() {
    }

    public Usuario(String ciclo, String nombre, String contrasenha) {
        this.ciclo = ciclo;
        this.nombre = nombre;
        this.contrasenha = contrasenha;
    }

    public Usuario(String ciclo, String nombre, String contrasenha, List<Pedido> pedidos) {
        this.ciclo = ciclo;
        this.nombre = nombre;
        this.contrasenha = contrasenha;
        this.pedidos = pedidos;
    }

    public Usuario(Long id, String ciclo, String nombre, String contrasenha) {
        this.id = id;
        this.ciclo = ciclo;
        this.nombre = nombre;
        this.contrasenha = contrasenha;
    }

    public Usuario(Long id, String ciclo, String nombre, String contrasenha, List<Pedido> pedidos) {
        this.id = id;
        this.ciclo = ciclo;
        this.nombre = nombre;
        this.contrasenha = contrasenha;
        this.pedidos = pedidos;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    @Override
    public String toString() {
        return "||Usuario|| " + " id -> | " + id
                + " |, nombre-> | " + ciclo
                + " |, contrasenha-> | " + nombre
                + " |, email-> | " + contrasenha + " |";
    }
    
    
    
}
