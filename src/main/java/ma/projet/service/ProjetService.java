package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProjetService extends ServiceBase<ma.projet.classes.Projet> {
    @Override protected Class<ma.projet.classes.Projet> getEntityClass() { return ma.projet.classes.Projet.class; }

    public List<Tache> getTachesPlanifiees(int projetId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("""
                FROM Tache t WHERE t.projet.id = :pid ORDER BY t.dateDebut
            """, Tache.class)
            .setParameter("pid", projetId)
            .getResultList();
        }
    }

    public List<Object[]> getTachesRealiseesAvecDates(int projetId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("""
                SELECT t.id, t.nom, et.dateDebutReelle, et.dateFinReelle
                FROM EmployeTache et JOIN et.tache t
                WHERE t.projet.id = :pid
                AND et.dateDebutReelle IS NOT NULL AND et.dateFinReelle IS NOT NULL
                ORDER BY et.dateDebutReelle
            """)
            .setParameter("pid", projetId)
            .getResultList();
        }
    }
}
