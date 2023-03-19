package mx.edu.utez.AdoptaMe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.edu.utez.AdoptaMe.entity.Blog;
import mx.edu.utez.AdoptaMe.entity.MovementManagement;
import mx.edu.utez.AdoptaMe.entity.UserAdoptame;
import mx.edu.utez.AdoptaMe.model.request.blog.BlogInsertDto;
import mx.edu.utez.AdoptaMe.model.request.blog.BlogUpdateDto;
import mx.edu.utez.AdoptaMe.repository.BlogRepository;
import mx.edu.utez.AdoptaMe.repository.UserAdoptameRepository;
import mx.edu.utez.AdoptaMe.util.InfoMovement;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService{

    private final Validator validator;

    private final BlogRepository blogRepository;
    private final MovementManagementServiceImpl movementManagementService;
    private final InfoMovement infoMovement;
    private final UserAdoptameRepository userAdoptameRepository;

    private final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);


    public BlogServiceImpl(BlogRepository blogRepository, MovementManagementServiceImpl movementManagementService, InfoMovement infoMovement,
                           UserAdoptameRepository userAdoptameRepository, Validator validator){
        this.blogRepository = blogRepository;
        this.movementManagementService = movementManagementService;
        this.infoMovement = infoMovement;
        this.userAdoptameRepository = userAdoptameRepository;
        this.validator = validator;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Blog> findAllBlog(Pageable pageable) {
        return blogRepository.findAllByOrderByCreatedAtDesc(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Blog> findBlogById(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog;
    }


    @Override
    public boolean saveBlog(BlogInsertDto blogDto, String imageName, String username) {
        boolean validInsert = false;

        UserAdoptame user = userAdoptameRepository.findUserByUsername(username);

        if(user != null){
            Blog blog = new Blog();

            BeanUtils.copyProperties(blogDto, blog);
            blog.setIsPrincipal(blogDto.getPrincipal());
            blog.setImage(imageName);
            blog.setUser(user);
            try{
                Blog blogInsertedBd = blogRepository.save(blog);

                if(blogInsertedBd.getId() != 0){

                    validInsert = true;

                    MovementManagement movement = new MovementManagement();

                    movement.setModuleName(infoMovement.getModuleName());
                    movement.setUsername(infoMovement.getUsername());
                    movement.setAction(infoMovement.getActionMovement());
                    movement.setMovementDate(new Date());
                    movement.setNewData(blogInsertedBd.toString());

                    movementManagementService.createOrUpdate(movement);
                }

            }catch (Exception e){
                    logger.error("error to insert blog");
            }
        }
        return validInsert;
    }

    @Override
    public boolean updateBlog(BlogUpdateDto blogDto) {
        boolean validUpdate = false;

        Optional<Blog> blogDataRegistered =  blogRepository.findById(blogDto.getId());

        if(blogDataRegistered.isPresent()){
            try{
                Blog blogUpdate = new Blog();
                BeanUtils.copyProperties(blogDataRegistered.get(), blogUpdate);
                BeanUtils.copyProperties(blogDto, blogUpdate);
                blogUpdate.setIsPrincipal(blogDto.getPrincipal());

                Blog blogisUpdated = blogRepository.save(blogUpdate);

                if(blogisUpdated.getId() != 0){
                    validUpdate = true;

                    MovementManagement movement = new MovementManagement();

                    movement.setModuleName(infoMovement.getModuleName());
                    movement.setUsername(infoMovement.getUsername());
                    movement.setAction(infoMovement.getActionMovement());
                    movement.setMovementDate(new Date());
                    movement.setPreviousData(blogDataRegistered.get().toString());
                    movement.setNewData(blogisUpdated.toString());
                    movementManagementService.createOrUpdate(movement);
                }

            }catch (Exception e){
                logger.error("error to update a blog");
            }
        }

        return validUpdate;
    }


    public Map<String, List<String>> getValidationInsertBlog(BlogInsertDto blogDto){
        Set<ConstraintViolation<BlogInsertDto>> violations = validator.validate(blogDto);
        Map<String, List<String>> errors = new HashMap<>();

        if(!violations.isEmpty()){
            for (ConstraintViolation<BlogInsertDto> error: violations) {
                List<String> messages = new ArrayList<>();
                Path path = error.getPropertyPath();
                String key = path.toString();
                String message = error.getMessage();
                if(errors.get(key) != null){
                    errors.get(key).add(message);
                }else{
                    messages.add(message);
                    errors.put(key,messages);
                }
            }
        }
        return errors;
    }

    public Map<String, List<String>> getValidationToUpdateBlog(BlogUpdateDto blogDto) {
        Set<ConstraintViolation<BlogUpdateDto>> violations = validator.validate(blogDto);
        Map<String, List<String>> errors = new HashMap<>();

        if (!violations.isEmpty()) {
            for (ConstraintViolation<BlogUpdateDto> error : violations) {

                List<String> messages = new ArrayList<>();

                Path path = error.getPropertyPath();
                String key = path.toString();
                String message = error.getMessage();
                if (errors.get(key) != null) {
                    errors.get(key).add(message);
                } else {
                    messages.add(message);
                    errors.put(key, messages);
                }
            }
        }
        return errors;
    }


    @Override
    public List<Blog> findAllByIsPrincipal(Boolean isPrincipal) {
        
        return blogRepository.findAllByIsPrincipal(isPrincipal);
    }

}
