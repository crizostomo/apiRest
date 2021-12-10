package br.com.youtubeproject.api.rest.service.mapper.request;

import br.com.youtubeproject.api.rest.model.request.UserRequest;
import br.com.youtubeproject.api.rest.persistence.entity.User;
import br.com.youtubeproject.api.rest.service.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper implements Mapper<UserRequest, User> {

    @Autowired
    private ModelMapper modelMapper; //Add

    @Override
    public User map(UserRequest input) {
        User user = modelMapper.map(input, User.class);

//        return modelMapper.map(input, UserRequest.class); //Add
//        if(input == null){
//            return null;
//        }
//
//        User user = new User();
//        user.setName(input.getName());
//        user.setEmail(input.getEmail());
//
        return user;
    }
}
