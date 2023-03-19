package mx.edu.utez.AdoptaMe.validation;

import org.springframework.beans.factory.annotation.Autowired;
import mx.edu.utez.AdoptaMe.annotation.PersonalityAccepted;
import mx.edu.utez.AdoptaMe.entity.Personality;
import mx.edu.utez.AdoptaMe.service.PersonalityServiceImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PersonalityAcceptedValidator  implements ConstraintValidator<PersonalityAccepted, Personality> {

    @Autowired
    private PersonalityServiceImpl personalityService;

    @Override
    public void initialize(PersonalityAccepted personalityAccepted) {

    }

    @Override
    public boolean isValid(Personality value, ConstraintValidatorContext context) {
        boolean flag = false;

        if (value.getId() != 0) {
            Optional<Personality> item = personalityService.findPersonalityById(value.getId());

            if (item.isPresent()) {
                flag = true;
            }
        }

        return flag;
    }
}
