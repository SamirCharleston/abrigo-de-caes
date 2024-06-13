package app.AbrigoCanino.service;

import app.AbrigoCanino.config.configuracoesEspecificas.MensagensDeErro;
import app.AbrigoCanino.config.configuracoesEspecificas.MensagensDeSucesso;
import app.AbrigoCanino.entities.ColaboradorEntity;
import app.AbrigoCanino.repositories.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    public String save(ColaboradorEntity colaborador) throws Exception{
        if(colaborador == null){
            throw new Exception(MensagensDeErro.OBJETO_NULO);
        }
        colaboradorRepository.save(colaborador);
        return MensagensDeSucesso.CADASTRO_SUCESSO;
    }

    public ColaboradorEntity findById(Long id) throws Exception {
        Optional<ColaboradorEntity> colaboradorOptional = colaboradorRepository.findById(id);
        if (colaboradorOptional.isPresent()) {
            return colaboradorOptional.get();
        } else {
            throw new Exception(MensagensDeErro.ID_NAO_ENCONTRADO);
        }
    }

    public List<ColaboradorEntity> findAll() throws Exception{
        if(colaboradorRepository.findAll().isEmpty()){
            throw new Exception(MensagensDeErro.ID_NAO_ENCONTRADO);
        }
        return colaboradorRepository.findAll();
    }
    public String update(ColaboradorEntity colaborador) throws Exception{
        colaboradorRepository.save(colaborador);
        return MensagensDeSucesso.ALTERACAO_SUCESSO;
    }
    public String delete(Long id) throws Exception{
        colaboradorRepository.deleteById(id);
        return MensagensDeSucesso.EXCLUSAO_SUCESSO;
    }
}
