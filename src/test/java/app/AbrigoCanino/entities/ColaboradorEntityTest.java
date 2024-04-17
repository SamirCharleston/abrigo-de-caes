package app.AbrigoCanino.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.AbrigoCanino.entities.Permissao;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ColaboradorEntityTest {

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
    void testColaboradorEntity_ValidAttributes_ShouldNotViolateConstraints() {
        // Arrange
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setMatricula("12345");
        colaborador.setPermissao(Permissao.ADMIN);
        colaborador.setNome("Fulano");

        // Act
        Set<ConstraintViolation<ColaboradorEntity>> violations = validator.validate(colaborador);

        // Assert
        assertTrue(violations.isEmpty(), "Não deve violar nenhuma restrição de validação.");
    }


    @Test
    void testColaboradorEntity_NullPermissao_ShouldNotViolateConstraints() {
        // Arrange
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setMatricula("67890");
        colaborador.setNome("Ciclano");

        // Act
        Set<ConstraintViolation<ColaboradorEntity>> violations = validator.validate(colaborador);

        // Assert
        assertTrue(violations.isEmpty(), "Não deve violar nenhuma restrição de validação.");
    }



    @Test
    void testSetMatricula() {
        // Arrange
        ColaboradorEntity colaborador = new ColaboradorEntity();
        String matricula = "654321";

        // Act
        colaborador.setMatricula(matricula);

        // Assert
        assertEquals(matricula, colaborador.getMatricula());
    }

    @Test
    void testSetPermissao() {
        // Arrange
        ColaboradorEntity colaborador = new ColaboradorEntity();
        Permissao permissao = Permissao.ADMIN;

        // Act
        colaborador.setPermissao(permissao);

        // Assert
        assertEquals(permissao, colaborador.getPermissao());
    }

    @Test
    void testSetNome() {
        // Arrange
        ColaboradorEntity colaborador = new ColaboradorEntity();
        String nome = "Maria";

        // Act
        colaborador.setNome(nome);

        // Assert
        assertEquals(nome, colaborador.getNome());
    }

    @Test
    void testToString() {
        // Arrange
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setMatricula("123456");
        colaborador.setNome("Carlos");
        colaborador.setPermissao(Permissao.ADMIN);

        // Act
        String result = colaborador.toString();

        // Assert
        String expected = "ColaboradorEntity(matricula=123456, permissao=ADMIN, nome=Carlos)";
        assertEquals(expected, result);
    }

    @Test
    void testSetAndGetId() {
        // Arrange
        ColaboradorEntity colaborador = new ColaboradorEntity();
        Long id = 1L;

        // Act
        colaborador.setId(id);

        // Assert
        assertEquals(id, colaborador.getId());
    }



}
