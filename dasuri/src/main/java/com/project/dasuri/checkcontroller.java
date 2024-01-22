package com.project.dasuri;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class checkcontroller {
    @RequestMapping("/index")
    public String login() {

        return "index";
    }

    @RequestMapping("/notice")
    public String notice() {

        return "notice";
    }

    @RequestMapping("/community")
    public String community() {

        return "community";
    }

    @RequestMapping("/proinfo")
    public String proinfo() {

        return "proinfo";
    }

    @RequestMapping("/servicecenter")
    public String servicecenter() {

        return "servicecenter";
    }
}



