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

    @PersistenceContext
    private EntityManager entityManager;

    private AbstractEntity entity;

    @BeforeEach
    void setUp() {
        entity = new TestEntity();
    }


    @Test
    void testSetStatus() {
        // Arrange
        AbstractEntity entity = new TestEntity();
        entity.setStatus(false);

        // Act
        boolean status = entity.isStatus();

        // Assert
        assertFalse(status);
    }

    @Test
    void testSetId() {
        // Arrange
        AbstractEntity entity = new TestEntity();
        Long newId = 123L;

        // Act
        entity.setId(newId);

        // Assert
        assertEquals(newId, entity.getId());
    }

    private static class TestEntity extends AbstractEntity {
        // Empty class, using for testing purposes
    }


    @Test
    void testEquals() {
        // Arrange
        entity.setId(1L);
        AbstractEntity otherEntity = new TestEntity();
        otherEntity.setId(1L);

        // Act & Assert
        assertEquals(entity, otherEntity);
    }

    @Test
    void testNotEquals() {
        // Arrange
        entity.setId(1L);
        AbstractEntity otherEntity = new TestEntity();
        otherEntity.setId(2L);

        // Act & Assert
        assertNotEquals(entity, otherEntity);
    }

    @Test
    void testHashCode() {
        // Arrange
        entity.setId(1L);
        AbstractEntity otherEntity = new TestEntity();
        otherEntity.setId(1L);

        // Act & Assert
        assertEquals(entity.hashCode(), otherEntity.hashCode());
    }

}
