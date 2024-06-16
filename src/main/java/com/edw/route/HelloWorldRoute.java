package com.edw.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *  com.edw.route.HelloWorldRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Jun 2024 13:11
 */
@Component
public class HelloWorldRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest()
            .get("/hello-world")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                    .to("direct:hello-world");

        from("direct:hello-world")
                .routeId("hello-world-api")
                .log("calling getHelloWorld")
                .setBody(constant("{\"hello\":\"world\"}"));
    }
}
