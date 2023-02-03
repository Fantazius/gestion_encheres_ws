package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.models.Photo;
import com.example.gestionEncheres.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired(required=true)
    PhotoRepository photoRepository;

    @GetMapping
    private List<Photo> findAll(){
        return photoRepository.findAll();
    }

}
