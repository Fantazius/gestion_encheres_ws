package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre,Integer> {
}
