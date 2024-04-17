package app.AbrigoCanino.service;

import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.entities.CachorroEntity;
import app.AbrigoCanino.repositories.CachorroRepository;
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

public class CachorroServiceTest {

    @Mock
    private CachorroRepository cachorroRepository;

    @InjectMocks
    private CachorroService cachorroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCachorro() {
        CachorroEntity cachorro = new CachorroEntity();
        Mockito.when(cachorroRepository.save(cachorro)).thenReturn(cachorro);
        String result = null;
        try {
            result = cachorroService.save(cachorro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, result);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        CachorroEntity cachorro = new CachorroEntity();
        Mockito.when(cachorroRepository.findById(id)).thenReturn(Optional.of(cachorro));
        try {
            CachorroEntity result = cachorroService.findById(id);
            Assertions.assertEquals(cachorro, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll() {
        List<CachorroEntity> cachorros = new ArrayList<>();
        CachorroEntity cachorro1 = new CachorroEntity();
        cachorros.add(cachorro1);
        Mockito.when(cachorroRepository.findAll()).thenReturn(cachorros);
        try {
            List<CachorroEntity> result = cachorroService.findAll();
            Assertions.assertEquals(cachorros, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        CachorroEntity cachorro = new CachorroEntity();
        Mockito.when(cachorroRepository.save(cachorro)).thenReturn(cachorro);
        try {
            String result = cachorroService.update(cachorro);
            Assertions.assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        try {
            String result = cachorroService.delete(id);
            Assertions.assertEquals(MensagensDeSucesso.EXCLUSAO_SUCESSO, result);
            Mockito.verify(cachorroRepository, Mockito.times(1)).deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


