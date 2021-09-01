package com.learning.fun.jokes.dao;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

class ChuckNorrisDaoTest {

    private MockWebServer mockWebServer;

    @Value("${chucknorris.api.host}")
    String apiHost;

    @BeforeEach
    void setupMockWebServer(){
        this.mockWebServer = new MockWebServer();
    }

    @Test
    void testForValidIntegration() throws IOException, InterruptedException {
        mockWebServer.enqueue(new MockResponse().setBody("test joke"));
        mockWebServer.start(8080);
        HttpUrl baseUrl = mockWebServer.url("/jokes/random?category=test");


        ChuckNorrisDao chuckNorrisDao = new ChuckNorrisDao(WebClient.builder());
        ReflectionTestUtils.setField(chuckNorrisDao,"apiHost", "http://localhost");

//        System.out.println(mockWebServer.takeRequest());

        chuckNorrisDao.getJoke("test");

        System.out.println(mockWebServer.takeRequest());


    }
}