package com.dev.service;

import com.dev.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public List<Employee> employess(int pageNumber,int pageSize);

    Optional<Employee> getEmployeeId(Long id);

    void deleteEmpoyee(Long id);

    public Employee updateById(Employee employee);

    public List<Employee> findByName(String name);
    public Employee findByNameAndLocation(String name,String location);

    public Employee findByKeyword(String name);

    List<Employee> getEmployeeByNameOrLocation(String name,String location);

    Integer deleteEmployeeByName(String name);


    List<Employee> findDepartment();

}
