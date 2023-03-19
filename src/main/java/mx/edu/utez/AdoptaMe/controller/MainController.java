package mx.edu.utez.AdoptaMe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import mx.edu.utez.AdoptaMe.entity.Blog;
import mx.edu.utez.AdoptaMe.service.BlogServiceImpl;

import java.util.List;

@Controller
public class MainController {
    private final BlogServiceImpl blogService;

    public MainController(BlogServiceImpl blogService){
        this.blogService = blogService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        List<Blog> blogs = blogService.findAllByIsPrincipal(true);
        boolean flagRegister = (blogs.size() > 0) ?  true : false;

        model.addAttribute("listBlogs",blogs);
        model.addAttribute("flagRegister",flagRegister);
        return "index";
    }
}
