package app.AbrigoCanino.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table(name = "cachorros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CachorroEntity extends AbstractEntity {
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String raca;

    @Column(nullable = false)
    private int idadeAproximada;

    @Column(nullable = false)
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
    private String descricao;

}
