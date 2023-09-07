package com.dev.service.impl;

import com.dev.model.Employee;
import com.dev.repository.EmployeeRepository;
import com.dev.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> employess(int pageNumber,int pageSize) {
        Pageable pages= PageRequest.of(pageNumber,pageSize, Sort.Direction.ASC,"id");
      //  Pageable pages= PageRequest.of(pageNumber,pageSize, Sort.Direction.ASC,"id","name"); // we can implment multipale coloem ofr shorting
        return employeeRepository.findAll(pages).getContent();
    }

    @Override
    public Optional<Employee> getEmployeeId(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteEmpoyee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override

    public Employee updateById(Employee employee) {
        return employeeRepository.save(employee);
    }
    @Override
    public List<Employee> findByName(String name) {
        Sort sort=Sort.by(Sort.Direction.DESC,"age");
        return (List<Employee>) employeeRepository.findByName(name,sort);
    }
    @Override
    public Employee findByNameAndLocation(String name,String location) {
        return employeeRepository.findByNameAndLocation(name,location);
    }

    @Override
    public Employee findByKeyword(String name) {
        return employeeRepository.findByNameContaining(name);
    }

    @Override
    public List<Employee> getEmployeeByNameOrLocation(String name,String location) {
        return employeeRepository.getEmployeeByNameOrLocation(name,location);
    }

    @Override
    public Integer deleteEmployeeByName(String name) {
        return employeeRepository.deleteEmployeeByName(name);
    }

    @Override
    public List<Employee> findDepartment() {
        return employeeRepository.getDepartment();
    }


}
