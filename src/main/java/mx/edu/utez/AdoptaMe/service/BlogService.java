package mx.edu.utez.AdoptaMe.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mx.edu.utez.AdoptaMe.entity.Blog;
import mx.edu.utez.AdoptaMe.model.request.blog.BlogInsertDto;
import mx.edu.utez.AdoptaMe.model.request.blog.BlogUpdateDto;

import java.util.List;
import java.util.Optional;


public interface BlogService {
     Page<Blog> findAllBlog(Pageable pageable);
     Optional<Blog> findBlogById(Long id);
     boolean saveBlog(BlogInsertDto blog, String imageName, String username);
     boolean updateBlog(BlogUpdateDto blog);
     List<Blog> findAllByIsPrincipal(Boolean isPrincipal);
   
}
