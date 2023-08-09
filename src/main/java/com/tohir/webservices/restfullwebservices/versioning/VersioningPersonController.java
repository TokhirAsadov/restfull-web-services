package com.tohir.webservices.restfullwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersioningPerson(){
        return new PersonV1("Tohir Asadov");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersioningPerson(){
        return new PersonV2(new Name("Tohir","Asadov"));
    }
}
