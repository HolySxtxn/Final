package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.Bill;
import com.example.PhotoMaster.models.Orders;
import com.example.PhotoMaster.models.Price;
import com.example.PhotoMaster.repo.BillRepository;
import com.example.PhotoMaster.repo.OrderRepository;
import com.example.PhotoMaster.repo.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class OrderController extends BaseController {
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    BillRepository billRepository;
    @Autowired
    OrderRepository orderRepository;


    @GetMapping("/order/add")
    public String orderAdd(Model model, Orders order) {
        insertHeader(model);
        Iterable<Price> prices = priceRepository.findAll();
        model.addAttribute("prices", prices);
        Iterable<Bill> bills = billRepository.findAll();
        model.addAttribute("bills", bills);
        model.addAttribute("order", order);
        return "order/order-add";
    }

    @PostMapping("/order/add")
    public String orderDataAdd(Model model, @ModelAttribute("order") @Valid Orders order,
                               @RequestParam String pricelist,
                               @RequestParam Long billid,
                               BindingResult bindingResult) {
        insertHeader(model);
        try {

            Price price = priceRepository.findByName(pricelist);
            Orders orderForAdd = new Orders(
                    billRepository.findById(billid).orElseThrow(),
                    price,
                    order.getCount());
            if (bindingResult.hasErrors()) {
                return "order/order-add";
            }
            orderRepository.save(orderForAdd);
            return "redirect:/order/add";
        } catch (Exception e) {
            return "redirect:/order/add";
        }
    }


}
