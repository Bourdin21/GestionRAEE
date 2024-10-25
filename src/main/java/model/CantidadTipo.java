package model;

import javax.persistence.*;

@Entity
@Table(name = "CantidadTipo")
public class CantidadTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCantidadTipo")
    private int idCantidadTipo;

    @ManyToOne
    @JoinColumn(name = "idTipo", nullable = false)
    private TipoRAEE tipoRAEE;

    @ManyToOne
    @JoinColumn(name = "idSolicitud", nullable = false)
    private Solicitud solicitud;

    @Column(name = "cantidad")
    private float cantidad;

    // Constructor sin parámetros
    public CantidadTipo() {}

    // Constructor con parámetros
    public CantidadTipo(TipoRAEE tipoRAEE, Solicitud solicitud, float cantidad) {
        this.tipoRAEE = tipoRAEE;
        this.solicitud = solicitud;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdCantidadTipo() {
        return idCantidadTipo;
    }

    public void setIdCantidadTipo(int idCantidadTipo) {
        this.idCantidadTipo = idCantidadTipo;
    }

    public TipoRAEE getTipoRAEE() {
        return tipoRAEE;
    }

    public void setTipoRAEE(TipoRAEE tipoRAEE) {
        this.tipoRAEE = tipoRAEE;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
}
