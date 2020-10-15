package com.watersolution.inventory.component.entity.item.repository;

import com.watersolution.inventory.component.entity.item.model.db.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAllByStatus(int status, Pageable pageable);

    Page<Item> findAllByNameLikeAndStatusIn(String name, List<Integer> statusList, Pageable pageable);

    Page<Item> findAllByTypeLikeAndStatusIn(String type, List<Integer> statusList, Pageable pageable);

    Page<Item> findAllByStatusIn(List<Integer> statusList, Pageable pageable);

    Page<Item> findAllByNameLikeAndTypeLikeAndStatusIn(String name, String type, List<Integer> statusList, Pageable pageable);
}
