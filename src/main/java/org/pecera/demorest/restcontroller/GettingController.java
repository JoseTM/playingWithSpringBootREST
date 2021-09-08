package org.pecera.demorest.restcontroller;

import org.pecera.demorest.restDto.GreetingDto;
import org.pecera.demorest.services.implementations.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GettingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final KafkaService kafkaService;

    @Autowired
    public GettingController(KafkaService kafkaService){
        this.kafkaService = kafkaService;
    }

    @GetMapping("/greeting")
    public Optional<GreetingDto> greeting (@RequestParam(value = "name", defaultValue = "World") String name){

        return GreetingDto.newNullsafeDto(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greetingKafka")
    public Optional<GreetingDto> greetingKafka (@RequestParam(value = "name", defaultValue = "World") String name){

        Optional<GreetingDto> greeting = GreetingDto.newNullsafeDto(counter.incrementAndGet(), String.format(template, name));
        kafkaService.send(greeting.isPresent()? greeting.get().getContent() : "");

        return greeting;
    }

}
