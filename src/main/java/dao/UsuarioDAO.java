package dao;

import model.Usuario;
import org.hibernate.query.Query;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UsuarioDAO {

    // Crear un nuevo usuario
    public void guardarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Usuario", Usuario.class).list();
        }
    }

    // Obtener un usuario por ID
    public Usuario obtenerUsuarioPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    // Actualizar un usuario existente
    public void actualizarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Eliminar un usuario por ID
    public void eliminarUsuario(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, id);
            if (usuario != null) {
                session.delete(usuario);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Método para buscar usuario por nombre
    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> query = session.createQuery("from Usuario where lower(nombre) = :nombre", Usuario.class);
            query.setParameter("nombre", nombreUsuario.toLowerCase());
            return (Usuario)query.uniqueResult(); // Devuelve el usuario encontrado o null si no hay coincidencias
        } catch (Exception e) {
            System.err.println("Error al obtener el usuario con nombre " + nombreUsuario);
            e.printStackTrace();
            return null; // Retorna null en caso de excepción
        }
    }
}
