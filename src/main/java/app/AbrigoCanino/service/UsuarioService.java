package app.AbrigoCanino.service;

import app.AbrigoCanino.auth.Authorization;
import app.AbrigoCanino.auth.Usuario;
import app.AbrigoCanino.dtos.UsuarioParaAutenticarDTO;
import app.AbrigoCanino.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Long cadastrar(UsuarioParaAutenticarDTO usuario) throws Exception {
        //Define o id para nulo para que o jpa possa crialo
        if(usuarioRepository.existsByUsername(usuario.getUsername())){ //Verifica se o nome ja existe no banco
            throw new Exception("Usuario ja cadastrado");
        }

        Usuario usuarioASalvar = new Usuario();

        if(usuario.getRole() != null && usuario.getRole().equals("ADMIN")){
            usuarioASalvar.setRole(Authorization.ADMIN);
        } else {
            usuarioASalvar.setRole(Authorization.USER);
        }

        usuarioASalvar.setUsername(usuario.getUsername());
        usuarioASalvar.setPassword(usuario.getPassword());

        return usuarioRepository.save(usuarioASalvar).getId(); //Salva o usuario, e retorna o id do usuario salvo
    }
}
