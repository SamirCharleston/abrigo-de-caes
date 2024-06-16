package app.AbrigoCanino.service;

import app.AbrigoCanino.config.configuracoesEspecificas.MensagensDeErro;
import app.AbrigoCanino.config.configuracoesEspecificas.MensagensDeSucesso;
import app.AbrigoCanino.entities.AbstractEntity;
import app.AbrigoCanino.entities.TutorEntity;
import app.AbrigoCanino.repositories.TutorRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    EntityManager entityManager;

    @Transactional
    public String save(TutorEntity tutor) throws Exception {
        boolean anularId = true;
        if(!verificaMaioridade(tutor.getIdade())){
            throw new Exception("Tutor nao deve ser menor de idade.");
        }
        if(tutor.getContato().isBlank()){
            throw new Exception("Contato nao deve ser em branco");
        }
        if(tutorRepository.existsByNome(tutor.getNome())){
            if(tutor.isStatus()) {
                throw new DataIntegrityViolationException("Este tutor ja esta cadastrado");
            } else {
                tutor = tutorRepository.findByNome(tutor.getNome());
                tutor.setStatus(true);
                anularId = false;
            }
        }

        if(anularId){
            tutor.setId(null);
        }

        tutorRepository.save(tutor);
        return MensagensDeSucesso.CADASTRO_SUCESSO;
    }



    public TutorEntity findById(Long id) throws Exception {
        TutorEntity tutor = tutorRepository.findById(id).orElseThrow(() -> new Exception(MensagensDeErro.ID_NAO_ENCONTRADO));
        if(!tutor.isStatus()){
            throw new Exception(MensagensDeErro.ID_NAO_ENCONTRADO);
        }
        return tutor;
    }

    public List<TutorEntity> findAll() {
        List<TutorEntity> tutores = tutorRepository.findAll();
        if (tutores.isEmpty()) {
            throw new RuntimeException("Sem tutuores salvos!");
        }

        List<TutorEntity> tutors = tutores
                .stream()
                .filter(AbstractEntity::isStatus)
                .collect(Collectors.toList());

        if (tutors.isEmpty()) {
            throw new RuntimeException("Sem tutores disponiveis!");
        }

        return tutors;
    }

    public String update(TutorEntity tutor) throws Exception {

        tutorRepository.findById(tutor.getId()).orElseThrow(() -> new IllegalStateException("Tutor nao encontrado"));

        tutorRepository.save(tutor);
        return MensagensDeSucesso.ALTERACAO_SUCESSO;
    }

    public String delete(Long id) {
//        if(!tutorRepository.existsById(id)){
//            return MensagensDeErro.ID_NAO_ENCONTRADO;
//        }
//        tutorRepository.deleteById(id);
        TutorEntity tutor = tutorRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor nao encontrado!"));

        tutor.setStatus(false);
        tutorRepository.save(tutor);

        return MensagensDeSucesso.EXCLUSAO_SUCESSO;
    }
    public boolean verificaMaioridade(int idade){
        return idade >= 18;
    }
}
