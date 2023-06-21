package com.devinhouse.biblioteca.controller;

import com.devinhouse.biblioteca.dto.EmprestimoResponse;
import com.devinhouse.biblioteca.model.Emprestimo;
import com.devinhouse.biblioteca.model.Leitor;
import com.devinhouse.biblioteca.model.Livro;
import com.devinhouse.biblioteca.service.EmprestimoService;
import com.devinhouse.biblioteca.service.LeitorService;
import com.devinhouse.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/emprestimos")
@RequiredArgsConstructor
@Slf4j
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    private final LivroService livroService;
    private final LeitorService leitorService;

    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EmprestimoResponse>> consultar() {
        List<Emprestimo> emprestimos = emprestimoService.consultar();
        List<EmprestimoResponse> resp = emprestimos.stream().map(l -> mapper.map(l, EmprestimoResponse.class)).toList();
        resp.forEach(r -> {
            Livro livro = livroService.consultar(r.getIsbn());
            Leitor leitor = leitorService.consultar(r.getCpf());
            r.setTitulo(livro.getTitulo());
            r.setNome(leitor.getNome());
        });
        return ResponseEntity.ok(resp);
    }

//    @PostMapping
//    public ResponseEntity<EmprestimoResponse> inserir(@RequestBody @Valid EmprestimoRequest request) {
//        Emprestimo emprestimo = mapper.map(request, Emprestimo.class);
//        emprestimo = emprestimoService.incluir(emprestimo);
//        EmprestimoResponse resp = mapper.map(emprestimo, EmprestimoResponse.class);
//        return ResponseEntity.created(URI.create(emprestimo.getId().toString())).body(resp);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity excluir(@PathVariable Integer id) {
//        emprestimoService.excluir(id);
//        return ResponseEntity.noContent().build();
//    }

}
