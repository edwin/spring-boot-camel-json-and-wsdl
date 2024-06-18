package com.edw.route;

import io.restassured.RestAssured;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

/**
 * <pre>
 *  com.edw.route.HelloWorldRouteTest
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Jun 2024 15:32
 */
@CamelSpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("01. Testing Index Page")
public class HelloWorldRouteTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = this.port;
    }

    @Test
    @DisplayName("01. Test Hello World Page should give 200")
    public void testHelloWorld() {
        given()
            .when()
                .get("/api/hello-world")
            .then()
                .statusCode(200)
                .body("hello", isA(String.class))
                .body("hello", equalTo("world"))
                .log().all();
    }
}
