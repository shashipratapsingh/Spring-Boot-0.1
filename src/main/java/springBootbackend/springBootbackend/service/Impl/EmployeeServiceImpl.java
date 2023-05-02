package springBootbackend.springBootbackend.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootbackend.springBootbackend.model.Employee;
import springBootbackend.springBootbackend.repository.EmployeeRepository;
import springBootbackend.springBootbackend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployee(long id) {
		return this.employeeRepository.findById(id).orElseThrow();
	}

	@Override
	public List<Employee> getEmployesss() {
		List<Employee> employees=this.employeeRepository.findAll();
		return employees;
	}

	@Override
	public List<Employee>  searchEmployeeByName(String firstName) {
		List<Employee> employees=this.employeeRepository.findByfirstName(firstName);
		return employees;
	}

}
