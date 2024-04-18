package app.AbrigoCanino.service;


import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.entities.TutorEntity;
import app.AbrigoCanino.repositories.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TutorServiceTest {
    @MockBean
    TutorRepository tutorRepository;
    @Autowired
    TutorService tutorService;
    TutorEntity tutor1;
    @BeforeEach
    public void setUp() {
        TutorEntity tutor = new TutorEntity();

        tutor.setStatus(true);
        tutor.setId(1L);
        tutor.setNome("Samir");
        tutor.setIdade(18);
        tutor.setRg("2151511");
        tutor.setCpf("12345678901");
        tutor.setContato("12345678901");
        tutor.setDataRequerimento(LocalDate.now());
        tutor.setEndereco("Rua ABC");

        tutor1 = tutor;
    }
    @Test
    public void save_TutorSalvoComSucesso_RetornaMensagemDeSucesso() {
    }
    @Test
    public void verificaMaioridade_TutorEMaiorDeIdade_RetornaTrue() {
        assertTrue(tutorService.verificaMaioridade(tutor1.getIdade()));
    }
    @Test
    public void verificaMaioridade_TutorNAoEMaiorDeIdade_RetornaFalse() {
        assertFalse(tutorService.verificaMaioridade(17));
    }
}
