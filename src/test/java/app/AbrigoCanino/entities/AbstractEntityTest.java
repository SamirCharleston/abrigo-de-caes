package app.AbrigoCanino.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AbstractEntityTest {

    // Injeção do EntityManager para acessar o contexto de persistência
    @PersistenceContext
    private EntityManager entityManager;

    // Entidade abstrata utilizada para testes
    private AbstractEntity entity;

    // Configuração inicial antes de cada teste
    @BeforeEach
    void setUp() {
        // Cria uma nova instância da entidade para cada teste
        entity = new TestEntity();
    }


    @Test
    void testSetStatus() {
        // Arrange
        // Criação de uma instância da entidade de teste
        AbstractEntity entity = new TestEntity();
        // Define o status como falso
        entity.setStatus(false);

        // Act
        // Obtém o status da entidade
        boolean status = entity.isStatus();

        // Assert
        // Verifica se o status é falso
        assertFalse(status);
    }

    @Test
    void testSetId() {
        // Arrange
        // Criação de uma instância da entidade de teste
        AbstractEntity entity = new TestEntity();
        // Define um novo ID
        Long newId = 123L;

        // Act
        // Define o ID da entidade
        entity.setId(newId);

        // Assert
        // Verifica se o ID definido é igual ao novo ID
        assertEquals(newId, entity.getId());
    }

    // Classe de teste concreta que estende a entidade abstrata para fins de teste
    private static class TestEntity extends AbstractEntity {
        // Classe vazia, utilizada apenas para fins de teste
    }


    @Test
    void testEquals() {
        // Arrange
        // Define o ID da entidade
        entity.setId(1L);
        // Cria uma nova instância de outra entidade com o mesmo ID
        AbstractEntity otherEntity = new TestEntity();
        otherEntity.setId(1L);

        // Act & Assert
        // Verifica se as duas entidades são iguais
        assertEquals(entity, otherEntity);
    }

    @Test
    void testNotEquals() {
        // Arrange
        // Define o ID da entidade
        entity.setId(1L);
        // Cria uma nova instância de outra entidade com um ID diferente
        AbstractEntity otherEntity = new TestEntity();
        otherEntity.setId(2L);

        // Act & Assert
        // Verifica se as duas entidades não são iguais
        assertNotEquals(entity, otherEntity);
    }

    @Test
    void testHashCode() {
        // Arrange
        // Define o ID da entidade
        entity.setId(1L);
        // Cria uma nova instância de outra entidade com o mesmo ID
        AbstractEntity otherEntity = new TestEntity();
        otherEntity.setId(1L);

        // Act & Assert
        // Verifica se os hashcodes das duas entidades são iguais
        assertEquals(entity.hashCode(), otherEntity.hashCode());
    }

}
