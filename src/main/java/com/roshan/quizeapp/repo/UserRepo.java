package com.roshan.quizeapp.repo;

import com.roshan.quizeapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<AppUser,Integer> {
    Optional<AppUser> findByUsername(String username);
}
