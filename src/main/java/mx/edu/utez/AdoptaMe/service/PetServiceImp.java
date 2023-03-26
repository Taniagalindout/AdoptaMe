package mx.edu.utez.AdoptaMe.service;
import mx.edu.utez.AdoptaMe.entity.Pet;
import mx.edu.utez.AdoptaMe.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImp implements PetService{
    @Autowired
    private PetRepository petRepository;

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet savePet(Pet pet) {
        petRepository.save(pet);
        return pet;
    }

    @Override
    public void updatePet(Long id, Pet updatedPet) {
        Pet pet = getPetById(id);
        if (pet != null) {
            updatedPet.setId(id);
            petRepository.save(updatedPet);
        }
    }

    @Override
    public void deletePet(Long id) {
        Pet pet = getPetById(id);
        if (pet != null) {
            petRepository.delete(pet);
        }
    }
}
