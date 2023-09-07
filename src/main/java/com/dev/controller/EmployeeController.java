package com.dev.controller;

import com.dev.model.Employee;
import com.dev.repository.EmployeeRepository;
import com.dev.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;



    @PostMapping("/")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> employees(@RequestParam int pageNumber,@RequestParam int pageSize) {
        return new ResponseEntity<>(employeeService.employess(pageNumber,pageSize), OK) ;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeId(@PathVariable Long id)
    {
        return new ResponseEntity<>(employeeService.getEmployeeId(id), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable Long id)
    {
        this.employeeService.deleteEmpoyee(id);
        return new ResponseEntity<HttpStatus>(NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateById(@PathVariable Long id,@RequestBody  Employee employee) {
        employee.setId(id);
        return new ResponseEntity<Employee>(employeeService.updateById(employee), OK);
    }

    @GetMapping("find/{name}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable String name) {
        return new ResponseEntity<>(employeeService.findByName(name), HttpStatus.OK);
    }
    @GetMapping("find/findByNameAndLocation/{name}/{location}")
    public ResponseEntity<Employee> findByNameAndLocation(@PathVariable String name,@PathVariable String location) {
        return new ResponseEntity<>(employeeService.findByNameAndLocation(name,location), HttpStatus.OK);
    }

    @GetMapping("find/findBy/") // kam nahi kr raha hia starting se free time me dekhenge
    public ResponseEntity<Employee> findByKeyword(@PathVariable(value = "keyword") String name) {
        return new ResponseEntity<>(employeeService.findByKeyword(name), HttpStatus.OK);
    }



    @GetMapping("find/getEmployeeByNameOrLocation/{name}/{location}")
    public ResponseEntity<List<Employee>> getEmployeeByNameOrLocation(@PathVariable String name,@PathVariable String location) {
        return new ResponseEntity<>(this.employeeService.getEmployeeByNameOrLocation(name,location), OK);
    }



    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name) {
        return new ResponseEntity<>(employeeService.deleteEmployeeByName(name)+ "No. of records Deleted", OK);
    }

    // for native query
    @GetMapping("filter/")
    public List<Employee> findDepartment() {
        return employeeService.findDepartment();
    }




}
