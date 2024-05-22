package app.AbrigoCanino.repositories;

import app.AbrigoCanino.entities.CachorroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CachorroRepository  extends JpaRepository<CachorroEntity, Long> {
    Optional<CachorroEntity> getReferenceByNome(String nome);
    List<CachorroEntity> findByStatus(boolean status);
}
