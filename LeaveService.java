package services;

import models.Employee;
import models.LeaveRequest;
import java.time.LocalDate;
import java.util.*;

public class LeaveService {
    private List<LeaveRequest> leaveRequests = new ArrayList<>();
    private EmployeeService empService;

    public LeaveService(EmployeeService empService) {
        this.empService = empService;
    }

    public String applyLeave(int empId, LocalDate start, LocalDate end) {
        Employee emp = empService.getEmployee(empId);
        if (emp == null) return "Employee not found.";
        if (start.isBefore(emp.getJoiningDate())) return "Leave before joining date.";
        if (end.isBefore(start)) return "Invalid date range.";
        int days = (int) (end.toEpochDay() - start.toEpochDay()) + 1;
        if (days > emp.getLeaveBalance()) return "Insufficient leave balance.";

        for (LeaveRequest lr : leaveRequests) {
            if (lr.getEmployeeId() == empId && lr.getStatus().equals("APPROVED") &&
                !(end.isBefore(lr.getStartDate()) || start.isAfter(lr.getEndDate()))) {
                return "Overlapping leave request.";
            }
        }

        leaveRequests.add(new LeaveRequest(empId, start, end));
        return "Leave request submitted.";
    }

    public String approveLeave(int empId, LocalDate start, LocalDate end) {
        for (LeaveRequest lr : leaveRequests) {
            if (lr.getEmployeeId() == empId && lr.getStartDate().equals(start) && lr.getEndDate().equals(end)) {
                if (!lr.getStatus().equals("PENDING")) return "Already processed.";
                lr.setStatus("APPROVED");
                Employee emp = empService.getEmployee(empId);
                int days = (int) (end.toEpochDay() - start.toEpochDay()) + 1;
                emp.setLeaveBalance(emp.getLeaveBalance() - days);
                return "Leave approved.";
            }
        }
        return "Leave request not found.";
    }

    public String rejectLeave(int empId, LocalDate start, LocalDate end) {
        for (LeaveRequest lr : leaveRequests) {
            if (lr.getEmployeeId() == empId && lr.getStartDate().equals(start) && lr.getEndDate().equals(end)) {
                if (!lr.getStatus().equals("PENDING")) return "Already processed.";
                lr.setStatus("REJECTED");
                return "Leave rejected.";
            }
        }
        return "Leave request not found.";
    }

    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }
}
