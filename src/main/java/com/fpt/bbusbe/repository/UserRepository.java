package com.fpt.bbusbe.repository;

import com.fpt.bbusbe.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.status='ACTIVE' " +
            "and (lower(u.name) like :keyword " +
            "or lower(u.address) like :keyword " +
            "or lower(u.username) like :keyword " +
            "or lower(u.phone) like :keyword " +
            "or lower(u.email) like :keyword)")
    Page<User> searchByKeyword(String keyword, Pageable pageable);

    User findByUsername(String username);
    User findByPhone(String phone);
    User findByEmail(String email);

}
