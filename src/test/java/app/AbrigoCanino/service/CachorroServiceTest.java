package app.AbrigoCanino.service;

import app.AbrigoCanino.entities.CachorroEntity;
import app.AbrigoCanino.repositories.CachorroRepository;
import app.AbrigoCanino.service.CachorroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CachorroServiceTest {

    private CachorroRepository cachorroRepository;
    private CachorroService cachorroService;

    @BeforeEach
    public void setUp() {
        cachorroRepository = Mockito.mock(CachorroRepository.class);
        cachorroService = new CachorroService(cachorroRepository);
    }

    @Test
    public void testGetAllCachorros() {
        // Mocking the behavior of cachorroRepository.findAll() method
        List<CachorroEntity> cachorros = new ArrayList<>();
        Mockito.when(cachorroRepository.findAll()).thenReturn(cachorros);

        // Testing the getAllCachorros method of CachorroService
        List<CachorroEntity> result = cachorroService.getAllCachorros();
        Assertions.assertEquals(cachorros, result);
    }

    @Test
    public void testGetCachorroById() {
        // Mocking the behavior of cachorroRepository.findById() method
        Long id = 1L;
        CachorroEntity cachorro = new CachorroEntity();
        Mockito.when(cachorroRepository.findById(id)).thenReturn(Optional.of(cachorro));

        // Testing the getCachorroById method of CachorroService
        CachorroEntity result = cachorroService.getCachorroById(id);
        Assertions.assertEquals(cachorro, result);
    }

    @Test
    public void testSaveOrUpdateCachorro() {
        // Mocking the behavior of cachorroRepository.save() method
        CachorroEntity cachorro = new CachorroEntity();
        Mockito.when(cachorroRepository.save(cachorro)).thenReturn(cachorro);

        // Testing the saveOrUpdateCachorro method of CachorroService
        CachorroEntity result = cachorroService.saveOrUpdateCachorro(cachorro);
        Assertions.assertEquals(cachorro, result);
    }

    @Test
    public void testDeleteCachorro() {
        // Mocking the behavior of cachorroRepository.deleteById() method
        Long id = 1L;

        // Testing the deleteCachorro method of CachorroService
        cachorroService.deleteCachorro(id);

        // Verifying that cachorroRepository.deleteById() was called with the correct argument
        Mockito.verify(cachorroRepository, Mockito.times(1)).deleteById(id);
    }
}

