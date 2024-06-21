package com.alura.forum_hub.domain.topico;

import com.alura.forum_hub.domain.curso.Curso;
import com.alura.forum_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;

    @Enumerated(EnumType.STRING)
    private Curso curso;

    @CreatedDate
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(columnDefinition = "autor_id",referencedColumnName = "id")
    private Usuario autor;

    private Boolean ativo;

    public Topico(DadosTopico dados, Usuario usuario) {
        this.ativo = true;
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.curso = dados.curso();
        this.data = LocalDateTime.now();
        this.autor = usuario;
    }


    public void atualizarInformacoesTopico(DadosAtualizarTopicos dados) {
        if(dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if(dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if(dados.curso() != null) {
            this.curso = dados.curso();
        }
    }

    public void desabilitarTopico(){
        this.ativo = false;
    }

    public void habilitarTopico() {
        this.ativo = true;
    }
}
