package com.watersolution.inventory.component.entity.customer.repository;

import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findAllByStatus(int status, Pageable pageable);

    Page<Customer> findAllByNameLikeAndStatusIn(String name, List<Integer> statusList, Pageable pageable);

    Page<Customer> findAllByTypeLikeAndStatusIn(String type, List<Integer> statusList, Pageable pageable);

    Page<Customer> findAllByStatusIn(List<Integer> statusList, Pageable pageable);

    Page<Customer> findAllByNameLikeAndTypeLikeAndStatusIn(String name, String type, List<Integer> statusList, Pageable pageable);

    Customer findByIdAndStatus(long id, int status);

    Customer findByIdAndStatusIn(long id, List<Integer> statusList);

    Customer findByEmail(String userName);
}
