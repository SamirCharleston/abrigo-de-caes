package app.AbrigoCanino.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.loader.ast.internal.AbstractEntityBatchLoader;
import org.springframework.boot.autoconfigure.web.WebProperties;

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
    private String descricao;

}
