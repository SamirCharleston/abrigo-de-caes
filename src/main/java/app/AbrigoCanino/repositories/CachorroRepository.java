package app.AbrigoCanino.repositories;

import app.AbrigoCanino.entities.CachorroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CachorroRepository  extends JpaRepository<CachorroEntity, Long> {
    Optional<CachorroEntity> getReferenceByNome(String nome);
}
