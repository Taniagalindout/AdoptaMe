package mx.edu.utez.AdoptaMe.controller;

import jakarta.validation.Valid;
import mx.edu.utez.AdoptaMe.entity.Pet;
import mx.edu.utez.AdoptaMe.repository.PetRepository;
import mx.edu.utez.AdoptaMe.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PetController {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetService petService;
    @PostMapping("/pet")
    public ResponseEntity<Object> savePet(@Valid @RequestBody Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores de validación, retorna una respuesta de error
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        // Realiza todas las validaciones necesarias antes de guardar la mascota
        if (!pet.getType().equalsIgnoreCase("perro") && !pet.getType().equalsIgnoreCase("gato")) {
            return new ResponseEntity<>("El tipo de mascota debe ser 'perro' o 'gato'", HttpStatus.BAD_REQUEST);
        }

        // Validación adicional ...

        // Si todas las validaciones pasan, guarda la mascota en la base de datos
        Pet savedPet = petService.savePet(pet);

        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
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
    public String submitEditPetForm(@PathVariable("id") Long id, @Valid @ModelAttribute("pet") Pet pet, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pet-form";
        }
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
