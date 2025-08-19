package models;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String department;
    private LocalDate joiningDate;
    private int leaveBalance = 20;

    public Employee(int id, String name, String email, String department, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.joiningDate = joiningDate;
    }

}
