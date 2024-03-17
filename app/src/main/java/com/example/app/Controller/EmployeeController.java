package com.example.app.Controller;

import org.springframework.data.domain.Page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.Model.Employee;
import com.example.app.Service.EmployeeService;


@Controller
public class EmployeeController{

    @Autowired
    private EmployeeService empService;
    //display list of employee
    @GetMapping("/")
    public String viewHomePage(Model model){
    //    model.addAttribute("listEmp", empService.getallEmployees());
    //    return "index";
     return findPaginated(1,"firstname","asc",model);
    }

    @GetMapping("/ShowNewEmployeeForm")
    public String ShowNewEmployeeForm(Model model){
        Employee emp = new Employee();
        model.addAttribute("emp", emp);
        return "newemp";
    }

    @PostMapping("/saveEmp")
    public String saveEmployee(@ModelAttribute("emp") Employee Emp){
        empService.saveEmployee(Emp);
        return "redirect:/";
    }

    @GetMapping("/ShowFormForUpdate/{id}")
    public String ShowFormForUpxdate(@PathVariable("id") Long id, Model model){
        Employee emp = empService.getEmployeeById(id);
        model.addAttribute("emp", emp);
        return "update_emp";

    }

    @GetMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id") Long id){
        empService.deleteEmployee(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo,@RequestParam("sortField") String sortField,@RequestParam("sortDir") String sortDir, Model model){
        int pageSize = 5;
        Page<Employee> pg = empService.findPaginated(pageNo, pageSize,sortField,sortDir);
        List<Employee> lst  = pg.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", pg.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverse", sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("totalItem", pg.getTotalElements());
        model.addAttribute("listEmp", lst);
        return "index";

    }
}
