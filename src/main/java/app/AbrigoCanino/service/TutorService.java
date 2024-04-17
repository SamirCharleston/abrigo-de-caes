package app.AbrigoCanino.service;

import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.entities.TutorEntity;
import app.AbrigoCanino.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;

    public String save(TutorEntity tutor) throws Exception {
        if (tutor.getIdade()<18){
            throw new Exception("Tutor nao deve ser menor de idade.");
        }
        tutorRepository.save(tutor);
        return MensagensDeSucesso.CADASTRO_SUCESSO;
    }

    public TutorEntity findById(Long id) throws Exception {
        return tutorRepository.findById(id).orElseThrow(() -> new Exception(MensagensDeErro.ID_NAO_ENCONTRADO));
    }

    public List<TutorEntity> findAll() throws Exception {
        if(tutorRepository.findAll().isEmpty())
            throw new Exception(MensagensDeErro.LISTA_VAZIA);
        return tutorRepository.findAll();
    }

    public String update(TutorEntity tutor) {
        tutorRepository.save(tutor);
        return MensagensDeSucesso.ALTERACAO_SUCESSO;
    }

    public String delete(Long id) throws Exception{
        if(!tutorRepository.existsById(id)){
            throw new Exception(MensagensDeErro.ID_NAO_ENCONTRADO);
        }
        tutorRepository.delete(findById(id));
        return MensagensDeSucesso.EXCLUSAO_SUCESSO;
    }
}
