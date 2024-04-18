package app.AbrigoCanino.controllers;


import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.entities.CachorroEntity;
import app.AbrigoCanino.entities.RequerimentoEntity;
import app.AbrigoCanino.entities.TutorEntity;
import app.AbrigoCanino.repositories.RequerimentoRepository;
import app.AbrigoCanino.service.RequerimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class RequerimentoControllerTest {

    @MockBean
    RequerimentoRepository requerimentoRepository;

    @Autowired
    RequerimentoController requerimentoController;

    @Autowired
    RequerimentoService requerimentoService;

    RequerimentoEntity  requerimento1;

    List<RequerimentoEntity> requerimentos;

    TutorEntity tutor;
    List<CachorroEntity> cachorros;

    @BeforeEach
    void setUp(){
        tutor = new TutorEntity();
        tutor.setId(1L); // Defina um ID para o tutor
        cachorros = new ArrayList<>(); // Inicialize a lista de cachorros
        cachorros.add(new CachorroEntity()); // Adicione um cachorro à lista

        RequerimentoEntity requerimento = new RequerimentoEntity();
        requerimento.setId(1L);
        requerimento.setAutorDoRequerimento(tutor);
        requerimento.setCaesRequeridos(cachorros);
        requerimento.setStatus(true);

        requerimento1 = requerimento;
        List<RequerimentoEntity> requerimentoslista = new ArrayList<>();
        requerimentoslista.add(requerimento);

        requerimentos = requerimentoslista;

        when(requerimentoRepository.existsById(1L)).thenReturn(true);
        when(requerimentoRepository.existsById(2L)).thenReturn(false);
        doNothing().when(requerimentoRepository).deleteById(1L);
        when(requerimentoRepository.findById(2L)).thenReturn(Optional.empty());
        when(requerimentoRepository.findById(1L)).thenReturn(Optional.of(requerimento));
        when(requerimentoRepository.save(requerimento)).thenReturn(requerimento);
        when(requerimentoRepository.findAll()).thenReturn(requerimentoslista);
        doNothing().when(requerimentoRepository).deleteById(requerimento.getId());
    }


    @Test
    void save_RequerimentoInvalido_DeveRetornarErro() {
        ResponseEntity<ObjetoResposta<Void>> response = requerimentoController.save(requerimento1);

        assertNotNull(response.getBody());
        assertEquals("Autor nao encontrado", response.getBody().getMensagem());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void findById_Requerimento_DeveRetornarErro(){
        ResponseEntity<ObjetoResposta<RequerimentoEntity>> response = requerimentoController.findById(2L);

        assertNotNull(response.getBody());
        assertEquals(MensagensDeErro.ID_NAO_ENCONTRADO, response.getBody().getMensagem());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void findAll_Requerimento_DeveRetornarLista(){
        ResponseEntity<ObjetoResposta<List<RequerimentoEntity>>> response = requerimentoController.findAll();

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getObjeto().size());
    }
    @Test
    void update_Requerimento_DeveRetornarSucesso(){
        ResponseEntity<ObjetoResposta<Void>> response = requerimentoController.update(requerimento1);

        assertNotNull(response.getBody());
        assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, response.getBody().getMensagem());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteById_Requerimento_DeveRetornarErro(){
        ResponseEntity<ObjetoResposta<Void>> response = requerimentoController.delete(2L);

        assertNotNull(response.getBody());
        assertEquals(MensagensDeErro.ID_NAO_ENCONTRADO, response.getBody().getMensagem());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertThrows(Exception.class, () -> {
            requerimentoService.delete(10L);
        });
    }
    @Test
    void delete_Requerimento_DeveRetornarSucesso(){
        ResponseEntity<ObjetoResposta<Void>> response = requerimentoController.delete(1L);

        assertNotNull(response.getBody());
        assertEquals(MensagensDeSucesso.EXCLUSAO_SUCESSO, response.getBody().getMensagem());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
