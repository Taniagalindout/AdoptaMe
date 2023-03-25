package mx.edu.utez.AdoptaMe.controller;

import ch.qos.logback.core.model.Model;
import mx.edu.utez.AdoptaMe.service.UserAdoptameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAdoptameController {
    @Autowired
    private UserAdoptameService userAdoptameService;

    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    @GetMapping("/")
    public String verPaginaDeInicio(Model modelo) {
        return "index";
    }
}
