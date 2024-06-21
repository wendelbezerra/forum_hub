package com.alura.forum_hub.domain.topico;

import com.alura.forum_hub.domain.curso.Curso;

import java.time.LocalDateTime;

public record DadosListarTopico(
        Long id,
        String titulo,
        String mensagem,
        Curso curso,
        LocalDateTime data
) {
    public DadosListarTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso(), topico.getData());
    }
}
