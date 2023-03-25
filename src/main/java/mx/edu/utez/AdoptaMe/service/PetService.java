package mx.edu.utez.AdoptaMe.service;

import mx.edu.utez.AdoptaMe.entity.Pet;

import java.util.List;

public interface PetService {
    List<Pet> getAllPets();

    Pet getPetById(Long id);

    void savePet(Pet pet);

    void updatePet(Long id, Pet updatedPet);

    void deletePet(Long id);

}
