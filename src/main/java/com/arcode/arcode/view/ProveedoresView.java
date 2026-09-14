package com.arcode.arcode.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arcode.arcode.model.Proveedores;
import com.arcode.arcode.repository.ProveedoresRepository;

@Controller
public class ProveedoresView {
    @Autowired
    private ProveedoresRepository proveedoresRepository;
    @GetMapping("/main/module/proveedores")
    public String lista(Model model) {
        model.addAttribute("proveedores", proveedoresRepository.findAll());

        model.addAttribute("proveedor", new Proveedores());
        return "components/proveedores";
    }

    @PostMapping("/proveedores/save")
    public String save(@ModelAttribute("proveedor") Proveedores proveedores, RedirectAttributes ra) {
        proveedores.setEstado("ACTIVO");
        proveedoresRepository.save(proveedores);
        ra.addAttribute("message", "Proveedor creado con exito");
        return "redirect:/main/proveedores";
    }

    @PostMapping("/proveedores/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        proveedoresRepository.deleteById(id);
        ra.addAttribute("message", "Proveedor eliminado con exito");
        return "redirect:/main/th/proveedores";
    }
}