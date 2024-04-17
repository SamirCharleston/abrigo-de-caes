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

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @InjectMocks
    private ColaboradorService colaboradorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveColaborador() {
        // Mocking a collaborator
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setNome("Nome do Colaborador");

        // Mocking the behavior of colaboradorRepository.save() method
        Mockito.when(colaboradorRepository.save(Mockito.any())).thenReturn(colaborador);

        // Testing the save method of ColaboradorService
        try {
            String result = colaboradorService.save(colaborador);
            Assertions.assertEquals(MensagensDeSucesso.CADASTRO_SUCESSO, result);
        } catch (Exception e) {
            // Handling the exception if any
            Assertions.fail("Exception thrown: " + e.getMessage());
        }
    }

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

    @Test
    public void testUpdateColaborador() throws Exception {
        ColaboradorEntity colaborador = new ColaboradorEntity();
        colaborador.setNome("Nome do Colaborador");

        Mockito.when(colaboradorRepository.save(Mockito.any())).thenReturn(colaborador);

        String result = colaboradorService.update(colaborador);

        Assertions.assertEquals(MensagensDeSucesso.ALTERACAO_SUCESSO, result);
    }

    @Test
    public void testDeleteColaborador() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(colaboradorRepository).deleteById(id);

        String result = colaboradorService.delete(id);

        Assertions.assertEquals(MensagensDeSucesso.EXCLUSAO_SUCESSO, result);
    }

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
