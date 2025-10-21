package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EmployeService extends ServiceBase<Employe> {
    @Override protected Class<Employe> getEntityClass() { return Employe.class; }

    public List<Tache> getTachesRealiseesParEmploye(int employeId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("""
                SELECT et.tache FROM EmployeTache et
                WHERE et.employe.id = :eid
                AND et.dateDebutReelle IS NOT NULL AND et.dateFinReelle IS NOT NULL
            """, Tache.class)
            .setParameter("eid", employeId)
            .getResultList();
        }
    }

    public List<Projet> getProjetsGeresParEmploye(int employeId) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("""
                FROM Projet p WHERE p.chef.id = :eid
            """, Projet.class)
            .setParameter("eid", employeId)
            .getResultList();
        }
    }
}
