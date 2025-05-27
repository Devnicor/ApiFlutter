package dev.arley.ApiFlutter.repository;

import dev.arley.ApiFlutter.entities.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends CrudRepository <Bank , Long > {

    Optional<Bank> findByNumeroCuenta(String numeroCuenta);

    void deleteByNumeroCuenta(String numeroCuenta);
}
