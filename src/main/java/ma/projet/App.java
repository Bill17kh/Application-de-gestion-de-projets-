package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Bootstrap SessionFactory
        HibernateUtil.getSessionFactory();

        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService employeTacheService = new EmployeTacheService();

        // --- Données de démonstration minimalistes ---
        Employe chef = new Employe("Dupont","Paul","0600112233");
        employeService.create(chef);

        Projet p = new Projet("Gestion de stock", LocalDate.of(2013,1,14), LocalDate.of(2013,12,31), chef);
        projetService.create(p);

        Tache t1 = new Tache("Analyse", LocalDate.of(2013,2,1), LocalDate.of(2013,2,28), 1500, p);
        Tache t2 = new Tache("Conception", LocalDate.of(2013,3,1), LocalDate.of(2013,3,31), 1200, p);
        Tache t3 = new Tache("Développement", LocalDate.of(2013,4,1), LocalDate.of(2013,4,30), 5000, p);
        tacheService.create(t1); tacheService.create(t2); tacheService.create(t3);

        Employe emp = new Employe("Martin","Sami","0600223344");
        employeService.create(emp);

        EmployeTache et1 = new EmployeTache();
        et1.setEmploye(emp); et1.setTache(t1);
        et1.setDateDebutReelle(LocalDate.of(2013,2,10));
        et1.setDateFinReelle(LocalDate.of(2013,2,20));
        employeTacheService.create(et1);

        EmployeTache et2 = new EmployeTache();
        et2.setEmploye(emp); et2.setTache(t2);
        et2.setDateDebutReelle(LocalDate.of(2013,3,10));
        et2.setDateFinReelle(LocalDate.of(2013,3,15));
        employeTacheService.create(et2);

        EmployeTache et3 = new EmployeTache();
        et3.setEmploye(emp); et3.setTache(t3);
        et3.setDateDebutReelle(LocalDate.of(2013,4,10));
        et3.setDateFinReelle(LocalDate.of(2013,4,25));
        employeTacheService.create(et3);

        // --- Exemples d'affichages ---
        System.out.println("Projet : " + p.getId() + "  Nom : " + p.getNom() + "  Date début : " + p.getDateDebut());
        System.out.println("Liste des tâches réalisées:");
        List<Object[]> lignes = projetService.getTachesRealiseesAvecDates(p.getId());
        System.out.println("Num  Nom            Date Début Réelle   Date Fin Réelle");
        for (Object[] row : lignes) {
            System.out.printf("%-4s %-14s %-18s %-18s%n", row[0], row[1], row[2], row[3]);
        }

        System.out.println("\nTâches prix > 1000:");
        tacheService.getTachesPrixSuperieurA(1000).forEach(t ->
            System.out.println(t.getId() + " - " + t.getNom() + " (" + t.getPrix() + ")"));

        System.out.println("\nProjets gérés par " + chef.getNom() + " :");
        employeService.getProjetsGeresParEmploye(chef.getId()).forEach(pr ->
            System.out.println(pr.getId() + " - " + pr.getNom()));

        System.out.println("\nTâches réalisées par " + emp.getNom() + " :");
        employeService.getTachesRealiseesParEmploye(emp.getId()).forEach(t ->
            System.out.println(t.getId() + " - " + t.getNom()));
    }
}
