package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.entities.ColaboradorEntity;
import app.AbrigoCanino.service.ColaboradorService;
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

class ColaboradorControllerTest {

    @Mock
    private ColaboradorService colaboradorService;

    @InjectMocks
    private ColaboradorController colaboradorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ColaboradorValido_DeveRetornarStatusOk() throws Exception {
        // Arrange
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setNome("João");
        when(colaboradorService.save(colaborador)).thenReturn("Colaborador cadastrado com sucesso.");

        // Act
        ResponseEntity<ObjetoResposta<Void>> responseEntity = colaboradorController.save(colaborador);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(colaboradorService, times(1)).save(colaborador);
    }

    @Test
    void findById_IdExistente_DeveRetornarColaborador() throws Exception {
        // Arrange
        Long id = 1L;
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setId(id);
        colaborador.setNome("João");
        when(colaboradorService.findById(id)).thenReturn(colaborador);

        // Act
        ResponseEntity<ObjetoResposta<ColaboradorEntity>> responseEntity = colaboradorController.findById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(id, responseEntity.getBody().getObjeto().getId());
        verify(colaboradorService, times(1)).findById(id);
    }

    @Test
    void findAll_ColaboradoresExistem_DeveRetornarListaDeColaboradores() throws Exception {
        // Arrange
        List<ColaboradorEntity> colaboradores = new ArrayList<>();
        colaboradores.add(new ColaboradorEntity());
        colaboradores.add(new ColaboradorEntity());
        when(colaboradorService.findAll()).thenReturn(colaboradores);

        // Act
        ResponseEntity<ObjetoResposta<List<ColaboradorEntity>>> responseEntity = colaboradorController.findAll();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(colaboradores.size(), responseEntity.getBody().getObjeto().size());
        verify(colaboradorService, times(1)).findAll();
    }

    @Test
    void update_ColaboradorValido_DeveRetornarStatusOk() throws Exception {
        // Arrange
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setId(1L);
        colaborador.setNome("Maria");
        when(colaboradorService.update(colaborador)).thenReturn("Colaborador atualizado com sucesso.");

        // Act
        ResponseEntity<ObjetoResposta<Void>> responseEntity = colaboradorController.update(colaborador);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(colaboradorService, times(1)).update(colaborador);
    }

    @Test
    void delete_IdExistente_DeveRetornarStatusOk() throws Exception {
        // Arrange
        Long id = 1L;
        when(colaboradorService.delete(id)).thenReturn("Colaborador removido com sucesso.");

        // Act
        ResponseEntity<ObjetoResposta<Void>> responseEntity = colaboradorController.delete(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(colaboradorService, times(1)).delete(id);
    }
}