package app.AbrigoCanino.service;

import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.entities.CachorroEntity;
import app.AbrigoCanino.entities.RequerimentoEntity;
import app.AbrigoCanino.entities.TutorEntity;
import app.AbrigoCanino.repositories.CachorroRepository;
import app.AbrigoCanino.repositories.RequerimentoRepository;
import app.AbrigoCanino.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequerimentoService {
    @Autowired
    private RequerimentoRepository requerimentoRepository;
    @Autowired
    TutorRepository tutorRepository;
    @Autowired
    private CachorroRepository cachorroRepository;
    public String save(RequerimentoEntity requerimento) throws Exception {

        if(requerimento.getAutorDoRequerimento() == null){
            throw new Exception("Autor nao pode ser nulo");
        }
        TutorEntity tutor = tutorRepository
                .getReferenceByNome(requerimento.getAutorDoRequerimento().getNome())
                .orElseThrow(() -> new Exception("Autor nao encontrado"));

        if(requerimento.getCaesRequeridos().isEmpty()){
            throw new Exception("A lista de caes nao pode ser vazia");
        }

        List<CachorroEntity> cachorroEntities = new ArrayList<CachorroEntity>();
        for(CachorroEntity c : requerimento.getCaesRequeridos()){
            if(c.getNome() == null){
                throw new Exception("O nome do cachorro nao pode ser nulo");
            }
            cachorroEntities.add(cachorroRepository
                    .getReferenceByNome(c.getNome())
                    .orElseThrow(() -> new Exception("O cachorro " + c.getNome() + "  nao pode ser encontrado"))
            );
        };

        requerimento.setAutorDoRequerimento(tutor);
        requerimento.setCaesRequeridos(cachorroEntities);

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

    public String update(RequerimentoEntity requerimento) throws Exception {
        RequerimentoEntity requerimentoExistente = requerimentoRepository.findById(requerimento.getId())
                .orElseThrow(() -> new Exception(MensagensDeErro.ID_NAO_ENCONTRADO));
        requerimentoRepository.save(requerimentoExistente);
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
