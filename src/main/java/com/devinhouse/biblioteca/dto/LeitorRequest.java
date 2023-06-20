package com.devinhouse.biblioteca.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeitorRequest {

    @NotNull(message = "Campo obrigatório")
    private Long cpf;

    @NotEmpty(message = "Campo obrigatório")
    @Size(min = 2, max = 100, message = "Tamanho informado é inválido")
    private String nome;

    @NotNull(message = "Campo obrigatório")
    @Past(message = "Deve ser uma data anterior à data atual")
    private LocalDate dataNascimento;

}
