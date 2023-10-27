package br.com.lanchonete.apilanchonete.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProdutoController {
    @Autowired private ProdutoService service;

    @GetMapping("/meus-produtos")
    public String showProdutoList(Model model){
        List<Produto> listProdutos = service.listAll();
        model.addAttribute("listProdutos", listProdutos);
        return "meus-produtos";
    }

    @GetMapping("/meus-produtos/new")
    public String showNewForm(Model model){
        model.addAttribute("produto", new Produto());
        model.addAttribute("pageTitle", "Cadastrar Produto");
        return "prodForm";
    }
    
    @PostMapping("/meus-produtos/save")
    public String saveProduto(Produto produto, RedirectAttributes ra){
        service.save(produto);
        ra.addFlashAttribute("message", "Produto salvo com sucesso!");
        return "redirect:/meus-produtos";  
    }
    
    @GetMapping("/meus-produtos/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Produto produto = service.get(id);
            model.addAttribute("produto", produto);
            model.addAttribute("pageTitle", "Editar Produto (ID: " + id + ")");
            return "prodForm";
        } catch (ProdutoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/meus-produtos";  
        }
    }
    
    @GetMapping("/produtos/delete/{id}")
    public String deleteProduto(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "O produto com ID " + id + " foi deletado.");
        } catch (ProdutoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/meus-produtos";
    }
}
