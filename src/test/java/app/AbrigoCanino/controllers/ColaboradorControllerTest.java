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

}