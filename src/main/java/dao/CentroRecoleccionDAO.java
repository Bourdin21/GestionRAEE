package dao;

import model.CentroRecoleccion;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CentroRecoleccionDAO {

    // Crear un nuevo centro de recolección
    public void guardarCentro(CentroRecoleccion centro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(centro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Obtener todos los centros de recolección
    public List<CentroRecoleccion> obtenerCentros() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from CentroRecoleccion", CentroRecoleccion.class).list();
        }
    }

    // Obtener un centro por ID
    public CentroRecoleccion obtenerCentroPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(CentroRecoleccion.class, id);
        }
    }

    // Actualizar un centro existente
    public void actualizarCentro(CentroRecoleccion centro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(centro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Eliminar un centro por ID
    public void eliminarCentro(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CentroRecoleccion centro = session.get(CentroRecoleccion.class, id);
            if (centro != null) {
                session.delete(centro);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
