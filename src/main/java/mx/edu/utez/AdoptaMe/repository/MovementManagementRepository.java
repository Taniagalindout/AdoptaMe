package mx.edu.utez.AdoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.edu.utez.AdoptaMe.entity.MovementManagement;

@Repository
public interface MovementManagementRepository extends JpaRepository<MovementManagement, Long> {
}
