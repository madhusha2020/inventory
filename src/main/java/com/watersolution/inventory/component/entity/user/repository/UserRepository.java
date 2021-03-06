package com.watersolution.inventory.component.entity.user.repository;

import com.watersolution.inventory.component.entity.user.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameAndStatus(String userName, Integer status);

    List<User> findByStatus(int status);

    List<User> findByStatusIn(List<Integer> statusList);

    User findByUserNameAndStatusIn(String userName, List<Integer> statusList);
}
