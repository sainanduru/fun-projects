package com.learning.fun.jokes.dao;

import com.learning.fun.jokes.model.response.Joke;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChuckNorrisDao {

    private final WebClient.Builder webClient;
    private static final String JOKE_RANDOM_PATH = "/jokes/random";
    private static final String CATEGORY = "category";
    private String jokeCategory = null;

    public String getJoke(String category){
        String joke = null;
        this.jokeCategory = category;

        WebClient client = webClient
                .baseUrl("https://api.chucknorris.io")
                .defaultHeader("accept", "application/json")
                .build();

        Joke response = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(JOKE_RANDOM_PATH)
                        .queryParam(CATEGORY, this.jokeCategory)
                        .build())
                .retrieve()
                .bodyToMono(Joke.class)
                .block();

        log.info(response.toString());

        joke = response.getValue();
        return joke;
    }

}
