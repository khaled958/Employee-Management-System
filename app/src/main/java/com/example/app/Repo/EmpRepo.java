package com.example.app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.Model.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee,Long>{
 
}
