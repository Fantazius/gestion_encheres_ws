package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.Genre;
import com.example.gestionEncheres.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    //getting all genres record by using the method findaAll() of CrudRepository
    public List<Genre> getAllGenres()
    {
        List<Genre> genres = new ArrayList<Genre>();
        genreRepository.findAll().forEach(genres::add);
        return genres;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Genre getGenresById(int id)
    {
        return genreRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Genre genre)
    {
        genreRepository.save(genre);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        genreRepository.deleteById(id);
    }
    //updating a record
    public void update(Genre genre)
    {
        genreRepository.save(genre);
    }
}
