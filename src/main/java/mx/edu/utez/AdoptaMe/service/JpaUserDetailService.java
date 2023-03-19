package mx.edu.utez.AdoptaMe.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import mx.edu.utez.AdoptaMe.entity.Role;
import mx.edu.utez.AdoptaMe.entity.UserAdoptame;
import mx.edu.utez.AdoptaMe.repository.UserAdoptameRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaUserDetailService implements UserDetailsService {

    private final UserAdoptameRepository userAdoptameRepository;

    public JpaUserDetailService(UserAdoptameRepository userAdoptameRepository) {
        this.userAdoptameRepository = userAdoptameRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAdoptame user = userAdoptameRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("No se ha encontrado información sobre el usuario ".concat(username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role rol: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(rol.getName()));
        }


        if (authorities.isEmpty()) {
            throw new UsernameNotFoundException("El usuario " + username + " no tiene un rol asignado");
        }

        return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
    }
}