package app.AbrigoCanino.controllers;

import app.AbrigoCanino.config.configuracoes.MensagensDeErro;
import app.AbrigoCanino.config.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.config.configuracoes.ObjetoResposta;
import app.AbrigoCanino.entities.CachorroEntity;
import app.AbrigoCanino.repositories.CachorroRepository;
import app.AbrigoCanino.service.CachorroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class CachorroControllerTest {


    @MockBean
    CachorroRepository cachorroRepository;

    @Autowired
    CachorroController cachorroController;

    @Autowired
    CachorroService cachorroService;

    CachorroEntity cachorro1;

    List<CachorroEntity> cachorros;

    @BeforeEach
    void setUp() {
        CachorroEntity cachorro = new CachorroEntity();

        CachorroEntity cachorro2 = new CachorroEntity();
        cachorro2.setId(2L);

        List<CachorroEntity> cachorrosLista = new ArrayList<>();
        cachorrosLista.add(cachorro1);
        cachorrosLista.add(cachorro2);

        cachorro.setId(1L);
        cachorro.setNome("Joao");
        cachorro.setRaca("Pitbull");
        cachorro.setIdadeAproximada(10);
        cachorro.setDescricao("Descricao");


        cachorro1 = cachorro;
        cachorros = cachorrosLista;

        when(cachorroRepository.existsById(1L)).thenReturn(true);
        when(cachorroRepository.findById(2L)).thenReturn(Optional.empty());
        when(cachorroRepository.findById(1L)).thenReturn(Optional.of(cachorro));
        when((cachorroRepository.save(cachorro))).thenReturn(cachorro);
        when(cachorroRepository.findAll()).thenReturn(cachorrosLista);
        doNothing().when(cachorroRepository).deleteById(cachorro.getId());
    }

    @Test
    void save_CachorroValido_DeveRetornarStatusOk() {
        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.save(cachorro1);

        assertNotNull(response.getBody());
        assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, response.getBody().getMensagem());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void save_CachorroNulo_DeveLancarExcessao(){
        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.save(null);

        assertNotNull(response.getBody());
        assertThrows(Exception.class, () -> {
            cachorroService.findById(null);
        });
    }

    @Test
    void findAll_CachorrosExistem_DeveRetornarListaDeCachorro() {
        ResponseEntity<ObjetoResposta<List<CachorroEntity>>> response = cachorroController.findAll();

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cachorros, response.getBody().getObjeto());
        assertFalse(response.getBody().getObjeto().isEmpty());
    }

    @Test
    void findById_BuscarCachorroPorId_DeveRetornarCachorro() {
        ResponseEntity<ObjetoResposta<CachorroEntity>> response = cachorroController.findById(1L);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ObjetoResposta<CachorroEntity> objetoResposta = response.getBody();
        assertNotNull(objetoResposta.getObjeto());
        assertEquals(cachorro1, objetoResposta.getObjeto());
    }

    @Test
    void findById_IdNaoExistente_DeveLancarExcessao(){
        ResponseEntity<ObjetoResposta<CachorroEntity>> response = cachorroController.findById(3L);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(MensagensDeErro.ID_NAO_ENCONTRADO, response.getBody().getMensagem());
        assertThrows(Exception.class, () -> {
            cachorroService.findById(20L);
        });
    }

    @Test
    void delete_cachorroExistente_DeveRetornarStatusOk() {
        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.delete(1L);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MensagensDeSucesso.EXCLUSAO_SUCESSO, response.getBody().getMensagem());
        verify(cachorroRepository, times(1)).deleteById(1L);
    }



    @Test
    void update_CachorroExistente_DeveRetornarStatusOk() {
        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.update(cachorro1);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, response.getBody().getMensagem());

        verify(cachorroRepository, times(1)).save(cachorro1);
    }

}