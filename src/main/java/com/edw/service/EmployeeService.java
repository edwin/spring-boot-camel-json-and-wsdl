package com.edw.service;

import com.edw.soap.EmployeeServicePortType;
import org.apache.camel.component.cxf.jaxws.CxfEndpoint;
import org.apache.camel.component.cxf.spring.jaxws.CxfSpringEndpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *  com.edw.service.EmployeeService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Jun 2024 22:41
 */
@Service
public class EmployeeService {

    @Value("${employee.soap.url}")
    private String employeeSoapUrl;

    @Bean
    public CxfEndpoint employeeServiceEndpoint_getEmployeeById() {
        CxfSpringEndpoint cxfEndpoint = new CxfSpringEndpoint();
        cxfEndpoint.setServiceClass(EmployeeServicePortType.class);
        cxfEndpoint.setAddress(employeeSoapUrl);
        cxfEndpoint.setWsdlURL("/wsdl/employee.wsdl");
        cxfEndpoint.setDefaultOperationName("GetEmployeeById");
        cxfEndpoint.setDefaultOperationNamespace("http://localhost/employee");
        return cxfEndpoint;
    }

    @Bean
    public CxfEndpoint employeeServiceEndpoint_getEmployeesByName() {
        CxfSpringEndpoint cxfEndpoint = new CxfSpringEndpoint();
        cxfEndpoint.setServiceClass(EmployeeServicePortType.class);
        cxfEndpoint.setAddress(employeeSoapUrl);
        cxfEndpoint.setWsdlURL("/wsdl/employee.wsdl");
        cxfEndpoint.setDefaultOperationName("GetEmployeesByName");
        cxfEndpoint.setDefaultOperationNamespace("http://localhost/employee");
        return cxfEndpoint;
    }
}
