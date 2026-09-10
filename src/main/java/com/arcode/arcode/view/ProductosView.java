package com.arcode.arcode.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.arcode.arcode.model.Productos;
import com.arcode.arcode.repository.ProductosRepository;

@Controller
public class ProductosView {
    @Autowired
    private ProductosRepository productosRepository;
    @GetMapping("/main/module/inventario")
    public String lista(Model model) {
        model.addAttribute("productos", productosRepository.findAll());
        return "components/inventario";
    }

    //TODO: Realizar el modal del registro de productos e ingresar la ruta
    // 
    // @GetMapping("/productos/register")
    // public String form(Model model) {
    //     model.addAttribute("productos", new Productos());
    //     return "auth/register";
    // }
    //
    // @PostMapping("/productos/save")
    // public String save(@ModelAttribute Clientes clientes, RedirectAttributes ra) {
    //     productosRepository.save(clientes);
    //     ra.addAttribute("message", "Producto creado con exito");
    //     return "redirect:/main/inventario";
    // }
    //
    // @GetMapping("/main/productos/update/{id}")
    // public String update(@PathVariable Long id, Model model) {
    //     Clientes clientes = clientesRepository.findById(id).orElse(null);
    //     model.addAttribute("clientes", clientes);
    //     return "auth/register";
    // }

    @PostMapping("/productos/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        productosRepository.deleteById(id);
        ra.addAttribute("message", "Producto eliminado con exito");
        return "redirect:/main/module/inventario";
    }
}