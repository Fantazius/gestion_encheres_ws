package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.Photo;
import com.example.gestionEncheres.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> findAll(){
        return photoRepository.findAll();
    }
}
