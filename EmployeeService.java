package services;

import models.Employee;
import java.util.*;

public class EmployeeService {
    private Map<Integer, Employee> employees = new HashMap<>();
    private int nextId = 1;

    public Employee addEmployee(String name, String email, String dept, LocalDate joiningDate) {
        Employee emp = new Employee(nextId++, name, email, dept, joiningDate);
        employees.put(emp.getId(), emp);
        return emp;
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public boolean updateEmployee(int id, String name, String email, String dept, LocalDate joiningDate) {
        Employee emp = employees.get(id);
        if (emp == null) return false;
        emp.setName(name);
        emp.setEmail(email);
        emp.setDepartment(dept);
        emp.setJoiningDate(joiningDate);
        return true;
    }

    public boolean deleteEmployee(int id) {
        return employees.remove(id) != null;
    }
}
