package com.fpt.bbusbe.repository;

import com.fpt.bbusbe.model.entity.User;
import com.fpt.bbusbe.model.entity.UserHasRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserHasRoleRepository extends JpaRepository<UserHasRole, Integer> {
}
