package mx.edu.utez.AdoptaMe.service;

import mx.edu.utez.AdoptaMe.entity.UserAdoptame;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserAdoptameService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    List<UserAdoptame> getAllUsers();
    UserAdoptame getUserById(Long id);
    UserAdoptame saveUser(UserAdoptame user);
    UserAdoptame updateUser(Long id, UserAdoptame user);
    void deleteUser(Long id);
}
