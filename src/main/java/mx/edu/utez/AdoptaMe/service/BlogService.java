package mx.edu.utez.AdoptaMe.service;
import mx.edu.utez.AdoptaMe.entity.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogs();

    Blog getBlogById(Long id);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

}