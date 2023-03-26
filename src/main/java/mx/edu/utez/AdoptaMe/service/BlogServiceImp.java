package mx.edu.utez.AdoptaMe.service;
import mx.edu.utez.AdoptaMe.entity.Blog;
import mx.edu.utez.AdoptaMe.repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog blogToUpdate = getBlogById(id);
        if (blogToUpdate != null) {
            blogToUpdate.setTitle(blog.getTitle());
            blogToUpdate.setContent(blog.getContent());
            blogToUpdate.setIsPrincipal(blog.getIsPrincipal());
            blogToUpdate.setImage(blog.getImage());
            blogToUpdate.setUser(blog.getUser());
            return blogRepository.save(blogToUpdate);
        }
        return null;
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

}
