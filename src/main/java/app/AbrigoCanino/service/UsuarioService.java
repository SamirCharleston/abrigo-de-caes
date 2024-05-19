package app.AbrigoCanino.service;

import app.AbrigoCanino.dtos.UsuarioAutenticadoDTO;
import app.AbrigoCanino.dtos.UsuarioParaAutenticarDTO;
import app.AbrigoCanino.entities.UsuarioEntity;
import app.AbrigoCanino.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioAutenticadoDTO autenticar(UsuarioParaAutenticarDTO usuario) throws Exception {
        //Busca o usuario no banco e caso nao encontre lanca uma excecao
        UsuarioEntity usuarioEncontrado = usuarioRepository
                .findByNome(usuario.getNome())
                .orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));

        //Verifica se a senha esta correta e caso nao esteja lanca uma excecao,
        //neste momento nao eh necessario verificar se os nomes sao iguais pois
        //se existe eh pq eh igual
        if (!usuarioEncontrado.getSenha().equals(usuario.getSenha())) {
            throw new AuthenticationException("Falha na autenticacao");
        }

        UsuarioAutenticadoDTO usuarioAutenticado = new UsuarioAutenticadoDTO();

        //Simula o retorno de uma informacao de sessao
        usuarioAutenticado.setCodigo(usuario.getNome() + usuario.getSenha());

        return usuarioAutenticado;
    }
    public Long cadastrar(UsuarioParaAutenticarDTO usuario) throws Exception {
        //Define o id para nulo para que o jpa possa crialo
        if(usuarioRepository.existsByNome(usuario.getNome())){ //Verifica se o nome ja existe no banco
            throw new Exception("Usuario ja cadastrado");
        }

        UsuarioEntity usuarioASalvar = new UsuarioEntity();
        usuarioASalvar.setNome(usuario.getNome());
        usuarioASalvar.setSenha(usuario.getSenha());

        return usuarioRepository.save(usuarioASalvar).getId(); //Salva o usuario, e retorna o id do usuario salvo
    }
}
