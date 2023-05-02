package springBootbackend.springBootbackend.service;

import java.util.List;

import springBootbackend.springBootbackend.model.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee employee);
	
	public Employee getEmployee(long id);
	
	List<Employee> getEmployesss();
	
	 List<Employee>  searchEmployeeByName(String firstName);
	
	
	

}
