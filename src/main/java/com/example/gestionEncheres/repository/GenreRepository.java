package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer> {
}
