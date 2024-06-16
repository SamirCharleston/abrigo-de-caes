package app.AbrigoCanino.repositories;

import app.AbrigoCanino.entities.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface TutorRepository extends JpaRepository<TutorEntity, Long> {
    Optional<TutorEntity> getReferenceByNome(String nome);
    boolean existsByNome(String nome);
    TutorEntity findByNome(String nome);
    List<TutorEntity> findByStatus(boolean status);
}
