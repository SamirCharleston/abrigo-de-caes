package app.AbrigoCanino.entities;

import app.AbrigoCanino.repositories.AdocaoRepository;
import app.AbrigoCanino.repositories.CachorroRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "adocoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AdocaoEntity extends AbstractEntity {
    @Column(nullable = false)
    private String nome;

    @ManyToOne
    private CachorroEntity cachorro;

    @ManyToOne
    private AdocaoEntity adocao;

}
