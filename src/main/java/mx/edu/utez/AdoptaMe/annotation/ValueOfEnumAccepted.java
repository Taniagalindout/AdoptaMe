package mx.edu.utez.AdoptaMe.annotation;


import mx.edu.utez.AdoptaMe.validation.ValueOfEnumAcceptedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValueOfEnumAcceptedValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValueOfEnumAccepted {

    Class<? extends Enum<?>> enumClass();

    String message() default "{adoptame.constraints.enum.accepted.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};

}
