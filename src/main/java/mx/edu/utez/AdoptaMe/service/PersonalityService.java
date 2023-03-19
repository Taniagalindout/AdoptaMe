package mx.edu.utez.AdoptaMe.service;

import mx.edu.utez.AdoptaMe.entity.Personality;

import java.util.List;
import java.util.Optional;

public interface PersonalityService {
    List<Personality> findAllPersonalities();

    Long countAllPersonalities();

    Optional<Personality> findPersonalityById(Long id);
}
