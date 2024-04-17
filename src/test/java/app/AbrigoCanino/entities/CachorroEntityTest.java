package app.AbrigoCanino.entities;

import app.AbrigoCanino.entities.CachorroEntity;
import app.AbrigoCanino.entities.Porte;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CachorroEntityTest {

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
    void cachorroEntity_AllAttributesValid_ShouldNotViolateConstraints() {
        // Arrange
        CachorroEntity cachorro = new CachorroEntity(
                "Belinha",
                "Vira-lata",
                2,
                false,
                "",
                "Cachorro dócil e brincalhão",
                Porte.PEQUENO,
                LocalDate.now(),
                LocalDate.now().minusMonths(1),
                "Cachorrinha muito amigável"
        );

        // Act
        var violations = validator.validate(cachorro);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void cachorroEntity_ValidAttributes_ShouldNotViolateConstraints() {
        // Arrange
        CachorroEntity cachorro = new CachorroEntity(
                "Rex",
                "Labrador",
                3,
                false,
                "",
                "Cachorro amigável e brincalhão",
                Porte.MEDIO,
                LocalDate.now(),
                LocalDate.now().minusMonths(2),
                "Labrador marrom"
        );

        // Act
        Set violations = validator.validate(cachorro);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void cachorroEntity_NullRaca_ShouldViolateNotBlankConstraint() {
        // Arrange
        CachorroEntity cachorro = new CachorroEntity(
                "Rex",
                null, // Raca is null
                3,
                false,
                "",
                "Cachorro amigável e brincalhão",
                Porte.MEDIO,
                LocalDate.now(),
                LocalDate.now().minusMonths(2),
                "Labrador marrom"
        );

        // Act
        Set violations = validator.validate(cachorro);

        // Assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void cachorroEntity_InvalidIdadeAproximada_ShouldViolateMinConstraint() {
        // Arrange
        CachorroEntity cachorro = new CachorroEntity(
                "Rex",
                "Labrador",
                0, // Invalid idadeAproximada (less than 1)
                false,
                "",
                "Cachorro amigável e brincalhão",
                Porte.MEDIO,
                LocalDate.now(),
                LocalDate.now().minusMonths(2),
                "Labrador marrom"
        );

        // Act
        Set violations = validator.validate(cachorro);

        // Assert
        assertFalse(violations.isEmpty());
    }

    @Test
    void cachorroEntity_NonEmptyDescricao_ShouldNotBeEmpty() {
        // Arrange
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setDescricao("Descrição do cachorro");

        // Act
        boolean descricaoNotEmpty = !cachorro.getDescricao().isEmpty();

        // Assert
        assertTrue(descricaoNotEmpty, "A descrição do cachorro não deve estar vazia.");
    }

    @Test
    void cachorroEntity_NonEmptyPossuiDeficiencia_ShouldNotBeEmpty() {
        // Arrange
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setPossuiDeficiencia(true);

        // Act
        boolean possuiDeficienciaNotEmpty = cachorro.isPossuiDeficiencia();

        // Assert
        assertTrue(possuiDeficienciaNotEmpty, "O campo 'possuiDeficiencia' não deve estar vazio.");
    }

}