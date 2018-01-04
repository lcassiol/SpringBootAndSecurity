package com.lcassiol.IRepositories;

import com.lcassiol.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
