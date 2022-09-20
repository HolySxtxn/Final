package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.Status;
import com.example.PhotoMaster.repo.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StatusController extends BaseController {
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/status")
    public String statusMain(Model model) {
        insertHeader(model);
        Iterable<Status> statuses = statusRepository.findAll();
        model.addAttribute("statuses", statuses);
        return "status/status-main";
    }

    @GetMapping("/status/add")
    public String statusAdd(Model model, Status status) {
        insertHeader(model);
        model.addAttribute("status", status);
        return "status/status-add";
    }

    @PostMapping("/status/add")
    public String statusDataAdd(Model model, @ModelAttribute("status") @Valid Status status, BindingResult bindingResult) {
        insertHeader(model);
        try {
            if (bindingResult.hasErrors()) {
                return "status/status-add";
            }
            statusRepository.save(status);
            return "redirect:/status";
        } catch (Exception e) {
            return "redirect:/status";
        }
    }

    @RequestMapping(value = "/status/{id}/edit", method = RequestMethod.GET)

    public String statusEdit(@PathVariable("id") long id,
                             Model model) {
        insertHeader(model);
        Status status = statusRepository.findById(id).orElseThrow();
        model.addAttribute("status", status);
        return "status/status-edit";
    }

    @RequestMapping(value = "/status/{id}/edit", method = RequestMethod.POST)

    public String statusDataUpdate(Model model, @ModelAttribute("status") @Valid Status status, BindingResult bindingResult,
                                   @PathVariable("id") long id) {
        insertHeader(model);
        try {
            status.setId(id);
            if (bindingResult.hasErrors()) {
                return "status/status-edit";
            }
            statusRepository.save(status);
            return "redirect:/status";
        } catch (Exception e) {
            return "redirect:/status";
        }

    }

    @GetMapping("/status/filter")
    public String statusFilter(Model model) {
        insertHeader(model);
        return "status/status-filter";
    }

    @PostMapping("/status/filter/result")
    public String statusResultContains(@RequestParam String name, Model model) {
        insertHeader(model);
        List<Status> result = statusRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "status/status-filter";
    }

    @RequestMapping(value = "/status/{id}/remove", method = RequestMethod.POST)
    public String statusDataDelete(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        try {
            Status status = statusRepository.findById(id).orElseThrow();
            statusRepository.delete(status);
            return "redirect:/status";
        } catch (Exception e) {
            return "redirect:/status";
        }
    }


}
