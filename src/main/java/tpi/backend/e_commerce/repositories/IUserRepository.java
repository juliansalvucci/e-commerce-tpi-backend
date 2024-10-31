package tpi.backend.e_commerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tpi.backend.e_commerce.models.User;


@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
    // Since email is unique, we'll find users by email
    Optional<User> findByEmail(String email);

    @Query("select CASE when COUNT(u) > 0 then true else false end from User u where UPPER(u.email) = UPPER(?1)")
    boolean existsByEmail(String email);

    @Query("select u from User u where u.id = ?1 and u.deleted = false")
    Optional<User> findActiveById(Long id);

    @Query("select u from User u where u.deleted = false")
    List<User> findAllActive();

    @Query("select u from User u where u.deleted = true")
    List<User> findAllDeleted();

}

