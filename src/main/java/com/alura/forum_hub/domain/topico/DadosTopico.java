package com.alura.forum_hub.domain.topico;

import com.alura.forum_hub.domain.curso.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        Curso curso
) {
}
