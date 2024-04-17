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

    // Mock do serviço de requerimentos
    @Mock
    private RequerimentoService requerimentoService;

    // Injeta o mock do serviço no controlador de requerimentos
    @InjectMocks
    private RequerimentoController requerimentoController;

    // Método para configurar os mocks antes de cada teste
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Teste para verificar se a atualização de um requerimento funciona corretamente
    @Test
    void update_RequerimentoValido_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        RequerimentoEntity requerimento = new RequerimentoEntity();
        requerimento.setId(1L);

        // Configuração do comportamento esperado do serviço mock
        when(requerimentoService.update(requerimento)).thenReturn("Requerimento atualizado com sucesso.");

        // Ação (Act)
        ResponseEntity<ObjetoResposta<Void>> responseEntity = requerimentoController.update(requerimento);

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(requerimentoService, times(1)).update(requerimento);
    }

    // Teste para verificar se a exclusão de um requerimento funciona corretamente
    @Test
    void delete_IdExistente_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        Long id = 1L;

        // Configuração do comportamento esperado do serviço mock
        when(requerimentoService.delete(id)).thenReturn("Requerimento removido com sucesso.");

        // Ação (Act)
        ResponseEntity<ObjetoResposta<Void>> responseEntity = requerimentoController.delete(id);

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(requerimentoService, times(1)).delete(id);
    }

    // Teste para verificar se o salvamento de um requerimento funciona corretamente
    @Test
    void save_RequerimentoValido_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        RequerimentoEntity requerimento = new RequerimentoEntity();

        // Configuração do comportamento esperado do serviço mock
        when(requerimentoService.save(requerimento)).thenReturn("Requerimento cadastrado com sucesso.");

        // Ação (Act)
        ResponseEntity<ObjetoResposta<Void>> responseEntity = requerimentoController.save(requerimento);

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(requerimentoService, times(1)).save(requerimento);
    }

    // Teste para verificar se a busca por ID de um requerimento funciona corretamente
    @Test
    void findById_IdExistente_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        Long id = 1L;
        RequerimentoEntity requerimento = new RequerimentoEntity();

        // Configuração do comportamento esperado do serviço mock
        when(requerimentoService.findById(id)).thenReturn(requerimento);

        // Ação (Act)
        ResponseEntity<ObjetoResposta<RequerimentoEntity>> responseEntity = requerimentoController.findById(id);

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(requerimentoService, times(1)).findById(id);
    }

    // Teste para verificar se a busca por todos os requerimentos funciona corretamente
    @Test
    void findAll_RequerimentosExistentes_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Configuração do cenário (Arrange)
        List<RequerimentoEntity> requerimentos = new ArrayList<>();
        RequerimentoEntity requerimento1 = new RequerimentoEntity();
        RequerimentoEntity requerimento2 = new RequerimentoEntity();
        requerimentos.add(requerimento1);
        requerimentos.add(requerimento2);

        // Configuração do comportamento esperado do serviço mock
        when(requerimentoService.findAll()).thenReturn(requerimentos);

        // Ação (Act)
        ResponseEntity<ObjetoResposta<List<RequerimentoEntity>>> responseEntity = requerimentoController.findAll();

        // Assert
        // Verificação dos resultados da ação (Assert)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(requerimentoService, times(1)).findAll();
    }
}
