package com.alura.forum_hub.domain.topico;

import com.alura.forum_hub.domain.curso.Curso;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTopicos(
        @NotNull
        Long id,
        String titulo,
        String mensagem,
        Curso curso
) {
}
