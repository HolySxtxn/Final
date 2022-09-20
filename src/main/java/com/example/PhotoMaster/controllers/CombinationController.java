package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.Combination;
import com.example.PhotoMaster.models.Employee;
import com.example.PhotoMaster.models.Post;
import com.example.PhotoMaster.repo.CombinationRepository;
import com.example.PhotoMaster.repo.EmployeeRepository;
import com.example.PhotoMaster.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CombinationController extends BaseController {
    @Autowired
    CombinationRepository combinationRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/combination")
    public String combinationMain(Model model) {
        insertHeader(model);
        Iterable<Combination> combinations = combinationRepository.findAll();
        model.addAttribute("combinations", combinations);
        return "combination/combination-main";
    }

    @GetMapping("/combination/add")
    public String combinationAdd(Model model, Combination combination) {
        insertHeader(model);
        Iterable<Employee> employee = employeeRepository.findAll();
        Iterable<Post> post = postRepository.findAll();
        model.addAttribute("post", post);
        model.addAttribute("employee", employee);
        model.addAttribute("combination", combination);
        return "combination/combination-add";
    }

    @PostMapping("/combination/add")
    public String combinationDataAdd(Model model, @ModelAttribute("combination") @Valid Combination combination,
                                     @RequestParam String employeeid,
                                     @RequestParam String postname,
                                     BindingResult bindingResult) {
        insertHeader(model);
        try {
            String[] data = employeeid.split(" ");
            Employee employee1 = employeeRepository.findById(Long.parseLong(data[0])).orElseThrow();
            Post post1 = postRepository.findByName(postname);
            Combination combinationForAdd = new Combination(
                    employee1,
                    post1,
                    combination.getPart());
            if (bindingResult.hasErrors()) {
                return "combination/combination-add";
            }
            combinationRepository.save(combinationForAdd);
            return "redirect:/combination";
        } catch (Exception e) {
            return "redirect:/combination";
        }
    }

    @RequestMapping(value = "/combination/{id}/edit", method = RequestMethod.GET)

    public String combinationEdit(@PathVariable("id") long id,
                                  Model model) {
        insertHeader(model);
        Combination combination = combinationRepository.findById(id).orElseThrow();
        model.addAttribute("combination", combination);
        Employee employee = combination.getEmployee();
        Post post = combination.getPost();
        model.addAttribute("post", post);
        model.addAttribute("employee", employee);
        return "combination/combination-edit";
    }

    @RequestMapping(value = "/combination/{id}/edit", method = RequestMethod.POST)

    public String combinationDataUpdate(Model model, @ModelAttribute("combination") @Valid Combination combination,
                                        @RequestParam String employeeid,
                                        @RequestParam String postname,
                                        BindingResult bindingResult, @PathVariable("id") long id) {
        insertHeader(model);
        try {
            String[] data = employeeid.split(" ");
            Employee employee1 = employeeRepository.findById(Long.parseLong(data[0])).orElseThrow();
            Post post1 = postRepository.findByName(postname);
            Combination combinationForUpdate = new Combination(
                    employee1,
                    post1,
                    combination.getPart());
            combinationForUpdate.setId(id);
            if (bindingResult.hasErrors()) {
                return "combination/combination-edit";
            }
            combinationRepository.save(combinationForUpdate);
            return "redirect:/combination";
        } catch (Exception e) {
            return "redirect:/combination";
        }

    }

    @RequestMapping(value = "/combination/{id}/remove", method = RequestMethod.POST)
    public String combinationDataDelete(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        try {
            Combination combination = combinationRepository.findById(id).orElseThrow();
            combinationRepository.delete(combination);
            return "redirect:/combination";
        } catch (Exception e) {
            return "redirect:/combination";
        }
    }

    @GetMapping("/combination/filter")
    public String combinationFilter(Model model) {
        insertHeader(model);
        return "combination/combination-filter";
    }

    @PostMapping("/combination/filter/result")
    public String combinationResultContains(@RequestParam Long employeeid, Model model) {
        insertHeader(model);
        List<Combination> result = combinationRepository.findByEmployee(employeeRepository.findById(employeeid).orElseThrow());
        model.addAttribute("result", result);
        return "combination/combination-filter";
    }

}
