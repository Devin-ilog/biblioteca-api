package com.devinhouse.biblioteca.service;

import com.devinhouse.biblioteca.exception.RegistroNaoEncontradoException;
import com.devinhouse.biblioteca.model.Livro;
import com.devinhouse.biblioteca.repository.LivroRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @InjectMocks
    private LivroService service;

    @Mock
    private LivroRepository repo;

    @Test
    @DisplayName("Quando consulta por isbn de livro nao cadastrado, deve lancar excecao")
    void consultarPorIsbn_naoExiste() {
        String isbn = "13-3243412-3453242";
        Mockito.when(repo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class,
                () -> service.consultar(isbn));
    }

    @Test
    @DisplayName("Quando consulta por isbn de livro cadastrado, deve retornar livro")
    void consultarPorIsbn() {
        // given
        String isbn = "13-3243412-3453242";
        Livro livro = new Livro();
        Mockito.when(repo.findById(Mockito.anyString())).thenReturn(Optional.of(livro));
        // when
        Livro resultado = service.consultar(isbn);
        // then
        assertNotNull(resultado);
    }

}