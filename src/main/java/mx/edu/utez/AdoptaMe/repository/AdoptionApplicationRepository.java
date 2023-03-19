package mx.edu.utez.AdoptaMe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import mx.edu.utez.AdoptaMe.entity.AdoptionApplication;

import java.util.Optional;

@Repository
public interface AdoptionApplicationRepository extends PagingAndSortingRepository<AdoptionApplication, Long> {

    @Query("SELECT a FROM AdoptionApplication a WHERE a.user.username LIKE ?1")
    Page<AdoptionApplication> findAllByUser(String username, Pageable pageable);

    Optional<AdoptionApplication> findAll(Long id);

    AdoptionApplication save(AdoptionApplication adoptionApplicationToInsert);

    Optional<AdoptionApplication> findById(Long id);
}
