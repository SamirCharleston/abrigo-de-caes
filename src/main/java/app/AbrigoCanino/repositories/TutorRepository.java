package app.AbrigoCanino.repositories;

import app.AbrigoCanino.entities.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface TutorRepository extends JpaRepository<TutorEntity, Long> {

}
