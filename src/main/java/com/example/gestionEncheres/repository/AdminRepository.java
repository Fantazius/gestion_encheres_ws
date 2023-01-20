package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Admin;
import com.example.gestionEncheres.models.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer> {
    @Query(value = "select * from admins where identifiant= :identifiant and password=:pswd",nativeQuery = true)
    public Admin findByIdentifiantAndPassword(@Param("identifiant") String identifiant, @Param("pswd") String password);
}
