package app.AbrigoCanino.service;

import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.entities.RequerimentoEntity;
import app.AbrigoCanino.repositories.RequerimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequerimentoService {
    @Autowired
    private RequerimentoRepository requerimentoRepository;
    public String save(RequerimentoEntity requerimento) throws Exception {
        requerimentoRepository.save(requerimento);
        return MensagensDeSucesso.CADASTRO_SUCESSO;
    }

    public RequerimentoEntity findById(Long id) throws Exception {
        return requerimentoRepository.findById(id).orElseThrow(() -> new Exception(MensagensDeErro.ID_NAO_ENCONTRADO));
    }

    public List<RequerimentoEntity> findAll() throws Exception {
        if(requerimentoRepository.findAll().isEmpty())
            throw new Exception(MensagensDeErro.LISTA_VAZIA);
        return requerimentoRepository.findAll();
    }

    public String update(RequerimentoEntity requerimento) {
        requerimentoRepository.save(requerimento);
        return MensagensDeSucesso.ALTERACAO_SUCESSO;
    }

    public String delete(Long id) throws Exception{
        if(!requerimentoRepository.existsById(id)){
            throw new Exception(MensagensDeErro.ID_NAO_ENCONTRADO);
        }
        requerimentoRepository.delete(findById(id));
        return MensagensDeSucesso.EXCLUSAO_SUCESSO;
    }
}
