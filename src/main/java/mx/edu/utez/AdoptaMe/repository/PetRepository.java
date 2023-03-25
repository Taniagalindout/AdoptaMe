package mx.edu.utez.AdoptaMe.repository;

import mx.edu.utez.AdoptaMe.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // Método de guardado
    Pet save(Pet pet);
    List<Pet> findAll();

    Pet findById(long id);
}
