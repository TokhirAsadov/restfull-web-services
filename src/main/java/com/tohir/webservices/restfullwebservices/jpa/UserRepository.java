package com.tohir.webservices.restfullwebservices.jpa;

import com.tohir.webservices.restfullwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
