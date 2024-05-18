package app.AbrigoCanino.controllers;


import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.entities.TutorEntity;
import app.AbrigoCanino.repositories.TutorRepository;
import app.AbrigoCanino.service.TutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TutorControllerTest {

    @MockBean
    TutorRepository tutorRepository;

    @Autowired
    TutorController tutorController;

    @Autowired
    TutorService tutorService;

    TutorEntity tutor1;

    List<TutorEntity> tutores;

    @BeforeEach
    void setUp(){
        TutorEntity tutor = new TutorEntity();

        List<TutorEntity> tutoreslista = new ArrayList<>();

        tutor.setId(1L);
        tutor.setStatus(true);
        tutor.setDataRequerimento(LocalDate.now());
        tutor.setContato("123");
        tutor.setIdade(20);
        tutor.setEndereco("Rua123");

        tutoreslista.add(tutor);

        tutor1 = tutor;
        tutores = tutoreslista;

        when(tutorRepository.existsById(1L)).thenReturn(true);
        when(tutorRepository.findById(2L)).thenReturn(Optional.empty());
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));
        when((tutorRepository.save(tutor))).thenReturn(tutor);
        when(tutorRepository.findAll()).thenReturn(tutoreslista);
        doNothing().when(tutorRepository).deleteById(tutor.getId());
    }

    @Test
    void save_TutorValido_DeveRetornarStatusOk() {
        ResponseEntity<ObjetoResposta<Void>> response = tutorController.save(tutor1);
        // Assert: Verificação dos resultados da ação
        // Verificar se o status HTTP retornado é OK (200)
        assertNotNull(response.getBody());
        assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, response.getBody().getMensagem());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void save_TutorMenorIdade_DeveLancarExcessao() {
        tutor1.setIdade(17);
        ResponseEntity<ObjetoResposta<Void>> response = tutorController.save(tutor1);
        // Assert: Verificação dos resultados da ação
        // Verificar se o status HTTP retornado é OK (200)
        assertNotNull(response.getBody());
        assertEquals("Tutor nao deve ser menor de idade.", response.getBody().getMensagem());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertThrows(Exception.class, () -> {
            tutorService.save(tutor1);
        });
    }
    @Test
    void save_TutorContatoEmBranco_DeveLancarExcessao(){
        tutor1.setContato("");
        ResponseEntity<ObjetoResposta<Void>> response = tutorController.save(tutor1);
        // Assert: Verificação dos resultados da ação
        // Verificar se o status HTTP retornado é OK (200)
        assertNotNull(response.getBody());
        assertEquals("Contato nao deve ser em branco", response.getBody().getMensagem());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertThrows(Exception.class, () -> {
            tutorService.save(tutor1);
        });
    }

    @Test
    void save_TutorNulo_DeveLancarExcessao(){
        ResponseEntity<ObjetoResposta<Void>> response = tutorController.save(null);

        assertNotNull(response.getBody());
        assertThrows(Exception.class, () -> {
            tutorService.save(null);
        });
    }

    @Test
    void findAll_TutoresExistem_DeveRetornarListaDeTutores() {
        ResponseEntity<ObjetoResposta<List<TutorEntity>>> response = tutorController.findAll();

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tutores, response.getBody().getObjeto());
        assertFalse(response.getBody().getObjeto().isEmpty());
    }

    @Test
    void findById_BuscarTutorPorId_DeveRetornarTutor() {
        ResponseEntity<ObjetoResposta<TutorEntity>> response = tutorController.findById(1L);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ObjetoResposta<TutorEntity> objetoResposta = response.getBody();
        assertNotNull(objetoResposta.getObjeto());
        assertEquals(tutor1, objetoResposta.getObjeto());
    }

    @Test
    void findById_IdNaoExistente_DeveLancarExcessao(){
        ResponseEntity<ObjetoResposta<TutorEntity>> response = tutorController.findById(2L);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(MensagensDeErro.ID_NAO_ENCONTRADO, response.getBody().getMensagem());
        assertThrows(Exception.class, () -> {
            tutorService.findById(20L);
        });
    }

    @Test
    void delete_TutorExistente_DeveRetornarStatusOk() {
        ResponseEntity<ObjetoResposta<Void>> response = tutorController.delete(1L);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MensagensDeSucesso.EXCLUSAO_SUCESSO, response.getBody().getMensagem());
        verify(tutorRepository, times(1)).deleteById(1L);
    }

    @Test
    void update_TutorExistente_DeveRetornarStatusOk() {
        ResponseEntity<ObjetoResposta<Void>> response = tutorController.update(tutor1);


        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, response.getBody().getMensagem());


        verify(tutorRepository, times(1)).save(tutor1);
    }



}