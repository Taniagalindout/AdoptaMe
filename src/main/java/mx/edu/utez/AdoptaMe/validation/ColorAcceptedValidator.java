package mx.edu.utez.AdoptaMe.validation;

import org.springframework.beans.factory.annotation.Autowired;
import mx.edu.utez.AdoptaMe.annotation.ColorAccepted;
import mx.edu.utez.AdoptaMe.entity.Color;
import mx.edu.utez.AdoptaMe.service.ColorServiceImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ColorAcceptedValidator implements ConstraintValidator<ColorAccepted, Color> {

    @Autowired
    private ColorServiceImpl colorService;

    @Override
    public void initialize(ColorAccepted colorAccepted) {

    }

    @Override
    public boolean isValid(Color value, ConstraintValidatorContext context) {
        boolean flag = false;

        if (value.getId() != 0) {
            Optional<Color> item = colorService.findColorById(value.getId());

            if (item.isPresent()) {
                flag = true;
            }
        }

        return flag;
    }
}