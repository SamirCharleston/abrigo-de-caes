package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.entities.ColaboradorEntity;
import app.AbrigoCanino.repositories.ColaboradorRepository;
import app.AbrigoCanino.service.ColaboradorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@SpringBootTest
class ColaboradorControllerTest {
    @MockBean
    ColaboradorRepository colaboradorRepository;

    // Mock do serviço de colaboradores
    @MockBean
    private ColaboradorService colaboradorService;

    // Injeta o mock do serviço no controlador de colaboradores
    @Autowired
    private ColaboradorController colaboradorController;

    ColaboradorEntity colaborador1;
    List<ColaboradorEntity> colaboradores;

    ColaboradorControllerTest(){
        ColaboradorEntity colaborador = new ColaboradorEntity();
        List<ColaboradorEntity> colaboradoresLista = new ArrayList<>();

        colaborador.setNome("Joao");
        colaborador.setId(1L);
        colaborador.setMatricula("10");
        colaborador.setStatus(true);

        colaboradoresLista.add(colaborador);

        colaborador1 = colaborador;
        colaboradores = colaboradoresLista;
    }

    // Método para configurar os mocks antes de cada teste
    @BeforeEach
    void setUp() {

        when(colaboradorRepository.findById(01L).get()).thenReturn(colaborador1);
        when((colaboradorRepository.save(colaborador1))).thenReturn(colaborador1);
        when(colaboradorRepository.findAll()).thenReturn(colaboradores);
        doNothing().when(colaboradorRepository).deleteById(colaborador1.getId());
    }

    // Teste para verificar se o salvamento de um colaborador funciona corretamente
    @Test
    void save_ColaboradorValido_DeveRetornarStatusOk() {
        ResponseEntity<ObjetoResposta<Void>> response = colaboradorController.save(colaborador1);
        // Assert: Verificação dos resultados da ação
        // Verificar se o status HTTP retornado é OK (200)
        assertNotNull(response.getBody());
        assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, response.getBody().getMensagem());
        assertEquals(HttpStatus.OK, response.getStatusCode());
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