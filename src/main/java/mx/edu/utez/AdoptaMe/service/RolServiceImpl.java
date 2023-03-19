package mx.edu.utez.AdoptaMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.edu.utez.AdoptaMe.entity.Role;
import mx.edu.utez.AdoptaMe.repository.RolRepository;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    @Override
    @Transactional
    public List<Role> findAllRol() {
        return  rolRepository.findAll();
    }
}
