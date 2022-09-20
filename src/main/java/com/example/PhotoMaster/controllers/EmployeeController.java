package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.Employee;
import com.example.PhotoMaster.models.Role;
import com.example.PhotoMaster.models.User;
import com.example.PhotoMaster.repo.EmployeeRepository;
import com.example.PhotoMaster.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

@Controller
public class EmployeeController extends BaseController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/employee")
    public String employeeMain(Model model) {
        insertHeader(model);
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employee/employee-main";
    }

    @GetMapping("/employee/add")
    public String employeeAdd(Model model, Employee employee, User user) {
        insertHeader(model);
        model.addAttribute("employee", employee);
        model.addAttribute("user", user);
        return "employee/employee-add";
    }

    @PostMapping("/employee/add")
    public String employeeDataAdd(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult,
                                  @ModelAttribute("user") @Valid User user, BindingResult userResult,
                                  @RequestParam String role, Model model) {
        insertHeader(model);
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate birthday = LocalDate.parse(employee.getBirth());
            if (Period.between(birthday, currentDate).getYears() >= 18) {
                if (bindingResult.hasErrors()) {
                    return "employee/employee-add";
                }
                if (userResult.hasErrors()) {
                    return "/employee/add";
                }
                user.setActive(true);
                user.setRoles(Collections.singleton(Role.valueOf(role)));
                employee.setUser(user);
                employeeRepository.save(employee);

                return "redirect:/employee";
            } else
                return "employee/employee-add";
        } catch (Exception e) {
            return "redirect:/employee";
        }
    }

    @RequestMapping(value = "/employee/{id}/edit", method = RequestMethod.GET)

    public String employeeEdit(@PathVariable("id") long id,
                               Model model) {
        insertHeader(model);
        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        return "employee/employee-edit";
    }

    @RequestMapping(value = "/employee/{id}/edit", method = RequestMethod.POST)

    public String employeeDataUpdate(Model model, @ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult,
                                     @PathVariable("id") long id) {
        insertHeader(model);
        try {
            employee.setId(id);
            LocalDate currentDate = LocalDate.now();
            LocalDate birthday = LocalDate.parse(employee.getBirth());
            if (Period.between(birthday, currentDate).getYears() >= 18) {
                if (bindingResult.hasErrors()) {
                    return "employee/employee-edit";
                }
                employeeRepository.save(employee);
                return "redirect:/employee";
            } else
                return "employee/employee-edit";
        } catch (Exception e) {
            return "redirect:/employee";
        }

    }

    @GetMapping("/employee/filter")
    public String employeeFilter(Model model) {
        insertHeader(model);
        return "employee/employee-filter";
    }

    @PostMapping("/employee/filter/result")
    public String clientResultContains(@RequestParam String surname, Model model) {
        insertHeader(model);
        List<Employee> result = employeeRepository.findByInnContains(surname);
        model.addAttribute("result", result);
        return "employee/employee-filter";
    }

    @RequestMapping(value = "/employee/{id}/remove", method = RequestMethod.POST)
    public String employeeDataDelete(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        try {
            Employee employee = employeeRepository.findById(id).orElseThrow();
            employeeRepository.delete(employee);
            return "redirect:/employee";
        } catch (Exception e) {
            return "redirect:/employee";
        }
    }

}
