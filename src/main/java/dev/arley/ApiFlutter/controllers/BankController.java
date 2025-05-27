package dev.arley.ApiFlutter.controllers;


import dev.arley.ApiFlutter.entities.Bank;
import dev.arley.ApiFlutter.services.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private IBankService service;

    @GetMapping("")
    public List<Bank> getAll() {
        return service.getAll();
    }


    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<?> getByNumeroCuenta(@PathVariable String numeroCuenta) {
        try {
            return ResponseEntity.ok(service.getByNumeroCuenta(numeroCuenta));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/crearCuenta")
    public ResponseEntity<String> save(@RequestBody Bank bank) {
        service.save(bank);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cuenta bancaria creada con éxito");
    }


    @PutMapping("/{numeroCuenta}/consignar")
    public ResponseEntity<String> consignarSaldo(@PathVariable String numeroCuenta, @RequestParam double amount) {
        try {
            service.consignarSaldo(numeroCuenta, amount);
            return ResponseEntity.ok("Saldo consignado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PutMapping("/{numeroCuenta}/retirar")
    public ResponseEntity<String> retirarSaldo(@PathVariable String numeroCuenta, @RequestParam double amount) {
        try {
            service.retirarSaldo(numeroCuenta, amount);
            return ResponseEntity.ok("Saldo retirado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<String> deleteByNumeroCuenta(@PathVariable String numeroCuenta) {
        try {
            service.deleteByNumeroCuenta(numeroCuenta);
            return ResponseEntity.ok("Cuenta bancaria eliminada exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

