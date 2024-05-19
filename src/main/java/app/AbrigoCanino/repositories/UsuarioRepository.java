package app.AbrigoCanino.repositories;

import app.AbrigoCanino.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByNome(String nome);
    boolean existsByNome(String nome);
}
