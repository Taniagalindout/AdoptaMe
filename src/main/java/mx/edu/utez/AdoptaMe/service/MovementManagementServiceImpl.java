package mx.edu.utez.AdoptaMe.service;

import org.springframework.stereotype.Service;
import mx.edu.utez.AdoptaMe.entity.MovementManagement;
import mx.edu.utez.AdoptaMe.repository.MovementManagementRepository;

@Service
public class MovementManagementServiceImpl implements MovementManagementService{

    private final MovementManagementRepository movementManagementRepository;

    public MovementManagementServiceImpl(MovementManagementRepository repository) {
        this.movementManagementRepository = repository;
    }

    @Override
    public void createOrUpdate(MovementManagement movementManagement) {

        movementManagementRepository.save(movementManagement);
    }
}
