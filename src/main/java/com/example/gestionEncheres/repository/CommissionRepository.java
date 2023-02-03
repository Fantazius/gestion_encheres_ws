package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission,Integer> {

    @Query(value = "select * from commissions order by datechangement desc limit 1",nativeQuery = true)
    public Commission getCurrentCommission();
}
