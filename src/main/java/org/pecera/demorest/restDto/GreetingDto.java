package org.pecera.demorest.restDto;

import java.util.Objects;
import java.util.Optional;

public class GreetingDto {
    private final Long id;
    private final String content;

    private GreetingDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public static Optional<GreetingDto> newNullsafeDto(Long id, String content){
        GreetingDto greeting = null;
        if (Objects.nonNull(id) && Objects.nonNull(content))
            greeting = new GreetingDto(id, content);

        return Optional.ofNullable(greeting);
    }

    public static Optional<GreetingDto> emptyGreetingDto(){
        return Optional.of(new GreetingDto(0L,""));
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
