package app.AbrigoCanino.controllers;

import app.AbrigoCanino.config.configuracoes.EnderecoEndPoint;
import app.AbrigoCanino.config.configuracoes.ObjetoResposta;
import app.AbrigoCanino.entities.RequerimentoEntity;
import app.AbrigoCanino.service.RequerimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/requerimento")
@CrossOrigin("*")
public class RequerimentoController {
    @Autowired
    private RequerimentoService requerimentoService;
    @PostMapping(EnderecoEndPoint.CADASTRAR)
    public ResponseEntity<ObjetoResposta<Void>> save(@RequestBody RequerimentoEntity requerimento){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(requerimentoService.save(requerimento));
            return ResponseEntity.ok(resposta);
        } catch(Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @GetMapping(EnderecoEndPoint.BUSCAR_ID)
    public ResponseEntity<ObjetoResposta<RequerimentoEntity>> findById(@RequestParam Long id){
        ObjetoResposta<RequerimentoEntity> resposta = new ObjetoResposta<>();
        try{
            resposta.setObjeto(requerimentoService.findById(id));
            return ResponseEntity.ok(resposta);
        } catch(Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @GetMapping(EnderecoEndPoint.LISTAR)
    public ResponseEntity<ObjetoResposta<List<RequerimentoEntity>>> findAll(){
        ObjetoResposta<List<RequerimentoEntity>> resposta = new ObjetoResposta<>();
        try{
            resposta.setObjeto(requerimentoService.findAll());
            return ResponseEntity.ok(resposta);
        } catch(Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @PutMapping(EnderecoEndPoint.ATUALIZAR)
    public ResponseEntity<ObjetoResposta<Void>> update(@RequestBody RequerimentoEntity requerimento){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(requerimentoService.update(requerimento));
            return ResponseEntity.ok(resposta);
        } catch(Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @DeleteMapping(EnderecoEndPoint.DELETAR)
    public ResponseEntity<ObjetoResposta<Void>> delete(@RequestParam("id") Long id){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(requerimentoService.delete(id));
            return ResponseEntity.ok(resposta);
        } catch(Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
}
