package rsw.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rsw.model.Employee;
import rsw.services.EmployeeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpControllerTest {

    @InjectMocks
    EmpController testable;

    @Mock
    EmployeeService employeeServiceMock;

    @Test
    void getEmp() {
        String amount = "2";
        List<Employee> employeeList = Arrays.asList(new Employee(1), new Employee(2));

        when (employeeServiceMock.getEmpList(amount)).thenReturn(employeeList);

        assertEquals(amount, testable.getEmployees(amount).blockLast().getId());
    }
}