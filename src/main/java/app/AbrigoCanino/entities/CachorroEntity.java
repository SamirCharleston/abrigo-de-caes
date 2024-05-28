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
    @Column
    private String nome;

    @Column
    private String raca;

    @Column
    private int idadeAproximada;

    @Column
    @Enumerated(EnumType.STRING)
    private Porte porte;

    @Column
    private LocalDate dataDeAdocao;
    @Column
    @NotBlank(message = "A descrição do cachorro não pode estar em branco")
    private String descricao;
}
