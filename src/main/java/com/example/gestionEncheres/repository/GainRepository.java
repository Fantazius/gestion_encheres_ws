package com.example.gestionEncheres.repository;

import com.example.gestionEncheres.models.Gain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GainRepository extends JpaRepository<Gain,Integer> {
}
