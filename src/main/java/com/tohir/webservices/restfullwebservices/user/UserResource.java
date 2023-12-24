package com.tohir.webservices.restfullwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; // for to use universally

import com.tohir.webservices.restfullwebservices.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }


    // http://localhost:8080/users

    //EntityModel
    //WebMvcBuilder
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        User user = service.findOne(id);
        if (user == null)
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
        EntityModel<User> entityModel = EntityModel.of(user);// for add pretty looks as above
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());// link qushish uchun ishlatiladi
        entityModel.add(link.withRel("all-users"));// name of link was added
        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User save = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
