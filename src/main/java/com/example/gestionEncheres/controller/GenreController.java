package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Genre;
import com.example.gestionEncheres.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired(required=true)
    GenreService genreService;
    //creating a get mapping that retrieves all the Genre detail from the database
    @GetMapping()
    private Object getAllGenres()
    {
        try {
            return new Data(genreService.getAllGenres());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific genres
    @GetMapping("/{genreid}")
    private Genre getGenres(@PathVariable("genreid") int genreid)
    {
        return genreService.getGenresById(genreid);
    }
    //creating a delete mapping that deletes a specified genres
    @DeleteMapping("/{genreid}")
    private void delete(@PathVariable("genreid") int genreid)
    {
        genreService.delete(genreid);
    }
    //creating post mapping that post the genre detail in the database
    @PostMapping()
    private int save(@RequestBody Genre genre)
    {
        genreService.saveOrUpdate(genre);
        return genre.getIdGenre();
    }
    //creating put mapping that updates the genre detail
    @PutMapping()
    private Genre update(@RequestBody Genre genre)
    {
        genreService.saveOrUpdate(genre);
        return genre;
    }

}
