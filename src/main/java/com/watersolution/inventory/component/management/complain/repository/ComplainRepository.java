package com.watersolution.inventory.component.management.complain.repository;

import com.watersolution.inventory.component.management.complain.model.db.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplainRepository extends JpaRepository<Complain, Long> {

    List<Complain> findAllByStatusIn(List<Integer> statusList);

    Complain findByIdAndStatusIn(long id, List<Integer> statusList);
}
