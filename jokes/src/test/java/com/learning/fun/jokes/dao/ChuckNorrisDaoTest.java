package com.learning.fun.jokes.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.fun.jokes.model.response.Joke;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

class ChuckNorrisDaoTest {

    private static MockWebServer mockWebServer;
    private static String chuckNorrisHost;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setup() throws IOException {

        // Instantiate and start mock server
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // override the targetHost
        chuckNorrisHost = "http://localhost:" + String.valueOf(mockWebServer.getPort());
    }

    @Test
    void testForValidIntegration() throws JsonProcessingException {

        // sets mockServer response
        Joke testJoke = new Joke();
        testJoke.setValue("test joke");
        mockWebServer.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(testJoke)).addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));

        //calls the actual class by setting private field values
        ChuckNorrisDao chuckNorrisDao = new ChuckNorrisDao(WebClient.builder());
        ReflectionTestUtils.setField(chuckNorrisDao,"apiHost", chuckNorrisHost);

        //validations
        String actualJoke = chuckNorrisDao.getJoke("test");
        Assertions.assertEquals(testJoke.getValue(), actualJoke);

    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}