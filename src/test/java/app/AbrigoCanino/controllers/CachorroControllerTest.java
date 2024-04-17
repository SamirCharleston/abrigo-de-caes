package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.entities.CachorroEntity;
import app.AbrigoCanino.service.CachorroService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CachorroControllerTest {

    @Mock
    private CachorroService cachorroService;

    @InjectMocks
    private CachorroController cachorroController;

    @Test
    public void testSaveCachorro() throws Exception {
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setNome("Rex");

        when(cachorroService.save(cachorro)).thenReturn("Cachorro salvo com sucesso");

        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.save(cachorro);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cachorro salvo com sucesso", response.getBody().getMensagem());
    }

    @Test
    public void testFindCachorroById() throws Exception {
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setId(1L);
        cachorro.setNome("Rex");

        when(cachorroService.findById(1L)).thenReturn(cachorro);

        ResponseEntity<ObjetoResposta<CachorroEntity>> response = cachorroController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cachorro, response.getBody().getObjeto());
    }

    @Test
    public void testFindAllCachorros() throws Exception {
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setId(1L);
        cachorro.setNome("Rex");

        List<CachorroEntity> cachorros = Collections.singletonList(cachorro);

        when(cachorroService.findAll()).thenReturn(cachorros);

        ResponseEntity<ObjetoResposta<List<CachorroEntity>>> response = cachorroController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cachorros, response.getBody().getObjeto());
    }

    @Test
    public void testUpdateCachorro() throws Exception {
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setId(1L);
        cachorro.setNome("Rex");

        when(cachorroService.update(cachorro)).thenReturn("Cachorro atualizado com sucesso");

        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.update(cachorro);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cachorro atualizado com sucesso", response.getBody().getMensagem());
    }

    @Test
    public void testDeleteCachorro() throws Exception {
        Long cachorroId = 1L;

        when(cachorroService.delete(cachorroId)).thenReturn("Cachorro deletado com sucesso");

        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.delete(cachorroId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cachorro deletado com sucesso", response.getBody().getMensagem());
    }

    @Test
    public void testSaveCachorroSuccess() throws Exception {
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setNome("Rex");

        when(cachorroService.save(any())).thenReturn("Cachorro salvo com sucesso");

        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.save(cachorro);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cachorro salvo com sucesso", response.getBody().getMensagem());
        verify(cachorroService, times(1)).save(any());
    }

    @Test
    public void testFindCachorroByIdSuccess() throws Exception {
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setId(1L);
        cachorro.setNome("Rex");

        when(cachorroService.findById(1L)).thenReturn(cachorro);

        ResponseEntity<ObjetoResposta<CachorroEntity>> response = cachorroController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cachorro, response.getBody().getObjeto());
    }

    @Test
    public void testFindAllCachorrosSuccess() throws Exception {
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setId(1L);
        cachorro.setNome("Rex");

        List<CachorroEntity> cachorros = Collections.singletonList(cachorro);

        when(cachorroService.findAll()).thenReturn(cachorros);

        ResponseEntity<ObjetoResposta<List<CachorroEntity>>> response = cachorroController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cachorros, response.getBody().getObjeto());
    }

    @Test
    public void testUpdateCachorroSuccess() throws Exception {
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setId(1L);
        cachorro.setNome("Rex");

        when(cachorroService.update(cachorro)).thenReturn("Cachorro atualizado com sucesso");

        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.update(cachorro);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cachorro atualizado com sucesso", response.getBody().getMensagem());
    }

    @Test
    public void testDeleteCachorroSuccess() throws Exception {
        Long cachorroId = 1L;

        when(cachorroService.delete(cachorroId)).thenReturn("Cachorro deletado com sucesso");

        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.delete(cachorroId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cachorro deletado com sucesso", response.getBody().getMensagem());
    }

    @Test
    public void testFindCachorroByIdNotFound() throws Exception {
        when(cachorroService.findById(anyLong())).thenThrow(new RuntimeException("Cachorro não encontrado"));

        ResponseEntity<ObjetoResposta<CachorroEntity>> response = cachorroController.findById(1L);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Cachorro não encontrado", response.getBody().getMensagem());
    }


}
