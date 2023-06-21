package com.devinhouse.biblioteca.service;

import com.devinhouse.biblioteca.exception.LeitorComEmprestimosException;
import com.devinhouse.biblioteca.exception.RegistroExistenteException;
import com.devinhouse.biblioteca.exception.RegistroNaoEncontradoException;
import com.devinhouse.biblioteca.model.Leitor;
import com.devinhouse.biblioteca.repository.LeitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeitorService {

    private final LeitorRepository leitorRepo;
//    private final EmprestimoRepository emprestimoRepo;

    public List<Leitor> consultar() {
        return leitorRepo.findAll();
    }

    public Leitor consultar(Long cpf) {
        return leitorRepo.findById(cpf)
                .orElseThrow(RegistroNaoEncontradoException::new);
    }

    public Leitor incluir(Leitor leitor) {
        if (leitorRepo.existsById(leitor.getCpf()))
            throw new RegistroExistenteException();
        leitor = leitorRepo.save(leitor);
        return leitor;
    }

    public void excluir(Long cpf) {
        Leitor leitor = this.consultar(cpf);
        boolean possuiEmprestimosAtivos = leitor.getQtdEmprestimos() > 0;
        if (possuiEmprestimosAtivos)
            throw new LeitorComEmprestimosException();
        leitorRepo.deleteById(cpf);
    }

    public void incrementarEmprestimosAtivos(Long cpf) {
        Leitor leitor = this.consultar(cpf);
        leitor.setQtdEmprestimos( leitor.getQtdEmprestimos() + 1 );
        leitorRepo.save(leitor);
    }

    public void decrementarEmprestimosAtivos(Long cpf) {
        Leitor leitor = this.consultar(cpf);
        leitor.setQtdEmprestimos( leitor.getQtdEmprestimos() - 1 );
        leitorRepo.save(leitor);
    }

}
