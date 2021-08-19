package com.learning.fun.jokes.controller;

import com.learning.fun.jokes.dao.ChuckNorrisDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JokesController {

    private final ChuckNorrisDao jokesDao;

    @GetMapping(path="/joke")
    public String getJoke(){
        return jokesDao.getJoke();
    }
}
