package com.devinhouse.biblioteca.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmprestimoRequest {

    @NotEmpty(message = "Campo obrigatório")
    private String isbn;

    @NotNull(message = "Campo obrigatório")
    private Long cpf;

}
