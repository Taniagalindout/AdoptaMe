package mx.edu.utez.AdoptaMe.controller;

import mx.edu.utez.AdoptaMe.entity.Pet;
import mx.edu.utez.AdoptaMe.repository.PetRepository;
import mx.edu.utez.AdoptaMe.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetService petService;
    @PostMapping("/pet")
    public Pet savePet(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    @GetMapping("/pets")
    public String showPetList(Model model) {
        List<Pet> pets = petService.getAllPets();
        model.addAttribute("pets", pets);
        return "pet-list";
    }

    @GetMapping("/pets/{id}")
    public String showPetDetails(@PathVariable("id") Long id, Model model) {
        Pet pet = petService.getPetById(id);
        if (pet != null) {
            model.addAttribute("pet", pet);
            return "pet-details";
        } else {
            return "redirect:/pets";
        }
    }

    @GetMapping("/pets/{id}/edit")
    public String showEditPetForm(@PathVariable("id") Long id, Model model) {
        Pet pet = petService.getPetById(id);
        if (pet != null) {
            model.addAttribute("pet", pet);
            return "pet-form";
        } else {
            return "redirect:/pets";
        }
    }

    @PostMapping("/pets/{id}/edit")
    public String submitEditPetForm(@PathVariable("id") Long id, @ModelAttribute("pet") Pet pet, Model model) {
        petService.updatePet(id, pet);
        return "redirect:/pets/" + id;
    }

    @GetMapping("/pets/{id}/delete")
    public String showDeletePetConfirmation(@PathVariable("id") Long id, Model model) {
        Pet pet = petService.getPetById(id);
        if (pet != null) {
            model.addAttribute("pet", pet);
            return "pet-delete-confirmation";
        } else {
            return "redirect:/pets";
        }
    }

    @PostMapping("/pets/{id}/delete")
    public String submitDeletePetForm(@PathVariable("id") Long id, Model model) {
        petService.deletePet(id);
        return "redirect:/pets";
    }
}
