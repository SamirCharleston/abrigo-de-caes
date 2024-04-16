package app.AbrigoCanino.service;


import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.entities.TutorEntity;
import app.AbrigoCanino.repositories.TutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class TutorServiceTest {

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private TutorService tutorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTutor() throws Exception {
        TutorEntity tutor = new TutorEntity();
        when(tutorRepository.save(tutor)).thenReturn(tutor);
        String result = tutorService.save(tutor);
        assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, result);
    }

    @Test
    void testFindById() throws Exception {
        TutorEntity tutor = new TutorEntity();
        tutor.setId(1L);
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor));
        TutorEntity result = tutorService.findById(1L);
        assertEquals(tutor, result);
    }

    @Test
    void testFindByIdThrowsExceptionWhenIdNotFound() {
        when(tutorRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> tutorService.findById(1L));
        assertEquals(MensagensDeErro.ID_NAO_ENCONTRADO, exception.getMessage());
    }

    @Test
    void testFindAll() throws Exception {
        when(tutorRepository.findAll()).thenReturn(Collections.emptyList()); // Mock retorna uma lista vazia
        Exception exception = assertThrows(Exception.class, () -> tutorService.findAll());
        assertEquals(MensagensDeErro.LISTA_VAZIA, exception.getMessage());
    }

    @Test
    void testFindAllThrowsExceptionWhenListIsEmpty() {
        when(tutorRepository.findAll()).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(Exception.class, () -> tutorService.findAll());
        assertEquals(MensagensDeErro.LISTA_VAZIA, exception.getMessage());
    }

    @Test
    void testUpdateTutor() {
        TutorEntity tutor = new TutorEntity();
        when(tutorRepository.save(tutor)).thenReturn(tutor);
        String result = tutorService.update(tutor);
        assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, result);
    }

    @Test
    void testDeleteTutor() throws Exception {
        TutorEntity tutor = new TutorEntity();
        tutor.setId(1L);
        when(tutorRepository.existsById(1L)).thenReturn(true);
        tutorService.delete(tutor);
        verify(tutorRepository, times(1)).delete(tutor);
    }

    @Test
    void testDeleteTutorThrowsExceptionWhenIdNotFound() {
        TutorEntity tutor = new TutorEntity();
        tutor.setId(1L);
        when(tutorRepository.existsById(1L)).thenReturn(false);
        Exception exception = assertThrows(Exception.class, () -> tutorService.delete(tutor));
        assertEquals(MensagensDeErro.ID_NAO_ENCONTRADO, exception.getMessage());
    }


}
