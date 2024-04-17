package app.AbrigoCanino.service;


import app.AbrigoCanino.entities.AdocaoEntity;
import app.AbrigoCanino.repositories.AdocaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;




public class AdocaoServiceTest {

    @Mock
    private AdocaoRepository adocaoRepository;

    @InjectMocks
    private AdocaoService adocaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAdocoes() {
        List<AdocaoEntity> adocoes = new ArrayList<>();
        adocoes.add(new AdocaoEntity());
        adocoes.add(new AdocaoEntity());

        when(adocaoRepository.findAll()).thenReturn(adocoes);

        List<AdocaoEntity> result = adocaoService.getAllAdocoes();

        assertEquals(adocoes.size(), result.size());
    }

    @Test
    public void testGetAdocaoById() {
        Long id = 1L;
        AdocaoEntity adocao = new AdocaoEntity();
        adocao.setId(id);

        when(adocaoRepository.findById(id)).thenReturn(Optional.of(adocao));

        AdocaoEntity result = adocaoService.getAdocaoById(id);

        assertEquals(id, result.getId());
    }

    @Test
    public void testDeleteAdocao() {
        Long id = 1L;

        adocaoService.deleteAdocao(id);

        verify(adocaoRepository, times(1)).deleteById(id);
    }


}
