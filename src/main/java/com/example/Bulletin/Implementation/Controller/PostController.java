package com.example.Bulletin.Implementation.Controller;

import com.example.Bulletin.Implementation.Entity.Post;
import com.example.Bulletin.Implementation.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public String listPosts(Model model){
        model.addAttribute("posts", postService.getAllPosts());
        return "posts";
    }
    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model){
        Post post = postService.getPostById(id);
        post.setViews(post.getViews() + 1);
        postService.updatePost(id, post);
        model.addAttribute("post", post);
        return "View";
    }
    @GetMapping("/new")
    public String createPost(Model model){
        model.addAttribute("post", new Post());
        return "Create";
    }
    @PostMapping
    public String createPost1(@ModelAttribute Post post){
        postService.createPost(post);
        return "redirect:/posts";
    }
    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable Long id, Model model){
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "Edit";
    }
    @PostMapping("/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post){
        postService.updatePost(id, post);
        return "redirect:/posts";
    }
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id, @RequestParam String password){
        Post post = postService.getPostById(id);
        if (post.getPassword().equals(password)){
            postService.deletePost(id);
        }
        return "redirect:/posts";
    }

}
