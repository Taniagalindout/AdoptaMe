package mx.edu.utez.AdoptaMe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mx.edu.utez.AdoptaMe.entity.AdoptionApplication;
import mx.edu.utez.AdoptaMe.model.request.adoption.AdoptionRegisterDto;
import mx.edu.utez.AdoptaMe.model.request.adoption.AdoptionUpdateDto;

import java.util.Optional;

public interface AdoptionApplicationService {

    Page<AdoptionApplication> findAllAdoptionApplications(Pageable pageable);

    Page<AdoptionApplication> findAdoptionApplicationsByUsername(String username, Pageable pageable);

    Optional<AdoptionApplication> findAdoptionApplicationId(Long id);

    boolean createApplication(AdoptionRegisterDto adoptionRegisterDto);

    boolean changeStateAdoption(AdoptionUpdateDto adoptionUpdateDto);
}
