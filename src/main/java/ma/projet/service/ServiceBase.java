package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class ServiceBase<T> implements IDao<T> {

    protected abstract Class<T> getEntityClass();

    @Override
    public boolean create(T o) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.save(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(T o) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.update(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(T o) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tx = s.beginTransaction();
            s.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public T getById(int id) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(getEntityClass(), id);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("FROM " + getEntityClass().getSimpleName()).list();
        }
    }
}
