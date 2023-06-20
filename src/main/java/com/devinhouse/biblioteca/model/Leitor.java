package com.devinhouse.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "LEITORES")
@Data
public class Leitor {

    @Id
    private Long cpf;

    private String nome;

    private LocalDate dataNascimento;

    private int qtdEmprestimos;

}
