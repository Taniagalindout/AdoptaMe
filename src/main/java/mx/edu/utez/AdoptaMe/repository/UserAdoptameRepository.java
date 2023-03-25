package mx.edu.utez.AdoptaMe.repository;

import mx.edu.utez.AdoptaMe.entity.UserAdoptame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdoptameRepository extends JpaRepository<UserAdoptame, Long> {
    public UserAdoptame findByUsername(String username);
}
