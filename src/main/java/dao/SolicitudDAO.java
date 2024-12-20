package dao;

import model.Solicitud;
import org.hibernate.query.Query;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class SolicitudDAO {

    // Crear una nueva solicitud
    public void guardarSolicitud(Solicitud solicitud) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(solicitud);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Obtener todas las solicitudes
    public List<Solicitud> obtenerSolicitudes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Solicitud", Solicitud.class).list();
        }
    }

    // Obtener una solicitud por ID
    public Solicitud obtenerSolicitudPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Solicitud.class, id);
        }
    }

    // Método para obtener solicitudes por ID de usuario
    public List<Solicitud> obtenerSolicitudesPorUsuario(int idUsuario) {
        List<Solicitud> solicitudes = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Solicitud> query = session.createQuery("from Solicitud where usuario.idUsuario = :idUsuario", Solicitud.class);
            query.setParameter("idUsuario", idUsuario);
            solicitudes = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return solicitudes;
    }

    // Actualizar una solicitud existente
    public void actualizarSolicitud(Solicitud solicitud) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(solicitud);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Eliminar una solicitud por ID
    public void eliminarSolicitud(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Solicitud solicitud = session.get(Solicitud.class, id);
            if (solicitud != null) {
                session.delete(solicitud);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
