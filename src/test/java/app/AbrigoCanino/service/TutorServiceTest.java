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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class TutorServiceTest {

    // Mock para simular o repositório de tutores
    @Mock
    private TutorRepository tutorRepository;

    // Injeta o mock do repositório no serviço de tutores
    @InjectMocks
    private TutorService tutorService;

    // Configuração a ser executada antes de cada teste
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    // Teste para verificar se o salvamento de um tutor funciona corretamente
    @Test
    void testSaveTutor() throws Exception {
        TutorEntity tutor = new TutorEntity();
        when(tutorRepository.save(tutor)).thenReturn(tutor); // Configura o comportamento do repositório
        String result = tutorService.save(tutor); // Chama o método do serviço que será testado
        assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, result); // Verifica se o resultado é igual à mensagem de sucesso esperada
    }

    // Teste para verificar se a busca por ID retorna o tutor correto
    @Test
    void testFindById() throws Exception {
        TutorEntity tutor = new TutorEntity();
        tutor.setId(1L);
        when(tutorRepository.findById(1L)).thenReturn(Optional.of(tutor)); // Configura o comportamento do repositório
        TutorEntity result = tutorService.findById(1L); // Chama o método do serviço que será testado
        assertEquals(tutor, result); // Verifica se o resultado é igual ao tutor esperado
    }

    // Teste para verificar se uma exceção é lançada quando o ID não é encontrado
    @Test
    void testFindByIdThrowsExceptionWhenIdNotFound() {
        when(tutorRepository.findById(1L)).thenReturn(Optional.empty()); // Configura o comportamento do repositório
        Exception exception = assertThrows(Exception.class, () -> tutorService.findById(1L)); // Chama o método do serviço que será testado e espera uma exceção
        assertEquals(MensagensDeErro.ID_NAO_ENCONTRADO, exception.getMessage()); // Verifica se a mensagem de erro é a esperada
    }

    // Teste para verificar se uma exceção é lançada quando a lista de tutores está vazia
    @Test
    void testFindAllThrowsExceptionWhenListIsEmpty() {
        when(tutorRepository.findAll()).thenReturn(new ArrayList<>()); // Configura o comportamento do repositório
        Exception exception = assertThrows(Exception.class, () -> tutorService.findAll()); // Chama o método do serviço que será testado e espera uma exceção
        assertEquals(MensagensDeErro.LISTA_VAZIA, exception.getMessage()); // Verifica se a mensagem de erro é a esperada
    }

    // Teste para verificar se a atualização de um tutor funciona corretamente
    @Test
    void testUpdateTutor() {
        TutorEntity tutor = new TutorEntity();
        when(tutorRepository.save(tutor)).thenReturn(tutor); // Configura o comportamento do repositório
        String result = tutorService.update(tutor); // Chama o método do serviço que será testado
        assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, result); // Verifica se o resultado é igual à mensagem de sucesso esperada
    }

    // Teste para verificar se uma exceção é lançada ao tentar excluir um tutor com ID inválido
    @Test
    public void testDeleteTutorThrowsExceptionWhenIdNotFound() {
        TutorEntity tutor = new TutorEntity();
        tutor.setId(1L);
        when(tutorRepository.existsById(1L)).thenReturn(false); // Configura o comportamento do repositório
        Exception exception = assertThrows(Exception.class, () -> tutorService.delete(tutor.getId())); // Chama o método do serviço que será testado e espera uma exceção
        assertEquals(MensagensDeErro.ID_NAO_ENCONTRADO, exception.getMessage()); // Verifica se a mensagem de erro é a esperada
    }
}
