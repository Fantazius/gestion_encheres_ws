package com.example.gestionEncheres.repository;

import com.example.gestionEncheres.models.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@EnableMongoRepositories
public interface PhotoRepository extends MongoRepository<Photo,String> {
    Photo findPhotoByIdEnchere(Integer idEnchere);
}
