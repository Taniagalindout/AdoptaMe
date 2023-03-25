package mx.edu.utez.AdoptaMe.service;
import mx.edu.utez.AdoptaMe.entity.Pet;
import mx.edu.utez.AdoptaMe.repository.PetRepository;
import mx.edu.utez.AdoptaMe.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImp implements PetService{
    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }
}
