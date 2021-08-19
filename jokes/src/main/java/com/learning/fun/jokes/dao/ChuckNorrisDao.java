package com.learning.fun.jokes.dao;

import com.learning.fun.jokes.model.Category;
import com.learning.fun.jokes.model.response.Joke;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChuckNorrisDao {

    private final WebClient.Builder webClient;

    public String getJoke(){
        String joke = null;

        WebClient client = webClient
                .baseUrl("https://api.chucknorris.io")
                .build();

        Mono<Joke> response = client.get().uri("/jokes/random?category=dev")
                .retrieve()
                .bodyToMono(Joke.class);

        log.info(response.toString());
        return joke;
    }

}
