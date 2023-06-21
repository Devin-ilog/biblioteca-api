package com.devinhouse.biblioteca.service;

import com.devinhouse.biblioteca.exception.LivroIndisponivelException;
import com.devinhouse.biblioteca.exception.RegistroNaoEncontradoException;
import com.devinhouse.biblioteca.model.Emprestimo;
import com.devinhouse.biblioteca.model.Livro;
import com.devinhouse.biblioteca.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final LivroService livroService;
    private final LeitorService leitorService;
    private final EmprestimoRepository emprestimoRepo;

    public List<Emprestimo> consultar() {
        return emprestimoRepo.findAll();
    }

    public Emprestimo consultar(Integer id) {
        return emprestimoRepo.findById(id)
                .orElseThrow(RegistroNaoEncontradoException::new);
    }

    @Transactional
    public Emprestimo incluir(Emprestimo emprestimo) {
        Livro livro = livroService.consultar(emprestimo.getIsbn());
        if (!livro.isDisponivel())
            throw new LivroIndisponivelException();
        emprestimo.setDataHoraEmprestimo(LocalDateTime.now());
        emprestimo = emprestimoRepo.save(emprestimo);
        livroService.indisponibilizar(emprestimo.getIsbn());
        leitorService.incrementarEmprestimosAtivos(emprestimo.getCpf());
        return emprestimo;
    }

    public void excluir(Integer id) {
        Emprestimo emprestimo = this.consultar(id);
        livroService.disponibilizar(emprestimo.getIsbn());
        leitorService.decrementarEmprestimosAtivos(emprestimo.getCpf());
        emprestimoRepo.deleteById(id);
    }

}
