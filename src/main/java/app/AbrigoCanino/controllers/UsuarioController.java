package app.AbrigoCanino.controllers;

import app.AbrigoCanino.auth.Usuario;
import app.AbrigoCanino.config.configuracoes.ObjetoResposta;
import app.AbrigoCanino.dtos.UsuarioAutenticadoDTO;
import app.AbrigoCanino.dtos.UsuarioParaAutenticarDTO;
import app.AbrigoCanino.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/register")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ObjetoResposta<Long>> cadastrar(@RequestBody @Valid UsuarioParaAutenticarDTO usuario) {
        ObjetoResposta<Long> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem("Cadastrado - id: " + usuarioService.cadastrar(usuario).toString());
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
}
