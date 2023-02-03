package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Utilisateur;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    @Query(value = "select * from utilisateurs where email= :email and password=:pswd",nativeQuery = true)
    public Utilisateur findByEmailAndPassword(@Param("email") String email, @Param("pswd") String password);

    @Transactional
    @Modifying
    @Query(value = "update utilisateurs set montantsolde=montantsolde-:montant where idutilisateur=:iduser",nativeQuery = true)
    public void retrieveMontantWhenWin(@Param("iduser") Integer idUser,@Param("montant") Integer montant);

    @Transactional
    @Modifying
    @Query(value = "update utilisateurs set montantsolde=montantsolde+:montant where idutilisateur=:iduser",nativeQuery = true)
    public void collectMontantWhenWin(@Param("iduser") Integer idUser,@Param("montant") double montant);

    @Query(value = "select * from v_utilisateurs where idutilisateur=?1",nativeQuery = true)
    public Utilisateur findUserById(Integer idUtilisateur);

}