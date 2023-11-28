package com.bruquest.bruquestapi.repository;

import com.bruquest.bruquestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
