package app.AbrigoCanino.service;

import app.AbrigoCanino.configuracoes.MensagensDeErro;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.entities.TutorEntity;
import app.AbrigoCanino.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;

    @Transactional
    public String save(TutorEntity tutor) throws Exception {
        if(!verificaMaioridade(tutor.getIdade())){
            throw new Exception("Tutor nao deve ser menor de idade.");
        }
        if(tutor.getContato().isBlank()){
            throw new Exception("Contato nao deve ser em branco");
        }
        if(tutor.getDataRequerimento().isBefore(LocalDate.now())){
            throw new Exception("Data de requerimento nao pode ser anterior a data atual");
        }
        if(tutorRepository.existsByNome(tutor.getNome())){
            throw new DataIntegrityViolationException("Este tutor ja esta cadastrado");
        }
        tutor.setId(null);

        tutorRepository.save(tutor);
        return MensagensDeSucesso.CADASTRO_SUCESSO;
    }



    public TutorEntity findById(Long id) throws Exception {
        return tutorRepository.findById(id).orElseThrow(() -> new Exception(MensagensDeErro.ID_NAO_ENCONTRADO));
    }

    public List<TutorEntity> findAll() {
        List<TutorEntity> tutores = tutorRepository.findAll();
        if (tutores.isEmpty()) {
            return Collections.emptyList(); // Retorna uma lista vazia se não houver tutores
        }
        return tutores;
    }

    public String update(TutorEntity tutor) {
        tutorRepository.save(tutor);
        return MensagensDeSucesso.ALTERACAO_SUCESSO;
    }

    public String delete(Long id) {
        if(!tutorRepository.existsById(id)){
            return MensagensDeErro.ID_NAO_ENCONTRADO;
        }
        tutorRepository.deleteById(id);
        return MensagensDeSucesso.EXCLUSAO_SUCESSO;
    }
    public boolean verificaMaioridade(int idade){
        return idade >= 18;
    }
}
