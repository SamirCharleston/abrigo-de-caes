package app.AbrigoCanino.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PessoaEntityTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        // Configuração do validador antes de todos os testes
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() {
        // Configuração do validador antes de cada teste
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void pessoaEntity_AllAttributesValid_ShouldNotViolateConstraints() {
        // Testa se uma pessoa com todos os atributos válidos não viola as restrições de validação

        // Arrange
        // Cria uma pessoa com todos os atributos válidos
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "123.456.789-10", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void pessoaEntity_InvalidCPF_ShouldViolatePatternConstraint() {
        // Testa se uma pessoa com CPF inválido viola a restrição de padrão

        // Arrange
        // Cria uma pessoa com CPF inválido
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "123", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void pessoaEntity_NullNome_ShouldViolateNotBlankConstraint() {
        // Testa se uma pessoa com nome nulo viola a restrição de campo não vazio

        // Arrange
        // Cria uma pessoa com nome nulo
        TestPessoaEntity pessoa = new TestPessoaEntity(null, "123.456.789-10", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void pessoaEntity_NullRG_ShouldViolateNotBlankConstraint() {
        // Testa se uma pessoa com RG nulo viola a restrição de campo não vazio

        // Arrange
        // Cria uma pessoa com RG nulo
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "123.456.789-10", null);

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void pessoaEntity_ValidRG_ShouldNotViolatePatternConstraint() {
        // Testa se uma pessoa com RG válido não viola a restrição de padrão

        // Arrange
        // Cria uma pessoa com RG válido
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "123.456.789-10", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void pessoaEntity_EmptyCPF_ShouldViolateNotBlankConstraint() {
        // Testa se uma pessoa com CPF vazio viola a restrição de campo não vazio

        // Arrange
        // Cria uma pessoa com CPF vazio
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void pessoaEntity_EmptyNomeAndRG_ShouldViolateNotBlankConstraint() {
        // Testa se uma pessoa com nome e RG vazios viola a restrição de campo não vazio

        // Arrange
        // Cria uma pessoa com nome e RG vazios
        TestPessoaEntity pessoa = new TestPessoaEntity("", "123.456.789-10", "");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void pessoaEntity_ValidAttributes_ShouldNotViolateConstraints() {
        // Testa se uma pessoa com atributos válidos não viola as restrições de validação repetidamente

        // Arrange
        // Cria uma pessoa com atributos válidos
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "123.456.789-10", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertTrue(violations.isEmpty());
    }
}
