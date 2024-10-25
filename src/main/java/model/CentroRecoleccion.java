package model;

import javax.persistence.*;

@Entity
@Table(name = "CentrosRecoleccion")
public class CentroRecoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCentro")
    private int idCentro;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "capacidad")
    private int capacidad;

    // Constructor sin parámetros
    public CentroRecoleccion() {}

    // Constructor con parámetros
    public CentroRecoleccion(String nombre, String direccion, int capacidad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
    }

    // Getters y Setters
    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}