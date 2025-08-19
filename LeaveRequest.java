package models;

import java.time.LocalDate;

public class LeaveRequest {
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // PENDING, APPROVED, REJECTED

    public LeaveRequest(int employeeId, LocalDate startDate, LocalDate endDate) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "PENDING";
    }

    // Getters and Setters
    // toString() for display
}
