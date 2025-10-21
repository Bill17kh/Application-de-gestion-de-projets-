package ma.projet.classes;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projet")
public class Projet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Employe chef;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tache> taches = new HashSet<>();

    public Projet() {}
    public Projet(String nom, LocalDate dateDebut, LocalDate dateFin, Employe chef) {
        this.nom = nom; this.dateDebut = dateDebut; this.dateFin = dateFin; this.chef = chef;
    }

    public Integer getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public Employe getChef() { return chef; }
    public void setChef(Employe chef) { this.chef = chef; }
    public Set<Tache> getTaches() { return taches; }
}
