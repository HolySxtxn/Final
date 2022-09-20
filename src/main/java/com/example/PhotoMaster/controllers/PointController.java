package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.Point;
import com.example.PhotoMaster.repo.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PointController extends BaseController {
    @Autowired
    PointRepository pointRepository;

    @GetMapping("/point")
    public String pointMain(Model model) {
        insertHeader(model);
        Iterable<Point> points = pointRepository.findAll();
        model.addAttribute("points", points);
        return "point/point-main";
    }

    @GetMapping("/point/add")
    public String pointAdd(Model model, Point point) {
        insertHeader(model);
        model.addAttribute("point", point);
        return "point/point-add";
    }

    @PostMapping("/point/add")
    public String pointDataAdd(Model model, @ModelAttribute("point") @Valid Point point, BindingResult bindingResult) {
        insertHeader(model);
        try {
            if (bindingResult.hasErrors()) {
                return "point/point-add";
            }
            pointRepository.save(point);
            return "redirect:/point";
        } catch (Exception e) {
            return "redirect:/point";
        }
    }

    @RequestMapping(value = "/point/{id}/edit", method = RequestMethod.GET)

    public String pointEdit(@PathVariable("id") long id,
                            Model model) {
        insertHeader(model);
        Point point = pointRepository.findById(id).orElseThrow();
        model.addAttribute("point", point);
        return "point/point-edit";
    }

    @RequestMapping(value = "/point/{id}/edit", method = RequestMethod.POST)

    public String pointDataUpdate(Model model, @ModelAttribute("point") @Valid Point point, BindingResult bindingResult,
                                  @PathVariable("id") long id) {
        insertHeader(model);
        try {
            point.setId(id);
            if (bindingResult.hasErrors()) {
                return "point/point-edit";
            }
            pointRepository.save(point);
            return "redirect:/point";
        } catch (Exception e) {
            return "redirect:/point";
        }

    }

    @GetMapping("/point/filter")
    public String pointFilter(Model model) {
        insertHeader(model);
        return "point/point-filter";
    }

    @PostMapping("/point/filter/result")
    public String pointResultContains(@RequestParam String street, Model model) {
        insertHeader(model);
        List<Point> result = pointRepository.findByStreetContains(street);
        model.addAttribute("result", result);
        return "point/point-filter";
    }

    @RequestMapping(value = "/point/{id}/remove", method = RequestMethod.POST)
    public String pointDataDelete(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        try {
            Point point = pointRepository.findById(id).orElseThrow();
            pointRepository.delete(point);
            return "redirect:/point";
        } catch (Exception e) {
            return "redirect:/point";
        }
    }


}
