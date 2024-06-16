package com.edw.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 *  com.edw.bean.Employee
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 16 Jun 2024 13:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String employeeId;
    private String employeeName;
    private Integer employeeAge;
}