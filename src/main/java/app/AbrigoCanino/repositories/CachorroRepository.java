package app.AbrigoCanino.repositories;

import app.AbrigoCanino.entities.CachorroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CachorroRepository  extends JpaRepository<CachorroEntity, Long> {
    
}
