package com.effective.project.bank.card.management.system.repository;

import com.effective.project.bank.card.management.system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByName(String name);
}
