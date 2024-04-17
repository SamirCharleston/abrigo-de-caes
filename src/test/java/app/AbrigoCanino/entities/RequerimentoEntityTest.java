package app.AbrigoCanino.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RequerimentoEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testAutorDoRequerimento() {
        // Arrange
        TutorEntity tutor = new TutorEntity();
        tutor.setNome("João");

        RequerimentoEntity requerimento = new RequerimentoEntity();
        requerimento.setAutorDoRequerimento(tutor);

        // Act
        TutorEntity autor = requerimento.getAutorDoRequerimento();

        // Assert
        assertNotNull(autor);
        assertEquals("João", autor.getNome());
    }

    @Test
    void testCaesRequeridos() {
        // Arrange
        CachorroEntity cachorro1 = new CachorroEntity();
        cachorro1.setNome("Rex");

        CachorroEntity cachorro2 = new CachorroEntity();
        cachorro2.setNome("Bolt");

        RequerimentoEntity requerimento = new RequerimentoEntity();
        requerimento.setCaesRequeridos(Arrays.asList(cachorro1, cachorro2));

        // Act
        List<CachorroEntity> caes = requerimento.getCaesRequeridos();

        // Assert
        assertNotNull(caes);
        assertEquals(2, caes.size());
        assertEquals("Rex", caes.get(0).getNome());
        assertEquals("Bolt", caes.get(1).getNome());
    }

    @Test
    void testGetSetAutorDoRequerimento() {
        // Arrange
        RequerimentoEntity requerimentoEntity = new RequerimentoEntity();
        TutorEntity tutorEntity = new TutorEntity();

        // Act
        requerimentoEntity.setAutorDoRequerimento(tutorEntity);

        // Assert
        assertEquals(tutorEntity, requerimentoEntity.getAutorDoRequerimento());
    }

    @Test
    void testGetSetCaesRequeridos() {
        // Arrange
        RequerimentoEntity requerimentoEntity = new RequerimentoEntity();
        CachorroEntity cachorroEntity = new CachorroEntity();
        List<CachorroEntity> cachorros = new ArrayList<>();
        cachorros.add(cachorroEntity);

        // Act
        requerimentoEntity.setCaesRequeridos(cachorros);

        // Assert
        assertEquals(cachorros, requerimentoEntity.getCaesRequeridos());
    }

    @Test
    void testNoArgsConstructor() {
        // Act
        RequerimentoEntity requerimentoEntity = new RequerimentoEntity();

        // Assert
        assertNotNull(requerimentoEntity);
    }

    @Test
    void testId() {
        // Arrange
        RequerimentoEntity requerimento = new RequerimentoEntity();
        requerimento.setId(1L);

        // Act
        Long id = requerimento.getId();

        // Assert
        assertEquals(1L, id);
    }


    @Test
    void testEquals() {
        // Arrange
        TutorEntity tutor1 = new TutorEntity();
        tutor1.setId(1L);

        TutorEntity tutor2 = new TutorEntity();
        tutor2.setId(1L);

        RequerimentoEntity requerimento1 = new RequerimentoEntity();
        requerimento1.setAutorDoRequerimento(tutor1);

        RequerimentoEntity requerimento2 = new RequerimentoEntity();
        requerimento2.setAutorDoRequerimento(tutor2);

        // Assert
        assertTrue(requerimento1.equals(requerimento2));
        assertEquals(requerimento1.hashCode(), requerimento2.hashCode());
    }


    @Test
    void testNullAutorDoRequerimento() {
        // Arrange
        RequerimentoEntity requerimento = new RequerimentoEntity();

        // Act
        TutorEntity autor = requerimento.getAutorDoRequerimento();

        // Assert
        assertNull(autor);
    }



}