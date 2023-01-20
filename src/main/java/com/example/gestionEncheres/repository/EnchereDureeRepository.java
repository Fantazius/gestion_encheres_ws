package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.EnchereDuree;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EnchereDureeRepository extends JpaRepository<EnchereDuree,Integer> {
    @Query(value = "select * from encheredurees order by idduree desc limit 1",nativeQuery = true)
    public EnchereDuree getLastEnchereDuree();
}
