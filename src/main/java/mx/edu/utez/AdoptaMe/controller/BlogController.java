package mx.edu.utez.AdoptaMe.controller;
import mx.edu.utez.AdoptaMe.entity.Blog;
import mx.edu.utez.AdoptaMe.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String getAllBlogs(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "blog-list";
    }

    @GetMapping("/{id}")
    public String getBlogById(@PathVariable Long id, Model model) {
        Optional<Blog> blog = Optional.ofNullable(blogService.getBlogById(id));
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "blog-detail";
        } else {
            return "redirect:/blogs";
        }
    }

    @GetMapping("/create")
    public String showCreateBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog-form";
    }

    @PostMapping("/create")
    public String createBlog(@Valid @ModelAttribute("blog") Blog blog, BindingResult result, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (result.hasErrors()) {
            return "blog-form";
        }


        Blog newBlog = blogService.saveBlog(blog);
        return "redirect:/blogs/" + newBlog.getId();
    }

    @GetMapping("/{id}/edit")
    public String showEditBlogForm(@PathVariable Long id, Model model) {
        Optional<Blog> blog = Optional.ofNullable(blogService.getBlogById(id));
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "blog-form";
        } else {
            return "redirect:/blogs";
        }
    }

    @PostMapping("/{id}/edit")
    public String updateBlog(@PathVariable Long id, @Valid @ModelAttribute("blog") Blog blog, BindingResult result, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (result.hasErrors()) {
            return "blog-form";
        }

        Optional<Blog> existingBlog = Optional.ofNullable(blogService.getBlogById(id));
        if (existingBlog.isPresent()) {
            Blog updatedBlog = existingBlog.get();
            updatedBlog.setTitle(blog.getTitle());
            updatedBlog.setContent(blog.getContent());
            updatedBlog.setIsPrincipal(blog.getIsPrincipal());
            updatedBlog.setUser(blog.getUser());

            blogService.saveBlog(updatedBlog);
            return "redirect:/blogs/" + updatedBlog.getId();
        } else {
            return "redirect:/blogs";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs";
    }
}