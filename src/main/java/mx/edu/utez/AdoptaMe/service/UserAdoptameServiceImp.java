package mx.edu.utez.AdoptaMe.service;

import mx.edu.utez.AdoptaMe.entity.Role;
import mx.edu.utez.AdoptaMe.entity.UserAdoptame;
import mx.edu.utez.AdoptaMe.repository.UserAdoptameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAdoptameServiceImp implements UserAdoptameService{

    @Autowired
    private UserAdoptameRepository userAdoptameRepository;

    @Override
    public List<UserAdoptame> getAllUsers() {
        return userAdoptameRepository.findAll();
    }

    @Override
    public UserAdoptame getUserById(Long id) {
        return userAdoptameRepository.findById(id).orElse(null);
    }

    @Override
    public UserAdoptame saveUser(UserAdoptame user) {
        return userAdoptameRepository.save(user);
    }

    @Override
    public UserAdoptame updateUser(Long id, UserAdoptame user) {
        UserAdoptame userToUpdate = getUserById(id);

        if (userToUpdate != null) {
            userToUpdate.setName(user.getName());
            userToUpdate.setFirstLastname(user.getFirstLastname());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setFavoritesPets(user.getFavoritesPets());
            userToUpdate.setRoles(user.getRoles());
            userToUpdate.setCreatedAt(user.getCreatedAt());

            return saveUser(userToUpdate);
        }

        return null;
    }

    @Override
    public void deleteUser(Long id) {
        UserAdoptame user = getUserById(id);

        if (user != null) {
            userAdoptameRepository.delete(user);
        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAdoptame usuario = userAdoptameRepository.findByUsername(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(usuario.getUsername(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
