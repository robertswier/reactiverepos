package rsw.services;

import rsw.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmpList(String aantal);
    Employee getEmployeeById(String id);
}
