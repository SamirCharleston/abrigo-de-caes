package app.AbrigoCanino.service;

import app.AbrigoCanino.entities.AdocaoEntity;
import app.AbrigoCanino.repositories.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdocaoService {

        @Autowired
        private AdocaoRepository adocaoRepository;

        public List<AdocaoEntity> getAllAdocoes() {
            return adocaoRepository.findAll();
        }

        public AdocaoEntity getAdocaoById(Long id) {
            return adocaoRepository.findById(id).orElse(null);
        }


//        public AdocaoEntity saveOrUpdateAdocao(AdocaoService adocao) {
//            return adocaoRepository.save(adocao);
//        }

        public void deleteAdocao(Long id) {
            adocaoRepository.deleteById(id);
        }
}
