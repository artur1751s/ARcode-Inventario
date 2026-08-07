package com.arcode.arcode.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainView {
    @GetMapping
    public String main() {
        return "components/main";
    }

    @GetMapping("/{ruta}")
    public String ruta(@PathVariable String ruta) {
        return "components/main";
    }

    
}