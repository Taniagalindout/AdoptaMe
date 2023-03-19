package mx.edu.utez.AdoptaMe.model.request.adoption;

import mx.edu.utez.AdoptaMe.annotation.ValueOfEnumAccepted;
import mx.edu.utez.AdoptaMe.enums.StateAdoptionApplication;

import jakarta.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdoptionUpdateDto {

    @NotNull(message = "Debe indicar un valor para este campo")
    private Long id;

    @ValueOfEnumAccepted(enumClass = StateAdoptionApplication.class, message = "Este valor no es aceptado para el estado de la adopción")
    @NotEmpty(message = "Debe de indicar un valor")
    private String state;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
