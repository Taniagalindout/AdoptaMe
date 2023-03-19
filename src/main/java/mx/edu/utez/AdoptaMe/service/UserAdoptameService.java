package mx.edu.utez.AdoptaMe.service;

import mx.edu.utez.AdoptaMe.entity.UserAdoptame;
import mx.edu.utez.AdoptaMe.model.request.user.UserInsertDto;

import java.util.Optional;

public interface UserAdoptameService {
    boolean saveUser(UserInsertDto user);

    Optional<UserAdoptame> findUserById(Long id);

    UserAdoptame findUserByUsername(String username);
}
