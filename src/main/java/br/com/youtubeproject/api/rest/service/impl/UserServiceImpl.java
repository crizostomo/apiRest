package br.com.youtubeproject.api.rest.service.impl;

import br.com.youtubeproject.api.rest.model.request.UserRequest;
import br.com.youtubeproject.api.rest.model.response.UserResponse;
import br.com.youtubeproject.api.rest.persistence.entity.User;
import br.com.youtubeproject.api.rest.persistence.repository.UserRepository;
import br.com.youtubeproject.api.rest.service.Mapper;
import br.com.youtubeproject.api.rest.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.Assert.notNull;

@Service
@Getter @Setter
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository repository;

    private ModelMapper mapper;

    public UserServiceImpl(UserRepository repository, ModelMapper mapper, Mapper<UserRequest, User> requestMapper, Mapper<User, UserResponse> responseMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Autowired
    private Mapper<UserRequest, User> requestMapper;

    @Autowired
    private Mapper<User, UserResponse> responseMapper;

    @Override
    public UserResponse create(UserRequest request) {
        LOGGER.info("Creating a user register");
        notNull(request, "Invalid Request");
        User user = requestMapper.map(request);
        return repository.saveAndFlush(user).map((User input) -> responseMapper.map(input));
//        UserResponse response = new UserResponse();


//        User user = new User();
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//
//        repository.saveAndFlush(user);
//
//        response.setId(user.getId());
//        response.setName(user.getName());
//        response.setEmail(user.getEmail());

//        return response;



        //Request --> Persistir no banco --> Response
        //UserRequest --> User --> User response


    }

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        LOGGER.info("Searching all registers");
        notNull(pageable, "invalid page");
       return repository.findAll(pageable).map(user -> this.responseMapper.map(user));
    }

    @Override
    public Optional<UserResponse> get(Long id) {
        LOGGER.info("Searching Register");
        notNull(id, "Invalid ID");
        return repository.findById(id).map(this.responseMapper::map); //This method is the same as the above
    }

    @Override
    public Optional<UserResponse> update(Long id, UserRequest request) {
        LOGGER.info("Updating register");
        notNull(id, "Invalid ID");
        User updateData = this.requestMapper.map(request);

        return repository.findById(id)
                .map(user -> {
                    user.setEmail(updateData.getEmail());
                    /**
                     * Add
                     */
                    return this.responseMapper.map(repository.saveAndFlush(user));
                });
    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("Removing Register");
        notNull(id, "Invalid ID");

        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            LOGGER.warn("Error when removing register {}", id);
        }
        return false;
    }
}
