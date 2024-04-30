package ru.antonovmikhail.projections.employee.model;

public interface EmployeeProjection {
    void setFullName(String s);

    void setPosition(String s);

    void setDepartmentName(String s);

    String getFullName();

    String getPosition();

    String getDepartmentName();
}
