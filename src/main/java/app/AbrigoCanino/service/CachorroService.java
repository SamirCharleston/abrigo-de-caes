package app.AbrigoCanino.service;

import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
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

    public String save(CachorroEntity cachorro) throws Exception{
        cachorroRepository.save(cachorro);
        return MensagensDeSucesso.CADASTRO_SUCESSO;
    }
    public CachorroEntity findById(Long id) throws Exception{
        return cachorroRepository.findById(id).orElseThrow(
                () -> new Exception(MensagensDeErro.ID_NAO_ENCONTRADO)
        );
    }
    public List<CachorroEntity> findAll() throws Exception{
        if(cachorroRepository.findAll().isEmpty()){
            throw new Exception(MensagensDeErro.ID_NAO_ENCONTRADO);
        }
        return cachorroRepository.findAll();
    }
    public String update(CachorroEntity cachorro) throws Exception{
        cachorroRepository.save(cachorro);
        return MensagensDeSucesso.ALTERACAO_SUCESSO;
    }
    public String delete(Long id) throws Exception{
        cachorroRepository.deleteById(id);
        return MensagensDeSucesso.EXCLUSAO_SUCESSO;
    }
}
