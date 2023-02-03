package com.example.gestionEncheres.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;

@Entity
@Table(name="gains")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Gain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgain")
    private Integer idGain;
    @Column(name = "idenchere", nullable = false)
    private Integer idEnchere;
    @Column(name = "commission")
    private Double commission;
    @Column(name = "montantgain")
    private Double montantGain;

    public Gain(Integer idEnchere, Double commission, Double montantGain) {
        this.idEnchere = idEnchere;
        this.commission = commission;
        this.montantGain = montantGain;
    }
}
