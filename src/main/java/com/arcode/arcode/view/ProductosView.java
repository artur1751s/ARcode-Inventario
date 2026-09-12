package com.arcode.arcode.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arcode.arcode.repository.BodegasRepository;
import com.arcode.arcode.model.Productos;
import com.arcode.arcode.repository.ProductosRepository;

@Controller
public class ProductosView {
    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private BodegasRepository bodegasRepository;

    @GetMapping("/main/module/inventario")
    public String lista(Model model) {
        model.addAttribute("productos", productosRepository.findAll());
        model.addAttribute("bodegas", bodegasRepository.findAll());

        model.addAttribute("producto", new Productos());

        return "components/inventario";
    }
    
    @PostMapping("/productos/save")
    public String save(@ModelAttribute("producto") Productos productos, RedirectAttributes ra) {
        productosRepository.save(productos);
        ra.addAttribute("message", "Producto creado con exito");
        return "redirect:/main/inventario";
    }

    @PostMapping("/productos/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        productosRepository.deleteById(id);
        ra.addAttribute("message", "Producto eliminado con exito");
        return "redirect:/main/module/inventario";
    }
}