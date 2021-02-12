package rsw.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rsw.model.Employee;
import rsw.services.EmployeeClientService;

import java.net.URI;
import java.util.List;

@RestController
public class StartEmpController {

    @Autowired
    EmployeeClientService employeeClientService;

//    http://localhost:8095/start/10
    @RequestMapping(value = "/start/{amount}")
    public List<Employee> getEmp(@PathVariable String amount) {
        URI uri = URI.create("http://localhost:8080/getemp/" + amount);
        return employeeClientService.getEmployees(uri);
    }

    //    http://localhost:8095/startblocking/5
    @RequestMapping(value = "/startblocking/{amount}")
    public List<Employee> getEmpBlocking(@PathVariable String amount) {
        URI uri = URI.create("http://localhost:8080/getemp/" + amount);
        return employeeClientService.getEmployeesBlocking(uri);
    }

}
