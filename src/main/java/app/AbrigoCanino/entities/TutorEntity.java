package app.AbrigoCanino.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tutores", schema = "public")
public class TutorEntity extends AbstractEntity{
    @Column
    private String nome;
    @Column
    private String cpf;
    @Column(nullable = false)
    private String contato;
    @Column
    private int idade;
    @NotBlank(message = "O endereco nao pode ser em branco")
    @NotNull(message = "O endereco nao pode ser nulo")
    @Column
    private String endereco;
    @OneToMany
    @JsonIgnore
    private List<RequerimentoEntity> requerimentos;
}
