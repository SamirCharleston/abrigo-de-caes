package app.AbrigoCanino.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requerimentos", schema = "public")
public class RequerimentoEntity extends AbstractEntity {
    @NotNull(message = "O tutor nao deve ser nulo")
    @ManyToOne
    private TutorEntity autorDoRequerimento;
    @ManyToMany
    private List<CachorroEntity> caesRequeridos;
}
