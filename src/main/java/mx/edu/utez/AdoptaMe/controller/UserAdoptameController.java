package mx.edu.utez.AdoptaMe.controller;
import javax.validation.Valid;


import mx.edu.utez.AdoptaMe.service.UserAdoptameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import mx.edu.utez.AdoptaMe.entity.UserAdoptame;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        UserAdoptame user = userAdoptameService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "user-details";
        } else {
            return "redirect:/users";
        }
    }

    @GetMapping("")
    public String getAllUsers(Model model) {
        List<UserAdoptame> users = userAdoptameService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new UserAdoptame());
        return "user-form";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") UserAdoptame user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-form";
        }
        UserAdoptame newUser = userAdoptameService.saveUser(user);
        return "redirect:/users/" + newUser.getId();
    }

    @GetMapping("/{id}/edit")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        UserAdoptame user = userAdoptameService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "user-form";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute("user") UserAdoptame user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-form";
        }
        userAdoptameService.updateUser(id, user);
        return "redirect:/users/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userAdoptameService.deleteUser(id);
        return "redirect:/users";
    }
}
