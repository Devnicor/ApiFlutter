package dev.arley.ApiFlutter.services;

import dev.arley.ApiFlutter.entities.Bank;

import java.util.List;

public interface IBankService {

    List<Bank> getAll();

    void save (Bank bank);

    Bank getByNumeroCuenta(String numeroCuenta);

    void consignarSaldo(String numeroCuenta, double amount);

    void retirarSaldo(String numeroCuenta, double amount);

    void deleteByNumeroCuenta(String numeroCuenta);
}
