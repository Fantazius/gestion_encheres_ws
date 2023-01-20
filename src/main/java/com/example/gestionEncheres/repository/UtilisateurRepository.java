package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur,Integer> {
    @Query(value = "select * from utilisateurs where email= :email and password=:pswd",nativeQuery = true)
    public Utilisateur findByEmailAndPassword(@Param("email") String email, @Param("pswd") String password);
}