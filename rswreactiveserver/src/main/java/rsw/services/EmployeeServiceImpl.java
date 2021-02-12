package rsw.services;

import org.springframework.stereotype.Service;
import rsw.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public List<Employee> getEmpList(String amount) {
        List<Employee> empList = new ArrayList<>();
        if (amount == null || Integer.valueOf(amount) < 1) {
            amount = "10";
        }
        System.out.println("Amount=" + amount);
        for (int i =1 ; i <= Integer.valueOf(amount); i++) {
            empList.add(new Employee(Integer.toString(i), "name" + i));
        }
        return empList;
    }

    @Override
    public Employee getEmployeeById(String id) {
        return new Employee(id, "name" + id);
    }
}
