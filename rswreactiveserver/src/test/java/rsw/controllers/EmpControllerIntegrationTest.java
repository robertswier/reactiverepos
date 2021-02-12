package rsw.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import rsw.RswreactiveApplication;
import rsw.model.Employee;
import rsw.services.EmployeeService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= RswreactiveApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= RswreactiveApplication.class)
class EmpControllerIntegrationTest {

    @Autowired
    private WebTestClient testClient;

    @MockBean
    EmployeeService employeeServiceMock;

    @Test
    void getEmp() {
        String amount = "2";
        List<Employee> employeeList = Arrays.asList(new Employee(1), new Employee(2));
        given(employeeServiceMock.getEmpList(amount)).willReturn(employeeList);

        Arrays.stream(testClient.get()
                .uri("/getemp/" + amount)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Employee[].class)
                .returnResult()
                .getResponseBody())
                .findFirst()
                .get()
                .getId()
                .equals("1");
    }
}