package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class TacheService extends ServiceBase<Tache> {
    @Override protected Class<Tache> getEntityClass() { return Tache.class; }

    public List<Tache> getTachesPrixSuperieurA(double minPrix) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createNamedQuery("Tache.prixSuperieurA", Tache.class)
                    .setParameter("minPrix", minPrix)
                    .getResultList();
        }
    }

    public List<Tache> getTachesRealiseesEntre(LocalDate from, LocalDate to) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("""
                SELECT DISTINCT et.tache FROM EmployeTache et
                WHERE et.dateDebutReelle >= :d1 AND et.dateFinReelle <= :d2
            """, Tache.class)
            .setParameter("d1", from)
            .setParameter("d2", to)
            .getResultList();
        }
    }
}
