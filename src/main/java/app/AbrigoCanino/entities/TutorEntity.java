package app.AbrigoCanino.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tutores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TutorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @NotNull
    @NotBlank
    private String Nome;

    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    private String telefone;

}
