package dao;

import model.CantidadTipo;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CantidadTipoDAO {

    // Crear una nueva cantidad de tipo RAEE en una solicitud
    public void guardarCantidadTipo(CantidadTipo cantidadTipo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cantidadTipo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Obtener todos los registros de cantidadTipo
    public List<CantidadTipo> obtenerCantidadesPorSolicitud(int idSolicitud) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from CantidadTipo where solicitud.idSolicitud = :idSolicitud", CantidadTipo.class)
                    .setParameter("idSolicitud", idSolicitud).list();
        }
    }

    // Eliminar un registro de cantidadTipo por ID
    public void eliminarCantidadTipo(int idCantidadTipo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CantidadTipo cantidadTipo = session.get(CantidadTipo.class, idCantidadTipo);
            if (cantidadTipo != null) {
                session.delete(cantidadTipo);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
