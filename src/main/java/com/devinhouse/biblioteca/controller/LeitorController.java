package com.devinhouse.biblioteca.controller;

import com.devinhouse.biblioteca.dto.LeitorResponse;
import com.devinhouse.biblioteca.model.Leitor;
import com.devinhouse.biblioteca.service.LeitorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/leitores")
@RequiredArgsConstructor
@Slf4j
public class LeitorController {

    private final LeitorService leitorService;

    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<LeitorResponse>> consultar() {
        List<Leitor> leitores = leitorService.consultar();
        Collections.sort(leitores, Comparator.comparing(Leitor::getNome)); // ordem alfabetica
        List<LeitorResponse> resp = leitores.stream().map(l -> mapper.map(l, LeitorResponse.class)).toList();
        return ResponseEntity.ok(resp);
    }


//    @PostMapping
//    public ResponseEntity<LeitorResponse> inserir(@RequestBody @Valid LeitorRequest request) {
//        Leitor leitor = mapper.map(request, Leitor.class);
//        leitor = leitorService.incluir(leitor);
//        LeitorResponse resp = mapper.map(leitor, LeitorResponse.class);
//        return ResponseEntity.created(URI.create(leitor.getCpf().toString())).body(resp);
//    }
//
//    @DeleteMapping("{cpf}")
//    public ResponseEntity excluir(@PathVariable Long cpf) {
//        leitorService.excluir(cpf);
//        return ResponseEntity.noContent().build();
//    }

}
