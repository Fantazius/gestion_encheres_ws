package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Commission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends CrudRepository<Commission,Integer> {
}
