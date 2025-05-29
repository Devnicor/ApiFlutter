package dev.arley.ApiFlutter.services;

import dev.arley.ApiFlutter.entities.Users;

import java.util.List;

public interface IUsersService {

    List<Users> getAll();

    Users save (Users users);


    Users getById(Long id);

    Users login(String correo, String contrasena);

    Users update(Long id, Users updatedUser);

    void delete(Long id);
}
