package dao;

import model.TipoRAEE;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TipoRAEEDAO {

    // Crear un nuevo tipo de RAEE
    public void guardarTipoRAEE(TipoRAEE tipoRAEE) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tipoRAEE);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Obtener todos los tipos de RAEE
    public List<TipoRAEE> obtenerTiposRAEE() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from TipoRAEE", TipoRAEE.class).list();
        }
    }

    // Obtener un tipo de RAEE por ID
    public TipoRAEE obtenerTipoRAEEPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TipoRAEE.class, id);
        }
    }

    // Eliminar un tipo de RAEE por ID
    public void eliminarTipoRAEE(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TipoRAEE tipoRAEE = session.get(TipoRAEE.class, id);
            if (tipoRAEE != null) {
                session.delete(tipoRAEE);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
