package app.AbrigoCanino.service;

import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.entities.ColaboradorEntity;
import app.AbrigoCanino.repositories.ColaboradorRepository;
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

public class ColaboradorServiceTest {

    // Mock para simular o repositório de colaboradores
    @Mock
    private ColaboradorRepository colaboradorRepository;

    // Injeta o mock do repositório no serviço de colaboradores
    @InjectMocks
    private ColaboradorService colaboradorService;

    // Configuração a ser executada antes de cada teste
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    // Teste para verificar se o salvamento de um colaborador funciona corretamente
    @Test
    public void testSaveColaborador() {
        // Mockando um colaborador
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setNome("Nome do Colaborador");

        // Mockando o comportamento do método colaboradorRepository.save()
        Mockito.when(colaboradorRepository.save(Mockito.any())).thenReturn(colaborador);

        // Testando o método save do ColaboradorService
        try {
            String result = colaboradorService.save(colaborador); // Chama o método do serviço que será testado
            // Verifica se o resultado é igual à mensagem de sucesso esperada
            Assertions.assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, result);
        } catch (Exception e) {
            // Tratando a exceção, se houver
            Assertions.fail("Exception thrown: " + e.getMessage());
        }
    }

    // Teste para verificar se a busca por ID retorna o colaborador correto
    @Test
    public void testFindById() throws Exception {
        Long id = 1L;
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setId(id);
        colaborador.setNome("Nome do Colaborador");

        Mockito.when(colaboradorRepository.findById(id)).thenReturn(Optional.of(colaborador));

        ColaboradorEntity result = colaboradorService.findById(id);

        Assertions.assertEquals(colaborador, result);
    }

    // Teste para verificar se a busca por todos os colaboradores retorna a lista correta
    @Test
    public void testFindAll() throws Exception {
        List<ColaboradorEntity> colaboradores = new ArrayList<>();
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setNome("Nome do Colaborador");
        colaboradores.add(colaborador);

        Mockito.when(colaboradorRepository.findAll()).thenReturn(colaboradores);

        List<ColaboradorEntity> result = colaboradorService.findAll();

        Assertions.assertEquals(colaboradores, result);
    }

    // Teste para verificar se a atualização de um colaborador funciona corretamente
    @Test
    public void testUpdateColaborador() throws Exception {
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setNome("Nome do Colaborador");

        Mockito.when(colaboradorRepository.save(Mockito.any())).thenReturn(colaborador);

        String result = colaboradorService.update(colaborador);

        Assertions.assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, result);
    }

    // Teste para verificar se a exclusão de um colaborador funciona corretamente
    @Test
    public void testDeleteColaborador() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(colaboradorRepository).deleteById(id);

        String result = colaboradorService.delete(id);

        Assertions.assertEquals(MensagensDeSucesso.EXCLUSAO_SUCESSO, result);
    }

    // Teste para verificar se a busca por todos os colaboradores retorna uma lista vazia corretamente
    @Test
    public void testFindAllEmptyList() {
        List<ColaboradorEntity> colaboradores = new ArrayList<>();

        Mockito.when(colaboradorRepository.findAll()).thenReturn(colaboradores);

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            colaboradorService.findAll();
        });

        Assertions.assertEquals(MensagensDeErro.ID_NAO_ENCONTRADO, exception.getMessage());
    }

}
