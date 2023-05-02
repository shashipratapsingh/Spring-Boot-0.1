package springBootbackend.springBootbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springBootbackend.springBootbackend.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByfirstName(String firstName);

}
