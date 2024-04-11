package app.AbrigoCanino.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private Long idade;

    @NotNull
    @NotBlank
    private Long matricula;

    @NotNull
    @NotBlank
    private String cpf;

}