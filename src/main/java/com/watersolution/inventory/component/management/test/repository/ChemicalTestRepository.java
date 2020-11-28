package com.watersolution.inventory.component.management.test.repository;

import com.watersolution.inventory.component.management.test.model.db.ChemicalTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemicalTestRepository extends JpaRepository<ChemicalTest, Long> {
}
