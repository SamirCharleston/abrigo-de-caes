package app.AbrigoCanino.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.loader.ast.internal.AbstractEntityBatchLoader;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cachorros", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CachorroEntity extends AbstractEntity {
    @Column(nullable = false)
    @NotBlank(message = "O nome do cachorro não pode estar em branco")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "A raça do cachorro não pode estar em branco")
    private String raca;

    @Column(nullable = false)
    @NotNull(message = "A idade aproximada do cachorro não pode ser nula")
    @Min(value = 1, message = "A idade aproximada do cachorro deve ser maior que zero")
    private int idadeAproximada;

    @Column(nullable = false)
    @NotNull(message = "A descrição do cachorro não pode estar em branco")
    private boolean possuiDeficiencia;

    @Column(nullable = false)
    private String descricaoDaDeficiencia;

    @Column(nullable = false)
    private String observacaoDoAnimal;

    @Column(nullable = false)
    private Porte porte;

    @Column(nullable = false)
    private LocalDate dataDeAdocao;

    @Column(nullable = false)
    private LocalDate dataDeChegadaAoAbrigo;

    @Column(nullable = false)
    @NotBlank(message = "A descrição do cachorro não pode estar em branco")
    private String descricao;

}
