package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;
    private final UserService userService;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, UserService userService, EmailService emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String postIndex(Model model) {

        List<Post> postList = postsDao.findAll();

        model.addAttribute("title", "All Posts");
        model.addAttribute("posts", postList);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postView(Model model, @PathVariable long id ) {
        Post post = postsDao.getOne(id);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post) {
        User user = userService.getLoggedInUser();
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String postForm(Model model) {
        model.addAttribute("post", new Post());
            return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        User user = userService.getLoggedInUser();
        post.setUser(user);

        Post savedPost = postsDao.save(post);

        String subject = "New Post Created: " + savedPost.getTitle();
        String body = "Dear " + savedPost.getUser().getUsername()
                + ". Thank you for creating a post. Your post id is "
                + savedPost.getId();
        emailService.prepareAndSend(savedPost, subject, body);
        return "redirect:/posts";
    }

}
