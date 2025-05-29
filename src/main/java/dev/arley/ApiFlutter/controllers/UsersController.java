package dev.arley.ApiFlutter.controllers;



import dev.arley.ApiFlutter.entities.Users;
import dev.arley.ApiFlutter.repository.UsersRepository;
import dev.arley.ApiFlutter.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuentas")
public class UsersController {

    @Autowired
    private IUsersService service;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("")
    public List<Users> getAll(){return service.getAll();}


    @PostMapping("/crearUsuario")
    public ResponseEntity<?> registerUser(@RequestBody Users userRequest) {
        try {
            Users savedUser = service.save(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users userRequest) {
        try {
            Users user = service.login(userRequest.getCorreo(), userRequest.getContrasena());
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PutMapping("actualizarUsuario/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        try {
            Users updatedUser = service.update(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("eliminarUsuario/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }




}
