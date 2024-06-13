package app.AbrigoCanino.controllers;

import app.AbrigoCanino.config.configuracoesEspecificas.EnderecoEndPoint;
import app.AbrigoCanino.config.configuracoesEspecificas.ObjetoResposta;
import app.AbrigoCanino.entities.CachorroEntity;
import app.AbrigoCanino.service.CachorroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/cachorro")
@CrossOrigin("*")
public class CachorroController {

    @Autowired
    private CachorroService cachorroService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(EnderecoEndPoint.CADASTRAR)
    public ResponseEntity<ObjetoResposta<Void>> save(@RequestBody CachorroEntity cachorro){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(cachorroService.save(cachorro));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @GetMapping(EnderecoEndPoint.BUSCAR_ID)
    public ResponseEntity<ObjetoResposta<CachorroEntity>> findById(@RequestParam Long id){
        ObjetoResposta<CachorroEntity> resposta = new ObjetoResposta<>();
        try{
            resposta.setObjeto(cachorroService.findById(id));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @GetMapping(EnderecoEndPoint.LISTAR)
    public ResponseEntity<ObjetoResposta<List<CachorroEntity>>> findAll(){
        ObjetoResposta<List<CachorroEntity>> resposta = new ObjetoResposta<>();
        try{
            resposta.setObjeto(cachorroService.findAll());
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(EnderecoEndPoint.ATUALIZAR)
    public ResponseEntity<ObjetoResposta<Void>> update(@RequestBody CachorroEntity cachorro){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(cachorroService.update(cachorro));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(EnderecoEndPoint.DELETAR)
    public ResponseEntity<ObjetoResposta<Void>> delete(@RequestParam("id") Long id){
        ObjetoResposta<Void> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem(cachorroService.delete(id));
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
}
