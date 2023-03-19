package mx.edu.utez.AdoptaMe.validation;

import org.springframework.beans.factory.annotation.Autowired;
import mx.edu.utez.AdoptaMe.annotation.PetAccepted;
import mx.edu.utez.AdoptaMe.entity.Pet;
import mx.edu.utez.AdoptaMe.service.PetServiceImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PetAcceptedValidator implements ConstraintValidator<PetAccepted, Long> {

    @Autowired
    private PetServiceImpl petService;

    @Override
    public void initialize(PetAccepted petAccepted) {

    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        boolean flag = false;

        if ( value != 0) {

            Optional<Pet> pet = petService.findPetById(value);

            if (pet.isPresent()) {
                flag = true;
            }
        }

        return flag;
    }
}
