package com.example.app.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.app.Model.Employee;

public interface EmployeeService {
 
    List<Employee> getallEmployees();

    void saveEmployee(Employee emp);
    
    Employee getEmployeeById(Long id);

    void deleteEmployee(Long id);
    Page<Employee> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection);
}
