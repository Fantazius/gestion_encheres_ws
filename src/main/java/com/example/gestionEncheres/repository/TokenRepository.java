package com.example.gestionEncheres.repository;

import javax.transaction.Transactional;

import com.example.gestionEncheres.models.Token;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends CrudRepository<Token,Integer> {
    @Query(value = "select isTokenValid(?1)",nativeQuery = true)
    public boolean isTokenValid(String token);

    @Query(value = "select * from Tokens where token=?1",nativeQuery = true)
    public Token getTokenByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "update Tokens set dateexpiration=now() where token=:token",nativeQuery = true)
    public void deconnexion(@Param("token")String token);
}
