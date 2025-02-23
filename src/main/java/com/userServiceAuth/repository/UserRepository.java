package com.userServiceAuth.repository;

import com.userServiceAuth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
