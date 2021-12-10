package br.com.youtubeproject.api.rest.service.mapper.response;

import br.com.youtubeproject.api.rest.model.response.UserResponse;
import br.com.youtubeproject.api.rest.persistence.entity.User;
import br.com.youtubeproject.api.rest.service.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserResponseMapper implements Mapper<User, UserResponse> {

    @Autowired
    private ModelMapper modelMapper; //Add

    @Override
    public UserResponse map(User input) {
        UserResponse userResponse = modelMapper.map(input, UserResponse.class);
//
//        if(input == null){
//            return null;
//        }
//        UserResponse response = new UserResponse();
//        response.setId(input.getId());
//        response.setName(input.getName());
//        response.setEmail(input.getEmail());
//        return response;
        return userResponse;

    }
}
