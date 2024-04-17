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
    void cachorroEntity_AllAttributesValid_ShouldNotViolateConstraints() {
        // Arrange
        // Cria um cachorro com todos os atributos válidos
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
        // Cria um cachorro com atributos válidos
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
        // Cria um cachorro com raça nula, violando a restrição NotBlank
        CachorroEntity cachorro = new CachorroEntity(
                "Rex",
                null, // Raca é nula
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
        // Cria um cachorro com idade aproximada inválida, violando a restrição Min
        CachorroEntity cachorro = new CachorroEntity(
                "Rex",
                "Labrador",
                0, // Idade aproximada inválida (menor que 1)
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
        // Cria um cachorro e define uma descrição não vazia
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
        // Cria um cachorro e define que possui deficiência
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setPossuiDeficiencia(true);

        // Act
        boolean possuiDeficienciaNotEmpty = cachorro.isPossuiDeficiencia();

        // Assert
        assertTrue(possuiDeficienciaNotEmpty, "O campo 'possuiDeficiencia' não deve estar vazio.");
    }

}