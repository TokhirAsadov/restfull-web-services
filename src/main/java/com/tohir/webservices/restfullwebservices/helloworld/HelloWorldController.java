package com.tohir.webservices.restfullwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

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

    @GetMapping("/hello-world-internationalized")
    public String sayHelloWorldInternationalized() {

        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);

        //return new HelloWorld("Hello World V2");

        //1:good.morning.message
        //2:
//      - Example: `en` - English (Good morning)
//      - Example: `nl` - Dutch (Goedemorgen)
//      - Example: `fr` - French (Bonjour)
//      - Example: `uz` - Uzbek (Xayrli tong)
    }
}
