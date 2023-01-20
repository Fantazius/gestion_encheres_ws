package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission,Integer> {
}
