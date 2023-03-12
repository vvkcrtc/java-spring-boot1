package ru.netology.springboot1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Container

    private static GenericContainer<?> myAppFirst = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080) ;
    @Container
    private static GenericContainer<?> myAppSecond = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);


    @BeforeAll
    public static void setUp() {
         myAppFirst.start();
         myAppSecond.start();
    }

    @Test
    void contextLoadsFirst() {
        Integer myAppFirstPort = myAppFirst.getMappedPort(8080);

        ResponseEntity<String> firstEntity = restTemplate.getForEntity("http://localhost:" + myAppFirstPort +
                "/profile", String.class);
        System.out.println(firstEntity.getBody());

        Assertions.assertEquals(firstEntity.getBody(),"Current profile is dev");
    }
    @Test
    void contextLoadsSecond() {
        Integer myAppSecondPort = myAppSecond.getMappedPort(8081);

        ResponseEntity<String> secondEntity = restTemplate.getForEntity("http://localhost:" + myAppSecondPort +
                "/profile", String.class);
        System.out.println(secondEntity.getBody());

        Assertions.assertEquals(secondEntity.getBody(),"Current profile is production");
    }

@AfterAll
    public static void stop() {
        myAppFirst.stop();
        myAppSecond.stop();
}



}