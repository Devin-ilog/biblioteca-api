package com.devinhouse.biblioteca.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeitorResponse {

    private Long cpf;

    private String nome;

    private LocalDate dataNascimento;

    private int qtdEmprestimos;

}
