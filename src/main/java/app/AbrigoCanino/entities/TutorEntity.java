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

   @Column(nullable = false)
    private String Nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

}
