package app.AbrigoCanino.service;

import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.entities.RequerimentoEntity;
import app.AbrigoCanino.repositories.RequerimentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequerimentoServiceTest {

    @Mock
    private RequerimentoRepository requerimentoRepository;

    @InjectMocks
    private RequerimentoService requerimentoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveRequerimento() {
        RequerimentoEntity requerimento = new RequerimentoEntity();
        Mockito.when(requerimentoRepository.save(requerimento)).thenReturn(requerimento);
        String result = null;
        try {
            result = requerimentoService.save(requerimento);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, result);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        RequerimentoEntity requerimento = new RequerimentoEntity();
        Mockito.when(requerimentoRepository.findById(id)).thenReturn(Optional.of(requerimento));
        try {
            RequerimentoEntity result = requerimentoService.findById(id);
            Assertions.assertEquals(requerimento, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll() {
        List<RequerimentoEntity> requerimentos = new ArrayList<>();
        RequerimentoEntity requerimento1 = new RequerimentoEntity();
        requerimentos.add(requerimento1);
        Mockito.when(requerimentoRepository.findAll()).thenReturn(requerimentos);
        try {
            List<RequerimentoEntity> result = requerimentoService.findAll();
            Assertions.assertEquals(requerimentos, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        RequerimentoEntity requerimento = new RequerimentoEntity();
        Mockito.when(requerimentoRepository.save(requerimento)).thenReturn(requerimento);
        String result = requerimentoService.update(requerimento);
        Assertions.assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, result);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        RequerimentoEntity requerimento = new RequerimentoEntity();
        Mockito.when(requerimentoRepository.existsById(id)).thenReturn(true);
        Mockito.when(requerimentoRepository.findById(id)).thenReturn(Optional.of(requerimento));
        try {
            String result = requerimentoService.delete(id);
            Assertions.assertEquals(MensagensDeSucesso.EXCLUSAO_SUCESSO, result);
            Mockito.verify(requerimentoRepository, Mockito.times(1)).delete(requerimento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteWithInvalidId() {
        Long id = 1L;
        Mockito.when(requerimentoRepository.existsById(id)).thenReturn(false);
        try {
            Assertions.assertThrows(Exception.class, () -> requerimentoService.delete(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

