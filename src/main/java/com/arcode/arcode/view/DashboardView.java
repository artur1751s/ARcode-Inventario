package com.arcode.arcode.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardView {
    @GetMapping("/main/module/dashboard")
    public String main() {
        return "components/dashboard";
    }

    //TODO: Realizar cargue de informacion de la db en dashboard y mostrar mas informacion dinamica
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

    // @PostMapping("/pedidos/delete/{id}")
    // public String delete(@PathVariable Long id, RedirectAttributes ra) {
    //     pedidosRepository.deleteById(id);
    //     ra.addAttribute("message", "Pedido eliminado con exito");
    //     return "redirect:/main/th/pedidos";
    // }
}