package ma.projet.classes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employe")
public class Employe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;
    private String telephone;

    @OneToMany(mappedBy = "chef")
    private Set<Projet> projetsGeres = new HashSet<>();

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeTache> employeTaches = new HashSet<>();

    public Employe() {}
    public Employe(String nom, String prenom, String telephone) {
        this.nom = nom; this.prenom = prenom; this.telephone = telephone;
    }

    public Integer getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public Set<Projet> getProjetsGeres() { return projetsGeres; }
    public Set<EmployeTache> getEmployeTaches() { return employeTaches; }
}
