package com.appointment.app.repository;

import com.appointment.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
