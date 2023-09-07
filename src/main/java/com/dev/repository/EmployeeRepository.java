package com.dev.repository;

import com.dev.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
    List<Employee> findByName(String name,Sort sort);

    Employee findByNameAndLocation(String name,String location);


    Employee findByNameContaining(String keyword);


    @Query("FROM Employee WHERE name =:name OR location =:location")
    List<Employee> getEmployeeByNameOrLocation(String name,String location);

    @Transactional
    @Modifying
    @Query("DELETE FROM Employee WHERE name =:name")
    Integer deleteEmployeeByName(String name);

     //nativa query
    //@Query(value = "select * from Employee WHERE department=:department", nativeQuery=true)
    @Query(value = "select * from Employee", nativeQuery=true)
    List<Employee> getDepartment();
}
