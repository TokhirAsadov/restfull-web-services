package com.tohir.webservices.restfullwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    // URL Versioning - Twitter
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersioningOfPerson(){
        return new PersonV1("Tohir Asadov");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersioningOfPerson(){
        return new PersonV2(new Name("Tohir","Asadov"));
    }

    // Request Parameter versioning - Amazon
    @GetMapping(path = "/person",params = "version=1")
    public PersonV1 getFirstVersioningOfPersonRequestParameter(){
        return new PersonV1("Tohir Asadov");
    }

    @GetMapping(path = "/person",params = "version=2")
    public PersonV2 getSecondVersioningOfPersonRequestParameter(){
        return new PersonV2(new Name("Tohir","Asadov"));
    }

    // (Custom) headers versioning - Microsoft
    @GetMapping(path = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersioningOfPersonRequestHeader(){
        return new PersonV1("Tohir Asadov");
    }
    @GetMapping(path = "/person/header",headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersioningOfPersonRequestHeader(){
        return new PersonV2(new Name("Tohir","Asadov"));
    }

    // Media type versioning (a.k.a "content negotiation" or "accept header") - GitHub
    @GetMapping(path = "/person/accept",produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersioningOfPersonAcceptHeader(){
        return new PersonV1("Tohir Asadov");
    }
    @GetMapping(path = "/person/accept",produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersioningOfPersonAcceptHeader(){
        return new PersonV2(new Name("Tohir","Asadov"));
    }
}
