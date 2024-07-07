package com.training.online_shopping.repository;

import com.training.online_shopping.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
