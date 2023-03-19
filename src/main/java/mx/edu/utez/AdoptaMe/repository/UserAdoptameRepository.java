package mx.edu.utez.AdoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.edu.utez.AdoptaMe.entity.UserAdoptame;

@Repository
public interface UserAdoptameRepository extends JpaRepository<UserAdoptame, Long> {

    UserAdoptame findUserByUsername(String username);
}
