package com.devinhouse.biblioteca.service;

import com.devinhouse.biblioteca.exception.RegistroNaoEncontradoException;
import com.devinhouse.biblioteca.model.Livro;
import com.devinhouse.biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepo;

    public List<Livro> consultar() {
        return livroRepo.findAll();
    }

    public Livro consultar(String isbn) {
        return livroRepo.findById(isbn)
                .orElseThrow(RegistroNaoEncontradoException::new);
    }

    public void excluir(String isbn) {
        if (!livroRepo.existsById(isbn))
            throw new RegistroNaoEncontradoException();
        livroRepo.deleteById(isbn);
    }

    public void indisponibilizar(String isbn) {
        Livro livro = this.consultar(isbn);
        livro.setDisponivel(false);
        livroRepo.save(livro);
    }

    public void disponibilizar(String isbn) {
        Livro livro = this.consultar(isbn);
        livro.setDisponivel(true);
        livroRepo.save(livro);
    }

}
