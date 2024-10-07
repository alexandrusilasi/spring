package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String greet()
    {
        return "Hello World!";
    }

    @RequestMapping("/about")
    @ResponseBody
    public String about()
    {
        return "about";
    }

}
