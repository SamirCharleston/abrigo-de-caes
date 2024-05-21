package app.AbrigoCanino.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requerimentos", schema = "public")
public class RequerimentoEntity extends AbstractEntity {
    @NotNull(message = "A data nao deve ser nula")
    @Column
    private LocalDate dataDoRequerimento;
    @NotNull(message = "O tutor nao deve ser nulo")
    @ManyToOne
    private TutorEntity autorDoRequerimento;
    @ManyToMany
    private List<CachorroEntity> caesRequeridos;
}