package app.AbrigoCanino.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private boolean status;
    @Column
    private LocalDateTime dataDaCriacao;
    @Column
    private LocalDateTime dataDeAtualizacao;
    @PrePersist
    public void prePersist() {
        this.dataDaCriacao = LocalDateTime.now();
        this.status = true;
    }
    @PreUpdate
    public void preUpdate() {
        this.dataDeAtualizacao = LocalDateTime.now();
    }
}
