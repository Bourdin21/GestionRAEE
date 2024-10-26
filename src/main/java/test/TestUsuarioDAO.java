package test;

import dao.UsuarioDAO;
import model.Usuario;
import util.HibernateUtil;

public class TestUsuarioDAO {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Crear y guardar un nuevo usuario
        Usuario nuevoUsuario = new Usuario("Juan Perez", "Calle Falsa 123", "juan.perez@gmail.com", "1234567890", Usuario.Rol.USER);
        usuarioDAO.guardarUsuario(nuevoUsuario);
        System.out.println("Usuario creado: " + nuevoUsuario);

        // Obtener todos los usuarios
        System.out.println("Lista de usuarios:");
        usuarioDAO.obtenerUsuarios().forEach(usuario -> System.out.println(usuario.getNombre()));

        // Obtener un usuario por ID
        Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorId(nuevoUsuario.getIdUsuario());
        System.out.println("Usuario obtenido por ID: " + usuarioExistente.getNombre());

        // Actualizar el usuario
        usuarioExistente.setDireccion("Nueva Calle 456");
        usuarioDAO.actualizarUsuario(usuarioExistente);
        System.out.println("Usuario actualizado: " + usuarioExistente.getDireccion());

        // Eliminar el usuario
        usuarioDAO.eliminarUsuario(usuarioExistente.getIdUsuario());
        System.out.println("Usuario eliminado.");
        HibernateUtil.shutdown();
    }
}
