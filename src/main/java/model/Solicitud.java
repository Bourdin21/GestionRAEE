package model;

import javax.persistence.*;

@Entity
@Table(name = "Solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSolicitud")
    private int idSolicitud;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idCentro", nullable = false)
    private CentroRecoleccion centroRecoleccion;

    @Column(name = "estado")
    private String estado;

    // Constructor sin parámetros
    public Solicitud() {}

    // Constructor con parámetros
    public Solicitud(Usuario usuario, CentroRecoleccion centroRecoleccion, String estado) {
        this.usuario = usuario;
        this.centroRecoleccion = centroRecoleccion;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CentroRecoleccion getCentroRecoleccion() {
        return centroRecoleccion;
    }

    public void setCentroRecoleccion(CentroRecoleccion centroRecoleccion) {
        this.centroRecoleccion = centroRecoleccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
