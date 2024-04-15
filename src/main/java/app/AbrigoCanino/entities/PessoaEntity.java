package app.AbrigoCanino.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PessoaEntity extends AbstractEntity{
    @Column
    private String nome;
    @Column
    private String cpf;
    @Column
    private String rg;
}
