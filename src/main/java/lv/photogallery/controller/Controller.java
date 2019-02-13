package lv.photogallery.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/")
    public String index() {

        return "index";
    }

    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    @RequestMapping("/registration")
    public String registration() {

        return "registration";
    }
    @RequestMapping("/gallery")
    public String gallery() {

        return "gallery";
    }
    @RequestMapping("/dashboard")
    public String dashboard() {

        return "dashboard";
    }
    @RequestMapping("/admin")
    public String admin() {

        return "admin";
    }


}