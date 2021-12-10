package br.com.youtubeproject.api.rest.ws.v1.or.controller;

import br.com.youtubeproject.api.rest.model.request.UserRequest;
import br.com.youtubeproject.api.rest.model.response.UserResponse;
import br.com.youtubeproject.api.rest.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/v1")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService service;

    @PostMapping //create
    @ApiOperation("API Responsiblle for the users creation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Created"),
            @ApiResponse(code = 500, message = "An error ocurred"),
            @ApiResponse(code = 404, message = "Page Not Found")

    })
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        LOGGER.info("Requesting received");
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(Pageable pageable) {
        LOGGER.info("Searching Registers");
        Page<UserResponse> responses = service.getAll(pageable);
        return ResponseEntity.ok(responses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable("id") Long id) {
        LOGGER.info("Starting searches for registers");
        Optional<UserResponse> userResponse = service.get(id);
        return userResponse.map(body -> ResponseEntity.ok(body)).orElseGet(() -> ResponseEntity.notFound().build());
//        return userResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long id, @RequestBody UserRequest request) {
        LOGGER.info("Initializing update");
        Optional<UserResponse> update = service.update(id, request);
        return update.map(body -> ResponseEntity.ok(body)).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        LOGGER.info("Starting register removing");
        if(service.delete(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}

