package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Gagnant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GagnantRepository extends JpaRepository<Gagnant,Integer> {
}
