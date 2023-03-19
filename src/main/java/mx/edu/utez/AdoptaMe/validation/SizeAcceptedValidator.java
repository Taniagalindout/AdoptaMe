package mx.edu.utez.AdoptaMe.validation;

import org.springframework.beans.factory.annotation.Autowired;
import mx.edu.utez.AdoptaMe.annotation.SizeAccepted;
import mx.edu.utez.AdoptaMe.entity.Size;
import mx.edu.utez.AdoptaMe.service.SizeServiceImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class SizeAcceptedValidator implements ConstraintValidator<SizeAccepted, Size> {

    @Autowired
    private SizeServiceImpl sizeService;

    @Override
    public void initialize(SizeAccepted sizeAccepted) {

    }

    @Override
    public boolean isValid(Size value, ConstraintValidatorContext context) {
        boolean flag = false;

        if (value.getId() != 0) {
            Optional<Size> item = sizeService.findSizeById(value.getId());

            if (item.isPresent()) {
                flag = true;
            }
        }

        return flag;
    }
}
