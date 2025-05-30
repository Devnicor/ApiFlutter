package dev.arley.ApiFlutter.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cuenta_bank")
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(unique = true)
    private String numeroCuenta;
    private Double disponible;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

}
