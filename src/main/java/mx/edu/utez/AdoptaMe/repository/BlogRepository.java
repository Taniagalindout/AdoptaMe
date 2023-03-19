package mx.edu.utez.AdoptaMe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import mx.edu.utez.AdoptaMe.entity.Blog;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    List<Blog> findAllByIsPrincipal(Boolean isPrincipal);
    Page<Blog> findAllByOrderByCreatedAtDesc(Pageable peagable);

    Optional<Blog> findById(Long id);

    Blog save(Blog blog);
}
