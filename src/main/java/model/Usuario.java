package model;
import javax.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    public enum Rol {
        ADMIN, USER
    }

    // Constructor
    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String direccion, String email, String telefono, Rol rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
    }

    public Usuario(String nombre, String direccion, String email, String telefono, Rol rol) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private int idUsuario;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol; // Campo para definir el rol del usuario

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
