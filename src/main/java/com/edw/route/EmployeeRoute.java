package com.edw.route;

import com.edw.soap.EmployeeByIdRequest;
import com.edw.soap.EmployeeResponse;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

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
            .get("/employee/{id}")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                    .type(EmployeeResponse.class)
                        .to("direct:get-employee");

        from("direct:get-employee")
            .routeId("get-employee-api")
            .log("calling get-employee to wsdl")
            .process(exchange -> {
                exchange.getIn().getHeaders().clear();
                exchange.getOut().setBody(new EmployeeByIdRequest());
            })
            .to("cxf:bean:employeeServiceEndpoint")
            .process(exchange -> {
                exchange.getIn().getHeaders().clear();
                EmployeeResponse employeeResponse = (EmployeeResponse) ((MessageContentsList) exchange.getIn().getBody()).get(0);
                exchange.getOut().setBody(employeeResponse);
            })
            .log("sending response get-employee to frontend as json")
            .marshal()
                .json(JsonLibrary.Jackson);
    }
}
