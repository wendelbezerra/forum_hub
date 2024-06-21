package com.alura.forum_hub.domain.topico;

import com.alura.forum_hub.domain.curso.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public record DadosRetornoDetalhadoTopico(
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        Curso curso,
        @CreatedDate
        LocalDateTime data
) {
    public DadosRetornoDetalhadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso(), LocalDateTime.now());
    }
}
