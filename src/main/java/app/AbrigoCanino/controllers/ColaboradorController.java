package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.EnderecoEndPoint;
import app.AbrigoCanino.configuracoes.MensagensDeSucesso;
import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.entities.ColaboradorEntity;
import app.AbrigoCanino.service.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/colaborador")
public class ColaboradorController {
    @Autowired
    private ColaboradorService colaboradorService;
    @PostMapping(EnderecoEndPoint.CADASTRAR)
    public ResponseEntity<ObjetoResposta<Void>> save(@RequestBody @Valid ColaboradorEntity colaborador){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(colaboradorService.save(colaborador));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @GetMapping(EnderecoEndPoint.BUSCAR_ID)
    public ResponseEntity<ObjetoResposta<ColaboradorEntity>> findById(@PathVariable Long id){
        ObjetoResposta<ColaboradorEntity> resposta = new ObjetoResposta<>();
        try{
            resposta.setObjeto(colaboradorService.findById(id));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @GetMapping(EnderecoEndPoint.LISTAR)
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
    @PutMapping(EnderecoEndPoint.ATUALIZAR)
    public ResponseEntity<ObjetoResposta<Void>> update(@RequestBody @Valid ColaboradorEntity colaborador){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(colaboradorService.update(colaborador));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @DeleteMapping("/colaborador")
    public ResponseEntity<ObjetoResposta<Void>> delete(@RequestParam("id") Long id) {
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try {
            String mensagem = colaboradorService.delete(id);
            resposta.setMensagem(mensagem);
            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    }

