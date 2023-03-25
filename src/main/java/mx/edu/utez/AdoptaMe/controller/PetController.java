package mx.edu.utez.AdoptaMe.controller;
import mx.edu.utez.AdoptaMe.entity.Pet;
import mx.edu.utez.AdoptaMe.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
    @Autowired
    private PetRepository petRepository;

    @PostMapping("/pet")
    public Pet savePet(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }
}
