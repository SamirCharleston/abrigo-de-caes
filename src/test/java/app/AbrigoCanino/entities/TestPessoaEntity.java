package app.AbrigoCanino.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_pessoa")
public class TestPessoaEntity extends PessoaEntity {

    public TestPessoaEntity(String nome, String cpf, String rg) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setRg(rg);
    }

    // Precisamos adicionar um construtor vazio para JPA
    public TestPessoaEntity() {
    }
}