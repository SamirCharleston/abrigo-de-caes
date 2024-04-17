package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.controllers.TutorController;
import app.AbrigoCanino.entities.TutorEntity;
import app.AbrigoCanino.service.TutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TutorControllerTest {

    @Mock
    private TutorService tutorService;

    @InjectMocks
    private TutorController tutorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_TutorValido_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        TutorEntity tutor = new TutorEntity();
        when(tutorService.save(tutor)).thenReturn("Tutor cadastrado com sucesso.");

        // Ação (Act)
        ResponseEntity<ObjetoResposta<Void>> responseEntity = tutorController.save(tutor);

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(tutorService, times(1)).save(tutor);
    }

    @Test
    void findById_IdExistente_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        Long id = 1L;
        TutorEntity tutor = new TutorEntity();
        when(tutorService.findById(id)).thenReturn(tutor);

        // Ação (Act)
        ResponseEntity<ObjetoResposta<TutorEntity>> responseEntity = tutorController.findById(id);

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(tutorService, times(1)).findById(id);
    }

    @Test
    void findAll_TutoresExistentes_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        List<TutorEntity> tutores = new ArrayList<>();
        TutorEntity tutor1 = new TutorEntity();
        TutorEntity tutor2 = new TutorEntity();
        tutores.add(tutor1);
        tutores.add(tutor2);
        when(tutorService.findAll()).thenReturn(tutores);

        // Ação (Act)
        ResponseEntity<ObjetoResposta<List<TutorEntity>>> responseEntity = tutorController.findAll();

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(tutorService, times(1)).findAll();
    }

    @Test
    void update_TutorValido_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        TutorEntity tutor = new TutorEntity();
        when(tutorService.update(tutor)).thenReturn("Tutor atualizado com sucesso.");

        // Ação (Act)
        ResponseEntity<ObjetoResposta<Void>> responseEntity = tutorController.update(tutor);

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(tutorService, times(1)).update(tutor);
    }

    @Test
    void delete_IdExistente_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        Long id = 1L;
        when(tutorService.delete(id)).thenReturn("Tutor removido com sucesso.");

        // Ação (Act)
        ResponseEntity<ObjetoResposta<Void>> responseEntity = tutorController.delete(id);

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(tutorService, times(1)).delete(id);
    }

    @Test
    void save_TutorInvalido_DeveRetornarBadRequest() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        TutorEntity tutor = new TutorEntity();
        when(tutorService.save(tutor)).thenThrow(new IllegalArgumentException("Tutor inválido"));

        // Ação (Act)
        ResponseEntity<ObjetoResposta<Void>> responseEntity = tutorController.save(tutor);

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        verify(tutorService, times(1)).save(tutor);
    }
}