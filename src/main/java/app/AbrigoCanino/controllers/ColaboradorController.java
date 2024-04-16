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
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public ResponseEntity<ColaboradorEntity> findById(@RequestParam Long id){
        try{
            return ResponseEntity.ok(colaboradorService.findById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
