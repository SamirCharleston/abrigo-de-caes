package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.controllers.RequerimentoController;
import app.AbrigoCanino.entities.RequerimentoEntity;
import app.AbrigoCanino.service.RequerimentoService;
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

class RequerimentoControllerTest {

    @Mock
    private RequerimentoService requerimentoService;

    @InjectMocks
    private RequerimentoController requerimentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void update_RequerimentoValido_DeveRetornarStatusOk() throws Exception {
        // Arrange
        RequerimentoEntity requerimento = new RequerimentoEntity();
        requerimento.setId(1L);
        when(requerimentoService.update(requerimento)).thenReturn("Requerimento atualizado com sucesso.");

        // Act
        ResponseEntity<ObjetoResposta<Void>> responseEntity = requerimentoController.update(requerimento);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(requerimentoService, times(1)).update(requerimento);
    }

    @Test
    void delete_IdExistente_DeveRetornarStatusOk() throws Exception {
        // Arrange
        Long id = 1L;
        when(requerimentoService.delete(id)).thenReturn("Requerimento removido com sucesso.");

        // Act
        ResponseEntity<ObjetoResposta<Void>> responseEntity = requerimentoController.delete(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(requerimentoService, times(1)).delete(id);
    }

    @Test
    void save_RequerimentoValido_DeveRetornarStatusOk() throws Exception {
        // Arrange
        RequerimentoEntity requerimento = new RequerimentoEntity();
        when(requerimentoService.save(requerimento)).thenReturn("Requerimento cadastrado com sucesso.");

        // Act
        ResponseEntity<ObjetoResposta<Void>> responseEntity = requerimentoController.save(requerimento);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(requerimentoService, times(1)).save(requerimento);
    }

    @Test
    void findById_IdExistente_DeveRetornarStatusOk() throws Exception {
        // Arrange
        Long id = 1L;
        RequerimentoEntity requerimento = new RequerimentoEntity();
        when(requerimentoService.findById(id)).thenReturn(requerimento);

        // Act
        ResponseEntity<ObjetoResposta<RequerimentoEntity>> responseEntity = requerimentoController.findById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(requerimentoService, times(1)).findById(id);
    }

    @Test
    void findAll_RequerimentosExistentes_DeveRetornarStatusOk() throws Exception {
        // Arrange
        List<RequerimentoEntity> requerimentos = new ArrayList<>();
        RequerimentoEntity requerimento1 = new RequerimentoEntity();
        RequerimentoEntity requerimento2 = new RequerimentoEntity();
        requerimentos.add(requerimento1);
        requerimentos.add(requerimento2);
        when(requerimentoService.findAll()).thenReturn(requerimentos);

        // Act
        ResponseEntity<ObjetoResposta<List<RequerimentoEntity>>> responseEntity = requerimentoController.findAll();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(requerimentoService, times(1)).findAll();
    }
}
