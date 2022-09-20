package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.Client;
import com.example.PhotoMaster.models.Loyalty;
import com.example.PhotoMaster.repo.ClientRepository;
import com.example.PhotoMaster.repo.LoyaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoyaltyController extends BaseController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    LoyaltyRepository loyaltyRepository;

    @GetMapping("/loyalty")
    public String loyaltyMain(Model model) {
        insertHeader(model);
        Iterable<Loyalty> loyalties = loyaltyRepository.findAll();
        model.addAttribute("loyalties", loyalties);
        return "loyalty/loyalty-main";
    }

    @GetMapping("/loyalty/filter")
    public String loyaltyFilter(Model model) {
        insertHeader(model);
        return "loyalty/loyalty-filter";
    }

    @PostMapping("/loyalty/filter/result")
    public String loyaltyResultContains(@RequestParam String phone, Model model) {
        insertHeader(model);
        Client client = clientRepository.findByPhone(phone);
        Loyalty result = loyaltyRepository.findByClient(client);
        model.addAttribute("result", result);
        return "loyalty/loyalty-filter";
    }

    @RequestMapping(value = "/loyalty/{id}/remove", method = RequestMethod.POST)
    public String loyaltyDataDelete(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        try {
            Loyalty loyalty = loyaltyRepository.findById(id).orElseThrow();
            loyaltyRepository.delete(loyalty);
            return "redirect:/loyalty";
        } catch (Exception e) {
            return "redirect:/loyalty";
        }
    }


}
