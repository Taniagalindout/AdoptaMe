package mx.edu.utez.AdoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.edu.utez.AdoptaMe.entity.Personality;

@Repository
public interface PersonalityRepository extends JpaRepository<Personality, Long> {
}
