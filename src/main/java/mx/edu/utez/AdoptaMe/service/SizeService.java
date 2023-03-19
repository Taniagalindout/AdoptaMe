package mx.edu.utez.AdoptaMe.service;

import mx.edu.utez.AdoptaMe.entity.Size;

import java.util.List;
import java.util.Optional;

public interface SizeService {
    List<Size> findAllSizes();

    Long countAllSizes();

    Optional<Size> findSizeById(Long id);
}
