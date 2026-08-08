package com.arcode.arcode.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import com.arcode.arcode.model.Proveedores;
import com.arcode.arcode.repository.ProveedoresRepository;

@Controller
public class ProveedoresView {
    @Autowired
    private ProveedoresRepository proveedoresRepository;

    //TODO: correcgir la ruta /main/th/proveedores a /main/proveedores quitando las rutas relativas de ComponentView
    @GetMapping("/main/th/proveedores")
    public String lista(Model model) {
        model.addAttribute("proveedores", proveedoresRepository.findAll());
        return "components/proveedores";
    }

    // @GetMapping("/clientes/register")
    // public String form(Model model) {
    //     model.addAttribute("clientes", new Clientes());
    //     return "auth/register";
    // }

    // @GetMapping("/clientes/login")
    // public String main() {
    //     return "/auth/login";
    // }

    // @PostMapping("/clientes/save")
    // public String save(@ModelAttribute Clientes clientes, RedirectAttributes ra) {
    //     clientesRepository.save(clientes);
    //     ra.addAttribute("message", "Cliente creado con exito");
    //     return "redirect:/main";
    // }

    // @GetMapping("/main/clientes/update/{id}")
    // public String update(@PathVariable Long id, Model model) {
    //     Clientes clientes = clientesRepository.findById(id).orElse(null);
    //     model.addAttribute("clientes", clientes);
    //     return "auth/register";
    // }

    @PostMapping("/proveedores/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        proveedoresRepository.deleteById(id);
        ra.addAttribute("message", "Proveedor eliminado con exito");
        return "redirect:/main/th/proveedores";
    }
}