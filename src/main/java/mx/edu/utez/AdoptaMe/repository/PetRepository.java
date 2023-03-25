package mx.edu.utez.AdoptaMe.repository;

import mx.edu.utez.AdoptaMe.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // Método de guardado
    Pet save(Pet pet);
}
