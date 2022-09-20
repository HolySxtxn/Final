package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.Price;
import com.example.PhotoMaster.repo.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PriceController extends BaseController {
    @Autowired
    PriceRepository priceRepository;

    @GetMapping("/price")
    public String priceMain(Model model) {
        insertHeader(model);
        Iterable<Price> prices = priceRepository.findAll();
        model.addAttribute("prices", prices);
        return "price/price-main";
    }

    @GetMapping("/price/add")
    public String priceAdd(Model model, Price price) {
        insertHeader(model);
        model.addAttribute("price", price);
        return "price/price-add";
    }

    @PostMapping("/price/add")
    public String priceDataAdd(Model model, @ModelAttribute("price") @Valid Price price, BindingResult bindingResult) {
        insertHeader(model);
        try {
            if (bindingResult.hasErrors()) {
                return "price/price-add";
            }
            priceRepository.save(price);
            return "redirect:/price";
        } catch (Exception e) {
            return "redirect:/price";
        }
    }

    @RequestMapping(value = "/price/{id}/edit", method = RequestMethod.GET)

    public String priceEdit(@PathVariable("id") long id,
                            Model model) {
        insertHeader(model);
        Price price = priceRepository.findById(id).orElseThrow();
        model.addAttribute("price", price);
        return "price/price-edit";
    }

    @RequestMapping(value = "/price/{id}/edit", method = RequestMethod.POST)

    public String priceDataUpdate(Model model, @ModelAttribute("price") @Valid Price price, BindingResult bindingResult,
                                  @PathVariable("id") long id) {
        insertHeader(model);
        try {
            price.setId(id);
            if (bindingResult.hasErrors()) {
                return "price/price-edit";
            }
            priceRepository.save(price);
            return "redirect:/price";
        } catch (Exception e) {
            return "redirect:/price";
        }

    }

    @GetMapping("/price/filter")
    public String priceFilter(Model model) {
        insertHeader(model);
        return "price/price-filter";
    }

    @PostMapping("/price/filter/result")
    public String priceResultContains(@RequestParam String name, Model model) {
        insertHeader(model);
        List<Price> result = priceRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "price/price-filter";
    }

    @RequestMapping(value = "/price/{id}/remove", method = RequestMethod.POST)
    public String priceDataDelete(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        try {
            Price price = priceRepository.findById(id).orElseThrow();
            priceRepository.delete(price);
            return "redirect:/price";
        } catch (Exception e) {
            return "redirect:/price";
        }
    }


}
