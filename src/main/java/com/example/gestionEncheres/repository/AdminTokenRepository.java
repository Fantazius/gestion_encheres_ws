package com.example.gestionEncheres.repository;

import com.example.gestionEncheres.models.AdminToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AdminTokenRepository extends JpaRepository<AdminToken,Integer> {
    @Query(value = "select isAdminTokenValid(?1)",nativeQuery = true)
    public boolean isAdminTokenValid(String token);

    @Query(value = "select * from admintokens where token=?1",nativeQuery = true)
    public AdminToken getTokenByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "update admintokens set dateexpiration=now() where token=:token",nativeQuery = true)
    public void deconnexion(@Param("token")String token);
}
