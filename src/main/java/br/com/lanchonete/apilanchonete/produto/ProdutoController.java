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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProdutoController {
    @Autowired private ProdutoService serviceProd;
    @Autowired private UserService serviceUser;
    @Autowired private HttpServletRequest request;

    @GetMapping("/vendedor/meus-produtos/{idUser}") 
    public String showProdutoList(@PathVariable("idUser") Integer idUser, Model model, RedirectAttributes ra){
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            try {
                User user = serviceUser.get(idUser);
                Integer id = (Integer) session.getAttribute("idUser");
                List<Produto> listProdutos = serviceProd.listProd(id);
                model.addAttribute("listProdutos", listProdutos);
                model.addAttribute("idUser", id);
                return "meus-produtos";

            } catch (UserNotFoundException e) {
                ra.addFlashAttribute("message", e.getMessage());
                return "redirect:/vendedor";  
            }

        } else {
            return "redirect:/login";  
        }
        
        
    }

    @GetMapping("/vendedor/meus-produtos/new/{idUser}")
    public String showNewForm(@PathVariable("idUser") Integer idUser, Model model, RedirectAttributes ra){
        try {
            User user = serviceUser.get(idUser);
            Produto produto = new Produto();
            produto.setUser(user);
            model.addAttribute("produto", produto);
            model.addAttribute("pageTitle", "Cadastrar Produto");
            return "prodForm";

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/vendedor";  
        }
    }
    
    @PostMapping("/vendedor/meus-produtos/save")
    public String saveProduto(RedirectAttributes ra, Produto produto, Model model){
            HttpSession session = request.getSession();
            Integer idUser = (Integer) session.getAttribute("idUser");
            model.addAttribute("idUser", idUser);
            
            serviceProd.save(produto);
            ra.addFlashAttribute("message", "Produto salvo com sucesso!");

            return "redirect:/vendedor/meus-produtos/{idUser}"; 
    }
    
    @GetMapping("/vendedor/meus-produtos/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("idUser");
        model.addAttribute("idUser", idUser);
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
    
    @GetMapping("/vendedor/meus-produtos/delete/{id}")
    public String deleteProduto(@PathVariable("id") Integer id, RedirectAttributes ra, Model model) {
        HttpSession session = request.getSession();
        Integer idUser = (Integer) session.getAttribute("idUser");
        model.addAttribute("idUser", idUser);
        try {
            serviceProd.delete(id);
            ra.addFlashAttribute("message", "O produto com ID " + id + " foi deletado.");
        } catch (ProdutoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/vendedor/meus-produtos/{idUser}";
    }

    @GetMapping("/vendedor")
    public String vendedor(Model model) {
        HttpSession session = request.getSession();
        if ((Integer)session.getAttribute("nivelUser") == 2) {
            Integer idUser = (Integer) session.getAttribute("idUser");
            model.addAttribute("idUser", idUser);
            return "mainPage";
        } else {
            return "redirect:/login";  
        }
        
    }
}
