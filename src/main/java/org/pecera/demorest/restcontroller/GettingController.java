package org.pecera.demorest.restcontroller;

import org.pecera.demorest.restDto.GreetingDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GettingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Optional<GreetingDto> greeting (@RequestParam(value = "name", defaultValue = "World") String name){

        return GreetingDto.newNullsafeDto(counter.incrementAndGet(), String.format(template, name));
    }

}
