package com.arcode.arcode.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arcode.arcode.model.Pedidos;
import com.arcode.arcode.repository.ClientesRepository;
import com.arcode.arcode.repository.PedidosRepository;

@Controller
public class PedidosView {
    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("/main/module/pedidos")
    public String lista(Model model) {
        model.addAttribute("pedidos", pedidosRepository.findAll());
        model.addAttribute("clientes", clientesRepository.findAll());

        model.addAttribute("pedido", new Pedidos());
        
        return "components/pedidos";
    }

    @PostMapping("/pedidos/save")
    public String save(@ModelAttribute("pedido") Pedidos pedidos, RedirectAttributes ra) {
        pedidosRepository.save(pedidos);
        ra.addAttribute("message", "Pedido creado con exito");
        return "redirect:/main/pedidos";
    }

    @PostMapping("/pedidos/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        pedidosRepository.deleteById(id);
        ra.addAttribute("message", "Pedido eliminado con exito");
        return "redirect:/main/pedidos";
    }
}