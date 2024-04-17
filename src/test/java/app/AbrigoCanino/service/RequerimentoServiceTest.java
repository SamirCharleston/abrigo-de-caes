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

    // Mock para simular o repositório de requerimentos
    @Mock
    private RequerimentoRepository requerimentoRepository;

    // Injeta o mock do repositório no serviço de requerimentos
    @InjectMocks
    private RequerimentoService requerimentoService;

    // Configuração a ser executada antes de cada teste
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    // Teste para verificar se o salvamento de um requerimento funciona corretamente
    @Test
    public void testSaveRequerimento() {
        RequerimentoEntity requerimento = new RequerimentoEntity();
        Mockito.when(requerimentoRepository.save(requerimento)).thenReturn(requerimento); // Configura o comportamento do repositório
        String result = null;
        try {
            result = requerimentoService.save(requerimento); // Chama o método do serviço que será testado
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Verifica se o resultado é igual à mensagem de sucesso esperada
        Assertions.assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, result);
    }

    // Teste para verificar se a busca por ID retorna o requerimento correto
    @Test
    public void testFindById() {
        Long id = 1L;
        RequerimentoEntity requerimento = new RequerimentoEntity();
        Mockito.when(requerimentoRepository.findById(id)).thenReturn(Optional.of(requerimento)); // Configura o comportamento do repositório
        try {
            RequerimentoEntity result = requerimentoService.findById(id); // Chama o método do serviço que será testado
            // Verifica se o resultado é igual ao requerimento esperado
            Assertions.assertEquals(requerimento, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Teste para verificar se a busca por todos os requerimentos retorna a lista correta
    @Test
    public void testFindAll() {
        List<RequerimentoEntity> requerimentos = new ArrayList<>();
        RequerimentoEntity requerimento1 = new RequerimentoEntity();
        requerimentos.add(requerimento1);
        Mockito.when(requerimentoRepository.findAll()).thenReturn(requerimentos); // Configura o comportamento do repositório
        try {
            List<RequerimentoEntity> result = requerimentoService.findAll(); // Chama o método do serviço que será testado
            // Verifica se o resultado é igual à lista de requerimentos esperada
            Assertions.assertEquals(requerimentos, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Teste para verificar se a atualização de um requerimento funciona corretamente
    @Test
    public void testUpdate() {
        RequerimentoEntity requerimento = new RequerimentoEntity();
        Mockito.when(requerimentoRepository.save(requerimento)).thenReturn(requerimento); // Configura o comportamento do repositório
        String result = requerimentoService.update(requerimento); // Chama o método do serviço que será testado
        // Verifica se o resultado é igual à mensagem de sucesso esperada
        Assertions.assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, result);
    }

    // Teste para verificar se a exclusão de um requerimento funciona corretamente
    @Test
    public void testDelete() {
        Long id = 1L;
        RequerimentoEntity requerimento = new RequerimentoEntity();
        Mockito.when(requerimentoRepository.existsById(id)).thenReturn(true); // Configura o comportamento do repositório
        Mockito.when(requerimentoRepository.findById(id)).thenReturn(Optional.of(requerimento)); // Configura o comportamento do repositório
        try {
            String result = requerimentoService.delete(id); // Chama o método do serviço que será testado
            // Verifica se o resultado é igual à mensagem de sucesso esperada e se o método delete do repositório foi chamado
            Assertions.assertEquals(MensagensDeSucesso.EXCLUSAO_SUCESSO, result);
            Mockito.verify(requerimentoRepository, Mockito.times(1)).delete(requerimento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Teste para verificar se a exclusão de um requerimento com ID inválido lança uma exceção
    @Test
    public void testDeleteWithInvalidId() {
        Long id = 1L;
        Mockito.when(requerimentoRepository.existsById(id)).thenReturn(false); // Configura o comportamento do repositório
        try {
            Assertions.assertThrows(Exception.class, () -> requerimentoService.delete(id)); // Chama o método do serviço que será testado e espera uma exceção
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

