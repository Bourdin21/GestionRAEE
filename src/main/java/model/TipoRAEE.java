package model;

import javax.persistence.*;

@Entity
@Table(name = "TipoRAEE")
public class TipoRAEE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipo")
    private int idTipo;

    @Column(name = "descripcion")
    private String descripcion;

    // Constructor sin parámetros
    public TipoRAEE() {}

    // Constructor con parámetros
    public TipoRAEE(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
