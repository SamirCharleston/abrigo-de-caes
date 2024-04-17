package app.AbrigoCanino.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "colaboradores", schema = "public")
public class ColaboradorEntity extends PessoaEntity{
    @Column(nullable = false)
    private String matricula;

    @Column
    @Enumerated(EnumType.STRING)
    private Permissao permissao;

    @Column(nullable = false)
    private String nome; // Novo campo nome

}