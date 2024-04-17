package app.AbrigoCanino.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Esta classe representa uma entidade de teste para PessoaEntity
@Entity
@Table(name = "test_pessoa") // Define o nome da tabela no banco de dados como "test_pessoa"
public class TestPessoaEntity extends PessoaEntity {

    // Construtor que recebe nome, CPF e RG para inicializar a entidade de teste
    public TestPessoaEntity(String nome, String cpf, String rg) {
        // Chama o construtor da superclasse PessoaEntity para inicializar nome, CPF e RG
        super.setNome(nome); // Define o nome da pessoa
        super.setCpf(cpf); // Define o CPF da pessoa
        super.setRg(rg); // Define o RG da pessoa
    }

    // Construtor vazio necessário para o funcionamento adequado do JPA
    public TestPessoaEntity() {
    }
}