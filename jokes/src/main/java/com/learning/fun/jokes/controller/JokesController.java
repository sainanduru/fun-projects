package com.learning.fun.jokes.controller;

import com.learning.fun.jokes.dao.ChuckNorrisDao;
import com.learning.fun.jokes.validation.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JokesController {

    private final ChuckNorrisDao jokesDao;

    private static final String INVALID_CATEGORY = "Invalid category. Valid categories are CAREER, CELEBRITY, DEV, MUSIC, SPORT, SCIENCE";

    @GetMapping(path="/joke")
    public ResponseEntity<String> getJoke(@RequestParam String category){
        CategoryValidator categoryValidator = new CategoryValidator();
        try{
            if(categoryValidator.isValidCategory(category.toUpperCase())){
                return new ResponseEntity<>(jokesDao.getJoke(category), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(INVALID_CATEGORY, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Chuck Norris API is unavailable. please try later!", HttpStatus.BAD_REQUEST);
        }
    }
}
