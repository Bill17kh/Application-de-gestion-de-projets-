package ma.projet.classes;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employe_tache")
public class EmployeTache {
    @EmbeddedId
    private EmployeTacheId id = new EmployeTacheId();

    @ManyToOne
    @MapsId("employeId")
    @JoinColumn(name="employe_id")
    private Employe employe;

    @ManyToOne
    @MapsId("tacheId")
    @JoinColumn(name="tache_id")
    private Tache tache;

    private LocalDate dateDebutReelle;
    private LocalDate dateFinReelle;

    public EmployeTache() {}
    public EmployeTache(Employe emp, Tache t, LocalDate debut, LocalDate fin) {
        this.employe = emp;
        this.tache = t;
        this.id = new EmployeTacheId(emp.getId(), t.getId());
        this.dateDebutReelle = debut;
        this.dateFinReelle = fin;
    }

    public EmployeTacheId getId() { return id; }
    public Employe getEmploye() { return employe; }
    public Tache getTache() { return tache; }
    public LocalDate getDateDebutReelle() { return dateDebutReelle; }
    public LocalDate getDateFinReelle() { return dateFinReelle; }

    public void setEmploye(Employe employe) { this.employe = employe; }
    public void setTache(Tache tache) { this.tache = tache; }
    public void setDateDebutReelle(LocalDate dateDebutReelle) { this.dateDebutReelle = dateDebutReelle; }
    public void setDateFinReelle(LocalDate dateFinReelle) { this.dateFinReelle = dateFinReelle; }
}
