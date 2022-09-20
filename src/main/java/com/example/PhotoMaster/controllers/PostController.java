package com.example.PhotoMaster.controllers;

import com.example.PhotoMaster.models.Post;
import com.example.PhotoMaster.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController extends BaseController {
    @Autowired
    PostRepository postRepository;

    @GetMapping("/post")
    public String postMain(Model model) {
        insertHeader(model);
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "post/post-main";
    }

    @GetMapping("/post/add")
    public String postAdd(Model model, Post post) {
        insertHeader(model);
        model.addAttribute("post", post);
        return "post/post-add";
    }

    @PostMapping("/post/add")
    public String postDataAdd(Model model, @ModelAttribute("post") @Valid Post post, BindingResult bindingResult) {
        insertHeader(model);
        try {
            if (bindingResult.hasErrors()) {
                return "post/post-add";
            }
            postRepository.save(post);
            return "redirect:/post";
        } catch (Exception e) {
            return "redirect:/post";
        }
    }

    @RequestMapping(value = "/post/{id}/edit", method = RequestMethod.GET)

    public String postEdit(@PathVariable("id") long id,
                           Model model) {
        insertHeader(model);
        Post post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "post/post-edit";
    }

    @RequestMapping(value = "/post/{id}/edit", method = RequestMethod.POST)

    public String postDataUpdate(Model model, @ModelAttribute("post") @Valid Post post, BindingResult bindingResult,
                                 @PathVariable("id") long id) {
        insertHeader(model);
        try {
            post.setId(id);
            if (bindingResult.hasErrors()) {
                return "post/post-edit";
            }
            postRepository.save(post);
            return "redirect:/post";
        } catch (Exception e) {
            return "redirect:/post";
        }

    }

    @GetMapping("/post/filter")
    public String postFilter(Model model) {
        insertHeader(model);

        return "post/post-filter";
    }

    @PostMapping("/post/filter/result")
    public String postResultContains(@RequestParam String name, Model model) {
        insertHeader(model);
        List<Post> result = postRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "post/post-filter";
    }

    @RequestMapping(value = "/post/{id}/remove", method = RequestMethod.POST)
    public String postDataDelete(@PathVariable("id") long id, Model model) {
        insertHeader(model);
        try {
            Post post = postRepository.findById(id).orElseThrow();
            postRepository.delete(post);
            return "redirect:/post";
        } catch (Exception e) {
            return "redirect:/post";
        }
    }


}
