package com.arcode.arcode.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/component")
public class ComponentView {
    @GetMapping ("/{ruta}")
    public String componente(@PathVariable String ruta) {
        return "components/" + ruta;
    }
}