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

    // Mock para simular o repositório de cachorros
    @Mock
    private CachorroRepository cachorroRepository;

    // Injeta o mock do repositório no serviço de cachorros
    @InjectMocks
    private CachorroService cachorroService;

    // Configuração a ser executada antes de cada teste
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    // Teste para verificar se o salvamento de um cachorro funciona corretamente
    @Test
    public void testSaveCachorro() {
        CachorroEntity cachorro = new CachorroEntity();
        Mockito.when(cachorroRepository.save(cachorro)).thenReturn(cachorro); // Configura o comportamento do repositório
        String result = null;
        try {
            result = cachorroService.save(cachorro); // Chama o método do serviço que será testado
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Verifica se o resultado é igual à mensagem de sucesso esperada
        Assertions.assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, result);
    }

    // Teste para verificar se a busca por ID retorna o cachorro correto
    @Test
    public void testFindById() {
        Long id = 1L;
        CachorroEntity cachorro = new CachorroEntity();
        Mockito.when(cachorroRepository.findById(id)).thenReturn(Optional.of(cachorro)); // Configura o comportamento do repositório
        try {
            CachorroEntity result = cachorroService.findById(id); // Chama o método do serviço que será testado
            // Verifica se o resultado é igual ao cachorro esperado
            Assertions.assertEquals(cachorro, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Teste para verificar se a busca por todos os cachorros retorna a lista correta
    @Test
    public void testFindAll() {
        List<CachorroEntity> cachorros = new ArrayList<>();
        CachorroEntity cachorro1 = new CachorroEntity();
        cachorros.add(cachorro1);
        Mockito.when(cachorroRepository.findAll()).thenReturn(cachorros); // Configura o comportamento do repositório
        try {
            List<CachorroEntity> result = cachorroService.findAll(); // Chama o método do serviço que será testado
            // Verifica se o resultado é igual à lista de cachorros esperada
            Assertions.assertEquals(cachorros, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Teste para verificar se a atualização de um cachorro funciona corretamente
    @Test
    public void testUpdate() {
        CachorroEntity cachorro = new CachorroEntity();
        Mockito.when(cachorroRepository.save(cachorro)).thenReturn(cachorro); // Configura o comportamento do repositório
        try {
            String result = cachorroService.update(cachorro); // Chama o método do serviço que será testado
            // Verifica se o resultado é igual à mensagem de sucesso esperada
            Assertions.assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Teste para verificar se a exclusão de um cachorro funciona corretamente
    @Test
    public void testDelete() {
        Long id = 1L;
        try {
            String result = cachorroService.delete(id); // Chama o método do serviço que será testado
            // Verifica se o resultado é igual à mensagem de sucesso esperada e se o método deleteById do repositório foi chamado uma vez
            Assertions.assertEquals(MensagensDeSucesso.EXCLUSAO_SUCESSO, result);
            Mockito.verify(cachorroRepository, Mockito.times(1)).deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


