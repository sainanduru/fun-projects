package com.learning.fun.jokes.controller;

import com.learning.fun.jokes.dao.ChuckNorrisDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JokesControllerTest {

    @InjectMocks
    JokesController controller;

    @Mock
    ChuckNorrisDao chuckNorrisDao;

    @Test
    void testForValidCategory() {

        String expectedResponse = "It's a joke!";
        Mockito.when(chuckNorrisDao.getJoke(Mockito.anyString())).thenReturn(expectedResponse);

        String actualResponse = controller.getJoke("Dev");

        Assertions.assertEquals(expectedResponse, actualResponse);

    }

    @Test
    void testForInvalidCategory(){

        String expectedResponse = "Invalid category. Valid categories are CAREER, CELEBRITY, DEV, MUSIC, SPORT, SCIENCE";

        String actualResponse = controller.getJoke("test");

        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}