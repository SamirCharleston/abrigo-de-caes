package app.AbrigoCanino.service;

import app.AbrigoCanino.entities.CachorroEntity;
import app.AbrigoCanino.repositories.CachorroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CachorroService {
    private final CachorroRepository cachorroRepository; // Declarar a variável cachorroRepository

    @Autowired
    public CachorroService(CachorroRepository cachorroRepository) {
        this.cachorroRepository = cachorroRepository; // Inicializar a variável cachorroRepository
    }

    public List<CachorroEntity> getAllCachorros() {
        return cachorroRepository.findAll();
    }

    public CachorroEntity getCachorroById(Long id) {
        return cachorroRepository.findById(id).orElse(null);
    }

    public CachorroEntity saveOrUpdateCachorro(CachorroEntity cachorro) {
        return cachorroRepository.save(cachorro);
    }

    public void deleteCachorro(Long id) {
        cachorroRepository.deleteById(id);
    }
}
