package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Gagnant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GagnantRepository extends CrudRepository<Gagnant,Integer> {
}
