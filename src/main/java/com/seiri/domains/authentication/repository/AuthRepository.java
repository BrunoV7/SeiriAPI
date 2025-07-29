package com.seiri.domains.authentication.repository;

import com.seiri.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<User, UUID> {

   Boolean existsByEmail(String email);

   Optional<User> findByEmail(String username);

   UserDetails getUserByEmail(String email);

}
