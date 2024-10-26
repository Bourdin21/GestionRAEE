package service;

import dao.UsuarioDAO;
import model.Usuario;
import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Crear un nuevo usuario
    public void crearUsuario(Usuario usuario) {
        usuarioDAO.guardarUsuario(usuario);
    }

    // Obtener un usuario por ID
    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioDAO.obtenerUsuarioPorId(id);
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.obtenerUsuarios();
    }

    // Actualizar un usuario
    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.actualizarUsuario(usuario);
    }

    // Eliminar un usuario por ID
    public void eliminarUsuario(int id) {
        usuarioDAO.eliminarUsuario(id);
    }

    // Método para obtener usuario por nombre
    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
        return usuarioDAO.obtenerUsuarioPorNombre(nombreUsuario);
    }
}
