package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.Client;
import com.example.PhotoMaster.models.Loyalty;
import com.example.PhotoMaster.repo.ClientRepository;
import com.example.PhotoMaster.repo.LoyaltyRepository;
import com.example.PhotoMaster.repo.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;

@Controller
public class ClientController extends BaseController {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    LoyaltyRepository loyaltyRepository;

    @GetMapping("/client")
    public String clientMain(Model model) {
        insertHeader(model);
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "client/client-main";
    }

    @GetMapping("/client/add")
    public String clientAdd(Model model, Client client) {
        insertHeader(model);
        model.addAttribute("client", client);
        return "client/client-add";
    }

    @PostMapping("/client/add")
    public String clientDataAdd(Model model, @ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {
        insertHeader(model);
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate birthday = LocalDate.parse(client.getBirth());
            if (Period.between(birthday, currentDate).getYears() >= 14) {
                if (bindingResult.hasErrors()) {
                    return "client/client-add";
                }
                clientRepository.save(client);
                Loyalty loyalty = new Loyalty(client,
                        0,
                        statusRepository.findBySum(0));
                loyaltyRepository.save(loyalty);
                return "redirect:/client";
            } else
                return "client/client-add";
        } catch (Exception e) {
            return "redirect:/client";
        }
    }

    @RequestMapping(value = "/client/{id}/edit", method = RequestMethod.GET)

    public String clientEdit(@PathVariable("id") long id,
                             Model model) {
        insertHeader(model);
        Client client = clientRepository.findById(id).orElseThrow();
        model.addAttribute("client", client);
        return "client/client-edit";
    }

    @RequestMapping(value = "/client/{id}/edit", method = RequestMethod.POST)

    public String clientDataUpdate(Model model, @ModelAttribute("client") @Valid Client client, BindingResult bindingResult,
                                   @PathVariable("id") long id) {
        insertHeader(model);
        try {
            client.setId(id);
            LocalDate currentDate = LocalDate.now();
            LocalDate birthday = LocalDate.parse(client.getBirth());
            if (Period.between(birthday, currentDate).getYears() >= 14) {
                if (bindingResult.hasErrors()) {
                    return "client/client-edit";
                }
                clientRepository.save(client);
                return "redirect:/client";
            } else
                return "client/client-edit";
        } catch (Exception e) {
            return "redirect:/client";
        }

    }

    @GetMapping("/client/filter")
    public String clientFilter(Model model) {
        insertHeader(model);
        return "client/client-filter";
    }

    @PostMapping("/client/filter/result")
    public String clientResultContains(@RequestParam String phone, Model model) {
        insertHeader(model);
        Client result = clientRepository.findByPhone(phone);
        model.addAttribute("result", result);
        return "client/client-filter";
    }

    @RequestMapping(value = "/client/{id}/remove", method = RequestMethod.POST)
    public String clientDataDelete(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        try {
            Client client = clientRepository.findById(id).orElseThrow();
            clientRepository.delete(client);
            return "redirect:/client";
        } catch (Exception e) {
            return "redirect:/client";
        }
    }


}
