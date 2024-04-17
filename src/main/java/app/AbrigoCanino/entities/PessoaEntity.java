package app.AbrigoCanino.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class PessoaEntity extends AbstractEntity{
    @Column
    @NotBlank
    private String nome;
    @Column
    @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$", message = "CPF deve estar no formato xxx.xxx.xxx-xx")
    private String cpf;
    @Column
    @NotBlank
    private String rg;

}
