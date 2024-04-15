package app.AbrigoCanino.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@Table(name = "requerimentos", schema = "public")
public class RequerimentoEntity extends AbstractEntity {
    @ManyToOne
    private TutorEntity autorDoRequerimento;
    @ManyToMany
    private List<CachorroEntity> caesRequeridos;
}
