package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.ObjtetoResposta;
import app.AbrigoCanino.entities.ColaboradorEntity;
import app.AbrigoCanino.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/")
public class ColaboradorController {
    @Autowired
    private ColaboradorService colaboradorService;
    public ResponseEntity<ObjtetoResposta<Void>> save(@RequestBody ColaboradorEntity colaborador){
        ObjtetoResposta<Void> resposta = new ObjtetoResposta<>();
        try{
            resposta.setMensagem(colaboradorService.save(colaborador));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    public ResponseEntity<ObjtetoResposta<ColaboradorEntity>> findById(@RequestParam Long id){
        ObjtetoResposta<ColaboradorEntity> resposta = new ObjtetoResposta<>();
        try{
            resposta.setObjeto(colaboradorService.findById(id));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    public ResponseEntity<ObjtetoResposta<List<ColaboradorEntity>>> findAll(){
        ObjtetoResposta<List<ColaboradorEntity>> resposta = new ObjtetoResposta<>();
        try{
            resposta.setObjeto(colaboradorService.findAll());
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    public ResponseEntity<ObjtetoResposta<Void>> update(@RequestBody ColaboradorEntity colaborador){
        ObjtetoResposta<Void> resposta = new ObjtetoResposta<>();
        try{
            resposta.setMensagem(colaboradorService.update(colaborador));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    public ResponseEntity<ObjtetoResposta<Void>> delete(@RequestParam("id") Long id){
        ObjtetoResposta<Void> resposta = new ObjtetoResposta<>();
        try{
            resposta.setMensagem(colaboradorService.delete(id));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
}
