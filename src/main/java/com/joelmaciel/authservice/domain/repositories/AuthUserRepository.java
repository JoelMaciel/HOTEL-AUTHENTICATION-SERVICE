package com.joelmaciel.authservice.domain.repositories;

import com.joelmaciel.authservice.domain.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {

    Optional<AuthUser> findByUserName(String userName);
}
