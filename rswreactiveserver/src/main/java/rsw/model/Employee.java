package rsw.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    String id;
    String name;

    public Employee(int i) {
        this.id = String.valueOf(i);
    }
}
