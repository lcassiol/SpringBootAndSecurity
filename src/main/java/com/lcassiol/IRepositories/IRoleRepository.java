package com.lcassiol.IRepositories;

import com.lcassiol.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);

}