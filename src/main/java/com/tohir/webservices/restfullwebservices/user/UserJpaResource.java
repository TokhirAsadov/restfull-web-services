package com.tohir.webservices.restfullwebservices.user;

import com.tohir.webservices.restfullwebservices.exceptions.UserNotFoundException;
import com.tohir.webservices.restfullwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private UserRepository repository;

    public UserJpaResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }


    // http://localhost:8080/users

    //EntityModel
    //WebMvcBuilder
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id: " + id);


        /**
         * {
         *      "id":1,
         *      "name":"John",
         *      "birthDate":"1993-12-24",
         *      "_links":{
         *          "all-users":{
         *              "href":"http://localhost:8080/users"
         *          }
         *      }
         * }
         * **/
        EntityModel<User> entityModel = EntityModel.of(user.get());// for add pretty looks as above
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());// link qushish uchun ishlatiladi
        entityModel.add(link.withRel("all-users"));// name of link was added
        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User save = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
