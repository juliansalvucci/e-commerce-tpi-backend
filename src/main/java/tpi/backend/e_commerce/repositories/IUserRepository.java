package tpi.backend.e_commerce.repositories;

import org.springframework.data.repository.CrudRepository;

import tpi.backend.e_commerce.models.User;

public interface IUserRepository extends CrudRepository<User, Long> {

}
