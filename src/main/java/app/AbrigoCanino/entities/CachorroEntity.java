package app.AbrigoCanino.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "cachorros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CachorroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String descricao;


}
