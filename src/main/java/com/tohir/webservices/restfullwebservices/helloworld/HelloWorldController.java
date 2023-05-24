package com.tohir.webservices.restfullwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/hello-world")
    public HelloWorld sayHelloWorld() {
        return new HelloWorld("Hello World message");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorld sayPathVariable(@PathVariable String name) {
        return new HelloWorld("Hello World "+name);
    }
}
