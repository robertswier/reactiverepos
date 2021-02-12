package rsw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rsw.model.Employee;
import rsw.services.EmployeeService;

@RestController
public class EmpController {

    @Autowired
    EmployeeService employeeService;

    //    http://localhost:8080/getemployees
    @RequestMapping(value = "/getemployees")
    public Flux<Employee> getEmployees() {
        return Flux.fromIterable(employeeService.getEmpList("5"));
    }

//    http://localhost:8080/getemp/1
    @RequestMapping(value = "/getemp/{amount}")
    public Flux<Employee> getEmployees(@PathVariable String amount) {
        return Flux.fromIterable(employeeService.getEmpList(amount));
    }

    //    http://localhost:8080/employee/1
    @RequestMapping(value = "/employee/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable String id) {
        return Mono.just(employeeService.getEmployeeById(id));
    }

}
