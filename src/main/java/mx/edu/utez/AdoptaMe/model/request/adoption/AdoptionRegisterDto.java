package mx.edu.utez.AdoptaMe.model.request.adoption;

import mx.edu.utez.AdoptaMe.annotation.PetAccepted;
import mx.edu.utez.AdoptaMe.annotation.UsernameAccepted;

public class AdoptionRegisterDto {

    @UsernameAccepted(message = "Este usuario no es aceptado")
    private String username;

    @PetAccepted(message = "Esta mascota no es aceptada")
    private Long petId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }
}
