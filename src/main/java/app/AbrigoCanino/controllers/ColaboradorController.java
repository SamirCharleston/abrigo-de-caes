package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.entities.ColaboradorEntity;
import app.AbrigoCanino.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/colaborador")
public class ColaboradorController {
    @Autowired
    private ColaboradorService colaboradorService;
    @PostMapping("/cadastrar")
    public ResponseEntity<ObjetoResposta<Void>> save(@RequestBody ColaboradorEntity colaborador){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(colaboradorService.save(colaborador));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @GetMapping("/buscar-todos")
    public ResponseEntity<ObjetoResposta<ColaboradorEntity>> findById(@RequestParam Long id){
        ObjetoResposta<ColaboradorEntity> resposta = new ObjetoResposta<>();
        try{
            resposta.setObjeto(colaboradorService.findById(id));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    public ResponseEntity<ObjetoResposta<List<ColaboradorEntity>>> findAll(){
        ObjetoResposta<List<ColaboradorEntity>> resposta = new ObjetoResposta<>();
        try{
            resposta.setObjeto(colaboradorService.findAll());
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    public ResponseEntity<ObjetoResposta<Void>> update(@RequestBody ColaboradorEntity colaborador){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(colaboradorService.update(colaborador));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    public ResponseEntity<ObjetoResposta<Void>> delete(@RequestParam("id") Long id){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(colaboradorService.delete(id));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
}
