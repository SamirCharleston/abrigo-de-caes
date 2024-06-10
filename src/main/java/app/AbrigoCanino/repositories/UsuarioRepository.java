package app.AbrigoCanino.repositories;

import app.AbrigoCanino.auth.Usuario;
import app.AbrigoCanino.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String nome);
    boolean existsByUsername(String nome);
}
