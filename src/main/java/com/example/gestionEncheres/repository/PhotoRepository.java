package com.example.gestionEncheres.repository;

import com.example.gestionEncheres.models.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo,String> {
}
