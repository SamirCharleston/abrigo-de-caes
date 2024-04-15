package app.AbrigoCanino.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "colaboradores", schema = "public")
public class ColaboradorEntity extends AbstractEntity{
    @Column(nullable = false)
    private String matricula;

    @Column(nullable = false)
    private Permissao permissao;
}