package com.edw.route;

import io.restassured.RestAssured;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
            "server.port=54768"
        }
)
public class HelloWorldRouteTest {

    @BeforeAll
    public static void setup() throws Exception {
        RestAssured.port = 54768;
    }

    @Test
    public void testHelloCallExternalAPI() {
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
