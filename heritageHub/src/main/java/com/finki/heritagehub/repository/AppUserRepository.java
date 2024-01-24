package com.finki.heritagehub.repository;

import com.finki.heritagehub.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByUsernameAndPassword(String username, String password);
}
