package tpi.backend.e_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import tpi.backend.e_commerce.models.User;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    // Since email is unique, we'll find users by email
    Optional<User> findByEmail(String email);
}

