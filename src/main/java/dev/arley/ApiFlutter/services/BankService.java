package dev.arley.ApiFlutter.services;

import dev.arley.ApiFlutter.entities.Bank;
import dev.arley.ApiFlutter.entities.Users;
import dev.arley.ApiFlutter.repository.BankRepository;
import dev.arley.ApiFlutter.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService implements IBankService {

    @Autowired
    private BankRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Bank save(Bank bank) {

        return repository.save(bank);
    }


    @Override
    public List<Bank> getAll(){return (List<Bank>) repository.findAll();}

    @Override
    public Bank getByNumeroCuenta(String numeroCuenta) {
        return repository.findByNumeroCuenta(numeroCuenta).orElseThrow(() ->
                new RuntimeException("Cuenta bancaria con el # " + numeroCuenta + " no encontrada"));
    }
    @Override
    public void consignarSaldo(String numeroCuenta, double amount) {
        Bank bank = getByNumeroCuenta(numeroCuenta);
        if (bank != null) {
            bank.setDisponible(bank.getDisponible() + amount);
            repository.save(bank);
        } else {
            throw new RuntimeException("Cuenta bancaria con el número " + numeroCuenta + " no encontrada");
        }
    }

    @Override
    public void retirarSaldo(String numeroCuenta, double amount) {
        Bank bank = getByNumeroCuenta(numeroCuenta);
        if (bank != null) {
            if (bank.getDisponible() >= amount) {
                bank.setDisponible(bank.getDisponible() - amount);
                repository.save(bank);
            } else {
                throw new RuntimeException("El monto supera el saldo disponible");
            }
        } else {
            throw new RuntimeException("Cuenta bancaria con el número " + numeroCuenta + " no encontrada");
        }
    }

    @Override
    public void deleteByNumeroCuenta(String numeroCuenta) {
        Bank bank = getByNumeroCuenta(numeroCuenta);
        if (bank!= null) {
            repository.deleteByNumeroCuenta(numeroCuenta);
        }
    }



}
