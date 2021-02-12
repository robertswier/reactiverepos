package rsw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import rsw.model.Employee;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeClientServiceImpl implements EmployeeClientService {

    private final WebClient webClient;

    @Autowired
    public EmployeeClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<Employee> getEmployees(URI uri) {
        System.out.println("1 Start");
        List<Employee> employeeList = new ArrayList<>();

        Instant start = Instant.now();
        Flux<Employee> flux = webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Employee.class);
        Instant end = Instant.now();
        flux.subscribe(emp -> {
            System.out.println("2 emp from reactive server: " + emp.getId() + ": " + Instant.now());
            employeeList.add(emp);
        });
        System.out.println("3 End method ...: " + Instant.now());
        return employeeList;
    }

    @Override
    public List<Employee> getEmployeesBlocking(URI uri) {
        System.out.println("1 Start, uri =" + uri);
        Instant start = Instant.now();
        List<Employee> employeeList = webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Employee.class)
                .collectList()
//        timeout is 10 seconds
                .block(Duration.ofSeconds(10));
        Instant end = Instant.now();
        System.out.println("2 emp from reactive server: " + employeeList.size() + ": " + Instant.now());
        System.out.println("3 End method ...: " + Instant.now());
        return employeeList;
    }
}
