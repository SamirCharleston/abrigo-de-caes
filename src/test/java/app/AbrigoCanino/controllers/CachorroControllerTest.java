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

    // Mock para simular o serviço de cachorros
    @Mock
    private CachorroService cachorroService;

    // Injeta o mock do serviço no controlador de cachorros
    @InjectMocks
    private CachorroController cachorroController;

    // Teste para verificar se o salvamento de um cachorro funciona corretamente
    @Test
    public void testSaveCachorro() throws Exception {
        // Arrange: Preparação do ambiente de teste
        // Cria um cachorro de exemplo
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setNome("Rex");
        // Configura o comportamento esperado do serviço de cachorros
        when(cachorroService.save(cachorro)).thenReturn("Cachorro salvo com sucesso");

        // Act: Execução da ação que está sendo testada
        // Chama o método save() do controlador de cachorros
        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.save(cachorro);

        // Assert: Verificação dos resultados da ação
        // Verifica se o status da resposta e a mensagem são os esperados
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cachorro salvo com sucesso", response.getBody().getMensagem());
    }

    // Teste para verificar se a busca por ID de um cachorro funciona corretamente
    @Test
    public void testFindCachorroById() throws Exception {
        // Arrange
        // Preparação do ambiente de teste
        // Cria um cachorro de exemplo
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setId(1L);
        cachorro.setNome("Rex");
        // Configura o comportamento esperado do serviço de cachorros
        when(cachorroService.findById(1L)).thenReturn(cachorro);

        // Act
        // Execução da ação que está sendo testada
        // Chama o método findById() do controlador de cachorros
        ResponseEntity<ObjetoResposta<CachorroEntity>> response = cachorroController.findById(1L);

        // Assert
        // Verificação dos resultados da ação
        // Verifica se o status da resposta e o objeto retornado são os esperados
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cachorro, response.getBody().getObjeto());
    }

    // Teste para verificar se a busca por todos os cachorros funciona corretamente
    @Test
    public void testFindAllCachorros() throws Exception {
        // Arrange
        // Preparação do ambiente de teste
        // Cria um cachorro de exemplo
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setId(1L);
        cachorro.setNome("Rex");
        // Cria uma lista de cachorros
        List<CachorroEntity> cachorros = Collections.singletonList(cachorro);
        // Configura o comportamento esperado do serviço de cachorros
        when(cachorroService.findAll()).thenReturn(cachorros);

        // Act
        // Execução da ação que está sendo testada
        // Chama o método findAll() do controlador de cachorros
        ResponseEntity<ObjetoResposta<List<CachorroEntity>>> response = cachorroController.findAll();

        // Assert
        // Verificação dos resultados da ação
        // Verifica se o status da resposta e a lista de objetos retornada são os esperados
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cachorros, response.getBody().getObjeto());
    }

    // Teste para verificar se a atualização de um cachorro funciona corretamente
    @Test
    public void testUpdateCachorro() throws Exception {
        // Arrange
        // Preparação do ambiente de teste
        // Cria um cachorro de exemplo
        CachorroEntity cachorro = new CachorroEntity();
        cachorro.setId(1L);
        cachorro.setNome("Rex");
        // Configura o comportamento esperado do serviço de cachorros
        when(cachorroService.update(cachorro)).thenReturn("Cachorro atualizado com sucesso");

        // Act
        // Execução da ação que está sendo testada
        // Chama o método update() do controlador de cachorros
        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.update(cachorro);

        // Assert
        // Verificação dos resultados da ação
        // Verifica se o status da resposta e a mensagem são os esperados
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cachorro atualizado com sucesso", response.getBody().getMensagem());
    }

    // Teste para verificar se a exclusão de um cachorro funciona corretamente
    @Test
    public void testDeleteCachorro() throws Exception {
        // Arrange
        // Preparação do ambiente de teste
        // Define o ID do cachorro
        Long cachorroId = 1L;
        // Configura o comportamento esperado do serviço de cachorros
        when(cachorroService.delete(cachorroId)).thenReturn("Cachorro deletado com sucesso");

        // Act
        // Execução da ação que está sendo testada
        // Chama o método delete() do controlador de cachorros
        ResponseEntity<ObjetoResposta<Void>> response = cachorroController.delete(cachorroId);

        // Assert
        // Verificação dos resultados da ação
        // Verifica se o status da resposta e a mensagem são os esperados
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Cachorro deletado com sucesso", response.getBody().getMensagem());
    }

    // Teste para verificar se uma exceção é lançada ao buscar um cachorro por ID não existente
    @Test
    public void testFindCachorroByIdNotFound() throws Exception {
        // Arrange
        // Preparação do ambiente de teste
        // Configura o comportamento esperado do serviço de cachorros para lançar uma exceção
        when(cachorroService.findById(anyLong())).thenThrow(new RuntimeException("Cachorro não encontrado"));

        // Act
        // Execução da ação que está sendo testada
        // Chama o método findById() do controlador de cachorros
        ResponseEntity<ObjetoResposta<CachorroEntity>> response = cachorroController.findById(1L);
        // Assert
        // Verificação dos resultados da ação
        // Verifica se o status da resposta e a mensagem são os esperados
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Cachorro não encontrado", response.getBody().getMensagem());
    }
}