package app.AbrigoCanino.entities;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


// Classe de teste para TutorEntity
public class TutorEntityTest {

    // Teste para verificar a igualdade entre dois objetos TutorEntity
    @Test
    void testEquals() {
        // Arrange
        LocalDate dataRequerimento = LocalDate.of(2022, 4, 15);
        String contato = "123456789";
        List<RequerimentoEntity> requerimentos = new ArrayList<>();

        TutorEntity tutor1 = new TutorEntity();
        tutor1.setDataRequerimento(dataRequerimento);
        tutor1.setContato(contato);
        tutor1.setRequerimentos(requerimentos);

        TutorEntity tutor2 = new TutorEntity();
        tutor2.setDataRequerimento(dataRequerimento);
        tutor2.setContato(contato);
        tutor2.setRequerimentos(requerimentos);

        // Act & Assert
        assertEquals(tutor1, tutor2);
    }

    // Teste para verificar a diferença entre dois objetos TutorEntity
    @Test
    void testNotEquals() {
        // Arrange
        TutorEntity tutor1 = new TutorEntity();
        tutor1.setDataRequerimento(LocalDate.of(2022, 4, 15));
        tutor1.setContato("123456789");
        tutor1.setRequerimentos(new ArrayList<>());

        TutorEntity tutor2 = new TutorEntity();
        tutor2.setDataRequerimento(LocalDate.of(2023, 5, 20));
        tutor2.setContato("987654321");
        tutor2.setRequerimentos(new ArrayList<>());

        // Act & Assert
        assertNotEquals(tutor1, tutor2);
    }

    // Teste para verificar o hashCode de dois objetos TutorEntity iguais
    @Test
    void testHashCodeEqualObjects() {
        // Arrange
        LocalDate dataRequerimento = LocalDate.of(2022, 4, 15);
        String contato = "123456789";
        List<RequerimentoEntity> requerimentos = new ArrayList<>();

        TutorEntity tutor1 = new TutorEntity();
        tutor1.setDataRequerimento(dataRequerimento);
        tutor1.setContato(contato);
        tutor1.setRequerimentos(requerimentos);

        TutorEntity tutor2 = new TutorEntity();
        tutor2.setDataRequerimento(dataRequerimento);
        tutor2.setContato(contato);
        tutor2.setRequerimentos(requerimentos);

        // Act & Assert
        assertEquals(tutor1.hashCode(), tutor2.hashCode());
    }

    // Teste para verificar o hashCode de dois objetos TutorEntity diferentes
    @Test
    void testHashCodeDifferentObjects() {
        // Arrange
        TutorEntity tutor1 = new TutorEntity();
        tutor1.setDataRequerimento(LocalDate.of(2022, 4, 15));
        tutor1.setContato("123456789");
        tutor1.setRequerimentos(new ArrayList<>());

        TutorEntity tutor2 = new TutorEntity();
        tutor2.setDataRequerimento(LocalDate.of(2023, 5, 20));
        tutor2.setContato("987654321");
        tutor2.setRequerimentos(new ArrayList<>());

        // Act & Assert
        assertNotEquals(tutor1.hashCode(), tutor2.hashCode());
    }
}