package com.example.gestionEncheres.models;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="gagnants")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class
Gagnant {
    @Id
    @Column(name = "idenchere", nullable = false)
    private Integer idEnchere;
    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    private Utilisateur utilisateur;
    @Column(name = "montant")
    private int montant;

    @Override
    public String toString() {
        return "Gagnant{" +
                "idEnchere=" + idEnchere +
                ", utilisateur=" + utilisateur +
                ", montant=" + montant +
                '}';
    }
}
