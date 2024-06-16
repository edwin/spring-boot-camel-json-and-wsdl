package com.edw.route;

import com.edw.bean.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <pre>
 *  com.edw.route.EmployeeRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Jun 2024 13:37
 */
@Component
public class EmployeeRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest()
            .get("/employee")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                    .type(Employee.class)
                    .to("direct:get-all-employee")
            .get("/employee/{id}")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .   type(Employee.class)
                        .to("direct:get-employee");

        from("direct:get-all-employee")
                .routeId("get-all-employee-api")
                .log("calling get-all-employee")
                .process(
                    exchange -> exchange.getMessage().setBody(
                            Arrays.asList(
                                    Employee
                                        .builder()
                                            .employeeAge(31)
                                            .employeeName("Edwin")
                                            .employeeId("001")
                                    .build(),
                                    Employee
                                        .builder()
                                            .employeeAge(28)
                                            .employeeName("Someone")
                                            .employeeId("002")
                                    .build()
                            )
                        )
                )
                .marshal().json(JsonLibrary.Jackson);


        from("direct:get-employee")
                .routeId("get-employee-api")
                .log("calling get-employee")
                .process(
                        exchange -> exchange.getMessage().setBody(
                                        Employee
                                                .builder()
                                                .employeeAge(31)
                                                .employeeName("Edwin")
                                                .employeeId(
                                                        exchange.getIn()
                                                                .getHeader("id").toString()
                                                )
                                            .build()
                        )
                )
                .marshal().json(JsonLibrary.Jackson);
    }
}
