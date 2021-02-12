package rsw.services;

import rsw.model.Employee;

import java.net.URI;
import java.util.List;

public interface EmployeeClientService {

    List<Employee> getEmployees(URI uri);
    List<Employee> getEmployeesBlocking(URI uri);
}
