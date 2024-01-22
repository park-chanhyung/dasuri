package com.project.dasuri;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class checkcontroller {
    @RequestMapping("/index")
    public String login() {

        return "index";
    }

}
