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
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void pessoaEntity_AllAttributesValid_ShouldNotViolateConstraints() {
        // Arrange
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "123.456.789-10", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void pessoaEntity_InvalidCPF_ShouldViolatePatternConstraint() {
        // Arrange
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "123", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void pessoaEntity_NullNome_ShouldViolateNotBlankConstraint() {
        // Arrange
        TestPessoaEntity pessoa = new TestPessoaEntity(null, "123.456.789-10", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void pessoaEntity_NullRG_ShouldViolateNotBlankConstraint() {
        // Arrange
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "123.456.789-10", null);

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertFalse(violations.isEmpty());
    }
    @Test
    void pessoaEntity_ValidRG_ShouldNotViolatePatternConstraint() {
        // Arrange
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "123.456.789-10", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertTrue(violations.isEmpty());
    }


    @Test
    void pessoaEntity_EmptyCPF_ShouldViolateNotBlankConstraint() {
        // Arrange
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void pessoaEntity_EmptyNomeAndRG_ShouldViolateNotBlankConstraint() {
        // Arrange
        TestPessoaEntity pessoa = new TestPessoaEntity("", "123.456.789-10", "");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertFalse(violations.isEmpty());
    }


    @Test
    void pessoaEntity_ValidAttributes_ShouldNotViolateConstraints() {
        // Arrange
        TestPessoaEntity pessoa = new TestPessoaEntity("Fulano", "123.456.789-10", "123456789");

        // Act
        Set<ConstraintViolation<TestPessoaEntity>> violations = validator.validate(pessoa);

        // Assert
        assertTrue(violations.isEmpty());
    }
}
