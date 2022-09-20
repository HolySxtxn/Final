package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.*;
import com.example.PhotoMaster.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class BillController extends BaseController {
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    BillRepository billRepository;
    @Autowired
    PointRepository pointRepository;
    @Autowired
    LoyaltyRepository loyaltyRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/bill")
    public String billMain(Model model) {
        insertHeader(model);
        Iterable<Bill> bills = billRepository.findAll();
        model.addAttribute("bills", bills);
        return "bill/bill-main";
    }

    @RequestMapping(value = "/bill/{id}", method = RequestMethod.GET)
    public String billOrder(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        Bill bill = billRepository.findById(id).orElseThrow();
        Iterable<Orders> orders = orderRepository.findByBill(bill);
        model.addAttribute("orders", orders);
        model.addAttribute("bill", bill);
        return "bill/bill-order";
    }

    @GetMapping("/bill/add")
    public String billAdd(Model model, Bill bill) {
        insertHeader(model);
        Iterable<Point> point = pointRepository.findAll();
        model.addAttribute("point", point);
        model.addAttribute("bill", bill);
        return "bill/bill-add";
    }

    @PostMapping("/bill/add")
    public String billDataAdd(Model model, @ModelAttribute("bill") @Valid Bill bill,
                              @RequestParam String employeeid,
                              @RequestParam String phone,
                              @RequestParam String pointinfo,
                              BindingResult bindingResult) {
        insertHeader(model);
        try {
            String[] data = pointinfo.split(" ");
            Point point = pointRepository.findById(Long.parseLong(data[0])).orElseThrow();
            Client client = clientRepository.findByPhone(phone);
            Loyalty loyalty = loyaltyRepository.findByClient(client);
            Employee employee = employeeRepository.findById(Long.parseLong(employeeid)).orElseThrow();
            Bill billForAdd = new Bill(
                    new Date().toString(),
                    employee,
                    loyalty,
                    point);
            if (bindingResult.hasErrors()) {
                return "bill/bill-add";
            }
            billRepository.save(billForAdd);
            return "redirect:/bill";
        } catch (Exception e) {
            return "redirect:/bill";
        }
    }


    @RequestMapping(value = "/bill/{id}/remove", method = RequestMethod.POST)
    public String BillDataDelete(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        try {
            Bill bill = billRepository.findById(id).orElseThrow();
            billRepository.delete(bill);
            return "redirect:/bill";
        } catch (Exception e) {
            return "redirect:/bill";
        }
    }

    @GetMapping("/bill/filter")
    public String billFilter(Model model) {

        insertHeader(model);
        return "bill/bill-filter";
    }

    @PostMapping("/bill/filter/result")
    public String billResultContains(@RequestParam String phone, Model model) {
        insertHeader(model);
        Client client = clientRepository.findByPhone(phone);
        Loyalty loyalty = loyaltyRepository.findByClient(client);
        Iterable<Bill> result = billRepository.findByLoyalty(loyalty);
        model.addAttribute("result", result);
        return "bill/bill-filter";
    }


}
