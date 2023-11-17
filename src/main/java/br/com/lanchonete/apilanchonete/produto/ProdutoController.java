package br.com.lanchonete.apilanchonete.produto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lanchonete.apilanchonete.user.User;
import br.com.lanchonete.apilanchonete.user.UserNotFoundException;
import br.com.lanchonete.apilanchonete.user.UserService;

@Controller
public class ProdutoController {
    @Autowired private ProdutoService serviceProd;
    @Autowired private UserService serviceUser;

    @GetMapping("/vendedor/meus-produtos/{idUser}")
    public String showProdutoList(@PathVariable("idUser") Integer idUser, Model model, RedirectAttributes ra){
        try {
            User user = serviceUser.get(idUser);
            List<Produto> listProdutos = serviceProd.listProd(user.getId());
            model.addAttribute("listProdutos", listProdutos);
            return "meus-produtos";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/vendedor";  
        }
    }

    @GetMapping("/vendedor/meus-produtos/{idUser}/new")
    public String showNewForm(@PathVariable("idUser") Integer idUser, Model model, RedirectAttributes ra){
        try {
            User user = serviceUser.get(idUser);
            idUser = user.getId();
            model.addAttribute("produto", new Produto());
            model.addAttribute("user", idUser);
            model.addAttribute("pageTitle", "Cadastrar Produto");
            return "prodForm";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/vendedor/meus-produtos/{idUser}";  
        }
    }
    
    @PostMapping("/vendedor/meus-produtos/save")
    public String saveProduto(Model model, RedirectAttributes ra, Produto produto){
            serviceProd.save(produto);
            ra.addFlashAttribute("message", "Produto salvo com sucesso!");
            return "redirect:/vendedor/meus-produtos/{idUser}"; 
    }
    
    @GetMapping("/vendedor/meus-produtos/{idUser}/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Produto produto = serviceProd.get(id);
            model.addAttribute("produto", produto);
            model.addAttribute("pageTitle", "Editar Produto (ID: " + id + ")");
            return "prodForm";
        } catch (ProdutoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/vendedor/meus-produtos/{idUser}";  
        }
    }
    
    @GetMapping("/vendedor/meus-produtos/{idUser}/delete/{id}")
    public String deleteProduto(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            serviceProd.delete(id);
            ra.addFlashAttribute("message", "O produto com ID " + id + " foi deletado.");
        } catch (ProdutoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/vendedor/meus-produtos/{idUser}";
    }
}
