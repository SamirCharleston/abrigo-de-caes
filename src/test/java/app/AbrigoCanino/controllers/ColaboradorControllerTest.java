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

    // Mock do serviço de colaboradores
    @Mock
    private ColaboradorService colaboradorService;

    // Injeta o mock do serviço no controlador de colaboradores
    @InjectMocks
    private ColaboradorController colaboradorController;

    // Método para configurar os mocks antes de cada teste
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Teste para verificar se o salvamento de um colaborador funciona corretamente
    @Test
    void save_ColaboradorValido_DeveRetornarStatusOk() throws Exception {
        // Arrange: Preparação do ambiente de teste
        // Criar um colaborador de exemplo
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setNome("João");
        // Configurar o comportamento esperado do serviço de colaboradores
        when(colaboradorService.save(colaborador)).thenReturn("Colaborador cadastrado com sucesso.");

        // Act: Execução da ação que está sendo testada
        // Chamar o método save() do controlador de colaboradores
        ResponseEntity<ObjetoResposta<Void>> responseEntity = colaboradorController.save(colaborador);

        // Assert: Verificação dos resultados da ação
        // Verificar se o status HTTP retornado é OK (200)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Verificar se o método save() do serviço foi chamado exatamente uma vez com o colaborador de exemplo
        verify(colaboradorService, times(1)).save(colaborador);
    }

    // Teste para verificar se a busca por ID de um colaborador funciona corretamente
    @Test
    void findById_IdExistente_DeveRetornarColaborador() throws Exception {
        // Arrange
        // Preparação do ambiente de teste
        Long id = 1L;
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setId(id);
        colaborador.setNome("João");
        // Configurar o comportamento esperado do serviço de colaboradores
        when(colaboradorService.findById(id)).thenReturn(colaborador);

        // Act
        // Execução da ação que está sendo testada
        ResponseEntity<ObjetoResposta<ColaboradorEntity>> responseEntity = colaboradorController.findById(id);

        // Assert
        // Verificação dos resultados da ação
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(id, responseEntity.getBody().getObjeto().getId());
        verify(colaboradorService, times(1)).findById(id);
    }

    // Teste para verificar se a busca por todos os colaboradores funciona corretamente
    @Test
    void findAll_ColaboradoresExistem_DeveRetornarListaDeColaboradores() throws Exception {
        // Arrange
        // Preparação do ambiente de teste
        List<ColaboradorEntity> colaboradores = new ArrayList<>();
        colaboradores.add(new ColaboradorEntity());
        colaboradores.add(new ColaboradorEntity());
        // Configurar o comportamento esperado do serviço de colaboradores
        when(colaboradorService.findAll()).thenReturn(colaboradores);

        // Act
        // Execução da ação que está sendo testada
        ResponseEntity<ObjetoResposta<List<ColaboradorEntity>>> responseEntity = colaboradorController.findAll();

        // Assert
        // Verificação dos resultados da ação
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(colaboradores.size(), responseEntity.getBody().getObjeto().size());
        verify(colaboradorService, times(1)).findAll();
    }

    // Teste para verificar se a atualização de um colaborador funciona corretamente
    @Test
    void update_ColaboradorValido_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Preparação do ambiente de teste
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setId(1L);
        colaborador.setNome("Maria");
        // Configurar o comportamento esperado do serviço de colaboradores
        when(colaboradorService.update(colaborador)).thenReturn("Colaborador atualizado com sucesso.");

        // Act
        // Execução da ação que está sendo testada
        ResponseEntity<ObjetoResposta<Void>> responseEntity = colaboradorController.update(colaborador);

        // Assert
        // Verificação dos resultados da ação
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(colaboradorService, times(1)).update(colaborador);
    }

    // Teste para verificar se a exclusão de um colaborador funciona corretamente
    @Test
    void delete_IdExistente_DeveRetornarStatusOk() throws Exception {
        // Arrange
        // Preparação do ambiente de teste
        Long id = 1L;
        // Configurar o comportamento esperado do serviço de colaboradores
        when(colaboradorService.delete(id)).thenReturn("Colaborador removido com sucesso.");

        // Act
        // Execução da ação que está sendo testada
        ResponseEntity<ObjetoResposta<Void>> responseEntity = colaboradorController.delete(id);

        // Assert
        // Verificação dos resultados da ação
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(colaboradorService, times(1)).delete(id);
    }
}