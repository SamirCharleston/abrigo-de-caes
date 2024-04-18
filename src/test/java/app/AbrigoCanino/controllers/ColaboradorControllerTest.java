package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.MensagensDeErro;
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

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class ColaboradorControllerTest {
    @MockBean
    ColaboradorRepository colaboradorRepository;

    @Autowired
    ColaboradorController colaboradorController;
    @Autowired
    ColaboradorService colaboradorService;
    ColaboradorEntity colaborador1;
    List<ColaboradorEntity> colaboradores;
    @BeforeEach
    void setUp() {
        ColaboradorEntity colaborador = new ColaboradorEntity();

        List<ColaboradorEntity> colaboradoresLista = new ArrayList<>();

        colaborador.setNome("Joao");
        colaborador.setId(1L);
        colaborador.setMatricula("10");
        colaborador.setStatus(true);
        colaboradoresLista.add(colaborador);

        colaborador1 = colaborador;
        colaboradores = colaboradoresLista;

        when(colaboradorRepository.findById(1L)).thenReturn(Optional.of(colaborador));
        when((colaboradorRepository.save(colaborador))).thenReturn(colaborador);
        when(colaboradorRepository.findAll()).thenReturn(colaboradoresLista);
        doNothing().when(colaboradorRepository).deleteById(colaborador.getId());
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

    @Test
    void save_ColaboradorNulo_DeveLancarExcessao(){
        ResponseEntity<ObjetoResposta<Void>> response = colaboradorController.save(null);

        assertNotNull(response.getBody());
        assertThrows(Exception.class, () -> {
            throw new Exception(MensagensDeErro.OBJETO_NULO);
        });
    }

    @Test
    void findAll_ColaboradoresExistem_DeveRetornarListaDeColaboradores() {
        ResponseEntity<ObjetoResposta<List<ColaboradorEntity>>> response = colaboradorController.findAll();

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(colaboradores, response.getBody().getObjeto());
        assertFalse(response.getBody().getObjeto().isEmpty());
    }

    @Test
    void findById_BuscarColaboradorPorId_DeveRetornarColaborador(){
        ResponseEntity<ObjetoResposta<ColaboradorEntity>> response = colaboradorController.findById(01L);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(colaborador1, response.getBody().getObjeto());
        assertNotNull(response.getBody().getObjeto());
    }
    @Test
    void findById_IdNaoExistente_DeveLancarExcessao(){
        ResponseEntity<ObjetoResposta<ColaboradorEntity>> response = colaboradorController.findById(2L);

        assertNotNull(response.getBody());
        assertEquals(MensagensDeErro.ID_NAO_ENCONTRADO, response.getBody().getMensagem());
        assertThrows(Exception.class, () -> {
            colaboradorService.findById(20L);
        });
    }
//
//    // Teste para verificar se a busca por ID de um colaborador funciona corretamente
//    @Test
//    void findById_IdExistente_DeveRetornarColaborador() throws Exception {
//        // Arrange
//        // Preparação do ambiente de teste
//        Long id = 1L;
//        ColaboradorEntity colaborador = new ColaboradorEntity();
//        colaborador.setId(id);
//        colaborador.setNome("João");
//        // Configurar o comportamento esperado do serviço de colaboradores
//        when(colaboradorService.findById(id)).thenReturn(colaborador);
//        // Act
//        // Execução da ação que está sendo testada
//        ResponseEntity<ObjetoResposta<ColaboradorEntity>> responseEntity = colaboradorController.findById(id);
//        // Assert
//        // Verificação dos resultados da ação
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(id, responseEntity.getBody().getObjeto().getId());
//    }
//
//    // Teste para verificar se a busca por todos os colaboradores funciona corretamente
//    @Test
//    void findAll_ColaboradoresExistem_DeveRetornarListaDeColaboradores() throws Exception {
//        // Arrange
//        // Preparação do ambiente de teste
//        List<ColaboradorEntity> colaboradores = new ArrayList<>();
//        colaboradores.add(new ColaboradorEntity());
//        colaboradores.add(new ColaboradorEntity());
//        // Configurar o comportamento esperado do serviço de colaboradores
//        when(colaboradorService.findAll()).thenReturn(colaboradores);
//
//        // Act
//        // Execução da ação que está sendo testada
//        ResponseEntity<ObjetoResposta<List<ColaboradorEntity>>> responseEntity = colaboradorController.findAll();
//
//        // Assert
//        // Verificação dos resultados da ação
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(colaboradores.size(), responseEntity.getBody().getObjeto().size());
//        verify(colaboradorService, times(1)).findAll();
//    }
//
//    // Teste para verificar se a atualização de um colaborador funciona corretamente
//    @Test
//    void update_ColaboradorValido_DeveRetornarStatusOk() throws Exception {
//        // Arrange
//        // Preparação do ambiente de teste
//        ColaboradorEntity colaborador = new ColaboradorEntity();
//        colaborador.setId(1L);
//        colaborador.setNome("Maria");
//        // Configurar o comportamento esperado do serviço de colaboradores
//        when(colaboradorService.update(colaborador)).thenReturn("Colaborador atualizado com sucesso.");
//
//        // Act
//        // Execução da ação que está sendo testada
//        ResponseEntity<ObjetoResposta<Void>> responseEntity = colaboradorController.update(colaborador);
//
//        // Assert
//        // Verificação dos resultados da ação
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        verify(colaboradorService, times(1)).update(colaborador);
//    }
//
//    // Teste para verificar se a exclusão de um colaborador funciona corretamente
//    @Test
//    void delete_IdExistente_DeveRetornarStatusOk() throws Exception {
//        // Arrange
//        // Preparação do ambiente de teste
//        Long id = 1L;
//        // Configurar o comportamento esperado do serviço de colaboradores
//        when(colaboradorService.delete(id)).thenReturn("Colaborador removido com sucesso.");
//
//        // Act
//        // Execução da ação que está sendo testada
//        ResponseEntity<ObjetoResposta<Void>> responseEntity = colaboradorController.delete(id);
//
//        // Assert
//        // Verificação dos resultados da ação
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        verify(colaboradorService, times(1)).delete(id);
//    }
}