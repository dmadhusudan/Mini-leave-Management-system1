import models.*;
import services.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeService empService = new EmployeeService();
        LeaveService leaveService = new LeaveService(empService);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Employee\n2. View Employees\n3. Update Employee\n4. Delete Employee");
            System.out.println("5. Apply Leave\n6. Approve Leave\n7. Reject Leave\n8. View Leave Requests\n9. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Dept: "); String dept = sc.nextLine();
                    System.out.print("Joining Date (yyyy-mm-dd): "); LocalDate jd = LocalDate.parse(sc.nextLine());
                    Employee emp = empService.addEmployee(name, email, dept, jd);
                    System.out.println("Added: " + emp);
                }
                case 2 -> empService.getAllEmployees().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Dept: "); String dept = sc.nextLine();
                    System.out.print("Joining Date (yyyy-mm-dd): "); LocalDate jd = LocalDate.parse(sc.nextLine());
                    boolean updated = empService.updateEmployee(id, name, email, dept, jd);
                    System.out.println(updated ? "Updated." : "Employee not found.");
                }
                case 4 -> {
                    System.out.print("ID: "); int id = sc.nextInt();
                    System.out.println(empService.deleteEmployee(id) ? "Deleted." : "Not found.");
                }
                case 5 -> {
                    System.out.print("Emp ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Start Date (yyyy-mm-dd): "); LocalDate sd = LocalDate.parse(sc.nextLine());
                    System.out.print("End Date (yyyy-mm-dd): "); LocalDate ed = LocalDate.parse(sc.nextLine());
                    System.out.println(leaveService.applyLeave(id, sd, ed));
                }
                case 6 -> {
                    System.out.print("Emp ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Start Date: "); LocalDate sd = LocalDate.parse(sc.nextLine());
                    System.out.print("End Date: "); LocalDate ed = LocalDate.parse(sc.nextLine());
                    System.out.println(leaveService.approveLeave(id, sd, ed));
                }
                case 7 -> {
                    System.out.print("Emp ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Start Date: "); LocalDate sd = LocalDate.parse(sc.nextLine());
                    System.out.print("End Date: "); LocalDate ed = LocalDate.parse(sc.nextLine());
                    System.out.println(leaveService.rejectLeave(id, sd, ed));
                }
                case 8 -> leaveService.getLeaveRequests().forEach(System.out::println);
                case 9 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
