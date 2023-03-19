package mx.edu.utez.AdoptaMe.validation;

import org.springframework.beans.factory.annotation.Autowired;
import mx.edu.utez.AdoptaMe.annotation.UsernameAccepted;
import mx.edu.utez.AdoptaMe.entity.UserAdoptame;
import mx.edu.utez.AdoptaMe.service.UserAdoptameServiceImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameAcceptedValidator implements ConstraintValidator<UsernameAccepted, String> {

    @Autowired
    private UserAdoptameServiceImpl userAdoptameService;

    @Override
    public void initialize(UsernameAccepted usernameAccepted) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean flag = false;

        if (value != null ) {

            UserAdoptame user = userAdoptameService.findUserByUsername(value);

            if (user != null && user.getId() !=  0) {
                flag = true;
            }
        }

        return flag;
    }
}
