package com.alura.forum_hub.controller;

import com.alura.forum_hub.domain.topico.*;
import com.alura.forum_hub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository repository;

    @GetMapping
    public Page<DadosListarTopico> listar(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue (paginacao).map(DadosListarTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosRetornoIdTopico(topico));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosRetornoDetalhadoTopico> cadastrar
            (@RequestBody @Valid DadosTopico dados, UriComponentsBuilder uriBilder, @AuthenticationPrincipal Usuario usuario){
        var topico = new Topico(dados, usuario);
        repository.save(topico);
        var uri = uriBilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosRetornoDetalhadoTopico(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosRetornoDetalhadoTopico> atualizar(@RequestBody @Valid DadosAtualizarTopicos dados) {
        var topico = repository.getReferenceById(dados.id());
        topico.atualizarInformacoesTopico(dados);
        return ResponseEntity.ok(new DadosRetornoDetalhadoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosRetornoDetalhadoTopico> desabilitar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        topico.desabilitarTopico();
        return ResponseEntity.ok(new DadosRetornoDetalhadoTopico(topico));
    }

    @PutMapping("/{id}/ativo")
    @Transactional
    public ResponseEntity<DadosRetornoDetalhadoTopico> desabilitar2(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        topico.habilitarTopico();
        return ResponseEntity.ok(new DadosRetornoDetalhadoTopico(topico));
    }
}
