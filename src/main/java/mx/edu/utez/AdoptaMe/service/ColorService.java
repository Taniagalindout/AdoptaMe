package mx.edu.utez.AdoptaMe.service;

import mx.edu.utez.AdoptaMe.entity.Color;

import java.util.List;
import java.util.Optional;

public interface ColorService {
    List<Color> findAllColors();

    Long countAllColors();

    Optional<Color> findColorById(Long id);
}
