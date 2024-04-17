package app.AbrigoCanino.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "tutores", schema = "public")
public class TutorEntity extends PessoaEntity{
    @Column
    private LocalDate dataRequerimento;
    @Column(nullable = false)
    private String contato;
    @Column(nullable = false)
    private int idade;
    @Column
    private String endereco;
    @OneToMany
    private List<RequerimentoEntity> requerimentos;
}
