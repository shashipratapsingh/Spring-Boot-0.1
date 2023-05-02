package springBootbackend.springBootbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springBootbackend.springBootbackend.model.Employee;
import springBootbackend.springBootbackend.service.EmployeeService;


@CrossOrigin("*")
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
	{
		Employee createEmployee = this.employeeService.createEmployee(employee);
		return new ResponseEntity<>(createEmployee,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long id)
	{
		return ResponseEntity.ok(this.employeeService.getEmployee(id));
	}
	
	@GetMapping("/employess")
	public ResponseEntity<List<Employee>> employees(){
		return ResponseEntity.ok(this.employeeService.getEmployesss());
		
	}
	
	//searching by the name
	@GetMapping("/searcingByName/{firstName}")
	public ResponseEntity<List<Employee>> searchEmployeeByName(@PathVariable String firstName){
		return ResponseEntity.ok(this.employeeService.searchEmployeeByName(firstName));
		
	}

}
