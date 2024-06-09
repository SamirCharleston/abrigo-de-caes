package app.AbrigoCanino.controllers;

import app.AbrigoCanino.configuracoes.ObjetoResposta;
import app.AbrigoCanino.dtos.UsuarioAutenticadoDTO;
import app.AbrigoCanino.dtos.UsuarioParaAutenticarDTO;
import app.AbrigoCanino.entities.UsuarioEntity;
import app.AbrigoCanino.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "api/usuario")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ObjetoResposta<Long>> cadastrar(@RequestBody @Valid UsuarioParaAutenticarDTO usuario) {
        ObjetoResposta<Long> resposta = new ObjetoResposta<>();
        try{
            resposta.setMensagem("id: " + usuarioService.cadastrar(usuario).toString());
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }
    @PostMapping("/autenticar")
    public ResponseEntity<ObjetoResposta<UsuarioAutenticadoDTO>>
    autenticar(HttpSession session, @RequestBody @Valid UsuarioParaAutenticarDTO usuario) {
        ObjetoResposta<UsuarioAutenticadoDTO> resposta = new ObjetoResposta<>();
        try{
            resposta.setObjeto(usuarioService.autenticar(usuario));//Verifica a autenticacao do usuario
            session.setAttribute("nomeDoUsuario", usuario.getNome());//Inicia a secao do usuario
            return ResponseEntity.ok(resposta);
        } catch (Exception e){
            resposta.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(resposta);
        }
    }

}
