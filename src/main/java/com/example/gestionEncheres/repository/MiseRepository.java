package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Mise;
import com.example.gestionEncheres.models.Token;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MiseRepository extends JpaRepository<Mise,Integer> {
    @Query(value = "select * from tokens where token=:token",nativeQuery = true)
    public Token getTokensByToken(@Param("token") String token);

    @Transactional
    @Modifying
    @Query(value = "insert into mises(idutilisateur,idenchere,montant,datemise) values(:idutilisateur, :idenchere, :montant, NOW())",nativeQuery = true)
    public int addMise(@Param("idutilisateur") int idutilisateur, @Param("idenchere") int idenchere, @Param("montant") int montant);

    @Query(value = "select * from v_gagnants where idenchere=?1",nativeQuery = true)
    public Mise getGagnant(Integer idEnchere);
}
