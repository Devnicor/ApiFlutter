package dev.arley.ApiFlutter.services;

import dev.arley.ApiFlutter.entities.Users;
import dev.arley.ApiFlutter.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements IUsersService{
    @Autowired
    private UsersRepository repository;

    public Users save(Users users){this.repository.save(users);
        return users;
    }

    @Override
    public List<Users> getAll(){return (List<Users>) repository.findAll();}

    @Override
    public Users getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario con ID " + id + " no encontrado"));
    }

    @Override
    public Users login(String correo, String contrasena) {
        Users user = repository.findByCorreo(correo);
        if (user != null && user.getContrasena().equals(contrasena)) {
            return user;
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }

    @Override
    public Users update(Long id, Users updatedUser) {
        Users user = getById(id);
        if (user != null) {
            user.setNombre(updatedUser.getNombre());
            user.setCorreo(updatedUser.getCorreo());
            user.setContrasena(updatedUser.getContrasena());
            return repository.save(user);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Users user = getById(id);
        if (user != null) {
            repository.deleteById(id);
        }
    }



}
