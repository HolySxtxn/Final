package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.User;
import com.example.PhotoMaster.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController extends BaseController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public String userMain(Model model) {
        insertHeader(model);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user/user-main";
    }


    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)

    public String userEdit(@PathVariable("id") long id,
                           Model model) {
        insertHeader(model);
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "user/user-edit";
    }

    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.POST)

    public String userDataUpdate(Model model, @ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                                 @PathVariable("id") long id) {
        insertHeader(model);
        try {
            user.setId(id);
            if (bindingResult.hasErrors()) {
                return "user/user-edit";
            }
            userRepository.save(user);
            return "redirect:/user";
        } catch (Exception e) {
            return "redirect:/user";
        }

    }


    @RequestMapping(value = "/user/{id}/remove", method = RequestMethod.POST)
    public String priceDataDelete(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        try {
            User user = userRepository.findById(id).orElseThrow();
            userRepository.delete(user);
            return "redirect:/user";
        } catch (Exception e) {
            return "redirect:/user";
        }
    }


}
