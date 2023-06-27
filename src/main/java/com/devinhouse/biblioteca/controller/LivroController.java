package com.devinhouse.biblioteca.controller;

import com.devinhouse.biblioteca.dto.LivroResponse;
import com.devinhouse.biblioteca.model.Livro;
import com.devinhouse.biblioteca.service.LivroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
@Slf4j
public class LivroController {

    private final LivroService livroService;

    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<LivroResponse>> consultar() {
        List<Livro> livros = livroService.consultar();
        List<LivroResponse> resp = livros.stream().map(l -> mapper.map(l, LivroResponse.class)).toList();
        return ResponseEntity.ok(resp);
    }

}
