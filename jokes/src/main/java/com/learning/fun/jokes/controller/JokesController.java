package com.learning.fun.jokes.controller;

import com.learning.fun.jokes.dao.ChuckNorrisDao;
import com.learning.fun.jokes.validation.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JokesController {

    private final ChuckNorrisDao jokesDao;

    private final String INVALID_CATEGORY = "Invalid category. Valid categories are CAREER, CELEBRITY, DEV, MUSIC, SPORT, SCIENCE";

    @GetMapping(path="/joke")
    public String getJoke(@RequestParam String category){
        CategoryValidator categoryValidator = new CategoryValidator();
        if(categoryValidator.isValidCategory(category)){
            return jokesDao.getJoke(category);
        } else {
            return INVALID_CATEGORY;
        }
    }
}
