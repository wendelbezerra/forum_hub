package com.alura.forum_hub.domain.topico;

import com.alura.forum_hub.domain.curso.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public record DadosRetornoIdTopico(
       Long id,
       @NotBlank
       String titulo,
       @NotBlank
       String mensagem,
       @NotNull
       Curso curso,
       @CreatedDate
       LocalDateTime data,
       @NotNull
       Boolean ativo,
       @NotNull
       Long autor
) {
    public DadosRetornoIdTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso(), topico.getData(), topico.getAtivo(), topico.getAutor().getId());
    }
}