package com.devinhouse.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "LIVROS")
@Data
public class Livro {

    @Id
    private String isbn;

    private String titulo;

    private String autores;

    private boolean disponivel;

}
