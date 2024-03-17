package com.example.app.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.app.Model.Employee;
import com.example.app.Repo.EmpRepo;

@Service
public class EmpService implements EmployeeService{

    @Autowired
    private EmpRepo repo;

    @Override
    public List<Employee> getallEmployees() {
      return repo.findAll();
    }

    @Override
    public void saveEmployee(Employee emp){
         repo.save(emp);
    }

    @Override
    public Employee getEmployeeById(Long id){
        Optional<Employee> optional = repo.findById(id);
        Employee emp = null;
        if(optional.isPresent()){
             emp = optional.get();
        }
        else{
             throw new RuntimeException("Employee not found for id : "+id);
        }
        return emp;
    }

    @Override
    public void deleteEmployee(Long id){
        repo.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();  
        Pageable pageable = PageRequest.of(pageNo-1, pageSize,sort);
           return repo.findAll(pageable);
    }



}
