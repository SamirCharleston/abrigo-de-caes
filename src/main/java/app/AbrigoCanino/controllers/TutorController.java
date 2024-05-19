package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.EnderecoEndPoint;
import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.entities.TutorEntity;
import app.AbrigoCanino.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/tutor")
@CrossOrigin("*")
public class TutorController {
    @Autowired
    private TutorService tutorService;
    @PostMapping(EnderecoEndPoint.CADASTRAR)
    public ResponseEntity<ObjetoResposta<Void>> save(@RequestBody TutorEntity tutor){
        ObjetoResposta<Void> resposta = new ObjetoResposta<Void>();
        try{
            resposta.setMensagem(tutorService.save(tutor));
            return ResponseEntity.ok(resposta);
        } catch(Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @GetMapping(EnderecoEndPoint.BUSCAR_ID)
    public ResponseEntity<ObjetoResposta<TutorEntity>> findById(@RequestParam Long id){
        ObjetoResposta<TutorEntity> resposta = new ObjetoResposta<TutorEntity>();
        try{
            resposta.setObjeto(tutorService.findById(id));
            return ResponseEntity.ok(resposta);
        } catch(Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @GetMapping(EnderecoEndPoint.LISTAR)
    public ResponseEntity<ObjetoResposta<List<TutorEntity>>> findAll(){
        ObjetoResposta<List<TutorEntity>> resposta = new ObjetoResposta<List<TutorEntity>>();
        try{
            resposta.setObjeto(tutorService.findAll());
            return ResponseEntity.ok(resposta);
        } catch(Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @PutMapping(EnderecoEndPoint.ATUALIZAR)
    public ResponseEntity<ObjetoResposta<Void>> update(@RequestBody TutorEntity tutor){
        ObjetoResposta<Void> resposta = new ObjetoResposta<Void>();
        try{
            resposta.setMensagem(tutorService.update(tutor));
            return ResponseEntity.ok(resposta);
        } catch(Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @DeleteMapping(EnderecoEndPoint.DELETAR)
    public ResponseEntity<ObjetoResposta<Void>> delete(@RequestParam("id") Long id){
        ObjetoResposta<Void> resposta = new ObjetoResposta<Void>();
        try{
            resposta.setMensagem(tutorService.delete(id));
            return ResponseEntity.ok(resposta);
        } catch(Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
}
