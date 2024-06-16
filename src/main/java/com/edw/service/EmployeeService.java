package com.edw.service;

import com.edw.soap.EmployeeServicePortType;
import org.apache.camel.component.cxf.jaxws.CxfEndpoint;
import org.apache.camel.component.cxf.spring.jaxws.CxfSpringEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;

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
    @Bean
    public CxfEndpoint employeeServiceEndpoint() {
        CxfSpringEndpoint cxfEndpoint = new CxfSpringEndpoint();
        cxfEndpoint.setServiceClass(EmployeeServicePortType.class);
        cxfEndpoint.setAddress("http://localhost:8088/mockEmployeeServiceSOAP");
        cxfEndpoint.setWsdlURL("/wsdl/employee.wsdl");
        cxfEndpoint.setDefaultOperationName("GetEmployeeById");
        cxfEndpoint.setDefaultOperationNamespace("http://localhost/employee");
        return cxfEndpoint;
    }
}
