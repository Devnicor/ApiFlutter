package dev.arley.ApiFlutter.repository;

import dev.arley.ApiFlutter.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByCorreo(String correo);

}
