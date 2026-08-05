package com.arcode.arcode.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainView {
    @GetMapping("/main")
    public String clientes() {
        return "components/main";
    }
}