package ru.otus.spring.psannikov.spring.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.psannikov.spring.security.service.MyService;

@Controller
public class PagesController {

    private final MyService myService;

    public PagesController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/public")
    public String publicPage() {
        return "public";
    }

    @GetMapping("/user")
    public String userPage() {
        myService.onlyUser();
        return "user";
    }

    @GetMapping("/manager")
    public String managerPage() {
        myService.onlyAdmin();
        return "manager";
    }

    @GetMapping("/admin")
    //@Secured( "ADMIN" )
    public String adminPage() {
//        myService.onlyUser();
        myService.onlyAdmin();
        return "admin";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
            .getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        return "authenticated";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }
}
