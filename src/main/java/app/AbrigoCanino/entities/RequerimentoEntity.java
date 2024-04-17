package app.AbrigoCanino.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@Table(name = "requerimentos", schema = "public")
public class RequerimentoEntity extends AbstractEntity {
    @NotNull(message = "O tutor nao deve ser nulo")
    @ManyToOne
    private TutorEntity autorDoRequerimento;
    @NotNull(message = "A lista de caes nao deve ser nula")
    @ManyToMany
    private List<CachorroEntity> caesRequeridos;
}
